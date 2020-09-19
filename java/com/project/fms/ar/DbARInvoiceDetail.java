
package com.project.fms.ar; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*; 
import com.project.fms.ar.*; 
import com.project.*;
import com.project.util.*;
import com.project.util.lang.I_Language;

public class DbARInvoiceDetail extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String TBL_AR_INVOICE_DETAIL = "ar_invoice_detail";

	public static final  int COL_AR_INVOICE_ID = 0;
	public static final  int COL_AR_INVOICE_DETAIL_ID = 1;
	public static final  int COL_ITEM_NAME = 2;
	public static final  int COL_COA_ID = 3;
	public static final  int COL_QTY = 4;
	public static final  int COL_PRICE = 5;
	public static final  int COL_DISCOUNT = 6;
	public static final  int COL_TOTAL_AMOUNT = 7;
	public static final  int COL_DEPARTMENT_ID = 8;
	public static final  int COL_COMPANY_ID = 9;

	public static final  String[] colNames = {
		"ar_invoice_id",
		"ar_invoice_detail_id",
		"item_name",
		"coa_id",
		"qty",
		"price",
		"discount",
		"total_amount",
		"department_id",
		"company_id"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG,
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_INT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_LONG,
		TYPE_LONG
	 }; 

	public DbARInvoiceDetail(){
	}

	public DbARInvoiceDetail(int i) throws CONException { 
		super(new DbARInvoiceDetail()); 
	}

	public DbARInvoiceDetail(String sOid) throws CONException { 
		super(new DbARInvoiceDetail(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbARInvoiceDetail(long lOid) throws CONException { 
		super(new DbARInvoiceDetail(0)); 
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
		return TBL_AR_INVOICE_DETAIL;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbARInvoiceDetail().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		ARInvoiceDetail arinvoicedetail = fetchExc(ent.getOID()); 
		ent = (Entity)arinvoicedetail; 
		return arinvoicedetail.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((ARInvoiceDetail) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((ARInvoiceDetail) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static ARInvoiceDetail fetchExc(long oid) throws CONException{ 
		try{ 
			ARInvoiceDetail arinvoicedetail = new ARInvoiceDetail();
			DbARInvoiceDetail pstARInvoiceDetail = new DbARInvoiceDetail(oid); 
			arinvoicedetail.setOID(oid);

			arinvoicedetail.setArInvoiceId(pstARInvoiceDetail.getlong(COL_AR_INVOICE_ID));
			arinvoicedetail.setItemName(pstARInvoiceDetail.getString(COL_ITEM_NAME));
			arinvoicedetail.setCoaId(pstARInvoiceDetail.getlong(COL_COA_ID));
			arinvoicedetail.setQty(pstARInvoiceDetail.getInt(COL_QTY));
			arinvoicedetail.setPrice(pstARInvoiceDetail.getdouble(COL_PRICE));
			arinvoicedetail.setDiscount(pstARInvoiceDetail.getdouble(COL_DISCOUNT));
			arinvoicedetail.setTotalAmount(pstARInvoiceDetail.getdouble(COL_TOTAL_AMOUNT));
			arinvoicedetail.setDepartmentId(pstARInvoiceDetail.getlong(COL_DEPARTMENT_ID));
			arinvoicedetail.setCompanyId(pstARInvoiceDetail.getlong(COL_COMPANY_ID));

			return arinvoicedetail; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbARInvoiceDetail(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(ARInvoiceDetail arinvoicedetail) throws CONException{ 
		try{ 
			DbARInvoiceDetail pstARInvoiceDetail = new DbARInvoiceDetail(0);

			pstARInvoiceDetail.setLong(COL_AR_INVOICE_ID, arinvoicedetail.getArInvoiceId());
			pstARInvoiceDetail.setString(COL_ITEM_NAME, arinvoicedetail.getItemName());
			pstARInvoiceDetail.setLong(COL_COA_ID, arinvoicedetail.getCoaId());
			pstARInvoiceDetail.setInt(COL_QTY, arinvoicedetail.getQty());
			pstARInvoiceDetail.setDouble(COL_PRICE, arinvoicedetail.getPrice());
			pstARInvoiceDetail.setDouble(COL_DISCOUNT, arinvoicedetail.getDiscount());
			pstARInvoiceDetail.setDouble(COL_TOTAL_AMOUNT, arinvoicedetail.getTotalAmount());
			pstARInvoiceDetail.setLong(COL_DEPARTMENT_ID, arinvoicedetail.getDepartmentId());
			pstARInvoiceDetail.setLong(COL_COMPANY_ID, arinvoicedetail.getCompanyId());

			pstARInvoiceDetail.insert(); 
			arinvoicedetail.setOID(pstARInvoiceDetail.getlong(COL_AR_INVOICE_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbARInvoiceDetail(0),CONException.UNKNOWN); 
		}
		return arinvoicedetail.getOID();
	}

	public static long updateExc(ARInvoiceDetail arinvoicedetail) throws CONException{ 
		try{ 
			if(arinvoicedetail.getOID() != 0){ 
				DbARInvoiceDetail pstARInvoiceDetail = new DbARInvoiceDetail(arinvoicedetail.getOID());

				pstARInvoiceDetail.setLong(COL_AR_INVOICE_ID, arinvoicedetail.getArInvoiceId());
				pstARInvoiceDetail.setString(COL_ITEM_NAME, arinvoicedetail.getItemName());
				pstARInvoiceDetail.setLong(COL_COA_ID, arinvoicedetail.getCoaId());
				pstARInvoiceDetail.setInt(COL_QTY, arinvoicedetail.getQty());
				pstARInvoiceDetail.setDouble(COL_PRICE, arinvoicedetail.getPrice());
				pstARInvoiceDetail.setDouble(COL_DISCOUNT, arinvoicedetail.getDiscount());
				pstARInvoiceDetail.setDouble(COL_TOTAL_AMOUNT, arinvoicedetail.getTotalAmount());
				pstARInvoiceDetail.setLong(COL_DEPARTMENT_ID, arinvoicedetail.getDepartmentId());
				pstARInvoiceDetail.setLong(COL_COMPANY_ID, arinvoicedetail.getCompanyId());

				pstARInvoiceDetail.update(); 
				return arinvoicedetail.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbARInvoiceDetail(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbARInvoiceDetail pstARInvoiceDetail = new DbARInvoiceDetail(oid);
			pstARInvoiceDetail.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbARInvoiceDetail(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + TBL_AR_INVOICE_DETAIL; 
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
				ARInvoiceDetail arinvoicedetail = new ARInvoiceDetail();
				resultToObject(rs, arinvoicedetail);
				lists.add(arinvoicedetail);
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

	private static void resultToObject(ResultSet rs, ARInvoiceDetail arinvoicedetail){
		try{
			arinvoicedetail.setOID(rs.getLong(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_AR_INVOICE_ID]));
			arinvoicedetail.setArInvoiceId(rs.getLong(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_AR_INVOICE_ID]));
			arinvoicedetail.setItemName(rs.getString(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_ITEM_NAME]));
			arinvoicedetail.setCoaId(rs.getLong(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_COA_ID]));
			arinvoicedetail.setQty(rs.getInt(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_QTY]));
			arinvoicedetail.setPrice(rs.getDouble(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_PRICE]));
			arinvoicedetail.setDiscount(rs.getDouble(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_DISCOUNT]));
			arinvoicedetail.setTotalAmount(rs.getDouble(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_TOTAL_AMOUNT]));
			arinvoicedetail.setDepartmentId(rs.getLong(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_DEPARTMENT_ID]));
			arinvoicedetail.setCompanyId(rs.getLong(DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_COMPANY_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long arInvoiceId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + TBL_AR_INVOICE_DETAIL + " WHERE " + 
						DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_AR_INVOICE_ID] + " = " + arInvoiceId;

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
			String sql = "SELECT COUNT("+ DbARInvoiceDetail.colNames[DbARInvoiceDetail.COL_AR_INVOICE_ID] + ") FROM " + TBL_AR_INVOICE_DETAIL;
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
			  	   ARInvoiceDetail arinvoicedetail = (ARInvoiceDetail)list.get(ls);
				   if(oid == arinvoicedetail.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        
        
        
}
