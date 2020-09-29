
<%@page import="com.project.coorp.pinjaman.JspJenisPinjaman"%>
<%@page import="com.project.coorp.pinjaman.CmdJenisPinjaman"%>
<%@page import="com.project.coorp.pinjaman.JenisPinjaman"%>
<%@page import="com.project.coorp.pinjaman.DbJenisPinjaman"%>
<%@ page language = "java" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.project.util.*" %>
<%@ page import = "com.project.main.db.*" %>
<%@ page import = "com.project.main.entity.*" %>
<%@ page import = "com.project.coorp.jenisPinjaman.*" %>
<%@ page import = "com.project.general.*" %>
<%@ page import = "com.project.admin.*" %>
<%@ page import = "com.project.util.jsp.*" %>
<%@ include file = "../main/javainit.jsp" %>
<%@ include file = "../main/checksp.jsp" %>
<%
            boolean priv = QrUserSession.isHavePriviledge(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_MASTER_DATA, AppMenu.M2_SIPADU_DATA_ANGGOTA);
            boolean privView = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_MASTER_DATA, AppMenu.M2_SIPADU_DATA_ANGGOTA, AppMenu.PRIV_VIEW);
            boolean privUpdate = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_MASTER_DATA, AppMenu.M2_SIPADU_DATA_ANGGOTA, AppMenu.PRIV_UPDATE);
            boolean privAdd = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_MASTER_DATA, AppMenu.M2_SIPADU_DATA_ANGGOTA, AppMenu.PRIV_ADD);
            boolean privDelete = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_MASTER_DATA, AppMenu.M2_SIPADU_DATA_ANGGOTA, AppMenu.PRIV_DELETE);
%>
<!-- Jsp Block -->
<%
            int iJSPCommand = JSPRequestValue.requestCommand(request);
            int start = JSPRequestValue.requestInt(request, "start");
            int prevJSPCommand = JSPRequestValue.requestInt(request, "prev_command");
            long oidJenisPinjaman = JSPRequestValue.requestLong(request, "hidden_jenis_pinjaman_id");
            int showAll = JSPRequestValue.requestInt(request, "show_all");

            /*variable declaration*/
            int recordToGet = 10;
            String msgString = "";
            int iErrCode = JSPMessage.NONE;
            String whereClause = "";
            String orderClause = "";

            String memo = "";
            JenisPinjaman jenisPinjamanOld = new JenisPinjaman();

            //Ambiil data lama
            if (oidJenisPinjaman != 0) {
                try {
                    jenisPinjamanOld = DbJenisPinjaman.fetchExc(oidJenisPinjaman);
                } catch (Exception e) {
                }
            }

            //Cmd
            CmdJenisPinjaman ctrlJenisPinjaman = new CmdJenisPinjaman(request);
            JSPLine ctrLine = new JSPLine();
            Vector listMember = new Vector(1, 1);

            /*switch statement */
            iErrCode = ctrlJenisPinjaman.action(iJSPCommand, oidJenisPinjaman);
            /* end switch*/
            JspJenisPinjaman jspJenisPinjaman = ctrlJenisPinjaman.getForm();

            /*count list All JenisPinjaman*/
            int vectSize = DbJenisPinjaman.getCount(whereClause);

            JenisPinjaman jenisPinjaman = ctrlJenisPinjaman.getJenisPinjaman();
            msgString = ctrlJenisPinjaman.getMessage();

            if (iJSPCommand == JSPCommand.NONE) {
                if (oidJenisPinjaman == 0) {
                    iJSPCommand = JSPCommand.ADD;
                } else {
                    iJSPCommand = JSPCommand.EDIT;
                }
            }

            if (iJSPCommand == JSPCommand.SAVE && iErrCode == 0) {
                msgString = "Data Saved";
            }

            if ((iJSPCommand == JSPCommand.FIRST || iJSPCommand == JSPCommand.PREV) ||
                    (iJSPCommand == JSPCommand.NEXT || iJSPCommand == JSPCommand.LAST)) {
                start = ctrlJenisPinjaman.actionList(iJSPCommand, start, vectSize, recordToGet);
            }
            /* end switch list*/

            /* get record to display */
            listMember = DbJenisPinjaman.list(start, recordToGet, whereClause, orderClause);

            /*handle condition if size of record to display = 0 and start > 0 	after delete*/
            if (listMember.size() < 1 && start > 0) {
                if (vectSize - recordToGet > recordToGet) {
                    start = start - recordToGet;
                } //go to JSPCommand.PREV
                else {
                    start = 0;
                    iJSPCommand = JSPCommand.FIRST;
                    prevJSPCommand = JSPCommand.FIRST; //go to JSPCommand.FIRST
                }
                listMember = DbJenisPinjaman.list(start, recordToGet, whereClause, orderClause);
            }
