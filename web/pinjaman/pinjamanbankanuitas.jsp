
<%@page import="com.project.coorp.report.RptAnggotaPinjamBankAnuitasL"%>
<%@page import="com.project.coorp.report.RptAnggotaPinjamBankAnuitas"%>
<%@ page language = "java" %>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "com.project.util.*" %>
<%@ page import = "com.project.util.jsp.*" %>
<%@ page import = "com.project.main.db.*" %>
<%@ page import = "com.project.general.*" %>
<%@ page import = "com.project.coorp.pinjaman.*" %>
<%@ page import = "com.project.coorp.member.*" %>
<%@ page import = "com.project.*" %>
<%@ page import = "java.util.Date" %>
<%@ include file = "../main/javainit.jsp" %>
<%@ include file = "../main/checksp.jsp" %>
<%
            boolean priv = QrUserSession.isHavePriviledge(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_BARU);
            boolean privView = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_BARU, AppMenu.PRIV_VIEW);
            boolean privUpdate = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_BARU, AppMenu.PRIV_UPDATE);
            boolean privAdd = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_BARU, AppMenu.PRIV_ADD);
            boolean privDelete = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.MENU1_SIPADU_PINJAMAN, AppMenu.M2_SIPADU_PINJAMAN_KOP_BARU, AppMenu.PRIV_DELETE);
%>
<!-- Jsp Block -->
<%!
    public String drawList(Vector objectClass, long pinjamanId) {
        JSPList ctrlist = new JSPList();
        ctrlist.setAreaWidth("100%");
        ctrlist.setListStyle("listgen");
        ctrlist.setTitleStyle("tableheader");
        ctrlist.setCellStyle("cellStyle");
        ctrlist.setHeaderStyle("tableheader");
        ctrlist.addHeader("Number", "8%");
        ctrlist.addHeader("Date", "8%");
        ctrlist.addHeader("Note", "8%");
        ctrlist.addHeader("Total Pinjaman", "8%");
        ctrlist.addHeader("Bunga", "8%");
        ctrlist.addHeader("Status", "8%");
        ctrlist.addHeader("User Id", "8%");
        ctrlist.addHeader("Biaya Administrasi", "8%");
        ctrlist.addHeader("Jenis Barang", "8%");
        ctrlist.addHeader("Detail Jenis Barang", "8%");
        ctrlist.addHeader("Bank Id", "8%");
        ctrlist.addHeader("Lama Cicilan", "8%");

        ctrlist.setLinkRow(0);
        ctrlist.setLinkSufix("");
        Vector lstData = ctrlist.getData();
        Vector lstLinkData = ctrlist.getLinkData();
        ctrlist.setLinkPrefix("javascript:cmdEdit('");
        ctrlist.setLinkSufix("')");
        ctrlist.reset();
        int index = -1;

        for (int i = 0; i < objectClass.size(); i++) {
            Pinjaman pinjaman = (Pinjaman) objectClass.get(i);
            Vector rowx = new Vector();
            if (pinjamanId == pinjaman.getOID()) {
                index = i;
            }

            rowx.add(pinjaman.getNumber());

            String str_dt_Date = "";
            try {
                Date dt_Date = pinjaman.getDate();
                if (dt_Date == null) {
                    dt_Date = new Date();
                }

                str_dt_Date = JSPFormater.formatDate(dt_Date, "dd MMMM yyyy");
            } catch (Exception e) {
                str_dt_Date = "";
            }

            rowx.add(str_dt_Date);

            rowx.add(pinjaman.getNote());

            rowx.add(String.valueOf(pinjaman.getTotalPinjaman()));

            rowx.add(String.valueOf(pinjaman.getBunga()));

            rowx.add(String.valueOf(pinjaman.getStatus()));

            rowx.add(String.valueOf(pinjaman.getUserId()));

            rowx.add(String.valueOf(pinjaman.getBiayaAdministrasi()));

            rowx.add(String.valueOf(pinjaman.getJenisBarang()));

            rowx.add(pinjaman.getDetailJenisBarang());

            rowx.add(String.valueOf(pinjaman.getBankId()));

            rowx.add(String.valueOf(pinjaman.getLamaCicilan()));

            lstData.add(rowx);
            lstLinkData.add(String.valueOf(pinjaman.getOID()));
        }

        return ctrlist.drawList(index);
    }

%>
<%
            if (session.getValue("KONSTAN") != null) {
                session.removeValue("KONSTAN");
            }

            if (session.getValue("DETAIL") != null) {
                session.removeValue("DETAIL");
            }
            int iJSPCommand = JSPRequestValue.requestCommand(request);
            int start = JSPRequestValue.requestInt(request, "start");
            int prevJSPCommand = JSPRequestValue.requestInt(request, "prev_command");
            long oidPinjaman = JSPRequestValue.requestLong(request, "hidden_pinjaman_id");
            long oidPinjamanDetail = JSPRequestValue.requestLong(request, "hidden_pinjaman_detail_id");

            if (iJSPCommand == JSPCommand.NONE) {
                iJSPCommand = JSPCommand.ADD;
            }

            Vector listBank = DbBank.list(0, 0, "", "");

            /*variable declaration*/
            int recordToGet = 10;
            String msgString = "";
            int iErrCode = JSPMessage.NONE;
            String whereClause = "";
            String orderClause = "";

            CmdPinjaman ctrlPinjaman = new CmdPinjaman(request);
            JSPLine ctrLine = new JSPLine();
            Vector listPinjaman = new Vector(1, 1);

            /*switch statement */
            iErrCode = ctrlPinjaman.action(iJSPCommand, oidPinjaman);

            /* end switch*/
            JspPinjaman jspPinjaman = ctrlPinjaman.getForm();

            /*count list All Pinjaman*/
            int vectSize = DbPinjaman.getCount(whereClause);

            Pinjaman pinjaman = ctrlPinjaman.getPinjaman();

            RptAnggotaPinjamBankAnuitas rptKonstan = new RptAnggotaPinjamBankAnuitas();
            Vector vTemp = new Vector();

            if (oidPinjaman == 0) {
                oidPinjaman = pinjaman.getOID();
            }

            if (iJSPCommand == JSPCommand.SAVE && pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                DbPinjamanDetail.setupDetailPinjamanAnuitas(pinjaman);
            } else if (iJSPCommand == JSPCommand.POST) {
                PinjamanDetail pinjamanDetail = new PinjamanDetail();
                try {
                    pinjamanDetail = DbPinjamanDetail.fetchExc(oidPinjamanDetail);
                } catch (Exception e) {
                }
                double bungaKoperasi = JSPRequestValue.requestDouble(request, "bunga_koperasi");
                double bungaBank = JSPRequestValue.requestDouble(request, "bunga_bank");
                DbPinjamanDetail.updateDetailPinjamanAnuitas(pinjamanDetail, bungaKoperasi, bungaBank);
            }

            if (iJSPCommand == JSPCommand.UPDATE) {
                String strDate = JSPRequestValue.requestString(request, "jatuh_tempo");
                DbPinjamanDetail.updateJatuhTempo(oidPinjaman, strDate);
            }

            msgString = ctrlPinjaman.getMessage();


            if ((iJSPCommand == JSPCommand.FIRST || iJSPCommand == JSPCommand.PREV) ||
                    (iJSPCommand == JSPCommand.NEXT || iJSPCommand == JSPCommand.LAST)) {
                start = ctrlPinjaman.actionList(iJSPCommand, start, vectSize, recordToGet);
            }
            /* end switch list*/

            /* get record to display */
            listPinjaman = DbPinjaman.list(start, recordToGet, whereClause, orderClause);

            /*handle condition if size of record to display = 0 and start > 0 	after delete*/
            if (listPinjaman.size() < 1 && start > 0) {
                if (vectSize - recordToGet > recordToGet) {
                    start = start - recordToGet;
                } //go to JSPCommand.PREV
                else {
                    start = 0;
                    iJSPCommand = JSPCommand.FIRST;
                    prevJSPCommand = JSPCommand.FIRST; //go to JSPCommand.FIRST
                }
                listPinjaman = DbPinjaman.list(start, recordToGet, whereClause, orderClause);
            }

            Vector pds = DbPinjamanDetail.list(0, 0, "pinjaman_id=" + pinjaman.getOID(), "cicilan_ke");


