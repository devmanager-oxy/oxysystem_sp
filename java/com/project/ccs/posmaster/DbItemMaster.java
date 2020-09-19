
package com.project.ccs.posmaster; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.util.lang.I_Language;
import com.project.ccs.posmaster.*; 

public class DbItemMaster extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_ITEM_MASTER = "pos_item_master";

	public static final  int COL_ITEM_MASTER_ID = 0;
	public static final  int COL_ITEM_GROUP_ID = 1;
	public static final  int COL_ITEM_CATEGORY_ID = 2;
	public static final  int COL_UOM_PURCHASE_ID = 3;
	public static final  int COL_UOM_RECIPE_ID = 4;
	public static final  int COL_UOM_STOCK_ID = 5;
	public static final  int COL_UOM_SALES_ID = 6;
	public static final  int COL_CODE = 7;
	public static final  int COL_BARCODE = 8;
	public static final  int COL_NAME = 9;
	public static final  int COL_UOM_PURCHASE_STOCK_QTY = 10;
	public static final  int COL_UOM_STOCK_RECIPE_QTY = 11;
	public static final  int COL_UOM_STOCK_SALES_QTY = 12;
	public static final  int COL_FOR_SALE = 13;
	public static final  int COL_FOR_BUY = 14;
	public static final  int COL_IS_ACTIVE = 15;
	public static final  int COL_SELLING_PRICE = 16;
	public static final  int COL_COGS = 17;
	public static final  int COL_RECIPE_ITEM = 18;
        public static final  int COL_NEED_RECIPE = 19;
        
        public static final  int COL_DEFAULT_VENDOR_ID = 20;
        public static final  int COL_MIN_STOCK = 21;
        public static final  int COL_TYPE = 22;

        
	public static final  String[] colNames = {
		"item_master_id",
		"item_group_id",
		"item_category_id",
		"uom_purchase_id",
		"uom_recipe_id",
		"uom_stock_id",
		"uom_sales_id",
		"code",
		"barcode",
		"name",
		"uom_purchase_stock_qty",
		"uom_stock_recipe_qty",
		"uom_stock_sales_qty",
		"for_sales",
		"for_buy",
		"is_active",
		"selling_price",
		"cogs",
		"recipe_item",
                "need_recipe",
                
                "default_vendor_id",
                "min_stock",
                "type"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_INT,
		TYPE_INT,
		TYPE_INT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_INT,
                TYPE_INT,
                
                TYPE_LONG,
                TYPE_FLOAT,
                TYPE_INT
	 }; 

	public DbItemMaster(){
	}

	public DbItemMaster(int i) throws CONException { 
		super(new DbItemMaster()); 
	}

	public DbItemMaster(String sOid) throws CONException { 
		super(new DbItemMaster(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbItemMaster(long lOid) throws CONException { 
		super(new DbItemMaster(0)); 
		String sOid = "0"; 
		try { 
			sOid = String.valueOf(lOid); 
		}catch(Exception e) { 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	} 

	public int getFieldSize(){ 
		return colNames.length; 
	}

	public String getTableName(){ 
		return DB_ITEM_MASTER;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbItemMaster().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		ItemMaster itemmaster = fetchExc(ent.getOID()); 
		ent = (Entity)itemmaster; 
		return itemmaster.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((ItemMaster) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((ItemMaster) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static ItemMaster fetchExc(long oid) throws CONException{ 
		try{ 
			ItemMaster itemmaster = new ItemMaster();
			DbItemMaster pstItemMaster = new DbItemMaster(oid); 
			itemmaster.setOID(oid);

			itemmaster.setItemGroupId(pstItemMaster.getlong(COL_ITEM_GROUP_ID));
			itemmaster.setItemCategoryId(pstItemMaster.getlong(COL_ITEM_CATEGORY_ID));
			itemmaster.setUomPurchaseId(pstItemMaster.getlong(COL_UOM_PURCHASE_ID));
			itemmaster.setUomRecipeId(pstItemMaster.getlong(COL_UOM_RECIPE_ID));
			itemmaster.setUomStockId(pstItemMaster.getlong(COL_UOM_STOCK_ID));
			itemmaster.setUomSalesId(pstItemMaster.getlong(COL_UOM_SALES_ID));
			itemmaster.setCode(pstItemMaster.getString(COL_CODE));
			itemmaster.setBarcode(pstItemMaster.getString(COL_BARCODE));
			itemmaster.setName(pstItemMaster.getString(COL_NAME));
			itemmaster.setUomPurchaseStockQty(pstItemMaster.getdouble(COL_UOM_PURCHASE_STOCK_QTY));
			itemmaster.setUomStockRecipeQty(pstItemMaster.getdouble(COL_UOM_STOCK_RECIPE_QTY));
			itemmaster.setUomStockSalesQty(pstItemMaster.getdouble(COL_UOM_STOCK_SALES_QTY));
			itemmaster.setForSale(pstItemMaster.getInt(COL_FOR_SALE));
			itemmaster.setForBuy(pstItemMaster.getInt(COL_FOR_BUY));
			itemmaster.setIsActive(pstItemMaster.getInt(COL_IS_ACTIVE));
			itemmaster.setSellingPrice(pstItemMaster.getdouble(COL_SELLING_PRICE));
			itemmaster.setCogs(pstItemMaster.getdouble(COL_COGS));
			itemmaster.setRecipeItem(pstItemMaster.getInt(COL_RECIPE_ITEM));
                        itemmaster.setNeedRecipe(pstItemMaster.getInt(COL_NEED_RECIPE));
                        
                        itemmaster.setDefaultVendorId(pstItemMaster.getlong(COL_DEFAULT_VENDOR_ID));
                        itemmaster.setMinStock(pstItemMaster.getdouble(COL_MIN_STOCK));
                        itemmaster.setType(pstItemMaster.getInt(COL_TYPE));

			return itemmaster; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbItemMaster(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(ItemMaster itemmaster) throws CONException{ 
		try{ 
			DbItemMaster pstItemMaster = new DbItemMaster(0);

			pstItemMaster.setLong(COL_ITEM_GROUP_ID, itemmaster.getItemGroupId());
			pstItemMaster.setLong(COL_ITEM_CATEGORY_ID, itemmaster.getItemCategoryId());
			pstItemMaster.setLong(COL_UOM_PURCHASE_ID, itemmaster.getUomPurchaseId());
			pstItemMaster.setLong(COL_UOM_RECIPE_ID, itemmaster.getUomRecipeId());
			pstItemMaster.setLong(COL_UOM_STOCK_ID, itemmaster.getUomStockId());
			pstItemMaster.setLong(COL_UOM_SALES_ID, itemmaster.getUomSalesId());
			pstItemMaster.setString(COL_CODE, itemmaster.getCode());
			pstItemMaster.setString(COL_BARCODE, itemmaster.getBarcode());
			pstItemMaster.setString(COL_NAME, itemmaster.getName());
			pstItemMaster.setDouble(COL_UOM_PURCHASE_STOCK_QTY, itemmaster.getUomPurchaseStockQty());
			pstItemMaster.setDouble(COL_UOM_STOCK_RECIPE_QTY, itemmaster.getUomStockRecipeQty());
			pstItemMaster.setDouble(COL_UOM_STOCK_SALES_QTY, itemmaster.getUomStockSalesQty());
			pstItemMaster.setInt(COL_FOR_SALE, itemmaster.getForSale());
			pstItemMaster.setInt(COL_FOR_BUY, itemmaster.getForBuy());
			pstItemMaster.setInt(COL_IS_ACTIVE, itemmaster.getIsActive());
			pstItemMaster.setDouble(COL_SELLING_PRICE, itemmaster.getSellingPrice());
			pstItemMaster.setDouble(COL_COGS, itemmaster.getCogs());
			pstItemMaster.setInt(COL_RECIPE_ITEM, itemmaster.getRecipeItem());
                        pstItemMaster.setInt(COL_NEED_RECIPE, itemmaster.getNeedRecipe());
                        
                        pstItemMaster.setLong(COL_DEFAULT_VENDOR_ID, itemmaster.getDefaultVendorId());
                        pstItemMaster.setDouble(COL_MIN_STOCK, itemmaster.getMinStock());
                        pstItemMaster.setInt(COL_TYPE, itemmaster.getType());

			pstItemMaster.insert(); 
			itemmaster.setOID(pstItemMaster.getlong(COL_ITEM_MASTER_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbItemMaster(0),CONException.UNKNOWN); 
		}
		return itemmaster.getOID();
	}

	public static long updateExc(ItemMaster itemmaster) throws CONException{ 
		try{ 
			if(itemmaster.getOID() != 0){ 
				DbItemMaster pstItemMaster = new DbItemMaster(itemmaster.getOID());

				pstItemMaster.setLong(COL_ITEM_GROUP_ID, itemmaster.getItemGroupId());
				pstItemMaster.setLong(COL_ITEM_CATEGORY_ID, itemmaster.getItemCategoryId());
				pstItemMaster.setLong(COL_UOM_PURCHASE_ID, itemmaster.getUomPurchaseId());
				pstItemMaster.setLong(COL_UOM_RECIPE_ID, itemmaster.getUomRecipeId());
				pstItemMaster.setLong(COL_UOM_STOCK_ID, itemmaster.getUomStockId());
				pstItemMaster.setLong(COL_UOM_SALES_ID, itemmaster.getUomSalesId());
				pstItemMaster.setString(COL_CODE, itemmaster.getCode());
				pstItemMaster.setString(COL_BARCODE, itemmaster.getBarcode());
				pstItemMaster.setString(COL_NAME, itemmaster.getName());
				pstItemMaster.setDouble(COL_UOM_PURCHASE_STOCK_QTY, itemmaster.getUomPurchaseStockQty());
				pstItemMaster.setDouble(COL_UOM_STOCK_RECIPE_QTY, itemmaster.getUomStockRecipeQty());
				pstItemMaster.setDouble(COL_UOM_STOCK_SALES_QTY, itemmaster.getUomStockSalesQty());
				pstItemMaster.setInt(COL_FOR_SALE, itemmaster.getForSale());
				pstItemMaster.setInt(COL_FOR_BUY, itemmaster.getForBuy());
				pstItemMaster.setInt(COL_IS_ACTIVE, itemmaster.getIsActive());
				pstItemMaster.setDouble(COL_SELLING_PRICE, itemmaster.getSellingPrice());
				pstItemMaster.setDouble(COL_COGS, itemmaster.getCogs());
				pstItemMaster.setInt(COL_RECIPE_ITEM, itemmaster.getRecipeItem());
                                pstItemMaster.setInt(COL_NEED_RECIPE, itemmaster.getNeedRecipe());
                                
                                pstItemMaster.setLong(COL_DEFAULT_VENDOR_ID, itemmaster.getDefaultVendorId());
                                pstItemMaster.setDouble(COL_MIN_STOCK, itemmaster.getMinStock());
                                pstItemMaster.setInt(COL_TYPE, itemmaster.getType());

				pstItemMaster.update(); 
				return itemmaster.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbItemMaster(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbItemMaster pstItemMaster = new DbItemMaster(oid);
			pstItemMaster.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbItemMaster(0),CONException.UNKNOWN); 
		}
		return oid;
	}

	public static Vector listAll(){ 
		return list(0, 500, "",""); 
	}

	public static Vector list(int limitStart,int recordToGet, String whereClause, String order){
		Vector lists = new Vector(); 
		CONResultSet dbrs = null;
		try {
			String sql = "SELECT * FROM " + DB_ITEM_MASTER; 
			if(whereClause != null && whereClause.length() > 0)
				sql = sql + " WHERE " + whereClause;
			if(order != null && order.length() > 0)
				sql = sql + " ORDER BY " + order;
			if(limitStart == 0 && recordToGet == 0)
				sql = sql + "";
			else
				sql = sql + " LIMIT " + limitStart + ","+ recordToGet ;
			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();
			while(rs.next()) {
				ItemMaster itemmaster = new ItemMaster();
				resultToObject(rs, itemmaster);
				lists.add(itemmaster);
			}
			rs.close();
			return lists;

		}catch(Exception e) {
			System.out.println(e);
		}finally {
			CONResultSet.close(dbrs);
		}
			return new Vector();
	}

	public static void resultToObject(ResultSet rs, ItemMaster itemmaster){
		try{
			itemmaster.setOID(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]));
			itemmaster.setItemGroupId(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]));
			itemmaster.setItemCategoryId(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_ITEM_CATEGORY_ID]));
			itemmaster.setUomPurchaseId(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_UOM_PURCHASE_ID]));
			itemmaster.setUomRecipeId(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_UOM_RECIPE_ID]));
			itemmaster.setUomStockId(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_UOM_STOCK_ID]));
			itemmaster.setUomSalesId(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_UOM_SALES_ID]));
			itemmaster.setCode(rs.getString(DbItemMaster.colNames[DbItemMaster.COL_CODE]));
			itemmaster.setBarcode(rs.getString(DbItemMaster.colNames[DbItemMaster.COL_BARCODE]));
			itemmaster.setName(rs.getString(DbItemMaster.colNames[DbItemMaster.COL_NAME]));
			itemmaster.setUomPurchaseStockQty(rs.getDouble(DbItemMaster.colNames[DbItemMaster.COL_UOM_PURCHASE_STOCK_QTY]));
			itemmaster.setUomStockRecipeQty(rs.getDouble(DbItemMaster.colNames[DbItemMaster.COL_UOM_STOCK_RECIPE_QTY]));
			itemmaster.setUomStockSalesQty(rs.getDouble(DbItemMaster.colNames[DbItemMaster.COL_UOM_STOCK_SALES_QTY]));
			itemmaster.setForSale(rs.getInt(DbItemMaster.colNames[DbItemMaster.COL_FOR_SALE]));
			itemmaster.setForBuy(rs.getInt(DbItemMaster.colNames[DbItemMaster.COL_FOR_BUY]));
			itemmaster.setIsActive(rs.getInt(DbItemMaster.colNames[DbItemMaster.COL_IS_ACTIVE]));
			itemmaster.setSellingPrice(rs.getDouble(DbItemMaster.colNames[DbItemMaster.COL_SELLING_PRICE]));
			itemmaster.setCogs(rs.getDouble(DbItemMaster.colNames[DbItemMaster.COL_COGS]));
			itemmaster.setRecipeItem(rs.getInt(DbItemMaster.colNames[DbItemMaster.COL_RECIPE_ITEM]));
                        itemmaster.setNeedRecipe(rs.getInt(DbItemMaster.colNames[DbItemMaster.COL_NEED_RECIPE]));
                        
                        itemmaster.setDefaultVendorId(rs.getLong(DbItemMaster.colNames[DbItemMaster.COL_DEFAULT_VENDOR_ID]));
                        itemmaster.setMinStock(rs.getDouble(DbItemMaster.colNames[DbItemMaster.COL_MIN_STOCK]));
                        itemmaster.setType(rs.getInt(DbItemMaster.colNames[DbItemMaster.COL_TYPE]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long itemMasterId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_ITEM_MASTER + " WHERE " + 
						DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID] + " = " + itemMasterId;

			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();

			while(rs.next()) { result = true; }
			rs.close();
		}catch(Exception e){
			System.out.println("err : "+e.toString());
		}finally{
			CONResultSet.close(dbrs);
			return result;
		}
	}

	public static int getCount(String whereClause){
		CONResultSet dbrs = null;
		try {
			String sql = "SELECT COUNT("+ DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID] + ") FROM " + DB_ITEM_MASTER;
			if(whereClause != null && whereClause.length() > 0)
				sql = sql + " WHERE " + whereClause;

			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();

			int count = 0;
			while(rs.next()) { count = rs.getInt(1); }

			rs.close();
			return count;
		}catch(Exception e) {
			return 0;
		}finally {
			CONResultSet.close(dbrs);
		}
	}


	/* This method used to find current data */
	public static int findLimitStart( long oid, int recordToGet, String whereClause){
		String order = "";
		int size = getCount(whereClause);
		int start = 0;
		boolean found =false;
		for(int i=0; (i < size) && !found ; i=i+recordToGet){
			 Vector list =  list(i,recordToGet, whereClause, order); 
			 start = i;
			 if(list.size()>0){
			  for(int ls=0;ls<list.size();ls++){ 
			  	   ItemMaster itemmaster = (ItemMaster)list.get(ls);
				   if(oid == itemmaster.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
}
