<%-- 
    Document   : search.jsp
    Created on : Nov 30, 2019, 4:21:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search for hotel</h1>
        <%--if not login--%>
        <s:if test="%{#attr.USER == null}">
            <s:form action="moveToLogin" method="POST">
                <input type="submit" value="Login" />
                <s:hidden name="txtCheckIn" value="%{txtDatecheckIn}"/>
                <s:hidden name="txtCheckOut" value="%{txtDatecheckOut}"/>
                <s:hidden name="txtSearchArea" value="%{cbxArea}"/>
                <s:hidden name="txtSearchAmount" value="%{txtAmount}"/>
                <s:hidden name="txtSearchRoomType" value="%{cbxRoomType}"/>
            </s:form>
                <s:form action="moveToRegisterPage" method="POST">
                    <input type="submit" value="Register"/>
                </s:form>
        </s:if>
        <%--if logined--%>
        <s:if test="%{#attr.USER != null}">
            Welcome, <s:property value="%{#attr.USER.name}"/>
            <s:form action="logOut">
                <input type="submit" value="Log out"/>
            </s:form>
                
            <s:if test="%{#attr.USER.roleID == 'admin'}">
                <s:form action="moveToCreatePage">
                    <input type="submit" value="Create new user"/>
                </s:form>
            </s:if>  
            <s:if test="%{#attr.USER.roleID != 'admin'}">
                <s:form action="moveToHistoryPage">
                    <input type="submit" value="view history"/>
                </s:form>
            </s:if>          
        </s:if>
        
        <s:form action="searchHotel" method="POST" theme="simple">
            <%--List of area--%>
            Select area : 
            <s:select list="%{#attr.LIST_AREA}" name="cbxArea" value="cbxArea"/></br>
            <%--List of room type--%>
            Select type of room :
            <s:select list="%{#attr.LIST_ROOM_TYPES}" name="cbxRoomType" value="cbxRoomType"/></br>
            Select amount of room : <s:textfield type="number" min="1" name="txtAmount"/></br>
            Check in date :<s:textfield type="text" name="txtDatecheckIn" id="txtDatecheckIn"/></br>
            Check out date :<s:textfield type="text"  name="txtDatecheckOut"/></br>
            <input type="submit" value="Search" />
            <%--search value error--%>
            <s:if test="%{#attr.ERROR != null}">
                <s:set var="error" value="%{#attr.ERROR}"/>
                    <font color="red">
                        <s:property value="%{#error.checkInDateIsBeforeCurrentDate}"/>
                        <s:property value="%{#error.checkOutDateIsBeforeCheckInDate}"/>
                        <s:property value="%{#error.invalidDateFormat}"/>
                    </font>       
            </s:if>
        </s:form>
            <s:if test="%{!(txtAmount.isEmpty() or txtDatecheckIn.isEmpty() or txtDatecheckOut.isEmpty())}">
                <s:if test="%{#attr.ERROR == null}">
            <s:if test="%{listOfHotelInfo != null and !listOfHotelInfo.isEmpty()}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>hotel name</th>
                            <th>room type</th>
                            <th>description</th>
                            <th>price</th>
                            <s:if test="%{#attr.USER.roleID != 'admin'}">
                            <th>Book</th>
                            </s:if>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{listOfHotelInfo}">
                        <s:form action="addToBookingCart" method="POST" theme="simple">
                            <tr>
                                <td>
                                    <s:property value="hotelName"/>
                                    <s:hidden name="txtHotelName" value="%{hotelName}"/>
                                </td>
                                <td>
                                    <s:property value="roomType"/>
                                    <s:hidden name="txtRoomType" value="%{roomType}"/>
                                    <s:hidden name="txtRoomID" value="%{roomID}"/>
                                </td>
                                <td>
                                    <s:property value="description"/>
                                    <s:hidden name="txtDescription" value="%{description}"/>
                                </td>
                                <td>
                                    <s:property value="price"/>
                                    <s:hidden name="txtPrice" value="%{price}"/>
                                </td>
                                <s:if test="%{#attr.USER.roleID != 'admin'}">
                                <td>
                                    <input type="submit" value="Book" />
                                    <s:hidden name="txtCheckIn" value="%{txtDatecheckIn}"/>
                                    <s:hidden name="txtCheckOut" value="%{txtDatecheckOut}"/>
                                    <s:hidden name="txtSearchArea" value="%{cbxArea}"/>
                                    <s:hidden name="txtSearchAmount" value="%{txtAmount}"/>
                                    <s:hidden name="txtSearchRoomType" value="%{cbxRoomType}"/>
                                    <s:hidden name="txtRealAmount" value="%{realAmount}"/>
                                </td>
                                </s:if>
                            </tr>
                        </s:form>    
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:if test="%{listOfHotelInfo.isEmpty()}">
                No room matched !!!
            </s:if>
                
            </s:if>  <%--end of if there is  no error--%>  
            </s:if> 
            <s:if test="%{#attr.USER.roleID != 'admin'}">
                <s:form action="moveToViewCart" method="POST">
                    <input type="submit" value="view booking cart" />
                </s:form>
            </s:if> 

    </body>
</html>
