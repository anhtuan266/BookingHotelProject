/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import sample.user.UserDAO;

/**
 *
 * @author Admin
 */
public class RegisterNewUserAction {
    private final String FAIL = "fail";
    private final String SUCCESS = "success";
    
    private String txtEmail;
    private String txtName;
    private String txtPassword;
    private String txtPhone;
    private String txtAddress;
    
    public RegisterNewUserAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        UserDAO dao = new UserDAO();
        boolean result = dao.registNewUser(txtEmail, txtName, txtPassword, txtPhone, txtAddress);
        if (result)
        {
            url = SUCCESS;
        }
        return url;
    }

    /**
     * @return the txtEmail
     */
    public String getTxtEmail() {
        return txtEmail;
    }

    /**
     * @param txtEmail the txtEmail to set
     */
    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    /**
     * @return the txtName
     */
    public String getTxtName() {
        return txtName;
    }

    /**
     * @param txtName the txtName to set
     */
    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    /**
     * @return the txtPassword
     */
    public String getTxtPassword() {
        return txtPassword;
    }

    /**
     * @param txtPassword the txtPassword to set
     */
    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    /**
     * @return the txtPhone
     */
    public String getTxtPhone() {
        return txtPhone;
    }

    /**
     * @param txtPhone the txtPhone to set
     */
    public void setTxtPhone(String txtPhone) {
        this.txtPhone = txtPhone;
    }

    /**
     * @return the txtAddress
     */
    public String getTxtAddress() {
        return txtAddress;
    }

    /**
     * @param txtAddress the txtAddress to set
     */
    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }
    
}
