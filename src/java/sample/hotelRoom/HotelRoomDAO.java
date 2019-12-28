/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.hotelRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBU;

/**
 *
 * @author Admin
 */
public class HotelRoomDAO {
    private List<HotelRoomDTO> listOfHotels = null;
    public List<HotelRoomDTO> getListOfHotels()
    {
        return listOfHotels;
    }
    public void loadHotelAndRoomInfo(String areaCode) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if (con != null) {
                String sql = "select H.hotelID,hotelName,roomID,R.roomTypeID, T.type,description,amount,price"
                        + " from tbl_HotelRoom R inner join tbl_Hotels H"
                        + " on H.hotelID = R.hotelID"
                        + " inner join tbl_RoomTypes T"
                        + " on T.roomTypeID = R.roomTypeID"
                        + " where areaCode = ?";
                if (listOfHotels == null)
                {
                    listOfHotels = new ArrayList<>();
                }
                pst = con.prepareStatement(sql);
                pst.setString(1, areaCode);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String hotelID = rs.getString("hotelID");
                    String hotelName = rs.getString("hotelName");
                    String roomID = rs.getString("roomID");
                    String roomType = rs.getString("type");
                    String description = rs.getString("description");
                    int realAmount = rs.getInt("amount");
                    float price = rs.getFloat("price");
                    String roomTypeID = rs.getString("roomTypeID");
                    
                    HotelRoomDTO dto = new HotelRoomDTO(hotelID, hotelName, roomID,roomTypeID,roomType, description, realAmount, price);
                    listOfHotels.add(dto);
                }
            }
        }
        finally
        {
            if ( con != null)
            {
                con.close();
            }
            if ( pst != null)
            {
                pst.close();
            }
            if ( rs != null)
            {
                rs.close();
            }
        } 
    }
}
