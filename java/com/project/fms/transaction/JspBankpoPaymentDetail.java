/* 
 * Form Name  	:  JspBankpoPaymentDetail.java 
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

public class JspBankpoPaymentDetail extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private BankpoPaymentDetail bankpoPaymentDetail;

	public static final String JSP_NAME_BANKPOPAYMENTDETAIL		=  "JSP_NAME_BANKPOPAYMENTDETAIL" ;

	public static final int JSP_BANKPO_PAYMENT_ID			=  0 ;
	public static final int JSP_BANKPO_PAYMENT_DETAIL_ID			=  1 ;
	public static final int JSP_COA_ID			=  2 ;
	public static final int JSP_AMOUNT			=  3 ;
	public static final int JSP_MEMO			=  4 ;
	public static final int JSP_PURCHASE_ID			=  5 ;
	public static final int JSP_CURRENCY_ID			=  6 ;
	public static final int JSP_BOOKED_RATE			=  7 ;
	public static final int JSP_PO_AMOUNT			=  8 ;
	public static final int JSP_PAYMENT_AMOUNT			=  9 ;
        
        public static final int JSP_DEPARTMENT_ID			=  10 ;

	public static String[] colNames = {
		"detailJSP_BANKPO_PAYMENT_ID",  "detailJSP_BANKPO_PAYMENT_DETAIL_ID",
		"detailJSP_COA_ID",  "detailJSP_AMOUNT",
		"detailJSP_MEMO",  "detailJSP_PURCHASE_ID",
		"detailJSP_CURRENCY_ID",  "detailJSP_BOOKED_RATE",
		"detailJSP_PO_AMOUNT",  "detailJSP_PAYMENT_AMOUNT",
                "DJSP_DEPARTMENT_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG,  TYPE_FLOAT,
		TYPE_STRING,  TYPE_LONG,
		TYPE_FLOAT,  TYPE_FLOAT,
		TYPE_FLOAT,  TYPE_FLOAT,
                TYPE_LONG + ENTRY_REQUIRED
	} ;

	public JspBankpoPaymentDetail(){
	}
	public JspBankpoPaymentDetail(BankpoPaymentDetail bankpoPaymentDetail){
		this.bankpoPaymentDetail = bankpoPaymentDetail;
	}

	public JspBankpoPaymentDetail(HttpServletRequest request, BankpoPaymentDetail bankpoPaymentDetail){
		super(new JspBankpoPaymentDetail(bankpoPaymentDetail), request);
		this.bankpoPaymentDetail = bankpoPaymentDetail;
	}

	public String getFormName() { return JSP_NAME_BANKPOPAYMENTDETAIL; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public BankpoPaymentDetail getEntityObject(){ return bankpoPaymentDetail; }

	public void requestEntityObject(BankpoPaymentDetail bankpoPaymentDetail) {
		try{
			this.requestParam();
			bankpoPaymentDetail.setBankpoPaymentId(getLong(JSP_BANKPO_PAYMENT_ID));
			bankpoPaymentDetail.setCoaId(getLong(JSP_COA_ID));
			//bankpoPaymentDetail.setAmount(getDouble(JSP_AMOUNT));
			bankpoPaymentDetail.setMemo(getString(JSP_MEMO));
			//bankpoPaymentDetail.setPurchaseId(getLong(JSP_PURCHASE_ID));
			//bankpoPaymentDetail.setCurrencyId(getDouble(JSP_CURRENCY_ID));
			bankpoPaymentDetail.setBookedRate(getDouble(JSP_BOOKED_RATE));
			//bankpoPaymentDetail.setPoAmount(getDouble(JSP_PO_AMOUNT));
			bankpoPaymentDetail.setPaymentAmount(getDouble(JSP_PAYMENT_AMOUNT));
                        //bankpoPaymentDetail.setDepartmentId(getLong(JSP_DEPARTMENT_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
