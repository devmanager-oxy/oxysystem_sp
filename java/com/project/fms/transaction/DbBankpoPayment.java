
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
import com.project.general.Currency;
import com.project.general.DbCurrency;
import com.project.system.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;

import com.project.ccs.postransaction.receiving.*;

public class DbBankpoPayment extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_BANKPO_PAYMENT = "bankpo_payment";

	public static final  int COL_BANKPO_PAYMENT_ID = 0;
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
	public static final  int COL_VENDOR_ID = 12;
	public static final  int COL_CURRENCY_ID = 13;
	public static final  int COL_PAYMENT_METHOD = 14;
        public static final  int COL_PAYMENT_METHOD_ID = 15;
	public static final  int COL_STATUS = 16;        

	public static final  String[] colNames = {
		"bankpo_payment_id",		
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
		"vendor_id",
		"currency_id",
		"payment_method",
                "payment_method_id",
                "status"
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
                TYPE_LONG,
                TYPE_STRING
	 }; 

	public DbBankpoPayment(){
	}

	public DbBankpoPayment(int i) throws CONException { 
		super(new DbBankpoPayment()); 
	}

	public DbBankpoPayment(String sOid) throws CONException { 
		super(new DbBankpoPayment(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbBankpoPayment(long lOid) throws CONException { 
		super(new DbBankpoPayment(0)); 
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
		return DB_BANKPO_PAYMENT;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbBankpoPayment().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		BankpoPayment bankpopayment = fetchExc(ent.getOID()); 
		ent = (Entity)bankpopayment; 
		return bankpopayment.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((BankpoPayment) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((BankpoPayment) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static BankpoPayment fetchExc(long oid) throws CONException{ 
		try{ 
			BankpoPayment bankpopayment = new BankpoPayment();
			DbBankpoPayment pstBankpoPayment = new DbBankpoPayment(oid); 
			bankpopayment.setOID(oid);

			bankpopayment.setCoaId(pstBankpoPayment.getlong(COL_COA_ID));
			bankpopayment.setJournalNumber(pstBankpoPayment.getString(COL_JOURNAL_NUMBER));
			bankpopayment.setJournalCounter(pstBankpoPayment.getInt(COL_JOURNAL_COUNTER));
			bankpopayment.setJournalPrefix(pstBankpoPayment.getString(COL_JOURNAL_PREFIX));
			bankpopayment.setDate(pstBankpoPayment.getDate(COL_DATE));
			bankpopayment.setTransDate(pstBankpoPayment.getDate(COL_TRANS_DATE));
			bankpopayment.setMemo(pstBankpoPayment.getString(COL_MEMO));
			bankpopayment.setOperatorId(pstBankpoPayment.getlong(COL_OPERATOR_ID));
			bankpopayment.setOperatorName(pstBankpoPayment.getString(COL_OPERATOR_NAME));
			bankpopayment.setAmount(pstBankpoPayment.getdouble(COL_AMOUNT));
			bankpopayment.setRefNumber(pstBankpoPayment.getString(COL_REF_NUMBER));
			bankpopayment.setVendorId(pstBankpoPayment.getlong(COL_VENDOR_ID));
			bankpopayment.setCurrencyId(pstBankpoPayment.getlong(COL_CURRENCY_ID));
			bankpopayment.setPaymentMethod(pstBankpoPayment.getString(COL_PAYMENT_METHOD));
                        bankpopayment.setPaymentMethodId(pstBankpoPayment.getlong(COL_PAYMENT_METHOD_ID));
                        bankpopayment.setStatus(pstBankpoPayment.getString(COL_STATUS));
			return bankpopayment; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPayment(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(BankpoPayment bankpopayment) throws CONException{ 
		try{ 
			DbBankpoPayment pstBankpoPayment = new DbBankpoPayment(0);

			pstBankpoPayment.setLong(COL_COA_ID, bankpopayment.getCoaId());
			pstBankpoPayment.setString(COL_JOURNAL_NUMBER, bankpopayment.getJournalNumber());
			pstBankpoPayment.setInt(COL_JOURNAL_COUNTER, bankpopayment.getJournalCounter());
			pstBankpoPayment.setString(COL_JOURNAL_PREFIX, bankpopayment.getJournalPrefix());
			pstBankpoPayment.setDate(COL_DATE, bankpopayment.getDate());
			pstBankpoPayment.setDate(COL_TRANS_DATE, bankpopayment.getTransDate());
			pstBankpoPayment.setString(COL_MEMO, bankpopayment.getMemo());
			pstBankpoPayment.setLong(COL_OPERATOR_ID, bankpopayment.getOperatorId());
			pstBankpoPayment.setString(COL_OPERATOR_NAME, bankpopayment.getOperatorName());
			pstBankpoPayment.setDouble(COL_AMOUNT, bankpopayment.getAmount());
			pstBankpoPayment.setString(COL_REF_NUMBER, bankpopayment.getRefNumber());
			pstBankpoPayment.setLong(COL_VENDOR_ID, bankpopayment.getVendorId());
			pstBankpoPayment.setLong(COL_CURRENCY_ID, bankpopayment.getCurrencyId());
			pstBankpoPayment.setString(COL_PAYMENT_METHOD, bankpopayment.getPaymentMethod());
                        pstBankpoPayment.setLong(COL_PAYMENT_METHOD_ID, bankpopayment.getPaymentMethodId());
                        pstBankpoPayment.setString(COL_STATUS, bankpopayment.getStatus());
			pstBankpoPayment.insert(); 
			bankpopayment.setOID(pstBankpoPayment.getlong(COL_BANKPO_PAYMENT_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPayment(0),CONException.UNKNOWN); 
		}
		return bankpopayment.getOID();
	}

	public static long updateExc(BankpoPayment bankpopayment) throws CONException{ 
		try{ 
			if(bankpopayment.getOID() != 0){ 
				DbBankpoPayment pstBankpoPayment = new DbBankpoPayment(bankpopayment.getOID());

				pstBankpoPayment.setLong(COL_COA_ID, bankpopayment.getCoaId());
				pstBankpoPayment.setString(COL_JOURNAL_NUMBER, bankpopayment.getJournalNumber());
				pstBankpoPayment.setInt(COL_JOURNAL_COUNTER, bankpopayment.getJournalCounter());
				pstBankpoPayment.setString(COL_JOURNAL_PREFIX, bankpopayment.getJournalPrefix());
				pstBankpoPayment.setDate(COL_DATE, bankpopayment.getDate());
				pstBankpoPayment.setDate(COL_TRANS_DATE, bankpopayment.getTransDate());
				pstBankpoPayment.setString(COL_MEMO, bankpopayment.getMemo());
				pstBankpoPayment.setLong(COL_OPERATOR_ID, bankpopayment.getOperatorId());
				pstBankpoPayment.setString(COL_OPERATOR_NAME, bankpopayment.getOperatorName());
				pstBankpoPayment.setDouble(COL_AMOUNT, bankpopayment.getAmount());
				pstBankpoPayment.setString(COL_REF_NUMBER, bankpopayment.getRefNumber());
				pstBankpoPayment.setLong(COL_VENDOR_ID, bankpopayment.getVendorId());
				pstBankpoPayment.setLong(COL_CURRENCY_ID, bankpopayment.getCurrencyId());
				pstBankpoPayment.setString(COL_PAYMENT_METHOD, bankpopayment.getPaymentMethod());
                                pstBankpoPayment.setLong(COL_PAYMENT_METHOD_ID, bankpopayment.getPaymentMethodId());
                                pstBankpoPayment.setString(COL_STATUS, bankpopayment.getStatus());
				pstBankpoPayment.update(); 
				return bankpopayment.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPayment(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbBankpoPayment pstBankpoPayment = new DbBankpoPayment(oid);
			pstBankpoPayment.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPayment(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_BANKPO_PAYMENT; 
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
				BankpoPayment bankpopayment = new BankpoPayment();
				resultToObject(rs, bankpopayment);
				lists.add(bankpopayment);
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

	private static void resultToObject(ResultSet rs, BankpoPayment bankpopayment){
		try{
			bankpopayment.setOID(rs.getLong(DbBankpoPayment.colNames[DbBankpoPayment.COL_BANKPO_PAYMENT_ID]));
			bankpopayment.setCoaId(rs.getLong(DbBankpoPayment.colNames[DbBankpoPayment.COL_COA_ID]));
			bankpopayment.setJournalNumber(rs.getString(DbBankpoPayment.colNames[DbBankpoPayment.COL_JOURNAL_NUMBER]));
			bankpopayment.setJournalCounter(rs.getInt(DbBankpoPayment.colNames[DbBankpoPayment.COL_JOURNAL_COUNTER]));
			bankpopayment.setJournalPrefix(rs.getString(DbBankpoPayment.colNames[DbBankpoPayment.COL_JOURNAL_PREFIX]));
			bankpopayment.setDate(rs.getDate(DbBankpoPayment.colNames[DbBankpoPayment.COL_DATE]));
			bankpopayment.setTransDate(rs.getDate(DbBankpoPayment.colNames[DbBankpoPayment.COL_TRANS_DATE]));
			bankpopayment.setMemo(rs.getString(DbBankpoPayment.colNames[DbBankpoPayment.COL_MEMO]));
			bankpopayment.setOperatorId(rs.getLong(DbBankpoPayment.colNames[DbBankpoPayment.COL_OPERATOR_ID]));
			bankpopayment.setOperatorName(rs.getString(DbBankpoPayment.colNames[DbBankpoPayment.COL_OPERATOR_NAME]));
			bankpopayment.setAmount(rs.getDouble(DbBankpoPayment.colNames[DbBankpoPayment.COL_AMOUNT]));
			bankpopayment.setRefNumber(rs.getString(DbBankpoPayment.colNames[DbBankpoPayment.COL_REF_NUMBER]));
			bankpopayment.setVendorId(rs.getLong(DbBankpoPayment.colNames[DbBankpoPayment.COL_VENDOR_ID]));
			bankpopayment.setCurrencyId(rs.getLong(DbBankpoPayment.colNames[DbBankpoPayment.COL_CURRENCY_ID]));
			bankpopayment.setPaymentMethod(rs.getString(DbBankpoPayment.colNames[DbBankpoPayment.COL_PAYMENT_METHOD]));
                        bankpopayment.setPaymentMethodId(rs.getLong(DbBankpoPayment.colNames[DbBankpoPayment.COL_PAYMENT_METHOD_ID]));
                        bankpopayment.setStatus(rs.getString(DbBankpoPayment.colNames[DbBankpoPayment.COL_STATUS]));
		}catch(Exception e){ }
	}

	public static boolean checkOID(long bankpoPaymentId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_BANKPO_PAYMENT + " WHERE " + 
						DbBankpoPayment.colNames[DbBankpoPayment.COL_BANKPO_PAYMENT_ID] + " = " + bankpoPaymentId;

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
			String sql = "SELECT COUNT("+ DbBankpoPayment.colNames[DbBankpoPayment.COL_BANKPO_PAYMENT_ID] + ") FROM " + DB_BANKPO_PAYMENT;
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
			  	   BankpoPayment bankpopayment = (BankpoPayment)list.get(ls);
				   if(oid == bankpopayment.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static double getPaymentByPeriod(Periode openPeriod, long coaId){
                double result = 0;
                CONResultSet crs = null;
                try{
                    String sql = "select sum("+colNames[COL_AMOUNT]+") from "+DB_BANKPO_PAYMENT+
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
        
        public static int getNextCounter(long oid){
                int result = 0;
                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max(journal_counter) from "+DB_BANKPO_PAYMENT+" where "+
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
            
                /*Company sysCompany = DbCompany.getCompany();
                Location sysLocation = DbLocation.getLocationById(sysCompany.getSystemLocation());

                String code = sysLocation.getCode();//DbSystemProperty.getValueByName("LOCATION_CODE");
                code = code + sysCompany.getBankPaymentPoCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");
                
                code = code + JSPFormater.formatDate(new Date(), "MMyy");
                
                return code;
                 */
                
                
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
                    code = code + sysCompany.getBankPaymentPoCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");
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
        
        public static double getTotalPaymentByInvoice(long invoiceId){
            double result = 0;
            CONResultSet crs = null;
            try{
                String sql = "select sum("+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_PAYMENT_BY_INV_CURRENCY_AMOUNT]+") from "+DbBankpoPaymentDetail.DB_BANKPO_PAYMENT_DETAIL+
                    " where "+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_INVOICE_ID]+"="+invoiceId;
                
                System.out.println(sql);
                
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
        
        
        public static double getTotalPaymentByInvoiceFin(long invId){
            double result = 0;
            CONResultSet crs = null;
            try{
                //String sql = "select "+DbBankpoPayment.colNames[DbBankpoPayment.COL_BANKPO_PAYMENT_ID]+" from "+DbBankpoPayment.DB_BANKPO_PAYMENT+
                //    " where "+DbBankpoPayment.colNames[DbBankpoPayment.COL_VENDOR_ID]+"="+vendorId;
                
                String sql = "SELECT sum(bpd."+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_PAYMENT_BY_INV_CURRENCY_AMOUNT]+
                    ") FROM "+DbBankpoPaymentDetail.DB_BANKPO_PAYMENT_DETAIL+" bpd "+
                    " where bpd."+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_INVOICE_ID]+" = "+invId;
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    //System.out.println(rs.getLong(1));
                    result = rs.getDouble(1);//result + getTotalPayment(rs.getLong(1));
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
        
        public static double getTotalPaymentByVendor(long vendorId){
            double result = 0;
            CONResultSet crs = null;
            try{
                String sql = "select "+DbBankpoPayment.colNames[DbBankpoPayment.COL_BANKPO_PAYMENT_ID]+" from "+DbBankpoPayment.DB_BANKPO_PAYMENT+
                    " where "+DbBankpoPayment.colNames[DbBankpoPayment.COL_VENDOR_ID]+"="+vendorId;
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    //System.out.println(rs.getLong(1));
                    result = result + getTotalPayment(rs.getLong(1));
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
        
        public static double getTotalPaymentByVendorFin(long vendorId){
            double result = 0;
            CONResultSet crs = null;
            try{
                //String sql = "select "+DbBankpoPayment.colNames[DbBankpoPayment.COL_BANKPO_PAYMENT_ID]+" from "+DbBankpoPayment.DB_BANKPO_PAYMENT+
                //    " where "+DbBankpoPayment.colNames[DbBankpoPayment.COL_VENDOR_ID]+"="+vendorId;
                
                String sql = "SELECT sum(bpd."+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_PAYMENT_BY_INV_CURRENCY_AMOUNT]+
                    ") FROM "+DbBankpoPaymentDetail.DB_BANKPO_PAYMENT_DETAIL+" bpd "+
                    " inner join "+DbReceive.DB_RECEIVE+" r on r."+DbReceive.colNames[DbReceive.COL_RECEIVE_ID]+
                    " = bpd."+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_INVOICE_ID]+
                    " where r."+DbReceive.colNames[DbReceive.COL_VENDOR_ID]+" = "+vendorId+
                    " and r."+DbReceive.colNames[DbReceive.COL_PAYMENT_STATUS]+"<>"+I_Project.INV_STATUS_FULL_PAID+
                    " and r."+DbReceive.colNames[DbReceive.COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"'";
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    //System.out.println(rs.getLong(1));
                    result = rs.getDouble(1);//result + getTotalPayment(rs.getLong(1));
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
        
        
        public static double getTotalPayment(long bankpoPayment){
            double result = 0;                      
            CONResultSet crs = null;
            try{
                String sql = "select sum("+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_PAYMENT_BY_INV_CURRENCY_AMOUNT]+") from "+DbBankpoPaymentDetail.DB_BANKPO_PAYMENT_DETAIL+
                    " where "+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_BANKPO_PAYMENT_ID]+"="+bankpoPayment;
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    System.out.println(result);
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

        //dipakai pada terdahulu menggunakan po pada finance
        public static double getTotalInvoice(long vendorId){
            double result = 0;                      
            CONResultSet crs = null;
            try{
                String sql = "select sum("+DbInvoice.colNames[DbInvoice.COL_GRAND_TOTAL]+") from "+DbInvoice.DB_INVOICE+
                    " where "+DbInvoice.colNames[DbInvoice.COL_VENDOR_ID]+"="+vendorId+" and "+DbInvoice.colNames[DbInvoice.COL_STATUS]+"<>'Not Posted'";
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    System.out.println(result);
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
        
        //dipakai 
        public static double getTotalInvoiceFin(long vendorId){
            double result = 0;                      
            CONResultSet crs = null;
            try{                
                String sql = "select sum("+DbReceive.colNames[DbReceive.COL_TOTAL_AMOUNT]+"-"+
                    DbReceive.colNames[DbReceive.COL_DISCOUNT_TOTAL]+"+"+
                    DbReceive.colNames[DbReceive.COL_TOTAL_TAX]+
                    " ) from "+DbReceive.DB_RECEIVE+
                    " where "+DbReceive.colNames[DbReceive.COL_VENDOR_ID]+"="+vendorId+
                    " and "+DbReceive.colNames[DbReceive.COL_PAYMENT_STATUS]+"<>"+I_Project.INV_STATUS_FULL_PAID+
                    " and "+DbReceive.colNames[DbReceive.COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"'";;
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    System.out.println(result);
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

        public static long postJournal(BankpoPayment bankpoPayment){
            
                long oid = 0;
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                //String IDRCODE = DbSystemProperty.getValueByName("CURRENCY_CODE_IDR");
                String USDCODE = DbSystemProperty.getValueByName("CURRENCY_CODE_USD");
                String EURCODE = DbSystemProperty.getValueByName("CURRENCY_CODE_EUR");
                
                double excRate = er.getValueIdr();
                
                if(bankpoPayment.getCurrencyId()!=comp.getBookingCurrencyId()){
                    
                    Currency c = new Currency();
                    try{
                        c = DbCurrency.fetchExc(bankpoPayment.getCurrencyId());
                    }
                    catch(Exception e){
                        
                    }
                    
                    if(c.getCurrencyCode().equals(USDCODE)){
                        excRate = er.getValueUsd();
                    }
                    else{
                        excRate = er.getValueEuro();
                    }
                }
                
                Vector bankAccounts = DbAccLink.list(0,1, "type='"+I_Project.ACC_LINK_GROUP_BANK+"' and location_id="+comp.getSystemLocation(), "");
                AccLink acBank = new AccLink();
                if(bankAccounts!=null && bankAccounts.size()>0){
                    acBank = (AccLink)bankAccounts.get(0);
                }                               
                
                Vector susAccounts = DbAccLink.list(0,1, "type='"+I_Project.ACC_LINK_GROUP_SUSPENSE_ACCOUNT+"' and location_id="+comp.getSystemLocation(), "");
                AccLink acSuspense = new AccLink();
                if(susAccounts!=null && susAccounts.size()>0){
                    acSuspense = (AccLink)susAccounts.get(0);
                }  
                
                System.out.println("\n\n=================== posting jurnal po payment ================\n");
                
                Vector details = DbBankpoPaymentDetail.list(0,0, "bankpo_payment_id="+bankpoPayment.getOID(), "");
                
                System.out.println("details : "+details);
                
                if(bankpoPayment.getOID()!=0 && details!=null && details.size()>0){
                    
                    oid = DbGl.postJournalMain(0, bankpoPayment.getDate(), bankpoPayment.getJournalCounter(), bankpoPayment.getJournalNumber(), bankpoPayment.getJournalPrefix(), I_Project.JOURNAL_TYPE_BANKPAYMENT_PO, 
                        bankpoPayment.getMemo(), bankpoPayment.getOperatorId(), "", bankpoPayment.getOID(), "", bankpoPayment.getTransDate());
                    
                    if(oid!=0){
                        
                        /*if(bankpoPayment.getCurrencyId()!=comp.getBookingCurrencyId()){
                            //journal debet inventory (pembelian)
                            DbGl.postJournalDetail(excRate, acSuspense.getCoaId(), 0, excRate * bankpoPayment.getAmount(),             
                                    bankpoPayment.getAmount(), bankpoPayment.getCurrencyId(), oid, "", 0);
                        }
                        else{
                            //journal debet inventory (pembelian)
                            DbGl.postJournalDetail(excRate, acSuspense.getCoaId(), 0, bankpoPayment.getAmount(),             
                                    bankpoPayment.getAmount(), bankpoPayment.getCurrencyId(), oid, "", 0);
                        }
                         */
                        
                        double amount = 0;
                        
                        for(int i=0; i<details.size(); i++){
                            
                            BankpoPaymentDetail bpd = (BankpoPaymentDetail)details.get(i);
                            
                            System.out.println(" => i="+i+", bpd.getInvoiceId() : "+bpd.getInvoiceId());
                            System.out.println(" bpd.getPaymentAmount() : "+bpd.getPaymentAmount());
                            
                            Receive rec = new Receive();
                            try{
                                rec = DbReceive.fetchExc(bpd.getInvoiceId());
                                //amount = rec.getTotalAmount();// - rec.getDiscountTotal() + rec.getTotalTax();
                                
                                System.out.println(" rec.getTotalAmount() : "+rec.getTotalAmount());
                                
                                Vector items = DbReceiveItem.list(0,0, DbReceiveItem.colNames[DbReceiveItem.COL_RECEIVE_ID]+"="+rec.getOID(), "");
                                if(items!=null && items.size()>0){
                                    for(int x=0; x<items.size(); x++){
                                        ReceiveItem ri = (ReceiveItem)items.get(x);
                                        
                                        amount = ri.getTotalAmount()/rec.getTotalAmount();
                                        
                                        System.out.println("    x : "+x+", ri amount : "+ri.getTotalAmount());
                                        System.out.println("    percent : "+JSPFormater.formatNumber(amount, "#,###.##"));
                                        System.out.println("    bpd.getPaymentAmount() : "+bpd.getPaymentAmount());
                                        System.out.println("    posted to journal : "+JSPFormater.formatNumber((bpd.getPaymentAmount() * amount), "#,###.##"));
                                        System.out.println("    ri.getApCoaId() : "+ri.getApCoaId());                                        
                                        
                                        //journal debet inventory (pembelian)
                                        long oidxx  = DbGl.postJournalDetail(excRate, ri.getApCoaId(), 0, (bpd.getPaymentAmount() * amount),             
                                                (bpd.getPaymentAmount() * amount), bankpoPayment.getCurrencyId(), oid, "", 0);
                                        
                                        System.out.println("    ===> end posting balik ap :: oidxx : "+oidxx+" ===\n\n\n");
                                        
                                    }
                                }
                            }
                            catch(Exception e){
                                System.out.println(e.toString());
                            }
                            
                        }
                        
			
                        
                        //journal credit pada cash/bank
                        if(bankpoPayment.getCurrencyId()!=comp.getBookingCurrencyId()){
                            DbGl.postJournalDetail(excRate, bankpoPayment.getCoaId(), excRate * bankpoPayment.getAmount(), 0,             
                                        bankpoPayment.getAmount(), bankpoPayment.getCurrencyId(), oid, "", 0);
                        }
                        else{
                            DbGl.postJournalDetail(excRate, bankpoPayment.getCoaId(), bankpoPayment.getAmount(), 0,             
                                        bankpoPayment.getAmount(), bankpoPayment.getCurrencyId(), oid, "", 0);
                        }
                        
                        
                        DbGl.optimizeJournal(oid);
                        
                    }
                }
                
                return oid;
            //return 10;
        }
        
        
        public static double getTotalPaymentByVendor(long oidVendor, Date startDate, Date endDate, long unitUsahaId){
            
            //String sql = "SELECT sum(bpp."+colNames[COL_AMOUNT]+") FROM "+DbReceive.DB_RECEIVE+" pr "+
            String sql = "SELECT sum(bpd."+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_PAYMENT_AMOUNT]+") FROM "+DbReceive.DB_RECEIVE+" pr "+
                " inner join "+DbBankpoPaymentDetail.DB_BANKPO_PAYMENT_DETAIL+" bpd "+
                " on bpd."+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_INVOICE_ID]+
                " = pr."+DbReceive.colNames[DbReceive.COL_RECEIVE_ID]+
                " inner join "+DB_BANKPO_PAYMENT+" bpp on bpp."+colNames[COL_BANKPO_PAYMENT_ID]+
                " = bpd."+DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_BANKPO_PAYMENT_ID]+
                " where pr."+DbReceive.colNames[DbReceive.COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"'"+
                //" and pr."+DbReceive.colNames[DbReceive.COL_PAYMENT_STATUS]+"<>"+I_Project.INV_STATUS_FULL_PAID+
                " and pr."+DbReceive.colNames[DbReceive.COL_VENDOR_ID]+"="+oidVendor;
            
            if(startDate!=null && endDate!=null){
                sql = sql + " and (pr."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+" >= '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                        " and pr."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+" <= '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
            }
            else if(startDate==null){
                sql = sql + " and pr."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+" >= '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"'";
            }
            else if(endDate==null){
                sql = sql + " and pr."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+" <= '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'";
            }
            
            if(unitUsahaId!=0){
                sql = sql + " and pr."+DbReceive.colNames[DbReceive.COL_UNIT_USAHA_ID]+"="+unitUsahaId;
            }
            
            System.out.println(sql);
            
            double result = 0;
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
            
            return result;
                
        }
        
        
}
