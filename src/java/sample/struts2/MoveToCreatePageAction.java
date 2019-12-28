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
import sample.role.RolesDAO;
import sample.role.RolesDTO;

/**
 *
 * @author Admin
 */
public class MoveToCreatePageAction {
    private final String SUCCESS = "success";
    private Map<String,String> listOfRoles;

    public Map<String, String> getListOfRoles() {
        return listOfRoles;
    }

    public void setListOfRoles(Map<String, String> listOfRoles) {
        this.listOfRoles = listOfRoles;
    }
    
    public MoveToCreatePageAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        RolesDAO dao = new RolesDAO();
        dao.loadAllRoles();
        List<RolesDTO> listRoles = dao.getListOfRoles();
        listOfRoles = new HashMap<>();
        for (RolesDTO listRole : listRoles) {
            listOfRoles.put(listRole.getRoleID(), listRole.getRoleName());
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("LIST_ROLES", listOfRoles);
        return url;
    }
    
}
