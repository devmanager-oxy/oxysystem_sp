/* 
 * Form Name  	:  JspBankDepositDetail.java 
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

public class JspBankDepositDetail extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private BankDepositDetail bankDepositDetail;

	public static final String JSP_NAME_BANKDEPOSITDETAIL		=  "JSP_NAME_BANKDEPOSITDETAIL" ;

	public static final int JSP_BANK_DEPOSIT_DETAIL_ID			=  0 ;
	public static final int JSP_BANK_DEPOSIT_ID			=  1 ;
	public static final int JSP_COA_ID			=  2 ;
	public static final int JSP_FOREIGN_CURRENCY_ID			=  3 ;
	public static final int JSP_FOREIGN_AMOUNT			=  4 ;
	public static final int JSP_BOOKED_RATE			=  5 ;
	public static final int JSP_MEMO			=  6 ;
	public static final int JSP_AMOUNT			=  7 ;

	public static String[] colNames = {
		"detailJSP_BANK_DEPOSIT_DETAIL_ID",  "detailJSP_BANK_DEPOSIT_ID",
		"detailJSP_COA_ID",  "detailJSP_FOREIGN_CURRENCY_ID",
		"detailJSP_FOREIGN_AMOUNT",  "detailSP_BOOKED_RATE",
		"detailJSP_MEMO",  "detailJSP_AMOUNT"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_LONG,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_FLOAT,
		TYPE_STRING,  TYPE_FLOAT
	} ;

	public JspBankDepositDetail(){
	}
	public JspBankDepositDetail(BankDepositDetail bankDepositDetail){
		this.bankDepositDetail = bankDepositDetail;
	}

	public JspBankDepositDetail(HttpServletRequest request, BankDepositDetail bankDepositDetail){
		super(new JspBankDepositDetail(bankDepositDetail), request);
		this.bankDepositDetail = bankDepositDetail;
	}

	public String getFormName() { return JSP_NAME_BANKDEPOSITDETAIL; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public BankDepositDetail getEntityObject(){ return bankDepositDetail; }

	public void requestEntityObject(BankDepositDetail bankDepositDetail) {
		try{
			this.requestParam();
			bankDepositDetail.setBankDepositId(getLong(JSP_BANK_DEPOSIT_ID));
			bankDepositDetail.setCoaId(getLong(JSP_COA_ID));
			bankDepositDetail.setForeignCurrencyId(getLong(JSP_FOREIGN_CURRENCY_ID));
			bankDepositDetail.setForeignAmount(getDouble(JSP_FOREIGN_AMOUNT));
			bankDepositDetail.setBookedRate(getDouble(JSP_BOOKED_RATE));
			bankDepositDetail.setMemo(getString(JSP_MEMO));
			bankDepositDetail.setAmount(getDouble(JSP_AMOUNT));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
