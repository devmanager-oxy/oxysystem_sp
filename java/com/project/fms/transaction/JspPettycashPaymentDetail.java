/* 
 * Form Name  	:  JspPettycashPaymentDetail.java 
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

public class JspPettycashPaymentDetail extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private PettycashPaymentDetail  pettycashPaymentDetail;

	public static final String JSP_NAME_PETTYCASHPAYMENTDETAIL		=  "JSP_NAME_PETTYCASHPAYMENTDETAIL" ;

	public static final int JSP_PETTYCASH_PAYMENT_ID			=  0 ;
	public static final int JSP_PETTYCASH_PAYMENT_DETAIL_ID			=  1 ;
	public static final int JSP_COA_ID			=  2 ;
	public static final int JSP_AMOUNT			=  3 ;
	public static final int JSP_MEMO			=  4 ;
        public static final int JSP_DEPARTMENT_ID			=  5 ;

	public static String[] colNames = {
		"detailJSP_PETTYCASH_PAYMENT_ID",  "detailJSP_PETTYCASH_PAYMENT_DETAIL_ID",
		"detailJSP_COA_ID",  "detailJSP_AMOUNT",
		"detailJSP_MEMO", "detailJSP_DEPARTMENT_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_FLOAT + ENTRY_REQUIRED,
		TYPE_STRING, TYPE_LONG
	} ;

	public JspPettycashPaymentDetail(){
	}
	public JspPettycashPaymentDetail(PettycashPaymentDetail pettycashPaymentDetail){
		this.pettycashPaymentDetail = pettycashPaymentDetail;
	}

	public JspPettycashPaymentDetail(HttpServletRequest request, PettycashPaymentDetail pettycashPaymentDetail){
		super(new JspPettycashPaymentDetail(pettycashPaymentDetail), request);
		this.pettycashPaymentDetail = pettycashPaymentDetail;
	}

	public String getFormName() { return JSP_NAME_PETTYCASHPAYMENTDETAIL; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public PettycashPaymentDetail getEntityObject(){ return pettycashPaymentDetail; }

	public void requestEntityObject(PettycashPaymentDetail pettycashPaymentDetail) {
		try{
			this.requestParam();
			pettycashPaymentDetail.setPettycashPaymentId(getLong(JSP_PETTYCASH_PAYMENT_ID));
			pettycashPaymentDetail.setCoaId(getLong(JSP_COA_ID));
			pettycashPaymentDetail.setAmount(getDouble(JSP_AMOUNT));
			pettycashPaymentDetail.setMemo(getString(JSP_MEMO));
                        pettycashPaymentDetail.setDepartmentId(getLong(JSP_DEPARTMENT_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