//posting jurnal pengakuan pihutang
            if (iJSPCommand == JSPCommand.SAVE && pinjaman.getStatus() == DbPinjaman.STATUS_APPROVE) {
                try {
                    DbPinjaman.postJournalPinjamanBankAnggota(pinjaman);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            }

%>
<html >
    <!-- #BeginTemplate "/Templates/indexsp.dwt" --> 
    <head>
        <!-- #BeginEditable "javascript" --> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=spTitle%></title>
        <link href="../css/csssp.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript">
            <%if (!priv || !privView) {%>
            window.location="<%=approot%>/nopriv.jsp";
            <%}%>
            
            <%if (iJSPCommand == JSPCommand.SUBMIT || iJSPCommand == JSPCommand.PRINT) {%>
            window.location="#go";
            <%}%>
            
            var sysDecSymbol = "<%=sSystemDecimalSymbol%>";
            var usrDigitGroup = "<%=sUserDigitGroup%>";
            var usrDecSymbol = "<%=sUserDecimalSymbol%>";

            function cmdPrintXLS(){
                window.open("<%=printrootsp%>.report.RptAnggotaPinjamBankAnuitasXLS?idx=<%=System.currentTimeMillis()%>");
            }

            function cmdPrintPdf(){
                window.open("<%=printrootsp%>.report.RptAnggotaPinjamBankAnuitasPdf?idx=<%=System.currentTimeMillis()%>","",'scrollbars=yes,status=yes,width=750,height=600,resizable=yes');
            }
            
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
            
            
            function cmdChangeType(){
                
                var bunga = document.frmpinjaman.<%=jspPinjaman.colNames[JspPinjaman.JSP_BUNGA] %>.value;
                bunga = removeChar(bunga);	
                bunga = cleanNumberFloat(bunga, sysDecSymbol, usrDigitGroup, usrDecSymbol);
                
                var lama = document.frmpinjaman.<%=jspPinjaman.colNames[JspPinjaman.JSP_LAMA_CICILAN] %>.value;
                lama = removeChar(lama);	
                lama = cleanNumberFloat(lama, sysDecSymbol, usrDigitGroup, usrDecSymbol);
                
                var total = document.frmpinjaman.<%=jspPinjaman.colNames[JspPinjaman.JSP_TOTAL_PINJAMAN] %>.value;
                total = removeChar(total);	
                total = cleanNumberFloat(total, sysDecSymbol, usrDigitGroup, usrDecSymbol);
                
                //alert("bunga : "+bunga+", lama : "+lama+", total : "+total);
                //alert("bungaf : "+parseFloat(bunga)+", lama  f: "+parseFloat(lama)+", total f : "+parseFloat(total));
                
                if(!isNaN(bunga) && parseFloat(bunga)>0 && !isNaN(lama) && parseFloat(lama)>0 && !isNaN(total) && parseFloat(total)>0){
                    
                    //alert("in");
                    
                    var cicilan = 0;
                    var type = document.frmpinjaman.<%=jspPinjaman.colNames[JspPinjaman.JSP_JENIS_CICILAN] %>.value;		
                    
                    //alert("type : "+type);
                    
                    bungaBln = parseFloat(bunga)/12;
                    
                    //bunga tetap
                    if(parseInt(type)==0){
                        cicilan = (parseFloat(total)/parseFloat(lama)) + ((parseFloat(total)/parseFloat(lama)) * (bungaBln/100));
                    }
                    else{
                        x = Math.pow(1/(1+(bungaBln/100)),(parseFloat(lama)));
                        
                        y = ((1-x))/(bungaBln/100);
                        
                        cicilan = parseFloat(total)/y;
                    }
                    
                    //alert("cicilan : "+cicilan);
                    
                    document.frmpinjaman.<%=jspPinjaman.colNames[JspPinjaman.JSP_CICILAN] %>.value = formatFloat(""+cicilan, '', sysDecSymbol, usrDigitGroup, usrDecSymbol, decPlace); 
                    
                }
                else{
                    document.frmpinjaman.<%=jspPinjaman.colNames[JspPinjaman.JSP_CICILAN] %>.value = "0.00";
                }
                
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
            
            function cmdBayar(oidpd){
                document.frmpinjaman.hidden_bayar_type.value="0";
                document.frmpinjaman.hidden_pinjaman_id.value="<%=pinjaman.getOID()%>";
                document.frmpinjaman.hidden_pinjaman_detail_id.value=oidpd;
                document.frmpinjaman.command.value="<%=JSPCommand.ADD%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                //document.frmpinjaman.action="bayarpinjamananuitas.jsp";
                document.frmpinjaman.action="bayarpinjaman.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdUpdateTanggal(oid){
                document.frmpinjaman.hidden_pinjaman_id.value="<%=pinjaman.getOID()%>";
                document.frmpinjaman.hidden_pinjaman_detail_id.value=oid;
                document.frmpinjaman.command.value="<%=JSPCommand.PRINT%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdUpdateJT(){
                document.frmpinjaman.hidden_pinjaman_id.value="<%=pinjaman.getOID()%>";
                document.frmpinjaman.command.value="<%=JSPCommand.UPDATE%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            
            function cmdEditBunga(oid){
                //alert("1");
                document.frmpinjaman.hidden_pinjaman_detail_id.value=oid;
                document.frmpinjaman.command.value="<%=JSPCommand.SUBMIT%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                //alert("2");
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdUpdateBunga(){
                document.frmpinjaman.command.value="<%=JSPCommand.POST%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdBayarDetail(oidpd){
                document.frmpinjaman.hidden_bayar_type.value="0";
                document.frmpinjaman.hidden_pinjaman_id.value="<%=pinjaman.getOID()%>";
                document.frmpinjaman.hidden_pinjaman_detail_id.value=oidpd;
                document.frmpinjaman.command.value="<%=JSPCommand.EDIT%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="bayarpinjaman.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdPelunasan(oidp){
                document.frmpinjaman.hidden_bayar_type.value="1";
                document.frmpinjaman.hidden_pinjaman_detail_id.value=oidp;
                document.frmpinjaman.command.value="<%=JSPCommand.ADD%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pelunasan.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdAdd(){
                document.frmpinjaman.hidden_pinjaman_id.value="0";
                document.frmpinjaman.command.value="<%=JSPCommand.ADD%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdAsk(oidPinjaman){
                document.frmpinjaman.hidden_pinjaman_id.value=oidPinjaman;
                document.frmpinjaman.command.value="<%=JSPCommand.ASK%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdConfirmDelete(oidPinjaman){
                document.frmpinjaman.hidden_pinjaman_id.value=oidPinjaman;
                document.frmpinjaman.command.value="<%=JSPCommand.DELETE%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            function cmdSave(){
                document.frmpinjaman.command.value="<%=JSPCommand.SAVE%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdEdit(oidPinjaman){
                document.frmpinjaman.hidden_pinjaman_id.value=oidPinjaman;
                document.frmpinjaman.command.value="<%=JSPCommand.EDIT%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdCancel(oidPinjaman){                
                document.frmpinjaman.command.value="<%=JSPCommand.EDIT%>";
                document.frmpinjaman.prev_command.value="<%=prevJSPCommand%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdBack(){
                document.frmpinjaman.command.value="<%=JSPCommand.BACK%>";                
                document.frmpinjaman.action="arsippinjamanbank.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdListFirst(){
                document.frmpinjaman.command.value="<%=JSPCommand.FIRST%>";
                document.frmpinjaman.prev_command.value="<%=JSPCommand.FIRST%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdListPrev(){
                document.frmpinjaman.command.value="<%=JSPCommand.PREV%>";
                document.frmpinjaman.prev_command.value="<%=JSPCommand.PREV%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdListNext(){
                document.frmpinjaman.command.value="<%=JSPCommand.NEXT%>";
                document.frmpinjaman.prev_command.value="<%=JSPCommand.NEXT%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function cmdListLast(){
                document.frmpinjaman.command.value="<%=JSPCommand.LAST%>";
                document.frmpinjaman.prev_command.value="<%=JSPCommand.LAST%>";
                document.frmpinjaman.action="pinjamanbankanuitas.jsp";
                document.frmpinjaman.submit();
            }
            
            function getMember(){
                window.open("scrmember.jsp","srcmember","scrollbars=no,height=400,width=800,addressbar=no,menubar=no,toolbar=no,location=no,");
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
    <body onLoad="MM_preloadImages('<%=approot%>/imagessp/home2.gif','<%=approot%>/imagessp/logout2.gif')">
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
                                        <td width="165" height="100%" valign="top" style="background:url(<%=approot%>/imagessp/leftmenu-bg.gif) repeat-y"> 
                                            <!-- #BeginEditable "menu" --> 
                  <%@ include file="../main/menusp.jsp"%>
                  <%@ include file="../calendar/calendarframe.jsp"%>
                                        <!-- #EndEditable --> </td>
                                        <td width="100%" valign="top"> 
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                        <td class="title"><!-- #BeginEditable "title" -->
                                               <%
                                               String isNew = "";
                                               if(pinjaman.getOID()==0){
                                                    isNew = "Baru";
                                               }
                                String navigator = "<font class=\"lvl1\">Pinjaman Anggota Ke Bank</font><font class=\"tit1\">&nbsp;&raquo;&nbsp;<span class=\"lvl2\">Pinjaman Bank "+ isNew +" - Angsuran Bunga Anuitas</span></font>";
                                               %>
                                               <%@ include file="../main/navigatorsp.jsp"%>
                                                        <!-- #EndEditable --></td>
                                                </tr>
                                                <!--tr> 
                      <td><img src="<%=approot%>/imagessp/title-sp.gif" width="584" height="1"></td> 
                                                </tr-->
                                                <tr> 
                                                    <td><!-- #BeginEditable "content" --> 
                                                        <form name="frmpinjaman" method ="post" action="">
                                                            <input type="hidden" name="command" value="<%=iJSPCommand%>">
                                                            <input type="hidden" name="vectSize" value="<%=vectSize%>">
                                                            <input type="hidden" name="start" value="<%=start%>">
                                                            <input type="hidden" name="prev_command" value="<%=prevJSPCommand%>">
                                                            <input type="hidden" name="hidden_pinjaman_id" value="<%=oidPinjaman%>">
                                                            <input type="hidden" name="hidden_pinjaman_detail_id" value="<%=oidPinjamanDetail%>">
                                                            <input type="hidden" name="hidden_bayar_type" value="">
                                                            <input type="hidden" name="menu_idx" value="<%=menuIdx%>">
                                                            <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_USER_ID] %>"  value="<%= (pinjaman.getUserId() == 0) ? user.getOID() : pinjaman.getUserId()%>">
                                                            <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_APPROVE_BY_ID] %>"  value="<%= (pinjaman.getApproveById() == 0) ? user.getOID() : pinjaman.getApproveById()%>">
                                                            <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_TYPE] %>"  value="<%= DbPinjaman.TYPE_PINJAMAN_BANK %>">
                                                            <input type="hidden" name="<%=jspPinjaman.colNames[jspPinjaman.JSP_JENIS_CICILAN]%>" value="<%=DbPinjaman.JENIS_CICILAN_ANUITAS%>">
                                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                <tr align="left" valign="top"> 
                                                                    <td height="8"  colspan="3">&nbsp; </td>
                                                                </tr>
                                                                <tr align="left" valign="top"> 
                                                                    <td height="8" valign="middle" colspan="3" class="container"> 
                                                                        <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                                                            <tr align="left"> 
                                                                            <td height="21" width="11%">Anggota</td>
                                                                            <td height="21" width="24%"> 
                                                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                                                <tr> 
                                                                                    <td width="65%"> 
                                                                                        <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_MEMBER_ID] %>"  value="<%= pinjaman.getMemberId() %>" class="formElemen" size="30">
                                                                                        <%
            Member m = new Member();
            String str = "";
            if (pinjaman.getMemberId() != 0) {
                try {
                    m = DbMember.fetchExc(pinjaman.getMemberId());
                } catch (Exception e) {
                }
                str = m.getNoMember() + "/" + m.getNama();

                rptKonstan.setPeminjam(m.getNama());
                rptKonstan.setNik(m.getNoMember());
            }

                                                                                        %>
                                                                                        <input type="text" name="anggota" value="<%=str%>" readOnly size="30">
                                                                                    <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_MEMBER_ID) %> </td>
                                                                                    <td width="3%"><img src="../images/spacer.gif" width="8" height="8"></td>
                                                                                    <td width="32%" nowrap><a href="javascript:getMember()"><img src="../images/search2.jpg" width="22" border="0"></a><img src="../images/spacer.gif" width="20" height="8"></td>
                                                                                </tr>
                                                                            </table>
                                                                            <td height="21" width="14%">Nomor Rekening 
                                                                            Pinjaman 
                                                                            <td height="21" width="51%"> 
                                                                            <%
            if (pinjaman.getOID() == 0) {
                pinjaman.setType(DbPinjaman.TYPE_PINJAMAN_BANK);
            }
            int cnt = DbPinjaman.getNextCounter(pinjaman.getType());
            String prefix = DbPinjaman.getNumberPrefix(pinjaman.getType());
            String number = DbPinjaman.getNextNumber(cnt, pinjaman.getType());
            if (pinjaman.getOID() == 0) {
                pinjaman.setNumber(number);
            }
                                                                            %>
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_NUMBER] %>"  value="<%= pinjaman.getNumber() %>" class="formElemen">
                                                                            <%rptKonstan.setNoRekening(pinjaman.getNumber());%>
                                                                            <tr align="left">
                                                                            <td height="21" width="11%">Bank</td>
                                                                            <td height="21" width="24%"> 
                                                                            <select name="<%=jspPinjaman.colNames[jspPinjaman.JSP_BANK_ID]%>">
                                                                                <%
                                                                                String bankName = "";
            if (listBank != null && listBank.size() > 0) {
                for (int i = 0; i < listBank.size(); i++) {
                    Bank bank = (Bank) listBank.get(i);
                                                                                %>
                                                                                <option value="<%=bank.getOID()%>" <%if (bank.getOID() == pinjaman.getBankId()) { bankName = bank.getName();%>selected<%}%>><%=bank.getName()%></option>
                                                                                <%
                }
            }%>
                                                                            </select>
                                                                            * <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_TOTAL_PINJAMAN) %>
                                                                            <%rptKonstan.setBank(bankName);%>
                                                                            <td height="21" width="14%">&nbsp; 
                                                                            <td height="21" width="51%">&nbsp; 
                                                                            <tr align="left"> 
                                                                            <td height="21" width="11%">Tanggal</td>
                                                                            <td height="21" width="24%"> 
                                                                            <input name="<%=jspPinjaman.colNames[JspPinjaman.JSP_DATE] %>" value="<%=JSPFormater.formatDate((pinjaman.getDate() == null) ? new Date() : pinjaman.getDate(), "dd/MM/yyyy")%>" size="11" readonly>
                                                                            <%rptKonstan.setTanggal(pinjaman.getDate());%>
                                                                            <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.frmpinjaman.<%=jspPinjaman.colNames[JspPinjaman.JSP_DATE] %>);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt=""></a>
                                                                            <td height="21" width="14%">&nbsp; 
                                                                            <td height="21" width="51%">&nbsp; 
                                                                            <tr align="left"> 
                                                                            <td height="21" width="11%">Total Pinjaman</td>
                                                                            <td height="21" width="24%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_TOTAL_PINJAMAN] %>"  value="<%= JSPFormater.formatNumber(pinjaman.getTotalPinjaman(), "#,###.##") %>" class="formElemen" style="text-align:right" onBlur="javascript:checkNumber(this)" onClick="this.select()" onChange="javascript:cmdChangeType()">
                                                                            * <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_TOTAL_PINJAMAN) %>
                                                                            <%rptKonstan.setTotalPinjaman(pinjaman.getTotalPinjaman());%>
                                                                            <td height="21" width="14%">Bunga Pinjaman
                                                                            Koperasi 
                                                                            <td height="21" width="51%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_BUNGA] %>"  value="<%= pinjaman.getBunga() %>" class="formElemen" size="10"  style="text-align:right" onBlur="javascript:checkNumber1(this)" onClick="this.select()" onChange="javascript:cmdChangeType()">
                                                                            % /tahun * <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_BUNGA) %>
                                                                            <%rptKonstan.setBungaPinjamanKoperasi(pinjaman.getBunga());%>
                                                                            <tr align="left">
                                                                            <td height="21" width="11%">Biaya Administrasi</td>
                                                                            <td height="21" width="24%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_BIAYA_ADMINISTRASI] %>"  value="<%= JSPFormater.formatNumber(pinjaman.getBiayaAdministrasi(), "#,###.##") %>" class="formElemen"  style="text-align:right" onBlur="javascript:checkNumber(this)" onClick="this.select()">
                                                                            <%rptKonstan.setBiayaAdministrasi(pinjaman.getBiayaAdministrasi());%>
                                                                            <td height="21" width="14%">Bunga Pinjaman
                                                                            Bank 
                                                                            <td height="21" width="51%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_BUNGA_BANK] %>"  value="<%= pinjaman.getBungaBank() %>" class="formElemen" size="10"  style="text-align:right" onBlur="javascript:checkNumber1(this)" onClick="this.select()">
                                                                            % /tahun * <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_BUNGA_BANK) %>
                                                                            <%rptKonstan.setBungaPinjamanBank(pinjaman.getBungaBank());%>
                                                                            <tr align="left">
                                                                            <td height="21" width="11%">Biaya Provisi</td>
                                                                            <td height="21" width="24%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_PROVISI] %>"  value="<%= JSPFormater.formatNumber(pinjaman.getProvisi(), "#,###.##") %>" class="formElemen"  style="text-align:right" onBlur="javascript:checkNumber(this)" onClick="this.select()">
                                                                            <%rptKonstan.setBiayaProvisi(pinjaman.getProvisi());%>
                                                                            <td height="21" width="14%">Lama Angsuran
                                                                            <td height="21" width="51%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_LAMA_CICILAN] %>"  value="<%= pinjaman.getLamaCicilan() %>" class="formElemen" size="10" onBlur="javascript:checkNumber1(this)" style="text-align:right" onClick="this.select()" onChange="javascript:cmdChangeType()">
                                                                            bulan * <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_LAMA_CICILAN) %>
                                                                            <%rptKonstan.setLamaAngsuran(pinjaman.getLamaCicilan());%>
                                                                            <tr align="left"> 
                                                                            <td height="21" width="11%">Biaya Asuransi</td>
                                                                            <td height="21" width="24%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_BIAYA_ADMINISTRASI] %>3"  value="<%= JSPFormater.formatNumber(pinjaman.getBiayaAdministrasi(), "#,###.##") %>" class="formElemen"  style="text-align:right" onBlur="javascript:checkNumber(this)" onClick="this.select()">
                                                                            <%rptKonstan.setBiayaAsuransi(pinjaman.getBiayaAdministrasi());%>
                                                                            <td height="21" width="14%">Besar Angsuran
                                                                            <td height="21" width="51%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_CICILAN] %>"  value="<%= JSPFormater.formatNumber(pinjaman.getCicilan(), "#,###.##") %>" class="formElemen"  style="text-align:right" onBlur="javascript:checkNumber(this)" onClick="this.select()">
                                                                            <%rptKonstan.setBesarAngsuran(pinjaman.getCicilan());%><tr align="left">
                                                                            <td height="21" width="11%">&nbsp;</td>
                                                                            <td height="21" width="24%">&nbsp; 
                                                                            <td height="21" width="14%" nowrap>Angsuran 
                                                                            Terbayar Sampai Ke 
                                                                            <td height="21" width="51%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_ANGSURAN_TERAKHIR] %>" size="10" value="<%= JSPFormater.formatNumber(pinjaman.getAngsuranTerakhir(), "###") %>" class="formElemen"  style="text-align:right" onBlur="javascript:checkNumber1(this)" onClick="this.select()">
                                                                            <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_ANGSURAN_TERAKHIR) %>
                                                                            <%rptKonstan.setAngsuranTerakhir(pinjaman.getAngsuranTerakhir());%>
                                                                            <tr align="left"> 
                                                                            <td height="21" width="11%">Jenis Pinjaman</td>
                                                                            <td height="21" width="24%"> 
                                                                            <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_JENIS_PINJAMAN_ID] %>">
                                                                                <%
                                                                                JenisPinjaman jPinjaman = new JenisPinjaman();
                                                                                Vector listJenisPinjaman = DbJenisPinjaman.list(0, 0, "", "");
                                                                                for (int i = 0; i < listJenisPinjaman.size(); i++) {
                                                                                try {
                                                                                    jPinjaman = (JenisPinjaman) listJenisPinjaman.get(i);
                                                                                } catch (Exception e){}
                                                                                %>
                                                                                <option value="<%=jPinjaman.getOID()%>" <%if (jPinjaman.getOID() == pinjaman.getJenisPinjamanId()) {%>selected<%}%>><%=jPinjaman.getJenisPinjaman().toUpperCase()%></option>
                                                                                <%}%>
                                                                            </select>
                                                                                <%rptKonstan.setJenisPinjaman(pinjaman.getJenisBarang());%>
                                                                            <td height="21" width="14%">Jatuh Tempo Setiap 
                                                                            Tanggal 
                                                                            <td height="21" width="51%"> 
                                                                            <input type="text" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_TANGGAL_JATUH_TEMPO] %>"  value="<%= pinjaman.getTanggalJatuhTempo() %>" class="formElemen" size="10"  onBlur="javascript:checkNumber1(this)" style="text-align:right" onClick="this.select()">
                                                                            * <%= jspPinjaman.getErrorMsg(JspPinjaman.JSP_TANGGAL_JATUH_TEMPO) %>
                                                                            <%rptKonstan.setJatuhTempo(pinjaman.getTanggalJatuhTempo());%>
                                                                            <tr align="left"> 
                                                                            <td height="10" colspan="4"></td>
                                                                            <tr align="left"> 
                                                                            <td height="21" width="11%">Keterangan</td>
                                                                            <td height="21" colspan="3"> 
                                                                            <textarea name="<%=jspPinjaman.colNames[JspPinjaman.JSP_DETAIL_JENIS_BARANG] %>" class="formElemen" cols="120" rows="2"><%= pinjaman.getDetailJenisBarang() %></textarea>
                                                                            <%rptKonstan.setKeterangan(pinjaman.getDetailJenisBarang());%>
                                                                            <tr align="left">
                                                                                <td height="8" valign="middle" width="11%">&nbsp;</td>
                                                                                <td height="8" width="24%" valign="top">&nbsp;</td>
                                                                                <td height="8" width="14%" valign="top">&nbsp;</td>
                                                                                <td height="8" width="51%" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                            <%if(false){%>
                                                                            <tr align="left"> 
                                                                                <td height="21" valign="middle" width="11%" bgcolor="#EEEEEE"> 
                                                                                    <div align="center"><b>Detail Jurnal </b></div>
                                                                                </td>
                                                                                <td height="21" width="24%" valign="top">&nbsp;</td>
                                                                                <td height="21" width="14%" valign="top">&nbsp;</td>
                                                                                <td height="21" width="51%" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                            <tr align="left"> 
                                                                                <td height="8" valign="middle" colspan="4"> 
                                                                                    <table width="70%" border="0" cellspacing="1" cellpadding="1">
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%" height="21"><b>Keterangan</b></td>
                                                                                            <td width="34%" height="21"><b>Debet</b></td>
                                                                                            <td width="36%" height="21"><b>Kredit</b></td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">Pihutang (AR)</td>
                                                                                            <td width="34%" nowrap> 
                                                                                                <%
            Vector temp = new Vector();
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {

                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_DEBET + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_AR_DEBET_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaArDebetId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_AR_DEBET_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%	}
                                                                                                    }
                                                                                                } else {

                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaArDebetId());
                                                                                                    } catch (Exception e) {
                                                                                                    }

                                                                                                %>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%" nowrap>Pendapatan Admin 
                                                                                            Diterima Pada </td>
                                                                                            <td width="34%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_ADM_DEBET + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ADMIN_DEBET_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaAdminDebetId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ADMIN_DEBET_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }
                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaAdminDebetId());
                                                                                                    } catch (Exception e) {
                                                                                                    }
                                                                                                %>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%" nowrap>Pendapatan Provisi 
                                                                                            Diterima Pada </td>
                                                                                            <td width="34%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_PROVISI_DEBET + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_PROVISI_DEBET_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaProvisiDebetId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_PROVISI_DEBET_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }
                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaProvisiDebetId());
                                                                                                    } catch (Exception e) {
                                                                                                    }
                                                                                                %>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%" nowrap>Pendapatan Asuransi 
                                                                                            Diterima Pada </td>
                                                                                            <td width="34%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_ASURANSI_DEBET + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ASURANSI_DEBET_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaAsuransiDebetId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ASURANSI_DEBET_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }
                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaAsuransiDebetId());
                                                                                                    } catch (Exception e) {
                                                                                                    }
                                                                                                %>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">Pinjaman Diambil Dari</td>
                                                                                            <td width="34%">&nbsp; </td>
                                                                                            <td width="36%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_CREDIT + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_AR_CREDIT_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaArCreditId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_AR_CREDIT_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }
                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaArCreditId());
                                                                                                    } catch (Exception e) {
                                                                                                    }
                                                                                                %>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">Pendapatan Biaya Admin 
                                                                                            Pada </td>
                                                                                            <td width="34%">&nbsp; </td>
                                                                                            <td width="36%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_ADM_CREDIT + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ADMIN_CREDIT_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaAdminCreditId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ADMIN_CREDIT_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }

                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaAdminCreditId());
                                                                                                    } catch (Exception e) {
                                                                                                    }%>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">Pendapatan Biaya Provisi 
                                                                                            Pada </td>
                                                                                            <td width="34%">&nbsp; </td>
                                                                                            <td width="36%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_PROVISI_CREDIT + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_PROVISI_CREDIT_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaProvisiCreditId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_PROVISI_CREDIT_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }

                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaProvisiCreditId());
                                                                                                    } catch (Exception e) {
                                                                                                    }%>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">Pendapatan Biaya Asuransi 
                                                                                            Pada </td>
                                                                                            <td width="34%">&nbsp; </td>
                                                                                            <td width="36%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_ASURANSI_CREDIT + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ASURANSI_CREDIT_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaAsuransiCreditId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_ASURANSI_CREDIT_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }

                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaAsuransiCreditId());
                                                                                                    } catch (Exception e) {
                                                                                                    }%>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">&nbsp;</td>
                                                                                            <td width="34%">&nbsp;</td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1">
                                                                                            <td width="30%"><b>Titipan 1 Kali Angsuran</b></td>
                                                                                            <td width="34%">&nbsp;</td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">Ditampung Pada</td>
                                                                                            <td width="34%"> 
                                                                                                <%
            temp = new Vector();
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {

                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_TITIPAN_DEBET + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_TITIPAN_DEBET_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaTitipanDebetId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_TITIPAN_DEBET_ID] %>2" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%	}
                                                                                                    }
                                                                                                } else {

                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaTitipanDebetId());
                                                                                                    } catch (Exception e) {
                                                                                                    }

                                                                                                %>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#E1E1E1"> 
                                                                                            <td width="30%">Akun Hutang Ke Anggota</td>
                                                                                            <td width="34%">&nbsp; </td>
                                                                                            <td width="36%"> 
                                                                                                <%
            if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {
                temp = DbAccLink.list(0, 0, DbAccLink.colNames[DbAccLink.COL_TYPE] + "='" + I_Project.ACC_LINK_PINJAMAN_TITIPAN_CREDIT + "'", "");
                if (temp != null && temp.size() > 0) {
                    if (temp.size() > 0) {
                                                                                                %>
                                                                                                <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_TITIPAN_CREDIT_ID] %>">
                                                                                                    <%for (int i = 0; i < temp.size(); i++) {
                                                                                                                        AccLink al = (AccLink) temp.get(i);
                                                                                                                        Coa cx = new Coa();
                                                                                                                        try {
                                                                                                                            cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                        } catch (Exception e) {
                                                                                                                        }
                                                                                                    %>
                                                                                                    <option value="<%=cx.getOID()%>" <%if (pinjaman.getCoaTitipanCreditId() == cx.getOID()) {%>selected<%}%>><%=cx.getCode() + " - " + cx.getName()%></option>
                                                                                                    <%}%>
                                                                                                </select>
                                                                                                <%} else {
                                                                                                                    AccLink al = new AccLink();
                                                                                                                    al = (AccLink) temp.get(0);
                                                                                                                    Coa cx = new Coa();
                                                                                                                    try {
                                                                                                                        cx = DbCoa.fetchExc(al.getCoaId());
                                                                                                                    } catch (Exception e) {
                                                                                                                    }
                                                                                                %>
                                                                                                <input type="hidden" name="<%=jspPinjaman.colNames[JspPinjaman.JSP_COA_TITIPAN_CREDIT_ID] %>" value="<%=al.getCoaId()%>">
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}
                                                                                                    }

                                                                                                } else {
                                                                                                    Coa cx = new Coa();
                                                                                                    try {
                                                                                                        cx = DbCoa.fetchExc(pinjaman.getCoaTitipanCreditId());
                                                                                                    } catch (Exception e) {
                                                                                                    }%>
                                                                                                <%=cx.getCode() + " - " + cx.getName()%> 
                                                                                                <%}%>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr> 
                                                                                            <td width="30%">&nbsp;</td>
                                                                                            <td width="34%">&nbsp;</td>
                                                                                            <td width="36%">&nbsp;</td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <%}%>
                                                                            <tr align="left"> 
                                                                                <td height="8" valign="middle" width="11%">&nbsp;</td>
                                                                                <td height="8" width="24%" valign="top">&nbsp;</td>
                                                                                <td height="8" width="14%" valign="top">&nbsp;</td>
                                                                                <td height="8" width="51%" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                            <tr align="left"> 
                                                                                <td height="8" valign="middle" width="11%">Status 
                                                                                Pinjaman </td>
                                                                                <td height="8" width="24%" valign="top"> 
                                                                                    <%if (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT) {%>
                                                                                    <select name="<%=jspPinjaman.colNames[JspPinjaman.JSP_STATUS] %>">
                                                                                        <%for (int i = 0; i < DbPinjaman.strStatus.length; i++) {%>
                                                                                        <option value="<%=i%>" <%if (pinjaman.getStatus() == i) {%>selected<%}%>><%=DbPinjaman.strStatus[i]%></option>
                                                                                        <%}%>
                                                                                    </select>
                                                                                    <%} else {%>
                                                                                    <table width="40%" border="0" cellspacing="1" cellpadding="1">
                                                                                        <tr> 
                                                                                            <td height="25" bgcolor="#FFFF00"> 
                                                                                                <div align="center"><b><font color="#003399"><%=DbPinjaman.strStatus[pinjaman.getStatus()]%></font></b></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                    <%}%>
                                                                                </td>
                                                                                <td height="8" width="14%" valign="top">&nbsp;</td>
                                                                                <td height="8" width="51%" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                            <%if (pinjaman.getOID() != 0) {%>
                                                                            <tr align="left"> 
                                                                                <td height="21" valign="middle" width="11%">&nbsp;</td>
                                                                                <td height="21" width="24%" valign="top">&nbsp;</td>
                                                                                <td height="21" width="14%" valign="top">&nbsp;</td>
                                                                                <td height="21" width="51%" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                            <tr align="left"> 
                                                                                <td height="21" valign="middle" colspan="4"> 
                                                                                    <table width="32%" border="0" cellspacing="1" cellpadding="1">
                                                                                        <tr> 
                                                                                            <td width="33%" class="tablecell1"><b><u>Document 
                                                                                            History</u></b></td>
                                                                                            <td width="34%" class="tablecell1"> 
                                                                                                <div align="center"><b><u>User</u></b></div>
                                                                                            </td>
                                                                                            <td width="33%" class="tablecell1"> 
                                                                                                <div align="center"><b><u>Tanggal</u></b></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr> 
                                                                                            <td width="33%" class="tablecell1"><i>Dibuat 
                                                                                            oleh</i></td>
                                                                                            <td width="34%" class="tablecell1"> 
                                                                                                <div align="center"> <i> 
                                                                                                        <%
    User u = new User();
    try {
        u = DbUser.fetch(pinjaman.getUserId());
    } catch (Exception e) {
    }
                                                                                                        %>
                                                                                                <%=u.getLoginId()%></i></div>
                                                                                            </td>
                                                                                            <td width="33%" class="tablecell1"> 
                                                                                                <div align="center"><i><%=JSPFormater.formatDate(pinjaman.getDate(), "dd MMMM yy")%></i></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr> 
                                                                                            <td width="33%" class="tablecell1"><i> 
                                                                                                    <%if (pinjaman.getStatus() == DbPinjaman.STATUS_APPROVE || pinjaman.getStatus() == DbPinjaman.STATUS_LUNAS) {%>
                                                                                                    Disetujui 
                                                                                                    <%} else if (pinjaman.getStatus() == DbPinjaman.STATUS_DITOLAK) {%>
                                                                                                    Ditolak 
                                                                                                    <%}%>
                                                                                            oleh</i></td>
                                                                                            <td width="34%" class="tablecell1"> 
                                                                                                <div align="center"> <i> 
                                                                                                        <%
    u = new User();
    try {
        u = DbUser.fetch(pinjaman.getApproveById());
    } catch (Exception e) {
    }
                                                                                                        %>
                                                                                                <%=u.getLoginId()%></i></div>
                                                                                            </td>
                                                                                            <td width="33%" class="tablecell1"> 
                                                                                                <div align="center"> <i> 
                                                                                                        <%if (pinjaman.getApproveById() != 0) {%>
                                                                                                        <%=JSPFormater.formatDate(pinjaman.getApproveDate(), "dd MMMM yy")%> 
                                                                                                        <%}%>
                                                                                                </i></div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <%}%>
                                                                            <tr align="left"> 
                                                                                <td height="21" valign="middle" width="11%">&nbsp;</td>
                                                                                <td height="21" width="24%" valign="top">&nbsp;</td>
                                                                                <td height="21" width="14%" valign="top">&nbsp;</td>
                                                                                <td height="21" width="51%" valign="top">&nbsp; 
                                                                                </td>
                                                                            </tr>
                                                                            <tr align="left" > 
                                                                                <td colspan="4" class="command" valign="top"> 
                                                                                    <%
            ctrLine.setLocationImg(approot + "/images/ctr_line");
            ctrLine.initDefault();
            ctrLine.setTableWidth("60%");
            String scomDel = "javascript:cmdAsk('" + oidPinjaman + "')";
            String sconDelCom = "javascript:cmdConfirmDelete('" + oidPinjaman + "')";
            String scancel = "javascript:cmdEdit('" + oidPinjaman + "')";
            ctrLine.setBackCaption("Ke List Pinjaman");
            ctrLine.setJSPCommandStyle("buttonlink");
            ctrLine.setDeleteCaption("Hapus");
            ctrLine.setSaveCaption("Simpan");
            ctrLine.setConfirmDelCaption("Ya Hapus");
            ctrLine.setAddCaption("");

            if (privDelete) {
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

            if (pinjaman.getStatus() != DbPinjaman.STATUS_DRAFT) {
                ctrLine.setDeleteCaption("");
                ctrLine.setSaveCaption("");
            }

            if (iJSPCommand == JSPCommand.UPDATE) {
                iJSPCommand = JSPCommand.EDIT;
            }

                                                                                    %>
                                                                                <%= ctrLine.drawImage(iJSPCommand, iErrCode, msgString)%> </td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="11%">&nbsp;</td>
                                                                                <td width="24%">&nbsp;</td>
                                                                                <td width="14%">&nbsp;</td>
                                                                                <td width="51%">&nbsp;</td>
                                                                            </tr>
                                                                            <%if (pds != null && pds.size() > 0) {%>
                                                                            <tr align="left" valign="middle" > 
                                                                                <td colspan="4" height="23"> 
                                                                                    <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                                                        <tr> 
                                                                                            <td class="tablecell1" height="20" >&nbsp;<b>Ilustrasi 
                                                                                            Angsuran</b> </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                </td>
                                                                            </tr>
                                                                            <tr align="left" > 
                                                                                <td colspan="4" valign="top"> 
                                                                                    <div align="left"> 
                                                                                        <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                                                                            <tr> 
                                                                                                <td class="tablehdr" width="3%">&nbsp;</td>
                                                                                                <td class="tablehdr" colspan="5">Koperasi</td>
                                                                                                <td class="tablehdr" colspan="5">Bank</td>
                                                                                                <td class="tablehdr" width="6%">&nbsp;</td>
                                                                                                <td class="tablehdr" width="5%">&nbsp;</td>
                                                                                                <td class="tablehdr" width="6%">&nbsp;</td>
                                                                                                <td width="5%" class="tablehdr">&nbsp;</td>
                                                                                                <td width="7%" class="tablehdr">&nbsp;</td>
                                                                                            </tr>
                                                                                            <tr> 
                                                                                                <td class="tablehdr" width="3%">Ang.</td>
                                                                                                <td class="tablehdr" width="9%">%/bln</td>
                                                                                                <td class="tablehdr" width="9%">Pokok 
                                                                                                </td>
                                                                                                <td class="tablehdr" width="9%">Bunga 
                                                                                                </td>
                                                                                                <td class="tablehdr" width="9%"> 
                                                                                                    <div align="center">Saldo</div>
                                                                                                </td>
                                                                                                <td class="tablehdr" width="8%">Angsuran 
                                                                                                </td>
                                                                                                <td class="tablehdr" width="9%">%/bln</td>
                                                                                                <td class="tablehdr" width="9%">Pokok 
                                                                                                </td>
                                                                                                <td class="tablehdr" width="8%">Bunga 
                                                                                                </td>
                                                                                                <td class="tablehdr" width="9%">Saldo 
                                                                                                </td>
                                                                                                <td class="tablehdr" width="7%">Angsuran 
                                                                                                </td>
                                                                                                <td class="tablehdr" width="6%">Jatuh 
                                                                                                Tempo</td>
                                                                                                <td class="tablehdr" width="5%">Status</td>
                                                                                                <td class="tablehdr" width="6%">Tanggal 
                                                                                                Angsuran</td>
                                                                                                <td width="5%" class="tablehdr">Angsuran</td>
                                                                                                <td width="7%" class="tablehdr">Pelunasan</td>
                                                                                            </tr>
                                                                                            <tr> 
                                                                                                <td class="tablecell" width="3%"> 
                                                                                                    <div align="center"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap>&nbsp;</td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pinjaman.getTotalPinjaman(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="8%" nowrap> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap>&nbsp;</td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="8%" nowrap> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pinjaman.getTotalPinjaman(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="7%" nowrap> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="6%" nowrap> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="5%" nowrap> 
                                                                                                    <div align="center"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="6%" nowrap> 
                                                                                                    <div align="center"></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="5%">&nbsp; 
                                                                                                </td>
                                                                                                <td class="tablecell" width="7%"> 
                                                                                                    <div align="center"></div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <%
    boolean nextPayment = false;
    long prevPaidDetail = 0;

    for (int i = 0; i < pds.size(); i++) {
        PinjamanDetail pd = (PinjamanDetail) pds.get(i);

        RptAnggotaPinjamBankAnuitasL detail = new RptAnggotaPinjamBankAnuitasL();

        Vector bayars = DbBayarPinjaman.list(0, 0, "pinjaman_detail_id=" + pd.getOID(), "");
        BayarPinjaman bp = new BayarPinjaman();
        if (bayars != null && bayars.size() > 0) {
            bp = (BayarPinjaman) bayars.get(0);
        }

        //pertama
        if (i == 0 && bp.getOID() == 0) {
            nextPayment = true;
        } //kedua dst
        else {
            if (prevPaidDetail != 0 && bp.getOID() == 0) {
                nextPayment = true;
            }
        }
        detail.setAngsuran(pd.getCicilanKe());
        detail.setAngPokokKop(pd.getAmount());
        detail.setBungaKop(pd.getBunga());
        detail.setTotalAngsuranKop(pd.getTotalKoperasi());
        detail.setSaldoKop(pd.getSaldoKoperasi());

        detail.setAngPokokBank(pd.getAmountBank());
        detail.setBungaBank(pd.getBungaBank());
        detail.setTotalAngsuranBank(pd.getTotalBank());
        detail.setSaldoBank(pd.getSaldoBank());

        detail.setTglJatuhTempo(pd.getJatuhTempo());

        vTemp.add(detail);

        prevPaidDetail = bp.getOID();
                                                                                            %>
                                                                                            <%
                                                                                                Date xdt = new Date();
                                                                                                Date jtt = pd.getJatuhTempo();

                                                                                                boolean editable = true;
                                                                                                if (pd.getStatus() == 0) {
                                                                                                    if (jtt.getYear() < xdt.getYear()) {
                                                                                                        editable = false;
                                                                                                    } else {
                                                                                                        if (jtt.getYear() == xdt.getYear()) {
                                                                                                            if (jtt.getMonth() <= xdt.getMonth()) {
                                                                                                                editable = false;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    editable = false;
                                                                                                }


                                                                                                if (i % 2 == 0) {
                                                                                            %>
                                                                                            <tr> 
                                                                                                <td class="tablecell" width="3%"> 
                                                                                                    <div align="center"><%=pd.getCicilanKe()%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right"> 
                                                                                                        <%
                                                                                                if (editable && (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT || pinjaman.getStatus() == DbPinjaman.STATUS_APPROVE)) {%>
                                                                                                        <a href="javascript:cmdEditBunga('<%=pd.getOID()%>')"><%=JSPFormater.formatNumber(pd.getBungaKoperasiPercent(), "#,###.##")%></a> 
                                                                                                        <%} else {%>
                                                                                                        <%=JSPFormater.formatNumber(pd.getBungaKoperasiPercent(), "#,###.##")%> 
                                                                                                        <%}%>
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getAmount(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getBunga(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getSaldoKoperasi(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="8%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getTotalKoperasi(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right"><%=JSPFormater.formatNumber(pd.getBungaBankPercent(), "#,###.##")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getAmountBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="8%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getBungaBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getSaldoBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="7%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getTotalBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="6%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp; 
                                                                                                        <%if (i == 0 && bp.getOID() == 0) {%>
                                                                                                        <a href="javascript:cmdUpdateTanggal('<%=pd.getOID()%>')"><%=JSPFormater.formatDate(pd.getJatuhTempo(), "dd/MM/yy")%></a> 
                                                                                                        <%} else {%>
                                                                                                        <%=JSPFormater.formatDate(pd.getJatuhTempo(), "dd/MM/yy")%> 
                                                                                                        <%}%>
                                                                                                    &nbsp;&nbsp;</div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="5%" nowrap> 
                                                                                                    <div align="center"><%=DbPinjaman.strPaymentStatus[pd.getStatus()]%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="6%" nowrap> 
                                                                                                    <div align="center"><%=(bp.getOID() != 0) ? JSPFormater.formatDate(bp.getTanggal(), "dd/MM/yy") : "-"%></div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="5%"> 
                                                                                                    <div align="center"> 
                                                                                                        <%
                                                                                                if (pinjaman.getStatus() != DbPinjaman.STATUS_DRAFT) {
                                                                                                    if (bp.getOID() == 0) {
                                                                                                        if (pinjaman.getStatus() != DbPinjaman.STATUS_LUNAS && nextPayment) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdBayar('<%=pd.getOID()%>')">Angsuran</a> 
                                                                                                        <%	}
                                                                                                                } else {
                                                                                                                    if (bp.getType() == DbBayarPinjaman.TYPE_ANGSURAN) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdBayarDetail('<%=pd.getOID()%>')">Detail</a> 
                                                                                                        <%
                                                                                                        }
                                                                                                    }
                                                                                                }%>
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td class="tablecell" width="7%"> 
                                                                                                    <div align="center"> 
                                                                                                        <%
                                                                                                if (pinjaman.getStatus() != DbPinjaman.STATUS_DRAFT) {
                                                                                                    if (bp.getOID() == 0) {
                                                                                                        if (pinjaman.getStatus() != DbPinjaman.STATUS_LUNAS && nextPayment) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdPelunasan('<%=pd.getOID()%>')">Pelunasan</a> 
                                                                                                        <%
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    if (bp.getType() == DbBayarPinjaman.TYPE_PELUNASAN) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdPelunasanDetail('<%=pd.getOID()%>')">Detail</a> 
                                                                                                        <%
                                                                                                        }
                                                                                                    }
                                                                                                }%>
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <%} else {%>
                                                                                            <tr> 
                                                                                                <td class="tablecell1" width="3%"> 
                                                                                                    <div align="center"><%=pd.getCicilanKe()%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="9%" nowrap> 
                                                                                                    <div align="right"> 
                                                                                                        <%
                                                                                                if (editable && (pinjaman.getStatus() == DbPinjaman.STATUS_DRAFT || pinjaman.getStatus() == DbPinjaman.STATUS_APPROVE)) {%>
                                                                                                        <a href="javascript:cmdEditBunga('<%=pd.getOID()%>')"><%=JSPFormater.formatNumber(pd.getBungaKoperasiPercent(), "#,###.##")%></a> 
                                                                                                        <%} else {%>
                                                                                                        <%=JSPFormater.formatNumber(pd.getBungaKoperasiPercent(), "#,###.##")%> 
                                                                                                        <%}%>
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getAmount(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getBunga(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getSaldoKoperasi(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="8%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getTotalKoperasi(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="9%" nowrap> 
                                                                                                    <div align="right"><%=JSPFormater.formatNumber(pd.getBungaBankPercent(), "#,###.##")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getAmountBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="8%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getBungaBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="9%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getSaldoBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="7%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp;<%=JSPFormater.formatNumber(pd.getTotalBank(), "#,###")%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="6%" nowrap> 
                                                                                                    <div align="right">&nbsp;&nbsp; 
                                                                                                        <%if (i == 0 && bp.getOID() == 0) {%>
                                                                                                        <a href="javascript:cmdUpdateTanggal('<%=pd.getOID()%>')"><%=JSPFormater.formatDate(pd.getJatuhTempo(), "dd/MM/yy")%></a> 
                                                                                                        <%} else {%>
                                                                                                        <%=JSPFormater.formatDate(pd.getJatuhTempo(), "dd/MM/yy")%> 
                                                                                                        <%}%>
                                                                                                    &nbsp;&nbsp;</div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="5%" nowrap> 
                                                                                                    <div align="center"><%=DbPinjaman.strPaymentStatus[pd.getStatus()]%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="6%" nowrap> 
                                                                                                    <div align="center"><%=(bp.getOID() != 0) ? JSPFormater.formatDate(bp.getTanggal(), "dd/MM/yy") : "-"%></div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="5%"> 
                                                                                                    <div align="center"> 
                                                                                                        <%
                                                                                                if (pinjaman.getStatus() != DbPinjaman.STATUS_DRAFT) {
                                                                                                    if (bp.getOID() == 0) {
                                                                                                        if (pinjaman.getStatus() != DbPinjaman.STATUS_LUNAS && nextPayment) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdBayar('<%=pd.getOID()%>')">Angsuran</a> 
                                                                                                        <%	}
                                                                                                                } else {
                                                                                                                    if (bp.getType() == DbBayarPinjaman.TYPE_ANGSURAN) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdBayarDetail('<%=pd.getOID()%>')">Detail</a> 
                                                                                                        <%
                                                                                                        }
                                                                                                    }
                                                                                                }%>
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td class="tablecell1" width="7%"> 
                                                                                                    <div align="center"> 
                                                                                                        <%
                                                                                                if (pinjaman.getStatus() != DbPinjaman.STATUS_DRAFT) {
                                                                                                    if (bp.getOID() == 0) {
                                                                                                        if (pinjaman.getStatus() != DbPinjaman.STATUS_LUNAS && nextPayment) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdPelunasan('<%=pd.getOID()%>')">Pelunasan</a> 
                                                                                                        <%
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    if (bp.getType() == DbBayarPinjaman.TYPE_PELUNASAN) {
                                                                                                        %>
                                                                                                        <a href="javascript:cmdPelunasanDetail('<%=pd.getOID()%>')">Detail</a> 
                                                                                                        <%
                                                                                                        }
                                                                                                    }
                                                                                                }%>
                                                                                                    </div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            
                                                                                            <%



                                                                                                }%>
                                                                                            <%if (iJSPCommand == JSPCommand.PRINT && pd.getOID() == oidPinjamanDetail) {%>
                                                                                            <tr>
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%"><a name="go"></a></td>
                                                                                                <td width="9%"></td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="7%">Jatuh tempo Angsuran 
                                                                                                I</td>
                                                                                                <td width="6%"> 
                                                                                                    <input name="jatuh_tempo" value="<%=JSPFormater.formatDate(new Date(), "dd/MM/yyyy")%>" size="11" readonly>
                                                                                                <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.frmpinjaman.jatuh_tempo);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt=""></a></td>
                                                                                                <td width="5%"><a href="javascript:cmdUpdateJT()">Update</a></td>
                                                                                                <td width="6%"><a href="javascript:cmdCancel()">Batalkan</a></td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                            </tr>
                                                                                            <%}%>
                                                                                            <%if (iJSPCommand == JSPCommand.SUBMIT && pd.getOID() == oidPinjamanDetail) {%>
                                                                                            <tr> 
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%"><a name="go"></a></td>
                                                                                                <td width="9%"></td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                            </tr>
                                                                                            <tr> 
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%"><b>Bunga Kop.</b></td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="center"> 
                                                                                                        <input type="text" name="bunga_koperasi" size="10">
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td width="9%"><b>Bunga Bank</b></td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="center"> 
                                                                                                        <input type="text" name="bunga_bank" size="10">
                                                                                                    </div>
                                                                                                </td>
                                                                                                <td width="8%"> 
                                                                                                    <div align="center"><a href="javascript:cmdUpdateBunga()">Update</a></div>
                                                                                                </td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="center"><a href="javascript:cmdCancel()">Batalkan</a></div>
                                                                                                </td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                            </tr>
                                                                                            <tr> 
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="center">%/tahun</div>
                                                                                                </td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="center">%/tahun</div>
                                                                                                </td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                            </tr>
                                                                                            <%}
                                                                                                nextPayment = false;
                                                                                            %>
                                                                                            <%}%>
                                                                                            <tr> 
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="6%">&nbsp;</td>
                                                                                                <td width="5%">&nbsp;</td>
                                                                                                <td width="7%">&nbsp;</td>
                                                                                            </tr>
                                                                                            <tr> 
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td colspan="2"><b>Total Pinjaman</b> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td colspan="4" class="tablecell1"> 
                                                                                                    <div align="right"><b><%= JSPFormater.formatNumber(pinjaman.getTotalPinjaman(), "#,###.##") %></b></div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr> 
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td colspan="2"><b>Total Pembayaran</b> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td colspan="4" class="tablecell1"> 
                                                                                                    <div align="right"> <b> 
                                                                                                            <%
    double totalBayar = DbBayarPinjaman.getTotalPayment(pinjaman.getOID());
                                                                                                            %>
                                                                                                    <%= JSPFormater.formatNumber(totalBayar, "#,###.##") %></b></div>
                                                                                                </td>
                                                                                            </tr>
                                                                                            <tr> 
                                                                                                <td width="3%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="9%">&nbsp;</td>
                                                                                                <td width="8%">&nbsp;</td>
                                                                                                <td width="9%"> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td colspan="2"><b>Sisa</b> 
                                                                                                    <div align="right"></div>
                                                                                                </td>
                                                                                                <td colspan="4" class="tablecell1"> 
                                                                                                    <div align="right"><b><%= JSPFormater.formatNumber(pinjaman.getTotalPinjaman() - totalBayar, "#,###.##") %></b></div>
                                                                                                </td>
                                                                                            </tr>
                                                                                        </table>
                                                                                    </div>
                                                                                </td>
                                                                            </tr>
                                                                            <tr align="left" >
                                                                                <td colspan="4" valign="top">
                                                                                    <table width="30%" border="0" cellspacing="0" cellpadding="0">
                                                                                        <tr>
                                                                                            <td><a href="javascript:cmdPrintXLS()"  onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('close211111','','../images/printxls2.gif',1)"><img src="../images/printxls.gif" name="close211111" border="0"></a></td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td><a href="javascript:cmdPrintPdf()"  onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('printpdf','','../images/exportpdf2.png',1)"><img src="../images/exportpdf.png" name="printpdf" border="0"></a></td>
                                                                                        </tr>
                                                                                    </table>
                                                                                &nbsp;</td>
                                                                            </tr>
                                                                            <%}%>
                                                                            <tr align="left" > 
                                                                                <td colspan="4" valign="top" height="12">&nbsp;</td>
                                                                            </tr>
                                                                            <tr align="left" > 
                                                                                <td colspan="4" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                            <tr align="left" > 
                                                                                <td colspan="4" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                            <tr align="left" > 
                                                                                <td colspan="4" valign="top">&nbsp;</td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </form>
                                                        <%
            session.putValue("KONSTAN", rptKonstan);
            session.putValue("DETAIL", vTemp);
                                                        %>
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
