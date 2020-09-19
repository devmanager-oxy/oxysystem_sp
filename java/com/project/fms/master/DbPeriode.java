
package com.project.fms.master; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.fms.master.*; 
import com.project.*;
import com.project.util.*;

public class DbPeriode extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc { 

	public static final  String DB_PERIODE = "periode";

	public static final  int COL_PERIODE_ID = 0;
	public static final  int COL_START_DATE = 1;
	public static final  int COL_END_DATE = 2;
	public static final  int COL_STATUS = 3;
	public static final  int COL_NAME = 4;
        public static final  int COL_INPUT_TOLERANCE = 5;

	public static final  String[] colNames = {
		"periode_id",
		"start_date",
		"end_date",
		"status",
		"name",
                "input_tolerance"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_DATE,
		TYPE_DATE,
		TYPE_STRING,
		TYPE_STRING,
                TYPE_DATE
	 }; 

	public DbPeriode(){
	}

	public DbPeriode(int i) throws CONException { 
		super(new DbPeriode()); 
	}

	public DbPeriode(String sOid) throws CONException { 
		super(new DbPeriode(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbPeriode(long lOid) throws CONException { 
		super(new DbPeriode(0)); 
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
		return DB_PERIODE;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbPeriode().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Periode periode = fetchExc(ent.getOID()); 
		ent = (Entity)periode; 
		return periode.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Periode) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Periode) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Periode fetchExc(long oid) throws CONException{ 
		try{ 
			Periode periode = new Periode();
			DbPeriode dbPeriode = new DbPeriode(oid); 
			periode.setOID(oid);

			periode.setStartDate(dbPeriode.getDate(COL_START_DATE));
			periode.setEndDate(dbPeriode.getDate(COL_END_DATE));
			periode.setStatus(dbPeriode.getString(COL_STATUS));
			periode.setName(dbPeriode.getString(COL_NAME));
                        periode.setInputTolerance(dbPeriode.getDate(COL_INPUT_TOLERANCE));

			return periode; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPeriode(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Periode periode) throws CONException{ 
		try{ 
			DbPeriode dbPeriode = new DbPeriode(0);

			dbPeriode.setDate(COL_START_DATE, periode.getStartDate());
			dbPeriode.setDate(COL_END_DATE, periode.getEndDate());
			dbPeriode.setString(COL_STATUS, periode.getStatus());
			dbPeriode.setString(COL_NAME, periode.getName());
                        dbPeriode.setDate(COL_INPUT_TOLERANCE, periode.getInputTolerance());

			dbPeriode.insert(); 
			periode.setOID(dbPeriode.getlong(COL_PERIODE_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPeriode(0),CONException.UNKNOWN); 
		}
		return periode.getOID();
	}

	public static long updateExc(Periode periode) throws CONException{ 
		try{ 
			if(periode.getOID() != 0){ 
				DbPeriode dbPeriode = new DbPeriode(periode.getOID());

				dbPeriode.setDate(COL_START_DATE, periode.getStartDate());
				dbPeriode.setDate(COL_END_DATE, periode.getEndDate());
				dbPeriode.setString(COL_STATUS, periode.getStatus());
				dbPeriode.setString(COL_NAME, periode.getName());
                                dbPeriode.setDate(COL_INPUT_TOLERANCE, periode.getInputTolerance());

				dbPeriode.update(); 
				return periode.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPeriode(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbPeriode dbPeriode = new DbPeriode(oid);
			dbPeriode.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPeriode(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_PERIODE; 
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
                        
			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();
			while(rs.next()) {
				Periode periode = new Periode();
				resultToObject(rs, periode);
				lists.add(periode);
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

	private static void resultToObject(ResultSet rs, Periode periode){
		try{
			periode.setOID(rs.getLong(DbPeriode.colNames[DbPeriode.COL_PERIODE_ID]));
			periode.setStartDate(rs.getDate(DbPeriode.colNames[DbPeriode.COL_START_DATE]));
			periode.setEndDate(rs.getDate(DbPeriode.colNames[DbPeriode.COL_END_DATE]));
			periode.setStatus(rs.getString(DbPeriode.colNames[DbPeriode.COL_STATUS]));
			periode.setName(rs.getString(DbPeriode.colNames[DbPeriode.COL_NAME]));
                        periode.setInputTolerance(rs.getDate(DbPeriode.colNames[DbPeriode.COL_INPUT_TOLERANCE]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long periodeId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_PERIODE + " WHERE " + 
						DbPeriode.colNames[DbPeriode.COL_PERIODE_ID] + " = " + periodeId;

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
			String sql = "SELECT COUNT("+ DbPeriode.colNames[DbPeriode.COL_PERIODE_ID] + ") FROM " + DB_PERIODE;
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
			  	   Periode periode = (Periode)list.get(ls);
				   if(oid == periode.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static Periode getOpenPeriod(){
               Vector v = list(0,0, "status='"+I_Project.STATUS_PERIOD_OPEN+"'", "start_date desc");
               if(v!=null && v.size()>0){
                   return (Periode)v.get(0);
               }
               return new Periode();
        }
        
        public static Periode getPeriodByTransDate(Date dt){
                String where = "'"+JSPFormater.formatDate(dt, "yyyy-MM-dd")+"' between start_date and end_date ";

                Vector v = DbPeriode.list(0,0, where, "");
                
                if(v!=null && v.size()>0){
                    return (Periode)v.get(0);    
                }
            
                return new Periode();
        }
        
        
        
        public static Vector getAllPeriodInYear(Date dt){
            
            String where = "year('"+JSPFormater.formatDate(dt, "yyyy-MM-dd")+"') = year("+colNames[COL_START_DATE]+")";
            
            return list(0,0, where, null);
            
        }

        public static String closePeriod(Periode newAp, boolean isYearlyClosing){
            
            newAp.setStatus(I_Project.STATUS_PERIOD_OPEN);
            
            String strStartDate = "'"+JSPFormater.formatDate(newAp.getStartDate(), "yyyy-MM-dd")+"'";
            String strEndDate = "'"+JSPFormater.formatDate(newAp.getEndDate(), "yyyy-MM-dd")+"'";
            
            String where = "(start_date between "+strStartDate+" and "+strEndDate + ") or (end_date between "+strStartDate+" and "+strEndDate+")";
            Vector v = DbPeriode.list(0,0, where, "");
            if(v!=null && v.size()>0){
                return "Period date overlap existing date period";
            }
            else{
                long oid = 0;                                
                //close open period
                Periode ap = getOpenPeriod();
                
                try{
                    if(ap.getOID()!=0){
                        
                        ap.setStatus(I_Project.STATUS_PERIOD_CLOSED);                                                                    
                        oid = DbPeriode.insertExc(newAp);                            

                        if(oid!=0){
                            oid = DbPeriode.updateExc(ap);                        
                            DbCoa.getOpeningBalanceClosing(ap,isYearlyClosing);
                        }
                    }
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
            }
            
            return "";
            
        }   
        
        
        public static Periode getPrevPeriode(Periode periode){
            
            Date startDate = periode.getStartDate();
            Date xDate = (Date)startDate.clone();
            xDate.setDate(xDate.getDate()-10);
            
            String where = JSPFormater.formatDate(xDate, "yyyy-MM-dd")+" between " +
                colNames[COL_START_DATE]+" and "+colNames[COL_END_DATE];
            
            Vector v = DbPeriode.list(0,0, where, "");
            if(v!=null && v.size()>0){
                return (Periode)v.get(0);
            }
            
            return new Periode();
            
        }
        
        
}
