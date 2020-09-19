
<% ((HttpServletResponse) response).addCookie(new Cookie("JSESSIONID", session.getId()));%>
<%@ page language="java" %> 
<%@ page import="java.util.*" %>
<%@ page import="com.project.util.jsp.*" %>
<%@ page import="com.project.admin.*" %>
<%@ page import="com.project.system.*" %>
<%@ page import="com.project.general.Company" %>
<%@ page import="com.project.general.DbCompany" %>
<%@ page import="com.project.general.Location" %>
<%@ page import="com.project.general.DbLocation" %>
<%@ include file="main/javainit.jsp"%> 
<%!
    final static int CMD_NONE = 0;
    final static int CMD_LOGIN = 1;
    final static int MAX_SESSION_IDLE = 20000;
%>

<%
//untuk meng-expired kan session
            response.setHeader("Expires", "Mon, 06 Jan 1990 00:00:01 GMT");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "nocache");
            
            int iCommand = JSPRequestValue.requestCommand(request);
            int dologin = 0;

            try {
                if (session.getValue("ADMIN_LOGIN") != null) {
                    session.removeValue("ADMIN_LOGIN");
                }
            } catch (Exception e) {
                System.out.println(" ==> Exception during remove all menu session");
            }

            dologin = QrUserSession.DO_LOGIN_OK;
            if (iCommand == CMD_LOGIN) {
                String loginID = JSPRequestValue.requestString(request, "login_id");
                String passwd = JSPRequestValue.requestString(request, "pass_wd");
                String remoteIP = request.getRemoteAddr();
                QrUserSession userQr = new QrUserSession(remoteIP);
                
                session.putValue("APP_LANG", "2");
                dologin = userQr.doLogin(loginID, passwd);

                if (dologin == QrUserSession.DO_LOGIN_OK) {
                    session.setMaxInactiveInterval(MAX_SESSION_IDLE);
                    session.putValue("ADMIN_LOGIN", userQr);
                    userQr = (QrUserSession) session.getValue("ADMIN_LOGIN");
                }
            }

            String msg = "";
             Vector v = DbCompany.list(0, 0, "", "");
            if (iCommand == CMD_LOGIN) {
                if (dologin == QrUserSession.DO_LOGIN_OK) {                    
                    if (v != null && v.size() > 0) {
                        response.sendRedirect("homesp.jsp");
                    } else {                        
                        response.sendRedirect(approot + "/approot"); 
                    }
                } else {                   
                    msg = "Login ID or password invalid";
                    response.sendRedirect(approot);
                }
            }
%>
<html lang="en">
    <head>
        <title><%=spTitle%></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"> 
        <!--===============================================================================================-->
        <link rel="icon" type="image/png" href="public/images/icons/icon.ico"/>          
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="public/vendor/bootstrap/css/bootstrap.min.css"> 
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="public/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="public/css/util.css">
        <link rel="stylesheet" type="text/css" href="public/css/main.css">
        <!--===============================================================================================-->
        <script language="javascript">
            function cmdLogin(){
                document.frmLogin.action = "index.jsp";
                document.frmLogin.command.value="<%=CMD_LOGIN%>";
                document.frmLogin.submit();
            }
        </script>
    </head>
    <body style="background-color: #666666;">        
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100">
                    <form name="frmLogin" method="post" onSubmit="javascript:cmdLogin()" class="login100-form validate-form">
                        <input type="hidden" name="command" value="<%=CMD_LOGIN%>">
                        <input type="hidden" name="sel_top_mn" value="4">
                        <input type="hidden" name="command" value="<%=CMD_LOGIN%>">
                        <span class="login100-form-title p-b-43">
                            Login to continue
                        </span>
                        <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                            <input class="input100" type="text" name="login_id" id="textfield" value="" onClick="this.select()">
                            <span class="focus-input100"></span>
                            <span class="label-input100">Username</span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Password is required">
                            <input class="input100" type="password" name="pass_wd"  id="textfield2" value="" onClick="this.select()">
                            <span class="focus-input100"></span>
                            <span class="label-input100">Password</span>
                        </div>
                        
                        <div class="flex-sb-m w-full p-t-3 p-b-32">
                            <div class="contact100-form-checkbox">
                                <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                                <label class="label-checkbox100" for="ckb1">
                                    Remember me
                                </label>
                            </div>
                        </div>
                        <div class="container-login100-form-btn">
                            <button class="login100-form-btn" name="button" id="button" onClick="javascript:cmdLogin()">
                                Login
                            </button>
                        </div>
                        
                        <p id="errmsg"><%=msg%></p>
                        <script language="JavaScript">
                            document.frmLogin.login_id.focus();
                        </script>
                    </form>                    
                    <div class="login100-more" style="background-image: url('public/images/login.jpg');">
                    </div>
                </div>
            </div>
        </div>        
        <!--===============================================================================================-->
        <script src="public/vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="public/vendor/bootstrap/js/popper.js"></script>
        <script src="public/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="public/js/main.js"></script>
        
    </body>
</html>


