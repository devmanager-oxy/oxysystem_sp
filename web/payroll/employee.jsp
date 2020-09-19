<%@ page language = "java" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.project.util.*" %>
<%@ page import = "com.project.util.jsp.*" %>
<%@ page import = "com.project.main.entity.*" %>
<%@ page import = "com.project.main.db.*" %>
<%@ page import = "com.project.payroll.*" %>
<%@ page import = "com.project.general.*" %>
<%@ page import = "com.project.*" %>
<%@ include file = "../main/javainit.jsp" %>
<% int  appObjCode = 1;// AppObjInfo.composeObjCode(AppObjInfo.--, AppObjInfo.--, AppObjInfo.--); %>
<%@ include file = "../main/check.jsp" %>
<%
/* Check privilege except VIEW, view is already checked on checkuser.jsp as basic access*/
boolean privAdd=true;//userSession.checkPrivilege(AppObjInfo.composeCode(appObjCode, AppObjInfo.COMMAND_ADD));
boolean privUpdate=true;//userSession.checkPrivilege(AppObjInfo.composeCode(appObjCode, AppObjInfo.COMMAND_UPDATE));
boolean privDelete=true;//userSession.checkPrivilege(AppObjInfo.composeCode(appObjCode, AppObjInfo.COMMAND_DELETE));
%>
<%
boolean masterPriv = QrUserSession.isHavePriviledge(appSessUser.getUserOID(), AppMenu.M1_MENU_MASTER, AppMenu.M2_MENU_HRD_EMPLOYEE);
boolean masterPrivView = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.M1_MENU_MASTER, AppMenu.M2_MENU_HRD_EMPLOYEE, AppMenu.PRIV_VIEW);
boolean masterPrivUpdate = appSessUser.isPriviledged(appSessUser.getUserOID(), AppMenu.M1_MENU_MASTER, AppMenu.M2_MENU_HRD_EMPLOYEE, AppMenu.PRIV_UPDATE);
%>
<!-- Jsp Block -->
<%!

	public String drawList(Vector objectClass ,  long employeeId)

	{
		JSPList cmdist = new JSPList();
		cmdist.setAreaWidth("100%");
		cmdist.setListStyle("listgen");
		cmdist.setTitleStyle("tablehdr");
		cmdist.setCellStyle("tablecell");
		cmdist.setCellStyle1("tablecell1");
		cmdist.setHeaderStyle("tablehdr");
		cmdist.addHeader("Name","20%");
		cmdist.addHeader("Emp. Num","10%");		
		cmdist.addHeader("Address","25%");
		cmdist.addHeader("ID","15%");
		cmdist.addHeader("Nationality","20%");
		cmdist.addHeader("Emp. Type","10%");
		

		cmdist.setLinkRow(0);
		cmdist.setLinkSufix("");
		Vector lstData = cmdist.getData();
		Vector lstLinkData = cmdist.getLinkData();
		cmdist.setLinkPrefix("javascript:cmdEdit('");
		cmdist.setLinkSufix("')");
		cmdist.reset();
		int index = -1;

		for (int i = 0; i < objectClass.size(); i++) {
			Employee employee = (Employee)objectClass.get(i);
			 Vector rowx = new Vector();
			 if(employeeId == employee.getOID())
				 index = i;

			rowx.add(employee.getName());

			rowx.add(employee.getEmpNum());

			rowx.add(employee.getAddress()+((employee.getCity().length()>0) ? ", "+employee.getCity() : "")+((employee.getState().length()>0) ? ", "+employee.getState() : "")+", "+employee.getCountryName());

			rowx.add(employee.getIdType()+" / "+employee.getIdNumber());

			rowx.add(employee.getNationalityName());

			rowx.add(employee.getEmpType());

			lstData.add(rowx);
			lstLinkData.add(String.valueOf(employee.getOID()));
		}

		return cmdist.draw(index);
	}

%>
<%
int iJSPCommand = JSPRequestValue.requestCommand(request);
int start = JSPRequestValue.requestInt(request, "start");
int prevJSPCommand = JSPRequestValue.requestInt(request, "prev_command");
long oidEmployee = JSPRequestValue.requestLong(request, "hidden_employee_id");

/*variable declaration*/
int recordToGet = 10;
String msgString = "";
int iErrCode = JSPMessage.NONE;
String whereClause = "";
String orderClause = "";

