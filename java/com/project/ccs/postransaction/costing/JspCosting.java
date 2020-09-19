
package com.project.ccs.postransaction.costing;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;

public class JspCosting extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private Costing costing;

	public static final String JSP_NAME_COSTING		=  "JSP_NAME_COSTING" ;

	public static final int JSP_COSTING_ID			=  0 ;
	public static final int JSP_DATE			=  1 ;
	public static final int JSP_COUNTER			=  2 ;
	public static final int JSP_NUMBER			=  3 ;
	public static final int JSP_NOTE			=  4 ;
	public static final int JSP_APPROVAL_1			=  5 ;
	public static final int JSP_APPROVAL_2			=  6 ;
	public static final int JSP_APPROVAL_3			=  7 ;
	public static final int JSP_STATUS			=  8 ;
	public static final int JSP_LOCATION_ID			=  9 ;
	public static final int JSP_USER_ID			=  10 ;

	public static String[] colNames = {
		"JSP_COSTING_ID",  "JSP_DATE",
		"JSP_COUNTER",  "JSP_NUMBER",
		"JSP_NOTE",  "JSP_APPROVAL_1",
		"JSP_APPROVAL_2",  "JSP_APPROVAL_3",
		"JSP_STATUS",  "JSP_LOCATION_ID",
		"JSP_USER_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_DATE,
		TYPE_INT,  TYPE_STRING,
		TYPE_STRING,  TYPE_INT,
		TYPE_INT,  TYPE_INT,
		TYPE_STRING,  TYPE_LONG,
		TYPE_LONG
	} ;

	public JspCosting(){
	}
	public JspCosting(Costing costing){
		this.costing = costing;
	}

	public JspCosting(HttpServletRequest request, Costing costing){
		super(new JspCosting(costing), request);
		this.costing = costing;
	}

	public String getFormName() { return JSP_NAME_COSTING; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public Costing getEntityObject(){ return costing; }

	public void requestEntityObject(Costing costing) {
		try{
			this.requestParam();
			costing.setDate(getDate(JSP_DATE));
			costing.setCounter(getInt(JSP_COUNTER));
			costing.setNumber(getString(JSP_NUMBER));
			costing.setNote(getString(JSP_NOTE));
			costing.setApproval1(getInt(JSP_APPROVAL_1));
			costing.setApproval2(getInt(JSP_APPROVAL_2));
			costing.setApproval3(getInt(JSP_APPROVAL_3));
			costing.setStatus(getString(JSP_STATUS));
			costing.setLocationId(getLong(JSP_LOCATION_ID));
			costing.setUserId(getLong(JSP_USER_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
