
package com.project.general;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.general.*;

public class JspLocation extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private Location location;

	public static final String JSP_NAME_LOCATION		=  "JSP_NAME_LOCATION" ;

	public static final int JSP_LOCATION_ID			=  0 ;
	public static final int JSP_TYPE			=  1 ;
	public static final int JSP_NAME			=  2 ;
	public static final int JSP_ADDRESS_STREET			=  3 ;
	public static final int JSP_ADDRESS_COUNTRY			=  4 ;
	public static final int JSP_ADDRESS_CITY			=  5 ;
	public static final int JSP_TELP			=  6 ;
	public static final int JSP_PIC			=  7 ;
        public static final int JSP_CODE			=  8 ;
        public static final int JSP_DESCRIPTION			=  9 ;

	public static String[] colNames = {
		"JSP_LOCATION_ID",  "JSP_TYPE",
		"JSP_NAME",  "JSP_ADDRESS_STREET",
		"JSP_ADDRESS_COUNTRY",  "JSP_ADDRESS_CITY",
		"JSP_TELP",  "JSP_PIC",
                "JSP_CODE", "JSP_DESCRIPTION"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_STRING,
		TYPE_STRING + ENTRY_REQUIRED,  TYPE_STRING,
		TYPE_STRING,  TYPE_STRING,
		TYPE_STRING,  TYPE_STRING,
                TYPE_STRING + ENTRY_REQUIRED, TYPE_STRING
	} ;

	public JspLocation(){
	}
	public JspLocation(Location location){
		this.location = location;
	}

	public JspLocation(HttpServletRequest request, Location location){
		super(new JspLocation(location), request);
		this.location = location;
	}

	public String getFormName() { return JSP_NAME_LOCATION; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public Location getEntityObject(){ return location; }

	public void requestEntityObject(Location location) {
		try{
			this.requestParam();
			location.setType(getString(JSP_TYPE));
			location.setName(getString(JSP_NAME));
			location.setAddressStreet(getString(JSP_ADDRESS_STREET));
			location.setAddressCountry(getString(JSP_ADDRESS_COUNTRY));
			location.setAddressCity(getString(JSP_ADDRESS_CITY));
			location.setTelp(getString(JSP_TELP));
			location.setPic(getString(JSP_PIC));
                        location.setCode(getString(JSP_CODE));
                        location.setDescription(getString(JSP_DESCRIPTION));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