CmdEmployee ctrlEmployee = new CmdEmployee(request);
JSPLine ctrLine = new JSPLine();
Vector listEmployee = new Vector(1,1);

/*switch statement */
iErrCode = ctrlEmployee.action(iJSPCommand , oidEmployee);
/* end switch*/
JspEmployee jspEmployee = ctrlEmployee.getForm();

/*count list All Employee*/
int vectSize = DbEmployee.getCount(whereClause);

Employee employee = ctrlEmployee.getEmployee();
msgString =  ctrlEmployee.getMessage();

if((iJSPCommand == JSPCommand.FIRST || iJSPCommand == JSPCommand.PREV )||
  (iJSPCommand == JSPCommand.NEXT || iJSPCommand == JSPCommand.LAST)){
		start = ctrlEmployee.actionList(iJSPCommand, start, vectSize, recordToGet);
 } 
/* end switch list*/

/* get record to display */
listEmployee = DbEmployee.list(start,recordToGet, whereClause , orderClause);

/*handle condition if size of record to display = 0 and start > 0 	after delete*/
if (listEmployee.size() < 1 && start > 0)
{
	 if (vectSize - recordToGet > recordToGet)
			start = start - recordToGet;   //go to JSPCommand.PREV
	 else{
		 start = 0 ;
		 iJSPCommand = JSPCommand.FIRST;
		 prevJSPCommand = JSPCommand.FIRST; //go to JSPCommand.FIRST
	 }
	 listEmployee = DbEmployee.list(start,recordToGet, whereClause , orderClause);
}
%>
<html ><!-- #BeginTemplate "/Templates/index.dwt" -->
<head>
<!-- #BeginEditable "javascript" --> 
<title><%=systemTitle%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="../css/default.css" rel="stylesheet" type="text/css" />
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">

<%if(!masterPriv || !masterPrivView){%>
	window.location="<%=approot%>/nopriv.jsp";
<%}%>

function cmdAdd(){
	document.frmemployee.hidden_employee_id.value="0";
	document.frmemployee.command.value="<%=JSPCommand.ADD%>";
	document.frmemployee.prev_command.value="<%=prevJSPCommand%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
}

function cmdAsk(oidEmployee){
	document.frmemployee.hidden_employee_id.value=oidEmployee;
	document.frmemployee.command.value="<%=JSPCommand.ASK%>";
	document.frmemployee.prev_command.value="<%=prevJSPCommand%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
}

function cmdConfirmDelete(oidEmployee){
	document.frmemployee.hidden_employee_id.value=oidEmployee;
	document.frmemployee.command.value="<%=JSPCommand.DELETE%>";
	document.frmemployee.prev_command.value="<%=prevJSPCommand%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
}
function cmdSave(){
	document.frmemployee.command.value="<%=JSPCommand.SAVE%>";
	document.frmemployee.prev_command.value="<%=prevJSPCommand%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
	}

function cmdEdit(oidEmployee){
	<%if(masterPrivUpdate){%>
	document.frmemployee.hidden_employee_id.value=oidEmployee;
	document.frmemployee.command.value="<%=JSPCommand.EDIT%>";
	document.frmemployee.prev_command.value="<%=prevJSPCommand%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
	<%}%>
	}

function cmdCancel(oidEmployee){
	document.frmemployee.hidden_employee_id.value=oidEmployee;
	document.frmemployee.command.value="<%=JSPCommand.EDIT%>";
	document.frmemployee.prev_command.value="<%=prevJSPCommand%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
}

function cmdBack(){
	document.frmemployee.command.value="<%=JSPCommand.BACK%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
	}

function cmdListFirst(){
	document.frmemployee.command.value="<%=JSPCommand.FIRST%>";
	document.frmemployee.prev_command.value="<%=JSPCommand.FIRST%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
}

function cmdListPrev(){
	document.frmemployee.command.value="<%=JSPCommand.PREV%>";
	document.frmemployee.prev_command.value="<%=JSPCommand.PREV%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
	}

function cmdListNext(){
	document.frmemployee.command.value="<%=JSPCommand.NEXT%>";
	document.frmemployee.prev_command.value="<%=JSPCommand.NEXT%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
}

