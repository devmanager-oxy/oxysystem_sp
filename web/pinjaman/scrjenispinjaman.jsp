<%@page import="com.project.coorp.pinjaman.DbJenisPinjaman"%>
<%@page import="com.project.coorp.pinjaman.JenisPinjaman"%>
<%@page import="com.project.coorp.pinjaman.JspJenisPinjaman"%>
<%@page import="com.project.coorp.pinjaman.CmdJenisPinjaman"%>
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
<% //int  appObjCode = ObjInfo.composeObjCode(ObjInfo.G1_MASTERDATA, ObjInfo.G2_MD_COMPANY, ObjInfo.OBJ_DIVISION); %>
<%@ include file = "../main/checksp.jsp" %>
<%
/* Check privilege except VIEW, view is already checked on checkuser.jsp as basic access*/
boolean privAdd=true;//userSession.checkPrivilege(AppObjInfo.composeCode(appObjCode, AppObjInfo.COMMAND_ADD));
boolean privUpdate=true;//userSession.checkPrivilege(AppObjInfo.composeCode(appObjCode, AppObjInfo.COMMAND_UPDATE));
boolean privDelete=true;//userSession.checkPrivilege(AppObjInfo.composeCode(appObjCode, AppObjInfo.COMMAND_DELETE));
boolean privNone = true;
%>
<!-- Jsp Block -->
<%!

	public Vector drawList(Vector objectClass ,  long jenisPinjamanId)

	{
		JSPList ctrlist = new JSPList();
		ctrlist.setAreaWidth("50%");
		ctrlist.setListStyle("listgen");
		ctrlist.setTitleStyle("tablehdr");
		ctrlist.setCellStyle("tablecell");
		ctrlist.setCellStyle1("tablecell1");
		ctrlist.setHeaderStyle("tablehdr");

		ctrlist.addHeader("No","10%");
		ctrlist.addHeader("Jenis Pinjaman","90%");

		ctrlist.setLinkRow(0);
		ctrlist.setLinkSufix("");
		Vector lstData = ctrlist.getData();
		Vector lstLinkData = ctrlist.getLinkData();
		ctrlist.setLinkPrefix("javascript:cmdEdit('");
		ctrlist.setLinkSufix("')");
		ctrlist.reset();
		int index = -1;

		Vector temp = new Vector();

		for (int i = 0; i < objectClass.size(); i++) {
			JenisPinjaman jenisPinjaman = (JenisPinjaman)objectClass.get(i);
			//for set value to print report
			
			 Vector rowx = new Vector();

			 if(jenisPinjamanId == jenisPinjaman.getOID())
				 index = i;

			rowx.add((i+1)+"");
			rowx.add(jenisPinjaman.getJenisPinjaman());

			lstData.add(rowx);
			lstLinkData.add(String.valueOf(jenisPinjaman.getOID()));

			temp.add(jenisPinjaman);
		}

		Vector vc = new Vector();
		vc.add(ctrlist.draw(index));
		vc.add(temp);
		return vc;
		//return ctrlist.draw(index);
	}

%>
<%

    if(session.getValue("JENIS_PINJAMAN_REPORT")!=null){
			session.removeValue("JENIS_PINJAMAN_REPORT");
	}


int iJSPCommand = JSPRequestValue.requestCommand(request);
int start = JSPRequestValue.requestInt(request, "start");
int prevJSPCommand = JSPRequestValue.requestInt(request, "prev_command");
long oidJenisPinjaman = JSPRequestValue.requestLong(request, "hidden_jenis_pinjaman_id");

//Request value
String srcJenisPinjaman = JSPRequestValue.requestString(request, "src_jenis_pinjaman");

/*variable declaration*/
int recordToGet = 20;
String msgString = "";
int iErrCode = JSPMessage.NONE;
String whereClause = "";
String orderClause = "";

if(srcJenisPinjaman != null &&srcJenisPinjaman.length()>0){
    whereClause = DbJenisPinjaman.colNames[DbJenisPinjaman.COL_JENIS_PINJAMAN] + " like '%" + srcJenisPinjaman + "%' ";
}

CmdJenisPinjaman ctrlJenisPinjaman = new CmdJenisPinjaman(request);
JSPLine ctrLine = new JSPLine();
Vector listJenisPinjaman = new Vector(1,1);

