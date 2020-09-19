
package com.project.ccs.postransaction.costing; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.lang.I_Language;

public class DbCosting extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_POS_COSTING = "pos_costing";

	public static final  int COL_COSTING_ID = 0;
	public static final  int COL_DATE = 1;
	public static final  int COL_COUNTER = 2;
	public static final  int COL_NUMBER = 3;
	public static final  int COL_NOTE = 4;
	public static final  int COL_APPROVAL_1 = 5;
	public static final  int COL_APPROVAL_2 = 6;
	public static final  int COL_APPROVAL_3 = 7;
	public static final  int COL_STATUS = 8;
	public static final  int COL_LOCATION_ID = 9;
	public static final  int COL_USER_ID = 10;

	public static final  String[] colNames = {
		"costing_id",
		"date",
		"counter",
		"number",
		"note",
		"approval_1",
		"approval_2",
		"approval_3",
		"status",
		"location_id",
		"user_id"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_DATE,
		TYPE_INT,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_INT,
		TYPE_INT,
		TYPE_INT,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_LONG
	 }; 

	public DbCosting(){
	}

	public DbCosting(int i) throws CONException { 
		super(new DbCosting()); 
	}

	public DbCosting(String sOid) throws CONException { 
		super(new DbCosting(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbCosting(long lOid) throws CONException { 
		super(new DbCosting(0)); 
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
		return DB_POS_COSTING;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbCosting().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Costing costing = fetchExc(ent.getOID()); 
		ent = (Entity)costing; 
		return costing.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Costing) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Costing) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Costing fetchExc(long oid) throws CONException{ 
		try{ 
			Costing costing = new Costing();
			DbCosting pstCosting = new DbCosting(oid); 
			costing.setOID(oid);

			costing.setDate(pstCosting.getDate(COL_DATE));
			costing.setCounter(pstCosting.getInt(COL_COUNTER));
			costing.setNumber(pstCosting.getString(COL_NUMBER));
			costing.setNote(pstCosting.getString(COL_NOTE));
			costing.setApproval1(pstCosting.getInt(COL_APPROVAL_1));
			costing.setApproval2(pstCosting.getInt(COL_APPROVAL_2));
			costing.setApproval3(pstCosting.getInt(COL_APPROVAL_3));
			costing.setStatus(pstCosting.getString(COL_STATUS));
			costing.setLocationId(pstCosting.getlong(COL_LOCATION_ID));
			costing.setUserId(pstCosting.getlong(COL_USER_ID));

			return costing; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCosting(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Costing costing) throws CONException{ 
		try{ 
			DbCosting pstCosting = new DbCosting(0);

			pstCosting.setDate(COL_DATE, costing.getDate());
			pstCosting.setInt(COL_COUNTER, costing.getCounter());
			pstCosting.setString(COL_NUMBER, costing.getNumber());
			pstCosting.setString(COL_NOTE, costing.getNote());
			pstCosting.setInt(COL_APPROVAL_1, costing.getApproval1());
			pstCosting.setInt(COL_APPROVAL_2, costing.getApproval2());
			pstCosting.setInt(COL_APPROVAL_3, costing.getApproval3());
			pstCosting.setString(COL_STATUS, costing.getStatus());
			pstCosting.setLong(COL_LOCATION_ID, costing.getLocationId());
			pstCosting.setLong(COL_USER_ID, costing.getUserId());

			pstCosting.insert(); 
			costing.setOID(pstCosting.getlong(COL_COSTING_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCosting(0),CONException.UNKNOWN); 
		}
		return costing.getOID();
	}

	public static long updateExc(Costing costing) throws CONException{ 
		try{ 
			if(costing.getOID() != 0){ 
				DbCosting pstCosting = new DbCosting(costing.getOID());

				pstCosting.setDate(COL_DATE, costing.getDate());
				pstCosting.setInt(COL_COUNTER, costing.getCounter());
				pstCosting.setString(COL_NUMBER, costing.getNumber());
				pstCosting.setString(COL_NOTE, costing.getNote());
				pstCosting.setInt(COL_APPROVAL_1, costing.getApproval1());
				pstCosting.setInt(COL_APPROVAL_2, costing.getApproval2());
				pstCosting.setInt(COL_APPROVAL_3, costing.getApproval3());
				pstCosting.setString(COL_STATUS, costing.getStatus());
				pstCosting.setLong(COL_LOCATION_ID, costing.getLocationId());
				pstCosting.setLong(COL_USER_ID, costing.getUserId());

				pstCosting.update(); 
				return costing.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCosting(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbCosting pstCosting = new DbCosting(oid);
			pstCosting.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCosting(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_POS_COSTING; 
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
				Costing costing = new Costing();
				resultToObject(rs, costing);
				lists.add(costing);
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

	private static void resultToObject(ResultSet rs, Costing costing){
		try{
			costing.setOID(rs.getLong(DbCosting.colNames[DbCosting.COL_COSTING_ID]));
			costing.setDate(rs.getDate(DbCosting.colNames[DbCosting.COL_DATE]));
			costing.setCounter(rs.getInt(DbCosting.colNames[DbCosting.COL_COUNTER]));
			costing.setNumber(rs.getString(DbCosting.colNames[DbCosting.COL_NUMBER]));
			costing.setNote(rs.getString(DbCosting.colNames[DbCosting.COL_NOTE]));
			costing.setApproval1(rs.getInt(DbCosting.colNames[DbCosting.COL_APPROVAL_1]));
			costing.setApproval2(rs.getInt(DbCosting.colNames[DbCosting.COL_APPROVAL_2]));
			costing.setApproval3(rs.getInt(DbCosting.colNames[DbCosting.COL_APPROVAL_3]));
			costing.setStatus(rs.getString(DbCosting.colNames[DbCosting.COL_STATUS]));
			costing.setLocationId(rs.getLong(DbCosting.colNames[DbCosting.COL_LOCATION_ID]));
			costing.setUserId(rs.getLong(DbCosting.colNames[DbCosting.COL_USER_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long costingId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_POS_COSTING + " WHERE " + 
						DbCosting.colNames[DbCosting.COL_COSTING_ID] + " = " + costingId;

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
			String sql = "SELECT COUNT("+ DbCosting.colNames[DbCosting.COL_COSTING_ID] + ") FROM " + DB_POS_COSTING;
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
			  	   Costing costing = (Costing)list.get(ls);
				   if(oid == costing.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
}