function cmdListLast(){
	document.frmemployee.command.value="<%=JSPCommand.LAST%>";
	document.frmemployee.prev_command.value="<%=JSPCommand.LAST%>";
	document.frmemployee.action="employee.jsp";
	document.frmemployee.submit();
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
<body onLoad="MM_preloadImages('<%=approot%>/images/home2.gif','<%=approot%>/images/logout2.gif','../images/new2.gif')">
<table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
  <tr> 
    <td valign="top"> 
      <table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
        <tr> 
          <td height="96"> 
            <!-- #BeginEditable "header" --> 
            <%@ include file="../main/hmenu.jsp"%>
            <!-- #EndEditable -->
          </td>
        </tr>
        <tr> 
          <td valign="top"> 
            <table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%">
              <!--DWLayoutTable-->
              <tr> 
                <td width="165" height="100%" valign="top" style="background:url(<%=approot%>/images/leftmenu-bg.gif) repeat-y"> 
                  <!-- #BeginEditable "menu" --> 
                  <%@ include file="../main/menu.jsp"%>
                  <%@ include file="../calendar/calendarframe.jsp"%>
                  <!-- #EndEditable -->
                </td>
                <td width="100%" valign="top"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td class="title"><!-- #BeginEditable "title" -->
					  <%
					  String navigator = "<font class=\"lvl1\">Payroll</font><font class=\"tit1\">&nbsp;&raquo;&nbsp;<span class=\"lvl2\">Employee as User</span></font>";
					  %>
					  <%@ include file="../main/navigator.jsp"%>
					  <!-- #EndEditable --></td>
                    </tr>
                    <!--tr> 
                      <td><img src="<%=approot%>/images/title-sp.gif" width="584" height="1"></td> 
                    </tr-->
                    <tr> 
                      <td><!-- #BeginEditable "content" --> 
                        <form name="frmemployee" method ="post" action="">
                          <input type="hidden" name="command" value="<%=iJSPCommand%>">
                          <input type="hidden" name="vectSize" value="<%=vectSize%>">
                          <input type="hidden" name="start" value="<%=start%>">
                          <input type="hidden" name="prev_command" value="<%=prevJSPCommand%>">
                          <input type="hidden" name="hidden_employee_id" value="<%=oidEmployee%>">
                          <input type="hidden" name="menu_idx" value="<%=menuIdx%>">
						  
						  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
                              <td class="container"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr align="left" valign="top"> 
                              <td height="8"  colspan="3"> 
                                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr align="left" valign="top"> 
                                          <td height="8" valign="middle" colspan="3"> 
                                          </td>
                                        </tr>
                                        <%
							try{
								if (listEmployee.size()>0){
							%>
                                        <tr align="left" valign="top"> 
                                          <td height="22" valign="middle" colspan="3"> 
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                              <tr> 
                                                <td class="boxed1"><%= drawList(listEmployee,oidEmployee)%></td>
                                              </tr>
                                            </table>
                                          </td>
                                        </tr>
                                        <%  } 
						  }catch(Exception exc){ 
						  }%>
                                        <tr align="left" valign="top"> 
                                          <td height="8" align="left" colspan="3" class="command" valign="top"> 
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
								ctrLine.setFirstImage("<img name=\"Image23x\" border=\"0\" src=\""+approot+"/images/first.gif\" alt=\"First\">");
								   ctrLine.setPrevImage("<img name=\"Image24x\" border=\"0\" src=\""+approot+"/images/prev.gif\" alt=\"Prev\">");
								   ctrLine.setNextImage("<img name=\"Image25x\" border=\"0\" src=\""+approot+"/images/next.gif\" alt=\"Next\">");
								   ctrLine.setLastImage("<img name=\"Image26x\" border=\"0\" src=\""+approot+"/images/last.gif\" alt=\"Last\">");
								   
								   ctrLine.setFirstOnMouseOver("MM_swapImage('Image23x','','"+approot+"/images/first2.gif',1)");
								   ctrLine.setPrevOnMouseOver("MM_swapImage('Image24x','','"+approot+"/images/prev2.gif',1)");
								   ctrLine.setNextOnMouseOver("MM_swapImage('Image25x','','"+approot+"/images/next2.gif',1)");
								   ctrLine.setLastOnMouseOver("MM_swapImage('Image26x','','"+approot+"/images/last2.gif',1)");
								 %>
                                            <%=ctrLine.drawImageListLimit(cmd,vectSize,start,recordToGet)%> </span> </td>
                                        </tr>
                                        <tr align="left" valign="top">
                                          <td height="13" valign="middle" colspan="3">&nbsp;</td>
                                        </tr>
                                        <%if(iJSPCommand!=JSPCommand.ADD && iJSPCommand!=JSPCommand.EDIT && iJSPCommand!=JSPCommand.ASK && iErrCode==0){%>
                                        <tr align="left" valign="top"> 
                                          <td height="22" valign="middle" colspan="3"><%if(masterPrivUpdate){%><a href="javascript:cmdAdd()"  onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('new21','','../images/new2.gif',1)"><img src="../images/new.gif" name="new21" width="71" height="22" border="0"></a><%}%></td>
                                        </tr>
                                        <%}%>
                                      </table>
                              </td>
                            </tr>
                            <tr align="left" valign="top"> 
                              <td height="8" valign="middle" colspan="3"> 
                                <%if((iJSPCommand ==JSPCommand.ADD)||(iJSPCommand==JSPCommand.SAVE)&&(jspEmployee.errorSize()>0)||(iJSPCommand==JSPCommand.EDIT)||(iJSPCommand==JSPCommand.ASK)){%>
                                <table width="100%" border="0" cellspacing="1" cellpadding="0">
                                  <tr> 
                                    <td height="21" valign="middle" width="10%" align="left">&nbsp;</td>
                                    <td height="21" colspan="2" width="90%" class="comment" valign="top" align="left">&nbsp;</td>
                                  </tr>
                                  <tr> 
                                    <td height="21" valign="middle" width="10%" align="left">&nbsp;</td>
                                    <td height="21" colspan="2" width="90%" class="comment" valign="top" align="left">*)= 
                                      required</td>
                                  </tr>
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Name</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_NAME] %>"  value="<%= employee.getName() %>" class="formElemen" size="35">
                                      * <%= jspEmployee.getErrorMsg(JspEmployee.JSP_NAME) %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Emp. 
                                      Number</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_EMP_NUM] %>"  value="<%= employee.getEmpNum() %>" class="formElemen">
                                      * <%= jspEmployee.getErrorMsg(JspEmployee.JSP_EMP_NUM) %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Address</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_ADDRESS] %>"  value="<%= employee.getAddress() %>" class="formElemen" size="45">
                                      * <%= jspEmployee.getErrorMsg(JspEmployee.JSP_ADDRESS) %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;City</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_CITY] %>"  value="<%= employee.getCity() %>" class="formElemen" size="35">
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;State</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_STATE] %>"  value="<%= employee.getState() %>" class="formElemen" size="30">
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Country</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% Vector countryid_value = new Vector(1,1);
						Vector countryid_key = new Vector(1,1);
						
						Vector countries = DbCountry.list(0,0, "", "name");
					 	String sel_countryid = ""+employee.getCountryId();
						if(countries!=null && countries.size()>0){
						   for(int i=0; i<countries.size(); i++){
							   Country c = (Country)countries.get(i);
							   countryid_value.add(""+c.getName());
							   countryid_key.add(""+c.getOID());
						   }
					    }
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_COUNTRY_ID],null, sel_countryid, countryid_key, countryid_value, "", "formElemen") %> * <%= jspEmployee.getErrorMsg(JspEmployee.JSP_COUNTRY_ID) %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Nationality</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% Vector nationalityid_value = new Vector(1,1);
						Vector nationalityid_key = new Vector(1,1);
					 	String sel_nationalityid = ""+employee.getNationalityId();
						
						if(countries!=null && countries.size()>0){
						   for(int i=0; i<countries.size(); i++){
						   	   Country c = (Country)countries.get(i);	
							   nationalityid_value.add(""+c.getNationality());
							   nationalityid_key.add(""+c.getOID());
					   	   }
						}
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_NATIONALITY_ID],null, sel_nationalityid, nationalityid_key, nationalityid_value, "", "formElemen") %> * <%= jspEmployee.getErrorMsg(JspEmployee.JSP_NATIONALITY_ID) %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;ID</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% Vector idtype_value = new Vector(1,1);
						Vector idtype_key = new Vector(1,1);
					 	String sel_idtype = ""+employee.getIdType();
						for(int i=0; i<I_Project.strId.length; i++){	
						   idtype_key.add(I_Project.strId[i]);
						   idtype_value.add(I_Project.strId[i]);
					    }
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_ID_TYPE],null, sel_idtype, idtype_key, idtype_value, "", "formElemen") %> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_ID_NUMBER] %>"  value="<%= employee.getIdNumber() %>" class="formElemen" size="30">
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Phone</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_PHONE] %>"  value="<%= employee.getPhone() %>" class="formElemen" size="20">
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Hp</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_HP] %>"  value="<%= employee.getHp() %>" class="formElemen" size="20">
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Date 
                                      of Birth</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr> 
                                          <td width="12%"> 
                                            <input name="<%=jspEmployee.colNames[JspEmployee.JSP_DOB]%>" value="<%=JSPFormater.formatDate((employee.getDob()==null) ? new Date() : employee.getDob(), "dd/MM/yyyy")%>" size="11" style="text-align:center" readOnly>
                                            <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.frmemployee.<%=jspEmployee.colNames[JspEmployee.JSP_DOB]%>);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt="visit date"></a> 
                                          </td>
                                          <td width="2%"> 
                                            <input type="checkbox" name="<%=jspEmployee.colNames[JspEmployee.JSP_IGNORE_BIRTH]%>" value="1" <%if(employee.getIgnoreBirth()==1){%>checked<%}%>>
                                          </td>
                                          <td width="86%">Ignore</td>
                                        </tr>
                                      </table>
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Marital 
                                      Status</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% Vector maritalstatus_value = new Vector(1,1);
							   Vector maritalstatus_key = new Vector(1,1);
							   String sel_maritalstatus = ""+employee.getMaritalStatus();
							   for(int i=0; i<I_Project.strMarital.length; i++){
							   		maritalstatus_key.add(I_Project.strMarital[i]);
							   		maritalstatus_value.add(I_Project.strMarital[i]);
							   }
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_MARITAL_STATUS],null, sel_maritalstatus, maritalstatus_key, maritalstatus_value, "", "formElemen") %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;</td>
                                    <td height="21" colspan="2" width="90%">&nbsp; 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Location 
                                      of Duty</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% Vector loc_value = new Vector(1,1);
							   Vector loc_key = new Vector(1,1);
							   String sel_loc = ""+employee.getLocationId();
							   Vector vctLoc = DbLocation.list(0,0, "", "");
							   loc_value.add("All");
							   loc_key.add("0");
							   if(vctLoc!=null && vctLoc.size()>0){
								   for(int i=0; i<vctLoc.size(); i++){
								   		Location loc = (Location)vctLoc.get(i);
										loc_value.add(loc.getName());
										loc_key.add(""+loc.getOID());
								   }
							   }
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_LOCATION_ID],null, sel_loc, loc_key, loc_value, "", "formElemen") %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Emp. 
                                      Type</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% Vector emptype_value = new Vector(1,1);
							   Vector emptype_key = new Vector(1,1);
							   String sel_emptype = ""+employee.getEmpType();
							   for(int i=0; i<I_Project.empType.length; i++){
							   		emptype_key.add(I_Project.empType[i]);
							   		emptype_value.add(I_Project.empType[i]);
							   }
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_EMP_TYPE],null, sel_emptype, emptype_key, emptype_value, "", "formElemen") %> 
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Department</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% 
							Vector department_value = new Vector(1,1);
							Vector department_key = new Vector(1,1);
							String sel_department = ""+employee.getDepartmentId();
							
							Vector deps = DbDepartment.list(0,0, "", "name");
							if(deps!=null && deps.size()>0){
								for(int i=0; i<deps.size(); i++){
									Department d = (Department)deps.get(i);
									department_key.add(""+d.getOID());
									department_value.add(""+d.getName());
								}
							}
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_DEPARTMENT_ID],null, sel_department, department_key, department_value, "", "formElemen") %> 
                                      <!--tr> 
                          <td height="21" width="10%">&nbsp;&nbsp;Section</td>
                          <td height="21" colspan="2" width="90%"> 
                            <% 
							Vector section_value = new Vector(1,1);
							Vector section_key = new Vector(1,1);
							
					 		String sel_section = ""+employee.getSectionId();
						    maritalstatus_key.add("---select---");
						    maritalstatus_value.add("");
					   %>
                            <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_SECTION_ID],null, sel_section, section_key, section_value, "", "formElemen") %> 
                        </tr-->
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Commencing 
                                      Date</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input name="<%=jspEmployee.colNames[JspEmployee.JSP_COMMENCING_DATE]%>" value="<%=JSPFormater.formatDate((employee.getCommencingDate()==null) ? new Date() : employee.getCommencingDate(), "dd/MM/yyyy")%>" size="11" style="text-align:center" readOnly>
                                      <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.frmemployee.<%=jspEmployee.colNames[JspEmployee.JSP_COMMENCING_DATE]%>);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt="visit date"></a> 
                                      <%if(employee.getEmpType().equals(I_Project.EMP_TYPE_CONTRACTUAL)){%>
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Contract 
                                      End</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input name="<%=jspEmployee.colNames[JspEmployee.JSP_CONTRACT_END]%>" value="<%=JSPFormater.formatDate((employee.getContractEnd()==null) ? new Date() : employee.getContractEnd(), "dd/MM/yyyy")%>" size="11" style="text-align:center" readOnly>
                                      <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.frmemployee.<%=jspEmployee.colNames[JspEmployee.JSP_CONTRACT_END]%>);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt="visit date"></a> 
                                  </tr>
                                  <%}%>
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Jamsostek</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr> 
                                          <td width="63%"> 
                                            <input type="text" name="<%=jspEmployee.colNames[JspEmployee.JSP_JAMSOSTEK_ID] %>"  value="<%= employee.getJamsostekId() %>" class="formElemen">
                                          </td>
                                          <td width="34%">&nbsp;</td>
                                        </tr>
                                      </table>
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Emp. 
                                      Status</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <% Vector empstatus_value = new Vector(1,1);
							   Vector emptatus_key = new Vector(1,1);
							   String sel_empstatus = ""+employee.getEmpStatus();
							   for(int i=0; i<I_Project.statusArray2.length; i++){
							   		emptatus_key.add(I_Project.statusArray2[i]);
							   		empstatus_value.add(I_Project.statusArray2[i]);
							   }
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_EMP_STATUS],null, sel_empstatus, emptatus_key, empstatus_value, "", "formElemen") %> 
                                  </tr>
                                  <%if(employee.getEmpStatus().equals(I_Project.STATUS_RESIGNED)){%>
                                  <tr> 
                                    <td height="21" width="10%">&nbsp;&nbsp;Resign 
                                      Date</td>
                                    <td height="21" colspan="2" width="90%"> 
                                      <input name="<%=jspEmployee.colNames[JspEmployee.JSP_COMMENCING_DATE]%>2" value="<%=JSPFormater.formatDate((employee.getCommencingDate()==null) ? new Date() : employee.getCommencingDate(), "dd/MM/yyyy")%>" size="11" style="text-align:center" readOnly>
                                      <a href="javascript:void(0)" onClick="if(self.gfPop)gfPop.fPopCalendar(document.frmemployee.<%=jspEmployee.colNames[JspEmployee.JSP_COMMENCING_DATE]%>);return false;" ><img class="PopcalTrigger" align="absmiddle" src="<%=approot%>/calendar/calbtn.gif" height="19" border="0" alt="visit date"></a> 
                                  <tr> 
                                    <td height="8" valign="middle" width="10%" align="left">&nbsp;&nbsp;Resign 
                                      Reason </td>
                                    <td height="8" colspan="2" width="90%" valign="top" align="left"> 
                                      <% Vector reason_value = new Vector(1,1);
							   Vector reason_key = new Vector(1,1);
							   String sel_reason = ""+employee.getResignReason();
							   for(int i=0; i<I_Project.empResignReason.length; i++){
							   		reason_key.add(I_Project.empResignReason[i]);
							   		reason_value.add(I_Project.empResignReason[i]);
							   }
					   %>
                                      <%= JSPCombo.draw(jspEmployee.colNames[JspEmployee.JSP_RESIGN_REASON],null, sel_reason, reason_key, reason_value, "", "formElemen") %></td>
                                  </tr>
                                  <tr> 
                                    <td height="8" valign="middle" width="10%" align="left">&nbsp;&nbsp;Resign 
                                      Note </td>
                                    <td height="8" colspan="2" width="90%" valign="top" align="left"> 
                                      <textarea name="<%=jspEmployee.colNames[JspEmployee.JSP_RESIGN_NOTE] %>" class="formElemen" cols="35" rows="3"><%= employee.getResignNote() %></textarea>
                                    </td>
                                  </tr>
                                  <%}%>
                                  <tr> 
                                    <td height="8" valign="middle" width="10%" align="left">&nbsp;</td>
                                    <td height="8" colspan="2" width="90%" valign="top" align="left">&nbsp; 
                                    </td>
                                  </tr>
                                  <tr > 
                                    <td colspan="3" class="command" valign="top" align="left"> 
                                      <%
									ctrLine.setLocationImg(approot+"/images/ctr_line");
									ctrLine.initDefault();
									ctrLine.setTableWidth("80%");
									String scomDel = "javascript:cmdAsk('"+oidEmployee+"')";
									String sconDelCom = "javascript:cmdConfirmDelete('"+oidEmployee+"')";
									String scancel = "javascript:cmdEdit('"+oidEmployee+"')";
									ctrLine.setBackCaption("Back to List");
									ctrLine.setJSPCommandStyle("buttonlink");
									ctrLine.setDeleteCaption("Delete");
									
										ctrLine.setOnMouseOut("MM_swapImgRestore()");
									ctrLine.setOnMouseOverSave("MM_swapImage('save','','"+approot+"/images/save2.gif',1)");
									ctrLine.setSaveImage("<img src=\""+approot+"/images/save.gif\" name=\"save\" height=\"22\" border=\"0\">");
									
									//ctrLine.setOnMouseOut("MM_swapImgRestore()");
									ctrLine.setOnMouseOverBack("MM_swapImage('back','','"+approot+"/images/cancel2.gif',1)");
									ctrLine.setBackImage("<img src=\""+approot+"/images/cancel.gif\" name=\"back\" height=\"22\" border=\"0\">");
									
									ctrLine.setOnMouseOverDelete("MM_swapImage('delete','','"+approot+"/images/delete2.gif',1)");
									ctrLine.setDeleteImage("<img src=\""+approot+"/images/delete.gif\" name=\"delete\" height=\"22\" border=\"0\">");
									
									ctrLine.setOnMouseOverEdit("MM_swapImage('edit','','"+approot+"/images/cancel2.gif',1)");
									ctrLine.setEditImage("<img src=\""+approot+"/images/cancel.gif\" name=\"edit\" height=\"22\" border=\"0\">");
									
									
									ctrLine.setWidthAllJSPCommand("90");
									ctrLine.setErrorStyle("warning");
									ctrLine.setErrorImage(approot+"/images/error.gif\" width=\"20\" height=\"20");
									ctrLine.setQuestionStyle("warning");
									ctrLine.setQuestionImage(approot+"/images/error.gif\" width=\"20\" height=\"20");
									ctrLine.setInfoStyle("success");
									ctrLine.setSuccessImage(approot+"/images/success.gif\" width=\"20\" height=\"20");

									if (privDelete){
										ctrLine.setConfirmDelJSPCommand(sconDelCom);
										ctrLine.setDeleteJSPCommand(scomDel);
										ctrLine.setEditJSPCommand(scancel);
									}else{ 
										ctrLine.setConfirmDelCaption("");
										ctrLine.setDeleteCaption("");
										ctrLine.setEditCaption("");
									}

									if(privAdd == false  && privUpdate == false){
										ctrLine.setSaveCaption("");
									}

									if (privAdd == false){
										ctrLine.setAddCaption("");
									}
									%>
                                      <%if(iJSPCommand==JSPCommand.ADD || iJSPCommand==JSPCommand.EDIT || iJSPCommand==JSPCommand.ASK || iErrCode!=0){%>
                                      <%= ctrLine.drawImageOnly(iJSPCommand, iErrCode, msgString)%> 
                                      <%}%>
                                    </td>
                                  </tr>
                                  <tr> 
                                    <td width="10%">&nbsp;</td>
                                    <td width="90%">&nbsp;</td>
                                  </tr>
                                  <tr > 
                                    <td colspan="3" valign="top" align="left"> 
                                      <div align="left"></div>
                                    </td>
                                  </tr>
                                </table>
                                <%}%>
                              </td>
                            </tr>
                          </table></td>
  </tr>
</table>
                          
                        </form>
                        <!-- #EndEditable -->
                      </td>
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
          <td height="25"> 
            <!-- #BeginEditable "footer" --> 
            <%@ include file="../main/footer.jsp"%>
            <!-- #EndEditable -->
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
<!-- #EndTemplate --></html>
