/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.combine;

import sample.hotelRoom.HotelRoomDTO;

/**
 *
 * @author Admin
 */
public class CombineDTO {
    private String roomID;
    private String roomtype;
    private String hotelName;
    private String roomDescription;
    private String checkInDate;
    private String checkOutDate;
    private float pice;
    private int realAmount;

    public CombineDTO(String roomID, String roomtype, String hotelName, String roomDescription, String checkInDate, String checkOutDate, float pice, int realAmount) {
        this.roomID = roomID;
        this.roomtype = roomtype;
        this.hotelName = hotelName;
        this.roomDescription = roomDescription;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.pice = pice;
        this.realAmount = realAmount;
    }

   

    public CombineDTO() {
    }
    
   
    
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof CombineDTO))
        {
            return false;
        }
        CombineDTO other = (CombineDTO) obj;
        if ((this.getRoomID() == null && other.getRoomID() != null)
                ||(this.getRoomID() != null && !this.roomID.equals(other.roomID)))
        {
            return false;
        }
        return true;
    }
   
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash +=(getRoomID() != null ? getRoomID().hashCode() : 0);
        return hash;
    }

    /**
     * @return the roomID
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * @param roomID the roomID to set
     */
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    /**
     * @return the roomtype
     */
    public String getRoomtype() {
        return roomtype;
    }

    /**
     * @param roomtype the roomtype to set
     */
    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return the roomDescription
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * @param roomDescription the roomDescription to set
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    /**
     * @return the checkInDate
     */
    public String getCheckInDate() {
        return checkInDate;
    }

    /**
     * @param checkInDate the checkInDate to set
     */
    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * @return the checkOutDate
     */
    public String getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * @param checkOutDate the checkOutDate to set
     */
    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * @return the pice
     */
    public float getPice() {
        return pice;
    }

    /**
     * @param pice the pice to set
     */
    public void setPice(float pice) {
        this.pice = pice;
    }

    /**
     * @return the realAmount
     */
    public int getRealAmount() {
        return realAmount;
    }

    /**
     * @param realAmount the realAmount to set
     */
    public void setRealAmount(int realAmount) {
        this.realAmount = realAmount;
    }

}
