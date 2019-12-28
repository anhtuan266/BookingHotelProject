/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.bookingDetails.bookingCartOBJ;
import sample.combine.CombineDTO;

/**
 *
 * @author Admin
 */
public class UpdateRoomAmountAction {
    private final String SUCCESS = "success";
    private String txtRoomID;
    private String txtAmount;

    public String getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(String txtAmount) {
        this.txtAmount = txtAmount;
    }
    
    public String getTxtRoomID() {
        return txtRoomID;
    }

    public void setTxtRoomID(String txtRoomID) {
        this.txtRoomID = txtRoomID;
    }
    
    public UpdateRoomAmountAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        int newAmount = Integer.parseInt(txtAmount);
        bookingCartOBJ cart = (bookingCartOBJ) session.getAttribute("CART");
        cart.UpdateItemQuantity(txtRoomID, newAmount);

        float totalPrice = 0;
        for (CombineDTO combine : cart.getItems().keySet()) {
            //calculate days between checkin and checkout
            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date1 = myFormat.parse(combine.getCheckInDate());
            Date date2 = myFormat.parse(combine.getCheckOutDate());
            long diff = date2.getTime() - date1.getTime();
            int d = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            int quantity = cart.getItems().get(combine);
            totalPrice += combine.getPice() * quantity * d;
        }
        session.setAttribute("CART", cart);
        session.setAttribute("TOTAL_PRICE", totalPrice);
        //recalculate total price after discount
        if ( session.getAttribute("DISCOUNT_PERCENT") != null)
        {
            int percent  = (int) session.getAttribute("DISCOUNT_PERCENT");
            float priceAfterDiscount  = totalPrice -(totalPrice * percent / 100);
            session.setAttribute("TOTAL_PRICE_AFTER_DISCOUNT", priceAfterDiscount);
        }
        return url;
    }
    
}
