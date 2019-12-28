/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.struts2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import sample.bookingDetails.BookingDetailsDAO;
import sample.bookingDetails.BookingDetailsDTO;
import sample.hotelRoom.HotelRoomDAO;
import sample.hotelRoom.HotelRoomDTO;
import sample.hotelRoom.searchHotelError;

/**
 *
 * @author Admin
 */
public class SearchHotelAction {
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private String cbxArea;
    private String cbxRoomType;
    private String txtAmount;
    private String txtDatecheckIn;
    private String txtDatecheckOut;
    private List<HotelRoomDTO> listOfHotelInfo;
    
    public SearchHotelAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("ERROR");
        searchHotelError error = new searchHotelError();
        //search value is empty
        if(txtAmount.isEmpty() || txtDatecheckIn.isEmpty() || txtDatecheckOut.isEmpty())
        {
            url = FAIL;
            return url;
        }
        Date newCheckInDate;
        Date newCheckOutDate;
        Date currentDate = new Date();
        //invalid date format
        try
        {
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
            newCheckInDate = sdformat.parse(txtDatecheckIn);
            newCheckOutDate = sdformat.parse(txtDatecheckOut);
        }
        catch(ParseException ex)
        {
            error.setInvalidDateFormat("Please input valid date format (yyyy/MM/dd)");
            session.setAttribute("ERROR", error);
            url = FAIL;
            return url;
        }
        //check in date before current date
        if (newCheckInDate.before(currentDate))
        {
            error.setCheckInDateIsBeforeCurrentDate("Check in date must after current date 1 day or more");
            session.setAttribute("ERROR", error);
            url = FAIL;
            return url;
        }
        if(newCheckOutDate.compareTo(newCheckInDate) <= 0)
        {
            error.setCheckOutDateIsBeforeCheckInDate("Check out date must after check in date 1 day or more");
            session.setAttribute("ERROR", error);
            url = FAIL;
            return url;
        }
        HotelRoomDAO hrDao = new HotelRoomDAO();
        BookingDetailsDAO bdDao = new BookingDetailsDAO();
        int amount = Integer.parseInt(txtAmount);
        //list to store hotelRoom to delete
        List<HotelRoomDTO> remover = new ArrayList<>();
        //get list of hotel in the area user want to search
        hrDao.loadHotelAndRoomInfo(cbxArea);
        listOfHotelInfo = hrDao.getListOfHotels();
        
        //get list of room of the hotel
        for (HotelRoomDTO hotelRoomDTO : listOfHotelInfo) {
            //filter the list where type = type, and amount can affort conditions
            if (!hotelRoomDTO.getRoomTypeID().equals(cbxRoomType))
            {
                remover.add(hotelRoomDTO);
            }
            if(hotelRoomDTO.getRealAmount() < amount)
            {
                remover.add(hotelRoomDTO);
            }
        }
        //remove
        for (HotelRoomDTO hotelRoomDTO : remover) {
            listOfHotelInfo.remove(hotelRoomDTO);
        }
        remover.clear();
        
        //get list of booking details
        bdDao.loadBookingDetails();
        List<BookingDetailsDTO> listOfBookingDetails = bdDao.getListOfBookingDetails();

        //get roomid in lisOfRoomID compare to records in listOfBookingDetails
        for(HotelRoomDTO hotelRoomDTO : listOfHotelInfo)
        {
            String roomid = hotelRoomDTO.getRoomID();
            for(BookingDetailsDTO dto : listOfBookingDetails)
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date oldCheckInDate = format.parse(dto.getCheckInDate());
                Date oldCheckOutDate = format.parse(dto.getCheckOutDate());
                //if roomid is existed in a booking detail
                if (roomid.equals(dto.getRoomId()))
                {
                    //if new checkin,checkout confict to checkin,checkout in bookingdetails
                    if (!(newCheckInDate.after(oldCheckOutDate) || newCheckOutDate.before(oldCheckInDate)))
                    {
                        //if the rest amount is not fit the condition
                        if (hotelRoomDTO.getRealAmount() - dto.getAmount() < amount)
                        {
                            remover.add(hotelRoomDTO);
                        }
                        else
                        {
                            hotelRoomDTO.setRealAmount(hotelRoomDTO.getRealAmount() - dto.getAmount());
                        }
                    }
                }
            }
        }
        //remove
        for (HotelRoomDTO hotelRoomDTO : remover) {
            listOfHotelInfo.remove(hotelRoomDTO);
        }
        remover.clear();
        return url;
    }

    /**
     * @return the cbxArea
     */
    public String getCbxArea() {
        return cbxArea;
    }

    /**
     * @param cbxArea the cbxArea to set
     */
    public void setCbxArea(String cbxArea) {
        this.cbxArea = cbxArea;
    }

    /**
     * @return the cbxRoomType
     */
    public String getCbxRoomType() {
        return cbxRoomType;
    }

    /**
     * @param cbxRoomType the cbxRoomType to set
     */
    public void setCbxRoomType(String cbxRoomType) {
        this.cbxRoomType = cbxRoomType;
    }

    /**
     * @return the txtAmount
     */
    public String getTxtAmount() {
        return txtAmount;
    }

    /**
     * @param txtAmount the txtAmount to set
     */
    public void setTxtAmount(String txtAmount) {
        this.txtAmount = txtAmount;
    }

    /**
     * @return the txtDatecheckIn
     */
    public String getTxtDatecheckIn() {
        return txtDatecheckIn;
    }

    /**
     * @param txtDatecheckIn the txtDatecheckIn to set
     */
    public void setTxtDatecheckIn(String txtDatecheckIn) {
        this.txtDatecheckIn = txtDatecheckIn;
    }

    /**
     * @return the txtDatecheckOut
     */
    public String getTxtDatecheckOut() {
        return txtDatecheckOut;
    }

    /**
     * @param txtDatecheckOut the txtDatecheckOut to set
     */
    public void setTxtDatecheckOut(String txtDatecheckOut) {
        this.txtDatecheckOut = txtDatecheckOut;
    }

    /**
     * @return the listOfHotelInfo
     */
    public List<HotelRoomDTO> getListOfHotelInfo() {
        return listOfHotelInfo;
    }

    /**
     * @param listOfHotelInfo the listOfHotelInfo to set
     */
    public void setListOfHotelInfo(List<HotelRoomDTO> listOfHotelInfo) {
        this.listOfHotelInfo = listOfHotelInfo;
    }
    
}
