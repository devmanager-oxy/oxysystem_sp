

package com.project.fms.transaction;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.fms.master.*;
import com.project.util.*;

public class JspCashReceive extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private CashReceive cashReceive;

	public static final String JSP_NAME_CASHRECEIVE		=  "JSP_NAME_CASHRECEIVE" ;

	public static final int JSP_CASH_RECEIVE_ID			=  0 ;
	public static final int JSP_COA_ID			=  1 ;
	public static final int JSP_JOURNAL_NUMBER			=  2 ;
	public static final int JSP_JOURNAL_COUNTER			=  3 ;
	public static final int JSP_JOURNAL_PREFIX			=  4 ;
	public static final int JSP_DATE			=  5 ;
	public static final int JSP_TRANS_DATE			=  6 ;
	public static final int JSP_MEMO			=  7 ;
	public static final int JSP_OPERATOR_ID			=  8 ;
	public static final int JSP_OPERATOR_NAME			=  9 ;
	public static final int JSP_AMOUNT			=  10 ;
	public static final int JSP_RECEIVE_FROM_ID			=  11 ;
	public static final int JSP_RECEIVE_FROM_NAME			=  12 ;
        
        public static final int JSP_TYPE			=  13 ;
        public static final int JSP_CUSTOMER_ID			=  14 ;

	public static String[] colNames = {
		"JSP_CASH_RECEIVE_ID",  "JSP_COA_ID",
		"JSP_JOURNAL_NUMBER",  "JSP_JOURNAL_COUNTER",
		"JSP_JOURNAL_PREFIX",  "JSP_DATE",
		"JSP_TRANS_DATE",  "JSP_MEMO_MAIN",
		"JSP_OPERATOR_ID",  "JSP_OPERATOR_NAME",
		"JSP_AMOUNT_MAIN",  "JSP_RECEIVE_FROM_ID",
		"JSP_RECEIVE_FROM_NAME",
                "JSP_TYPE", "JSP_CUSTOMER_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_STRING,  TYPE_INT,
		TYPE_STRING,  TYPE_DATE,
		TYPE_STRING,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_STRING,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_STRING,
                TYPE_INT, TYPE_LONG                
	} ;

	public JspCashReceive(){
	}
	public JspCashReceive(CashReceive cashReceive){
		this.cashReceive = cashReceive;
	}

	public JspCashReceive(HttpServletRequest request, CashReceive cashReceive){
		super(new JspCashReceive(cashReceive), request);
		this.cashReceive = cashReceive;
	}

	public String getFormName() { return JSP_NAME_CASHRECEIVE; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public CashReceive getEntityObject(){ return cashReceive; }

	public void requestEntityObject(CashReceive cashReceive) {
		try{
			this.requestParam();
			cashReceive.setCoaId(getLong(JSP_COA_ID));
			cashReceive.setJournalNumber(getString(JSP_JOURNAL_NUMBER));
			cashReceive.setJournalCounter(getInt(JSP_JOURNAL_COUNTER));
			cashReceive.setJournalPrefix(getString(JSP_JOURNAL_PREFIX));
			//cashReceive.setDate(getDate(JSP_DATE));
			cashReceive.setTransDate(JSPFormater.formatDate(getString(JSP_TRANS_DATE),"dd/MM/yyyy"));
			cashReceive.setMemo(getString(JSP_MEMO));
			cashReceive.setOperatorId(getLong(JSP_OPERATOR_ID));
			cashReceive.setOperatorName(getString(JSP_OPERATOR_NAME));
			cashReceive.setAmount(getDouble(JSP_AMOUNT));
			cashReceive.setReceiveFromId(getLong(JSP_RECEIVE_FROM_ID));
			cashReceive.setReceiveFromName(getString(JSP_RECEIVE_FROM_NAME));
                        
                        cashReceive.setType(getInt(JSP_TYPE));
                        cashReceive.setCustomerId(getLong(JSP_CUSTOMER_ID));
                        
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