/*switch statement */
iErrCode = ctrlJenisPinjaman.action(iJSPCommand , oidJenisPinjaman);
/* end switch*/
JspJenisPinjaman jspJenisPinjaman = ctrlJenisPinjaman.getForm();
/*count list All JenisPinjaman*/

int vectSize = DbJenisPinjaman.getCount(whereClause);

JenisPinjaman jenisPinjaman = ctrlJenisPinjaman.getJenisPinjaman();
msgString =  ctrlJenisPinjaman.getMessage();

if((iJSPCommand == JSPCommand.FIRST || iJSPCommand == JSPCommand.PREV )||
  (iJSPCommand == JSPCommand.NEXT || iJSPCommand == JSPCommand.LAST)){
		start = ctrlJenisPinjaman.actionList(iJSPCommand, start, vectSize, recordToGet);
 }
/* end switch list*/

/* get record to display */
listJenisPinjaman = DbJenisPinjaman.list(start,recordToGet, whereClause , orderClause);

/*handle condition if size of record to display = 0 and start > 0 	after delete*/
if (listJenisPinjaman.size() < 1 && start > 0)
{
	 if (vectSize - recordToGet > recordToGet)
			start = start - recordToGet;   //go to JSPCommand.PREV
	 else{
		 start = 0 ;
		 iJSPCommand = JSPCommand.FIRST;
		 prevJSPCommand = JSPCommand.FIRST; //go to JSPCommand.FIRST
	 }
	 listJenisPinjaman = DbJenisPinjaman.list(start,recordToGet, whereClause , orderClause);
	 
}

//out.println("listJenisPinjaman.size() : "+listJenisPinjaman.size());

%>
<html >
<!-- #BeginTemplate "/Templates/indexsp.dwt" -->
<head>
<!-- #BeginEditable "javascript" -->
<title>Simpan Pinjam</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/csssp.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">

<%//if(!anggotaKop){%>
	//window.location="<%=approot%>/nopriv.jsp";
<%//}%>

function cmdPrintAll(oidJenisPinjaman){
	window.open("<%=printroot%>.report.MemberListPdf?oid=<%=oidJenisPinjaman%>&idx=<%=System.currentTimeMillis()%>");
}

function cmdUnShowAll(){
    document.frmmember.command.value="<%=JSPCommand.LIST%>";
    document.frmmember.show_all.value=0;
    document.frmmember.action="scrjenispinjaman.jsp";
    document.frmmember.submit();
}

function cmdShowAll(){
    document.frmmember.command.value="<%=JSPCommand.LIST%>";
    document.frmmember.show_all.value=1;
    document.frmmember.action="scrjenispinjaman.jsp";
    document.frmmember.submit();
}

function cmdAdd(){
	document.frmmember.hidden_jenis_pinjaman_id.value="0";
	document.frmmember.command.value="<%=JSPCommand.ADD%>";
	document.frmmember.prev_command.value="<%=prevJSPCommand%>";
	document.frmmember.action="jenispinjaman.jsp";
	document.frmmember.submit();
}

function cmdSearch(){
	document.frmmember.command.value="<%=JSPCommand.LIST%>";
	document.frmmember.action="scrjenispinjaman.jsp";
	document.frmmember.submit();
}

function cmdAsk(oidJenisPinjaman){
	document.frmmember.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
	document.frmmember.command.value="<%=JSPCommand.ASK%>";
	document.frmmember.prev_command.value="<%=prevJSPCommand%>";
	document.frmmember.action="jenispinjaman.jsp";
	document.frmmember.submit();
}

function cmdConfirmDelete(oidJenisPinjaman){
	document.frmmember.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
	document.frmmember.command.value="<%=JSPCommand.DELETE%>";
	document.frmmember.prev_command.value="<%=prevJSPCommand%>";
	document.frmmember.action="jenispinjaman.jsp";
	document.frmmember.submit();
}
function cmdSave(){
	document.frmmember.command.value="<%=JSPCommand.SAVE%>";
	document.frmmember.prev_command.value="<%=prevJSPCommand%>";
	document.frmmember.action="jenispinjaman.jsp";
	document.frmmember.submit();
	}

function cmdEdit(oidJenisPinjaman){
	document.frmmember.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
	document.frmmember.command.value="<%=JSPCommand.EDIT%>";
	document.frmmember.prev_command.value="<%=prevJSPCommand%>";
	document.frmmember.action="jenispinjaman.jsp";
	document.frmmember.submit();
	}

