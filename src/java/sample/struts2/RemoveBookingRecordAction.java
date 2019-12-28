/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import sample.bookingDetails.BookingDetailsDAO;

/**
 *
 * @author Admin
 */
public class RemoveBookingRecordAction {
    private final String SUCCESS = "success";
    private String pkBookingID;
    private String pkRoomID;
    
    public RemoveBookingRecordAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        BookingDetailsDAO dao = new BookingDetailsDAO();
        dao.removeRecord(pkBookingID, pkRoomID);
        return url;
    }

    /**
     * @return the pkBookingID
     */
    public String getPkBookingID() {
        return pkBookingID;
    }

    /**
     * @param pkBookingID the pkBookingID to set
     */
    public void setPkBookingID(String pkBookingID) {
        this.pkBookingID = pkBookingID;
    }

    /**
     * @return the pkRoomID
     */
    public String getPkRoomID() {
        return pkRoomID;
    }

    /**
     * @param pkRoomID the pkRoomID to set
     */
    public void setPkRoomID(String pkRoomID) {
        this.pkRoomID = pkRoomID;
    }
    
}
