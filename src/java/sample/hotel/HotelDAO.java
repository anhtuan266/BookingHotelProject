/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.hotel;

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
public class HotelDAO {
    public List<String> selectHotelInAre(String areaCode) throws SQLException, NamingException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String> listOfHotels = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                String sql = "select hotelID from tbl_Hotels where areaCode = ?";
                listOfHotels = new ArrayList<>();
                pst = con.prepareStatement(sql);
                pst.setString(1, areaCode);
                rs = pst.executeQuery();
                while(rs.next())
                {
                    String hotelID = rs.getString("hotelID");
                    listOfHotels.add(hotelID);
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
             return listOfHotels;   
    }
    
    
}
