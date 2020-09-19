
package com.project.general;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.general.*;

public class JspVendor extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private Vendor vendor;

	public static final String JSP_NAME_VENDOR		=  "vendor";

	public static final int JSP_NAME			=  0 ;
	public static final int JSP_VENDOR_ID			=  1 ;
	public static final int JSP_CODE			=  2 ;
	public static final int JSP_ADDRESS			=  3 ;
	public static final int JSP_CITY			=  4 ;
	public static final int JSP_STATE			=  5 ;
	public static final int JSP_PHONE			=  6 ;
	public static final int JSP_HP			=  7 ;
	public static final int JSP_FAX			=  8 ;
	public static final int JSP_DUE_DATE			=  9 ;
	public static final int JSP_CONTACT			=  10 ;
	public static final int JSP_COUNTRY_NAME			=  11 ;
	public static final int JSP_COUNTRY_ID			=  12 ;
	public static final int JSP_TYPE			=  13 ;
        
        public static final  int JSP_DISCOUNT = 14;
        public static final  int JSP_EMAIL = 15;
        public static final  int JSP_NPWP = 16;
        public static final  int JSP_VENDOR_TYPE = 17;
        public static final  int JSP_PREV_LIABILITY = 18;
        public static final  int JSP_WEB_PAGE = 19;

	public static String[] colNames = {
		"JSP_NAME",  
                "JSP_VENDOR_ID",
		"JSP_CODE",  
                "JSP_ADDRESS",
		"JSP_CITY",  
                "JSP_STATE",
		"JSP_PHONE",  
                "JSP_HP",
		"JSP_FAX",  
                "JSP_DUE_DATE",
		"JSP_CONTACT",  
                "JSP_COUNTRY_NAME",
		"JSP_COUNTRY_ID",  
                "JSP_TYPE",
                
                "JSP_DISCOUNT",
                "JSP_EMAIL",
                "JSP_NPWP",
                "JSP_VENDOR_TYPE",
                "JSP_PREV_LIABILITY",
                "JSP_WEB_PAGE"
	} ;

	public static int[] fieldTypes = {
		TYPE_STRING + ENTRY_REQUIRED,  
                TYPE_LONG,
		TYPE_STRING + ENTRY_REQUIRED,  
                TYPE_STRING + ENTRY_REQUIRED,
		TYPE_STRING,  
                TYPE_STRING,
		TYPE_STRING,  
                TYPE_STRING,
		TYPE_STRING,  
                TYPE_INT,
		TYPE_STRING,  
                TYPE_STRING,
		TYPE_LONG,  
                TYPE_INT,
                
                TYPE_FLOAT,
                TYPE_STRING,
                TYPE_STRING,
                TYPE_STRING,
                TYPE_FLOAT,
                TYPE_STRING
	} ;

	public JspVendor(){
	}
	public JspVendor(Vendor vendor){
		this.vendor = vendor;
	}

	public JspVendor(HttpServletRequest request, Vendor vendor){
		super(new JspVendor(vendor), request);
		this.vendor = vendor;
	}

	public String getFormName() { return JSP_NAME_VENDOR; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public Vendor getEntityObject(){ return vendor; }

	public void requestEntityObject(Vendor vendor) {
		try{
			this.requestParam();
			vendor.setName(getString(JSP_NAME));
			vendor.setCode(getString(JSP_CODE));
			vendor.setAddress(getString(JSP_ADDRESS));
			vendor.setCity(getString(JSP_CITY));
			vendor.setState(getString(JSP_STATE));
			vendor.setPhone(getString(JSP_PHONE));
			vendor.setHp(getString(JSP_HP));
			vendor.setFax(getString(JSP_FAX));
			vendor.setDueDate(getInt(JSP_DUE_DATE));
			vendor.setContact(getString(JSP_CONTACT));
			vendor.setCountryName(getString(JSP_COUNTRY_NAME));
			vendor.setCountryId(getLong(JSP_COUNTRY_ID));
			vendor.setType(getInt(JSP_TYPE));
                     
                        vendor.setDiscount(getDouble(JSP_DISCOUNT));
                        vendor.setEmail(getString(JSP_EMAIL));
                        vendor.setNpwp(getString(JSP_NPWP));
                        vendor.setVendorType(getString(JSP_VENDOR_TYPE));
                        vendor.setPrevLiability(getDouble(JSP_PREV_LIABILITY));
                        vendor.setWebPage(getString(JSP_WEB_PAGE));
                        
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
