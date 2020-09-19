
<%-- 
    Document   : angspinjamankop
    Created on : Aug 24, 2020, 4:43:02 PM
    Author     : Roy Andika
--%>

<%@ page language="java"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "com.project.util.*" %>
<%@ page import = "com.project.util.jsp.*" %>
<%@ page import = "com.project.system.*" %>
<%@ page import = "com.project.main.db.*" %>
<%@ page import = "com.project.coorp.member.*" %>
<%@ page import = "com.project.coorp.pinjaman.*" %>
<%@ page import = "java.util.Date" %>
<%@ include file="../main/javainit.jsp"%>
<%@ include file="../main/checksp.jsp"%>
<%
            boolean priv = QrUserSession.isHavePriviledge(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_ARSIP);
            boolean privView = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_ARSIP, AppMenu.PRIV_VIEW);
            boolean privUpdate = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_ARSIP, AppMenu.PRIV_UPDATE);
            boolean privAdd = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_ARSIP, AppMenu.PRIV_ADD);
            boolean privDelete = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_ARSIP, AppMenu.PRIV_DELETE);
%>

<%
//jsp content
            int iJSPCommand = JSPRequestValue.requestCommand(request);
            int start = JSPRequestValue.requestInt(request, "start");
            int srcType = JSPRequestValue.requestInt(request, "src_type");
            int recordToGet = 10;
            String msgString = "";
            int iErrCode = JSPMessage.NONE;
            String whereClause = "";
            String orderClause = "";

            SrcPinjaman srcPinjaman = new SrcPinjaman();

            JspSrcPinjaman jspSrcPinjaman = new JspSrcPinjaman(request, srcPinjaman);
            jspSrcPinjaman.requestEntityObject(srcPinjaman);

            if (iJSPCommand == JSPCommand.BACK) {
                srcPinjaman = (SrcPinjaman) session.getValue("SRC_PINJAMAN");
            }

            Vector temp = new Vector();
            srcPinjaman.setIgnoreDate(1);
            srcPinjaman.setIgnoreAmount(1);
            srcPinjaman.setStatus(DbPinjaman.STATUS_APPROVE);
            srcPinjaman.setJenisPinjaman(-1);
            srcPinjaman.setType(DbPinjaman.TYPE_PINJAMAN_KOPERASI_KE_BANK);

            temp = SrcPinjaman.getListPinjamanKop(0, 0, srcPinjaman);
            int count = SrcPinjaman.getCountPinjamanKop(srcPinjaman);

            session.putValue("SRC_PINJAMAN", srcPinjaman);
