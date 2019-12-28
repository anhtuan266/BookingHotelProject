/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.hotelRoom;

/**
 *
 * @author Admin
 */
public class searchHotelError {
    private String checkInDateIsBeforeCurrentDate;
    private String checkOutDateIsBeforeCheckInDate;
    private String invalidDateFormat;

    /**
     * @return the checkInDateIsBeforeCurrentDate
     */
    public String getCheckInDateIsBeforeCurrentDate() {
        return checkInDateIsBeforeCurrentDate;
    }

    /**
     * @param checkInDateIsBeforeCurrentDate the checkInDateIsBeforeCurrentDate to set
     */
    public void setCheckInDateIsBeforeCurrentDate(String checkInDateIsBeforeCurrentDate) {
        this.checkInDateIsBeforeCurrentDate = checkInDateIsBeforeCurrentDate;
    }

    /**
     * @return the checkOutDateIsBeforeCheckInDate
     */
    public String getCheckOutDateIsBeforeCheckInDate() {
        return checkOutDateIsBeforeCheckInDate;
    }

    /**
     * @param checkOutDateIsBeforeCheckInDate the checkOutDateIsBeforeCheckInDate to set
     */
    public void setCheckOutDateIsBeforeCheckInDate(String checkOutDateIsBeforeCheckInDate) {
        this.checkOutDateIsBeforeCheckInDate = checkOutDateIsBeforeCheckInDate;
    }

    /**
     * @return the invalidDateFormat
     */
    public String getInvalidDateFormat() {
        return invalidDateFormat;
    }

    /**
     * @param invalidDateFormat the invalidDateFormat to set
     */
    public void setInvalidDateFormat(String invalidDateFormat) {
        this.invalidDateFormat = invalidDateFormat;
    }

    
    
}
