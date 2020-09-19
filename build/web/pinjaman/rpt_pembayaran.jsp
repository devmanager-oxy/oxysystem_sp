 
<%@ page language="java"%>
<%@ page import = "java.util.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "com.project.util.*" %>
<%@ page import = "com.project.util.jsp.*" %>
<%@ page import = "com.project.system.*" %>
<%@ page import = "com.project.main.db.*" %>
<%@ page import = "com.project.coorp.member.*" %>
<%@ page import = "com.project.coorp.pinjaman.*" %>

<%@ page import = "com.project.fms.report.*" %>

<%@ page import = "com.project.*" %>
<%@ page import = "java.util.Date" %>
<%@ include file="../main/javainit.jsp"%>
<%@ include file="../main/checksp.jsp"%>
<%

int srcJenisBarang = JSPRequestValue.requestInt(request, "src_jenis_barang");
int srcTypePinjaman = JSPRequestValue.requestInt(request, "src_type_pinjaman");
String srcStartDate = JSPRequestValue.requestString(request, "start_date");
String srcEndDate = JSPRequestValue.requestString(request, "end_date");

Date startDate = new Date();
Date endDate = new Date();
if(srcStartDate.length()>0){
	startDate = JSPFormater.formatDate(srcStartDate, "dd/MM/yyyy");
}
if(srcEndDate.length()>0){
	endDate = JSPFormater.formatDate(srcEndDate, "dd/MM/yyyy");
}

	if(session.getValue("KONSTAN")!=null){
		session.removeValue("KONSTAN");
	}
	if(session.getValue("DETAIL")!=null){
		session.removeValue("DETAIL");
	}
	if(session.getValue("SUMRAY")!=null){
		session.removeValue("SUMRAY");
	}
	
//jsp content
int type = JSPRequestValue.requestInt(request, "src_type");
Vector temp = DbPinjaman.getSaldoPiutang(type, srcJenisBarang, srcTypePinjaman);

//object for get value
RptTypeLaporan rptKonstan = new RptTypeLaporan();
Vector vTempDetail = new Vector();
Vector vTemp = new Vector();


%>
<html >
<!-- #BeginTemplate "/Templates/indexsp.dwt" --> 
<head>
<!-- #BeginEditable "javascript" --> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Simpan Pinjam</title>
<link href="../css/csssp.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<!--

function cmdChange(){
	document.form1.action="saldopiutang.jsp";
	document.form1.submit();
}

function cmdPrintSumrayXLS(){	 
	window.open("<%=printroot%>.report.RptLaporanPiutangSumrayXLS?idx=<%=System.currentTimeMillis()%>");
}

