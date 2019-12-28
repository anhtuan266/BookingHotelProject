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
public class AddToBookingCartAction {
    private final String SUCCESS = "success";
    
    private String txtHotelName;
    private String txtRoomType;
    private String txtRoomID;
    private String txtDescription;
    private String txtPrice;
    private String txtCheckIn;
    private String txtCheckOut;
    private String txtSearchArea;
    private String txtSearchAmount;
    private String txtSearchRoomType;
    private String txtRealAmount;
    
    public AddToBookingCartAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        float price = Float.parseFloat(txtPrice);
        int realAmount = Integer.parseInt(getTxtRealAmount());
        CombineDTO dto = new CombineDTO(txtRoomID, txtRoomType, txtHotelName, txtDescription, txtCheckIn, txtCheckOut, price,realAmount);
        bookingCartOBJ cart = (bookingCartOBJ) session.getAttribute("CART");
        if (cart == null) {
            cart = new bookingCartOBJ();
        }
        //add new obj to cart
        cart.addItemToCart(dto);
        //calculate total price
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

    /**
     * @return the txtHotelName
     */
    public String getTxtHotelName() {
        return txtHotelName;
    }

    /**
     * @param txtHotelName the txtHotelName to set
     */
    public void setTxtHotelName(String txtHotelName) {
        this.txtHotelName = txtHotelName;
    }

    /**
     * @return the txtRoomType
     */
    public String getTxtRoomType() {
        return txtRoomType;
    }

    /**
     * @param txtRoomType the txtRoomType to set
     */
    public void setTxtRoomType(String txtRoomType) {
        this.txtRoomType = txtRoomType;
    }

    /**
     * @return the txtRoomID
     */
    public String getTxtRoomID() {
        return txtRoomID;
    }

    /**
     * @param txtRoomID the txtRoomID to set
     */
    public void setTxtRoomID(String txtRoomID) {
        this.txtRoomID = txtRoomID;
    }

    /**
     * @return the txtDescription
     */
    public String getTxtDescription() {
        return txtDescription;
    }

    /**
     * @param txtDescription the txtDescription to set
     */
    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    /**
     * @return the txtPrice
     */
    public String getTxtPrice() {
        return txtPrice;
    }

    /**
     * @param txtPrice the txtPrice to set
     */
    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    /**
     * @return the txtCheckIn
     */
    public String getTxtCheckIn() {
        return txtCheckIn;
    }

    /**
     * @param txtCheckIn the txtCheckIn to set
     */
    public void setTxtCheckIn(String txtCheckIn) {
        this.txtCheckIn = txtCheckIn;
    }

    /**
     * @return the txtCheckOut
     */
    public String getTxtCheckOut() {
        return txtCheckOut;
    }

    /**
     * @param txtCheckOut the txtCheckOut to set
     */
    public void setTxtCheckOut(String txtCheckOut) {
        this.txtCheckOut = txtCheckOut;
    }

    /**
     * @return the txtSearchArea
     */
    public String getTxtSearchArea() {
        return txtSearchArea;
    }

    /**
     * @param txtSearchArea the txtSearchArea to set
     */
    public void setTxtSearchArea(String txtSearchArea) {
        this.txtSearchArea = txtSearchArea;
    }

    /**
     * @return the txtSearchAmount
     */
    public String getTxtSearchAmount() {
        return txtSearchAmount;
    }

    /**
     * @param txtSearchAmount the txtSearchAmount to set
     */
    public void setTxtSearchAmount(String txtSearchAmount) {
        this.txtSearchAmount = txtSearchAmount;
    }

    /**
     * @return the txtSearchRoomType
     */
    public String getTxtSearchRoomType() {
        return txtSearchRoomType;
    }

    /**
     * @param txtSearchRoomType the txtSearchRoomType to set
     */
    public void setTxtSearchRoomType(String txtSearchRoomType) {
        this.txtSearchRoomType = txtSearchRoomType;
    }

    /**
     * @return the txtRealAmount
     */
    public String getTxtRealAmount() {
        return txtRealAmount;
    }

    /**
     * @param txtRealAmount the txtRealAmount to set
     */
    public void setTxtRealAmount(String txtRealAmount) {
        this.txtRealAmount = txtRealAmount;
    }
    
}
