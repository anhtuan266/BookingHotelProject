/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.discount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import sample.utils.DBU;

/**
 *
 * @author Admin
 */
public class DiscountDAO {
    public int checkDiscountCode(String discountCode) throws SQLException, NamingException, ParseException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                Date currentDate = new Date();
                String sql ="select amount, expiryDate from tbl_Discounts where discountCode = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, discountCode);
                rs = pst.executeQuery();
                if(rs.next())
                {
                    String amountStr = rs.getString("amount");
                    int amount = Integer.parseInt(amountStr);
                    //code is out of stock
                    if ( amount <= 0)
                    {
                        return 1;
                    }
                    SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdformat.parse(rs.getString("expiryDate"));
                    if (date.before(currentDate))
                    {
                        return 2;
                    }
                }
                else
                {
                    return 3;
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
        return 4;
    }
    public int getDiscountPercent(String discountCode) throws SQLException, NamingException, ParseException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                String sql ="select percentDiscount from tbl_Discounts where discountCode = ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, discountCode);
                rs = pst.executeQuery();
                if(rs.next())
                {
                    int result = rs.getInt("percentDiscount");
                    return result;
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
        return -1;
    }
    
    public void minusDiscountAmount(String discountCode) throws SQLException, NamingException, ParseException
    {
        Connection con = null;
        PreparedStatement pst = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                String sql ="update tbl_Discounts set amount = amount - ? where discountCode = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, 1);
                pst.setString(2, discountCode);
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
}
