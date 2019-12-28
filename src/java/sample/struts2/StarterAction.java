/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.area.AreaDAO;
import sample.area.AreaDTO;
import sample.roomType.RoomTypeDAO;
import sample.roomType.RoomTypeDTO;

/**
 *
 * @author Admin
 */
public class StarterAction {
    private final String SUCCESS ="success";
    private Map<String,String> arealist ;
    private Map<String,String> roomTypelist ;
    public StarterAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        //get all area
        AreaDAO areaDao = new AreaDAO();
        areaDao.loadAllArea();
        List<AreaDTO> listOfArea = areaDao.getListOfArea();
        arealist = new HashMap<>();
        for (AreaDTO areaDTO : listOfArea) {
            arealist.put(areaDTO.getAreaCode(), areaDTO.getArea());
        }
        session.setAttribute("LIST_AREA", arealist);
        
        //get all type of room
        RoomTypeDAO dtDao = new RoomTypeDAO();
        dtDao.loadAllRoomType();
        List<RoomTypeDTO> listOfRoomType = dtDao.getListOfRoomType();
        roomTypelist = new HashMap<>();
        for (RoomTypeDTO roomTypeDTO : listOfRoomType) {
            roomTypelist.put(roomTypeDTO.getRoomTypeID(), roomTypeDTO.getType());
        }
        session.setAttribute("LIST_ROOM_TYPES", roomTypelist);
        
        return url;
    }

    /**
     * @return the arealist
     */
    public Map<String,String> getArealist() {
        return arealist;
    }

    /**
     * @param arealist the arealist to set
     */
    public void setArealist(Map<String,String> arealist) {
        this.arealist = arealist;
    }

    /**
     * @return the roomTypelist
     */
    public Map<String,String> getRoomTypelist() {
        return roomTypelist;
    }

    /**
     * @param roomTypelist the roomTypelist to set
     */
    public void setRoomTypelist(Map<String,String> roomTypelist) {
        this.roomTypelist = roomTypelist;
    }
    
}
