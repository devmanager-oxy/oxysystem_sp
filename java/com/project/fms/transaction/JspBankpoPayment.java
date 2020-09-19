/* 
 * Form Name  	:  JspBankpoPayment.java 
 * Created on 	:  [date] [time] AM/PM 
 * 
 * @author  	:  [authorName] 
 * @version  	:  [version] 
 */

/*******************************************************************
 * Class Description 	: [project description ... ] 
 * Imput Parameters 	: [input parameter ...] 
 * Output 		: [output ...] 
 *******************************************************************/

package com.project.fms.transaction;

/* java package */ 
import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
/* qdep package */ 
import com.project.util.jsp.*;
import com.project.fms.transaction.*;
import com.project.util.*;

public class JspBankpoPayment extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private BankpoPayment bankpoPayment;

	public static final String JSP_NAME_BANKPOPAYMENT		=  "JSP_NAME_BANKPOPAYMENT" ;

	public static final int JSP_BANKPO_PAYMENT_ID			=  0 ;
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
	public static final int JSP_REF_NUMBER			=  11 ;
	public static final int JSP_VENDOR_ID			=  12 ;
	public static final int JSP_CURRENCY_ID			=  13 ;
	public static final int JSP_PAYMENT_METHOD			=  14 ;
        public static final int JSP_PAYMENT_METHOD_ID			=  15 ;
	public static final  int JSP_STATUS = 16;        

	public static String[] colNames = {
		"JSP_BANKPO_PAYMENT_ID",  "JSP_COA_ID",
		"JSP_JOURNAL_NUMBER",  "JSP_JOURNAL_COUNTER",
		"JSP_JOURNAL_PREFIX",  "JSP_DATE",
		"JSP_TRANS_DATE",  "JSP_MEMO",
		"JSP_OPERATOR_ID",  "JSP_OPERATOR_NAME",
		"JSP_AMOUNT",  "JSP_REF_NUMBER",
		"JSP_VENDOR_ID",  "JSP_CURRENCY_ID",
		"JSP_PAYMENT_METHOD", "JSP_PAYMENT_METHOD_ID",
                "JSP_STATUS"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_STRING,  TYPE_INT,
		TYPE_STRING,  TYPE_STRING,
		TYPE_STRING + ENTRY_REQUIRED,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_LONG,  TYPE_STRING,
		TYPE_FLOAT,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_LONG,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_STRING, TYPE_LONG + ENTRY_REQUIRED,
                TYPE_STRING
	} ;

	public JspBankpoPayment(){
	}
	public JspBankpoPayment(BankpoPayment bankpoPayment){
		this.bankpoPayment = bankpoPayment;
	}

	public JspBankpoPayment(HttpServletRequest request, BankpoPayment bankpoPayment){
		super(new JspBankpoPayment(bankpoPayment), request);
		this.bankpoPayment = bankpoPayment;
	}

	public String getFormName() { return JSP_NAME_BANKPOPAYMENT; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public BankpoPayment getEntityObject(){ return bankpoPayment; }

	public void requestEntityObject(BankpoPayment bankpoPayment) {
		try{
			this.requestParam();
			bankpoPayment.setCoaId(getLong(JSP_COA_ID));
			//bankpoPayment.setJournalNumber(getString(JSP_JOURNAL_NUMBER));
			//bankpoPayment.setJournalCounter(getInt(JSP_JOURNAL_COUNTER));
			//bankpoPayment.setJournalPrefix(getString(JSP_JOURNAL_PREFIX));
			//bankpoPayment.setDate(getDate(JSP_DATE));
                        //bankpoPayment.setDate(JSPFormater.formatDate(getString(JSP_DATE),"dd/MM/yyyy"));
			//bankpoPayment.setTransDate(getDate(JSP_TRANS_DATE));
                        bankpoPayment.setTransDate(JSPFormater.formatDate(getString(JSP_TRANS_DATE),"dd/MM/yyyy"));
			bankpoPayment.setMemo(getString(JSP_MEMO));
			bankpoPayment.setOperatorId(getLong(JSP_OPERATOR_ID));
			bankpoPayment.setOperatorName(getString(JSP_OPERATOR_NAME));
			bankpoPayment.setAmount(getDouble(JSP_AMOUNT));
			bankpoPayment.setRefNumber(getString(JSP_REF_NUMBER));
			bankpoPayment.setVendorId(getLong(JSP_VENDOR_ID));
			bankpoPayment.setCurrencyId(getLong(JSP_CURRENCY_ID));
			bankpoPayment.setPaymentMethod(getString(JSP_PAYMENT_METHOD));
                        bankpoPayment.setPaymentMethodId(getLong(JSP_PAYMENT_METHOD_ID));
                        //bankpoPayment.setStatus(getString(JSP_STATUS));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
