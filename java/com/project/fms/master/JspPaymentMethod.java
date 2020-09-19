
package com.project.fms.master;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.fms.master.*;

public class JspPaymentMethod extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private PaymentMethod paymentMethod;

	public static final String JSP_NAME_PAYMENTMETHOD		=  "JSP_NAME_PAYMENTMETHOD" ;

	public static final int JSP_PAYMENT_METHOD_ID			=  0 ;
	public static final int JSP_DESCRIPTION			=  1 ;

	public static String[] colNames = {
		"JSP_PAYMENT_METHOD_ID",  "JSP_DESCRIPTION"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_STRING + ENTRY_REQUIRED
	} ;

	public JspPaymentMethod(){
	}
	public JspPaymentMethod(PaymentMethod paymentMethod){
		this.paymentMethod = paymentMethod;
	}

	public JspPaymentMethod(HttpServletRequest request, PaymentMethod paymentMethod){
		super(new JspPaymentMethod(paymentMethod), request);
		this.paymentMethod = paymentMethod;
	}

	public String getFormName() { return JSP_NAME_PAYMENTMETHOD; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public PaymentMethod getEntityObject(){ return paymentMethod; }

	public void requestEntityObject(PaymentMethod paymentMethod) {
		try{
			this.requestParam();
			paymentMethod.setDescription(getString(JSP_DESCRIPTION));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
