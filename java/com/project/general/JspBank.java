
package com.project.general;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.general.*;

public class JspBank extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private Bank bank;

	public static final String JSP_NAME_BANK		=  "JSP_NAME_BANK" ;

	public static final int JSP_BANK_ID			=  0 ;
	public static final int JSP_NAME			=  1 ;
	public static final int JSP_ADRESS			=  2 ;
	public static final int JSP_DEFAULT_BUNGA			=  3 ;

	public static String[] colNames = {
		"JSP_BANK_ID",  "JSP_NAME",
		"JSP_ADRESS",  "JSP_DEFAULT_BUNGA"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_STRING,  TYPE_FLOAT
	} ;

	public JspBank(){
	}
	public JspBank(Bank bank){
		this.bank = bank;
	}

	public JspBank(HttpServletRequest request, Bank bank){
		super(new JspBank(bank), request);
		this.bank = bank;
	}

	public String getFormName() { return JSP_NAME_BANK; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public Bank getEntityObject(){ return bank; }

	public void requestEntityObject(Bank bank) {
		try{
			this.requestParam();
			bank.setName(getString(JSP_NAME));
			bank.setAdress(getString(JSP_ADRESS));
			bank.setDefaultBunga(getDouble(JSP_DEFAULT_BUNGA));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
