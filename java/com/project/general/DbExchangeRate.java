

package com.project.general; 

/* package java */ 
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.project.main.entity.*;
import com.project.main.db.*;

import com.project.general.*; 

public class DbExchangeRate extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc { 

	public static final  String TBL_EXCHANGERATE = "exchangerate";

	public static final  int FLD_EXCHANGERATE_ID = 0;
	public static final  int FLD_DATE = 1;
	public static final  int FLD_VALUE_USD = 2;
        public static final  int FLD_VALUE_IDR = 3;
        public static final  int FLD_VALUE_EURO = 4;
	public static final  int FLD_STATUS = 5;	
	public static final  int FLD_USER_ID = 6;
        
        public static final  int FLD_CURRENCY_IDR_ID = 7;
        public static final  int FLD_CURRENCY_USD_ID = 8;
        public static final  int FLD_CURRENCY_EURO_ID = 9;

	public static final  String[] fieldNames = {
		"exchangerate_id",
		"date",
		"value_usd",
                "value_idr",
                "value_euro",
		"status",		
		"user_id",
                "currency_idr_id",
                "currency_usd_id",
                "currency_euro_id"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_DATE,
		TYPE_FLOAT,
                TYPE_FLOAT,
                TYPE_FLOAT,
		TYPE_INT,		
		TYPE_LONG,
                
                TYPE_LONG,
                TYPE_LONG,
                TYPE_LONG
	 }; 

	public DbExchangeRate(){
	}

	public DbExchangeRate(int i) throws CONException { 
		super(new DbExchangeRate()); 
	}

