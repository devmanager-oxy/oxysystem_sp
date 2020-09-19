

package com.project.ccs.postransaction.costing; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.lang.I_Language;

public class DbCostingItem extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_POS_COSTING_ITEM = "pos_costing_item";

	public static final  int COL_COSTING_ITEM_ID = 0;
	public static final  int COL_COSTING_ID = 1;
	public static final  int COL_ITEM_MASTER_ID = 2;
	public static final  int COL_QTY = 3;
	public static final  int COL_PRICE = 4;
	public static final  int COL_AMOUNT = 5;
	public static final  int COL_RECIPE = 6;
	public static final  int COL_RECIPE_AMOUNT = 7;

	public static final  String[] colNames = {
		"costing_item_id",
		"costing_id",
		"item_master_id",
		"qty",
		"price",
		"amount",
		"recipe",
		"recipe_amount"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_INT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_FLOAT
	 }; 

	public DbCostingItem(){
	}

	public DbCostingItem(int i) throws CONException { 
		super(new DbCostingItem()); 
	}

	public DbCostingItem(String sOid) throws CONException { 
		super(new DbCostingItem(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbCostingItem(long lOid) throws CONException { 
		super(new DbCostingItem(0)); 
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
		return DB_POS_COSTING_ITEM;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbCostingItem().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		CostingItem costingitem = fetchExc(ent.getOID()); 
		ent = (Entity)costingitem; 
		return costingitem.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((CostingItem) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((CostingItem) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static CostingItem fetchExc(long oid) throws CONException{ 
		try{ 
			CostingItem costingitem = new CostingItem();
			DbCostingItem pstCostingItem = new DbCostingItem(oid); 
			costingitem.setOID(oid);

			costingitem.setCostingId(pstCostingItem.getlong(COL_COSTING_ID));
			costingitem.setItemMasterId(pstCostingItem.getlong(COL_ITEM_MASTER_ID));
			costingitem.setQty(pstCostingItem.getInt(COL_QTY));
			costingitem.setPrice(pstCostingItem.getdouble(COL_PRICE));
			costingitem.setAmount(pstCostingItem.getdouble(COL_AMOUNT));
			costingitem.setRecipe(pstCostingItem.getdouble(COL_RECIPE));
			costingitem.setRecipeAmount(pstCostingItem.getdouble(COL_RECIPE_AMOUNT));

			return costingitem; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCostingItem(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(CostingItem costingitem) throws CONException{ 
		try{ 
			DbCostingItem pstCostingItem = new DbCostingItem(0);

			pstCostingItem.setLong(COL_COSTING_ID, costingitem.getCostingId());
			pstCostingItem.setLong(COL_ITEM_MASTER_ID, costingitem.getItemMasterId());
			pstCostingItem.setInt(COL_QTY, costingitem.getQty());
			pstCostingItem.setDouble(COL_PRICE, costingitem.getPrice());
			pstCostingItem.setDouble(COL_AMOUNT, costingitem.getAmount());
			pstCostingItem.setDouble(COL_RECIPE, costingitem.getRecipe());
			pstCostingItem.setDouble(COL_RECIPE_AMOUNT, costingitem.getRecipeAmount());

			pstCostingItem.insert(); 
			costingitem.setOID(pstCostingItem.getlong(COL_COSTING_ITEM_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCostingItem(0),CONException.UNKNOWN); 
		}
		return costingitem.getOID();
	}

	public static long updateExc(CostingItem costingitem) throws CONException{ 
		try{ 
			if(costingitem.getOID() != 0){ 
				DbCostingItem pstCostingItem = new DbCostingItem(costingitem.getOID());

				pstCostingItem.setLong(COL_COSTING_ID, costingitem.getCostingId());
				pstCostingItem.setLong(COL_ITEM_MASTER_ID, costingitem.getItemMasterId());
				pstCostingItem.setInt(COL_QTY, costingitem.getQty());
				pstCostingItem.setDouble(COL_PRICE, costingitem.getPrice());
				pstCostingItem.setDouble(COL_AMOUNT, costingitem.getAmount());
				pstCostingItem.setDouble(COL_RECIPE, costingitem.getRecipe());
				pstCostingItem.setDouble(COL_RECIPE_AMOUNT, costingitem.getRecipeAmount());

				pstCostingItem.update(); 
				return costingitem.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCostingItem(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbCostingItem pstCostingItem = new DbCostingItem(oid);
			pstCostingItem.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCostingItem(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_POS_COSTING_ITEM; 
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
				CostingItem costingitem = new CostingItem();
				resultToObject(rs, costingitem);
				lists.add(costingitem);
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

	private static void resultToObject(ResultSet rs, CostingItem costingitem){
		try{
			costingitem.setOID(rs.getLong(DbCostingItem.colNames[DbCostingItem.COL_COSTING_ITEM_ID]));
			costingitem.setCostingId(rs.getLong(DbCostingItem.colNames[DbCostingItem.COL_COSTING_ID]));
			costingitem.setItemMasterId(rs.getLong(DbCostingItem.colNames[DbCostingItem.COL_ITEM_MASTER_ID]));
			costingitem.setQty(rs.getInt(DbCostingItem.colNames[DbCostingItem.COL_QTY]));
			costingitem.setPrice(rs.getDouble(DbCostingItem.colNames[DbCostingItem.COL_PRICE]));
			costingitem.setAmount(rs.getDouble(DbCostingItem.colNames[DbCostingItem.COL_AMOUNT]));
			costingitem.setRecipe(rs.getDouble(DbCostingItem.colNames[DbCostingItem.COL_RECIPE]));
			costingitem.setRecipeAmount(rs.getDouble(DbCostingItem.colNames[DbCostingItem.COL_RECIPE_AMOUNT]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long costingItemId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_POS_COSTING_ITEM + " WHERE " + 
						DbCostingItem.colNames[DbCostingItem.COL_COSTING_ITEM_ID] + " = " + costingItemId;

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
			String sql = "SELECT COUNT("+ DbCostingItem.colNames[DbCostingItem.COL_COSTING_ITEM_ID] + ") FROM " + DB_POS_COSTING_ITEM;
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
			  	   CostingItem costingitem = (CostingItem)list.get(ls);
				   if(oid == costingitem.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
}
