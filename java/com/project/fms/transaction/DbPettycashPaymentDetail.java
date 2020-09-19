
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
import com.project.fms.activity.*;


public class DbPettycashPaymentDetail extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_PETTYCASH_PAYMENT_DETAIL = "pettycash_payment_detail";

	public static final  int COL_PETTYCASH_PAYMENT_ID = 0;
	public static final  int COL_PETTYCASH_PAYMENT_DETAIL_ID = 1;
	public static final  int COL_COA_ID = 2;
	public static final  int COL_AMOUNT = 3;
	public static final  int COL_MEMO = 4;
        
        public static final  int COL_DEPARTMENT_ID = 5;

	public static final  String[] colNames = {
		"pettycash_payment_id",
		"pettycash_payment_detail_id",
		"coa_id",
		"amount",
		"memo",
                "department_id"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG,
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_STRING,
                TYPE_LONG
	 }; 

	public DbPettycashPaymentDetail(){
	}

	public DbPettycashPaymentDetail(int i) throws CONException { 
		super(new DbPettycashPaymentDetail()); 
	}

	public DbPettycashPaymentDetail(String sOid) throws CONException { 
		super(new DbPettycashPaymentDetail(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbPettycashPaymentDetail(long lOid) throws CONException { 
		super(new DbPettycashPaymentDetail(0)); 
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
		return DB_PETTYCASH_PAYMENT_DETAIL;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbPettycashPaymentDetail().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		PettycashPaymentDetail pettycashpaymentdetail = fetchExc(ent.getOID()); 
		ent = (Entity)pettycashpaymentdetail; 
		return pettycashpaymentdetail.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((PettycashPaymentDetail) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((PettycashPaymentDetail) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static PettycashPaymentDetail fetchExc(long oid) throws CONException{ 
		try{ 
			PettycashPaymentDetail pettycashpaymentdetail = new PettycashPaymentDetail();
			DbPettycashPaymentDetail pstPettycashPaymentDetail = new DbPettycashPaymentDetail(oid); 
			pettycashpaymentdetail.setOID(oid);

			pettycashpaymentdetail.setPettycashPaymentId(pstPettycashPaymentDetail.getlong(COL_PETTYCASH_PAYMENT_ID));
			pettycashpaymentdetail.setCoaId(pstPettycashPaymentDetail.getlong(COL_COA_ID));
			pettycashpaymentdetail.setAmount(pstPettycashPaymentDetail.getdouble(COL_AMOUNT));
			pettycashpaymentdetail.setMemo(pstPettycashPaymentDetail.getString(COL_MEMO));
                        
                        pettycashpaymentdetail.setDepartmentId(pstPettycashPaymentDetail.getlong(COL_DEPARTMENT_ID));

			return pettycashpaymentdetail; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPaymentDetail(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(PettycashPaymentDetail pettycashpaymentdetail) throws CONException{ 
		try{ 
			DbPettycashPaymentDetail pstPettycashPaymentDetail = new DbPettycashPaymentDetail(0);

			pstPettycashPaymentDetail.setLong(COL_PETTYCASH_PAYMENT_ID, pettycashpaymentdetail.getPettycashPaymentId());
			pstPettycashPaymentDetail.setLong(COL_COA_ID, pettycashpaymentdetail.getCoaId());
			pstPettycashPaymentDetail.setDouble(COL_AMOUNT, pettycashpaymentdetail.getAmount());
			pstPettycashPaymentDetail.setString(COL_MEMO, pettycashpaymentdetail.getMemo());
                        
                        pstPettycashPaymentDetail.setLong(COL_DEPARTMENT_ID, pettycashpaymentdetail.getDepartmentId());

			pstPettycashPaymentDetail.insert(); 
			pettycashpaymentdetail.setOID(pstPettycashPaymentDetail.getlong(COL_PETTYCASH_PAYMENT_DETAIL_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPaymentDetail(0),CONException.UNKNOWN); 
		}
		return pettycashpaymentdetail.getOID();
	}

	public static long updateExc(PettycashPaymentDetail pettycashpaymentdetail) throws CONException{ 
		try{ 
			if(pettycashpaymentdetail.getOID() != 0){ 
				DbPettycashPaymentDetail pstPettycashPaymentDetail = new DbPettycashPaymentDetail(pettycashpaymentdetail.getOID());

				pstPettycashPaymentDetail.setLong(COL_PETTYCASH_PAYMENT_ID, pettycashpaymentdetail.getPettycashPaymentId());
				pstPettycashPaymentDetail.setLong(COL_COA_ID, pettycashpaymentdetail.getCoaId());
				pstPettycashPaymentDetail.setDouble(COL_AMOUNT, pettycashpaymentdetail.getAmount());
				pstPettycashPaymentDetail.setString(COL_MEMO, pettycashpaymentdetail.getMemo());
                                
                                pstPettycashPaymentDetail.setLong(COL_DEPARTMENT_ID, pettycashpaymentdetail.getDepartmentId());

				pstPettycashPaymentDetail.update(); 
				return pettycashpaymentdetail.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPaymentDetail(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbPettycashPaymentDetail pstPettycashPaymentDetail = new DbPettycashPaymentDetail(oid);
			pstPettycashPaymentDetail.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbPettycashPaymentDetail(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_PETTYCASH_PAYMENT_DETAIL; 
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
				PettycashPaymentDetail pettycashpaymentdetail = new PettycashPaymentDetail();
				resultToObject(rs, pettycashpaymentdetail);
				lists.add(pettycashpaymentdetail);
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

	private static void resultToObject(ResultSet rs, PettycashPaymentDetail pettycashpaymentdetail){
		try{
			pettycashpaymentdetail.setOID(rs.getLong(DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_PETTYCASH_PAYMENT_DETAIL_ID]));
			pettycashpaymentdetail.setPettycashPaymentId(rs.getLong(DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_PETTYCASH_PAYMENT_ID]));
			pettycashpaymentdetail.setCoaId(rs.getLong(DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_COA_ID]));
			pettycashpaymentdetail.setAmount(rs.getDouble(DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_AMOUNT]));
			pettycashpaymentdetail.setMemo(rs.getString(DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_MEMO]));
                        
                        pettycashpaymentdetail.setDepartmentId(rs.getLong(DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_DEPARTMENT_ID]));
                        

		}catch(Exception e){ }
	}

	public static boolean checkOID(long pettycashPaymentDetailId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_PETTYCASH_PAYMENT_DETAIL + " WHERE " + 
						DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_PETTYCASH_PAYMENT_ID] + " = " + pettycashPaymentDetailId;

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
			String sql = "SELECT COUNT("+ DbPettycashPaymentDetail.colNames[DbPettycashPaymentDetail.COL_PETTYCASH_PAYMENT_DETAIL_ID] + ") FROM " + DB_PETTYCASH_PAYMENT_DETAIL;
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
			  	   PettycashPaymentDetail pettycashpaymentdetail = (PettycashPaymentDetail)list.get(ls);
				   if(oid == pettycashpaymentdetail.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        public static void saveAllDetail(PettycashPayment pettycashPayment, Vector listPettycashPaymentDetail){
            if(listPettycashPaymentDetail!=null && listPettycashPaymentDetail.size()>0){
                for(int i=0; i<listPettycashPaymentDetail.size(); i++){
                    PettycashPaymentDetail crd = (PettycashPaymentDetail)listPettycashPaymentDetail.get(i);
                    crd.setPettycashPaymentId(pettycashPayment.getOID());
                    try{
                        DbPettycashPaymentDetail.insertExc(crd);
                    }
                    catch(Exception e){
                        
                    }
                }                
            }
            
            DbPettycashPayment.getUpdateTotalAmount(pettycashPayment.getOID());
            
        }
        
        public static Vector getActivityPredefined(long paymentOID){
            Vector result = new Vector();
            ActivityPeriod ap = DbActivityPeriod.getOpenPeriod();
            CONResultSet crs = null;
            try{
                String sql = "SELECT c.*, pd.* FROM coa_activity_budget c inner join pettycash_payment_detail pd on pd.coa_id=c.coa_id "+
                    " where pd.pettycash_payment_id="+paymentOID+" and c.activity_period_id = "+ap.getOID();
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    CoaActivityBudget cab = new CoaActivityBudget();
                    PettycashPaymentDetail ppd = new PettycashPaymentDetail();
                    DbCoaActivityBudget.resultToObject(rs, cab);
                    DbPettycashPaymentDetail.resultToObject(rs, ppd);
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
