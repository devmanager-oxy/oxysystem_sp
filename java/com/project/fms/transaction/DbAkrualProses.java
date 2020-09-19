package com.project.fms.transaction; 

/* package java */ 
import java.io.*
;
import java.sql.*
;import java.util.*
;import java.util.Date;

/* package qdep */
import com.project.util.lang.I_Language;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*; 
import com.project.fms.transaction.*;
import com.project.fms.master.*;
import com.project.util.*;
import com.project.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;

public class DbAkrualProses extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_AKRUAL_PROSES = "akrual_proses";

	public static final  int COL_AKRUAL_PROSES_ID = 0;
	public static final  int COL_REG_DATE = 1;
	public static final  int COL_JUMLAH = 2;
	public static final  int COL_CREDIT_COA_ID = 3;
	public static final  int COL_DEBET_COA_ID = 4;
	public static final  int COL_PERIODE_ID = 5;
	public static final  int COL_USER_ID = 6;
        public static final  int COL_AKRUAL_SETUP_ID = 7;

	public static final  String[] colNames = {
                "akrual_proses_id",
                "reg_date",
                "jumlah",
                "credit_coa_id",
                "debet_coa_id",
                "periode_id",
                "user_id",
                "akrual_setup_id"

	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_DATE,
		TYPE_FLOAT,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
                TYPE_LONG
	 }; 

	public DbAkrualProses(){
	}

	public DbAkrualProses(int i) throws CONException { 
		super(new DbAkrualProses()); 
	}

	public DbAkrualProses(String sOid) throws CONException { 
		super(new DbAkrualProses(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbAkrualProses(long lOid) throws CONException { 
		super(new DbAkrualProses(0)); 
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
		return DB_AKRUAL_PROSES;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbAkrualProses().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		AkrualProses akrualproses = fetchExc(ent.getOID()); 
		ent = (Entity)akrualproses; 
		return akrualproses.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((AkrualProses) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((AkrualProses) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static AkrualProses fetchExc(long oid) throws CONException{ 
		try{ 
			AkrualProses akrualproses = new AkrualProses();
			DbAkrualProses pstAkrualProses = new DbAkrualProses(oid); 
			akrualproses.setOID(oid);

			akrualproses.setRegDate(pstAkrualProses.getDate(COL_REG_DATE));
			akrualproses.setJumlah(pstAkrualProses.getdouble(COL_JUMLAH));
			akrualproses.setCreditCoaId(pstAkrualProses.getlong(COL_CREDIT_COA_ID));
			akrualproses.setDebetCoaId(pstAkrualProses.getlong(COL_DEBET_COA_ID));
			akrualproses.setPeriodeId(pstAkrualProses.getlong(COL_PERIODE_ID));
			akrualproses.setUserId(pstAkrualProses.getlong(COL_USER_ID));
                        akrualproses.setAkrualSetupId(pstAkrualProses.getlong(COL_AKRUAL_SETUP_ID));

			return akrualproses; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbAkrualProses(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(AkrualProses akrualproses) throws CONException{ 
		try{ 
			DbAkrualProses pstAkrualProses = new DbAkrualProses(0);

			pstAkrualProses.setDate(COL_REG_DATE, akrualproses.getRegDate());
			pstAkrualProses.setDouble(COL_JUMLAH, akrualproses.getJumlah());
			pstAkrualProses.setLong(COL_CREDIT_COA_ID, akrualproses.getCreditCoaId());
			pstAkrualProses.setLong(COL_DEBET_COA_ID, akrualproses.getDebetCoaId());
			pstAkrualProses.setLong(COL_PERIODE_ID, akrualproses.getPeriodeId());
			pstAkrualProses.setLong(COL_USER_ID, akrualproses.getUserId());
                        pstAkrualProses.setLong(COL_AKRUAL_SETUP_ID, akrualproses.getAkrualSetupId());

			pstAkrualProses.insert(); 
			akrualproses.setOID(pstAkrualProses.getlong(COL_AKRUAL_PROSES_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbAkrualProses(0),CONException.UNKNOWN); 
		}
		return akrualproses.getOID();
	}

	public static long updateExc(AkrualProses akrualproses) throws CONException{ 
		try{ 
			if(akrualproses.getOID() != 0){ 
				DbAkrualProses pstAkrualProses = new DbAkrualProses(akrualproses.getOID());

				pstAkrualProses.setDate(COL_REG_DATE, akrualproses.getRegDate());
				pstAkrualProses.setDouble(COL_JUMLAH, akrualproses.getJumlah());
				pstAkrualProses.setLong(COL_CREDIT_COA_ID, akrualproses.getCreditCoaId());
				pstAkrualProses.setLong(COL_DEBET_COA_ID, akrualproses.getDebetCoaId());
				pstAkrualProses.setLong(COL_PERIODE_ID, akrualproses.getPeriodeId());
				pstAkrualProses.setLong(COL_USER_ID, akrualproses.getUserId());
                                pstAkrualProses.setLong(COL_AKRUAL_SETUP_ID, akrualproses.getAkrualSetupId());

				pstAkrualProses.update(); 
				return akrualproses.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbAkrualProses(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbAkrualProses pstAkrualProses = new DbAkrualProses(oid);
			pstAkrualProses.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbAkrualProses(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_AKRUAL_PROSES; 
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
				AkrualProses akrualproses = new AkrualProses();
				resultToObject(rs, akrualproses);
				lists.add(akrualproses);
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

	private static void resultToObject(ResultSet rs, AkrualProses akrualproses){
		try{
			akrualproses.setOID(rs.getLong(DbAkrualProses.colNames[DbAkrualProses.COL_AKRUAL_PROSES_ID]));
			akrualproses.setRegDate(rs.getDate(DbAkrualProses.colNames[DbAkrualProses.COL_REG_DATE]));
			akrualproses.setJumlah(rs.getDouble(DbAkrualProses.colNames[DbAkrualProses.COL_JUMLAH]));
			akrualproses.setCreditCoaId(rs.getLong(DbAkrualProses.colNames[DbAkrualProses.COL_CREDIT_COA_ID]));
			akrualproses.setDebetCoaId(rs.getLong(DbAkrualProses.colNames[DbAkrualProses.COL_DEBET_COA_ID]));
			akrualproses.setPeriodeId(rs.getLong(DbAkrualProses.colNames[DbAkrualProses.COL_PERIODE_ID]));
			akrualproses.setUserId(rs.getLong(DbAkrualProses.colNames[DbAkrualProses.COL_USER_ID]));
                        akrualproses.setAkrualSetupId(rs.getLong(DbAkrualProses.colNames[DbAkrualProses.COL_AKRUAL_SETUP_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long akrualProsesId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_AKRUAL_PROSES + " WHERE " + 
						DbAkrualProses.colNames[DbAkrualProses.COL_AKRUAL_PROSES_ID] + " = " + akrualProsesId;

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
			String sql = "SELECT COUNT("+ DbAkrualProses.colNames[DbAkrualProses.COL_AKRUAL_PROSES_ID] + ") FROM " + DB_AKRUAL_PROSES;
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
			  	   AkrualProses akrualproses = (AkrualProses)list.get(ls);
				   if(oid == akrualproses.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static double getTotalAkrual(long asId){
            String sql = "SELECT sum("+colNames[COL_JUMLAH]+") FROM "+DB_AKRUAL_PROSES+
                    " where year("+colNames[COL_REG_DATE]+")="+JSPFormater.formatDate(new Date(), "yyyy")+
                    " and "+colNames[COL_AKRUAL_SETUP_ID]+"="+asId;
            
            CONResultSet crs = null;
            double result = 0;
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
                
            return result;
        }
        
        
        public static void postJournal(AkrualProses ap){
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                AkrualSetup as = new AkrualSetup();
                try{
                    as = DbAkrualSetup.fetchExc(ap.getAkrualSetupId());
                }
                catch(Exception e){
                    
                }
                
                Periode p = new Periode();
                try{
                    p = DbPeriode.fetchExc(ap.getPeriodeId());
                }
                catch(Exception e){
                    
                }
                
                if(ap.getOID()!=0){
                    
                    int cnt = DbGl.getNextCounter();
                    String pref = DbGl.getNumberPrefix();
                    String jnum = DbGl.getNextNumber(cnt);
                    
                    long oid = DbGl.postJournalMain(0, new Date(), cnt, jnum, pref, I_Project.JOURNAL_TYPE_AKRUAL, 
                        "Akrual jurnal - "+as.getNama()+" - "+p.getName(), ap.getUserId(), "", ap.getOID(), "", ap.getRegDate());
                    
                    if(oid!=0){
                        //journal debet pada expense
                        DbGl.postJournalDetail(er.getValueIdr(), ap.getDebetCoaId(), 0, ap.getJumlah(),             
                                    0, comp.getBookingCurrencyId(), oid, "Akrual - Debet", 0);
                        //journal credit pada cash
                        DbGl.postJournalDetail(er.getValueIdr(), ap.getCreditCoaId(), ap.getJumlah(), 0,             
                                ap.getJumlah(), comp.getBookingCurrencyId(), oid, "Akrual - Credit", 0);
                        
                    }
                }
        }
        
}
