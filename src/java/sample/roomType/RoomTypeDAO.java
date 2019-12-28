/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.roomType;

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
public class RoomTypeDAO {
    private List<RoomTypeDTO> listOfRoomType;
    public List<RoomTypeDTO> getListOfRoomType()
    {
        return  listOfRoomType;
    }
    
    public void loadAllRoomType() throws SQLException, NamingException
    {
        Connection con = null;
        Statement pst = null;
        ResultSet rs = null;
        try
        {
            con = DBU.makeConnection();
            if ( con != null)
            {
                String sql = "select roomTypeID, type from tbl_RoomTypes";
                pst = con.createStatement();
                rs = pst.executeQuery(sql);
                if ( listOfRoomType == null)
                {
                    listOfRoomType = new ArrayList<>();
                }
                while(rs.next())
                {
                    String roomTypeID = rs.getString("roomTypeID");
                    String type = rs.getString("type");
                    
                    RoomTypeDTO dto = new RoomTypeDTO(roomTypeID, type);
                    
                    listOfRoomType.add(dto);
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
