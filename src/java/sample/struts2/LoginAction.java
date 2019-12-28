/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Admin
 */
public class LoginAction {
    private final String SUCCESS = "success";
    private final String TRY = "try";
    private final String FAIL = "fail";
    private String txtEmail;
    private String txtPassword;
    private String txtCheckIn;
    private String txtCheckOut;
    private String txtSearchArea;
    private String txtSearchAmount;
    private String txtSearchRoomType;

    public String getTxtEmail() {
        return txtEmail;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }
    
    public LoginAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        //if input value is empty
        if(txtEmail.isEmpty() || txtPassword.isEmpty())
        {
            return url;
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        UserDTO dto = dao.checkLogin(txtEmail, txtPassword);
        if ( dto != null)
        {   
            List<String> listOfSearchValues = (List<String>) session.getAttribute("LIST_SEARCH_VALUES");
            if(listOfSearchValues == null)
            {
                url = SUCCESS;
            }
            else if (session != null) {

                txtCheckIn = listOfSearchValues.get(0);
                txtCheckOut = listOfSearchValues.get(1);
                txtSearchArea = listOfSearchValues.get(2);
                txtSearchAmount = listOfSearchValues.get(3);
                txtSearchRoomType = listOfSearchValues.get(4);
                url = TRY;
            }
            session.setAttribute("USER", dto);
        }
        return url;
    }
    public String logOut() throws Exception
    {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("CART");
        session.removeAttribute("USER");
        return url;
    }
    /**
     * @return the txtCheckIn
     */
    public String getTxtCheckIn() {
        return txtCheckIn;
    }

    /**
     * @param txtCheckIn the txtCheckIn to set
     */
    public void setTxtCheckIn(String txtCheckIn) {
        this.txtCheckIn = txtCheckIn;
    }

    /**
     * @return the txtCheckOut
     */
    public String getTxtCheckOut() {
        return txtCheckOut;
    }

    /**
     * @param txtCheckOut the txtCheckOut to set
     */
    public void setTxtCheckOut(String txtCheckOut) {
        this.txtCheckOut = txtCheckOut;
    }

    /**
     * @return the txtSearchArea
     */
    public String getTxtSearchArea() {
        return txtSearchArea;
    }

    /**
     * @param txtSearchArea the txtSearchArea to set
     */
    public void setTxtSearchArea(String txtSearchArea) {
        this.txtSearchArea = txtSearchArea;
    }

    /**
     * @return the txtSearchAmount
     */
    public String getTxtSearchAmount() {
        return txtSearchAmount;
    }

    /**
     * @param txtSearchAmount the txtSearchAmount to set
     */
    public void setTxtSearchAmount(String txtSearchAmount) {
        this.txtSearchAmount = txtSearchAmount;
    }

    /**
     * @return the txtSearchRoomType
     */
    public String getTxtSearchRoomType() {
        return txtSearchRoomType;
    }

    /**
     * @param txtSearchRoomType the txtSearchRoomType to set
     */
    public void setTxtSearchRoomType(String txtSearchRoomType) {
        this.txtSearchRoomType = txtSearchRoomType;
    }
    
}
