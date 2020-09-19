
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

public class DbVendorItem extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_VENDOR_ITEM = "pos_vendor_item";

	public static final  int COL_VENDOR_ITEM_ID = 0;
	public static final  int COL_ITEM_MASTER_ID = 1;
	public static final  int COL_VENDOR_ID = 2;
	public static final  int COL_LAST_PRICE = 3;
	public static final  int COL_LAST_DISCOUNT = 4;
	public static final  int COL_UPDATE_DATE = 5;

	public static final  String[] colNames = {
		"vendor_item_id",
		"item_master_id",
		"vendor_id",
		"last_price",
		"last_discount",
		"update_date"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_DATE
	 }; 

	public DbVendorItem(){
	}

	public DbVendorItem(int i) throws CONException { 
		super(new DbVendorItem()); 
	}

	public DbVendorItem(String sOid) throws CONException { 
		super(new DbVendorItem(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbVendorItem(long lOid) throws CONException { 
		super(new DbVendorItem(0)); 
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
		return DB_VENDOR_ITEM;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbVendorItem().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		VendorItem vendoritem = fetchExc(ent.getOID()); 
		ent = (Entity)vendoritem; 
		return vendoritem.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((VendorItem) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((VendorItem) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static VendorItem fetchExc(long oid) throws CONException{ 
		try{ 
			VendorItem vendoritem = new VendorItem();
			DbVendorItem pstVendorItem = new DbVendorItem(oid); 
			vendoritem.setOID(oid);

			vendoritem.setItemMasterId(pstVendorItem.getlong(COL_ITEM_MASTER_ID));
			vendoritem.setVendorId(pstVendorItem.getlong(COL_VENDOR_ID));
			vendoritem.setLastPrice(pstVendorItem.getdouble(COL_LAST_PRICE));
			vendoritem.setLastDiscount(pstVendorItem.getdouble(COL_LAST_DISCOUNT));
			vendoritem.setUpdateDate(pstVendorItem.getDate(COL_UPDATE_DATE));

			return vendoritem; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendorItem(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(VendorItem vendoritem) throws CONException{ 
		try{ 
			DbVendorItem pstVendorItem = new DbVendorItem(0);

			pstVendorItem.setLong(COL_ITEM_MASTER_ID, vendoritem.getItemMasterId());
			pstVendorItem.setLong(COL_VENDOR_ID, vendoritem.getVendorId());
			pstVendorItem.setDouble(COL_LAST_PRICE, vendoritem.getLastPrice());
			pstVendorItem.setDouble(COL_LAST_DISCOUNT, vendoritem.getLastDiscount());
			pstVendorItem.setDate(COL_UPDATE_DATE, vendoritem.getUpdateDate());

			pstVendorItem.insert(); 
			vendoritem.setOID(pstVendorItem.getlong(COL_VENDOR_ITEM_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendorItem(0),CONException.UNKNOWN); 
		}
		return vendoritem.getOID();
	}

	public static long updateExc(VendorItem vendoritem) throws CONException{ 
		try{ 
			if(vendoritem.getOID() != 0){ 
				DbVendorItem pstVendorItem = new DbVendorItem(vendoritem.getOID());

				pstVendorItem.setLong(COL_ITEM_MASTER_ID, vendoritem.getItemMasterId());
				pstVendorItem.setLong(COL_VENDOR_ID, vendoritem.getVendorId());
				pstVendorItem.setDouble(COL_LAST_PRICE, vendoritem.getLastPrice());
				pstVendorItem.setDouble(COL_LAST_DISCOUNT, vendoritem.getLastDiscount());
				pstVendorItem.setDate(COL_UPDATE_DATE, vendoritem.getUpdateDate());

				pstVendorItem.update(); 
				return vendoritem.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendorItem(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbVendorItem pstVendorItem = new DbVendorItem(oid);
			pstVendorItem.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendorItem(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_VENDOR_ITEM; 
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
				VendorItem vendoritem = new VendorItem();
				resultToObject(rs, vendoritem);
				lists.add(vendoritem);
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

	public static void resultToObject(ResultSet rs, VendorItem vendoritem){
		try{
			vendoritem.setOID(rs.getLong(DbVendorItem.colNames[DbVendorItem.COL_VENDOR_ITEM_ID]));
			vendoritem.setItemMasterId(rs.getLong(DbVendorItem.colNames[DbVendorItem.COL_ITEM_MASTER_ID]));
			vendoritem.setVendorId(rs.getLong(DbVendorItem.colNames[DbVendorItem.COL_VENDOR_ID]));
			vendoritem.setLastPrice(rs.getDouble(DbVendorItem.colNames[DbVendorItem.COL_LAST_PRICE]));
			vendoritem.setLastDiscount(rs.getDouble(DbVendorItem.colNames[DbVendorItem.COL_LAST_DISCOUNT]));
			vendoritem.setUpdateDate(rs.getDate(DbVendorItem.colNames[DbVendorItem.COL_UPDATE_DATE]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long vendorItemId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_VENDOR_ITEM + " WHERE " + 
						DbVendorItem.colNames[DbVendorItem.COL_VENDOR_ITEM_ID] + " = " + vendorItemId;

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
			String sql = "SELECT COUNT("+ DbVendorItem.colNames[DbVendorItem.COL_VENDOR_ITEM_ID] + ") FROM " + DB_VENDOR_ITEM;
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
			  	   VendorItem vendoritem = (VendorItem)list.get(ls);
				   if(oid == vendoritem.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
}
