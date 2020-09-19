
package com.project.ccs.posmaster;

import java.io.*; 
import java.util.*; 
import javax.servlet.*;
import javax.servlet.http.*; 
import com.project.util.jsp.*;
import com.project.ccs.posmaster.*;

public class JspItemMaster extends JSPHandler implements I_JSPInterface, I_JSPType 
{
	private ItemMaster itemMaster;

	public static final String JSP_NAME_ITEMMASTER		=  "JSP_NAME_ITEMMASTER" ;

	public static final int JSP_ITEM_MASTER_ID			=  0 ;
	public static final int JSP_ITEM_GROUP_ID			=  1 ;
	public static final int JSP_ITEM_CATEGORY_ID			=  2 ;
	public static final int JSP_UOM_PURCHASE_ID			=  3 ;
	public static final int JSP_UOM_RECIPE_ID			=  4 ;
	public static final int JSP_UOM_STOCK_ID			=  5 ;
	public static final int JSP_UOM_SALES_ID			=  6 ;
	public static final int JSP_CODE			=  7 ;
	public static final int JSP_BARCODE			=  8 ;
	public static final int JSP_NAME			=  9 ;
	public static final int JSP_UOM_PURCHASE_STOCK_QTY			=  10 ;
	public static final int JSP_UOM_STOCK_RECIPE_QTY			=  11 ;
	public static final int JSP_UOM_STOCK_SALES_QTY			=  12 ;
	public static final int JSP_FOR_SALE			=  13 ;
	public static final int JSP_FOR_BUY			=  14 ;
	public static final int JSP_IS_ACTIVE			=  15 ;
	public static final int JSP_SELLING_PRICE			=  16 ;
	public static final int JSP_COGS			=  17 ;
	public static final int JSP_RECIPE_ITEM			=  18 ;
        public static final int JSP_NEED_RECIPE			=  19 ;
        
        public static final int JSP_DEFAULT_VENDOR_ID			=  20 ;
        public static final int JSP_MIN_STOCK			=  21 ;

	public static String[] colNames = {
		"JSP_ITEM_MASTER_ID",  "JSP_ITEM_GROUP_ID",
		"JSP_ITEM_CATEGORY_ID",  "JSP_UOM_PURCHASE_ID",
		"JSP_UOM_RECIPE_ID",  "JSP_UOM_STOCK_ID",
		"JSP_UOM_SALES_ID",  "JSP_CODE",
		"JSP_BARCODE",  "JSP_NAME",
		"JSP_UOM_PURCHASE_STOCK_QTY",  "JSP_UOM_STOCK_RECIPE_QTY",
		"JSP_UOM_STOCK_SALES_QTY",  "JSP_FOR_SALE",
		"JSP_FOR_BUY",  "JSP_IS_ACTIVE",
		"JSP_SELLING_PRICE",  "JSP_COGS",
		"JSP_RECIPE_ITEM", "JSP_NEED_RECIPE",
                "JSP_DEFAULT_VENDOR_ID", "JSP_MIN_STOCK"
	} ;

	public static int[] fieldTypes = {
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG + ENTRY_REQUIRED,  TYPE_LONG,
		TYPE_LONG,  TYPE_LONG,
		TYPE_LONG,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_STRING,  TYPE_STRING + ENTRY_REQUIRED,
		TYPE_FLOAT,  TYPE_FLOAT,
		TYPE_FLOAT,  TYPE_INT,
		TYPE_INT,  TYPE_INT,
		TYPE_FLOAT,  TYPE_FLOAT,
		TYPE_INT, TYPE_INT,
                TYPE_LONG, TYPE_FLOAT
	} ;

	public JspItemMaster(){
	}
	public JspItemMaster(ItemMaster itemMaster){
		this.itemMaster = itemMaster;
	}

	public JspItemMaster(HttpServletRequest request, ItemMaster itemMaster){
		super(new JspItemMaster(itemMaster), request);
		this.itemMaster = itemMaster;
	}

	public String getFormName() { return JSP_NAME_ITEMMASTER; } 

	public int[] getFieldTypes() { return fieldTypes; }

	public String[] getFieldNames() { return colNames; } 

	public int getFieldSize() { return colNames.length; } 

	public ItemMaster getEntityObject(){ return itemMaster; }

	public void requestEntityObject(ItemMaster itemMaster) {
		try{
			this.requestParam();
			itemMaster.setItemGroupId(getLong(JSP_ITEM_GROUP_ID));
			itemMaster.setItemCategoryId(getLong(JSP_ITEM_CATEGORY_ID));
			itemMaster.setUomPurchaseId(getLong(JSP_UOM_PURCHASE_ID));
			itemMaster.setUomRecipeId(getLong(JSP_UOM_RECIPE_ID));
			itemMaster.setUomStockId(getLong(JSP_UOM_STOCK_ID));
			itemMaster.setUomSalesId(getLong(JSP_UOM_SALES_ID));
			itemMaster.setCode(getString(JSP_CODE));
			itemMaster.setBarcode(getString(JSP_BARCODE));
			itemMaster.setName(getString(JSP_NAME));
			itemMaster.setUomPurchaseStockQty(getDouble(JSP_UOM_PURCHASE_STOCK_QTY));
			itemMaster.setUomStockRecipeQty(getDouble(JSP_UOM_STOCK_RECIPE_QTY));
			itemMaster.setUomStockSalesQty(getDouble(JSP_UOM_STOCK_SALES_QTY));
			itemMaster.setForSale(getInt(JSP_FOR_SALE));
			itemMaster.setForBuy(getInt(JSP_FOR_BUY));
			itemMaster.setIsActive(getInt(JSP_IS_ACTIVE));
			itemMaster.setSellingPrice(getDouble(JSP_SELLING_PRICE));
			itemMaster.setCogs(getDouble(JSP_COGS));
			itemMaster.setRecipeItem(getInt(JSP_RECIPE_ITEM));
                        itemMaster.setNeedRecipe(getInt(JSP_NEED_RECIPE));
                        
                        itemMaster.setDefaultVendorId(getLong(JSP_DEFAULT_VENDOR_ID));
                        itemMaster.setMinStock(getDouble(JSP_MIN_STOCK));
                        
		}catch(Exception e){
			System.out.println("Error on requestEntityObject : "+e.toString());
		}
	}
}
