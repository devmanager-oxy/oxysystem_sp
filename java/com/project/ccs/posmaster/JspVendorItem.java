
package com.project.ccs.posmaster;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.ccs.posmaster.*;

public class JspVendorItem extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private VendorItem vendorItem;

	public static final String JSP_NAME_VENDORITEM		=  "JSP_NAME_VENDORITEM" ;

	public static final int JSP_VENDOR_ITEM_ID			=  0 ;
	public static final int JSP_ITEM_MASTER_ID			=  1 ;
	public static final int JSP_VENDOR_ID			=  2 ;
	public static final int JSP_LAST_PRICE			=  3 ;
	public static final int JSP_LAST_DISCOUNT			=  4 ;
	public static final int JSP_UPDATE_DATE			=  5 ;

	public static String[] colNames = {
		"JSP_VENDOR_ITEM_ID",  "JSP_ITEM_MASTER_ID",
		"JSP_VENDOR_ID",  "JSP_LAST_PRICE",
		"JSP_LAST_DISCOUNT",  "JSP_UPDATE_DATE"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG + ENTRY_REQUIRED,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_FLOAT,
		TYPE_FLOAT,  TYPE_DATE
	} ;

	public JspVendorItem(){
	}
	public JspVendorItem(VendorItem vendorItem){
		this.vendorItem = vendorItem;
	}

	public JspVendorItem(HttpServletRequest request, VendorItem vendorItem){
		super(new JspVendorItem(vendorItem), request);
		this.vendorItem = vendorItem;
	}

	public String getFormName() { return JSP_NAME_VENDORITEM; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public VendorItem getEntityObject(){ return vendorItem; }

	public void requestEntityObject(VendorItem vendorItem) {
		try{
			this.requestParam();
			vendorItem.setItemMasterId(getLong(JSP_ITEM_MASTER_ID));
			vendorItem.setVendorId(getLong(JSP_VENDOR_ID));
			vendorItem.setLastPrice(getDouble(JSP_LAST_PRICE));
			vendorItem.setLastDiscount(getDouble(JSP_LAST_DISCOUNT));
			vendorItem.setUpdateDate(getDate(JSP_UPDATE_DATE));
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
