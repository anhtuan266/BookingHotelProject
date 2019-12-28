/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

/**
 *
 * @author Admin
 */
public class registAndCreateUserError {
    private String dupplicateEmail;

    /**
     * @return the dupplicateEmail
     */
    public String getDupplicateEmail() {
        return dupplicateEmail;
    }

    /**
     * @param dupplicateEmail the dupplicateEmail to set
     */
    public void setDupplicateEmail(String dupplicateEmail) {
        this.dupplicateEmail = dupplicateEmail;
    }

    public registAndCreateUserError(String dupplicateEmail) {
        this.dupplicateEmail = dupplicateEmail;
    }

    public registAndCreateUserError() {
    }
    
}
