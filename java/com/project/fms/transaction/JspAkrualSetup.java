package com.project.fms.transaction;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.*;


public class JspAkrualSetup extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private AkrualSetup akrualSetup;

	public static final String JSP_NAME_AKRUALSETUP		=  "JSP_NAME_AKRUALSETUP" ;

	public static final int JSP_FIELD_AKRUAL_SETUP_ID			=  0 ;
	public static final int JSP_FIELD_NAMA			=  1 ;
	public static final int JSP_FIELD_ANGGARAN			=  2 ;
	public static final int JSP_FIELD_PEMBAGI			=  3 ;
	public static final int JSP_FIELD_CREDIT_COA_ID			=  4 ;
	public static final int JSP_FIELD_DEBET_COA_ID			=  5 ;
	public static final int JSP_FIELD_REG_DATE			=  6 ;
	public static final int JSP_FIELD_LAST_UPDATE			=  7 ;
	public static final int JSP_FIELD_USER_ID			=  8 ;
        public static final int JSP_FIELD_STATUS			=  9 ;

	public static String[] colNames = {
		"JSP_FIELD_AKRUAL_SETUP_ID",  "JSP_FIELD_NAMA",
		"JSP_FIELD_ANGGARAN",  "JSP_FIELD_PEMBAGI",
		"JSP_FIELD_CREDIT_COA_ID",  "JSP_FIELD_DEBET_COA_ID",
		"JSP_FIELD_REG_DATE",  "JSP_FIELD_LAST_UPDATE",
		"JSP_FIELD_USER_ID", "JSP_FIELD_STATUS"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_INT + ENTRY_REQUIRED,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_DATE,  TYPE_DATE,
		TYPE_LONG + ENTRY_REQUIRED, TYPE_INT
	} ;

	public JspAkrualSetup(){
	}
	public JspAkrualSetup(AkrualSetup akrualSetup){
		this.akrualSetup = akrualSetup;
	}

	public JspAkrualSetup(HttpServletRequest request, AkrualSetup akrualSetup){
		super(new JspAkrualSetup(akrualSetup), request);
		this.akrualSetup = akrualSetup;
	}

	public String getFormName() { return JSP_NAME_AKRUALSETUP; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public AkrualSetup getEntityObject(){ return akrualSetup; }

	public void requestEntityObject(AkrualSetup akrualSetup) {
		try{
			this.requestParam();
			akrualSetup.setNama(getString(JSP_FIELD_NAMA));
			akrualSetup.setAnggaran(getDouble(JSP_FIELD_ANGGARAN));
			akrualSetup.setPembagi(getInt(JSP_FIELD_PEMBAGI));
			akrualSetup.setCreditCoaId(getLong(JSP_FIELD_CREDIT_COA_ID));
			akrualSetup.setDebetCoaId(getLong(JSP_FIELD_DEBET_COA_ID));
			akrualSetup.setRegDate(getDate(JSP_FIELD_REG_DATE));
			akrualSetup.setLastUpdate(getDate(JSP_FIELD_LAST_UPDATE));
			akrualSetup.setUserId(getLong(JSP_FIELD_USER_ID));
                        akrualSetup.setStatus(getInt(JSP_FIELD_STATUS));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
