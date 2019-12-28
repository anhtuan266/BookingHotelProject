package sample.struts2;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.booking.BookingDAO;
import sample.bookingDetails.BookingDetailsDAO;
import sample.bookingDetails.bookingCartOBJ;
import sample.combine.CombineDTO;
import sample.discount.DiscountDAO;
import sample.user.UserDTO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class ConfirmBookingAction {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    
    public ConfirmBookingAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        //check that user completed captcha or not
         String captchaRespone = request.getParameter("g-recaptcha-response");
        if (captchaRespone == null || captchaRespone.length() <= 0)
        {
            session.setAttribute("CAPTCHA_ERROR", "please complete the captcha");
            url = FAIL;
            return url;
        }
        String secretKey = "6Ldxi8YUAAAAAMpc_KMEwuorQDybiq6IMvCNkuBy";
        JsonObject jsonObject = null;
        URLConnection connection = null;
        InputStream input = null;
//        create url connect to verify site to get result
        String verifyUrl = "https://www.google.com/recaptcha/api/siteverify";
        connection = new URL(verifyUrl + "?" + "secret="+secretKey+"&response="+captchaRespone).openConnection();
        input = connection.getInputStream();
        JsonReader rdr = Json.createReader(input);
        jsonObject = rdr.readObject();
        boolean result = jsonObject.getBoolean("success");
        session.removeAttribute("CAPTCHA_ERROR");
        if (!result)
        {
            session.setAttribute("CAPTCHA_ERROR", "you are not human");
            url = FAIL;
            return url;
        }
        //end check captcha
        //save cart record after passed captcha test
        DiscountDAO discDao = new DiscountDAO();
        BookingDAO bookingDao = new BookingDAO();
        BookingDetailsDAO bdDao = new BookingDetailsDAO();
        bookingCartOBJ cart = (bookingCartOBJ) session.getAttribute("CART");
        String error = "";
        UserDTO currentUser = (UserDTO) session.getAttribute("USER");
        session.removeAttribute("BOOKING_ERROR");
        for (CombineDTO combine : cart.getItems().keySet()) {
            //if room is out of stock
            if ( combine.getRealAmount() - cart.getItems().get(combine) < 0)
            {
                error = "there is not enough "+combine.getRoomtype()+" room in hotel "+combine.getHotelName();
                session.setAttribute("BOOKING_ERROR", error);
                url = FAIL;
                return url;
            }
        }
        //get current date to make bookingid
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String bookingId = myFormat.format(date);
        //check discount code
        if(session.getAttribute("DISCOUNT_CODE") != null)
        {
            String txtDiscount = (String) session.getAttribute("DISCOUNT_CODE");
            bookingDao.addNewBooking(bookingId,currentUser.getEmail(), txtDiscount);
            discDao.minusDiscountAmount(txtDiscount);
        }
        else
        {
            bookingDao.addNewBooking(bookingId,currentUser.getEmail(),"");
        }
        //add to booking details
        for (CombineDTO combine : cart.getItems().keySet()) {
            bdDao.addNewRecord(bookingId, combine.getRoomID()
                    , combine.getCheckInDate(), combine.getCheckOutDate(),cart.getItems().get(combine));
        }
        session.removeAttribute("CART");
        session.removeAttribute("DISCOUNT_CODE");
        session.removeAttribute("DISCOUNT_PERCENT");
        return url;
    }
    
}
