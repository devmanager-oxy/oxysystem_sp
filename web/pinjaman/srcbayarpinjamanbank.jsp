
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
            boolean priv = QrUserSession.isHavePriviledge(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_ANGSURAN);
            boolean privView = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_ANGSURAN, AppMenu.PRIV_VIEW);
            boolean privUpdate = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_ANGSURAN, AppMenu.PRIV_UPDATE);
            boolean privAdd = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_ANGSURAN, AppMenu.PRIV_ADD);
            boolean privDelete = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_ANGG_BANK_ANGSURAN, AppMenu.PRIV_DELETE);
%>
<%
//jsp content
            int iJSPCommand = JSPRequestValue.requestCommand(request);
            int prevJSPCommand = JSPRequestValue.requestInt(request, "prev_command");
            int start = JSPRequestValue.requestInt(request, "start");
            int srcType = JSPRequestValue.requestInt(request, "src_type");
            int recordToGet = 10;
            String msgString = "";
            int iErrCode = JSPMessage.NONE;
            String whereClause = "";
            String orderClause = "";
            JSPLine ctrLine = new JSPLine();
            CmdPinjaman ctrlPinjaman = new CmdPinjaman(request);

//out.println("srcType : "+srcType);

            SrcPinjaman srcPinjaman = new SrcPinjaman();

            JspSrcPinjaman jspSrcPinjaman = new JspSrcPinjaman(request, srcPinjaman);
            jspSrcPinjaman.requestEntityObject(srcPinjaman);

            if (iJSPCommand == JSPCommand.BACK) {
                srcPinjaman = (SrcPinjaman) session.getValue("SRC_PINJAMAN");
            }

            Vector temp = new Vector();
            int count = 0;

//if(iJSPCommand == JSPCommand.NONE){
            srcPinjaman.setIgnoreDate(1);
            srcPinjaman.setIgnoreAmount(1);
            srcPinjaman.setStatus(DbPinjaman.STATUS_APPROVE);
            srcPinjaman.setJenisPinjaman(-1);
//}

            if (srcType == 0) {
                srcPinjaman.setType(DbPinjaman.TYPE_PINJAMAN_KOPERASI);
            } else {
                srcPinjaman.setType(DbPinjaman.TYPE_PINJAMAN_BANK);
            }

            count = SrcPinjaman.getCount(srcPinjaman);
            int vectSize = count;

            if ((iJSPCommand == JSPCommand.FIRST || iJSPCommand == JSPCommand.PREV) ||
                    (iJSPCommand == JSPCommand.NEXT || iJSPCommand == JSPCommand.LAST)) {
                start = ctrlPinjaman.actionList(iJSPCommand, start, vectSize, recordToGet);
            }

//if(iJSPCommand == JSPCommand.LIST){
            temp = SrcPinjaman.getList(start, recordToGet, srcPinjaman);

