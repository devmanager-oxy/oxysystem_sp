/***********************************\
|  Create by Dek-Ndut               |
|  Karya kami mohon jangan dibajak  |
|                                   |
|  11/14/2008 9:58:07 AM
\***********************************/

package com.project.ccs.postransaction.sales;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.lang.I_Language;
import com.project.ccs.postransaction.sales.*;


public class DbSalesDetail extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {

	public static final  String DB_SALES_DETAIL = "pos_sales_detail";

	public static final  int COL_SALES_DETAIL_ID = 0;
	public static final  int COL_SALES_ID = 1;
	public static final  int COL_PRODUCT_MASTER_ID = 2;
	public static final  int COL_SQUENCE = 3;
	public static final  int COL_COGS = 4;
	public static final  int COL_SELLING_PRICE = 5;
	public static final  int COL_STATUS = 6;
	public static final  int COL_CURRENCY_ID = 7;
	public static final  int COL_COMPANY_ID = 8;
	public static final  int COL_QTY = 9;
	public static final  int COL_TOTAL = 10;
        
        public static final  int COL_DISCOUNT_PERCENT = 11;
        public static final  int COL_DISCOUNT_AMOUNT = 12;
        public static final  int COL_PROPOSAL_ID = 13;

	public static final  String[] colNames = {
		"sales_detail_id",
		"sales_id",
		"product_master_id",
		"squence",
		"cogs",
		"selling_price",
		"status",
		"currency_id",
		"company_id",
		"qty",
		"total",
                
                "discount_percent",
                "discount_amount",
                "proposal_id"
	};

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_INT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_INT,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_FLOAT,
		TYPE_FLOAT,
                
