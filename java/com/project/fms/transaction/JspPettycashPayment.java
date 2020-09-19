/* 
 * Form Name  	:  JspPettycashPayment.java 
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

public class JspPettycashPayment extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private PettycashPayment pettycashPayment;

	public static final String JSP_NAME_PETTYCASHPAYMENT		=  "JSP_NAME_PETTYCASHPAYMENT" ;

	public static final int JSP_PETTYCASH_PAYMENT_ID			=  0 ;
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
	public static final int JSP_REPLACE_STATUS			=  11 ;
        public static final int JSP_ACCOUNT_BALANCE			=  12 ;
        
        public static final int JSP_SHARE_GROUP_ID			=  13 ;
        public static final int JSP_SHARE_CATEGORY_ID			=  14 ;
        public static final int JSP_EXPENSE_CATEGORY_ID			=  15 ;
        
	public static String[] colNames = {
		"JSP_PETTYCASH_PAYMENT_ID",  "JSP_COA_ID",
		"JSP_JOURNAL_NUMBER",  "JSP_JOURNAL_COUNTER",
		"JSP_JOURNAL_PREFIX",  "JSP_DATE",
		"JSP_TRANS_DATE",  "JSP_MEMO",
		"JSP_OPERATOR_ID",  "JSP_OPERATOR_NAME",
		"JSP_AMOUNT",  "JSP_REPLACE_STATUS",
                "JSP_ACCOUNT_BALANCE", "JSP_SHARE_GROUP_ID",
                "JSP_SHARE_CATEGORY_ID", "JSP_EXPENSE_CATEGORY_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_STRING,  TYPE_INT,
		TYPE_STRING,  TYPE_DATE,
		TYPE_STRING,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_STRING,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_INT,
                TYPE_FLOAT, TYPE_LONG,
                TYPE_LONG, TYPE_LONG
	} ;

	public JspPettycashPayment(){
	}
	public JspPettycashPayment(PettycashPayment pettycashPayment){
		this.pettycashPayment = pettycashPayment;
	}

	public JspPettycashPayment(HttpServletRequest request, PettycashPayment pettycashPayment){
		super(new JspPettycashPayment(pettycashPayment), request);
		this.pettycashPayment = pettycashPayment;
	}

	public String getFormName() { return JSP_NAME_PETTYCASHPAYMENT; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public PettycashPayment getEntityObject(){ return pettycashPayment; }

	public void requestEntityObject(PettycashPayment pettycashPayment) {
		try{
			this.requestParam();
			pettycashPayment.setCoaId(getLong(JSP_COA_ID));
			//pettycashPayment.setJournalNumber(getString(JSP_JOURNAL_NUMBER));
			//pettycashPayment.setJournalCounter(getInt(JSP_JOURNAL_COUNTER));
			//pettycashPayment.setJournalPrefix(getString(JSP_JOURNAL_PREFIX));
			//pettycashPayment.setDate(getDate(JSP_DATE));
			pettycashPayment.setTransDate(JSPFormater.formatDate(getString(JSP_TRANS_DATE), "dd/MM/yyyy"));
			pettycashPayment.setMemo(getString(JSP_MEMO));
			pettycashPayment.setOperatorId(getLong(JSP_OPERATOR_ID));
			pettycashPayment.setOperatorName(getString(JSP_OPERATOR_NAME));
			pettycashPayment.setAmount(getDouble(JSP_AMOUNT));
			pettycashPayment.setReplaceStatus(getInt(JSP_REPLACE_STATUS));
                        pettycashPayment.setAccountBalance(getDouble(JSP_ACCOUNT_BALANCE));
                        pettycashPayment.setShareGroupId(getLong(JSP_SHARE_GROUP_ID));
                        pettycashPayment.setShareCategoryId(getLong(JSP_SHARE_CATEGORY_ID));
                        pettycashPayment.setExpenseCategoryId(getLong(JSP_EXPENSE_CATEGORY_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
