/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.bookingDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBU;

/**
 *
 * @author Admin
 */
public class BookingDetailsDAO {
    private List<BookingDetailsDTO> listOfBookingDetails = null;
    public List<BookingDetailsDTO> getListOfBookingDetails()
    {
        return listOfBookingDetails;
    }
    public void loadBookingDetails() throws SQLException, NamingException
    {
        Connection con = null;
        Statement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if (con != null) {
                    String sql = "select B.bookingID, roomID, checkInDate,checkOutDate,amount"
                            + " from tbl_BookingDetails B inner join tbl_Booking K"
                            + " on B.bookingID = K.bookingID where K.status = 'true' and B.status = 'true'";
                if (listOfBookingDetails == null)
                {
                    listOfBookingDetails = new ArrayList<>();
                }
                pst = con.createStatement();
                rs = pst.executeQuery(sql);
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String roomID = rs.getString("roomID");
                    String checkInDate = rs.getString("checkInDate");
                    String checkOutDate = rs.getString("checkOutDate");
                    int amount = rs.getInt("amount");
                    
                    BookingDetailsDTO dto = new BookingDetailsDTO(bookingID, roomID, checkInDate, checkOutDate, amount);
                    listOfBookingDetails.add(dto);
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
    
     public void addNewRecord(String bookingId,String roomid, String checkin,String checkout,int amount) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement pst = null;
        try
        {
            con = DBU.makeConnection();
            if (con != null) {
                    String sql = "insert into tbl_BookingDetails(bookingID,roomID,checkInDate,checkOutDate,amount,status)"
                            + " values(?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, bookingId);
                pst.setString(2, roomid);
                pst.setString(3, checkin);
                pst.setString(4, checkout);
                pst.setInt(5, amount);
                pst.setBoolean(6, true);
                pst.executeUpdate();
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
        } 
    }
     
     public void loadBookingHistory(String userEmail) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if (con != null) {
                    String sql = "select E.type,T.hotelName, K.bookingDate, B.bookingID, B.roomID, checkInDate,checkOutDate,B.amount,K.discountCode,(B.amount*h.price) as totalPrice"
                            + " from tbl_BookingDetails B"
                            + " inner join tbl_Booking K"
                            + " on B.bookingID = K.bookingID"
                            + " inner join tbl_HotelRoom H"
                            + " on H.roomID = B.roomID"
                            + " inner join tbl_Hotels T"
                            + " on T.hotelID = h.hotelID"
                            + " inner join tbl_RoomTypes E"
                            + " on E.roomTypeID = H.roomTypeID"
                            + " where K.status = 'true' and B.status = 'true' and K.userEmail = ? order by K.bookingDate";
                if (listOfBookingDetails == null)
                {
                    listOfBookingDetails = new ArrayList<>();
                }
                pst = con.prepareStatement(sql);
                pst.setString(1, userEmail);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String roomID = rs.getString("roomID");
                    String checkInDate = rs.getString("checkInDate");
                    String checkOutDate = rs.getString("checkOutDate");
                    int amount = rs.getInt("amount");
                    String bookingDate =rs.getString("bookingDate");
                    String roomtype = rs.getString("type");
                    String hotelname =rs.getString("hotelName");
                    String discountCode =rs.getString("discountCode");
                    float totalprice =rs.getFloat("totalPrice");
                    BookingDetailsDTO dto = new BookingDetailsDTO(bookingID, roomID, checkInDate, checkOutDate, amount,bookingDate,hotelname,roomtype,discountCode,totalprice);
                    listOfBookingDetails.add(dto);
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
     
     public void searchByHotelNameInHistory(String userEmail, String name) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if (con != null) {
                    String sql = "select E.type,T.hotelName, K.bookingDate, B.bookingID, B.roomID, checkInDate,checkOutDate,B.amount,K.discountCode,(B.amount*h.price) as totalPrice"
                            + " from tbl_BookingDetails B"
                            + " inner join tbl_Booking K"
                            + " on B.bookingID = K.bookingID"
                            + " inner join tbl_HotelRoom H"
                            + " on H.roomID = B.roomID"
                            + " inner join tbl_Hotels T"
                            + " on T.hotelID = h.hotelID"
                            + " inner join tbl_RoomTypes E"
                            + " on E.roomTypeID = H.roomTypeID"
                            + " where K.status = 'true' and B.status = 'true' and K.userEmail = ? and T.hotelName like ? order by K.bookingDate";
                if (listOfBookingDetails == null)
                {
                    listOfBookingDetails = new ArrayList<>();
                }
                pst = con.prepareStatement(sql);
                pst.setString(1, userEmail);
                pst.setString(2,"%"+name+"%");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String roomID = rs.getString("roomID");
                    String checkInDate = rs.getString("checkInDate");
                    String checkOutDate = rs.getString("checkOutDate");
                    int amount = rs.getInt("amount");
                    String bookingDate =rs.getString("bookingDate");
                    String roomtype = rs.getString("type");
                    String hotelname =rs.getString("hotelName");
                    String discountCode =rs.getString("discountCode");
                    float totalprice =rs.getFloat("totalPrice");
                    BookingDetailsDTO dto = new BookingDetailsDTO(bookingID, roomID, checkInDate, checkOutDate, amount,bookingDate,hotelname,roomtype,discountCode,totalprice);
                    listOfBookingDetails.add(dto);
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
     
     public void searchByDateInHistory(String userEmail, String date) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if (con != null) {
                    String sql = "select E.type,T.hotelName, K.bookingDate, B.bookingID, B.roomID, checkInDate,checkOutDate,B.amount,K.discountCode,(B.amount*h.price) as totalPrice"
                            + " from tbl_BookingDetails B"
                            + " inner join tbl_Booking K"
                            + " on B.bookingID = K.bookingID"
                            + " inner join tbl_HotelRoom H"
                            + " on H.roomID = B.roomID"
                            + " inner join tbl_Hotels T"
                            + " on T.hotelID = h.hotelID"
                            + " inner join tbl_RoomTypes E"
                            + " on E.roomTypeID = H.roomTypeID"
                            + " where K.status = 'true' and B.status = 'true' and K.userEmail = ? and K.bookingDate between ? and ? order by K.bookingDate";
                if (listOfBookingDetails == null)
                {
                    listOfBookingDetails = new ArrayList<>();
                }
                pst = con.prepareStatement(sql);
                pst.setString(1, userEmail);
                pst.setString(2,date+" 00:00:00");
                pst.setString(3,date+" 23:59:59");
                rs = pst.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String roomID = rs.getString("roomID");
                    String checkInDate = rs.getString("checkInDate");
                    String checkOutDate = rs.getString("checkOutDate");
                    int amount = rs.getInt("amount");
                    String bookingDate =rs.getString("bookingDate");
                    String roomtype = rs.getString("type");
                    String hotelname =rs.getString("hotelName");
                    String discountCode =rs.getString("discountCode");
                    float totalprice =rs.getFloat("totalPrice");
                    BookingDetailsDTO dto = new BookingDetailsDTO(bookingID, roomID, checkInDate, checkOutDate, amount,bookingDate,hotelname,roomtype,discountCode,totalprice);
                    listOfBookingDetails.add(dto);
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
     
     public void removeRecord(String bookingID, String roomID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = DBU.makeConnection();
            if (con != null) {
                String sql = "update tbl_BookingDetails set status ='false' where bookingID = ? and roomID = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, bookingID);
                pst.setString(2, roomID);
                int row = pst.executeUpdate();
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