                TYPE_FLOAT,
                TYPE_FLOAT,
                TYPE_LONG
	};

	public DbSalesDetail(){
	}

	public DbSalesDetail(int i) throws CONException {
		super(new DbSalesDetail());
	}

	public DbSalesDetail(String sOid) throws CONException {
		super(new DbSalesDetail(0));
		if(!locate(sOid))
			throw new CONException(this,CONException.RECORD_NOT_FOUND);
		else
			return;
	}

	public DbSalesDetail(long lOid) throws CONException {
		super(new DbSalesDetail(0));
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
		return DB_SALES_DETAIL;
	}

	public String[] getFieldNames(){
		return colNames;
	}

	public int[] getFieldTypes(){
		return fieldTypes;
	}

	public String getPersistentName(){
		return new DbSalesDetail().getClass().getName();
	}

	public long fetchExc(Entity ent) throws Exception {
		SalesDetail salesDetail = fetchExc(ent.getOID());
		ent = (Entity)salesDetail;
		return salesDetail.getOID();
	}

	public long insertExc(Entity ent) throws Exception {
		return insertExc((SalesDetail) ent);
	}

	public long updateExc(Entity ent) throws Exception {
		return updateExc((SalesDetail) ent);
	}

	public long deleteExc(Entity ent) throws Exception {
		if(ent==null){
			throw new CONException(this,CONException.RECORD_NOT_FOUND);
		}
			return deleteExc(ent.getOID());
	}

	public static SalesDetail fetchExc(long oid) throws CONException{
		try{
			SalesDetail salesDetail = new SalesDetail();
			DbSalesDetail dbSalesDetail = new DbSalesDetail(oid);
			salesDetail.setOID(oid);

			salesDetail.setSalesId(dbSalesDetail.getlong(COL_SALES_ID));
			salesDetail.setProductMasterId(dbSalesDetail.getlong(COL_PRODUCT_MASTER_ID)); 
			salesDetail.setSquence(dbSalesDetail.getInt(COL_SQUENCE));
			salesDetail.setCogs(dbSalesDetail.getdouble(COL_COGS)); 
			salesDetail.setSellingPrice(dbSalesDetail.getdouble(COL_SELLING_PRICE)); 
			salesDetail.setStatus(dbSalesDetail.getInt(COL_STATUS));
			salesDetail.setCurrencyId(dbSalesDetail.getlong(COL_CURRENCY_ID));
			salesDetail.setCompanyId(dbSalesDetail.getlong(COL_COMPANY_ID));
			salesDetail.setQty(dbSalesDetail.getdouble(COL_QTY));
			salesDetail.setTotal(dbSalesDetail.getdouble(COL_TOTAL));

                        salesDetail.setDiscountPercent(dbSalesDetail.getdouble(COL_DISCOUNT_PERCENT));
                        salesDetail.setDiscountAmount(dbSalesDetail.getdouble(COL_DISCOUNT_AMOUNT));
                        salesDetail.setProposalId(dbSalesDetail.getlong(COL_PROPOSAL_ID));
                        
			return salesDetail;
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSalesDetail(0),CONException.UNKNOWN);
		}
	}

	public static long insertExc(SalesDetail salesDetail) throws CONException{
		try{
			DbSalesDetail dbSalesDetail = new DbSalesDetail(0);

			dbSalesDetail.setLong(COL_SALES_ID, salesDetail.getSalesId());
			dbSalesDetail.setLong(COL_PRODUCT_MASTER_ID, salesDetail.getProductMasterId());
			dbSalesDetail.setInt(COL_SQUENCE, salesDetail.getSquence());
			dbSalesDetail.setDouble(COL_COGS, salesDetail.getCogs());
			dbSalesDetail.setDouble(COL_SELLING_PRICE, salesDetail.getSellingPrice());
			dbSalesDetail.setInt(COL_STATUS, salesDetail.getStatus());
			dbSalesDetail.setLong(COL_CURRENCY_ID, salesDetail.getCurrencyId());
			dbSalesDetail.setLong(COL_COMPANY_ID, salesDetail.getCompanyId());
			dbSalesDetail.setDouble(COL_QTY, salesDetail.getQty());
			dbSalesDetail.setDouble(COL_TOTAL, salesDetail.getTotal());

                        dbSalesDetail.setDouble(COL_DISCOUNT_PERCENT, salesDetail.getDiscountPercent());
                        dbSalesDetail.setDouble(COL_DISCOUNT_AMOUNT, salesDetail.getDiscountAmount());
                        dbSalesDetail.setLong(COL_PROPOSAL_ID, salesDetail.getProposalId());
                        
			dbSalesDetail.insert();
			salesDetail.setOID(dbSalesDetail.getlong(COL_SALES_DETAIL_ID));
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSalesDetail(0),CONException.UNKNOWN);
		}
		return salesDetail.getOID();
	}

	public static long updateExc(SalesDetail salesDetail) throws CONException{
		try{
			if(salesDetail.getOID() != 0){
				DbSalesDetail dbSalesDetail = new DbSalesDetail(salesDetail.getOID());

				dbSalesDetail.setLong(COL_SALES_ID, salesDetail.getSalesId());
				dbSalesDetail.setLong(COL_PRODUCT_MASTER_ID, salesDetail.getProductMasterId());
				dbSalesDetail.setInt(COL_SQUENCE, salesDetail.getSquence());
				dbSalesDetail.setDouble(COL_COGS, salesDetail.getCogs());
				dbSalesDetail.setDouble(COL_SELLING_PRICE, salesDetail.getSellingPrice());
				dbSalesDetail.setInt(COL_STATUS, salesDetail.getStatus());
				dbSalesDetail.setLong(COL_CURRENCY_ID, salesDetail.getCurrencyId());
				dbSalesDetail.setLong(COL_COMPANY_ID, salesDetail.getCompanyId());
				dbSalesDetail.setDouble(COL_QTY, salesDetail.getQty());
				dbSalesDetail.setDouble(COL_TOTAL, salesDetail.getTotal());

                                dbSalesDetail.setDouble(COL_DISCOUNT_PERCENT, salesDetail.getDiscountPercent());
                                dbSalesDetail.setDouble(COL_DISCOUNT_AMOUNT, salesDetail.getDiscountAmount());
                                dbSalesDetail.setLong(COL_PROPOSAL_ID, salesDetail.getProposalId());
                                
				dbSalesDetail.update();
				return salesDetail.getOID();

			}
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSalesDetail(0),CONException.UNKNOWN);
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{
		try{
			DbSalesDetail dbSalesDetail = new DbSalesDetail(oid);
			dbSalesDetail.delete();
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSalesDetail(0),CONException.UNKNOWN);
		}
		return oid;
	}

	public static Vector listAll()
	{
		return list(0, 500, "","");
	}

	public static Vector list(int limitStart,int recordToGet, String whereClause, String order){
		Vector lists = new Vector();
		CONResultSet dbrs = null;
		try {
			String sql = "SELECT * FROM " + DB_SALES_DETAIL;
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
				SalesDetail salesDetail = new SalesDetail();
				resultToObject(rs, salesDetail);
				lists.add(salesDetail);
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

	private static void resultToObject(ResultSet rs, SalesDetail salesDetail){
		try{

			salesDetail.setOID(rs.getLong(DbSalesDetail.colNames[DbSalesDetail.COL_SALES_DETAIL_ID]));
			salesDetail.setSalesId(rs.getLong(DbSalesDetail.colNames[DbSalesDetail.COL_SALES_ID]));
			salesDetail.setProductMasterId(rs.getLong(DbSalesDetail.colNames[DbSalesDetail.COL_PRODUCT_MASTER_ID]));
			salesDetail.setSquence(rs.getInt(DbSalesDetail.colNames[DbSalesDetail.COL_SQUENCE]));
			salesDetail.setCogs(rs.getDouble(DbSalesDetail.colNames[DbSalesDetail.COL_COGS]));
			salesDetail.setSellingPrice(rs.getDouble(DbSalesDetail.colNames[DbSalesDetail.COL_SELLING_PRICE]));
			salesDetail.setStatus(rs.getInt(DbSalesDetail.colNames[DbSalesDetail.COL_STATUS]));
			salesDetail.setCurrencyId(rs.getLong(DbSalesDetail.colNames[DbSalesDetail.COL_CURRENCY_ID]));
			salesDetail.setCompanyId(rs.getLong(DbSalesDetail.colNames[DbSalesDetail.COL_COMPANY_ID]));
			salesDetail.setQty(rs.getDouble(DbSalesDetail.colNames[DbSalesDetail.COL_QTY]));
			salesDetail.setTotal(rs.getDouble(DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]));

                        salesDetail.setDiscountPercent(rs.getDouble(DbSalesDetail.colNames[DbSalesDetail.COL_DISCOUNT_PERCENT]));
                        salesDetail.setDiscountAmount(rs.getDouble(DbSalesDetail.colNames[DbSalesDetail.COL_DISCOUNT_AMOUNT]));
                        salesDetail.setProposalId(rs.getLong(DbSalesDetail.colNames[DbSalesDetail.COL_PROPOSAL_ID]));
                        
		}catch(Exception e){ }
	}

	public static boolean checkOID(long salesProductDetailId)
	{
		CONResultSet dbrs = null;
		boolean result = false;
		try
		{
			String sql = "SELECT * FROM " + DB_SALES_DETAIL + " WHERE " + 
				DbSalesDetail.colNames[DbSalesDetail.COL_SALES_DETAIL_ID] + " = " + salesProductDetailId;
			dbrs = CONHandler.execQueryResult(sql);
			ResultSet rs = dbrs.getResultSet();
			while(rs.next()) { result = true; }
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println("err : "+e.toString());
		}
		finally
		{
			CONResultSet.close(dbrs);
			return result;
		}
	}

	public static int getCount(String whereClause){
		CONResultSet dbrs = null;
		try {
			String sql = "SELECT COUNT("+ DbSalesDetail.colNames[DbSalesDetail.COL_SALES_DETAIL_ID] + ") FROM " + DB_SALES_DETAIL;
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
				SalesDetail salesDetail = (SalesDetail)list.get(ls);
				if(oid == salesDetail.getOID())
					found=true;
				}
			}
		}
		if((start >= size) && (size > 0))
			start = start - recordToGet;

		return start;
	}

        public static int getMaxSquence(long salesId){
                int result = 0;                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max(squence) from "+DB_SALES_DETAIL+" where "+
                        " sales_id='"+salesId+"' ";
                    
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
                    System.out.println(e);
                }
                finally{
                    CONResultSet.close(dbrs);
                }
                
                return result;
        }          
        
        /*
        public static double getSubTotalSalesAmount(long proposalId){ 
            double result = 0;
            
            String sql = "select sum("+colNames[COL_TOTAL]+") from "+DB_SALES_DETAIL+
                " where "+colNames[COL_PROPOSAL_ID]+"="+proposalId;
            
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
         */
        
        public static double getSubTotalSalesAmount(long salesId){ 
            double result = 0;
            
            String sql = "select sum("+colNames[COL_TOTAL]+") from "+DB_SALES_DETAIL+
                " where "+colNames[COL_SALES_ID]+"="+salesId;
            
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
        
        public static void deleteDocSales(long salesId){
            try{
                String sql = "delete from "+DB_SALES_DETAIL+" where "+colNames[COL_SALES_ID]+"="+salesId;
                CONHandler.execUpdate(sql);
            }
            catch(Exception e){
                
            }
        }
        
}
