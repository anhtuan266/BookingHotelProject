/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class MoveToLoginAction {
    private final String SUCCESS = "success";
    private String txtCheckIn;
    private String txtCheckOut;
    private String txtSearchArea;
    private String txtSearchAmount;
    private String txtSearchRoomType;

    public MoveToLoginAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        if(txtCheckIn != null)
        {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            List<String> listOfSearchValues = new ArrayList<>();
            listOfSearchValues.add(txtCheckIn);
            listOfSearchValues.add(txtCheckOut);
            listOfSearchValues.add(txtSearchArea);
            listOfSearchValues.add(txtSearchAmount);
            listOfSearchValues.add(txtSearchRoomType);
            session.setAttribute("LIST_SEARCH_VALUES", listOfSearchValues);
        }
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
