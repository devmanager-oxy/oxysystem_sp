/* 
 * Form Name  	:  JspBanknonpoPayment.java 
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

public class JspBanknonpoPayment extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private BanknonpoPayment banknonpoPayment;

	public static final String JSP_NAME_BANKNONPOPAYMENT		=  "JSP_NAME_BANKNONPOPAYMENT" ;

	public static final int JSP_BANKNONPO_PAYMENT_ID			=  0 ;
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
        public static final int JSP_ACCOUNT_BALANCE			=  12 ;
        public static final int JSP_PAYMENT_METHOD_ID			=  13 ;
        
        public static final int JSP_VENDOR_ID			=  14 ;
        public static final int JSP_INVOICE_NUMBER			=  15 ;
        public static final int JSP_TYPE			=  16 ;
        
	public static String[] colNames = {
		"JSP_BANKNONPO_PAYMENT_ID",  "JSP_COA_ID",
		"JSP_JOURNAL_NUMBER",  "JSP_JOURNAL_COUNTER",
		"JSP_JOURNAL_PREFIX",  "JSP_DATE",
		"JSP_TRANS_DATE",  "JSP_MEMO",
		"JSP_OPERATOR_ID",  "JSP_OPERATOR_NAME",
		"JSP_AMOUNT",  "JSP_REF_NUMBER",
                "JSP_ACCOUNT_BALANCE", "JSP_PAYMENT_METHOD_ID",
                "JSP_VENDOR_ID", "JSP_INVOICE_NUMBER",
                "JSP_TYPE"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_STRING,  TYPE_INT,
		TYPE_STRING,  TYPE_DATE,
		TYPE_STRING + ENTRY_REQUIRED,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_STRING,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_STRING + ENTRY_REQUIRED,
                TYPE_FLOAT, TYPE_LONG, 
                TYPE_LONG, TYPE_STRING,
                TYPE_INT
	} ;

	public JspBanknonpoPayment(){
	}
	public JspBanknonpoPayment(BanknonpoPayment banknonpoPayment){
		this.banknonpoPayment = banknonpoPayment;
	}

	public JspBanknonpoPayment(HttpServletRequest request, BanknonpoPayment banknonpoPayment){
		super(new JspBanknonpoPayment(banknonpoPayment), request);
		this.banknonpoPayment = banknonpoPayment;
	}

	public String getFormName() { return JSP_NAME_BANKNONPOPAYMENT; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public BanknonpoPayment getEntityObject(){ return banknonpoPayment; }

	public void requestEntityObject(BanknonpoPayment banknonpoPayment) {
		try{
			this.requestParam();
			banknonpoPayment.setCoaId(getLong(JSP_COA_ID));
			//banknonpoPayment.setJournalNumber(getString(JSP_JOURNAL_NUMBER));
			//banknonpoPayment.setJournalCounter(getInt(JSP_JOURNAL_COUNTER));
			//banknonpoPayment.setJournalPrefix(getString(JSP_JOURNAL_PREFIX));
			//banknonpoPayment.setDate(getDate(JSP_DATE));
			banknonpoPayment.setTransDate(JSPFormater.formatDate(getString(JSP_TRANS_DATE),"dd/MM/yyyy"));
			banknonpoPayment.setMemo(getString(JSP_MEMO));
			banknonpoPayment.setOperatorId(getLong(JSP_OPERATOR_ID));
			banknonpoPayment.setOperatorName(getString(JSP_OPERATOR_NAME));
			banknonpoPayment.setAmount(getDouble(JSP_AMOUNT));
			banknonpoPayment.setRefNumber(getString(JSP_REF_NUMBER));
                        banknonpoPayment.setAccountBalance(getDouble(JSP_ACCOUNT_BALANCE));
                        banknonpoPayment.setPaymentMethodId(getLong(JSP_PAYMENT_METHOD_ID));
                        banknonpoPayment.setVendorId(getLong(JSP_VENDOR_ID));
                        banknonpoPayment.setInvoiceNumber(getString(JSP_INVOICE_NUMBER));
                        banknonpoPayment.setType(getInt(JSP_TYPE));
                        
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
