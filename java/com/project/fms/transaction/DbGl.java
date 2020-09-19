
/* Created on 	:  [date] [time] AM/PM 
 * 
 * @author	 :
 * @version	 :
 */

/*******************************************************************
 * Class Description 	: [project description ... ] 
 * Imput Parameters 	: [input parameter ...] 
 * Output 		: [output ...] 
 *******************************************************************/

package com.project.fms.transaction; 

/* package java */ 
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

/* package qdep */
import com.project.util.lang.I_Language;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*; 
import com.project.fms.transaction.*;
import com.project.fms.master.*;
import com.project.*;
import com.project.util.*;
import com.project.util.lang.*;
import com.project.payroll.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;

public class DbGl extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_GL = "gl";

	public static final  int COL_GL_ID = 0;
	public static final  int COL_JOURNAL_NUMBER = 1;
	public static final  int COL_JOURNAL_COUNTER = 2;
	public static final  int COL_JOURNAL_PREFIX = 3;
	public static final  int COL_DATE = 4;
	public static final  int COL_TRANS_DATE = 5;
	public static final  int COL_OPERATOR_ID = 6;
	public static final  int COL_OPERATOR_NAME = 7;
	public static final  int COL_JOURNAL_TYPE = 8;
	public static final  int COL_OWNER_ID = 9;
	public static final  int COL_REF_NUMBER = 10;
	public static final  int COL_CURRENCY_ID = 11;
	public static final  int COL_MEMO = 12;
        public static final  int COL_PERIOD_ID = 13;
        
        public static final  int COL_ACTIVITY_STATUS = 14;
        public static final  int COL_NOT_ACTIVITY_BASE = 15;

	public static final  String[] colNames = {
		"gl_id",		
		"journal_number",
		"journal_counter",
		"journal_prefix",
		"date",
		"trans_date",
		"operator_id",
		"operator_name",
		"journal_type",
		"owner_id",
		"ref_number",
		"currency_id",
		"memo",
                "period_id",
                "activity_status",
                "not_activity_base"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_STRING,
		TYPE_INT,
		TYPE_STRING,
		TYPE_DATE,
		TYPE_DATE,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_INT,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_STRING,
                TYPE_LONG,
                TYPE_STRING,
                TYPE_INT
	 }; 

	public DbGl(){
	}

	public DbGl(int i) throws CONException { 
		super(new DbGl()); 
	}

	public DbGl(String sOid) throws CONException { 
		super(new DbGl(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbGl(long lOid) throws CONException { 
		super(new DbGl(0)); 
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
		return DB_GL;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbGl().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Gl gl = fetchExc(ent.getOID()); 
		ent = (Entity)gl; 
		return gl.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Gl) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Gl) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Gl fetchExc(long oid) throws CONException{ 
		try{ 
			Gl gl = new Gl();
			DbGl pstGl = new DbGl(oid); 
			gl.setOID(oid);

			gl.setJournalNumber(pstGl.getString(COL_JOURNAL_NUMBER));
			gl.setJournalCounter(pstGl.getInt(COL_JOURNAL_COUNTER));
			gl.setJournalPrefix(pstGl.getString(COL_JOURNAL_PREFIX));
			gl.setDate(pstGl.getDate(COL_DATE));
			gl.setTransDate(pstGl.getDate(COL_TRANS_DATE));
			gl.setOperatorId(pstGl.getlong(COL_OPERATOR_ID));
			gl.setOperatorName(pstGl.getString(COL_OPERATOR_NAME));
			gl.setJournalType(pstGl.getInt(COL_JOURNAL_TYPE));
			gl.setOwnerId(pstGl.getlong(COL_OWNER_ID));
			gl.setRefNumber(pstGl.getString(COL_REF_NUMBER));
			gl.setCurrencyId(pstGl.getlong(COL_CURRENCY_ID));
			gl.setMemo(pstGl.getString(COL_MEMO));
                        gl.setPeriodId(pstGl.getlong(COL_PERIOD_ID));
                        gl.setActivityStatus(pstGl.getString(COL_ACTIVITY_STATUS));
                        gl.setNotActivityBase(pstGl.getInt(COL_NOT_ACTIVITY_BASE));

			return gl; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGl(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Gl gl) throws CONException{ 
		try{ 
			DbGl pstGl = new DbGl(0);

			pstGl.setString(COL_JOURNAL_NUMBER, gl.getJournalNumber());
			pstGl.setInt(COL_JOURNAL_COUNTER, gl.getJournalCounter());
			pstGl.setString(COL_JOURNAL_PREFIX, gl.getJournalPrefix());
			pstGl.setDate(COL_DATE, gl.getDate());
			pstGl.setDate(COL_TRANS_DATE, gl.getTransDate());
			pstGl.setLong(COL_OPERATOR_ID, gl.getOperatorId());
			pstGl.setString(COL_OPERATOR_NAME, gl.getOperatorName());
			pstGl.setInt(COL_JOURNAL_TYPE, gl.getJournalType());
			pstGl.setLong(COL_OWNER_ID, gl.getOwnerId());
			pstGl.setString(COL_REF_NUMBER, gl.getRefNumber());
			pstGl.setLong(COL_CURRENCY_ID, gl.getCurrencyId());
			pstGl.setString(COL_MEMO, gl.getMemo());
                        pstGl.setLong(COL_PERIOD_ID, gl.getPeriodId());
                        pstGl.setString(COL_ACTIVITY_STATUS, gl.getActivityStatus());
                        pstGl.setInt(COL_NOT_ACTIVITY_BASE, gl.getNotActivityBase());

			pstGl.insert(); 
			gl.setOID(pstGl.getlong(COL_GL_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGl(0),CONException.UNKNOWN); 
		}
		return gl.getOID();
	}

	public static long updateExc(Gl gl) throws CONException{ 
		try{ 
			if(gl.getOID() != 0){ 
				DbGl pstGl = new DbGl(gl.getOID());

				pstGl.setString(COL_JOURNAL_NUMBER, gl.getJournalNumber());
				pstGl.setInt(COL_JOURNAL_COUNTER, gl.getJournalCounter());
				pstGl.setString(COL_JOURNAL_PREFIX, gl.getJournalPrefix());
				pstGl.setDate(COL_DATE, gl.getDate());
				pstGl.setDate(COL_TRANS_DATE, gl.getTransDate());
				pstGl.setLong(COL_OPERATOR_ID, gl.getOperatorId());
				pstGl.setString(COL_OPERATOR_NAME, gl.getOperatorName());
				pstGl.setInt(COL_JOURNAL_TYPE, gl.getJournalType());
				pstGl.setLong(COL_OWNER_ID, gl.getOwnerId());
				pstGl.setString(COL_REF_NUMBER, gl.getRefNumber());
				pstGl.setLong(COL_CURRENCY_ID, gl.getCurrencyId());
				pstGl.setString(COL_MEMO, gl.getMemo());
                                pstGl.setLong(COL_PERIOD_ID, gl.getPeriodId());
                                pstGl.setString(COL_ACTIVITY_STATUS, gl.getActivityStatus());
                                pstGl.setInt(COL_NOT_ACTIVITY_BASE, gl.getNotActivityBase());

				pstGl.update(); 
				return gl.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGl(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbGl pstGl = new DbGl(oid);
			pstGl.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGl(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_GL; 
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
				Gl gl = new Gl();
				resultToObject(rs, gl);
				lists.add(gl);
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

	public static void resultToObject(ResultSet rs, Gl gl){
		try{
			gl.setOID(rs.getLong(DbGl.colNames[DbGl.COL_GL_ID]));
			gl.setJournalNumber(rs.getString(DbGl.colNames[DbGl.COL_JOURNAL_NUMBER]));
			gl.setJournalCounter(rs.getInt(DbGl.colNames[DbGl.COL_JOURNAL_COUNTER]));
			gl.setJournalPrefix(rs.getString(DbGl.colNames[DbGl.COL_JOURNAL_PREFIX]));
			gl.setDate(rs.getDate(DbGl.colNames[DbGl.COL_DATE]));
			gl.setTransDate(rs.getDate(DbGl.colNames[DbGl.COL_TRANS_DATE]));
			gl.setOperatorId(rs.getLong(DbGl.colNames[DbGl.COL_OPERATOR_ID]));
			gl.setOperatorName(rs.getString(DbGl.colNames[DbGl.COL_OPERATOR_NAME]));
			gl.setJournalType(rs.getInt(DbGl.colNames[DbGl.COL_JOURNAL_TYPE]));
			gl.setOwnerId(rs.getLong(DbGl.colNames[DbGl.COL_OWNER_ID]));
			gl.setRefNumber(rs.getString(DbGl.colNames[DbGl.COL_REF_NUMBER]));
			gl.setCurrencyId(rs.getLong(DbGl.colNames[DbGl.COL_CURRENCY_ID]));
			gl.setMemo(rs.getString(DbGl.colNames[DbGl.COL_MEMO]));
                        gl.setPeriodId(rs.getLong(DbGl.colNames[DbGl.COL_PERIOD_ID]));
                        gl.setActivityStatus(rs.getString(DbGl.colNames[DbGl.COL_ACTIVITY_STATUS]));
                        gl.setNotActivityBase(rs.getInt(DbGl.colNames[DbGl.COL_NOT_ACTIVITY_BASE]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long glId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_GL + " WHERE " + 
						DbGl.colNames[DbGl.COL_GL_ID] + " = " + glId;

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
			String sql = "SELECT COUNT("+ DbGl.colNames[DbGl.COL_GL_ID] + ") FROM " + DB_GL;
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
			  	   Gl gl = (Gl)list.get(ls);
				   if(oid == gl.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        public static long postJournalMain(long currencyId, Date dt, int counter, String number, String prefix, int journalType, String memo,
            long operatorId, String operatorName, long ownerId, String refNumber, Date transDate){
                
                Periode openPeriod = DbPeriode.getOpenPeriod();
                
                if(transDate.before(openPeriod.getStartDate())){
                    transDate = openPeriod.getStartDate();
                }   
                
                if(transDate.after(openPeriod.getEndDate())){
                    transDate = openPeriod.getEndDate();
                }
                
                System.out.println("\nposting journal main ... transDate : "+transDate);
                
                Gl gl = new Gl();
                
                gl.setPeriodId(openPeriod.getOID());
		gl.setCurrencyId(currencyId);
                gl.setDate(dt);
                gl.setJournalCounter(counter);	
		gl.setJournalNumber(number);                
                gl.setJournalPrefix(prefix);                
                gl.setTransDate(transDate);
                gl.setOperatorId(operatorId);
                gl.setOperatorName(operatorName);
                gl.setJournalType(journalType);
                gl.setOwnerId(ownerId);
                gl.setRefNumber(refNumber);                
                gl.setMemo(memo);
                
                long oid = 0;
                try{
                    oid = DbGl.insertExc(gl);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                
                return oid;
            
        }
        
        public static long postJournalDetail(double bookedRate, long coaId, double credit, double debet,             
            double fCurrAmount, long currId, long glId, String memo, long departmentId){
                
                System.out.println("    \n\n** in posting journal detail ===");
                System.out.println("    ** in posting journal detail ===");
                System.out.println("    ** credit : "+credit);
                System.out.println("    ** debet : "+debet);
                
                GlDetail gldetail = new GlDetail();
                gldetail.setGlId(glId);
                gldetail.setCoaId(coaId);
                gldetail.setDebet(debet);
                gldetail.setCredit(credit);
                gldetail.setForeignCurrencyId(currId);
                gldetail.setForeignCurrencyAmount(fCurrAmount);
                gldetail.setMemo(memo);
                gldetail.setBookedRate(bookedRate);
                
                long depId = 0;
                long sectionId = 0;
                long subSectionId = 0;
                long jobId = 0;
                
                if(departmentId!=0){
                    try{
                        Department d = DbDepartment.fetchExc(departmentId);
                        
                        switch(d.getLevel()){
                            case 0 : 
                                depId = departmentId;
                                break;
                            case 1 :
                                sectionId = departmentId;
                                depId = d.getRefId();
                                break;
                            case 2 :
                                subSectionId = departmentId;
                                sectionId = d.getRefId();
                                d = DbDepartment.fetchExc(sectionId);
                                depId = d.getRefId();
                                break;
                            case 3 :                                
                                jobId = departmentId;
                                subSectionId = d.getRefId();
                                d = DbDepartment.fetchExc(subSectionId);
                                sectionId = d.getRefId();
                                d = DbDepartment.fetchExc(sectionId);
                                depId = d.getRefId();
                                break;
                            
                        }
                        
                    }
                    catch(Exception ex){
                        System.out.println(ex.toString());
                    }
                }
                
                
                Coa coa = new Coa();
                try{
                    coa = DbCoa.fetchExc(gldetail.getCoaId());
                }
                catch(Exception e){
                }
                
                
                gldetail.setDepartmentId(coa.getDepartmentId());//depId);
                gldetail.setSectionId(coa.getSectionId());//sectionId);
                gldetail.setSubSectionId(subSectionId);
                gldetail.setJobId(jobId);
                                
                long oid = 0;
                
                try{
                    oid = DbGlDetail.insertExc(gldetail);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                
                System.out.println("    ** end posting journal oid : "+oid+" ===");
            
                return oid;
        }
        
        public static int getNextCounter(){
                int result = 0;
                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max(journal_counter) from "+DB_GL+" where "+
                        " journal_prefix='"+getNumberPrefix()+"' ";
                    
                    System.out.println(sql);
                    
                    dbrs = CONHandler.execQueryResult(sql);
                    ResultSet rs = dbrs.getResultSet();
                    while(rs.next()){
                        result = rs.getInt(1);
                    }
                    
                    if(result==0){
                        result = result + 1;
                    }
                    else{
                        result = result + 1;
                    }
                    
                }
                catch(Exception e){
                    
                }
                finally{
                    CONResultSet.close(dbrs);
                }
                
                return result;
        }
        
        public static String getNumberPrefix(){
            
                Company sysCompany = DbCompany.getCompany();
                Location sysLocation = DbLocation.getLocationById(sysCompany.getSystemLocation());

                String code = sysLocation.getCode();//DbSystemProperty.getValueByName("LOCATION_CODE");
                code = code + sysCompany.getGeneralLedgerCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");
                
                code = code + JSPFormater.formatDate(new Date(), "MMyy");
                
                return code;
        }
        
        
        public static String getNextNumber(int ctr){
            
                String code = getNumberPrefix();
                
                if(ctr<10){
                    code = code + "000"+ctr;
                }
                else if(ctr<100){
                    code = code + "00"+ctr;
                }
                else if(ctr<1000){
                    code = code + "0"+ctr;
                }
                else{
                    code = code + ctr;
                }
                
                return code;
                
        }
        
        public static void postActivityStatus(long oidFlag, long oidGl){
            try{
                
                Gl pp = DbGl.fetchExc(oidGl);
                if(oidFlag==0){
                    pp.setActivityStatus(I_Project.STATUS_NOT_POSTED);
                }
                else{
                    pp.setActivityStatus(I_Project.STATUS_POSTED);
                }
                
                DbGl.updateExc(pp);
                
            }
            catch(Exception e){
                
            }
            
        }
        
        public static double getAmountByCoa(long coaId, Periode p, long glId){
            double result = 0;                      
            CONResultSet crs = null;
                                 
            //run query
            try{                
                String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glId;
                
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);                   
                }    
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }         
        
        public static double getAmountByCoaCD(long coaId, Periode p, long glId){
            double result = 0;                      
            CONResultSet crs = null;
                                 
            //run query
            try{                
                String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glId;
                
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);                   
                }    
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        //digunakan saat proses singkronisasi data dilakukan
        public static void synchGLPeriodId(){
        
            CONResultSet crs = null;
            Gl gl = new Gl();
            try{
                String sql = " select * from "+DbGl.DB_GL+" g "+
                             " where g."+DbGl.colNames[DbGl.COL_PERIOD_ID]+
                             " not in (select p."+DbPeriode.colNames[DbPeriode.COL_PERIODE_ID]+
                             " from "+DbPeriode.DB_PERIODE+" p) limit 0,1";
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){                    
                    DbGl.resultToObject(rs, gl);                    
                }                
                
                if(gl.getOID()!=0){
                    updateSynchGlPeriodId(gl);
                    synchGLPeriodId();
                }
                
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }
            
        }
        
        public static void updateSynchGlPeriodId(Gl gl){
            
            Date trDate = gl.getTransDate();
            String where = " to_days("+DbPeriode.colNames[DbPeriode.COL_START_DATE]+")<=to_days('"+JSPFormater.formatDate(trDate, "yyyy-MM-dd")+"')"+
                           " and to_days("+DbPeriode.colNames[DbPeriode.COL_END_DATE]+")>=to_days('"+JSPFormater.formatDate(trDate, "yyyy-MM-dd")+"')";
            
            Vector v = DbPeriode.list(0,0,  where, "");
            if(v!=null && v.size()>0){
                Periode p = (Periode)v.get(0);
                String sql = "update "+DbGl.DB_GL+" set "+colNames[COL_PERIOD_ID]+"="+p.getOID()+" where "+colNames[COL_PERIOD_ID]+"="+gl.getPeriodId();
                try{
                    CONHandler.execUpdate(sql);
                }
                catch(Exception e){
                    
                }
            }            
            
        }
        
        
        public static void optimizeJournal(long glOID){
            
            String sql = " select sum("+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")"+
                         ", sum("+DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_AMOUNT]+")"+
                         ", "+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+
                         ", "+DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_ID]+
                         ", "+DbGlDetail.colNames[DbGlDetail.COL_BOOKED_RATE]+
                         " from "+DbGlDetail.DB_GL_DETAIL+
                         " where "+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glOID+
                         " and "+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+">0"+
                         " group by "+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+
                         " , "+DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_ID];
            
            System.out.println(sql);
            
            CONResultSet crs = null;
            Vector temp = new Vector();
            try{
                crs = CONHandler.execQueryResult(sql); 
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    GlDetail gd = new GlDetail();
                    gd.setGlId(glOID);
                    gd.setDebet(rs.getDouble(1));
                    gd.setForeignCurrencyAmount(rs.getDouble(2));                    
                    gd.setCoaId(rs.getLong(3));
                    gd.setForeignCurrencyId(rs.getLong(4));
                    gd.setBookedRate(rs.getDouble(5));
                    gd.setCredit(0);
                    temp.add(gd);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            Vector jDetailDebet = new Vector();
            
            if(temp!=null && temp.size()>0){
                
                Vector jDebetDetails = DbGlDetail.list(0,0, DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glOID+" and "+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+">0", "");
                if(jDebetDetails!=null && jDebetDetails.size()>0){
                    for(int x=0; x<temp.size(); x++){
                        GlDetail gdx = (GlDetail)temp.get(x);
                        String memo = "";
                        for(int i=0; i<jDebetDetails.size(); i++){
                            GlDetail gd = (GlDetail)jDebetDetails.get(i);
                            if(gd.getCoaId()==gdx.getCoaId() && gd.getForeignCurrencyId()==gdx.getForeignCurrencyId()){
                                if(gd.getMemo()!=null && gd.getMemo().length()>0){
                                    memo = memo + gd.getMemo()+"("+JSPFormater.formatNumber(gd.getDebet(),"#,###.#")+"), ";
                                }
                            }
                        }
                        
                        if(memo.length()>0){
                            memo = memo.substring(0, memo.length()-2);
                        }
                        
                        gdx.setMemo(memo);
                        
                        jDetailDebet.add(gdx);
                        
                    }
                }
            }
            
            //process credit ===========================
            
            sql = " select sum("+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")"+
                         ", sum("+DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_AMOUNT]+")"+
                         ", "+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+
                         ", "+DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_ID]+
                         ", "+DbGlDetail.colNames[DbGlDetail.COL_BOOKED_RATE]+
                         " from "+DbGlDetail.DB_GL_DETAIL+
                         " where "+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glOID+
                         " and "+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+">0"+
                         " group by "+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+
                         " , "+DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_ID];
            
            System.out.println(sql);
            
            CONResultSet crsx = null;
            temp = new Vector();
            try{
                crsx = CONHandler.execQueryResult(sql); 
                ResultSet rs = crsx.getResultSet();
                while(rs.next()){
                    GlDetail gd = new GlDetail();
                    gd.setGlId(glOID);
                    gd.setCredit(rs.getDouble(1));
                    gd.setForeignCurrencyAmount(rs.getDouble(2));                    
                    gd.setCoaId(rs.getLong(3));
                    gd.setForeignCurrencyId(rs.getLong(4));
                    gd.setBookedRate(rs.getDouble(5));
                    gd.setDebet(0);
                    temp.add(gd);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crsx);
            }
            
            Vector jDetailCredit = new Vector();
            
            if(temp!=null && temp.size()>0){
                
                Vector jCreditDetails = DbGlDetail.list(0,0, DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glOID+" and "+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+">0", "");
                if(jCreditDetails!=null && jCreditDetails.size()>0){
                    for(int x=0; x<temp.size(); x++){
                        GlDetail gdx = (GlDetail)temp.get(x);
                        String memo = "";
                        for(int i=0; i<jCreditDetails.size(); i++){
                            GlDetail gd = (GlDetail)jCreditDetails.get(i);
                            if(gd.getCoaId()==gdx.getCoaId() && gd.getForeignCurrencyId()==gdx.getForeignCurrencyId()){
                                if(gd.getMemo()!=null && gd.getMemo().length()>0){
                                    memo = memo + gd.getMemo()+" ("+JSPFormater.formatNumber(gd.getCredit(),"#,###.#")+"), ";
                                }
                            }
                        }
                        
                        if(memo.length()>0){
                            memo = memo.substring(0, memo.length()-2);
                        }
                        
                        gdx.setMemo(memo);
                        
                        jDetailCredit.add(gdx);
                        
                    }
                }
            }
            
            
            //delete journal detail
            try{
                CONHandler.execUpdate("delete from "+DbGlDetail.DB_GL_DETAIL+" where "+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glOID);
            }
            catch(Exception e){
            }
            
            //insert yang baru
            for(int i=0; i<jDetailDebet.size(); i++){
                GlDetail gdx = (GlDetail)jDetailDebet.get(i);
                try{
                    DbGlDetail.insertExc(gdx);
                }
                catch(Exception e){
                }
            }
            
            for(int i=0; i<jDetailCredit.size(); i++){
                GlDetail gdx = (GlDetail)jDetailCredit.get(i);
                try{
                    DbGlDetail.insertExc(gdx);
                }
                catch(Exception e){
                }
            }
            
            
        }
        
        
        
        
        
}
