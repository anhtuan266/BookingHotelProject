/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import sample.utils.DBU;

/**
 *
 * @author Admin
 */
public class BookingDAO {
    public void addNewBooking(String bookingId,String userEmail,String discountCode) throws SQLException, NamingException
    {
         Connection con = null;
        PreparedStatement pst = null;
        try
        { 
            con = DBU.makeConnection();
            if ( con != null)
            {
                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String bookingdate = myFormat.format(date);
                String sql = "insert into tbl_Booking(bookingID,userEmail,discountCode,status,bookingDate) values (?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, bookingId);
                pst.setString(2,userEmail);
                if (!discountCode.isEmpty())
                {
                    pst.setString(3,discountCode);
                }
                else
                {
                    pst.setNull(3, java.sql.Types.NVARCHAR);
                }
                pst.setBoolean(4,true);
                pst.setString(5,bookingdate);
                int row = pst.executeUpdate();
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
}
