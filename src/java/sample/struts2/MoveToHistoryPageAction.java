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
import sample.bookingDetails.BookingDetailsDAO;
import sample.bookingDetails.BookingDetailsDTO;
import sample.user.UserDTO;

/**
 *
 * @author Admin
 */
public class MoveToHistoryPageAction {
    private final String SUCCESS = "success";
    private List<BookingDetailsDTO> history;
    
    public MoveToHistoryPageAction() {
    }
   
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        UserDTO currentUser = (UserDTO) session.getAttribute("USER");
        BookingDetailsDAO dao = new BookingDetailsDAO();
        dao.loadBookingHistory(currentUser.getEmail());
        history = dao.getListOfBookingDetails();
        return url;
    }

    /**
     * @return the history
     */
    public List<BookingDetailsDTO> getHistory() {
        return history;
    }

    /**
     * @param history the history to set
     */
    public void setHistory(List<BookingDetailsDTO> history) {
        this.history = history;
    }
    
}