	public DbExchangeRate(String sOid) throws CONException { 
		super(new DbExchangeRate(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbExchangeRate(long lOid) throws CONException { 
		super(new DbExchangeRate(0)); 
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
		return fieldNames.length; 
	}

	public String getTableName(){ 
		return TBL_EXCHANGERATE;
	}

	public String[] getFieldNames(){ 
		return fieldNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbExchangeRate().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		ExchangeRate exchangerate = fetchExc(ent.getOID()); 
		ent = (Entity)exchangerate; 
		return exchangerate.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((ExchangeRate) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((ExchangeRate) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static ExchangeRate fetchExc(long oid) throws CONException{ 
		try{ 
			ExchangeRate exchangerate = new ExchangeRate();
			DbExchangeRate pstExchangeRate = new DbExchangeRate(oid); 
			exchangerate.setOID(oid);

			exchangerate.setDate(pstExchangeRate.getDate(FLD_DATE));
			exchangerate.setValueUsd(pstExchangeRate.getdouble(FLD_VALUE_USD));
			exchangerate.setValueIdr(pstExchangeRate.getdouble(FLD_VALUE_IDR));
                        exchangerate.setValueEuro(pstExchangeRate.getdouble(FLD_VALUE_EURO));
			exchangerate.setStatus(pstExchangeRate.getInt(FLD_STATUS));			
			exchangerate.setUserId(pstExchangeRate.getlong(FLD_USER_ID));
                        
                        exchangerate.setCurrencyEuroId(pstExchangeRate.getlong(FLD_CURRENCY_EURO_ID));
                        exchangerate.setCurrencyIdrId(pstExchangeRate.getlong(FLD_CURRENCY_IDR_ID));
                        exchangerate.setCurrencyUsdId(pstExchangeRate.getlong(FLD_CURRENCY_USD_ID));

			return exchangerate; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbExchangeRate(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(ExchangeRate exchangerate) throws CONException{ 
		try{ 
			DbExchangeRate pstExchangeRate = new DbExchangeRate(0);

			pstExchangeRate.setDate(FLD_DATE, exchangerate.getDate());
			pstExchangeRate.setDouble(FLD_VALUE_USD, exchangerate.getValueUsd());
                        pstExchangeRate.setDouble(FLD_VALUE_IDR, exchangerate.getValueIdr());
                        pstExchangeRate.setDouble(FLD_VALUE_EURO, exchangerate.getValueEuro());
			pstExchangeRate.setInt(FLD_STATUS, exchangerate.getStatus());
			
			pstExchangeRate.setLong(FLD_USER_ID, exchangerate.getUserId());
                        
                        pstExchangeRate.setLong(FLD_CURRENCY_EURO_ID, exchangerate.getCurrencyEuroId());
                        pstExchangeRate.setLong(FLD_CURRENCY_IDR_ID, exchangerate.getCurrencyIdrId());
                        pstExchangeRate.setLong(FLD_CURRENCY_USD_ID, exchangerate.getCurrencyUsdId());

			pstExchangeRate.insert(); 
			exchangerate.setOID(pstExchangeRate.getlong(FLD_EXCHANGERATE_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbExchangeRate(0),CONException.UNKNOWN); 
		}
		return exchangerate.getOID();
	}

	public static long updateExc(ExchangeRate exchangerate) throws CONException{ 
		try{ 
			if(exchangerate.getOID() != 0){ 
				DbExchangeRate pstExchangeRate = new DbExchangeRate(exchangerate.getOID());

				pstExchangeRate.setDate(FLD_DATE, exchangerate.getDate());
				pstExchangeRate.setDouble(FLD_VALUE_USD, exchangerate.getValueUsd());
                                pstExchangeRate.setDouble(FLD_VALUE_IDR, exchangerate.getValueIdr());
                                pstExchangeRate.setDouble(FLD_VALUE_EURO, exchangerate.getValueEuro());
				pstExchangeRate.setInt(FLD_STATUS, exchangerate.getStatus());				
				pstExchangeRate.setLong(FLD_USER_ID, exchangerate.getUserId());
                                
                                pstExchangeRate.setLong(FLD_CURRENCY_EURO_ID, exchangerate.getCurrencyEuroId());
                                pstExchangeRate.setLong(FLD_CURRENCY_IDR_ID, exchangerate.getCurrencyIdrId());
                                pstExchangeRate.setLong(FLD_CURRENCY_USD_ID, exchangerate.getCurrencyUsdId());

				pstExchangeRate.update(); 
				return exchangerate.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbExchangeRate(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbExchangeRate pstExchangeRate = new DbExchangeRate(oid);
			pstExchangeRate.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbExchangeRate(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + TBL_EXCHANGERATE; 
			if(whereClause != null && whereClause.length() > 0)
				sql = sql + " WHERE " + whereClause;
			if(order != null && order.length() > 0)
				sql = sql + " ORDER BY " + order;
			switch (CONHandler.CONSVR_TYPE) {
                            case CONHandler.CONSVR_MYSQL:
                                if (limitStart == 0 && recordToGet == 0)
                                    sql = sql + "";
                                else
                                    sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                                break;

                            case CONHandler.CONSVR_POSTGRESQL:
                                if (limitStart == 0 && recordToGet == 0)
                                    sql = sql + "";
                                else
                                    sql = sql + " LIMIT " + recordToGet + " OFFSET " + limitStart;

                                break;

                            case CONHandler.CONSVR_SYBASE:
                                break;

                            case CONHandler.CONSVR_ORACLE:
                                break;

                            case CONHandler.CONSVR_MSSQL:
                                break;

                            default:
                                break;
                        }
                        
                        //System.out.println(sql);
                        
			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();
			while(rs.next()) {
				ExchangeRate exchangerate = new ExchangeRate();
				resultToObject(rs, exchangerate);
				lists.add(exchangerate);
			}
			rs.close();
			return lists;

		}catch(Exception e) {
			System.out.println(e.toString());
		}finally {
			CONResultSet.close(dbrs);
		}
			return new Vector();
	}

	public static void resultToObject(ResultSet rs, ExchangeRate exchangerate){
		try{
			exchangerate.setOID(rs.getLong(DbExchangeRate.fieldNames[DbExchangeRate.FLD_EXCHANGERATE_ID]));                        
			exchangerate.setValueUsd(rs.getDouble(DbExchangeRate.fieldNames[DbExchangeRate.FLD_VALUE_USD]));
                        exchangerate.setValueIdr(rs.getDouble(DbExchangeRate.fieldNames[DbExchangeRate.FLD_VALUE_IDR]));
                        exchangerate.setValueEuro(rs.getDouble(DbExchangeRate.fieldNames[DbExchangeRate.FLD_VALUE_EURO]));
			exchangerate.setStatus(rs.getInt(DbExchangeRate.fieldNames[DbExchangeRate.FLD_STATUS]));
			exchangerate.setUserId(rs.getLong(DbExchangeRate.fieldNames[DbExchangeRate.FLD_USER_ID]));
                        
                        exchangerate.setCurrencyEuroId(rs.getLong(DbExchangeRate.fieldNames[DbExchangeRate.FLD_CURRENCY_EURO_ID]));
                        exchangerate.setCurrencyIdrId(rs.getLong(DbExchangeRate.fieldNames[DbExchangeRate.FLD_CURRENCY_IDR_ID]));
                        exchangerate.setCurrencyUsdId(rs.getLong(DbExchangeRate.fieldNames[DbExchangeRate.FLD_CURRENCY_USD_ID]));
                        
                        
                        Date dt = rs.getDate(DbExchangeRate.fieldNames[DbExchangeRate.FLD_DATE]);
                        System.out.println("dt : "+dt);
                        
                        Date time = rs.getTime(DbExchangeRate.fieldNames[DbExchangeRate.FLD_DATE]);
                        
			exchangerate.setDate(dt);
                        exchangerate.setTime(time);
                        
                        System.out.println("time : "+time);
                        System.out.println("dt : "+dt);

		}catch(Exception e){
                        System.out.println(e.toString());
                }
	}

	public static boolean checkOID(long exchangerateId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + TBL_EXCHANGERATE + " WHERE " + 
						DbExchangeRate.fieldNames[DbExchangeRate.FLD_EXCHANGERATE_ID] + " = " + exchangerateId;

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
			String sql = "SELECT COUNT("+ DbExchangeRate.fieldNames[DbExchangeRate.FLD_EXCHANGERATE_ID] + ") FROM " + TBL_EXCHANGERATE;
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
			  	   ExchangeRate exchangerate = (ExchangeRate)list.get(ls);
				   if(oid == exchangerate.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static ExchangeRate getStandardRate(){
                ExchangeRate er = new ExchangeRate();
                Vector v = list(0,1, "status=0", "date desc");
                if(v!=null && v.size()>0){
                    er = (ExchangeRate)v.get(0);
                }
                return er;
        }
        
        public static ExchangeRate getStandardRate(long companyId){
                ExchangeRate er = new ExchangeRate();
                Vector v = list(0,1, "status=0 and company_id="+companyId, "date desc");
                if(v!=null && v.size()>0){
                    er = (ExchangeRate)v.get(0);
                }
                return er;
        }
        
}
