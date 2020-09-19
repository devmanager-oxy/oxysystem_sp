
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
import com.project.*;
import com.project.fms.master.*;
import com.project.util.*;
import com.project.system.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;

public class DbBanknonpoPayment extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_BANKNONPO_PAYMENT = "banknonpo_payment";

	public static final  int COL_BANKNONPO_PAYMENT_ID = 0;
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
	public static final  int COL_REF_NUMBER = 11;
        public static final  int COL_PAYMENT_METHOD_ID = 12;
        public static final  int COL_VENDOR_ID = 13;
        
        public static final  int COL_INVOICE_NUMBER = 14;
        public static final  int COL_ACTIVITY_STATUS = 15;
        public static final  int COL_TYPE = 16;

	public static final  String[] colNames = {
		"banknonpo_payment_id",		
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
		"ref_number",
                "payment_method_id",
                "vendor_id",
                "invoice_number",
                "activity_status",
                "type"

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
		TYPE_STRING,
                TYPE_LONG,
                TYPE_LONG,
                TYPE_STRING,
                TYPE_STRING,
                TYPE_INT
	 }; 
         
        
	public DbBanknonpoPayment(){
	}

	public DbBanknonpoPayment(int i) throws CONException { 
		super(new DbBanknonpoPayment()); 
	}

	public DbBanknonpoPayment(String sOid) throws CONException { 
		super(new DbBanknonpoPayment(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbBanknonpoPayment(long lOid) throws CONException { 
		super(new DbBanknonpoPayment(0)); 
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
		return DB_BANKNONPO_PAYMENT;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbBanknonpoPayment().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		BanknonpoPayment banknonpopayment = fetchExc(ent.getOID()); 
		ent = (Entity)banknonpopayment; 
		return banknonpopayment.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((BanknonpoPayment) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((BanknonpoPayment) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static BanknonpoPayment fetchExc(long oid) throws CONException{ 
		try{ 
			BanknonpoPayment banknonpopayment = new BanknonpoPayment();
			DbBanknonpoPayment pstBanknonpoPayment = new DbBanknonpoPayment(oid); 
			banknonpopayment.setOID(oid);

			banknonpopayment.setCoaId(pstBanknonpoPayment.getlong(COL_COA_ID));
			banknonpopayment.setJournalNumber(pstBanknonpoPayment.getString(COL_JOURNAL_NUMBER));
			banknonpopayment.setJournalCounter(pstBanknonpoPayment.getInt(COL_JOURNAL_COUNTER));
			banknonpopayment.setJournalPrefix(pstBanknonpoPayment.getString(COL_JOURNAL_PREFIX));
			banknonpopayment.setDate(pstBanknonpoPayment.getDate(COL_DATE));
			banknonpopayment.setTransDate(pstBanknonpoPayment.getDate(COL_TRANS_DATE));
			banknonpopayment.setMemo(pstBanknonpoPayment.getString(COL_MEMO));
			banknonpopayment.setOperatorId(pstBanknonpoPayment.getlong(COL_OPERATOR_ID));
			banknonpopayment.setOperatorName(pstBanknonpoPayment.getString(COL_OPERATOR_NAME));
			banknonpopayment.setAmount(pstBanknonpoPayment.getdouble(COL_AMOUNT));
			banknonpopayment.setRefNumber(pstBanknonpoPayment.getString(COL_REF_NUMBER));
                        banknonpopayment.setPaymentMethodId(pstBanknonpoPayment.getlong(COL_PAYMENT_METHOD_ID));
                        banknonpopayment.setVendorId(pstBanknonpoPayment.getlong(COL_VENDOR_ID));
                        banknonpopayment.setInvoiceNumber(pstBanknonpoPayment.getString(COL_INVOICE_NUMBER));
                        
                        banknonpopayment.setActivityStatus(pstBanknonpoPayment.getString(COL_ACTIVITY_STATUS));
                        banknonpopayment.setType(pstBanknonpoPayment.getInt(COL_TYPE));
			return banknonpopayment; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPayment(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(BanknonpoPayment banknonpopayment) throws CONException{ 
		try{ 
			DbBanknonpoPayment pstBanknonpoPayment = new DbBanknonpoPayment(0);

			pstBanknonpoPayment.setLong(COL_COA_ID, banknonpopayment.getCoaId());
			pstBanknonpoPayment.setString(COL_JOURNAL_NUMBER, banknonpopayment.getJournalNumber());
			pstBanknonpoPayment.setInt(COL_JOURNAL_COUNTER, banknonpopayment.getJournalCounter());
			pstBanknonpoPayment.setString(COL_JOURNAL_PREFIX, banknonpopayment.getJournalPrefix());
			pstBanknonpoPayment.setDate(COL_DATE, banknonpopayment.getDate());
			pstBanknonpoPayment.setDate(COL_TRANS_DATE, banknonpopayment.getTransDate());
			pstBanknonpoPayment.setString(COL_MEMO, banknonpopayment.getMemo());
			pstBanknonpoPayment.setLong(COL_OPERATOR_ID, banknonpopayment.getOperatorId());
			pstBanknonpoPayment.setString(COL_OPERATOR_NAME, banknonpopayment.getOperatorName());
			pstBanknonpoPayment.setDouble(COL_AMOUNT, banknonpopayment.getAmount());
			pstBanknonpoPayment.setString(COL_REF_NUMBER, banknonpopayment.getRefNumber());
                        pstBanknonpoPayment.setLong(COL_PAYMENT_METHOD_ID, banknonpopayment.getPaymentMethodId());
                        pstBanknonpoPayment.setLong(COL_VENDOR_ID, banknonpopayment.getVendorId());
                        
                        pstBanknonpoPayment.setString(COL_INVOICE_NUMBER, banknonpopayment.getInvoiceNumber());
                        pstBanknonpoPayment.setString(COL_ACTIVITY_STATUS, banknonpopayment.getActivityStatus());
                        pstBanknonpoPayment.setInt(COL_TYPE, banknonpopayment.getType());
			pstBanknonpoPayment.insert(); 
			banknonpopayment.setOID(pstBanknonpoPayment.getlong(COL_BANKNONPO_PAYMENT_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPayment(0),CONException.UNKNOWN); 
		}
		return banknonpopayment.getOID();
	}

	public static long updateExc(BanknonpoPayment banknonpopayment) throws CONException{ 
		try{ 
			if(banknonpopayment.getOID() != 0){ 
				DbBanknonpoPayment pstBanknonpoPayment = new DbBanknonpoPayment(banknonpopayment.getOID());

				pstBanknonpoPayment.setLong(COL_COA_ID, banknonpopayment.getCoaId());
				pstBanknonpoPayment.setString(COL_JOURNAL_NUMBER, banknonpopayment.getJournalNumber());
				pstBanknonpoPayment.setInt(COL_JOURNAL_COUNTER, banknonpopayment.getJournalCounter());
				pstBanknonpoPayment.setString(COL_JOURNAL_PREFIX, banknonpopayment.getJournalPrefix());
				pstBanknonpoPayment.setDate(COL_DATE, banknonpopayment.getDate());
				pstBanknonpoPayment.setDate(COL_TRANS_DATE, banknonpopayment.getTransDate());
				pstBanknonpoPayment.setString(COL_MEMO, banknonpopayment.getMemo());
				pstBanknonpoPayment.setLong(COL_OPERATOR_ID, banknonpopayment.getOperatorId());
				pstBanknonpoPayment.setString(COL_OPERATOR_NAME, banknonpopayment.getOperatorName());
				pstBanknonpoPayment.setDouble(COL_AMOUNT, banknonpopayment.getAmount());
				pstBanknonpoPayment.setString(COL_REF_NUMBER, banknonpopayment.getRefNumber());
                                pstBanknonpoPayment.setLong(COL_PAYMENT_METHOD_ID, banknonpopayment.getPaymentMethodId());
                                pstBanknonpoPayment.setLong(COL_VENDOR_ID, banknonpopayment.getVendorId());
                                
                                pstBanknonpoPayment.setString(COL_INVOICE_NUMBER, banknonpopayment.getInvoiceNumber());
                                pstBanknonpoPayment.setString(COL_ACTIVITY_STATUS, banknonpopayment.getActivityStatus());
                                pstBanknonpoPayment.setInt(COL_TYPE,banknonpopayment.getType());
				pstBanknonpoPayment.update(); 
				return banknonpopayment.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPayment(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbBanknonpoPayment pstBanknonpoPayment = new DbBanknonpoPayment(oid);
			pstBanknonpoPayment.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPayment(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_BANKNONPO_PAYMENT; 
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
				BanknonpoPayment banknonpopayment = new BanknonpoPayment();
				resultToObject(rs, banknonpopayment);
				lists.add(banknonpopayment);
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

	private static void resultToObject(ResultSet rs, BanknonpoPayment banknonpopayment){
		try{
			banknonpopayment.setOID(rs.getLong(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_BANKNONPO_PAYMENT_ID]));
			banknonpopayment.setCoaId(rs.getLong(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_COA_ID]));
			banknonpopayment.setJournalNumber(rs.getString(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_JOURNAL_NUMBER]));
			banknonpopayment.setJournalCounter(rs.getInt(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_JOURNAL_COUNTER]));
			banknonpopayment.setJournalPrefix(rs.getString(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_JOURNAL_PREFIX]));
			banknonpopayment.setDate(rs.getDate(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_DATE]));
			banknonpopayment.setTransDate(rs.getDate(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_TRANS_DATE]));
			banknonpopayment.setMemo(rs.getString(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_MEMO]));
			banknonpopayment.setOperatorId(rs.getLong(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_OPERATOR_ID]));
			banknonpopayment.setOperatorName(rs.getString(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_OPERATOR_NAME]));
			banknonpopayment.setAmount(rs.getDouble(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_AMOUNT]));
			banknonpopayment.setRefNumber(rs.getString(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_REF_NUMBER]));
                        banknonpopayment.setPaymentMethodId(rs.getLong(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_PAYMENT_METHOD_ID]));
                        banknonpopayment.setVendorId(rs.getLong(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_VENDOR_ID]));
                        
                        banknonpopayment.setInvoiceNumber(rs.getString(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_INVOICE_NUMBER]));
                        banknonpopayment.setActivityStatus(rs.getString(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_ACTIVITY_STATUS]));
                        banknonpopayment.setType(rs.getInt(DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_TYPE]));
                        

		}catch(Exception e){ }
	}

	public static boolean checkOID(long banknonpoPaymentId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_BANKNONPO_PAYMENT + " WHERE " + 
						DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_BANKNONPO_PAYMENT_ID] + " = " + banknonpoPaymentId;

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
			String sql = "SELECT COUNT("+ DbBanknonpoPayment.colNames[DbBanknonpoPayment.COL_BANKNONPO_PAYMENT_ID] + ") FROM " + DB_BANKNONPO_PAYMENT;
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
			  	   BanknonpoPayment banknonpopayment = (BanknonpoPayment)list.get(ls);
				   if(oid == banknonpopayment.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        public static int getNextCounter(long oid){
                int result = 0;
                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max(journal_counter) from "+DB_BANKNONPO_PAYMENT+" where "+
                        " journal_prefix='"+getNumberPrefix(oid)+"' ";
                    
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
        
        public static String getNumberPrefix(long oid){
            
                Company sysCompany = DbCompany.getCompany();
                Location sysLocation = DbLocation.getLocationById(sysCompany.getSystemLocation());

                String code = sysLocation.getCode();//DbSystemProperty.getValueByName("LOCATION_CODE");
                
                Coa coa = new Coa();
                try{
                    coa = DbCoa.fetchExc(oid);
                }
                catch(Exception e){
                }
                
                if(coa.getIsNeedExtra()==1){
                    code = code + coa.getCreditPrefixCode();//sysCompany.getBankPaymentNonpoCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");    
                }
                else{                
                    code = code + sysCompany.getBankPaymentNonpoCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");
                }
                
                code = code + JSPFormater.formatDate(new Date(), "MMyy");
                
                return code;
        }
        
        
        public static String getNextNumber(int ctr, long oid){
            
                String code = getNumberPrefix(oid);
                
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
        public static void postJournal(BanknonpoPayment cr, Vector details){
                
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                try{
                    cr = DbBanknonpoPayment.fetchExc(cr.getOID());
                }
                catch(Exception e){
                    
                }
                
                if(cr.getOID()!=0 && details!=null && details.size()>0){
                    long oid = DbGl.postJournalMain(0, cr.getDate(), cr.getJournalCounter(), cr.getJournalNumber(), cr.getJournalPrefix(), I_Project.JOURNAL_TYPE_BANKPAYMENT_NONPO, 
                        cr.getMemo(), cr.getOperatorId(), cr.getOperatorName(), cr.getOID(), "", cr.getTransDate());
                    
                    if(oid!=0){
                        for(int i=0; i<details.size(); i++){
                            BanknonpoPaymentDetail crd = (BanknonpoPaymentDetail)details.get(i);
                            //journal debet pada expense
                            DbGl.postJournalDetail(er.getValueIdr(), crd.getCoaId(), 0, crd.getAmount(),             
                                    0, comp.getBookingCurrencyId(), oid, crd.getMemo(), crd.getDepartmentId());
                        }
                        
                        //journal credit pada bank
                        DbGl.postJournalDetail(er.getValueIdr(), cr.getCoaId(), cr.getAmount(), 0,             
                                    0, comp.getBookingCurrencyId(), oid, cr.getMemo(), 0);
                    }
                }
        }
        
        
        public static double getPaymentByPeriod(Periode openPeriod, long coaId){
                double result = 0;
                CONResultSet crs = null;
                try{
                    String sql = "select sum("+colNames[COL_AMOUNT]+") from "+DB_BANKNONPO_PAYMENT+
                        " where ("+colNames[COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(openPeriod.getStartDate(), "yyyy-MM-dd")+"'"+
                        " and '"+JSPFormater.formatDate(openPeriod.getEndDate(), "yyyy-MM-dd")+"')"+
                        " and "+colNames[COL_COA_ID]+"="+coaId;                        
                    
                    //System.out.println("\n"+sql);
                    
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
        
        public static void postActivityStatus(long oidFlag, long oidBanknonpoPayment){
            try{
                
                BanknonpoPayment pp = DbBanknonpoPayment.fetchExc(oidBanknonpoPayment);
                if(oidFlag==0){
                    pp.setActivityStatus(I_Project.STATUS_NOT_POSTED);
                }
                else{
                    pp.setActivityStatus(I_Project.STATUS_POSTED);
                }
                
                DbBanknonpoPayment.updateExc(pp);
                
            }
            catch(Exception e){
                
            }
            
        }
        
        public static void updateAmount(BanknonpoPayment banknonpoPayment){
            String sql = "";
            CONResultSet crs = null;
            double amount = 0;
            try{
                sql = "SELECT sum("+DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_AMOUNT]+") FROM "+
                      DbBanknonpoPaymentDetail.DB_BANKNONPO_PAYMENT_DETAIL+" p where "+
                      DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_BANKNONPO_PAYMENT_ID]+"="+banknonpoPayment.getOID();                
                
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
                banknonpoPayment.setAmount(amount);
                DbBanknonpoPayment.updateExc(banknonpoPayment);
            }
            catch(Exception e){
                
            }
        }
        
}
