/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class SearchByDateInHistoryAction {
   private final String SUCCESS ="success";
   private String txtSearchValueDate;
   private List<BookingDetailsDTO> history;

    public List<BookingDetailsDTO> getHistory() {
        return history;
    }

    public void setHistory(List<BookingDetailsDTO> history) {
        this.history = history;
    }

   
    public String getTxtSearchValueDate() {
        return txtSearchValueDate;
    }

    public void setTxtSearchValueDate(String txtSearchValueDate) {
        this.txtSearchValueDate = txtSearchValueDate;
    }
   
    public SearchByDateInHistoryAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        if (txtSearchValueDate.isEmpty())
        {
            return url;
        }
        Date testDate;
        //invalid date format
        try
        {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
            testDate = sdformat.parse(txtSearchValueDate);
        }
        catch(ParseException ex)
        {
            String error = "Please input valid date format (yyyy/MM/dd)";
            session.setAttribute("ERROR", error);
            return url;
        }
        UserDTO currentUser = (UserDTO) session.getAttribute("USER");
        BookingDetailsDAO dao = new BookingDetailsDAO();
        dao.searchByDateInHistory(currentUser.getEmail(), txtSearchValueDate);
        history = dao.getListOfBookingDetails();
        return url;
    }
    
}