%>
<html >
    <!-- #BeginTemplate "/Templates/index.dwt" --> 
    <head>
        <!-- #BeginEditable "javascript" --> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=spTitle%></title>
        <link href="../css/csssp.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">            
            <%if (!priv || !privView ) {%>
            window.location="<%=approot%>/nopriv.jsp";
            <%}%>
            
            function cmdSearch(){
                document.form1.command.value="<%=JSPCommand.LIST%>";
                document.form1.action="angspinjamankop.jsp";
                document.form1.submit();
            }
            
            function cmdEdit(oid){
                document.form1.hidden_pinjaman_id.value=oid;
                document.form1.command.value="<%=JSPCommand.EDIT%>";
                document.form1.action="pinjamankopbankanuitas.jsp";
                document.form1.submit();
            }
            
            var sysDecSymbol = "<%=sSystemDecimalSymbol%>";
            var usrDigitGroup = "<%=sUserDigitGroup%>";
            var usrDecSymbol = "<%=sUserDecimalSymbol%>";
            
            function removeChar(number){
                
                var ix;
                var result = "";
                for(ix=0; ix<number.length; ix++){
                    var xx = number.charAt(ix);
                    //alert(xx);
                    if(!isNaN(xx)){
                        result = result + xx;
                    }
                    else{
                        if(xx==',' || xx=='.'){
                            result = result + xx;
                        }
                    }
                }
                
                return result;
            }
            
            function checkNumber(obj){
                var st = obj.value;
                
                result = removeChar(st);
                
                result = cleanNumberFloat(result, sysDecSymbol, usrDigitGroup, usrDecSymbol);
                obj.value = formatFloat(result, '', sysDecSymbol, usrDigitGroup, usrDecSymbol, decPlace); 
            }
            
            function checkNumber1(obj){
                var st = obj.value;
                
                result = removeChar(st);
                
                result = cleanNumberFloat(result, sysDecSymbol, usrDigitGroup, usrDecSymbol);
                obj.value = result;//formatFloat(result, '', sysDecSymbol, usrDigitGroup, usrDecSymbol, decPlace); 
            }
            
            <!--
            function MM_swapImgRestore() { //v3.0
                var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
            }
            function MM_preloadImages() { //v3.0
                var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
                    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
                    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
            }
            
            function MM_findObj(n, d) { //v4.01
                var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
                    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
                if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
                for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
                if(!x && d.getElementById) x=d.getElementById(n); return x;
            }
            
            function MM_swapImage() { //v3.0
                var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
                if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
            }
            //-->
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
            <%@ include file="../main/hmenusp.jsp"%>
                            <!-- #EndEditable --> </td>
                        </tr>
                        <tr> 
                            <td valign="top"> 
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
                                    <!--DWLayoutTable-->
                                    <tr> 
                                        <td width="165" height="100%" valign="top" style="background:url(<%=approot%>/images/leftmenu-bg.gif) repeat-y"> 
                                            <!-- #BeginEditable "menu" --> 
                  <%@ include file="../main/menusp.jsp"%>
                                   <%@ include file="../calendar/calendarframe.jsp"%>
                                        <!-- #EndEditable --> </td>
                                        <td width="100%" valign="top"> 
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <tr> 
                                                    <td class="title"><!-- #BeginEditable "title" -->
                                           <%                                           
            String navigator = "<font class=\"lvl1\">Pinjaman</font><font class=\"tit1\">&nbsp;&raquo;&nbsp;<font class=\"lvl1\">Pinjaman Koperasi</font><font class=\"tit1\">&nbsp;&raquo;&nbsp;<span class=\"lvl2\">Angsuran</span></font>";
                                           %>
                                           <%@ include file="../main/navigatorsp.jsp"%>
                                                    <!-- #EndEditable --></td>
                                                </tr>                                                
                                                <tr> 
                                                    <td><!-- #BeginEditable "content" --> 
                                                        <form id="form1" name="form1" method="post" action="">
                                                            <input type="hidden" name="command">	
                                                            <input type="hidden" name="start" value="<%=start%>">
                                                            <input type="hidden" name="hidden_pinjaman_id" value="">
                                                            <input type="hidden" name="src_type" value="<%=srcType%>">                                                            
                                                            <input type="hidden" name="menu_idx" value="<%=menuIdx%>">
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_IGNORE_DATE]%>" value="1"> 
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_IGNORE_AMOUNT]%>" value="1"> 
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_STATUS] %>" value="<%=DbPinjaman.STATUS_APPROVE%>"> 
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_TYPE] %>" value="<%=DbPinjaman.TYPE_PINJAMAN_KOPERASI_KE_BANK%>"> 
                                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                <tr> 
                                                                    <td class="container">
                                                                        <table width="100%" border="0" cellspacing="1" cellpadding="1">                                                                            
                                                                            <tr> 
                                                                                <td colspan="4" height="25"><b>Pencarian Pinjaman 
                                                                                Untuk Di-Angsur</b></td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="10%">No Pinjaman</td>
                                                                                <td width="20%"> 
                                                                                    <input type="text" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_NO_REKENING]%>" value="<%=srcPinjaman.getNoRekening()%>">
                                                                                </td>
                                                                                <td width="11%">&nbsp;</td>
                                                                                <td width="59%"></td>
                                                                            </tr>                                                                            
                                                                            <tr height="10"> 
                                                                                <td colspan="4">&nbsp;</td>                                                                                
                                                                            </tr>
                                                                            <tr> 
                                                                                <td colspan="4"> 
                                                                                    <table width="30%" border="0" cellspacing="0" cellpadding="0">
                                                                                        <tr> 
                                                                                            <td width="9%"><img src="../images/search.jpg" width="22"></td>
                                                                                            <td width="91%"><a href="javascript:cmdSearch()">Cari 
                                                                                            Data</a></td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <tr height="10"> 
                                                                                <td colspan="4">&nbsp;</td>                                                                                
                                                                            </tr>
                                                                            <tr> 
                                                                                <td colspan="4"> 
                                                                                    <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                                                        <tr>
                                                                                            <td class="tablehdr" width="15%">No Pinjaman</td>
                                                                                            <td class="tablehdr" width="13%">Tanggal</td>                                                                                            
                                                                                            <td class="tablehdr" width="15%">Jenis Pinjaman</td>
                                                                                            <td class="tablehdr" >Total Pinjaman</td>
                                                                                            <td class="tablehdr" width="11%">Status</td>
                                                                                        </tr>
                                                                                        <%if (temp != null && temp.size() > 0) {
                for (int i = 0; i < temp.size(); i++) {
                    Pinjaman pinjaman = (Pinjaman) temp.get(i);
                    
                    String style = "";
                    if (i % 2 == 0) {
                        style = "tablecell";
                    }else{
                        style = "tablecell1";
                    }
                                                                                        %>
                                                                                        <tr>
                                                                                            <td class="<%=style%>" ><a href="javascript:cmdEdit('<%=pinjaman.getOID()%>')"><%=pinjaman.getNumber()%></a></td>                                                                                            
                                                                                            <td class="<%=style%>" ><%=JSPFormater.formatDate(pinjaman.getDate(), "dd MMMM yyyy")%></td>                                                                                            
                                                                                            <td class="<%=style%>" ><%=DbPinjaman.strJenisBarang[pinjaman.getJenisBarang()]%></td>
                                                                                            <td class="<%=style%>" > 
                                                                                                <div align="right"><%=JSPFormater.formatNumber(pinjaman.getTotalPinjaman(), "#,###.##")%></div>
                                                                                            </td>
                                                                                            <td class="<%=style%>" > 
                                                                                                <div align="center"><%=DbPinjaman.strStatus[pinjaman.getStatus()]%></div>
                                                                                            </td>
                                                                                        </tr>                                                                                        
                                                                                        <%
                }
            }%>
                                                                                        
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="10%">&nbsp;</td>
                                                                                <td width="20%">&nbsp;</td>
                                                                                <td width="11%">&nbsp;</td>
                                                                                <td width="59%">&nbsp;</td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="10%">&nbsp;</td>
                                                                                <td width="20%">&nbsp;</td>
                                                                                <td width="11%">&nbsp;</td>
                                                                                <td width="59%">&nbsp;</td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                <tr> 
                                                                    <td>&nbsp;</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td>&nbsp;</td>
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
            <%@ include file="../main/footersp.jsp"%>
                            <!-- #EndEditable --> </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </body>
    <!-- #EndTemplate -->
</html>
