
package com.project.fms.master;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.general.*;

public class JspCoaBudget extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private CoaBudget coaBudget;

	public static final String JSP_NAME_COABUDGET		=  "JSP_NAME_COABUDGET" ;

	public static final int JSP_COA_ID			=  0 ;
	public static final int JSP_PERIODE_ID			=  1 ;
	public static final int JSP_COA_BUDGET_ID			=  2 ;
	public static final int JSP_AMOUNT			=  3 ;

	public static String[] colNames = {
		"JSP_COA_ID",  "JSP_PERIODE_ID",
		"JSP_COA_BUDGET_ID",  "JSP_AMOUNT"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG,  TYPE_FLOAT
	} ;

	public JspCoaBudget(){
	}
	public JspCoaBudget(CoaBudget coaBudget){
		this.coaBudget = coaBudget;
	}

	public JspCoaBudget(HttpServletRequest request, CoaBudget coaBudget){
		super(new JspCoaBudget(coaBudget), request);
		this.coaBudget = coaBudget;
	}

	public String getFormName() { return JSP_NAME_COABUDGET; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public CoaBudget getEntityObject(){ return coaBudget; }

	public void requestEntityObject(CoaBudget coaBudget) {
		try{
			this.requestParam();
			coaBudget.setCoaId(getLong(JSP_COA_ID));
			coaBudget.setPeriodeId(getLong(JSP_PERIODE_ID));
			coaBudget.setAmount(getDouble(JSP_AMOUNT));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
