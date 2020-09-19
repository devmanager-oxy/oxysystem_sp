
package com.project.fms.ar;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.*;

public class JspARInvoice extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private ARInvoice aRInvoice;

	public static final String JSP_NAME_ARINVOICE		=  "JSP_NAME_ARINVOICE" ;

	public static final int JSP_AR_INVOICE_ID			=  0 ;
	public static final int JSP_CURRENCY_ID			=  1 ;
	public static final int JSP_INVOICE_NUMBER			=  2 ;
	public static final int JSP_TERM_OF_PAYMENT_ID			=  3 ;
	public static final int JSP_DATE			=  4 ;
	public static final int JSP_TRANS_DATE			=  5 ;
	public static final int JSP_JOURNAL_NUMBER			=  6 ;
	public static final int JSP_JOURNAL_PREFIX			=  7 ;
	public static final int JSP_JOURNAL_COUNTER			=  8 ;
	public static final int JSP_DUE_DATE			=  9 ;
	public static final int JSP_VAT			=  10 ;
	public static final int JSP_MEMO			=  11 ;
	public static final int JSP_DISCOUNT_PERCENT			=  12 ;
	public static final int JSP_DISCOUNT			=  13 ;
	public static final int JSP_VAT_PERCENT			=  14 ;
	public static final int JSP_VAT_AMOUNT			=  15 ;
	public static final int JSP_TOTAL			=  16 ;
	public static final int JSP_STATUS			=  17 ;
	public static final int JSP_OPERATOR_ID			=  18 ;
	public static final int JSP_CUSTOMER_ID			=  19 ;
	public static final int JSP_COMPANY_ID			=  20 ;
	public static final int JSP_PROJECT_ID			=  21 ;
	public static final int JSP_PROJECT_TERM_ID			=  22 ;
        public static final int JSP_BANK_ACCOUNT_ID			=  23 ;

        public static final int JSP_LAST_PAYMENT_DATE                   =  24 ;
        public static final int JSP_LAST_PAYMENT_AMOUNT			=  25 ;
        
	public static String[] colNames = {
		"JSP_AR_INVOICE_ID",  "JSP_CURRENCY_ID",
		"JSP_INVOICE_NUMBER",  "JSP_TERM_OF_PAYMENT_ID",
		"JSP_DATE",  "JSP_TRANS_DATE",
		"JSP_JOURNAL_NUMBER",  "JSP_JOURNAL_PREFIX",
		"JSP_JOURNAL_COUNTER",  "JSP_DUE_DATE",
		"JSP_VAT",  "JSP_MEMO",
		"JSP_DISCOUNT_PERCENT",  "JSP_DISCOUNT",
		"JSP_VAT_PERCENT",  "JSP_VAT_AMOUNT",
		"JSP_TOTAL",  "JSP_STATUS",
		"JSP_OPERATOR_ID",  "JSP_CUSTOMER_ID",
		"JSP_COMPANY_ID",  "JSP_PROJECT_ID",
		"JSP_PROJECT_TERM_ID", "JSP_BANK_ACCOUNT_ID",
                
                "JSP_LAST_PAYMENT_DATE", "JSP_LAST_PAYMENT_AMOUNT"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_STRING,  TYPE_LONG,
		TYPE_DATE,  TYPE_DATE,
		TYPE_STRING,  TYPE_STRING,
		TYPE_INT,  TYPE_STRING,
		TYPE_INT,  TYPE_STRING,
		TYPE_FLOAT,  TYPE_FLOAT,
		TYPE_FLOAT,  TYPE_FLOAT,
		TYPE_FLOAT,  TYPE_INT,
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG, TYPE_LONG,
                
                TYPE_STRING, TYPE_FLOAT
	} ;

	public JspARInvoice(){
	}
	public JspARInvoice(ARInvoice aRInvoice){
		this.aRInvoice = aRInvoice;
	}

	public JspARInvoice(HttpServletRequest request, ARInvoice aRInvoice){
		super(new JspARInvoice(aRInvoice), request);
		this.aRInvoice = aRInvoice;
	}

	public String getFormName() { return JSP_NAME_ARINVOICE; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public ARInvoice getEntityObject(){ return aRInvoice; }

	public void requestEntityObject(ARInvoice aRInvoice) {
		try{
			this.requestParam();
			aRInvoice.setCurrencyId(getLong(JSP_CURRENCY_ID));
			//aRInvoice.setInvoiceNumber(getString(JSP_INVOICE_NUMBER));
			aRInvoice.setTermOfPaymentId(getLong(JSP_TERM_OF_PAYMENT_ID));
			//aRInvoice.setDate(getDate(JSP_DATE));
			aRInvoice.setTransDate(getDate(JSP_TRANS_DATE));
			//aRInvoice.setJournalNumber(getString(JSP_JOURNAL_NUMBER));
			//aRInvoice.setJournalPrefix(getString(JSP_JOURNAL_PREFIX));
			//aRInvoice.setJournalCounter(getInt(JSP_JOURNAL_COUNTER));
			aRInvoice.setDueDate(JSPFormater.formatDate(getString(JSP_DUE_DATE), "dd/MM/yyyy"));
			aRInvoice.setVat(getInt(JSP_VAT));
			aRInvoice.setMemo(getString(JSP_MEMO));
			aRInvoice.setDiscountPercent(getDouble(JSP_DISCOUNT_PERCENT));
			aRInvoice.setDiscount(getDouble(JSP_DISCOUNT));
			aRInvoice.setVatPercent(getDouble(JSP_VAT_PERCENT));
			aRInvoice.setVatAmount(getDouble(JSP_VAT_AMOUNT));
			aRInvoice.setTotal(getDouble(JSP_TOTAL));
			aRInvoice.setStatus(getInt(JSP_STATUS));
			aRInvoice.setOperatorId(getLong(JSP_OPERATOR_ID));
			aRInvoice.setCustomerId(getLong(JSP_CUSTOMER_ID));
			//aRInvoice.setCompanyId(getLong(JSP_COMPANY_ID));
			aRInvoice.setProjectId(getLong(JSP_PROJECT_ID));
			aRInvoice.setProjectTermId(getLong(JSP_PROJECT_TERM_ID));
                        aRInvoice.setBankAccountId(getLong(JSP_BANK_ACCOUNT_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
