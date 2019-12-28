<%-- 
    Document   : create
    Created on : Dec 1, 2019, 8:30:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <h1>Create New User</h1>
        <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script> 
        <script src="js/jquery.validate.js" type="text/javascript"></script> 
        <script src="js/additional-methods.js" type="text/javascript"></script> 
        <script type="text/javascript">
            $(function () {
                // validate signup form on keyup and submit
                $("#registerForm").validate({
                    rules: {
                        txtEmail : {
                            required: true,
                            email : true,
                            rangelength: [12,30]
                        },
                        txtName : {
                            required: true,
                            rangelength: [4,20]
                        },
                        txtPassword : {
                            required: true,
                            rangelength: [8,20]
                        },
                        txtConfirm : {
                            required: true,
                            equalTo : "#txtPassword"
                        },
                        txtAddress : {
                            required: true,
                            rangelength: [8,50]
                        },
                        txtPhone : {
                            required: true,
                            phoneUS : true
                        }
                        
                    }, //end rules
                    messages: {                      
                        txtPhone : {
                            phoneUS : "please input valid form of phone number (212-2xx-xxxx)"
                        }
                        
                    }
                  
                });//end validate

            });//end function
        </script>
        <s:form id="registerForm" method="POST" action="createUser" theme="simple">
            Email : <s:textfield name="txtEmail" value="%{txtEmail}"/></br>
            Name : <s:textfield name="txtName" value="%{txtName}"/></br>
            Password : <s:textfield type="password" name="txtPassword" value="%{txtPassword}" id="txtPassword"/></br>
            Confirm : <s:textfield type="password" name="txtConfirm" value="%{txtConfirm}"/></br>
            phone : <s:textfield name="txtPhone" value="%{txtPhone}"/></br>
            Address : <s:textfield name="txtAddress" value="%{txtAddress}"/></br>
            Role : <s:select list="%{#attr.LIST_ROLES}" name="cbxRole"/>
            <input type="submit" value="Create" />
            <s:if test="%{exception.message.contains('duplicate')}">
            <font color="red">
                <s:property value="%{txtEmail}"/> is existed
            </font>
            </s:if>
        </s:form>
    </body>
</html>
