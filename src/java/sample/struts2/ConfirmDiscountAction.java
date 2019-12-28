package sample.struts2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.bookingDetails.bookingCartOBJ;
import sample.discount.DiscountDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class ConfirmDiscountAction {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String txtDiscountCode;

    public String getTxtDiscountCode() {
        return txtDiscountCode;
    }

    public void setTxtDiscountCode(String txtDiscountCode) {
        this.txtDiscountCode = txtDiscountCode;
    }
    
    public ConfirmDiscountAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        if(txtDiscountCode.isEmpty())
        {
            return url;
        }
        else
        {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            DiscountDAO discDao = new DiscountDAO();
            int check = discDao.checkDiscountCode(txtDiscountCode);
            //discount code is not available
            if (check == 1 || check == 2) {
                session.setAttribute("DISCOUNT_ERROR", "this code is not available any more");
                return url;
            } 
            //discount code is not exist
            else if (check == 3) {
                session.setAttribute("DISCOUNT_ERROR", "this code is not exist");
                return url;
            }
            session.removeAttribute("DISCOUNT_ERROR");
            session.removeAttribute("DISCOUNT_PERCENT");
            session.removeAttribute("DISCOUNT_CODE");
            int percent = discDao.getDiscountPercent(txtDiscountCode);
            session.setAttribute("DISCOUNT_PERCENT",percent);
            float totalPrice = (float) session.getAttribute("TOTAL_PRICE");
            float priceAfterDiscount  = totalPrice -(totalPrice * percent / 100);
            session.setAttribute("TOTAL_PRICE_AFTER_DISCOUNT", priceAfterDiscount);
            session.setAttribute("DISCOUNT_CODE",txtDiscountCode);
            url = SUCCESS;
        }
        return url;
    }
    
}
