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
public class HotelRoomDTO {
    private String hotelID;
    private String hotelName;
    private String roomID;
    private String roomTypeID;
    private String roomType;
    private String description;
    private int realAmount;
    private float price;

    public HotelRoomDTO() {
    }

    public HotelRoomDTO(String hotelID, String hotelName, String roomID, String roomTypeID, String roomType, String description, int realAmount, float price) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.roomID = roomID;
        this.roomTypeID = roomTypeID;
        this.roomType = roomType;
        this.description = description;
        this.realAmount = realAmount;
        this.price = price;
    }

    /**
     * @return the hotelID
     */
    public String getHotelID() {
        return hotelID;
    }

    /**
     * @param hotelID the hotelID to set
     */
    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
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
     * @return the roomTypeID
     */
    public String getRoomTypeID() {
        return roomTypeID;
    }

    /**
     * @param roomTypeID the roomTypeID to set
     */
    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof HotelRoomDTO))
        {
            return false;
        }
        HotelRoomDTO other = (HotelRoomDTO) obj;
        if ((this.roomID == null && other.roomID != null)
                ||(this.roomID != null && !this.roomID.equals(other.roomID)))
        {
            return false;
        }
        return true;
    }
   
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash +=(roomID != null ? roomID.hashCode() : 0);
        return hash;
    }
   
}
