
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
import com.project.fms.master.*;
import com.project.util.*;
import com.project.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;


public class DbBankDeposit extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_BANK_DEPOSIT = "bank_deposit";

	public static final  int COL_BANK_DEPOSIT_ID = 0;
	public static final  int COL_MEMO = 1;
	public static final  int COL_DATE = 2;
	public static final  int COL_TRANS_DATE = 3;
	public static final  int COL_OPERATOR_ID = 4;
	public static final  int COL_OPERATOR_NAME = 5;
	public static final  int COL_JOURNAL_NUMBER = 6;
	public static final  int COL_JOURNAL_PREFIX = 7;
	public static final  int COL_JOURNAL_COUNTER = 8;
	public static final  int COL_COA_ID = 9;
	public static final  int COL_AMOUNT = 10;
	public static final  int COL_CURRENCY_ID = 11;

	public static final  String[] colNames = {
		"bank_deposit_id",
                "memo",
                "date",
                "trans_date",
                "operator_id",
                "operator_name",
                "journal_number",
                "journal_prefix",
                "journal_counter",
                "coa_id",
                "amount",
                "currency_id"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_STRING,
		TYPE_DATE,
		TYPE_DATE,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_INT,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_LONG
	 }; 

	public DbBankDeposit(){
	}

	public DbBankDeposit(int i) throws CONException { 
		super(new DbBankDeposit()); 
	}

	public DbBankDeposit(String sOid) throws CONException { 
		super(new DbBankDeposit(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbBankDeposit(long lOid) throws CONException { 
		super(new DbBankDeposit(0)); 
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
		return DB_BANK_DEPOSIT;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbBankDeposit().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		BankDeposit bankdeposit = fetchExc(ent.getOID()); 
		ent = (Entity)bankdeposit; 
		return bankdeposit.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((BankDeposit) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((BankDeposit) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static BankDeposit fetchExc(long oid) throws CONException{ 
		try{ 
			BankDeposit bankdeposit = new BankDeposit();
			DbBankDeposit pstBankDeposit = new DbBankDeposit(oid); 
			bankdeposit.setOID(oid);

			bankdeposit.setMemo(pstBankDeposit.getString(COL_MEMO));
			bankdeposit.setDate(pstBankDeposit.getDate(COL_DATE));
			bankdeposit.setTransDate(pstBankDeposit.getDate(COL_TRANS_DATE));
			bankdeposit.setOperatorId(pstBankDeposit.getlong(COL_OPERATOR_ID));
			bankdeposit.setOperatorName(pstBankDeposit.getString(COL_OPERATOR_NAME));
			bankdeposit.setJournalNumber(pstBankDeposit.getString(COL_JOURNAL_NUMBER));
			bankdeposit.setJournalPrefix(pstBankDeposit.getString(COL_JOURNAL_PREFIX));
			bankdeposit.setJournalCounter(pstBankDeposit.getInt(COL_JOURNAL_COUNTER));
			bankdeposit.setCoaId(pstBankDeposit.getlong(COL_COA_ID));
			bankdeposit.setAmount(pstBankDeposit.getdouble(COL_AMOUNT));
			bankdeposit.setCurrencyId(pstBankDeposit.getlong(COL_CURRENCY_ID));

			return bankdeposit; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankDeposit(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(BankDeposit bankdeposit) throws CONException{ 
		try{ 
			DbBankDeposit pstBankDeposit = new DbBankDeposit(0);

			pstBankDeposit.setString(COL_MEMO, bankdeposit.getMemo());
			pstBankDeposit.setDate(COL_DATE, bankdeposit.getDate());
			pstBankDeposit.setDate(COL_TRANS_DATE, bankdeposit.getTransDate());
			pstBankDeposit.setLong(COL_OPERATOR_ID, bankdeposit.getOperatorId());
			pstBankDeposit.setString(COL_OPERATOR_NAME, bankdeposit.getOperatorName());
			pstBankDeposit.setString(COL_JOURNAL_NUMBER, bankdeposit.getJournalNumber());
			pstBankDeposit.setString(COL_JOURNAL_PREFIX, bankdeposit.getJournalPrefix());
			pstBankDeposit.setInt(COL_JOURNAL_COUNTER, bankdeposit.getJournalCounter());
			pstBankDeposit.setLong(COL_COA_ID, bankdeposit.getCoaId());
			pstBankDeposit.setDouble(COL_AMOUNT, bankdeposit.getAmount());
			pstBankDeposit.setLong(COL_CURRENCY_ID, bankdeposit.getCurrencyId());

			pstBankDeposit.insert(); 
			bankdeposit.setOID(pstBankDeposit.getlong(COL_BANK_DEPOSIT_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankDeposit(0),CONException.UNKNOWN); 
		}
		return bankdeposit.getOID();
	}

	public static long updateExc(BankDeposit bankdeposit) throws CONException{ 
		try{ 
			if(bankdeposit.getOID() != 0){ 
				DbBankDeposit pstBankDeposit = new DbBankDeposit(bankdeposit.getOID());

				pstBankDeposit.setString(COL_MEMO, bankdeposit.getMemo());
				pstBankDeposit.setDate(COL_DATE, bankdeposit.getDate());
				pstBankDeposit.setDate(COL_TRANS_DATE, bankdeposit.getTransDate());
				pstBankDeposit.setLong(COL_OPERATOR_ID, bankdeposit.getOperatorId());
				pstBankDeposit.setString(COL_OPERATOR_NAME, bankdeposit.getOperatorName());
				pstBankDeposit.setString(COL_JOURNAL_NUMBER, bankdeposit.getJournalNumber());
				pstBankDeposit.setString(COL_JOURNAL_PREFIX, bankdeposit.getJournalPrefix());
				pstBankDeposit.setInt(COL_JOURNAL_COUNTER, bankdeposit.getJournalCounter());
				pstBankDeposit.setLong(COL_COA_ID, bankdeposit.getCoaId());
				pstBankDeposit.setDouble(COL_AMOUNT, bankdeposit.getAmount());
				pstBankDeposit.setLong(COL_CURRENCY_ID, bankdeposit.getCurrencyId());

				pstBankDeposit.update(); 
				return bankdeposit.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankDeposit(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbBankDeposit pstBankDeposit = new DbBankDeposit(oid);
			pstBankDeposit.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbBankDeposit(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_BANK_DEPOSIT; 
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
				BankDeposit bankdeposit = new BankDeposit();
				resultToObject(rs, bankdeposit);
				lists.add(bankdeposit);
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

	private static void resultToObject(ResultSet rs, BankDeposit bankdeposit){
		try{
			bankdeposit.setOID(rs.getLong(DbBankDeposit.colNames[DbBankDeposit.COL_BANK_DEPOSIT_ID]));
			bankdeposit.setMemo(rs.getString(DbBankDeposit.colNames[DbBankDeposit.COL_MEMO]));
			bankdeposit.setDate(rs.getDate(DbBankDeposit.colNames[DbBankDeposit.COL_DATE]));
			bankdeposit.setTransDate(rs.getDate(DbBankDeposit.colNames[DbBankDeposit.COL_TRANS_DATE]));
			bankdeposit.setOperatorId(rs.getLong(DbBankDeposit.colNames[DbBankDeposit.COL_OPERATOR_ID]));
			bankdeposit.setOperatorName(rs.getString(DbBankDeposit.colNames[DbBankDeposit.COL_OPERATOR_NAME]));
			bankdeposit.setJournalNumber(rs.getString(DbBankDeposit.colNames[DbBankDeposit.COL_JOURNAL_NUMBER]));
			bankdeposit.setJournalPrefix(rs.getString(DbBankDeposit.colNames[DbBankDeposit.COL_JOURNAL_PREFIX]));
			bankdeposit.setJournalCounter(rs.getInt(DbBankDeposit.colNames[DbBankDeposit.COL_JOURNAL_COUNTER]));
			bankdeposit.setCoaId(rs.getLong(DbBankDeposit.colNames[DbBankDeposit.COL_COA_ID]));
			bankdeposit.setAmount(rs.getDouble(DbBankDeposit.colNames[DbBankDeposit.COL_AMOUNT]));
			bankdeposit.setCurrencyId(rs.getLong(DbBankDeposit.colNames[DbBankDeposit.COL_CURRENCY_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long bankDepositId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_BANK_DEPOSIT + " WHERE " + 
						DbBankDeposit.colNames[DbBankDeposit.COL_BANK_DEPOSIT_ID] + " = " + bankDepositId;

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
			String sql = "SELECT COUNT("+ DbBankDeposit.colNames[DbBankDeposit.COL_BANK_DEPOSIT_ID] + ") FROM " + DB_BANK_DEPOSIT;
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
			  	   BankDeposit bankdeposit = (BankDeposit)list.get(ls);
				   if(oid == bankdeposit.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static double getDeposiByPeriod(Periode openPeriod, long coaId){
                double result = 0;
                CONResultSet crs = null;
                try{
                    String sql = "select sum("+colNames[COL_AMOUNT]+") from "+DB_BANK_DEPOSIT+
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
                    String sql = "select max(journal_counter) from "+DB_BANK_DEPOSIT+" where "+
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
                
                Coa coa = new Coa();
                try{
                    coa = DbCoa.fetchExc(oid);
                }
                catch(Exception e){                    
                }

                String code = sysLocation.getCode();//DbSystemProperty.getValueByName("LOCATION_CODE");
                
                if(coa.getIsNeedExtra()==1){
                    code = code + coa.getDebetPrefixCode();//sysCompany.getBankDepositCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");
                }
                else{
                    code = code + sysCompany.getBankDepositCode();//DbSystemProperty.getValueByName("JOURNAL_RECEIPT_CODE");
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
        public static void postJournal(BankDeposit cr, Vector details){
                Company comp = DbCompany.getCompany();
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                try{
                    cr = DbBankDeposit.fetchExc(cr.getOID());
                }
                catch(Exception e){
                }
                
                if(cr.getOID()!=0 && details!=null && details.size()>0){
                    long oid = DbGl.postJournalMain(0, cr.getDate(), cr.getJournalCounter(), cr.getJournalNumber(), cr.getJournalPrefix(), I_Project.JOURNAL_TYPE_CASH_RECEIVE, 
                        cr.getMemo(), cr.getOperatorId(), cr.getOperatorName(), cr.getOID(), "", cr.getTransDate());
                    
                    if(oid!=0){
                        for(int i=0; i<details.size(); i++){
                            BankDepositDetail crd = (BankDepositDetail)details.get(i);
                            //journal credit pada pendapatan
                            DbGl.postJournalDetail(crd.getBookedRate(), crd.getCoaId(), crd.getAmount(), 0,             
                                    crd.getForeignAmount(), crd.getForeignCurrencyId(), oid, crd.getMemo(), 0);
                        }
                        
                        //journal debet pada cash
                        DbGl.postJournalDetail(er.getValueIdr(), cr.getCoaId(), 0, cr.getAmount(),             
                                    0, comp.getBookingCurrencyId(), oid, cr.getMemo(), 0);
                    }
                }
        }
        
        public static void updateAmount(BankDeposit bankDeposit){
            String sql = "";
            CONResultSet crs = null;
            double amount = 0;
            try{
                sql = "SELECT sum("+DbBankDepositDetail.colNames[DbBankDepositDetail.COL_AMOUNT]+") FROM "+
                      DbBankDepositDetail.DB_BANK_DEPOSIT_DETAIL+" p where "+
                      DbBankDepositDetail.colNames[DbBankDepositDetail.COL_BANK_DEPOSIT_ID]+"="+bankDeposit.getOID();                
                
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
                bankDeposit.setAmount(amount);
                DbBankDeposit.updateExc(bankDeposit);
            }
            catch(Exception e){
                
            }
        }
        
}
