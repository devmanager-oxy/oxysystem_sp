/* 
 * Form Name  	:  JspBanknonpoPaymentDetail.java 
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

public class JspBanknonpoPaymentDetail extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private BanknonpoPaymentDetail banknonpoPaymentDetail;

	public static final String JSP_NAME_BANKNONPOPAYMENTDETAIL		=  "JSP_NAME_BANKNONPOPAYMENTDETAIL" ;

	public static final int JSP_BANKNONPO_PAYMENT_ID			=  0 ;
	public static final int JSP_BANKNONPO_PAYMENT_DETAIL_ID			=  1 ;
	public static final int JSP_COA_ID			=  2 ;
	public static final int JSP_AMOUNT			=  3 ;
	public static final int JSP_MEMO			=  4 ;
        
        public static final int JSP_FOREIGN_CURRENCY_ID			=  5 ;
        public static final int JSP_FOREIGN_AMOUNT			=  6 ;
        public static final int JSP_BOOKED_RATE			=  7 ;
        
        public static final int JSP_DEPARTMENT_ID			=  8 ;
        public static final int JSP_COA_ID_TEMP			=  9 ;
        public static final int JSP_COA_ID_TYPE			=  10 ;

	public static String[] colNames = {
		"detailJSP_BANKNONPO_PAYMENT_ID",  "detailJSP_BANKNONPO_PAYMENT_DETAIL_ID",
		"detailJSP_COA_ID",  "detailJSP_AMOUNT",
		"detailJSP_MEMO", "detailJSP_FOREIGN_CURRENCY_ID",
                "detailJSP_FOREIGN_AMOUNT", "detailJSP_BOOKED_RATE",
                "detailJSP_DEPARTMENT_ID", "detailJSP_COA_ID_TEMP", "detailJSP_COA_ID_TYPE"
                
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG,  TYPE_FLOAT + ENTRY_REQUIRED,
		TYPE_STRING, TYPE_LONG + ENTRY_REQUIRED,
                TYPE_FLOAT + ENTRY_REQUIRED, TYPE_FLOAT + ENTRY_REQUIRED,
                TYPE_LONG, TYPE_LONG, TYPE_INT
	} ;

	public JspBanknonpoPaymentDetail(){
	}
	public JspBanknonpoPaymentDetail(BanknonpoPaymentDetail banknonpoPaymentDetail){
		this.banknonpoPaymentDetail = banknonpoPaymentDetail;
	}

	public JspBanknonpoPaymentDetail(HttpServletRequest request, BanknonpoPaymentDetail banknonpoPaymentDetail){
		super(new JspBanknonpoPaymentDetail(banknonpoPaymentDetail), request);
		this.banknonpoPaymentDetail = banknonpoPaymentDetail;
	}

	public String getFormName() { return JSP_NAME_BANKNONPOPAYMENTDETAIL; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public BanknonpoPaymentDetail getEntityObject(){ return banknonpoPaymentDetail; }

	public void requestEntityObject(BanknonpoPaymentDetail banknonpoPaymentDetail) {
		try{
			this.requestParam();
			banknonpoPaymentDetail.setBanknonpoPaymentId(getLong(JSP_BANKNONPO_PAYMENT_ID));
			banknonpoPaymentDetail.setCoaId(getLong(JSP_COA_ID));
                        banknonpoPaymentDetail.setCoaIdTemp(getLong(JSP_COA_ID_TEMP));
                        banknonpoPaymentDetail.setType(getInt(JSP_COA_ID_TYPE));
			banknonpoPaymentDetail.setAmount(getDouble(JSP_AMOUNT));
			banknonpoPaymentDetail.setMemo(getString(JSP_MEMO));
                        
                        banknonpoPaymentDetail.setForeignCurrencyId(getLong(JSP_FOREIGN_CURRENCY_ID));
                        banknonpoPaymentDetail.setForeignAmount(getDouble(JSP_FOREIGN_AMOUNT));
                        banknonpoPaymentDetail.setBookedRate(getDouble(JSP_BOOKED_RATE));
                        
                        banknonpoPaymentDetail.setDepartmentId(getLong(JSP_DEPARTMENT_ID));
                        
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
