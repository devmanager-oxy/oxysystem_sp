
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
import com.project.fms.activity.*;


public class DbBanknonpoPaymentDetail extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_BANKNONPO_PAYMENT_DETAIL = "banknonpo_payment_detail";

	public static final  int COL_BANKNONPO_PAYMENT_ID = 0;
	public static final  int COL_BANKNONPO_PAYMENT_DETAIL_ID = 1;
	public static final  int COL_COA_ID = 2;
	public static final  int COL_AMOUNT = 3;
	public static final  int COL_MEMO = 4;
        public static final  int COL_FOREIGN_CURRENCY_ID = 5;
        public static final  int COL_FOREIGN_AMOUNT = 6;
        public static final  int COL_BOOKED_RATE = 7;
        
        public static final  int COL_DEPARTMENT_ID = 8;

	public static final  String[] colNames = {
		"banknonpo_payment_id",		
		"banknonpo_payment_detail_id",
		"coa_id",
		"amount",
		"memo",
                "foreign_currency_id",
                "foreign_amount",
                "booked_rate",
                
                "department_id"

	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG,
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_STRING,
                TYPE_LONG,
		TYPE_FLOAT,
                TYPE_FLOAT,
                
                TYPE_LONG
	 }; 

	public DbBanknonpoPaymentDetail(){
	}

	public DbBanknonpoPaymentDetail(int i) throws CONException { 
		super(new DbBanknonpoPaymentDetail()); 
	}

	public DbBanknonpoPaymentDetail(String sOid) throws CONException { 
		super(new DbBanknonpoPaymentDetail(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbBanknonpoPaymentDetail(long lOid) throws CONException { 
		super(new DbBanknonpoPaymentDetail(0)); 
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
		return DB_BANKNONPO_PAYMENT_DETAIL;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbBanknonpoPaymentDetail().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		BanknonpoPaymentDetail banknonpopaymentdetail = fetchExc(ent.getOID()); 
		ent = (Entity)banknonpopaymentdetail; 
		return banknonpopaymentdetail.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((BanknonpoPaymentDetail) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((BanknonpoPaymentDetail) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static BanknonpoPaymentDetail fetchExc(long oid) throws CONException{ 
		try{ 
			BanknonpoPaymentDetail banknonpopaymentdetail = new BanknonpoPaymentDetail();
			DbBanknonpoPaymentDetail dbBanknonpoPaymentDetail = new DbBanknonpoPaymentDetail(oid); 
			banknonpopaymentdetail.setOID(oid);

			banknonpopaymentdetail.setBanknonpoPaymentId(dbBanknonpoPaymentDetail.getlong(COL_BANKNONPO_PAYMENT_ID));
			banknonpopaymentdetail.setCoaId(dbBanknonpoPaymentDetail.getlong(COL_COA_ID));
			banknonpopaymentdetail.setAmount(dbBanknonpoPaymentDetail.getdouble(COL_AMOUNT));
			banknonpopaymentdetail.setMemo(dbBanknonpoPaymentDetail.getString(COL_MEMO));
                        
                        banknonpopaymentdetail.setForeignCurrencyId(dbBanknonpoPaymentDetail.getlong(COL_FOREIGN_CURRENCY_ID));
                        banknonpopaymentdetail.setForeignAmount(dbBanknonpoPaymentDetail.getdouble(COL_FOREIGN_AMOUNT));
                        banknonpopaymentdetail.setBookedRate(dbBanknonpoPaymentDetail.getdouble(COL_BOOKED_RATE));
                        
                        banknonpopaymentdetail.setDepartmentId(dbBanknonpoPaymentDetail.getlong(COL_DEPARTMENT_ID));

			return banknonpopaymentdetail; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPaymentDetail(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(BanknonpoPaymentDetail banknonpopaymentdetail) throws CONException{ 
		try{ 
			DbBanknonpoPaymentDetail dbBanknonpoPaymentDetail = new DbBanknonpoPaymentDetail(0);

			dbBanknonpoPaymentDetail.setLong(COL_BANKNONPO_PAYMENT_ID, banknonpopaymentdetail.getBanknonpoPaymentId());
			dbBanknonpoPaymentDetail.setLong(COL_COA_ID, banknonpopaymentdetail.getCoaId());
			dbBanknonpoPaymentDetail.setDouble(COL_AMOUNT, banknonpopaymentdetail.getAmount());
			dbBanknonpoPaymentDetail.setString(COL_MEMO, banknonpopaymentdetail.getMemo());
                        
                        dbBanknonpoPaymentDetail.setLong(COL_FOREIGN_CURRENCY_ID, banknonpopaymentdetail.getForeignCurrencyId());
                        dbBanknonpoPaymentDetail.setDouble(COL_FOREIGN_AMOUNT, banknonpopaymentdetail.getForeignAmount());
                        dbBanknonpoPaymentDetail.setDouble(COL_BOOKED_RATE, banknonpopaymentdetail.getBookedRate());
                        
                        dbBanknonpoPaymentDetail.setLong(COL_DEPARTMENT_ID, banknonpopaymentdetail.getDepartmentId());
                        

			dbBanknonpoPaymentDetail.insert(); 
			banknonpopaymentdetail.setOID(dbBanknonpoPaymentDetail.getlong(COL_BANKNONPO_PAYMENT_DETAIL_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPaymentDetail(0),CONException.UNKNOWN); 
		}
		return banknonpopaymentdetail.getOID();
	}

	public static long updateExc(BanknonpoPaymentDetail banknonpopaymentdetail) throws CONException{ 
		try{ 
			if(banknonpopaymentdetail.getOID() != 0){ 
				DbBanknonpoPaymentDetail dbBanknonpoPaymentDetail = new DbBanknonpoPaymentDetail(banknonpopaymentdetail.getOID());

				dbBanknonpoPaymentDetail.setLong(COL_BANKNONPO_PAYMENT_ID, banknonpopaymentdetail.getBanknonpoPaymentId());
				dbBanknonpoPaymentDetail.setLong(COL_COA_ID, banknonpopaymentdetail.getCoaId());
				dbBanknonpoPaymentDetail.setDouble(COL_AMOUNT, banknonpopaymentdetail.getAmount());
				dbBanknonpoPaymentDetail.setString(COL_MEMO, banknonpopaymentdetail.getMemo());
                                
                                dbBanknonpoPaymentDetail.setLong(COL_FOREIGN_CURRENCY_ID, banknonpopaymentdetail.getForeignCurrencyId());
                                dbBanknonpoPaymentDetail.setDouble(COL_FOREIGN_AMOUNT, banknonpopaymentdetail.getForeignAmount());
                                dbBanknonpoPaymentDetail.setDouble(COL_BOOKED_RATE, banknonpopaymentdetail.getBookedRate());
                                
                                dbBanknonpoPaymentDetail.setLong(COL_DEPARTMENT_ID, banknonpopaymentdetail.getDepartmentId());

				dbBanknonpoPaymentDetail.update(); 
				return banknonpopaymentdetail.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPaymentDetail(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbBanknonpoPaymentDetail dbBanknonpoPaymentDetail = new DbBanknonpoPaymentDetail(oid);
			dbBanknonpoPaymentDetail.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBanknonpoPaymentDetail(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_BANKNONPO_PAYMENT_DETAIL; 
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
				BanknonpoPaymentDetail banknonpopaymentdetail = new BanknonpoPaymentDetail();
				resultToObject(rs, banknonpopaymentdetail);
				lists.add(banknonpopaymentdetail);
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

	private static void resultToObject(ResultSet rs, BanknonpoPaymentDetail banknonpopaymentdetail){
		try{
			banknonpopaymentdetail.setOID(rs.getLong(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_BANKNONPO_PAYMENT_DETAIL_ID]));
			banknonpopaymentdetail.setBanknonpoPaymentId(rs.getLong(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_BANKNONPO_PAYMENT_ID]));
			banknonpopaymentdetail.setCoaId(rs.getLong(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_COA_ID]));
			banknonpopaymentdetail.setAmount(rs.getDouble(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_AMOUNT]));
			banknonpopaymentdetail.setMemo(rs.getString(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_MEMO]));
                        
                        banknonpopaymentdetail.setForeignCurrencyId(rs.getLong(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_FOREIGN_CURRENCY_ID]));
                        banknonpopaymentdetail.setForeignAmount(rs.getDouble(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_FOREIGN_AMOUNT]));
                        banknonpopaymentdetail.setBookedRate(rs.getDouble(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_BOOKED_RATE]));
                        
                        banknonpopaymentdetail.setDepartmentId(rs.getLong(DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_DEPARTMENT_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long banknonpoPaymentDetailId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_BANKNONPO_PAYMENT_DETAIL + " WHERE " + 
						DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_BANKNONPO_PAYMENT_ID] + " = " + banknonpoPaymentDetailId;

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
			String sql = "SELECT COUNT("+ DbBanknonpoPaymentDetail.colNames[DbBanknonpoPaymentDetail.COL_BANKNONPO_PAYMENT_DETAIL_ID] + ") FROM " + DB_BANKNONPO_PAYMENT_DETAIL;
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
			  	   BanknonpoPaymentDetail banknonpopaymentdetail = (BanknonpoPaymentDetail)list.get(ls);
				   if(oid == banknonpopaymentdetail.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        public static void saveAllDetail(BanknonpoPayment banknonpoPayment, Vector listBanknonpoPaymentDetail){
            if(listBanknonpoPaymentDetail!=null && listBanknonpoPaymentDetail.size()>0){
                for(int i=0; i<listBanknonpoPaymentDetail.size(); i++){
                    BanknonpoPaymentDetail crd = (BanknonpoPaymentDetail)listBanknonpoPaymentDetail.get(i);
                    crd.setBanknonpoPaymentId(banknonpoPayment.getOID());
                    System.out.println("Non-po-Type = "+banknonpoPayment.getType());
                    if(banknonpoPayment.getType()==1)
                    {
                        crd.setCoaId(crd.getCoaIdTemp());
                    }
                    try{
                        DbBanknonpoPaymentDetail.insertExc(crd);
                    }
                    catch(Exception e){
                        
                    }
                }                
            }
            
            DbBanknonpoPayment.updateAmount(banknonpoPayment); 
        }
        
        public static Vector getActivityPredefined(long paymentOID){
            Vector result = new Vector();
            ActivityPeriod ap = DbActivityPeriod.getOpenPeriod();
            CONResultSet crs = null;
            try{
                String sql = "SELECT c.*, pd.* FROM coa_activity_budget c inner join banknonpo_payment_detail pd on pd.coa_id=c.coa_id "+
                    " where pd.banknonpo_payment_id="+paymentOID+" and c.activity_period_id = "+ap.getOID();
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CoaActivityBudget cab = new CoaActivityBudget();
                    BanknonpoPaymentDetail ppd = new BanknonpoPaymentDetail();
                    DbCoaActivityBudget.resultToObject(rs, cab);
                    DbBanknonpoPaymentDetail.resultToObject(rs, ppd);
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
