
package com.project.fms.transaction; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.fms.transaction.*;  
import com.project.util.lang.*;
import com.project.util.*;
import com.project.system.*;
import com.project.fms.master.*;
import com.project.*;
import com.project.general.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;

public class DbCashReceive extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_CASH_RECEIVE = "cash_receive";

	public static final  int COL_CASH_RECEIVE_ID = 0;
	public static final  int COL_COA_ID = 1;
	public static final  int COL_JOURNAL_NUMBER = 2;
	public static final  int COL_JOURNAL_COUNTER = 3;
	public static final  int COL_JOURNAL_PREFIX = 4;
	public static final  int COL_DATE = 5;
	public static final  int COL_TRANS_DATE = 6;
	public static final  int COL_MEMO = 7;
	public static final  int COL_OPERATOR_ID = 8;
	public static final  int COL_OPERATOR_NAME = 9;
	public static final  int COL_AMOUNT = 10;
	public static final  int COL_RECEIVE_FROM_ID = 11;
	public static final  int COL_RECEIVE_FROM_NAME = 12;
        
    public static final  int COL_TYPE       = 13;
    public static final  int COL_CUSTOMER_ID = 14;
    
    public static final  int COL_IN_OUT = 15;
    
    public static final  int COL_POSTED_STATUS = 16;
    public static final  int COL_POSTED_BY_ID = 17;
    public static final  int COL_POSTED_DATE = 18;
    public static final  int COL_EFFECTIVE_DATE = 19;    

	public static final  String[] colNames = {
		"cash_receive_id",
		"coa_id",
		"journal_number",
		"journal_counter",
		"journal_prefix",
		"date",
		"trans_date",
		"memo",
		"operator_id",
		"operator_name",
		"amount",
		"receive_from_id",
		"receive_from_name",
        "type",
        "customer_id",
        "in_out",
        
        "posted_status",
        "posted_by_id",
        "posted_date",
        "effective_date"
        	
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_INT,
		TYPE_STRING,
		TYPE_DATE,
		TYPE_DATE,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_FLOAT,
		TYPE_LONG,
		TYPE_STRING,
	    TYPE_INT,
	    TYPE_LONG,
	    TYPE_INT,
	    TYPE_INT,
	    TYPE_LONG,
	    TYPE_DATE,
	    TYPE_DATE
	 }; 
         
         
     public static final int TYPE_CASH_INCOME = 0;
     public static final int TYPE_CASH_LIABILITY = 1;
     public static final int TYPE_CASH_LIABILITY_PAYMENT = 2;         
     public static final int TYPE_BYMHD = 3;
     public static final int TYPE_BYMHD_NEW = 4;
     public static final int TYPE_DP = 5;
     public static final int TYPE_DP_RETURN = 6;

	public DbCashReceive(){
	}

	public DbCashReceive(int i) throws CONException { 
		super(new DbCashReceive()); 
	}

	public DbCashReceive(String sOid) throws CONException { 
		super(new DbCashReceive(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbCashReceive(long lOid) throws CONException { 
		super(new DbCashReceive(0)); 
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
		return DB_CASH_RECEIVE;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbCashReceive().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		CashReceive cashreceive = fetchExc(ent.getOID()); 
		ent = (Entity)cashreceive; 
		return cashreceive.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((CashReceive) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((CashReceive) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static CashReceive fetchExc(long oid) throws CONException{ 
		try{ 
			CashReceive cashreceive = new CashReceive();
			DbCashReceive pstCashReceive = new DbCashReceive(oid); 
			cashreceive.setOID(oid);

			cashreceive.setCoaId(pstCashReceive.getlong(COL_COA_ID));
			cashreceive.setJournalNumber(pstCashReceive.getString(COL_JOURNAL_NUMBER));
			cashreceive.setJournalCounter(pstCashReceive.getInt(COL_JOURNAL_COUNTER));
			cashreceive.setJournalPrefix(pstCashReceive.getString(COL_JOURNAL_PREFIX));
			cashreceive.setDate(pstCashReceive.getDate(COL_DATE));
			cashreceive.setTransDate(pstCashReceive.getDate(COL_TRANS_DATE));
			cashreceive.setMemo(pstCashReceive.getString(COL_MEMO));
			cashreceive.setOperatorId(pstCashReceive.getlong(COL_OPERATOR_ID));
			cashreceive.setOperatorName(pstCashReceive.getString(COL_OPERATOR_NAME));
			cashreceive.setAmount(pstCashReceive.getdouble(COL_AMOUNT));
			cashreceive.setReceiveFromId(pstCashReceive.getlong(COL_RECEIVE_FROM_ID));
			cashreceive.setReceiveFromName(pstCashReceive.getString(COL_RECEIVE_FROM_NAME));
                        
            cashreceive.setType(pstCashReceive.getInt(COL_TYPE));
            cashreceive.setCustomerId(pstCashReceive.getlong(COL_CUSTOMER_ID));
            cashreceive.setInOut(pstCashReceive.getInt(COL_IN_OUT));
            
            cashreceive.setPostedStatus(pstCashReceive.getInt(COL_POSTED_STATUS));
            cashreceive.setPostedById(pstCashReceive.getlong(COL_POSTED_BY_ID));
            cashreceive.setPostedDate(pstCashReceive.getDate(COL_POSTED_DATE));
            cashreceive.setEffectiveDate(pstCashReceive.getDate(COL_EFFECTIVE_DATE));

			return cashreceive; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCashReceive(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(CashReceive cashreceive) throws CONException{ 
		try{ 
			DbCashReceive pstCashReceive = new DbCashReceive(0);

			pstCashReceive.setLong(COL_COA_ID, cashreceive.getCoaId());
			pstCashReceive.setString(COL_JOURNAL_NUMBER, cashreceive.getJournalNumber());
			pstCashReceive.setInt(COL_JOURNAL_COUNTER, cashreceive.getJournalCounter());
			pstCashReceive.setString(COL_JOURNAL_PREFIX, cashreceive.getJournalPrefix());
			pstCashReceive.setDate(COL_DATE, cashreceive.getDate());
			pstCashReceive.setDate(COL_TRANS_DATE, cashreceive.getTransDate());
			pstCashReceive.setString(COL_MEMO, cashreceive.getMemo());
			pstCashReceive.setLong(COL_OPERATOR_ID, cashreceive.getOperatorId());
			pstCashReceive.setString(COL_OPERATOR_NAME, cashreceive.getOperatorName());
			pstCashReceive.setDouble(COL_AMOUNT, cashreceive.getAmount());
			pstCashReceive.setLong(COL_RECEIVE_FROM_ID, cashreceive.getReceiveFromId());
			pstCashReceive.setString(COL_RECEIVE_FROM_NAME, cashreceive.getReceiveFromName());
                        
            pstCashReceive.setInt(COL_TYPE, cashreceive.getType());
            pstCashReceive.setLong(COL_CUSTOMER_ID, cashreceive.getCustomerId());
            pstCashReceive.setInt(COL_IN_OUT, cashreceive.getInOut());
            
            pstCashReceive.setInt(COL_POSTED_STATUS, cashreceive.getPostedStatus());
            pstCashReceive.setLong(COL_POSTED_BY_ID, cashreceive.getPostedById());
            pstCashReceive.setDate(COL_POSTED_DATE, cashreceive.getPostedDate());
            pstCashReceive.setDate(COL_EFFECTIVE_DATE, cashreceive.getEffectiveDate());

			pstCashReceive.insert(); 
			cashreceive.setOID(pstCashReceive.getlong(COL_CASH_RECEIVE_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCashReceive(0),CONException.UNKNOWN); 
		}
		return cashreceive.getOID();
	}

	public static long updateExc(CashReceive cashreceive) throws CONException{ 
		try{ 
			if(cashreceive.getOID() != 0){ 
				DbCashReceive pstCashReceive = new DbCashReceive(cashreceive.getOID());

				pstCashReceive.setLong(COL_COA_ID, cashreceive.getCoaId());
				pstCashReceive.setString(COL_JOURNAL_NUMBER, cashreceive.getJournalNumber());
				pstCashReceive.setInt(COL_JOURNAL_COUNTER, cashreceive.getJournalCounter());
				pstCashReceive.setString(COL_JOURNAL_PREFIX, cashreceive.getJournalPrefix());
				pstCashReceive.setDate(COL_DATE, cashreceive.getDate());
				pstCashReceive.setDate(COL_TRANS_DATE, cashreceive.getTransDate());
				pstCashReceive.setString(COL_MEMO, cashreceive.getMemo());
				pstCashReceive.setLong(COL_OPERATOR_ID, cashreceive.getOperatorId());
				pstCashReceive.setString(COL_OPERATOR_NAME, cashreceive.getOperatorName());
				pstCashReceive.setDouble(COL_AMOUNT, cashreceive.getAmount());
				pstCashReceive.setLong(COL_RECEIVE_FROM_ID, cashreceive.getReceiveFromId());
				pstCashReceive.setString(COL_RECEIVE_FROM_NAME, cashreceive.getReceiveFromName());
                                
                pstCashReceive.setInt(COL_TYPE, cashreceive.getType());
                pstCashReceive.setLong(COL_CUSTOMER_ID, cashreceive.getCustomerId());
                pstCashReceive.setInt(COL_IN_OUT, cashreceive.getInOut());
                                
                pstCashReceive.setInt(COL_POSTED_STATUS, cashreceive.getPostedStatus());
	            pstCashReceive.setLong(COL_POSTED_BY_ID, cashreceive.getPostedById());
	            pstCashReceive.setDate(COL_POSTED_DATE, cashreceive.getPostedDate());                
	            pstCashReceive.setDate(COL_EFFECTIVE_DATE, cashreceive.getEffectiveDate());

				pstCashReceive.update(); 
				return cashreceive.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCashReceive(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbCashReceive pstCashReceive = new DbCashReceive(oid);
			pstCashReceive.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCashReceive(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_CASH_RECEIVE; 
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
				CashReceive cashreceive = new CashReceive();
				resultToObject(rs, cashreceive);
				lists.add(cashreceive);
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

	private static void resultToObject(ResultSet rs, CashReceive cashreceive){
		try{
			cashreceive.setOID(rs.getLong(DbCashReceive.colNames[DbCashReceive.COL_CASH_RECEIVE_ID]));
			cashreceive.setCoaId(rs.getLong(DbCashReceive.colNames[DbCashReceive.COL_COA_ID]));
			cashreceive.setJournalNumber(rs.getString(DbCashReceive.colNames[DbCashReceive.COL_JOURNAL_NUMBER]));
			cashreceive.setJournalCounter(rs.getInt(DbCashReceive.colNames[DbCashReceive.COL_JOURNAL_COUNTER]));
			cashreceive.setJournalPrefix(rs.getString(DbCashReceive.colNames[DbCashReceive.COL_JOURNAL_PREFIX]));
			cashreceive.setDate(rs.getDate(DbCashReceive.colNames[DbCashReceive.COL_DATE]));
			cashreceive.setTransDate(rs.getDate(DbCashReceive.colNames[DbCashReceive.COL_TRANS_DATE]));
			cashreceive.setMemo(rs.getString(DbCashReceive.colNames[DbCashReceive.COL_MEMO]));
			cashreceive.setOperatorId(rs.getLong(DbCashReceive.colNames[DbCashReceive.COL_OPERATOR_ID]));
			cashreceive.setOperatorName(rs.getString(DbCashReceive.colNames[DbCashReceive.COL_OPERATOR_NAME]));
			cashreceive.setAmount(rs.getDouble(DbCashReceive.colNames[DbCashReceive.COL_AMOUNT]));
			cashreceive.setReceiveFromId(rs.getLong(DbCashReceive.colNames[DbCashReceive.COL_RECEIVE_FROM_ID]));
			cashreceive.setReceiveFromName(rs.getString(DbCashReceive.colNames[DbCashReceive.COL_RECEIVE_FROM_NAME]));
                        
            cashreceive.setType(rs.getInt(DbCashReceive.colNames[DbCashReceive.COL_TYPE]));
            cashreceive.setCustomerId(rs.getLong(DbCashReceive.colNames[DbCashReceive.COL_CUSTOMER_ID]));
            cashreceive.setInOut(rs.getInt(DbCashReceive.colNames[DbCashReceive.COL_IN_OUT]));

			cashreceive.setPostedStatus(rs.getInt(DbCashReceive.colNames[DbCashReceive.COL_POSTED_STATUS]));
            cashreceive.setPostedById(rs.getLong(DbCashReceive.colNames[DbCashReceive.COL_POSTED_BY_ID]));
            cashreceive.setPostedDate(rs.getDate(DbCashReceive.colNames[DbCashReceive.COL_POSTED_DATE]));
            cashreceive.setEffectiveDate(rs.getDate(DbCashReceive.colNames[DbCashReceive.COL_EFFECTIVE_DATE]));
            
		}catch(Exception e){ }
	}

	public static boolean checkOID(long cashReceiveId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_CASH_RECEIVE + " WHERE " + 
						DbCashReceive.colNames[DbCashReceive.COL_CASH_RECEIVE_ID] + " = " + cashReceiveId;

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
			String sql = "SELECT COUNT("+ DbCashReceive.colNames[DbCashReceive.COL_CASH_RECEIVE_ID] + ") FROM " + DB_CASH_RECEIVE;
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
			  	   CashReceive cashreceive = (CashReceive)list.get(ls);
				   if(oid == cashreceive.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        public static int getNextCounter(int type){
                int result = 0;
                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max(journal_counter) from "+DB_CASH_RECEIVE+" where "+
                        " journal_prefix='"+getNumberPrefix(type)+"' ";
                    
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
        
        public static String getNumberPrefix(int type){
            
                Company sysCompany = DbCompany.getCompany();
                Location sysLocation = DbLocation.getLocationById(sysCompany.getSystemLocation());

                String code = sysLocation.getCode();//DbSystemProperty.getValueByName("LOCATION_CODE");
                
                if(type==TYPE_CASH_INCOME){
                    code = code + DbSystemProperty.getValueByName("JOURNAL_CODE_CASH_RECEIPT");
                }
                else if(type==TYPE_CASH_LIABILITY){
                    code = code + DbSystemProperty.getValueByName("JOURNAL_CODE_TITIPAN");
                }
                else if(type==TYPE_CASH_LIABILITY_PAYMENT){
                    code = code + DbSystemProperty.getValueByName("JOURNAL_CODE_TITIPAN_PAYMENT");
                }                
                else if(type==TYPE_BYMHD_NEW){
                    code = code + DbSystemProperty.getValueByName("JOURNAL_CODE_BYMHD");
                }
                else if(type==TYPE_BYMHD){
                    code = code + DbSystemProperty.getValueByName("JOURNAL_CODE_BYMHD_PAYMENT");
                }                
                
                code = code + JSPFormater.formatDate(new Date(), "MMyy");
                
                return code;
        }
        
        
        public static String getNextNumber(int ctr, int type){
            
                String code = getNumberPrefix(type);
                
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
        
        
        //POSTING ke journal
        public static void postJournal(CashReceive cr, Vector details, long userId){
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                try{
                    cr = DbCashReceive.fetchExc(cr.getOID());
                }
                catch(Exception e){
                    
                }
                
                if(cr.getOID()!=0 && details!=null && details.size()>0){
                    long oid = DbGl.postJournalMain(0, cr.getDate(), cr.getJournalCounter(), cr.getJournalNumber(), cr.getJournalPrefix(), I_Project.JOURNAL_TYPE_CASH_RECEIVE, 
                        cr.getMemo(), cr.getOperatorId(), cr.getOperatorName(), cr.getOID(), "", cr.getTransDate());
                    
                    if(oid!=0){
                        for(int i=0; i<details.size(); i++){
                            CashReceiveDetail crd = (CashReceiveDetail)details.get(i);
                            //journal credit pada pendapatan
                            DbGl.postJournalDetail(crd.getBookedRate(), crd.getCoaId(), crd.getAmount(), 0,             
                                    crd.getForeignAmount(), crd.getForeignCurrencyId(), oid, crd.getMemo(), 0); //non departmenttal item, department id = 0
                        }
                        
                        //journal debet pada cash 
                        DbGl.postJournalDetail(er.getValueIdr(), cr.getCoaId(), 0, cr.getAmount(),             
                                    0, comp.getBookingCurrencyId(), oid, cr.getMemo(), 0);//non departmenttal item, department id = 0
                    }
                    
                    //update status
                    if(oid!=0){
                    	try{
                    	
	                    	cr.setPostedStatus(1);
	                    	cr.setPostedById(userId);
	                    	cr.setPostedDate(new Date());
	                    	
	                    	Date dt = new Date();
	                    	String where = "'"+JSPFormater.formatDate(dt, "yyyy-MM-dd")+"' between "+
	                    		DbPeriode.colNames[DbPeriode.COL_START_DATE] +" and "+
	                    		DbPeriode.colNames[DbPeriode.COL_END_DATE];
	                    		
	                    	Vector temp = DbPeriode.list(0,0, where, "");	
	                    	if(temp!=null && temp.size()>0){
	                    		cr.setEffectiveDate(new Date());
	                    	}
	                    	else{                    
	                    		Periode per = DbPeriode.getOpenPeriod();
	                    		cr.setEffectiveDate(per.getEndDate());
	                    	}                                   	
	                    	
	                    	DbCashReceive.updateExc(cr);
                    	}
                    	catch(Exception e){
                    		
                    	}
                    }
                }
        }
        
        
        //POSTING ke journal
        //jurnal hutang pada cash
        public static void postJournalKembaliTitipan(CashReceive cr, Vector details){
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                try{
                    cr = DbCashReceive.fetchExc(cr.getOID());
                }
                catch(Exception e){
                    
                }
                
                if(cr.getOID()!=0 && details!=null && details.size()>0){
                    long oid = DbGl.postJournalMain(0, cr.getDate(), cr.getJournalCounter(), cr.getJournalNumber(), cr.getJournalPrefix(), I_Project.JOURNAL_TYPE_CASH_RECEIVE, 
                        cr.getMemo(), cr.getOperatorId(), cr.getOperatorName(), cr.getOID(), "", cr.getTransDate());
                    
                    if(oid!=0){
                        
                        for(int i=0; i<details.size(); i++){
                            CashReceiveDetail crd = (CashReceiveDetail)details.get(i);
                            //journal debet pada utang
                            DbGl.postJournalDetail(crd.getBookedRate(), crd.getCoaId(), 0, crd.getAmount(),             
                                    crd.getForeignAmount(), crd.getForeignCurrencyId(), oid, crd.getMemo(), 0); //non departmenttal item, department id = 0
                        }
                        
                        //journal credit pada cash 
                        DbGl.postJournalDetail(er.getValueIdr(), cr.getCoaId(), cr.getAmount(), 0,             
                                    0, comp.getBookingCurrencyId(), oid, cr.getMemo(), 0);//non departmenttal item, department id = 0
                        
                        
                        
                        
                    }
                }
        }
        
        
        //POSTING ke journal
        //jurnal expense pada hutang
        public static void postJournalBYHMDNew(CashReceive cr, Vector details){
                
                Company comp = DbCompany.getCompany();
                
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                try{
                    cr = DbCashReceive.fetchExc(cr.getOID());
                }
                catch(Exception e){
                    
                }
                
                if(cr.getOID()!=0 && details!=null && details.size()>0){
                    long oid = DbGl.postJournalMain(0, cr.getDate(), cr.getJournalCounter(), cr.getJournalNumber(), cr.getJournalPrefix(), I_Project.JOURNAL_TYPE_CASH_RECEIVE, 
                        cr.getMemo(), cr.getOperatorId(), cr.getOperatorName(), cr.getOID(), "", cr.getTransDate());
                    
                    if(oid!=0){
                        
                        for(int i=0; i<details.size(); i++){
                            CashReceiveDetail crd = (CashReceiveDetail)details.get(i);
                            //journal debet pada expense
                            DbGl.postJournalDetail(crd.getBookedRate(), crd.getCoaId(), 0, crd.getAmount(),             
                                    crd.getForeignAmount(), crd.getForeignCurrencyId(), oid, crd.getMemo(), 0); //non departmenttal item, department id = 0
                        }
                        
                        //journal credit pada hutang 
                        DbGl.postJournalDetail(er.getValueIdr(), cr.getCoaId(), cr.getAmount(), 0,             
                                    0, comp.getBookingCurrencyId(), oid, cr.getMemo(), 0);//non departmenttal item, department id = 0
                        
                        
                    }
                }
        }
        
        //POSTING ke journal
        //jurnal hutang pada cash
        public static void postJournalBYHMD(CashReceive cr, Vector details){
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                try{
                    cr = DbCashReceive.fetchExc(cr.getOID());
                }
                catch(Exception e){
                    
                }
                
                if(cr.getOID()!=0 && details!=null && details.size()>0){
                    long oid = DbGl.postJournalMain(0, cr.getDate(), cr.getJournalCounter(), cr.getJournalNumber(), cr.getJournalPrefix(), I_Project.JOURNAL_TYPE_CASH_RECEIVE, 
                        cr.getMemo(), cr.getOperatorId(), cr.getOperatorName(), cr.getOID(), "", cr.getTransDate());
                    
                    if(oid!=0){
                        
                        for(int i=0; i<details.size(); i++){
                            CashReceiveDetail crd = (CashReceiveDetail)details.get(i);
                            //journal debet pada utang
                            DbGl.postJournalDetail(crd.getBookedRate(), crd.getCoaId(), 0, crd.getAmount(),             
                                    crd.getForeignAmount(), crd.getForeignCurrencyId(), oid, crd.getMemo(), 0); //non departmenttal item, department id = 0
                        }
                        
                        //journal credit pada cash 
                        DbGl.postJournalDetail(er.getValueIdr(), cr.getCoaId(), cr.getAmount(), 0,             
                                    0, comp.getBookingCurrencyId(), oid, cr.getMemo(), 0);//non departmenttal item, department id = 0
                        
                        
                        
                        
                    }
                }
        }
        
        
        public static void updateAmount(CashReceive cashReceive){
            String sql = "";
            CONResultSet crs = null;
            double amount = 0;
            try{
                sql = "SELECT sum("+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_AMOUNT]+") FROM "+
                      DbCashReceiveDetail.DB_CASH_RECEIVE_DETAIL+" p where "+
                      DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CASH_RECEIVE_ID]+"="+cashReceive.getOID();                
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    amount = rs.getDouble(1);
                }
                                
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }
            
            try{
                cashReceive.setAmount(amount);
                DbCashReceive.updateExc(cashReceive);
            }
            catch(Exception e){
                
            }
        }
        
        
        public static Vector getSaldoTitipan(long customerId){
            
            String sql = "select "+colNames[COL_CUSTOMER_ID]+", sum("+colNames[COL_AMOUNT]+"*"+colNames[COL_IN_OUT]+") from "+
                DB_CASH_RECEIVE+" where ("+colNames[COL_TYPE]+"="+TYPE_CASH_LIABILITY+" or "+colNames[COL_TYPE]+"="+TYPE_CASH_LIABILITY_PAYMENT+")";
                
                if(customerId!=0){
                    sql = sql + " and "+colNames[COL_CUSTOMER_ID]+"="+customerId;
                }
            
                sql = sql + " group by "+colNames[COL_CUSTOMER_ID];
                
                System.out.println(sql);
            
            CONResultSet crs = null;
            Vector result = new Vector();
            
            try{
                crs = CONHandler.execQueryResult(sql);    
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CashReceive cr = new CashReceive();
                    cr.setCustomerId(rs.getLong(1));
                    cr.setAmount(rs.getDouble(2));
                    result.add(cr);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        
        public static Vector getSaldoTitipan(long customerId, long accountId){
            
            /*String sql = "select "+colNames[COL_CUSTOMER_ID]+", sum("+colNames[COL_AMOUNT]+"*"+colNames[COL_IN_OUT]+") from "+
                DB_CASH_RECEIVE+" where ("+colNames[COL_TYPE]+"="+TYPE_CASH_LIABILITY+" or "+colNames[COL_TYPE]+"="+TYPE_CASH_LIABILITY_PAYMENT+")";
                
                if(customerId!=0){
                    sql = sql + " and "+colNames[COL_CUSTOMER_ID]+"="+customerId;
                }
            
                sql = sql + " group by "+colNames[COL_CUSTOMER_ID];
                
                System.out.println(sql);
             */
                
                
            String sql = "SELECT cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID]+", " +
                " sum(cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_AMOUNT]+
                " *c."+DbCashReceive.colNames[DbCashReceive.COL_IN_OUT]+") "+
                " FROM "+DB_CASH_RECEIVE+" c "+
                " inner join "+DbCashReceiveDetail.DB_CASH_RECEIVE_DETAIL+" cd "+
                " on cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CASH_RECEIVE_ID]+
                " = c."+DbCashReceive.colNames[DbCashReceive.COL_CASH_RECEIVE_ID]+
                " where (c."+colNames[COL_TYPE]+"="+TYPE_CASH_LIABILITY+" or c."+colNames[COL_TYPE]+"="+TYPE_CASH_LIABILITY_PAYMENT+")";
            
                if(customerId!=0){
                    sql = sql + " and cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID]+"="+customerId;
                }
            
                if(accountId!=0){
                    sql = sql + " and cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_COA_ID]+"="+accountId;
                }
                            
                sql = sql +" group by cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID];    
                
                System.out.println(sql);
            
            CONResultSet crs = null;
            Vector result = new Vector();
            
            try{
                crs = CONHandler.execQueryResult(sql);    
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CashReceive cr = new CashReceive();
                    cr.setCustomerId(rs.getLong(1));
                    cr.setAmount(rs.getDouble(2));
                    result.add(cr);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        
        public static Vector getSaldoBYMHD(long customerId){
            
            String sql = "select "+colNames[COL_CUSTOMER_ID]+", sum("+colNames[COL_AMOUNT]+"*"+colNames[COL_IN_OUT]+") from "+
                DB_CASH_RECEIVE+" where ("+colNames[COL_TYPE]+"="+TYPE_BYMHD_NEW+" or "+colNames[COL_TYPE]+"="+TYPE_BYMHD+")";
                
                if(customerId!=0){
                    sql = sql + " and "+colNames[COL_CUSTOMER_ID]+"="+customerId;
                }
            
                sql = sql + " group by "+colNames[COL_CUSTOMER_ID];
                
                System.out.println(sql);
            
            CONResultSet crs = null;
            Vector result = new Vector();
            
            try{
                crs = CONHandler.execQueryResult(sql);    
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CashReceive cr = new CashReceive();
                    cr.setCustomerId(rs.getLong(1));
                    cr.setAmount(rs.getDouble(2));
                    result.add(cr);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        
        public static Vector getSaldoBYMHD(long customerId, long accountId){
            
            /*String sql = "select "+colNames[COL_CUSTOMER_ID]+", sum("+colNames[COL_AMOUNT]+"*"+colNames[COL_IN_OUT]+") from "+
                DB_CASH_RECEIVE+" where ("+colNames[COL_TYPE]+"="+TYPE_BYMHD_NEW+" or "+colNames[COL_TYPE]+"="+TYPE_BYMHD+")";
                
                if(customerId!=0){
                    sql = sql + " and "+colNames[COL_CUSTOMER_ID]+"="+customerId;
                }
            
                sql = sql + " group by "+colNames[COL_CUSTOMER_ID];
                
                System.out.println(sql);
             */
            
            String sql = "SELECT cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID]+", " +
                " sum(cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_AMOUNT]+
                " *c."+DbCashReceive.colNames[DbCashReceive.COL_IN_OUT]+") "+
                " FROM "+DB_CASH_RECEIVE+" c "+
                " inner join "+DbCashReceiveDetail.DB_CASH_RECEIVE_DETAIL+" cd "+
                " on cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CASH_RECEIVE_ID]+
                " = c."+DbCashReceive.colNames[DbCashReceive.COL_CASH_RECEIVE_ID]+
                " where (c."+colNames[COL_TYPE]+"="+TYPE_BYMHD_NEW+" or c."+colNames[COL_TYPE]+"="+TYPE_BYMHD+")";
            
                if(customerId!=0){
                    sql = sql + " and cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID]+"="+customerId;
                }
            
                if(accountId!=0){
                    sql = sql + " and cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_BYMHD_COA_ID]+"="+accountId;
                }
                            
                sql = sql +" group by cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID];    
                
                System.out.println(sql);
            
            CONResultSet crs = null;
            Vector result = new Vector();
            
            try{
                crs = CONHandler.execQueryResult(sql);    
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CashReceive cr = new CashReceive();
                    cr.setCustomerId(rs.getLong(1));
                    cr.setAmount(rs.getDouble(2));
                    result.add(cr);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        
        public static Vector getSaldoDP(long customerId, long accountId){
            
            /*String sql = "select "+colNames[COL_CUSTOMER_ID]+", sum("+colNames[COL_AMOUNT]+"*"+colNames[COL_IN_OUT]+") from "+
                DB_CASH_RECEIVE+" where ("+colNames[COL_TYPE]+"="+TYPE_BYMHD_NEW+" or "+colNames[COL_TYPE]+"="+TYPE_BYMHD+")";
                
                if(customerId!=0){
                    sql = sql + " and "+colNames[COL_CUSTOMER_ID]+"="+customerId;
                }
            
                sql = sql + " group by "+colNames[COL_CUSTOMER_ID];
                
                System.out.println(sql);
             */
            
            String sql = "SELECT cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID]+", " +
                " sum(cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_AMOUNT]+
                " *c."+DbCashReceive.colNames[DbCashReceive.COL_IN_OUT]+") "+
                " FROM "+DB_CASH_RECEIVE+" c "+
                " inner join "+DbCashReceiveDetail.DB_CASH_RECEIVE_DETAIL+" cd "+
                " on cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CASH_RECEIVE_ID]+
                " = c."+DbCashReceive.colNames[DbCashReceive.COL_CASH_RECEIVE_ID]+
                " where (c."+colNames[COL_TYPE]+"="+TYPE_DP+" or c."+colNames[COL_TYPE]+"="+TYPE_DP_RETURN+")";
            
                if(customerId!=0){
                    sql = sql + " and cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID]+"="+customerId;
                }
            
                if(accountId!=0){
                    sql = sql + " and cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_BYMHD_COA_ID]+"="+accountId;
                }
                            
                sql = sql +" group by cd."+DbCashReceiveDetail.colNames[DbCashReceiveDetail.COL_CUSTOMER_ID];    
                
                System.out.println(sql);
            
            CONResultSet crs = null;
            Vector result = new Vector();
            
            try{
                crs = CONHandler.execQueryResult(sql);    
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CashReceive cr = new CashReceive();
                    cr.setCustomerId(rs.getLong(1));
                    cr.setAmount(rs.getDouble(2));
                    result.add(cr);
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