//}

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
            <%if (!priv || !privView) {%>
            window.location="<%=approot%>/nopriv.jsp";
            <%}%>
            
            function cmdSearch(){
                document.form1.command.value="<%=JSPCommand.LIST%>";
                document.form1.action="srcbayarpinjamanbank.jsp";
                document.form1.submit();
            }
            
            function cmdEdit(oid){
                document.form1.hidden_pinjaman_id.value=oid;
                document.form1.command.value="<%=JSPCommand.EDIT%>";
                <%if (srcType == 0) {%>
                document.form1.action="pinjaman.jsp";
                <%} else {%>
                document.form1.action="pinjamanbank.jsp";
                <%}%>
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
            
            function cmdListFirst(){
                document.form1.command.value="<%=JSPCommand.FIRST%>";
                document.form1.prev_command.value="<%=JSPCommand.FIRST%>";
                document.form1.action="srcbayarpinjaman.jsp";
                document.form1.submit();
            }
            
            function cmdListPrev(){
                document.form1.command.value="<%=JSPCommand.PREV%>";
                document.form1.prev_command.value="<%=JSPCommand.PREV%>";
                document.form1.action="srcbayarpinjaman.jsp";
                document.form1.submit();
            }
            
            function cmdListNext(){
                document.form1.command.value="<%=JSPCommand.NEXT%>";
                document.form1.prev_command.value="<%=JSPCommand.NEXT%>";
                document.form1.action="srcbayarpinjaman.jsp";
                document.form1.submit();
            }
            
            function cmdListLast(){
                document.form1.command.value="<%=JSPCommand.LAST%>";
                document.form1.prev_command.value="<%=JSPCommand.LAST%>";
                document.form1.action="srcbayarpinjaman.jsp";
                document.form1.submit();
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
            String navigator = "<font class=\"lvl1\">Pinjaman Anggota Ke Bank</font><font class=\"tit1\">&nbsp;&raquo;&nbsp;<span class=\"lvl2\">Angsuran</span></font>";
                                           %>
                                           <%@ include file="../main/navigatorsp.jsp"%>
                                                    <!-- #EndEditable --></td>
                                                </tr>  
                                                <tr> 
                                                    <td><!-- #BeginEditable "content" --> 
                                                        <form id="form1" name="form1" method="post" action="">
                                                            <input type="hidden" name="command">	
                                                            <input type="hidden" name="start" value="<%=start%>">
                                                            <input type="hidden" name="prev_command" value="<%=prevJSPCommand%>">
                                                            <input type="hidden" name="hidden_pinjaman_id" value="">
                                                            <input type="hidden" name="src_type" value="<%=srcType%>">
                                                            
                                                            <input type="hidden" name="menu_idx" value="<%=menuIdx%>">
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_IGNORE_DATE]%>" value="1"> 
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_IGNORE_AMOUNT]%>" value="1"> 
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_STATUS] %>" value="<%=DbPinjaman.STATUS_APPROVE%>"> 
                                                            <input type="hidden" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_TYPE] %>" value="<%=DbPinjaman.TYPE_PINJAMAN_KOPERASI%>"> 
                                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                <tr> 
                                                                    <td class="container">
                                                                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                                            <tr> 
                                                                                <td colspan="4">&nbsp;</td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td colspan="4" height="25"><b>Pencarian Pinjaman 
                                                                                Untuk Di-Angsur</b></td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="10%">No Anggota</td>
                                                                                <td width="20%"> 
                                                                                    <input type="text" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_NO_ANGGOTA]%>" value="<%=srcPinjaman.getNoAnggota()%>">
                                                                                </td>
                                                                                <td width="11%">No Rekening Pinjaman</td>
                                                                                <td width="59%"> 
                                                                                    <input type="text" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_NO_REKENING]%>" value="<%=srcPinjaman.getNoRekening()%>">
                                                                                </td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="10%">Nama Anggota</td>
                                                                                <td width="20%"> 
                                                                                    <input type="text" name="<%=JspSrcPinjaman.colNames[JspSrcPinjaman.JSP_NAMA_ANGGOTA]%>" value="<%=srcPinjaman.getNamaAnggota()%>">
                                                                                </td>
                                                                                <td width="11%">&nbsp;</td>
                                                                                <td width="59%">&nbsp;</td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="10%" nowrap>&nbsp;</td>
                                                                                <td width="20%" nowrap>&nbsp;</td>
                                                                                <td width="11%">&nbsp;</td>
                                                                                <td width="59%">&nbsp;</td>
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
                                                                            <tr> 
                                                                                <td width="10%">&nbsp;</td>
                                                                                <td width="20%">&nbsp;</td>
                                                                                <td width="11%">&nbsp;</td>
                                                                                <td width="59%">&nbsp;</td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td colspan="4"> 
                                                                                    <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                                                        <tr> 
                                                                                            <td class="tablehdr" width="9%">No Anggota</td>
                                                                                            <td class="tablehdr" width="21%">Nama</td>
                                                                                            <td class="tablehdr" width="13%">Tanggal</td>
                                                                                            <td class="tablehdr" width="15%">No 
                                                                                            Rekening</td>
                                                                                            <td class="tablehdr" width="15%">Jenis 
                                                                                            Pinjaman</td>
                                                                                            <td class="tablehdr" width="16%">Total 
                                                                                            Pinjaman</td>
                                                                                            <td class="tablehdr" width="11%">Status</td>
                                                                                        </tr>
                                                                                        <%if (temp != null && temp.size() > 0) {
                for (int i = 0; i < temp.size(); i++) {
                    Pinjaman pinjaman = (Pinjaman) temp.get(i);
                    Member member = new Member();
                    try {
                        member = DbMember.fetchExc(pinjaman.getMemberId());
                    } catch (Exception e) {
                    }
                    if (i % 2 == 0) {
                                                                                        %>
                                                                                        <tr> 
                                                                                            <td class="tablecell" width="9%"><%=member.getNoMember()%></td>
                                                                                            <td class="tablecell" width="21%"><a href="javascript:cmdEdit('<%=pinjaman.getOID()%>')"><%=member.getNama()%></a></td>
                                                                                            <td class="tablecell" width="13%"><%=JSPFormater.formatDate(pinjaman.getDate(), "dd MMMM yyyy")%></td>
                                                                                            <td class="tablecell" width="15%"><%=pinjaman.getNumber()%></td>
                                                                                            <td class="tablecell" width="15%"><%=DbPinjaman.strJenisBarang[pinjaman.getJenisBarang()]%></td>
                                                                                            <td class="tablecell" width="16%"> 
                                                                                                <div align="right"><%=JSPFormater.formatNumber(pinjaman.getTotalPinjaman(), "#,###.##")%></div>
                                                                                            </td>
                                                                                            <td class="tablecell" width="11%"> 
                                                                                                <div align="center"><%=DbPinjaman.strStatus[pinjaman.getStatus()]%></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%} else {%>
                                                                                        <tr> 
                                                                                            <td class="tablecell1" width="9%"><%=member.getNoMember()%></td>
                                                                                            <td class="tablecell1" width="21%"><a href="javascript:cmdEdit('<%=pinjaman.getOID()%>')"><%=member.getNama()%></a></td>
                                                                                            <td class="tablecell1" width="13%"><%=JSPFormater.formatDate(pinjaman.getDate(), "dd MMMM yyyy")%></td>
                                                                                            <td class="tablecell1" width="15%"><%=pinjaman.getNumber()%></td>
                                                                                            <td class="tablecell1" width="15%"><%=DbPinjaman.strJenisBarang[pinjaman.getJenisBarang()]%></td>
                                                                                            <td class="tablecell1" width="16%"> 
                                                                                                <div align="right"><%=JSPFormater.formatNumber(pinjaman.getTotalPinjaman(), "#,###.##")%></div>
                                                                                            </td>
                                                                                            <td class="tablecell1" width="11%"> 
                                                                                                <div align="center"><%=DbPinjaman.strStatus[pinjaman.getStatus()]%></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <%}
                }
            }%>
                                                                                        <tr> 
                                                                                            <td width="9%">&nbsp;</td>
                                                                                            <td width="21%">&nbsp;</td>
                                                                                            <td width="13%">&nbsp;</td>
                                                                                            <td width="15%">&nbsp;</td>
                                                                                            <td width="15%">&nbsp;</td>
                                                                                            <td width="16%">&nbsp;</td>
                                                                                            <td width="11%">&nbsp;</td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td colspan="4"><span class="command"> 
                                                                                        <%
            int cmd = 0;
            if ((iJSPCommand == JSPCommand.FIRST || iJSPCommand == JSPCommand.PREV) ||
                    (iJSPCommand == JSPCommand.NEXT || iJSPCommand == JSPCommand.LAST)) {
                cmd = iJSPCommand;
            } else {
                if (iJSPCommand == JSPCommand.NONE || prevJSPCommand == JSPCommand.NONE) {
                    cmd = JSPCommand.FIRST;
                } else {
                    cmd = prevJSPCommand;
                }
            }
                                                                                        %>
                                                                                        <% ctrLine.setLocationImg(approot + "/images/ctr_line");
            ctrLine.initDefault();
                                                                                        %>
                                                                                <%=ctrLine.drawImageListLimit(cmd, vectSize, start, recordToGet)%> </span></td>
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
