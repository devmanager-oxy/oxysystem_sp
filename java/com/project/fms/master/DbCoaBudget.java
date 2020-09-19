
package com.project.fms.master; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*; 
import com.project.util.lang.I_Language;

public class DbCoaBudget extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_COA_BUDGET = "coa_budget";

	public static final  int CL_COA_ID = 0;
	public static final  int CL_PERIODE_ID = 1;
	public static final  int CL_COA_BUDGET_ID = 2;
	public static final  int CL_AMOUNT = 3;
        public static final  int CL_BGT_YEAR = 4;
        public static final  int CL_BGT_MONTH = 5;
        
	public static final  String[] colNames = {
		"coa_id",
		"periode_id",
		"coa_budget_id",
		"amount",
                "bgt_year",
                "bgt_month"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_FLOAT,
                TYPE_INT,
                TYPE_INT
	 }; 

	public DbCoaBudget(){
	}

	public DbCoaBudget(int i) throws CONException { 
		super(new DbCoaBudget()); 
	}

	public DbCoaBudget(String sOid) throws CONException { 
		super(new DbCoaBudget(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbCoaBudget(long lOid) throws CONException { 
		super(new DbCoaBudget(0)); 
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
		return DB_COA_BUDGET;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbCoaBudget().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		CoaBudget coabudget = fetchExc(ent.getOID()); 
		ent = (Entity)coabudget; 
		return coabudget.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((CoaBudget) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((CoaBudget) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static CoaBudget fetchExc(long oid) throws CONException{ 
		try{ 
			CoaBudget coabudget = new CoaBudget();
			DbCoaBudget pstCoaBudget = new DbCoaBudget(oid); 
			coabudget.setOID(oid);

			coabudget.setCoaId(pstCoaBudget.getlong(CL_COA_ID));
			coabudget.setPeriodeId(pstCoaBudget.getlong(CL_PERIODE_ID));
			coabudget.setAmount(pstCoaBudget.getdouble(CL_AMOUNT));
                        coabudget.setBgtYear(pstCoaBudget.getInt(CL_BGT_YEAR));
                        coabudget.setBgtMonth(pstCoaBudget.getInt(CL_BGT_MONTH));

			return coabudget; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoaBudget(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(CoaBudget coabudget) throws CONException{ 
		try{ 
			DbCoaBudget pstCoaBudget = new DbCoaBudget(0);

			pstCoaBudget.setLong(CL_COA_ID, coabudget.getCoaId());
			pstCoaBudget.setLong(CL_PERIODE_ID, coabudget.getPeriodeId());
			pstCoaBudget.setDouble(CL_AMOUNT, coabudget.getAmount());
                        pstCoaBudget.setInt(CL_BGT_YEAR, coabudget.getBgtYear());
                        pstCoaBudget.setInt(CL_BGT_MONTH, coabudget.getBgtMonth());

			pstCoaBudget.insert(); 
			coabudget.setOID(pstCoaBudget.getlong(CL_COA_BUDGET_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoaBudget(0),CONException.UNKNOWN); 
		}
		return coabudget.getOID();
	}

	public static long updateExc(CoaBudget coabudget) throws CONException{ 
		try{ 
			if(coabudget.getOID() != 0){ 
				DbCoaBudget pstCoaBudget = new DbCoaBudget(coabudget.getOID());

				pstCoaBudget.setLong(CL_COA_ID, coabudget.getCoaId());
				pstCoaBudget.setLong(CL_PERIODE_ID, coabudget.getPeriodeId());
				pstCoaBudget.setDouble(CL_AMOUNT, coabudget.getAmount());
                                pstCoaBudget.setInt(CL_BGT_YEAR, coabudget.getBgtYear());
                                pstCoaBudget.setInt(CL_BGT_MONTH, coabudget.getBgtMonth());

				pstCoaBudget.update(); 
				return coabudget.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoaBudget(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbCoaBudget pstCoaBudget = new DbCoaBudget(oid);
			pstCoaBudget.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoaBudget(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_COA_BUDGET; 
			if(whereClause != null && whereClause.length() > 0)
				sql = sql + " WHERE " + whereClause;
			if(order != null && order.length() > 0)
				sql = sql + " ORDER BY " + order;
			if(limitStart == 0 && recordToGet == 0)
				sql = sql + "";
			else
				sql = sql + " LIMIT " + limitStart + ","+ recordToGet ;
                        
                        System.out.println(sql);
                        
			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();
			while(rs.next()) {
				CoaBudget coabudget = new CoaBudget();
				resultToObject(rs, coabudget);
				lists.add(coabudget);
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

	private static void resultToObject(ResultSet rs, CoaBudget coabudget){
		try{
			coabudget.setOID(rs.getLong(DbCoaBudget.colNames[DbCoaBudget.CL_COA_BUDGET_ID]));
			coabudget.setCoaId(rs.getLong(DbCoaBudget.colNames[DbCoaBudget.CL_COA_ID]));
			coabudget.setPeriodeId(rs.getLong(DbCoaBudget.colNames[DbCoaBudget.CL_PERIODE_ID]));
			coabudget.setAmount(rs.getDouble(DbCoaBudget.colNames[DbCoaBudget.CL_AMOUNT]));
                        coabudget.setBgtYear(rs.getInt(DbCoaBudget.colNames[DbCoaBudget.CL_BGT_YEAR]));
                        coabudget.setBgtMonth(rs.getInt(DbCoaBudget.colNames[DbCoaBudget.CL_BGT_MONTH]));

		}catch(Exception e){ }
	}

	/*public static boolean checkOID(long coaBudgetId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_COA_BUDGET + " WHERE " + 
						DbCoaBudget.colNames[DbCoaBudget.CL_COA_ID] + " = " + coaId;

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
         */

	public static int getCount(String whereClause){
		CONResultSet dbrs = null;
		try {
			String sql = "SELECT COUNT("+ DbCoaBudget.colNames[DbCoaBudget.CL_COA_BUDGET_ID] + ") FROM " + DB_COA_BUDGET;
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
			  	   CoaBudget coabudget = (CoaBudget)list.get(ls);
				   if(oid == coabudget.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        public static CoaBudget getBudget(long coaId, long periodId){
                
            String where = colNames[CL_COA_ID] +"="+coaId+" and "+colNames[CL_PERIODE_ID]+"="+periodId;
            Vector v = list(0,0, where, null);
            if(v!=null && v.size()>0){
                return (CoaBudget)v.get(0);
            }
            
            return new CoaBudget();
                
        }
        
        public static CoaBudget getBudget(long coaId, int year){
                
            String where = colNames[CL_COA_ID] +"="+coaId+" and "+colNames[CL_BGT_YEAR]+"="+year;
            Vector v = list(0,0, where, null);
            if(v!=null && v.size()>0){
                return (CoaBudget)v.get(0);
            }
            
            return new CoaBudget();
                
        }
        
        
        public static CoaBudget getBudget(long coaId, int year, int month){
                
            String where = colNames[CL_COA_ID] +"="+coaId+" and "+colNames[CL_BGT_YEAR]+"="+year+" and "+colNames[CL_BGT_MONTH]+"="+month;
            Vector v = list(0,0, where, null);
            if(v!=null && v.size()>0){
                return (CoaBudget)v.get(0);
            }
            
            return new CoaBudget();
                
        }
        
        public static double getBudgetRecursif(Coa coa, long periodId){
            
            double result = 0;
            
            if(coa.getStatus().equals("HEADER")){
                Vector v = DbCoa.list(0,0,  DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);
                        result = result + getBudgetRecursif(cx, periodId);
                    }
                }
            }
            else{
                CoaBudget cb = getBudget(coa.getOID(), periodId);
                result = result + cb.getAmount();
            }
            
            return result;
            
        }
        
        public static double getBudgetRecursif(Coa coa, int year){
            
            double result = 0;
            
            if(coa.getStatus().equals("HEADER")){
                Vector v = DbCoa.list(0,0,  DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);
                        result = result + getBudgetRecursif(cx, year);
                    }
                }
            }
            else{
                CoaBudget cb = getBudget(coa.getOID(), year);
                result = result + cb.getAmount();
            }
            
            return result;
            
        }
        
        public static long processBudget(long coaId, long periodId, double amount){
            
            CoaBudget cb = getBudget(coaId, periodId);
            long oid = 0;
            
            try{
                if(cb.getOID()!=0){
                    cb.setAmount(amount);
                    oid = updateExc(cb);    
                }
                else{
                    cb.setCoaId(coaId);
                    cb.setPeriodeId(periodId);
                    cb.setAmount(amount);
                    oid = insertExc(cb);
                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
            return oid;
            
        }
        
        
        
        
        
        public static long processBudget(long coaId, int year, double amount){
            
            CoaBudget cb = getBudget(coaId, year);
            long oid = 0;
            
            try{
                if(cb.getOID()!=0){
                    cb.setAmount(amount);
                    oid = updateExc(cb);    
                }
                else{
                    cb.setCoaId(coaId);
                    cb.setBgtYear(year);
                    cb.setAmount(amount);
                    oid = insertExc(cb);
                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
            return oid;
            
        }
        
        public static long processBudget(long coaId, int year, double amount, int month){
            
            CoaBudget cb = getBudget(coaId, year, month);
            long oid = 0;
            
            try{
                if(cb.getOID()!=0){
                    cb.setAmount(amount);
                    oid = updateExc(cb);    
                }
                else{
                    cb.setCoaId(coaId);
                    cb.setBgtYear(year);
                    cb.setBgtMonth(month);
                    cb.setAmount(amount);
                    oid = insertExc(cb);
                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
            return oid;
            
        }
        
        public static double getTMBudgetRecursif(Coa coa, int year, int month){
            double result = 0;
            if(coa.getStatus().equals("HEADER")){
                Vector temp = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(temp!=null && temp.size()>0){
                    for(int i=0; i<temp.size(); i++){
                        Coa c = (Coa)temp.get(i);
                        result = result + getTMBudgetRecursif(c, year, month);
                    }
                }
            }
            else{
                Vector bgts = DbCoaBudget.list(0,0, colNames[CL_COA_ID]+"="+coa.getOID()+
                        " and "+colNames[CL_BGT_YEAR]+"="+year+
                        " and "+colNames[CL_BGT_MONTH]+"="+month, "");
                
                if(bgts!=null && bgts.size()>0){
                    CoaBudget cb = (CoaBudget)bgts.get(0);
                    result = cb.getAmount();
                }                
            }
            
            return result;
        }
        
        public static double getTYBudgetRecursif(Coa coa, int year){
            double result = 0;    
            if(coa.getStatus().equals("HEADER")){
                Vector temp = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(temp!=null && temp.size()>0){
                    for(int i=0; i<temp.size(); i++){
                        Coa c = (Coa)temp.get(i);
                        result = result + getTYBudgetRecursif(c, year);
                    }
                }
            }
            else{
                
                String sql = "select sum("+colNames[CL_AMOUNT]+") from "+DB_COA_BUDGET+" where "+
                    colNames[CL_COA_ID]+"="+coa.getOID()+" and "+colNames[CL_BGT_YEAR]+"="+year;
                
                CONResultSet crs = null;
                try{
                    crs = CONHandler.execQueryResult(sql);
                    ResultSet rs = crs.getResultSet();
                    while(rs.next()){
                        result = rs.getDouble(1);
                    }
                }
                catch(Exception e){
                }
                finally{
                    CONResultSet.close(crs);
                }
                
            }
            
            return result;
        }
        
        public static double getUntilTMBudgetRecusrsif(Coa coa, int year, int month){
            double result = 0;
            if(coa.getStatus().equals("HEADER")){
                Vector temp = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(temp!=null && temp.size()>0){
                    for(int i=0; i<temp.size(); i++){
                        Coa c = (Coa)temp.get(i);
                        result = result + getUntilTMBudgetRecusrsif(c, year, month);
                    }
                }
            }
            else{
                
                String sql = "select sum("+colNames[CL_AMOUNT]+") from "+DB_COA_BUDGET+" where "+
                    colNames[CL_COA_ID]+"="+coa.getOID()+" and "+colNames[CL_BGT_YEAR]+"="+year+" and "+
                    colNames[CL_BGT_MONTH]+"<="+month;
                
                CONResultSet crs = null;
                try{
                    crs = CONHandler.execQueryResult(sql);
                    ResultSet rs = crs.getResultSet();
                    while(rs.next()){
                        result = rs.getDouble(1);
                    }
                }
                catch(Exception e){
                }
                finally{
                    CONResultSet.close(crs);
                }
                
            }
            
            return result;
        }
        
       
        
}