function cmdCancel(oidJenisPinjaman){
	document.frmmember.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
	document.frmmember.command.value="<%=JSPCommand.EDIT%>";
	document.frmmember.prev_command.value="<%=prevJSPCommand%>";
	document.frmmember.action="jenispinjaman.jsp";
	document.frmmember.submit();
}

function cmdBack(){
	document.frmmember.command.value="<%=JSPCommand.BACK%>";
	document.frmmember.action="jenispinjaman.jsp";
	document.frmmember.submit();
	}

function cmdListFirst(){
	document.frmmember.command.value="<%=JSPCommand.FIRST%>";
	document.frmmember.prev_command.value="<%=JSPCommand.FIRST%>";
	document.frmmember.action="scrjenispinjaman.jsp";
	document.frmmember.submit();
}

function cmdListPrev(){
	document.frmmember.command.value="<%=JSPCommand.PREV%>";
	document.frmmember.prev_command.value="<%=JSPCommand.PREV%>";
	document.frmmember.action="scrjenispinjaman.jsp";
	document.frmmember.submit();
	}

function cmdListNext(){
	document.frmmember.command.value="<%=JSPCommand.NEXT%>";
	document.frmmember.prev_command.value="<%=JSPCommand.NEXT%>";
	document.frmmember.action="scrjenispinjaman.jsp";
	document.frmmember.submit();
}

function cmdListLast(){
	document.frmmember.command.value="<%=JSPCommand.LAST%>";
	document.frmmember.prev_command.value="<%=JSPCommand.LAST%>";
	document.frmmember.action="scrjenispinjaman.jsp";
	document.frmmember.submit();
}

