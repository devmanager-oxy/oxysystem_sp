
package com.project.fms.transaction;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.fms.master.*;
import com.project.util.*;

public class JspCashReceiveDetail extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private CashReceiveDetail cashReceiveDetail;

	public static final String JSP_NAME_CASHRECEIVEDETAIL		=  "JSP_NAME_CASHRECEIVEDETAIL" ;

	public static final int JSP_CASH_RECEIVE_ID			=  0 ;
	public static final int JSP_CASH_RECEIVE_DETAIL_ID		=  1 ;
	public static final int JSP_COA_ID                              =  2 ;
	public static final int JSP_FOREIGN_CURRENCY_ID			=  3 ;
	public static final int JSP_FOREIGN_AMOUNT			=  4 ;
	public static final int JSP_BOOKED_RATE                         =  5 ;
	public static final int JSP_AMOUNT                              =  6 ;
	public static final int JSP_MEMO                                =  7 ;
        
        public static final int JSP_CUSTOMER_ID                         =  8;

	public static String[] colNames = {
		"xxJSP_CASH_RECEIVE_ID",  "xxJSP_CASH_RECEIVE_DETAIL_ID",
		"xxJSP_COA_ID",  "xxJSP_FOREIGN_CURRENCY_ID",
		"xxJSP_FOREIGN_AMOUNT",  "xxJSP_BOOKED_RATE",
		"xxJSP_AMOUNT",  "xxJSP_MEMO",
                "xx_JSP_CUSTOMER_ID"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_LONG,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_FLOAT,
		TYPE_FLOAT + ENTRY_REQUIRED,  TYPE_STRING,
                TYPE_LONG
	} ;

	public JspCashReceiveDetail(){
	}
	public JspCashReceiveDetail(CashReceiveDetail cashReceiveDetail){
		this.cashReceiveDetail = cashReceiveDetail;
	}

	public JspCashReceiveDetail(HttpServletRequest request, CashReceiveDetail cashReceiveDetail){
		super(new JspCashReceiveDetail(cashReceiveDetail), request);
		this.cashReceiveDetail = cashReceiveDetail;
	}

	public String getFormName() { return JSP_NAME_CASHRECEIVEDETAIL; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public CashReceiveDetail getEntityObject(){ return cashReceiveDetail; }

	public void requestEntityObject(CashReceiveDetail cashReceiveDetail) {
		try{
			this.requestParam();
			cashReceiveDetail.setCashReceiveId(getLong(JSP_CASH_RECEIVE_ID));
			cashReceiveDetail.setCoaId(getLong(JSP_COA_ID));
			cashReceiveDetail.setForeignCurrencyId(getLong(JSP_FOREIGN_CURRENCY_ID));
			cashReceiveDetail.setForeignAmount(getDouble(JSP_FOREIGN_AMOUNT));
			cashReceiveDetail.setBookedRate(getDouble(JSP_BOOKED_RATE));
			cashReceiveDetail.setAmount(getDouble(JSP_AMOUNT));
			cashReceiveDetail.setMemo(getString(JSP_MEMO));
                        cashReceiveDetail.setCustomerId(getLong(JSP_CUSTOMER_ID));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
