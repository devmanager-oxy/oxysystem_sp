
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
import com.project.system.*;
import com.project.util.*;
import com.project.fms.master.*;
import com.project.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;


public class DbPettycashPayment extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_PETTYCASH_PAYMENT = "pettycash_payment";

	public static final  int COL_PETTYCASH_PAYMENT_ID = 0;
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
	public static final  int COL_REPLACE_STATUS = 11;
        
        public static final  int COL_ACTIVITY_STATUS = 12;
        public static final  int COL_SHARE_GROUP_ID = 13;
        public static final  int COL_SHARE_CATEGORY_ID = 14;
        public static final  int COL_STATUS = 15;
        public static final  int COL_EXPENSE_CATEGORY_ID = 16; 

	public static final  String[] colNames = {
		"pettycash_payment_id",
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
		"replace_status",
                "activity_status",
                
                "share_group_id",
                "share_category_id",
                "status",
                "expense_category_id"
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
		TYPE_INT,
                TYPE_STRING,
                
                TYPE_LONG,
                TYPE_LONG,
                TYPE_INT,
                TYPE_LONG
	 }; 

	public DbPettycashPayment(){
	}

	public DbPettycashPayment(int i) throws CONException { 
		super(new DbPettycashPayment()); 
	}

	public DbPettycashPayment(String sOid) throws CONException { 
		super(new DbPettycashPayment(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbPettycashPayment(long lOid) throws CONException { 
		super(new DbPettycashPayment(0)); 
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
		return DB_PETTYCASH_PAYMENT;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbPettycashPayment().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		PettycashPayment pettycashpayment = fetchExc(ent.getOID()); 
		ent = (Entity)pettycashpayment; 
		return pettycashpayment.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((PettycashPayment) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((PettycashPayment) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static PettycashPayment fetchExc(long oid) throws CONException{ 
		try{ 
			PettycashPayment pettycashpayment = new PettycashPayment();
			DbPettycashPayment pstPettycashPayment = new DbPettycashPayment(oid); 
			pettycashpayment.setOID(oid);

			pettycashpayment.setCoaId(pstPettycashPayment.getlong(COL_COA_ID));
			pettycashpayment.setJournalNumber(pstPettycashPayment.getString(COL_JOURNAL_NUMBER));
			pettycashpayment.setJournalCounter(pstPettycashPayment.getInt(COL_JOURNAL_COUNTER));
			pettycashpayment.setJournalPrefix(pstPettycashPayment.getString(COL_JOURNAL_PREFIX));
			pettycashpayment.setDate(pstPettycashPayment.getDate(COL_DATE));
			pettycashpayment.setTransDate(pstPettycashPayment.getDate(COL_TRANS_DATE));
			pettycashpayment.setMemo(pstPettycashPayment.getString(COL_MEMO));
			pettycashpayment.setOperatorId(pstPettycashPayment.getlong(COL_OPERATOR_ID));
			pettycashpayment.setOperatorName(pstPettycashPayment.getString(COL_OPERATOR_NAME));
			pettycashpayment.setAmount(pstPettycashPayment.getdouble(COL_AMOUNT));
			pettycashpayment.setReplaceStatus(pstPettycashPayment.getInt(COL_REPLACE_STATUS));
                        
                        pettycashpayment.setActivityStatus(pstPettycashPayment.getString(COL_ACTIVITY_STATUS));
                        
                        pettycashpayment.setShareCategoryId(pstPettycashPayment.getlong(COL_SHARE_CATEGORY_ID));
                        pettycashpayment.setShareGroupId(pstPettycashPayment.getlong(COL_SHARE_GROUP_ID));
                        pettycashpayment.setStatus(pstPettycashPayment.getInt(COL_STATUS));
                        pettycashpayment.setExpenseCategoryId(pstPettycashPayment.getlong(COL_EXPENSE_CATEGORY_ID));

			return pettycashpayment; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPayment(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(PettycashPayment pettycashpayment) throws CONException{ 
		try{ 
			DbPettycashPayment pstPettycashPayment = new DbPettycashPayment(0);

			pstPettycashPayment.setLong(COL_COA_ID, pettycashpayment.getCoaId());
			pstPettycashPayment.setString(COL_JOURNAL_NUMBER, pettycashpayment.getJournalNumber());
			pstPettycashPayment.setInt(COL_JOURNAL_COUNTER, pettycashpayment.getJournalCounter());
			pstPettycashPayment.setString(COL_JOURNAL_PREFIX, pettycashpayment.getJournalPrefix());
			pstPettycashPayment.setDate(COL_DATE, pettycashpayment.getDate());
			pstPettycashPayment.setDate(COL_TRANS_DATE, pettycashpayment.getTransDate());
			pstPettycashPayment.setString(COL_MEMO, pettycashpayment.getMemo());
			pstPettycashPayment.setLong(COL_OPERATOR_ID, pettycashpayment.getOperatorId());
			pstPettycashPayment.setString(COL_OPERATOR_NAME, pettycashpayment.getOperatorName());
			pstPettycashPayment.setDouble(COL_AMOUNT, pettycashpayment.getAmount());
			pstPettycashPayment.setInt(COL_REPLACE_STATUS, pettycashpayment.getReplaceStatus());
                        
                        pstPettycashPayment.setString(COL_ACTIVITY_STATUS, pettycashpayment.getActivityStatus());
                        
                        pstPettycashPayment.setLong(COL_SHARE_CATEGORY_ID, pettycashpayment.getShareCategoryId());
                        pstPettycashPayment.setLong(COL_SHARE_GROUP_ID, pettycashpayment.getShareGroupId());
                        pstPettycashPayment.setInt(COL_STATUS, pettycashpayment.getStatus());
                        pstPettycashPayment.setLong(COL_EXPENSE_CATEGORY_ID, pettycashpayment.getExpenseCategoryId());

			pstPettycashPayment.insert(); 
			pettycashpayment.setOID(pstPettycashPayment.getlong(COL_PETTYCASH_PAYMENT_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPayment(0),CONException.UNKNOWN); 
		}
		return pettycashpayment.getOID();
	}

	public static long updateExc(PettycashPayment pettycashpayment) throws CONException{ 
		try{ 
			if(pettycashpayment.getOID() != 0){ 
				DbPettycashPayment pstPettycashPayment = new DbPettycashPayment(pettycashpayment.getOID());

				pstPettycashPayment.setLong(COL_COA_ID, pettycashpayment.getCoaId());
				pstPettycashPayment.setString(COL_JOURNAL_NUMBER, pettycashpayment.getJournalNumber());
				pstPettycashPayment.setInt(COL_JOURNAL_COUNTER, pettycashpayment.getJournalCounter());
				pstPettycashPayment.setString(COL_JOURNAL_PREFIX, pettycashpayment.getJournalPrefix());
				pstPettycashPayment.setDate(COL_DATE, pettycashpayment.getDate());
				pstPettycashPayment.setDate(COL_TRANS_DATE, pettycashpayment.getTransDate());
				pstPettycashPayment.setString(COL_MEMO, pettycashpayment.getMemo());
				pstPettycashPayment.setLong(COL_OPERATOR_ID, pettycashpayment.getOperatorId());
				pstPettycashPayment.setString(COL_OPERATOR_NAME, pettycashpayment.getOperatorName());
				pstPettycashPayment.setDouble(COL_AMOUNT, pettycashpayment.getAmount());
				pstPettycashPayment.setInt(COL_REPLACE_STATUS, pettycashpayment.getReplaceStatus());
                                pstPettycashPayment.setString(COL_ACTIVITY_STATUS, pettycashpayment.getActivityStatus());
                                
                                pstPettycashPayment.setLong(COL_SHARE_CATEGORY_ID, pettycashpayment.getShareCategoryId());
                                pstPettycashPayment.setLong(COL_SHARE_GROUP_ID, pettycashpayment.getShareGroupId());
                                pstPettycashPayment.setInt(COL_STATUS, pettycashpayment.getStatus());
                                pstPettycashPayment.setLong(COL_EXPENSE_CATEGORY_ID, pettycashpayment.getExpenseCategoryId());

				pstPettycashPayment.update(); 
				return pettycashpayment.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPayment(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbPettycashPayment pstPettycashPayment = new DbPettycashPayment(oid);
			pstPettycashPayment.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPayment(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_PETTYCASH_PAYMENT; 
			if(whereClause != null && whereClause.length() > 0)
				sql = sql + " WHERE " + whereClause;
			if(order != null && order.length() > 0)
				sql = sql + " ORDER BY " + order;
			if(limitStart == 0 && recordToGet == 0)
				sql = sql + "";
			else
				sql = sql + " LIMIT " + limitStart + ","+ recordToGet ;
                        
                        System.out.println("\n"+sql);
                        
			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();
			while(rs.next()) {
				PettycashPayment pettycashpayment = new PettycashPayment();
				resultToObject(rs, pettycashpayment);
				lists.add(pettycashpayment);
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

	private static void resultToObject(ResultSet rs, PettycashPayment pettycashpayment){
		try{
			pettycashpayment.setOID(rs.getLong(DbPettycashPayment.colNames[DbPettycashPayment.COL_PETTYCASH_PAYMENT_ID]));
			pettycashpayment.setCoaId(rs.getLong(DbPettycashPayment.colNames[DbPettycashPayment.COL_COA_ID]));
			pettycashpayment.setJournalNumber(rs.getString(DbPettycashPayment.colNames[DbPettycashPayment.COL_JOURNAL_NUMBER]));
			pettycashpayment.setJournalCounter(rs.getInt(DbPettycashPayment.colNames[DbPettycashPayment.COL_JOURNAL_COUNTER]));
			pettycashpayment.setJournalPrefix(rs.getString(DbPettycashPayment.colNames[DbPettycashPayment.COL_JOURNAL_PREFIX]));
			pettycashpayment.setDate(rs.getDate(DbPettycashPayment.colNames[DbPettycashPayment.COL_DATE]));
			pettycashpayment.setTransDate(rs.getDate(DbPettycashPayment.colNames[DbPettycashPayment.COL_TRANS_DATE]));
			pettycashpayment.setMemo(rs.getString(DbPettycashPayment.colNames[DbPettycashPayment.COL_MEMO]));
			pettycashpayment.setOperatorId(rs.getLong(DbPettycashPayment.colNames[DbPettycashPayment.COL_OPERATOR_ID]));
			pettycashpayment.setOperatorName(rs.getString(DbPettycashPayment.colNames[DbPettycashPayment.COL_OPERATOR_NAME]));
			pettycashpayment.setAmount(rs.getDouble(DbPettycashPayment.colNames[DbPettycashPayment.COL_AMOUNT]));
			pettycashpayment.setReplaceStatus(rs.getInt(DbPettycashPayment.colNames[DbPettycashPayment.COL_REPLACE_STATUS]));
                        pettycashpayment.setActivityStatus(rs.getString(DbPettycashPayment.colNames[DbPettycashPayment.COL_ACTIVITY_STATUS]));
                        
                        pettycashpayment.setShareCategoryId(rs.getLong(DbPettycashPayment.colNames[DbPettycashPayment.COL_SHARE_CATEGORY_ID]));
                        pettycashpayment.setShareGroupId(rs.getLong(DbPettycashPayment.colNames[DbPettycashPayment.COL_SHARE_GROUP_ID]));
                        pettycashpayment.setStatus(rs.getInt(DbPettycashPayment.colNames[DbPettycashPayment.COL_STATUS]));
                        pettycashpayment.setExpenseCategoryId(rs.getLong(DbPettycashPayment.colNames[DbPettycashPayment.COL_EXPENSE_CATEGORY_ID]));
                        

		}catch(Exception e){ }
	}

	public static boolean checkOID(long pettycashPaymentId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_PETTYCASH_PAYMENT + " WHERE " + 
						DbPettycashPayment.colNames[DbPettycashPayment.COL_PETTYCASH_PAYMENT_ID] + " = " + pettycashPaymentId;

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
			String sql = "SELECT COUNT("+ DbPettycashPayment.colNames[DbPettycashPayment.COL_PETTYCASH_PAYMENT_ID] + ") FROM " + DB_PETTYCASH_PAYMENT;
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
			  	   PettycashPayment pettycashpayment = (PettycashPayment)list.get(ls);
				   if(oid == pettycashpayment.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static int getNextCounter(){
                int result = 0;
                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max(journal_counter) from "+DB_PETTYCASH_PAYMENT+" where "+
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
                code = code + sysCompany.getPettycashPaymentCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");
                
                //String code = DbSystemProperty.getValueByName("LOCATION_CODE");
                //code = code + DbSystemProperty.getValueByName("JOURNAL_PETTYCASH_PAYMENT_CODE");
                
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
        
        
        public static double getPaymentByPeriod(Periode openPeriod, long coaId){
                double result = 0;
                CONResultSet crs = null;
                try{
                    String sql = "select sum("+colNames[COL_AMOUNT]+") from "+DB_PETTYCASH_PAYMENT+
                        " where ("+colNames[COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(openPeriod.getStartDate(), "yyyy-MM-dd")+"'"+
                        " and '"+JSPFormater.formatDate(openPeriod.getEndDate(), "yyyy-MM-dd")+"')"+
                        " and "+colNames[COL_COA_ID]+"="+coaId;
                    
                    System.out.println(sql);
                    
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
        
        public static final int REPLACE_STATUS_OPEN = 0;
        public static final int REPLACE_STATUS_CLOSED = 1;
        
        public static Vector getOpenPayment(long coaId){
            
            CONResultSet crs = null;
            Vector result = new Vector(1,1);
            try{
                String sql = "select * from "+DB_PETTYCASH_PAYMENT+" where coa_id="+coaId+
                    " and ("+colNames[COL_PETTYCASH_PAYMENT_ID]+" not in (select "+DbPettycashExpense.colNames[DbPettycashExpense.COL_PETTYCASH_PAYMENT_ID]+
                    " from "+DbPettycashExpense.DB_PETTYCASH_EXPENSES+"))";
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    PettycashPayment pp = new PettycashPayment();
                    DbPettycashPayment.resultToObject(rs, pp);
                    
                    result.add(pp);
                }
                
            }
            catch(Exception e){
                
            }
            finally{
                CONResultSet.close(crs);
            }
            
            //Vector result = list(0,0, colNames[COL_REPLACE_STATUS]+"="+REPLACE_STATUS_OPEN+" and coa_id="+coaId, "");
            return result;
        }
        
        
        public static Vector getPettycashPayment(long oidReplen){
            CONResultSet crs = null;
            Vector result = new Vector(1,1);
            try{
                String sql = "select pp.* from "+DB_PETTYCASH_PAYMENT+" as pp "+
                    " inner join "+DbPettycashExpense.DB_PETTYCASH_EXPENSES+" as pe "+
                    " on pe."+DbPettycashExpense.colNames[DbPettycashExpense.COL_PETTYCASH_PAYMENT_ID]+" = pp."+colNames[COL_PETTYCASH_PAYMENT_ID] +
                    " where pe."+DbPettycashExpense.colNames[DbPettycashExpense.COL_PETTYCASH_REPLENISHMENT_ID]+"="+oidReplen;
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    PettycashPayment pp = new PettycashPayment();
                    DbPettycashPayment.resultToObject(rs, pp);
                    
                    result.add(pp);
                }
                
            }
            catch(Exception e){
                
            }
            finally{
                CONResultSet.close(crs);
            }
            
            //Vector result = list(0,0, colNames[COL_REPLACE_STATUS]+"="+REPLACE_STATUS_OPEN+" and coa_id="+coaId, "");
            return result;
            
        }
        
        
        //posting ke journal
        public static void postJournal(PettycashPayment cr, Vector details){
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                try{
                    cr = DbPettycashPayment.fetchExc(cr.getOID());
                }
                catch(Exception e){
                    
                }
                
                System.out.println("\nposting pettycash payment\ncr.getAmount() : "+cr.getAmount()+"\n\n");
                
                if(cr.getOID()!=0 && details!=null && details.size()>0){
                    
                    long oid = DbGl.postJournalMain(0, cr.getDate(), cr.getJournalCounter(), cr.getJournalNumber(), cr.getJournalPrefix(), I_Project.JOURNAL_TYPE_PETTYCASH_PAYMENT, 
                        cr.getMemo(), cr.getOperatorId(), cr.getOperatorName(), cr.getOID(), "", cr.getTransDate());
                    
                    if(oid!=0){
                        for(int i=0; i<details.size(); i++){
                            PettycashPaymentDetail crd = (PettycashPaymentDetail)details.get(i);
                            //journal debet pada pendapatan
                            DbGl.postJournalDetail(er.getValueIdr(), crd.getCoaId(), 0, crd.getAmount(),             
                                    0, comp.getBookingCurrencyId(), oid, crd.getMemo(), crd.getDepartmentId()); // expense : departmental coa
                        }
                        
                        //journal credit pada petty cash
                        DbGl.postJournalDetail(er.getValueIdr(), cr.getCoaId(), cr.getAmount(), 0,             
                                    0, comp.getBookingCurrencyId(), oid, cr.getMemo(), 0); // petty cash : non departmental coa
                    }
                }
        }
        
        
        public static void postActivityStatus(long oidFlag, long oidPettyCashPayment){
            try{
                
                PettycashPayment pp = DbPettycashPayment.fetchExc(oidPettyCashPayment);
                if(oidFlag==0){
                    pp.setActivityStatus(I_Project.STATUS_NOT_POSTED);
                }
                else{
                    pp.setActivityStatus(I_Project.STATUS_POSTED);
                }
                
                DbPettycashPayment.updateExc(pp);
                
            }
            catch(Exception e){
                
            }
            
        }
        
        public static void getUpdateTotalAmount(long oid){
            String sql = "";
            CONResultSet crs = null;
            double amount = 0;
            try{
                sql = "SELECT sum("+DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_AMOUNT]+") FROM "+
                      DbPettycashPaymentDetail.DB_PETTYCASH_PAYMENT_DETAIL+" p where "+
                      DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_PETTYCASH_PAYMENT_ID]+"="+oid;                
                
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
            
            PettycashPayment pp = new PettycashPayment();
            try{
                pp = DbPettycashPayment.fetchExc(oid);
                pp.setAmount(amount);
                DbPettycashPayment.updateExc(pp);
            }
            catch(Exception e){
                
            }
            
        }
        
}