function cmdRefres(oidJenisPinjaman){
	document.frmmember.command.value="<%=JSPCommand.SUBMIT%>";
	document.frmmember.hidden_jenis_pinjaman_id.value=oidJenisPinjaman;
	document.frmmember.action="scrjenispinjaman.jsp";
	document.frmmember.submit();
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
            <%@ include file="../main/hmenusp.jsp" %>
            <!-- #EndEditable --> </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
              <!--DWLayoutTable-->
              <tr>
                <td width="165" height="100%" valign="top" style="background:url(<%=approot%>/imagessp/leftmenu-bg.gif) repeat-y">
                  <!-- #BeginEditable "menu" -->
                  <%@ include file="../main/menusp.jsp" %>
                  <%@ include file="../calendar/calendarframe.jsp"%>
                  <!-- #EndEditable --> </td>
                <td width="100%" valign="top">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td class="title"><!-- #BeginEditable "title" --><span class="level1">Jenis Pinjaman</span>
                        &raquo; <span class="level2">Pencarian Jenis Pinjaman<br>
                        </span><!-- #EndEditable --></td>
                    </tr>
                    <!--tr>
                      <td><img src="<%=approot%>/imagessp/title-sp.gif" width="584" height="1"></td>
                    </tr-->
                    <tr>
                      <td><!-- #BeginEditable "content" -->
                        <form name="frmmember" method ="post" action="">
                          <input type="hidden" name="show_all" value="0">
                          <input type="hidden" name="command" value="<%=iJSPCommand%>">
                          <input type="hidden" name="vectSize" value="<%=vectSize%>">
                          <input type="hidden" name="start" value="<%=start%>">
                          <input type="hidden" name="prev_command" value="<%=prevJSPCommand%>">
                          <input type="hidden" name="hidden_jenis_pinjaman_id" value="<%=oidJenisPinjaman%>">
                          <input type="hidden" name="menu_idx" value="<%=menuIdx%>">
                          <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr align="left" valign="top">
                              <td height="8"  colspan="3" class="container">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr align="left" valign="top">
                                    <td height="8" valign="middle" colspan="3">&nbsp;
                                    </td>
                                  </tr>
                                  <tr align="left" valign="top">
                                    <td height="14" valign="middle" colspan="3">
                                      <table width="100%" border="0" cellspacing="2" cellpadding="0">
                                        <tr>
                                          <td colspan="4" height="5"></td>
                                        </tr>
                                        <tr>
                                          <td width="10%">Jenis Pinjaman</td>
                                          <td width="42%">
                                            <input type="text" name="src_jenis_pinjaman" value="<%=srcJenisPinjaman%>">
                                          </td>
                                          <td width="10%">&nbsp</td>
                                          <td width="38%">&nbsp;</td>
                                        </tr>
                                        <tr>
                                          <td colspan="4" height="5"></td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr align="left" valign="top">
                                    <td height="22" valign="middle" colspan="3">
                                      <table width="100%" border="0" cellspacing="2" cellpadding="3">
                                        <tr>
                                          <td width="3%"><a href="javascript:cmdSearch()"><img src="../images/search.jpg" alt="Cari" width="25" border="0"></a></td>
                                          <td width="10%"><a href="javascript:cmdSearch()">Cari</a></td>
                                          <td width="2%">&nbsp;</td>
                                          <td width="3%"><a href="javascript:cmdAdd()"><img src="../images/ctr_line/BtnNew.jpg" alt="Cari" width="24" height="24" border="0"></a></td>
                                          <td width="58%" nowrap><a href="javascript:cmdAdd()">Entry
                                            Jenis Pinjaman Baru </a></td>
                                          <td width="12%">&nbsp;</td>
                                          <td width="12%">&nbsp;</td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr align="left" valign="top">
                                    <td height="15" valign="middle" colspan="3">&nbsp;
                                    </td>
                                  </tr>
                                  <%
							try{
								if (listJenisPinjaman.size()>0){
							%>
                                  <tr align="left" valign="top">
                                    <td height="22" valign="middle" colspan="3">
                                      <%
							  Vector x = drawList(listJenisPinjaman,oidJenisPinjaman);
							  String listStr = (String)x.get(0);
							  Vector objRpt = (Vector)x.get(1);
						  %>
                                      <%= listStr %>
                                      <%
							session.putValue("JENIS_PINJAMAN_REPORT",objRpt);
						  %>
                                    </td>
                                  </tr>
                                  <%  }
					  else{%>
                                  <tr align="left" valign="top">
                                    <td height="22" valign="middle" colspan="3">&nbsp;&nbsp;<font color="#FF0000">data
                                      kosong ...</font></td>
                                  </tr>
                                  <%}
						  }catch(Exception exc){
                                                      exc.printStackTrace();
						  }%>
                                  <tr align="left" valign="top">
                                    <td height="8" align="left" colspan="3" class="command">
                                      <span class="command">
                                      <%
								   int cmd = 0;
									   if ((iJSPCommand == JSPCommand.FIRST || iJSPCommand == JSPCommand.PREV )||
										(iJSPCommand == JSPCommand.NEXT || iJSPCommand == JSPCommand.LAST))
											cmd =iJSPCommand;
								   else{
									  if(iJSPCommand == JSPCommand.NONE || prevJSPCommand == JSPCommand.NONE)
										cmd = JSPCommand.FIRST;
									  else
									  	cmd =prevJSPCommand;
								   }
							    %>
                                      <% ctrLine.setLocationImg(approot+"/images/ctr_line");
							   	ctrLine.initDefault();
								 %>
                                      <%=ctrLine.drawImageListLimit(cmd,vectSize,start,recordToGet)%> </span> </td>
                                  </tr>
                                  <%if (false){//listJenisPinjaman.size()>0){%>
                                  <tr align="left" valign="top">
                                    <td height="22" valign="middle" colspan="3">
                                      <table width="100%" border="0" cellspacing="0" cellpadding="1">
                                        <tr>
                                          <td colspan="5" height="5"></td>
                                        </tr>
                                        <tr>
                                          <td width="4%"><a href="javascript:cmdPrintAll()"><img src="../images/ctr_line/print.jpg" alt="Cetak" width="30" border="0"></a></td>
                                          <td width="54%"><a href="javascript:cmdPrintAll()">Cetak
                                            / Print</a></td>
                                          <td width="13%">&nbsp;</td>
                                          <td width="13%">&nbsp;</td>
                                          <td width="16%">&nbsp;</td>
                                        </tr>
                                      </table>
                                    </td>
                                  </tr>
                                  <%}%>
                                  <tr>
                                      <td>&nbsp;</td>
                                  </tr>
                                </table>
                              </td>
                            </tr>
                            <tr align="left" valign="top">
                              <td height="8" valign="middle" colspan="3">&nbsp; </td>
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
