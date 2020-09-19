

package com.project.fms.transaction; 

/* package java */ 
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.project.util.lang.I_Language;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*; 
import com.project.fms.transaction.*;
import com.project.fms.activity.*;

public class DbBankpoPaymentDetail extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_BANKPO_PAYMENT_DETAIL = "bankpo_payment_detail";

	public static final  int COL_BANKPO_PAYMENT_ID = 0;
	public static final  int COL_BANKPO_PAYMENT_DETAIL_ID = 1;
	public static final  int COL_COA_ID = 2;	
	public static final  int COL_MEMO = 3;
	public static final  int COL_INVOICE_ID = 4;
	public static final  int COL_CURRENCY_ID = 5;
	public static final  int COL_BOOKED_RATE = 6;
	public static final  int COL_PAYMENT_BY_INV_CURRENCY_AMOUNT = 7;
	public static final  int COL_PAYMENT_AMOUNT = 8;
        
	public static final  String[] colNames = {
		"bankpo_payment_id",		
		"bankpo_payment_detail_id",
		"coa_id",
		"memo",
		"invoice_id",
		"currency_id",
		"booked_rate",
		"payment_by_inv_currency_amount",
		"payment_amount"

	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG,
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_FLOAT
	 }; 

	public DbBankpoPaymentDetail(){
	}

	public DbBankpoPaymentDetail(int i) throws CONException { 
		super(new DbBankpoPaymentDetail()); 
	}

	public DbBankpoPaymentDetail(String sOid) throws CONException { 
		super(new DbBankpoPaymentDetail(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbBankpoPaymentDetail(long lOid) throws CONException { 
		super(new DbBankpoPaymentDetail(0)); 
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
		return DB_BANKPO_PAYMENT_DETAIL;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbBankpoPaymentDetail().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		BankpoPaymentDetail bankpopaymentdetail = fetchExc(ent.getOID()); 
		ent = (Entity)bankpopaymentdetail; 
		return bankpopaymentdetail.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((BankpoPaymentDetail) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((BankpoPaymentDetail) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static BankpoPaymentDetail fetchExc(long oid) throws CONException{ 
		try{ 
			BankpoPaymentDetail bankpopaymentdetail = new BankpoPaymentDetail();
			DbBankpoPaymentDetail pstBankpoPaymentDetail = new DbBankpoPaymentDetail(oid); 
			bankpopaymentdetail.setOID(oid);

			bankpopaymentdetail.setBankpoPaymentId(pstBankpoPaymentDetail.getlong(COL_BANKPO_PAYMENT_ID));
			bankpopaymentdetail.setCoaId(pstBankpoPaymentDetail.getlong(COL_COA_ID));
			bankpopaymentdetail.setMemo(pstBankpoPaymentDetail.getString(COL_MEMO));
			bankpopaymentdetail.setInvoiceId(pstBankpoPaymentDetail.getlong(COL_INVOICE_ID));
			bankpopaymentdetail.setCurrencyId(pstBankpoPaymentDetail.getlong(COL_CURRENCY_ID));
			bankpopaymentdetail.setBookedRate(pstBankpoPaymentDetail.getdouble(COL_BOOKED_RATE));
			bankpopaymentdetail.setPaymentByInvCurrencyAmount(pstBankpoPaymentDetail.getdouble(COL_PAYMENT_BY_INV_CURRENCY_AMOUNT));
			bankpopaymentdetail.setPaymentAmount(pstBankpoPaymentDetail.getdouble(COL_PAYMENT_AMOUNT));
                        

			return bankpopaymentdetail; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPaymentDetail(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(BankpoPaymentDetail bankpopaymentdetail) throws CONException{ 
		try{ 
			DbBankpoPaymentDetail pstBankpoPaymentDetail = new DbBankpoPaymentDetail(0);

			pstBankpoPaymentDetail.setLong(COL_BANKPO_PAYMENT_ID, bankpopaymentdetail.getBankpoPaymentId());
			pstBankpoPaymentDetail.setLong(COL_COA_ID, bankpopaymentdetail.getCoaId());
			pstBankpoPaymentDetail.setString(COL_MEMO, bankpopaymentdetail.getMemo());
			pstBankpoPaymentDetail.setLong(COL_INVOICE_ID, bankpopaymentdetail.getInvoiceId());
			pstBankpoPaymentDetail.setLong(COL_CURRENCY_ID, bankpopaymentdetail.getCurrencyId());
			pstBankpoPaymentDetail.setDouble(COL_BOOKED_RATE, bankpopaymentdetail.getBookedRate());
			pstBankpoPaymentDetail.setDouble(COL_PAYMENT_BY_INV_CURRENCY_AMOUNT, bankpopaymentdetail.getPaymentByInvCurrencyAmount());
			pstBankpoPaymentDetail.setDouble(COL_PAYMENT_AMOUNT, bankpopaymentdetail.getPaymentAmount());
                        
			pstBankpoPaymentDetail.insert(); 
			bankpopaymentdetail.setOID(pstBankpoPaymentDetail.getlong(COL_BANKPO_PAYMENT_DETAIL_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPaymentDetail(0),CONException.UNKNOWN); 
		}
		return bankpopaymentdetail.getOID();
	}

	public static long updateExc(BankpoPaymentDetail bankpopaymentdetail) throws CONException{ 
		try{ 
			if(bankpopaymentdetail.getOID() != 0){ 
				DbBankpoPaymentDetail pstBankpoPaymentDetail = new DbBankpoPaymentDetail(bankpopaymentdetail.getOID());

				pstBankpoPaymentDetail.setLong(COL_BANKPO_PAYMENT_ID, bankpopaymentdetail.getBankpoPaymentId());
				pstBankpoPaymentDetail.setLong(COL_COA_ID, bankpopaymentdetail.getCoaId());
				pstBankpoPaymentDetail.setString(COL_MEMO, bankpopaymentdetail.getMemo());
				pstBankpoPaymentDetail.setLong(COL_INVOICE_ID, bankpopaymentdetail.getInvoiceId());
				pstBankpoPaymentDetail.setDouble(COL_CURRENCY_ID, bankpopaymentdetail.getCurrencyId());
				pstBankpoPaymentDetail.setDouble(COL_BOOKED_RATE, bankpopaymentdetail.getBookedRate());
				pstBankpoPaymentDetail.setDouble(COL_PAYMENT_BY_INV_CURRENCY_AMOUNT, bankpopaymentdetail.getPaymentByInvCurrencyAmount());
				pstBankpoPaymentDetail.setDouble(COL_PAYMENT_AMOUNT, bankpopaymentdetail.getPaymentAmount());
                                
				pstBankpoPaymentDetail.update(); 
				return bankpopaymentdetail.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPaymentDetail(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbBankpoPaymentDetail pstBankpoPaymentDetail = new DbBankpoPaymentDetail(oid);
			pstBankpoPaymentDetail.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankpoPaymentDetail(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_BANKPO_PAYMENT_DETAIL; 
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
				BankpoPaymentDetail bankpopaymentdetail = new BankpoPaymentDetail();
				resultToObject(rs, bankpopaymentdetail);
				lists.add(bankpopaymentdetail);
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

	private static void resultToObject(ResultSet rs, BankpoPaymentDetail bankpopaymentdetail){
		try{
			bankpopaymentdetail.setOID(rs.getLong(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_BANKPO_PAYMENT_DETAIL_ID]));
			bankpopaymentdetail.setBankpoPaymentId(rs.getLong(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_BANKPO_PAYMENT_ID]));
			bankpopaymentdetail.setCoaId(rs.getLong(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_COA_ID]));
			bankpopaymentdetail.setMemo(rs.getString(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_MEMO]));
			bankpopaymentdetail.setInvoiceId(rs.getLong(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_INVOICE_ID]));
			bankpopaymentdetail.setCurrencyId(rs.getLong(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_CURRENCY_ID]));
			bankpopaymentdetail.setBookedRate(rs.getDouble(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_BOOKED_RATE]));
			bankpopaymentdetail.setPaymentByInvCurrencyAmount(rs.getDouble(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_PAYMENT_BY_INV_CURRENCY_AMOUNT]));
			bankpopaymentdetail.setPaymentAmount(rs.getDouble(DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_PAYMENT_AMOUNT]));
                        
		}catch(Exception e){ }
	}

	public static boolean checkOID(long bankpoPaymentDetailId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_BANKPO_PAYMENT_DETAIL + " WHERE " + 
						DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_BANKPO_PAYMENT_ID] + " = " + bankpoPaymentDetailId;

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
			String sql = "SELECT COUNT("+ DbBankpoPaymentDetail.colNames[DbBankpoPaymentDetail.COL_BANKPO_PAYMENT_DETAIL_ID] + ") FROM " + DB_BANKPO_PAYMENT_DETAIL;
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
			  	   BankpoPaymentDetail bankpopaymentdetail = (BankpoPaymentDetail)list.get(ls);
				   if(oid == bankpopaymentdetail.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static void insertItems(long bankpoPayementOID, Vector vInvEdit){
            
            if(vInvEdit!=null && vInvEdit.size()>0){
                
                deleteByInvoiceId(bankpoPayementOID);
                
                for(int i=0; i<vInvEdit.size(); i++){
                    BankpoPaymentDetail ii = (BankpoPaymentDetail)vInvEdit.get(i);
                    if(ii.getPaymentAmount()>0){
                        try{
                            ii.setBankpoPaymentId(bankpoPayementOID);
                            DbBankpoPaymentDetail.insertExc(ii);
                        }
                        catch(Exception e){
                            System.out.println(e.toString());
                        }
                    }
                }
            }
        }
        
        public static void deleteByInvoiceId(long bankpoPayementOID){
            try{
                String str = "delete from "+DB_BANKPO_PAYMENT_DETAIL+" where "+colNames[COL_BANKPO_PAYMENT_ID]+"="+ bankpoPayementOID;
                CONHandler.execUpdate(str);
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
        }
        
        public static Vector getActivityPredefined(long paymentOID){
            Vector result = new Vector();
            ActivityPeriod ap = DbActivityPeriod.getOpenPeriod();
            CONResultSet crs = null;
            try{
                String sql = "SELECT c.*, pd.* FROM coa_activity_budget c inner join bankpo_payment_detail pd on pd.coa_id=c.coa_id "+
                    " where pd.bankpo_payment_id="+paymentOID+" and c.activity_period_id = "+ap.getOID();
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CoaActivityBudget cab = new CoaActivityBudget();
                    BankpoPaymentDetail ppd = new BankpoPaymentDetail();
                    DbCoaActivityBudget.resultToObject(rs, cab);
                    DbBankpoPaymentDetail.resultToObject(rs, ppd);
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
        
}
