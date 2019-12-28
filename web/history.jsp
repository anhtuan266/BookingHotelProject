<%-- 
    Document   : history
    Created on : Dec 2, 2019, 1:51:56 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body>
        <h1>Your booking history</h1>
        <s:if test="%{#attr.USER != null}">
            Welcome, <s:property value="%{#attr.USER.name}"/>
            <s:form action="logOut">
                <input type="submit" value="Log out"/>
            </s:form>         
        </s:if>
                
        <s:form action="searchByNameInHistory" theme="simple">
            Enter hotel's name : <s:textfield name="txtSearchValueName" value="%{txtSearchValueName}"/>
            <input type="submit" value="search"/>
        </s:form>
        <s:form action="searchByDateInHistory" theme="simple">
            Enter booking date : <s:textfield name="txtSearchValueDate" value="%{txtSearchValueDate}"/>
            <input type="submit" value="search"/>
            <%--search value error--%>
            <s:if test="%{#attr.ERROR != null}">
                <s:set var="error" value="%{#attr.ERROR}"/>
                    <font color="red">
                        <s:property value="%{#error}"/>
                    </font>       
            </s:if>
        </s:form>    
            <s:if test="%{history != null and !history.isEmpty()}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>hotel name</th>
                            <th>room type</th>
                            <th>check in date</th>
                            <th>check out date</th>
                            <th>amount</th>
                            <th>booking date</th>
                            <th>discount code</th>
                            <th>total price</th>
                            <th>remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{history}">
                        <s:form action="addToBookingCart" method="POST" theme="simple">
                            <tr>
                                <td>
                                    <s:property value="hotelName"/>
                                </td>
                                <td>
                                    <s:property value="roomtype"/>
                                </td>
                                <td>
                                    <s:property value="checkInDate"/>                                   
                                </td>
                                <td>
                                    <s:property value="checkOutDate"/>
                                </td>
                                <td>
                                    <s:property value="amount"/>
                                </td>
                                <td>
                                   <s:property value="bookingDate"/>
                                </td>
                                <td>
                                   <s:property value="discountCode"/>
                                </td>
                                <td>
                                   <s:property value="totalPrice"/>
                                </td>
                                <td>
                                    <s:url var="delLink" action="removeBookingRecord">
                                        <s:param name="pkBookingID" value="%{bookingID}"></s:param>
                                        <s:param name="pkRoomID" value="%{roomId}"></s:param>
                                    </s:url>
                                    <s:a href="%{delLink}" onclick = "return confirmBox();">remove</s:a>
                                </td>
                            </tr>
                        </s:form>    
                        </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:if test="%{history.isEmpty()}">
                No history record !!!
            </s:if> 

    </body>
    <script type="text/javascript">
        function confirmBox() {
            var answer;
            answer = window.confirm("Do you really want to perform this action");
            if (answer == true) {
                return true;
            } else {
                return false;
            }
        }
    </script>
</html>