function cmdPrintDetailXLS(){	 
	window.open("<%=printroot%>.report.RptLaporanPiutangDetailXLS?idx=<%=System.currentTimeMillis()%>");
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}
function MM_preloadImages() { //v3.0
  var d=document; if(d.imagessp){ if(!d.MM_p) d.MM_p=new Array();
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
                      <td class="title"><!-- #BeginEditable "title" --><span class="level1">Keanggotaan</span> 
                        &raquo; <span class="level1">Simpan Pinjam</span> &raquo; 
                        <span class="level2">Saldo Piutang<br>
                        </span><!-- #EndEditable --></td>
                    </tr>
                    <!--tr> 
                      <td><img src="<%=approot%>/imagessp/title-sp.gif" width="584" height="1"></td> 
                    </tr-->
                    <tr> 
                      <td><!-- #BeginEditable "content" --> 
                        <form id="form1" name="form1" method="post" action="">
                          <input type="hidden" name="command">
						  <input type="hidden" name="menu_idx" value="<%=menuIdx%>">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td>&nbsp;</td>
                            </tr>
                            <tr> 
                              <td class="container">
                                <table width="100%" border="0" cellspacing="1" cellpadding="1">
                                  <tr>
                                    <td>
                                      <table width="40%" border="0" cellspacing="0" cellpadding="0">
                                        <tr> 
                                          <td width="26%" height="25"><b>Jenis 
                                            Pinjaman</b></td>
                                          <td width="74%" height="25"> 
                                            <select name="src_jenis_barang" onChange="javascript:cmdChange()">
                                              <%for(int i=0; i<DbPinjaman.strJenisBarang.length; i++){%>
                                              <option value="<%=i%>" <%if(i==srcJenisBarang){%>selected<%}%>><%=DbPinjaman.strJenisBarang[i]%></option>
                                              <%}%>
                                            </select>
                                          </td>
                                        </tr>
                                        <!--tr> 
                                          <td width="26%" height="20"><b>Tampilan</b></td>
                                          <td width="74%" height="20"> 
                                            <select name="src_type" onChange="javascript:cmdChange()">
                                              <%
										  if(type==1){
										    rptKonstan.setPiutangDetail("Detail Per Rekening Pinjaman");
										  }else{
										  	rptKonstan.setSumrayPiutang("Summary Per Anggota");
										  }
										%>
                                              <option value="1" <%if(type==1){%>selected<%}%>>Detail 
                                              Per Rekening Pinjaman</option>
                                              <option value="0" <%if(type==0){%>selected<%}%>>Summary 
                                              Per Anggota</option>
                                            </select>
                                          </td>
                                        </tr-->
                                        <tr> 
                                          <td width="26%" height="20"><b>Pinjaman</b></td>
                                          <td width="74%" height="20"> 
                                            <select name="src_type_pinjaman" onChange="javascript:cmdChange()">
                                              <option value="-1" <%if(srcTypePinjaman==-1){%>selected<%}%>>-</option>
                                              <option value="0" <%if(srcTypePinjaman==0){%>selected<%}%>>Koperasi</option>
                                              <option value="1" <%if(srcTypePinjaman==1){%>selected<%}%>>Bank 
                                              Mandiri</option>
                                            </select>
                                          </td>
                                        </tr>
                                        <tr> 
                                          <td width="26%" height="20">Tanggal</td>
                                          <td width="74%" height="20"> 
                                            <input name="start_date" value="<%=JSPFormater.formatDate((startDate==null) ? new Date() : startDate, "dd/MM/yyyy")%>" size="11" readonly>
                                            <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.form1.start_date);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt=""></a> 
                                            - 
                                            <input name="end_date" value="<%=JSPFormater.formatDate((endDate==null) ? new Date() : endDate, "dd/MM/yyyy")%>" size="11" readonly>
                                            <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.form1.end_date);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt=""></a> 
                                          </td>
                                        </tr>
                                        <tr> 
                                          <td width="26%" height="20">NIK</td>
                                          <td width="74%" height="20"> 
                                            <input type="text" name="textfield">
                                          </td>
                                        </tr>
                                        <tr> 
                                          <td width="26%" height="20">Nama</td>
                                          <td width="74%" height="20"> 
                                            <input type="text" name="textfield2">
                                          </td>
                                        </tr>
                                        <tr>
                                          <td width="26%" height="20">&nbsp;</td>
                                          <td width="74%" height="20">
                                            <input type="button" name="Button" value="Get Report">
                                          </td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr> 
                                    <td>&nbsp; </td>
                                  </tr>
                                  <tr> 
                                    <td>&nbsp;</td>
                                  </tr>
                                  <%
								  
								  double totalPinjaman = 0;
								  double totalAngsuran = 0;
								  
								  if(type==1){%>
                                  <tr> 
                                    <td> 
                                      <table width="87%" border="0" cellspacing="1" cellpadding="1">
                                        <tr> 
                                          <td width="3%" class="tablehdr">No</td>
                                          <td width="10%" class="tablehdr">NIK</td>
                                          <td width="16%" class="tablehdr">Nama</td>
                                          <td width="14%" class="tablehdr">Nomor 
                                            Rekening</td>
                                          <td width="11%" class="tablehdr">Tanggal</td>
                                          <td width="16%" class="tablehdr">Total 
                                            Pinjaman</td>
                                          <td width="15%" class="tablehdr">Total 
                                            Angsuran</td>
                                          <td width="15%" class="tablehdr">Saldo 
                                            Piutang</td>
                                        </tr>
                                        <%
										
											
										if(temp!=null && temp.size()>0){
										
											
										  
										  	for(int i=0; i<temp.size(); i++){
												Pinjaman p = (Pinjaman)temp.get(i);
												
												RptLaporanPiutangDetail detail = new RptLaporanPiutangDetail();
												
												//System.out.println("p.getMemberId() : "+p.getMemberId()); 
												
												Member m = new Member();
												try{
													m = DbMember.fetchExc(p.getMemberId());
												}
												catch(Exception e){
													System.out.println(e.toString());
												}
												
												totalPinjaman = totalPinjaman + p.getTotalPinjaman();
												totalAngsuran = totalAngsuran + p.getCicilan();
												
												detail.setNik(m.getNoMember());
												detail.setNama(m.getNama());
												detail.setNoRekening(p.getNumber());
												detail.setTanggal(p.getDate());
												detail.setTotalPinjaman(p.getTotalPinjaman());
												detail.setTotalAngsuran(p.getCicilan());
												double stSaldo = p.getTotalPinjaman() - p.getCicilan();
												detail.setTotalSaldo(stSaldo);
												vTempDetail.add(detail);
												
												if(i%2==0){
										%>
                                        <tr> 
                                          <td width="3%" class="tablecell" height="20"><%=i+1%></td>
                                          <td width="10%" class="tablecell" height="20"><%=m.getNoMember()%></td>
                                          <td width="16%" class="tablecell" height="20"><%=m.getNama()%></td>
                                          <td width="14%" class="tablecell" height="20"><%=p.getNumber()%></td>
                                          <td width="11%" class="tablecell" height="20"><%=JSPFormater.formatDate(p.getDate(), "dd/MM/yyyy")%></td>
                                          <td width="16%" class="tablecell" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getTotalPinjaman(), "#,###")%></div>
                                          </td>
                                          <td width="15%" class="tablecell" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getCicilan(), "#,###")%></div>
                                          </td>
                                          <td width="15%" class="tablecell" height="20"> 
                                            <div align="right"> 
                                              <%
										  double balance = p.getTotalPinjaman() - p.getCicilan();
										  %>
                                              <%=JSPFormater.formatNumber(balance, "#,###")%></div>
                                          </td>
                                        </tr>
                                        <%}else{%>
                                        <tr> 
                                          <td width="3%" class="tablecell1" height="20"><%=i+1%></td>
                                          <td width="10%" class="tablecell1" height="20"><%=m.getNoMember()%></td>
                                          <td width="16%" class="tablecell1" height="20"><%=m.getNama()%></td>
                                          <td width="14%" class="tablecell1" height="20"><%=p.getNumber()%></td>
                                          <td width="11%" class="tablecell1" height="20"><%=JSPFormater.formatDate(p.getDate(), "dd/MM/yyyy")%></td>
                                          <td width="16%" class="tablecell1" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getTotalPinjaman(), "#,###")%></div>
                                          </td>
                                          <td width="15%" class="tablecell1" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getCicilan(), "#,###")%></div>
                                          </td>
                                          <td width="15%" class="tablecell1" height="20"> 
                                            <div align="right"> 
                                              <%
										  double balance = p.getTotalPinjaman() - p.getCicilan();
										  %>
                                              <%=JSPFormater.formatNumber(balance, "#,###")%></div>
                                          </td>
                                        </tr>
                                        <%}}}%>
                                        <tr> 
                                          <td width="3%" height="22">&nbsp;</td>
                                          <td width="10%" height="22">&nbsp;</td>
                                          <td width="16%" height="22">&nbsp;</td>
                                          <td width="14%" height="22">&nbsp;</td>
                                          <td width="11%" bgcolor="#E1E1E1" height="22"><b>T 
                                            O T A L :</b></td>
                                          <td width="16%" bgcolor="#E1E1E1" height="22"> 
                                            <div align="right"><b><%=JSPFormater.formatNumber(totalPinjaman, "#,###")%></b></div>
                                            <% rptKonstan.setTotalPinjamanPiutangDetail(totalPinjaman);%>
                                          </td>
                                          <td width="15%" bgcolor="#E1E1E1" height="22"> 
                                            <div align="right"><b><%=JSPFormater.formatNumber(totalAngsuran, "#,###")%></b></div>
                                            <% rptKonstan.setTotalAngsuranPiutangDetail(totalAngsuran);%>
                                          </td>
                                          <td width="15%" bgcolor="#E1E1E1" height="22"> 
                                            <div align="right"><b><%=JSPFormater.formatNumber(totalPinjaman-totalAngsuran, "#,###")%></b></div>
                                            <%
											 double totalSaldo = totalPinjaman-totalAngsuran; 
											 rptKonstan.setTotalSaldoPiutangDetail(totalSaldo);
											%>
                                          </td>
                                        </tr>
                                        <tr> 
                                          <td width="3%">&nbsp;</td>
                                          <td width="10%">&nbsp;</td>
                                          <td width="16%">&nbsp;</td>
                                          <td width="14%">&nbsp;</td>
                                          <td width="11%">&nbsp;</td>
                                          <td width="16%">&nbsp;</td>
                                          <td width="15%">&nbsp;</td>
                                          <td width="15%">&nbsp;</td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  <%}else{%>
                                  <tr> 
                                    <td> 
                                      <table width="80%" border="0" cellspacing="1" cellpadding="1">
                                        <tr> 
                                          <td width="5%" class="tablehdr">No</td>
                                          <td width="12%" class="tablehdr">NIK</td>
                                          <td width="24%" class="tablehdr">Nama</td>
                                          <td width="20%" class="tablehdr">Total 
                                            Pinjaman</td>
                                          <td width="19%" class="tablehdr">Total 
                                            Angsuran</td>
                                          <td width="20%" class="tablehdr">Saldo 
                                            Piutang</td>
                                        </tr>
                                        <%
										if(temp!=null && temp.size()>0){
										  
										  	for(int i=0; i<temp.size(); i++){
												Pinjaman p = (Pinjaman)temp.get(i);
												
												RptLaporanPiutangSumray detail = new RptLaporanPiutangSumray();
												
												Member m = new Member();
												try{
													m = DbMember.fetchExc(p.getMemberId());
												}
												catch(Exception e){
												}
												
												//out.println("p.getMemberId() : "+p.getMemberId());
												Vector v = DbMember.list(0,1,"member_id='"+p.getMemberId()+"'", "");
												//out.println("v :"+v);
												if(v!=null && v.size()>0){
													m = (Member)v.get(0);
												}
												
												totalPinjaman = totalPinjaman + p.getTotalPinjaman();
												totalAngsuran = totalAngsuran + p.getCicilan();
												
												detail.setNik(m.getNoMember());
												detail.setNama(m.getNama());
												detail.setTotalPinjaman(p.getTotalPinjaman());
												detail.setTotalAngsuran(p.getCicilan());
												double tSaldo = p.getTotalPinjaman() - p.getCicilan();
												detail.setTotalSaldo(tSaldo);
												
												vTemp.add(detail);
												
												if(i%2==0){
										%>
                                        <tr> 
                                          <td width="5%" class="tablecell" height="20"><%=i+1%></td>
                                          <td width="12%" class="tablecell" height="20"><%=m.getNoMember()%></td>
                                          <td width="24%" class="tablecell" height="20"><%=m.getNama()%></td>
                                          <td width="20%" class="tablecell" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getTotalPinjaman(), "#,###")%></div>
                                          </td>
                                          <td width="19%" class="tablecell" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getCicilan(), "#,###")%></div>
                                          </td>
                                          <td width="20%" class="tablecell" height="20"> 
                                            <div align="right"> 
                                              <%
										  double balance = p.getTotalPinjaman() - p.getCicilan();
										  %>
                                              <%=JSPFormater.formatNumber(balance, "#,###")%></div>
                                          </td>
                                        </tr>
                                        <%}else{%>
                                        <tr> 
                                          <td width="5%" class="tablecell1" height="20"><%=i+1%></td>
                                          <td width="12%" class="tablecell1" height="20"><%=m.getNoMember()%></td>
                                          <td width="24%" class="tablecell1" height="20"><%=m.getNama()%></td>
                                          <td width="20%" class="tablecell1" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getTotalPinjaman(), "#,###")%></div>
                                          </td>
                                          <td width="19%" class="tablecell1" height="20"> 
                                            <div align="right"><%=JSPFormater.formatNumber(p.getCicilan(), "#,###")%></div>
                                          </td>
                                          <td width="20%" class="tablecell1" height="20"> 
                                            <div align="right"> 
                                              <%
										  double balance = p.getTotalPinjaman() - p.getCicilan();
										  %>
                                              <%=JSPFormater.formatNumber(balance, "#,###")%></div>
                                          </td>
                                        </tr>
                                        <%}}}%>
                                        <tr> 
                                          <td width="5%" height="23">&nbsp;</td>
                                          <td width="12%" height="23">&nbsp;</td>
                                          <td width="24%" bgcolor="#E1E1E1" height="23"><b>T 
                                            O T A L :</b></td>
                                          <td width="20%" bgcolor="#E1E1E1" height="23"> 
                                            <div align="right"><b><%=JSPFormater.formatNumber(totalPinjaman, "#,###")%></b></div>
                                            <% rptKonstan.setTotalPinjamanSamPiutang(totalPinjaman);%>
                                          </td>
                                          <td width="19%" bgcolor="#E1E1E1" height="23"> 
                                            <div align="right"><b><%=JSPFormater.formatNumber(totalAngsuran, "#,###")%></b></div>
                                            <% rptKonstan.setTotalAngsuranSamPiutang(totalAngsuran);%>
                                          </td>
                                          <td width="20%" bgcolor="#E1E1E1" height="23"> 
                                            <div align="right"><b><%=JSPFormater.formatNumber(totalPinjaman-totalAngsuran, "#,###")%></b></div>
                                            <%
											  double tSaldo = totalPinjaman-totalAngsuran; 
											  rptKonstan.setTotalSaldoSamPiutang(totalPinjaman);
											 %>
                                          </td>
                                        </tr>
                                        <tr> 
                                          <td width="5%">&nbsp;</td>
                                          <td width="12%">&nbsp;</td>
                                          <td width="24%">&nbsp;</td>
                                          <td width="20%">&nbsp;</td>
                                          <td width="19%">&nbsp;</td>
                                          <td width="20%">&nbsp;</td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  <%}%>
                                  <tr> 
                                    <td> 
                                      <table width="30%" border="0" cellspacing="1" cellpadding="1">
                                        <tr> 
                                          <%if(type==1){%>
                                          <td width="9%"><a href="javascript:cmdPrintDetailXLS()"><img src="../images/print2.gif" width="53" height="22" border="0"></a></td>
                                          <%}else{%>
                                          <td width="9%"><a href="javascript:cmdPrintSumrayXLS()"><img src="../images/print2.gif" width="53" height="22" border="0"></a></td>
                                          <%}%>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                </table>
                              </td>
                            </tr>
                            <tr> 
                              <td>&nbsp;</td>
                            </tr>
                          </table>
                        </form>
						<%
							session.putValue("KONSTAN", rptKonstan);
							session.putValue("DETAIL", vTempDetail);
							session.putValue("SUMRAY", vTemp);
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
