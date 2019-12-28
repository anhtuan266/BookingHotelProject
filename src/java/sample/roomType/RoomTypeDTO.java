/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.roomType;

/**
 *
 * @author Admin
 */
public class RoomTypeDTO {
    private String roomTypeID;
    private String type;

    public RoomTypeDTO(String roomTypeID, String type) {
        this.roomTypeID = roomTypeID;
        this.type = type;
    }

    public RoomTypeDTO() {
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
}
