/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.area;

import java.sql.Connection;
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
public class AreaDAO {
    private List<AreaDTO> listOfArea;
    public List<AreaDTO> getListOfArea()
    {
        return  listOfArea;
    }
    
    public void loadAllArea() throws SQLException, NamingException
    {
        Connection con = null;
        Statement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                String sql = "select areaCode, area from tbl_Areas";
                pst = con.createStatement();
                rs = pst.executeQuery(sql);
                if ( listOfArea == null)
                {
                    listOfArea = new ArrayList<>();
                }
                while(rs.next())
                {
                    String areacode = rs.getString("areaCode");
                    String area = rs.getString("area");
                    
                    AreaDTO dto = new AreaDTO(areacode, area);
                    
                    listOfArea.add(dto);
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
