/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.role;

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
public class RolesDAO {
    private List<RolesDTO> listOfRoles;
    public List<RolesDTO> getListOfRoles()
    {
        return  listOfRoles;
    }
    
    public void loadAllRoles() throws SQLException, NamingException
    {
        Connection con = null;
        Statement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                String sql = "select roleId, roleName from tbl_Roles";
                pst = con.createStatement();
                rs = pst.executeQuery(sql);
                
                while(rs.next())
                {
                    if (listOfRoles == null)
                    {
                        listOfRoles = new ArrayList<>();
                    }
                    String id = rs.getString("roleId");
                    String name = rs.getString("roleName");
                    RolesDTO dto = new RolesDTO(id, name);
                    listOfRoles.add(dto);
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