%>
<html >
    <!-- #BeginTemplate "/Templates/index.dwt" -->
    <head>
        <!-- #BeginEditable "javascript" -->
        <title><%=spTitle%></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="../css/csssp.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript">
            <%if (!priv || !privView) {%>
            window.location="<%=approot%>/nopriv.jsp";
            <%}%>

                function cmdUnShowAll(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.EDIT%>";
                    document.frmjenispinjaman.show_all.value=0;
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdShowAll(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.EDIT%>";
                    document.frmjenispinjaman.show_all.value=1;
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdAdd(){
                    document.frmjenispinjaman.hidden_jenis_pinjaman_id.value="0";
                    document.frmjenispinjaman.command.value="<%=JSPCommand.ADD%>";
                    document.frmjenispinjaman.prev_command.value="<%=prevJSPCommand%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdAsk(oidJenisPinjaman){
                    document.frmjenispinjaman.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
                    document.frmjenispinjaman.command.value="<%=JSPCommand.ASK%>";
                    document.frmjenispinjaman.prev_command.value="<%=prevJSPCommand%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdConfirmDelete(oidJenisPinjaman){
                    document.frmjenispinjaman.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
                    document.frmjenispinjaman.command.value="<%=JSPCommand.DELETE%>";
                    document.frmjenispinjaman.prev_command.value="<%=prevJSPCommand%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }
                function cmdSave(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.SAVE%>";
                    document.frmjenispinjaman.prev_command.value="<%=prevJSPCommand%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdEdit(oidJenisPinjaman){
                    document.frmjenispinjaman.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
                    document.frmjenispinjaman.command.value="<%=JSPCommand.EDIT%>";
                    document.frmjenispinjaman.prev_command.value="<%=prevJSPCommand%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdCancel(oidJenisPinjaman){
                    document.frmjenispinjaman.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
                    document.frmjenispinjaman.command.value="<%=JSPCommand.EDIT%>";
                    document.frmjenispinjaman.prev_command.value="<%=prevJSPCommand%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdBack(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.BACK%>";
                    document.frmjenispinjaman.action="scrjenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdListFirst(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.FIRST%>";
                    document.frmjenispinjaman.prev_command.value="<%=JSPCommand.FIRST%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdListPrev(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.PREV%>";
                    document.frmjenispinjaman.prev_command.value="<%=JSPCommand.PREV%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdListNext(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.NEXT%>";
                    document.frmjenispinjaman.prev_command.value="<%=JSPCommand.NEXT%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdListLast(){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.LAST%>";
                    document.frmjenispinjaman.prev_command.value="<%=JSPCommand.LAST%>";
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                function cmdRefres(oidJenisPinjaman){
                    document.frmjenispinjaman.command.value="<%=JSPCommand.SUBMIT%>";
                    document.frmjenispinjaman.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
                    document.frmjenispinjaman.action="jenispinjaman.jsp";
                    document.frmjenispinjaman.submit();
                }

                //-------------- script control line -------------------
                function MM_swapImgRestore() { //v3.0
                    var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
                }

                function MM_preloadImages() { //v3.0
                    var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
                        var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
                        if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
                }

                function MM_findObj(n, d) { //v4.0
                    var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
                        d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
                    if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
                    for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
                    if(!x && document.getElementById) x=document.getElementById(n); return x;
                }

                function MM_swapImage() { //v3.0
                    var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
                    if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
                }

        </script>
        <!-- #EndEditable -->
    </head>
    <body onLoad="MM_preloadImages('<%=approot%>/images/home2.gif','<%=approot%>/images/logout2.gif')">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
            <tr>
                <td valign="top">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                        <tr>
                            <td height="96"> <!-- #BeginEditable "header" -->
            <%@ include file="../main/hmenusp.jsp" %>
                            <!-- #EndEditable --> </td>
                        </tr>
                        <tr>
                            <td valign="top">
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
                                    <!--DWLayoutTable-->
                                    <tr>
                                        <td width="165" height="100%" valign="top" style="background:url(<%=approot%>/images/leftmenu-bg.gif) repeat-y">
                                            <!-- #BeginEditable "menu" -->
                  <%@ include file="../main/menusp.jsp" %>
                  <%@ include file="../calendar/calendarframe.jsp"%>
                                        <!-- #EndEditable --> </td>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td class="title"><!-- #BeginEditable "title" -->
                                           <%
            String navigator = "<font class=\"lvl1\">Master Data</font><font class=\"tit1\">&nbsp;&raquo;&nbsp;<span class=\"lvl2\">Daftar Jenis Pinjaman</span></font>";
                                           %>
                                           <%@ include file="../main/navigatorsp.jsp"%>
                                                    <!-- #EndEditable --></td>
                                                </tr>
                                                <!--tr>
                      <td><img src="<%=approot%>/images/title-sp.gif" width="584" height="1"></td>
                                                </tr-->
                                                <tr>
                                                    <td><!-- #BeginEditable "content" -->
                                                        <form name="frmjenispinjaman" method ="post" action="">
                                                            <input type="hidden" name="command" value="<%=iJSPCommand%>">
                                                            <input type="hidden" name="vectSize" value="<%=vectSize%>">
                                                            <input type="hidden" name="start" value="<%=start%>">
                                                            <input type="hidden" name="show_all" value="0">
                                                            <input type="hidden" name="prev_command" value="<%=prevJSPCommand%>">
                                                            <input type="hidden" name="hidden_jenis_pinjaman_id" value="<%=oidJenisPinjaman%>">
                                                            <input type="hidden" name="menu_idx" value="<%=menuIdx%>">
                                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                <tr align="left" valign="top">
                                                                    <td height="8"  colspan="3">&nbsp;</td>
                                                                </tr>
                                                                <tr align="left" valign="top">
                                                                    <td height="8" valign="middle" colspan="3" class="container">
                                                                        <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                                                            <tr align="left">
                                                                                <td height="5" valign="middle" colspan="5"></td>
                                                                            </tr>
                                                                            <tr align="left">
                                                                                <td height="17" width="15%"><font color="#ff0000">*
                                                                                ) data harap di isi</font></td>
                                                                                <td height="17" width="35%">&nbsp;</td>
                                                                                <td height="17" width="15%">&nbsp;</td>
                                                                                <td height="17" colspan="2" width="35%">&nbsp;
                                                                            </tr>
                                                                            <tr>
                                                                                <td height="5" valign="middle" colspan="5"></td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>Jenis Pinjaman</td>
                                                                                <td colspan="3"><input type="text" name="<%=jspJenisPinjaman.colNames[JspJenisPinjaman.JSP_JENIS_PINJAMAN] %>"  value="<%=(jenisPinjaman.getJenisPinjaman() == null) ? "" : jenisPinjaman.getJenisPinjaman() %>" class="formElemen" size="50">
                                                                            * <%= jspJenisPinjaman.getErrorMsg(JspJenisPinjaman.JSP_JENIS_PINJAMAN) %> </td>
                                                                            </tr>
                                                                            <tr align="left">
                                                                                <td height="8" valign="middle" colspan="2">
                                                                                    <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                                                                        <tr>
                                                                                            <td width="30%">&nbsp;</td>
                                                                                            <td width="70%">&nbsp; </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="2">
                                                                                                <%
            ctrLine.setLocationImg(approot + "/images/ctr_line");
            ctrLine.initDefault();
            ctrLine.setTableWidth("80%");
            String scomDel = "javascript:cmdAsk('" + oidJenisPinjaman + "')";
            String sconDelCom = "javascript:cmdConfirmDelete('" + oidJenisPinjaman + "')";
            String scancel = "javascript:cmdEdit('" + oidJenisPinjaman + "')";
            ctrLine.setBackCaption("Lihat Daftar");
            ctrLine.setJSPCommandStyle("buttonlink");
            ctrLine.setDeleteCaption("Hapus !");
            ctrLine.setSaveCaption("Simpan");
            ctrLine.setAddCaption("Entry JenisPinjaman Baru");
            ctrLine.setCancelCaption("Batalkan");
            ctrLine.setConfirmDelCaption("Ya Hapus !");
            ctrLine.setSavedInfo("Data Tersimpan");

            ctrLine.setOnMouseOut("MM_swapImgRestore()");
            ctrLine.setOnMouseOverSave("MM_swapImage('save','','" + approot + "/images/save2.gif',1)");
            ctrLine.setSaveImage("<img src=\"" + approot + "/images/save.gif\" name=\"save\" height=\"22\" border=\"0\">");

            //jspLine.setOnMouseAddNew("MM_swapImage('new','','"+approot+"/images/new2.gif',1)");
            ctrLine.setAddNewImage("<img src=\"" + approot + "/images/new.gif\" name=\"new\" height=\"22\" border=\"0\">");

            //jspLine.setOnMouseOut("MM_swapImgRestore()");
            ctrLine.setOnMouseOverBack("MM_swapImage('back','','" + approot + "/images/cancel2.gif',1)");
            ctrLine.setBackImage("<img src=\"" + approot + "/images/cancel.gif\" name=\"back\" height=\"22\" border=\"0\">");

            ctrLine.setOnMouseOverDelete("MM_swapImage('delete','','" + approot + "/images/delete2.gif',1)");
            ctrLine.setDeleteImage("<img src=\"" + approot + "/images/delete.gif\" name=\"delete\" height=\"22\" border=\"0\">");

            ctrLine.setOnMouseOverEdit("MM_swapImage('edit','','" + approot + "/images/cancel2.gif',1)");
            ctrLine.setEditImage("<img src=\"" + approot + "/images/cancel.gif\" name=\"edit\" height=\"22\" border=\"0\">");


            ctrLine.setWidthAllJSPCommand("90");
            ctrLine.setErrorStyle("warning");
            ctrLine.setErrorImage(approot + "/images/error.gif\" width=\"20\" height=\"20");
            ctrLine.setQuestionStyle("warning");
            ctrLine.setQuestionImage(approot + "/images/error.gif\" width=\"20\" height=\"20");
            ctrLine.setInfoStyle("success");
            ctrLine.setSuccessImage(approot + "/images/success.gif\" width=\"20\" height=\"20");

            if (privDelete) {//privDelete
                ctrLine.setConfirmDelJSPCommand(sconDelCom);
                ctrLine.setDeleteJSPCommand(scomDel);
                ctrLine.setEditJSPCommand(scancel);
            } else {
                ctrLine.setConfirmDelCaption("");
                ctrLine.setDeleteCaption("");
                ctrLine.setEditCaption("");
            }

            if (privAdd == false && privUpdate == false) {
                ctrLine.setSaveCaption("");
            }

            if (privAdd == false) {
                ctrLine.setAddCaption("");
            }
            if (iJSPCommand == JSPCommand.SUBMIT) {
                if (jenisPinjaman.getOID() == 0) {
                    iJSPCommand = JSPCommand.ADD;
                } else {
                    iJSPCommand = JSPCommand.EDIT;
                }
            }

                                                                                                %>
                                                                                            <%= ctrLine.drawImageOnly(iJSPCommand, iErrCode, msgString)%> </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <tr align="left">
                                                                                <td height="8" valign="middle" width="15%">&nbsp;</td>
                                                                                <td height="8" valign="middle" width="35%">&nbsp;</td>
                                                                                <td height="8" valign="middle" width="15%">&nbsp;</td>
                                                                                <td height="8" colspan="2" width="35%" valign="top">&nbsp;
                                                                                </td>
                                                                            </tr>

                                                                            <tr align="left" >
                                                                                <td colspan="5" class="command" valign="top">&nbsp;
                                                                                </td>
                                                                            </tr>
                                                                            <tr align="left" >
                                                                                <td colspan="5" valign="top">
                                                                                    <div align="left"></div>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                    <!-- #EndEditable --> </td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td height="25"> <!-- #BeginEditable "footer" -->
            <%@ include file="../main/footersp.jsp" %>
                            <!-- #EndEditable --> </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
    <!-- #EndTemplate -->
</html>
