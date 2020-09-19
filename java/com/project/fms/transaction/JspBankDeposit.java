/* 
 * Form Name  	:  JspBankDeposit.java 
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

/* project package */
import com.project.util.jsp.*;
import com.project.fms.transaction.*;
import com.project.util.*;


public class JspBankDeposit extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private BankDeposit bankDeposit;

	public static final String JSP_NAME_BANKDEPOSIT		=  "JSP_NAME_BANKDEPOSIT" ;

	public static final int JSP_BANK_DEPOSIT_ID			=  0 ;
	public static final int JSP_MEMO			=  1 ;
	public static final int JSP_DATE			=  2 ;
	public static final int JSP_TRANS_DATE			=  3 ;
	public static final int JSP_OPERATOR_ID			=  4 ;
	public static final int JSP_OPERATOR_NAME			=  5 ;
	public static final int JSP_JOURNAL_NUMBER			=  6 ;
	public static final int JSP_JOURNAL_PREFIX			=  7 ;
	public static final int JSP_JOURNAL_COUNTER			=  8 ;
	public static final int JSP_COA_ID			=  9 ;
	public static final int JSP_AMOUNT			=  10 ;
	public static final int JSP_CURRENCY_ID			=  11 ;

	public static String[] colNames = {
		"JSP_BANK_DEPOSIT_ID",  "JSP_MEMO",
		"JSP_DATE",  "JSP_TRANS_DATE",
		"JSP_OPERATOR_ID",  "JSP_OPERATOR_NAME",
		"JSP_JOURNAL_NUMBER",  "JSP_JOURNAL_PREFIX",
		"JSP_JOURNAL_COUNTER",  "JSP_COA_ID",
		"JSP_AMOUNT",  "JSP_CURRENCY_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_DATE,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_STRING,
		TYPE_STRING,  TYPE_STRING,
		TYPE_INT,  TYPE_LONG,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_LONG
	} ;

	public JspBankDeposit(){
	}
	public JspBankDeposit(BankDeposit bankDeposit){
		this.bankDeposit = bankDeposit;
	}

	public JspBankDeposit(HttpServletRequest request, BankDeposit bankDeposit){
		super(new JspBankDeposit(bankDeposit), request);
		this.bankDeposit = bankDeposit;
	}

	public String getFormName() { return JSP_NAME_BANKDEPOSIT; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public BankDeposit getEntityObject(){ return bankDeposit; }

	public void requestEntityObject(BankDeposit bankDeposit) {
		try{
			this.requestParam();
			bankDeposit.setMemo(getString(JSP_MEMO));
			//bankDeposit.setDate(getDate(JSP_DATE));
			bankDeposit.setTransDate(JSPFormater.formatDate(getString(JSP_TRANS_DATE), "dd/MM/yyyy"));
			bankDeposit.setOperatorId(getLong(JSP_OPERATOR_ID));
			bankDeposit.setOperatorName(getString(JSP_OPERATOR_NAME));
			//bankDeposit.setJournalNumber(getString(JSP_JOURNAL_NUMBER));
			//bankDeposit.setJournalPrefix(getString(JSP_JOURNAL_PREFIX));
			//bankDeposit.setJournalCounter(getInt(JSP_JOURNAL_COUNTER));
			bankDeposit.setCoaId(getLong(JSP_COA_ID));
			bankDeposit.setAmount(getDouble(JSP_AMOUNT));
			bankDeposit.setCurrencyId(getLong(JSP_CURRENCY_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
