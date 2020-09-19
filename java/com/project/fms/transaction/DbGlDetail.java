
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
import com.project.payroll.*;
import com.project.fms.activity.*;
import com.project.util.*;
import com.project.fms.master.*;
import com.project.*;

public class DbGlDetail extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_GL_DETAIL = "gl_detail";                

	public static final  int COL_GL_ID = 0;
	public static final  int COL_GL_DETAIL_ID = 1;
	public static final  int COL_COA_ID = 2;
	public static final  int COL_DEBET = 3;
	public static final  int COL_CREDIT = 4;
	public static final  int COL_FOREIGN_CURRENCY_ID = 5;
	public static final  int COL_FOREIGN_CURRENCY_AMOUNT = 6;
	public static final  int COL_MEMO = 7;
	public static final  int COL_BOOKED_RATE = 8;
        
        public static final  int COL_DEPARTMENT_ID = 9;
        public static final  int COL_SECTION_ID = 10;
        public static final  int COL_SUB_SECTION_ID = 11;
        public static final  int COL_JOB_ID = 12;
        
        public static final  int COL_STATUS_TRANSACTION = 13;
        public static final  int COL_CUSTOMER_ID = 14;

	public static final  String[] colNames = {
		"gl_id",		
		"gl_detail_id",
		"coa_id",
		"debet",
		"credit",
		"foreign_currency_id",
		"foreign_currency_amount",
		"memo",
		"booked_rate",
                
                "department_id",
                "section_id",
                "sub_section_id",
                "job_id",
                
                "status_transaksi",
                "customer_id"

	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG,
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_STRING,
		TYPE_FLOAT,
                
                TYPE_LONG,
                TYPE_LONG,
                TYPE_LONG,
                TYPE_LONG,
                
                TYPE_INT,
                TYPE_LONG
	 }; 

	public DbGlDetail(){
	}

	public DbGlDetail(int i) throws CONException { 
		super(new DbGlDetail()); 
	}

	public DbGlDetail(String sOid) throws CONException { 
		super(new DbGlDetail(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbGlDetail(long lOid) throws CONException { 
		super(new DbGlDetail(0)); 
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
		return DB_GL_DETAIL;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbGlDetail().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		GlDetail gldetail = fetchExc(ent.getOID()); 
		ent = (Entity)gldetail; 
		return gldetail.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((GlDetail) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((GlDetail) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static GlDetail fetchExc(long oid) throws CONException{ 
		try{ 
			GlDetail gldetail = new GlDetail();
			DbGlDetail pstGlDetail = new DbGlDetail(oid); 
			gldetail.setOID(oid);

			gldetail.setGlId(pstGlDetail.getlong(COL_GL_ID));
			gldetail.setCoaId(pstGlDetail.getlong(COL_COA_ID));
			gldetail.setDebet(pstGlDetail.getdouble(COL_DEBET));
			gldetail.setCredit(pstGlDetail.getdouble(COL_CREDIT));
			gldetail.setForeignCurrencyId(pstGlDetail.getlong(COL_FOREIGN_CURRENCY_ID));
			gldetail.setForeignCurrencyAmount(pstGlDetail.getdouble(COL_FOREIGN_CURRENCY_AMOUNT));
			gldetail.setMemo(pstGlDetail.getString(COL_MEMO));
			gldetail.setBookedRate(pstGlDetail.getdouble(COL_BOOKED_RATE));
                        
                        gldetail.setDepartmentId(pstGlDetail.getlong(COL_DEPARTMENT_ID));
                        gldetail.setSectionId(pstGlDetail.getlong(COL_SECTION_ID));
                        gldetail.setSubSectionId(pstGlDetail.getlong(COL_SUB_SECTION_ID));
                        gldetail.setJobId(pstGlDetail.getlong(COL_JOB_ID));
                        gldetail.setStatusTransaction(pstGlDetail.getInt(COL_STATUS_TRANSACTION));
                        gldetail.setCustomerId(pstGlDetail.getlong(COL_CUSTOMER_ID));

			return gldetail; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGlDetail(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(GlDetail gldetail) throws CONException{ 
		try{ 
			DbGlDetail pstGlDetail = new DbGlDetail(0);

			pstGlDetail.setLong(COL_GL_ID, gldetail.getGlId());
			pstGlDetail.setLong(COL_COA_ID, gldetail.getCoaId());
			pstGlDetail.setDouble(COL_DEBET, gldetail.getDebet());
			pstGlDetail.setDouble(COL_CREDIT, gldetail.getCredit());
			pstGlDetail.setLong(COL_FOREIGN_CURRENCY_ID, gldetail.getForeignCurrencyId());
			pstGlDetail.setDouble(COL_FOREIGN_CURRENCY_AMOUNT, gldetail.getForeignCurrencyAmount());
			pstGlDetail.setString(COL_MEMO, gldetail.getMemo());
			pstGlDetail.setDouble(COL_BOOKED_RATE, gldetail.getBookedRate());
                        
                        pstGlDetail.setLong(COL_DEPARTMENT_ID, gldetail.getDepartmentId());
                        pstGlDetail.setLong(COL_SECTION_ID, gldetail.getSectionId());
                        pstGlDetail.setLong(COL_SUB_SECTION_ID, gldetail.getSubSectionId());
                        pstGlDetail.setLong(COL_JOB_ID, gldetail.getJobId());
                        
                        pstGlDetail.setInt(COL_STATUS_TRANSACTION, gldetail.getStatusTransaction());
                        pstGlDetail.setLong(COL_CUSTOMER_ID, gldetail.getCustomerId());

			pstGlDetail.insert(); 
			gldetail.setOID(pstGlDetail.getlong(COL_GL_DETAIL_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGlDetail(0),CONException.UNKNOWN); 
		}
		return gldetail.getOID();
	}

	public static long updateExc(GlDetail gldetail) throws CONException{ 
		try{ 
			if(gldetail.getOID() != 0){ 
				DbGlDetail pstGlDetail = new DbGlDetail(gldetail.getOID());

				pstGlDetail.setLong(COL_GL_ID, gldetail.getGlId());
				pstGlDetail.setLong(COL_COA_ID, gldetail.getCoaId());
				pstGlDetail.setDouble(COL_DEBET, gldetail.getDebet());
				pstGlDetail.setDouble(COL_CREDIT, gldetail.getCredit());
				pstGlDetail.setLong(COL_FOREIGN_CURRENCY_ID, gldetail.getForeignCurrencyId());
				pstGlDetail.setDouble(COL_FOREIGN_CURRENCY_AMOUNT, gldetail.getForeignCurrencyAmount());
				pstGlDetail.setString(COL_MEMO, gldetail.getMemo());
				pstGlDetail.setDouble(COL_BOOKED_RATE, gldetail.getBookedRate());
                                
                                pstGlDetail.setLong(COL_DEPARTMENT_ID, gldetail.getDepartmentId());
                                pstGlDetail.setLong(COL_SECTION_ID, gldetail.getSectionId());
                                pstGlDetail.setLong(COL_SUB_SECTION_ID, gldetail.getSubSectionId());
                                pstGlDetail.setLong(COL_JOB_ID, gldetail.getJobId());
                                
                                pstGlDetail.setInt(COL_STATUS_TRANSACTION, gldetail.getStatusTransaction());
                                pstGlDetail.setLong(COL_CUSTOMER_ID, gldetail.getCustomerId());

				pstGlDetail.update(); 
				return gldetail.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGlDetail(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbGlDetail pstGlDetail = new DbGlDetail(oid);
			pstGlDetail.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbGlDetail(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_GL_DETAIL; 
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
				GlDetail gldetail = new GlDetail();
				resultToObject(rs, gldetail);
				lists.add(gldetail);
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

	public static void resultToObject(ResultSet rs, GlDetail gldetail){
		try{
			gldetail.setOID(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_GL_DETAIL_ID]));
			gldetail.setGlId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_GL_ID]));
			gldetail.setCoaId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_COA_ID]));
			gldetail.setDebet(rs.getDouble(DbGlDetail.colNames[DbGlDetail.COL_DEBET]));
			gldetail.setCredit(rs.getDouble(DbGlDetail.colNames[DbGlDetail.COL_CREDIT]));
			gldetail.setForeignCurrencyId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_ID]));
			gldetail.setForeignCurrencyAmount(rs.getDouble(DbGlDetail.colNames[DbGlDetail.COL_FOREIGN_CURRENCY_AMOUNT]));
			gldetail.setMemo(rs.getString(DbGlDetail.colNames[DbGlDetail.COL_MEMO]));
			gldetail.setBookedRate(rs.getDouble(DbGlDetail.colNames[DbGlDetail.COL_BOOKED_RATE]));
                        
                        gldetail.setDepartmentId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]));
                        gldetail.setSectionId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_SECTION_ID]));
                        gldetail.setSubSectionId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_SUB_SECTION_ID]));
                        gldetail.setJobId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_JOB_ID]));
                        
                        gldetail.setStatusTransaction(rs.getInt(DbGlDetail.colNames[DbGlDetail.COL_STATUS_TRANSACTION]));
                        gldetail.setCustomerId(rs.getLong(DbGlDetail.colNames[DbGlDetail.COL_CUSTOMER_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long glDetailId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_GL_DETAIL + " WHERE " + 
						DbGlDetail.colNames[DbGlDetail.COL_GL_ID] + " = " + glDetailId;

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
			String sql = "SELECT COUNT("+ DbGlDetail.colNames[DbGlDetail.COL_GL_DETAIL_ID] + ") FROM " + DB_GL_DETAIL;
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
			  	   GlDetail gldetail = (GlDetail)list.get(ls);
				   if(oid == gldetail.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static void saveAllDetail(Gl gl, Vector listGlDetail){
            
            boolean isActivityBase = false;
            
            if(listGlDetail!=null && listGlDetail.size()>0){
                
                for(int i=0; i<listGlDetail.size(); i++){
                    
                    GlDetail crd = (GlDetail)listGlDetail.get(i);
                    crd.setGlId(gl.getOID());
                    
                    long depId = 0;
                    long sectionId = 0;
                    long subSectionId = 0;
                    long jobId = 0;
                    
                    com.project.fms.master.Coa coa = new com.project.fms.master.Coa();
                    try{
                        coa = DbCoa.fetchExc(crd.getCoaId());
                        
                        //kalau expense pada posisi debet - pengeluaran bertambah
                        if(crd.getDebet()>0 && coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) ||
                        coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE)
                        ){
                            isActivityBase = true;
                        }
                    }
                    catch(Exception e){
                    }
                    
                    long departmentId = crd.getDepartmentId();
                    
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

                    crd.setDepartmentId(departmentId);//coa.getDepartmentId());//depId);
                    crd.setSectionId(coa.getSectionId());//sectionId);
                    crd.setSubSectionId(subSectionId);
                    crd.setJobId(jobId);
                    
                    try{
                        DbGlDetail.insertExc(crd);
                    }
                    catch(Exception e){
                        
                    }
                }                
            }
            
            if(!isActivityBase){
                try{
                    gl = DbGl.fetchExc(gl.getOID());
                    gl.setActivityStatus(I_Project.STATUS_POSTED);
                    gl.setNotActivityBase(1);
                    DbGl.updateExc(gl);
                }
                catch(Exception e){
                }
            }
        }
        
        public static Vector getActivityPredefined(long glOID){
            Vector result = new Vector();
            ActivityPeriod ap = DbActivityPeriod.getOpenPeriod();
            CONResultSet crs = null;
            try{
                String sql = "SELECT c.*, pd.* FROM "+DbCoaActivityBudget.DB_COA_ACTIVITY_BUDGET+" c inner join "+
                             " "+DbGlDetail.DB_GL_DETAIL+" pd on pd."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"=c."+DbCoaActivityBudget.colNames[DbCoaActivityBudget.COL_COA_ID]+
                             " where pd."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"="+glOID+
                             " and c."+DbCoaActivityBudget.colNames[DbCoaActivityBudget.COL_ACTIVITY_PERIOD_ID]+" = "+ap.getOID();
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CoaActivityBudget cab = new CoaActivityBudget();
                    GlDetail ppd = new GlDetail();
                    DbCoaActivityBudget.resultToObject(rs, cab);
                    resultToObject(rs, ppd);
                    Vector v = new Vector();
                    v.add(cab);
                    v.add(ppd);
                    result.add(v);
                }
                
            }
            catch(Exception e){
                
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
        }
        
        public static Vector getGeneralLedger(Date startDate, Date endDate, long oidCoa){
            
            String sql = " select g.*, gd.* from "+DbGl.DB_GL+" g "+
                         " inner join "+DbGlDetail.DB_GL_DETAIL+" gd on "+
                         " gd."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=g."+DbGl.colNames[DbGl.COL_GL_ID]+
                         " where (to_days(g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+")>=to_days('"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"') "+
                         " and to_days(g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+")<=to_days('"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')) and "+
                         " coa_id = "+oidCoa;//+ " and "+
                         //" g."+DbGl.colNames[DbGl.COL_POSTED_STATUS]+"="+DbGl.	;
                         
                         
                         //+" order by g."+DbGl.colNames[DbGl.COL_JOURNAL_NUMBER];
            
            sql = sql + " order by g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+", g."+DbGl.colNames[DbGl.COL_JOURNAL_NUMBER];
            
            System.out.println(sql);
            
            Vector result = new Vector();
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    GlDetail gd = new GlDetail();
                    DbGlDetail.resultToObject(rs, gd);
                    Gl gl = new Gl();
                    DbGl.resultToObject(rs, gl);
                    
                    Vector temp = new Vector();
                    temp.add(gl);
                    temp.add(gd);
                    result.add(temp);
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
        
        public static double getGLOpeningBalance(Date startDate, Coa coa){
            
            double openingBalance = coa.getOpeningBalance();
            
            String sql = "select sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+"), "+
                    " sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")"+
                    " from "+DB_GL_DETAIL+" gd "+
                    " inner join "+DbGl.DB_GL+" g on g."+DbGl.colNames[DbGl.COL_GL_ID]+" = gd."+colNames[COL_GL_ID]+
                    " where gd."+colNames[COL_COA_ID]+" = "+coa.getOID()+
                    " and g."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"<>0 "+
                    " and g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" < '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'";
            
            System.out.println(sql);
            
            double debet = 0;
            double credit = 0;
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                
                while(rs.next()){
                    debet = rs.getDouble(1);
                    credit = rs.getDouble(2);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            System.out.println("openingBalance : "+openingBalance);
            System.out.println("debet : "+debet);
            System.out.println("credit : "+credit);
            
            if(coa.getAccountGroup().equals(I_Project.ACC_GROUP_LIQUID_ASSET) 
            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_FIXED_ASSET) 
            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_ASSET) 
            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES)
            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE)
            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE)            
            ){
                openingBalance = openingBalance + (debet - credit);
                System.out.println("hasil : "+openingBalance);
            }
            else{
                openingBalance = openingBalance + (credit - debet);
                System.out.println("hasil 2 : "+openingBalance);
            }
            
            return openingBalance;
            
        }
        
        public static double getGeneralLedgerOpeningBalance(Date endDate, Coa coa){
            
            //cari opening balance awal tahun
            double openingBalance = 0;//DbCoaOpeningBalance.getOpeningBalance(p,  coa.getOID());
            
            String sqlOpen = " select opening_balance from coa_opening_balance ob "+
                    " inner join periode p on p.periode_id = ob.periode_id "+
                    " where '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"'"+
                    " between p.start_date and p.end_date and ob.coa_id = "+coa.getOID(); 
            
            CONResultSet crsx = null;
            System.out.println("\nget opening sql : "+sqlOpen);
            try{
                crsx = CONHandler.execQueryResult(sqlOpen);
                ResultSet rs = crsx.getResultSet();
                while(rs.next()){
                    openingBalance = rs.getDouble(1);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crsx);
            }
            
            System.out.println("\ncoa code name : "+coa.getCode()+" - "+coa.getName()+", balance : "+openingBalance);
            System.out.println("\nbalance pada opening balance table : "+openingBalance);
            
            //jika tanggal awal searching != dengan tanggal 1 lakukan penghitungan balance dari tanggal 1 sampe tanggal start
            if(endDate.getDate()!=1){
            
                Date startDate = (Date)endDate.clone();
                startDate.setDate(1);
                
                boolean isDebetPosition = false;

                if( coa.getAccountGroup().equals(I_Project.ACC_GROUP_LIQUID_ASSET) ||
                    coa.getAccountGroup().equals(I_Project.ACC_GROUP_FIXED_ASSET) ||
                    coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_ASSET) ||
                    coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES) ||
                    coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) ||
                    coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE)                
                ){
                    isDebetPosition = true;
                }

                String sql = "";
                //debet - credit
                if(isDebetPosition){
                    //sql = "select (sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gd ";

                    sql = " select (sum("+colNames[COL_DEBET]+") - sum("+colNames[COL_CREDIT]+")) as amount from "+DB_GL_DETAIL+" gd "+
                          " inner join "+DbGl.DB_GL+" g on g."+DbGl.colNames[DbGl.COL_GL_ID]+" = gd."+colNames[COL_GL_ID]+
                          " where gd."+colNames[COL_COA_ID]+"="+coa.getOID()+
                          " and "+
                          " (g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" >= '"+JSPFormater.formatDate(startDate,"yyyy-MM-dd")+"'"+
                          " and g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" < '"+JSPFormater.formatDate(endDate,"yyyy-MM-dd")+"')";


                }
                //credit - debet
                else{
                    sql = " select (sum("+colNames[COL_CREDIT]+") - sum("+colNames[COL_DEBET]+")) as amount from "+DB_GL_DETAIL+" gd "+
                          " inner join "+DbGl.DB_GL+" g on g."+DbGl.colNames[DbGl.COL_GL_ID]+" = gd."+colNames[COL_GL_ID]+
                          " where gd."+colNames[COL_COA_ID]+"="+coa.getOID()+
                          " and "+
                          " (g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" >= '"+JSPFormater.formatDate(startDate,"yyyy-MM-dd")+"'"+
                          " and g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" < '"+JSPFormater.formatDate(endDate,"yyyy-MM-dd")+"')";
                }


                System.out.println(sql);

                double sisa = 0;
                CONResultSet crs = null;
                try{
                    crs = CONHandler.execQueryResult(sql);
                    ResultSet rs = crs.getResultSet();
                    while(rs.next()){
                        sisa = rs.getDouble(1);
                    }
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                finally{
                    CONResultSet.close(crs);
                }
                
                openingBalance = openingBalance + sisa;
                
                System.out.println("\nsisa transaksi pada tanggal 1 sampe tanggal awal search = "+sisa);
                
            }  
                    
            return openingBalance;
        }
        
        /*
        public static double getGeneralLedgerOpeningBalance(Date endDate, Coa coa){
            
                        
            //set tanggal start ke 1 januari
            Date startDate = new Date();
            Company comp = DbCompany.getCompany();
            startDate.setMonth(comp.getEndFiscalMonth());
            startDate.setYear(startDate.getYear()-1);
            startDate.setDate(1);
            startDate.setMonth(startDate.getMonth()+1);
            
            String where = "to_days('"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"') >= to_days("+DbPeriode.colNames[DbPeriode.COL_START_DATE]+")"+
                    " and to_days('"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"') <= to_days("+DbPeriode.colNames[DbPeriode.COL_END_DATE]+")";
            
            //cari periode pada awal tahun
            Vector temp = DbPeriode.list(0,0, where, "");
            Periode p = new Periode();
            if(temp!=null && temp.size()>0){
                p = (Periode)temp.get(0);
            }
            
            //cari opening balance awal tahun
            double openingBalance = DbCoaOpeningBalance.getOpeningBalance(p,  coa.getOID());
            
            System.out.println("\ncoa code name : "+coa.getCode()+" - "+coa.getName()+", balance : "+openingBalance);
            
            boolean isDebetPosition = false;
            
            if( coa.getAccountGroup().equals(I_Project.ACC_GROUP_LIQUID_ASSET) ||
                coa.getAccountGroup().equals(I_Project.ACC_GROUP_FIXED_ASSET) ||
                coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_ASSET) ||
                coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES) ||
                coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) ||
                coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE)                
            ){
                isDebetPosition = true;
            }
            
            String sql = "";
            //debet - credit
            if(isDebetPosition){
                sql = "select (sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gd ";
            }
            //credit - debet
            else{
                sql = "select (sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gd."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gd ";
            }
            
            sql = sql + " inner join "+DbGl.DB_GL+" g on "+
                  " gd."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=g."+DbGl.colNames[DbGl.COL_GL_ID]+
                  " where (to_days(g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+")>=to_days('"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"') "+
                  " and to_days(g."+DbGl.colNames[DbGl.COL_TRANS_DATE]+")<to_days('"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')) and "+
                  " coa_id = "+coa.getOID();
            
            System.out.println(sql);
            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    openingBalance = openingBalance + rs.getDouble(1);
                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }
        
            return openingBalance;
        }
        
        */
        
        public static Vector getUnfinishTransaction(long coaId, Vector coas){
            
            String sql = "select * from "+DB_GL_DETAIL+"  gd "+
                    " inner join "+DbGl.DB_GL+" g on g."+DbGl.colNames[DbGl.COL_GL_ID]+"=gd."+colNames[COL_GL_ID]+
                    " where status_transaksi=0";
            
                    if(coaId!=0){
                        sql = sql + " and "+colNames[COL_COA_ID]+"="+coaId;
                    }
                    else{
                        String where = "";
                        if(coas!=null && coas.size()>0){                            
                            for(int i=0; i<coas.size(); i++){
                                Coa coax = (Coa)coas.get(i);
                                where = where+colNames[COL_COA_ID]+"="+coax.getOID()+" or ";
                            }
                            
                            where = "("+where.substring(0,where.length()-3)+")";
                        }
                        sql = sql + " and "+where;
                    }
            
                    sql = sql + " order by g."+DbGl.colNames[DbGl.COL_JOURNAL_NUMBER];
                    
                    System.out.println(sql);
            
            
            Vector result = new Vector();
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Gl gl = new Gl();
                    DbGl.resultToObject(rs, gl);
                    GlDetail gd = new GlDetail();
                    DbGlDetail.resultToObject(rs, gd);
                    
                    Vector temp = new Vector();
                    temp.add(gl);
                    temp.add(gd);
                    
                    result.add(temp);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
        public static Vector getSaldoTransaction(long customerId, long coaId, Vector coas){
            
            String sql = "select "+colNames[COL_CUSTOMER_ID]+", sum("+colNames[COL_CREDIT]+")-sum("+colNames[COL_DEBET]+")"+
                    " from "+DB_GL_DETAIL+" where status_transaksi=1 and "+colNames[COL_CUSTOMER_ID]+"!=0";
            
                    if(customerId!=0){
                        sql = sql + " and "+colNames[COL_CUSTOMER_ID]+"="+customerId;
                    }
            
                    if(coaId!=0){
                        sql = sql + " and "+colNames[COL_COA_ID]+"="+coaId;
                    }
                    else{
                        String where = "";
                        if(coas!=null && coas.size()>0){                            
                            for(int i=0; i<coas.size(); i++){
                                Coa coax = (Coa)coas.get(i);
                                where = where+colNames[COL_COA_ID]+"="+coax.getOID()+" or ";
                            }
                            
                            where = "("+where.substring(0,where.length()-3)+")";
                        }
                        sql = sql + " and "+where;
                    }
            
                    sql = sql + " group by "+colNames[COL_CUSTOMER_ID];
                    
                    System.out.println(sql);
            
            
            Vector result = new Vector();
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    GlDetail gd = new GlDetail();
                    
                    gd.setCustomerId(rs.getLong(1));
                    gd.setDebet(rs.getDouble(2));
                    
                    result.add(gd);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
        public static Vector getSaldoDeposit(long customerId, long coaId, Vector coas){
            
            String sql = "select "+colNames[COL_CUSTOMER_ID]+", sum("+colNames[COL_DEBET]+")-sum("+colNames[COL_CREDIT]+")"+
                    " from "+DB_GL_DETAIL+" where status_transaksi=1 and "+colNames[COL_CUSTOMER_ID]+"!=0";
            
                    if(customerId!=0){
                        sql = sql + " and "+colNames[COL_CUSTOMER_ID]+"="+customerId;
                    }
            
                    if(coaId!=0){
                        sql = sql + " and "+colNames[COL_COA_ID]+"="+coaId;
                    }
                    else{
                        String where = "";
                        if(coas!=null && coas.size()>0){                            
                            for(int i=0; i<coas.size(); i++){
                                Coa coax = (Coa)coas.get(i);
                                where = where+colNames[COL_COA_ID]+"="+coax.getOID()+" or ";
                            }
                            
                            where = "("+where.substring(0,where.length()-3)+")";
                        }
                        sql = sql + " and "+where;
                    }
            
                    sql = sql + " group by "+colNames[COL_CUSTOMER_ID];
                    
                    System.out.println(sql);
            
            
            Vector result = new Vector();
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    GlDetail gd = new GlDetail();
                    
                    gd.setCustomerId(rs.getLong(1));
                    gd.setDebet(rs.getDouble(2));
                    
                    result.add(gd);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
}
