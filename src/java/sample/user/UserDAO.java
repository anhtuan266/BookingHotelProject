/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import sample.utils.DBU;
import sample.utils.PasswordUtilities;

/**
 *
 * @author Admin
 */
public class UserDAO {
    public UserDTO checkLogin(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException
    {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        UserDTO dto = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                String encPass = PasswordUtilities.makeEncryptPassword(password);
                String sql = "select email,name,roleID from tbl_Users where email = ? and password = ? and active = 'true'";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, encPass);
                rs = pst.executeQuery();
                while(rs.next())
                {
                    String userEmail = rs.getString("email");
                    String name = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    dto = new UserDTO(userEmail, name, roleID);
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
        return dto;
    }
    public boolean registNewUser(String email, String name,String password,String phone, String address) throws NamingException, SQLException, NoSuchAlgorithmException
    {
        Connection con = null;
        PreparedStatement pst = null;
        String dateStr = "";
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
                Date date = new Date();  
                dateStr = formatter.format(date);  
                String encPass = PasswordUtilities.makeEncryptPassword(password);
                String sql = "insert into tbl_Users (email,name,password,phone,address,createDate,active,roleID)"
                        + " values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, name);
                pst.setString(3, encPass);
                pst.setString(4, phone);
                pst.setString(5, address);
                pst.setString(6, dateStr);
                pst.setBoolean(7, true);
                pst.setString(8,"cus");
                int row = pst.executeUpdate();
                if ( row > 0)
                {
                    return true;
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
        }
        return false;
    }
    
    public boolean createNewUser(String email, String name,String password,String phone, String address,String role) throws NamingException, SQLException, NoSuchAlgorithmException
    {
        Connection con = null;
        PreparedStatement pst = null;
        String dateStr = "";
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
                Date date = new Date();  
                dateStr = formatter.format(date);  
                String encPass = PasswordUtilities.makeEncryptPassword(password);
                String sql = "insert into tbl_Users (email,name,password,phone,address,createDate,active,roleID)"
                        + " values(?,?,?,?,?,?,?,?)";
                pst = con.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, name);
                pst.setString(3, encPass);
                pst.setString(4, phone);
                pst.setString(5, address);
                pst.setString(6, dateStr);
                pst.setBoolean(7, true);
                pst.setString(8,role);
                int row = pst.executeUpdate();
                if ( row > 0)
                {
                    return true;
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
        }
        return false;
    }
}
