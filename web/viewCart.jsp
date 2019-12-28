<%-- 
    Document   : viewCart.jsp
    Created on : Dec 1, 2019, 10:57:43 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
        <link href="css/CaptchaPopupCSS.css" rel="stylesheet" type="text/css">
        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
    </head>
    <body>
        
        <h1>Booking Cart</h1>
        <s:if test="%{#attr.USER != null}">
            Welcome, <s:property value="%{#attr.USER.name}"/>
            <s:form action="logOut">
                <input type="submit" value="Log out"/>
            </s:form>         
        </s:if>
        <s:if test="%{#attr.CART != null}">
            <s:set var="listOfBooking" value="%{#attr.CART.items}"/>
            <table border="1">
                <thead>
                    <tr>
                        <th>hotel name</th>
                        <th>room type</th>
                        <th>room description</th>
                        <th>price per day</th>
                        <th>Check in date</th>
                        <th>Check out date</th>
                        <th>quantity</th>
                        <th>Remove room</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="listOfBooking">
                        <s:form method="POST" action="updateRoomAmount" theme="simple"> 
                            <tr>
                                <td>
                                    <s:property value="%{key.hotelName}"/>
                                </td>
                                <td>
                                    <s:property value="%{key.roomtype}"/>
                                </td>
                                <td>
                                    <s:property value="%{key.roomDescription}"/>
                                </td>
                                <td>
                                    <s:property value="%{key.pice}"/>
                                </td>
                                <td>
                                    <s:property value="%{key.checkInDate}"/>
                                </td>
                                <td>
                                    <s:property value="%{key.checkOutDate}"/>
                                </td>
                                <td>
                                    <s:textfield type="number" min="1" value="%{value}" name="txtAmount"/>
                                </td>
                                <td>
                                    <s:url var="delLink" action="removeBookFromCart">
                                        <s:param name="pk" value="%{key.roomID}"></s:param>
                                    </s:url>
                                    <s:a href="%{delLink}" onclick = "return confirmBox();">remove</s:a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Update" />
                                    <s:hidden name="txtRoomID" value="%{key.roomID}"/>
                                </td>
                            </tr>
                        </s:form>
                    </s:iterator>
                    <tr>
                        <td colspan="9">
                            Total price : <s:property value="%{#attr.TOTAL_PRICE}"/>
                        </td>
                    </tr>
                    <s:if test="%{#attr.DISCOUNT_PERCENT != null}">
                    <tr>
                        <td colspan="9">
                            Discount percent : <s:property value="%{#attr.DISCOUNT_PERCENT}"/>%
                        </td>
                    </tr>
                    <tr>
                        <td colspan="9">
                            Total price : <s:property value="%{#attr.TOTAL_PRICE_AFTER_DISCOUNT}"/>
                        </td>
                    </tr>
                    </s:if>
                </tbody>
            </table>
                        
            <s:form action="confirmDiscount" theme="simple">
                Discount Code :<s:textfield name="txtDiscountCode" value="%{#attr.DISCOUNT_CODE}"/></br>
                <%--there is error--%>
                <s:if test="%{#attr.DISCOUNT_ERROR  != null}">
                    <font color="red">
                    <s:property value="%{#attr.DISCOUNT_ERROR}"/>
                    </font> 
                </s:if>
                <s:if test="%{#attr.BOOKING_ERROR != null}">
                    <font color="red">
                    <s:property value="%{#attr.BOOKING_ERROR }"/>
                    </font> 
                </s:if>
                <input type="submit" value="Confirm" />
            </s:form>  

                <s:div id="bigDiv">
                    <%-- Popup Div Starts Here --%>
                    <s:div id="popupContact">
                        <%-- Contact Us Form --%>
                        <s:form action="confirmBooking" id="formCaptcha" method="POST" name="form">
                            <img id="close" src="images/close.ico" onclick ="closePopUp()" width="30" height="30">
                            <h2>Please complete this captcha</h2>
                            <hr>
                            <s:div id="recaptcha" class="g-recaptcha"></s:div>
                            <br/>
                            <input type="submit" value="Confirm"/>
                        </s:form>
                    </s:div>
                    <%-- Popup Div Ends Here --%>
                </s:div>
                <%-- Display Popup Button --%>      
                <input id="popup" type="submit" value="Check out" onclick="openPopUp();"/>
                <s:if test="%{#attr.CAPTCHA_ERROR != null}">
                    <s:set var="error" value="%{#attr.CAPTCHA_ERROR}"/>
                    <font color="red">
                    <s:property value="%{#error}"/>
                    </font> 
                </s:if> <%--end of popup captcha form--%>      
                
        </s:if>   <%--end of if cart is empty--%>     
            
        <s:if test="%{#attr.CART == null}">
            Your booking cart is empty
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
        
        function openPopUp()
        {
          document.getElementById('bigDiv').style.display = "block";
        }
        
        function closePopUp(){
            document.getElementById('bigDiv').style.display = "none";
        }
        
        var onloadCallback = function() {
             widgetId1 = grecaptcha.render('recaptcha', {
                'sitekey' : '6Ldxi8YUAAAAANMbQiyzNmUS2W3OPhadnWLBS5aF',
                'theme' : 'light'
              });
        };
    </script>
</html>
