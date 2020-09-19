package com.project.fms.transaction;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.*;

public class JspAkrualProses extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private AkrualProses akrualProses;

	public static final String JSP_NAME_AKRUALPROSES		=  "JSP_NAME_AKRUALPROSES" ;

	public static final int JSP_FIELD_AKRUAL_PROSES_ID			=  0 ;
	public static final int JSP_FIELD_REG_DATE			=  1 ;
	public static final int JSP_FIELD_JUMLAH			=  2 ;
	public static final int JSP_FIELD_CREDIT_COA_ID			=  3 ;
	public static final int JSP_FIELD_DEBET_COA_ID			=  4 ;
	public static final int JSP_FIELD_PERIODE_ID			=  5 ;
	public static final int JSP_FIELD_USER_ID			=  6 ;
        public static final int JSP_FIELD_AKRUAL_SETUP_ID			=  7 ;

	public static String[] colNames = {
		"JSP_FIELD_AKRUAL_PROSES_ID",  "JSP_FIELD_REG_DATE",
		"JSP_FIELD_JUMLAH",  "JSP_FIELD_CREDIT_COA_ID",
		"JSP_FIELD_DEBET_COA_ID",  "JSP_FIELD_PERIODE_ID",
		"JSP_FIELD_USER_ID", "JSP_FIELD_AKRUAL_SETUP_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_DATE,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_LONG, TYPE_LONG
	} ;

	public JspAkrualProses(){
	}
	public JspAkrualProses(AkrualProses akrualProses){
		this.akrualProses = akrualProses;
	}

	public JspAkrualProses(HttpServletRequest request, AkrualProses akrualProses){
		super(new JspAkrualProses(akrualProses), request);
		this.akrualProses = akrualProses;
	}

	public String getFormName() { return JSP_NAME_AKRUALPROSES; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public AkrualProses getEntityObject(){ return akrualProses; }

	public void requestEntityObject(AkrualProses akrualProses) {
		try{
			this.requestParam();
			akrualProses.setRegDate(getDate(JSP_FIELD_REG_DATE));
			akrualProses.setJumlah(getDouble(JSP_FIELD_JUMLAH));
			akrualProses.setCreditCoaId(getLong(JSP_FIELD_CREDIT_COA_ID));
			akrualProses.setDebetCoaId(getLong(JSP_FIELD_DEBET_COA_ID));
			akrualProses.setPeriodeId(getLong(JSP_FIELD_PERIODE_ID));
			akrualProses.setUserId(getLong(JSP_FIELD_USER_ID));
                        akrualProses.setAkrualSetupId(getLong(JSP_FIELD_AKRUAL_SETUP_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
