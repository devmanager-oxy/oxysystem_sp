/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.ccs.postransaction.receiving;

import com.project.main.db.CONException;
import com.project.main.db.CONHandler;
import com.project.main.db.CONResultSet;
import com.project.main.db.I_CONInterface;
import com.project.main.db.I_CONType;
import com.project.main.entity.Entity;
import com.project.main.entity.I_PersintentExc;
import com.project.util.lang.I_Language;
import java.sql.ResultSet;
import java.util.Vector;
import com.project.util.*;
import com.project.general.*;
import java.util.Date;
import com.project.ccs.posmaster.*;
import com.project.*;
import com.project.fms.transaction.*;
import com.project.fms.master.Coa;
import com.project.fms.master.DbCoa;
import com.project.fms.master.AccLink;
import com.project.fms.master.DbAccLink;
import com.project.fms.transaction.*;


public class DbReceive extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {

    public static final String DB_RECEIVE = "pos_receive";
    
    public static final int COL_RECEIVE_ID = 0;
    public static final int COL_DATE = 1;
    public static final int COL_APPROVAL_1 = 2;
    public static final int COL_APPROVAL_2 = 3;
    public static final int COL_APPROVAL_3 = 4;
    public static final int COL_STATUS = 5;
    public static final int COL_USER_ID = 6;
    public static final int COL_NOTE = 7;
    public static final int COL_NUMBER = 8;
    public static final int COL_COUNTER = 9;
    public static final int COL_INCLUDE_TAX = 10;
    public static final int COL_TOTAL_TAX = 11;
    public static final int COL_TOTAL_AMOUNT = 12;
    public static final int COL_TAX_PERCENT = 13;
    public static final int COL_DISCOUNT_TOTAL = 14;
    public static final int COL_DISCOUNT_PERCENT = 15;
    public static final int COL_PAYMENT_TYPE = 16;
    public static final int COL_LOCATION_ID = 17;
    public static final int COL_VENDOR_ID = 18;
    public static final int COL_CURRENCY_ID = 19;
    public static final int COL_PREFIX_NUMBER = 20;
    public static final int COL_CLOSED_REASON = 21;
    
    public static final int COL_APPROVAL_1_DATE = 22;
    public static final int COL_APPROVAL_2_DATE = 23;
    public static final int COL_APPROVAL_3_DATE = 24;
    
    public static final int COL_PURCHASE_ID     = 25;
    public static final int COL_DUE_DATE        = 26;
    public static final int COL_PAYMENT_AMOUNT  = 27;
    public static final int COL_DO_NUMBER       = 28;
    public static final int COL_INVOICE_NUMBER  = 29;  
    
    public static final int COL_PAYMENT_STATUS  = 30;  
    
    public static final int COL_UNIT_USAHA_ID  = 31;  
    
    public static final String[] colNames = {
        "receive_id",
        "date",
        "approval_1",
        "approval_2",
        "approval_3",
        "status",
        "user_id",
        "note",
        "number",
        "counter",
        "include_tax",
        "total_tax",
        "total_amount",
        "tax_percent",
        "discount_total",
        "discount_percent",
        "payment_type",
        "location_id",
        "vendor_id",        
        "currency_id",
        "prefix_number",
        "closed_reason",
        "approval_1_date",
        "approval_2_date",
        "approval_3_date",
        
        "purchase_id",
        "due_date",
        "payment_amount",
        "do_number",
        "invoice_number",
        "payment_status",
        
        "unit_usaha_id"
        
    };
    public static final int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_DATE,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_INT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_LONG,
        TYPE_LONG,        
        TYPE_LONG,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_DATE,
        
        TYPE_LONG,
        TYPE_DATE,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        
        TYPE_LONG
    };
    
    public static int INCLUDE_TAX_NO = 0;
    public static int INCLUDE_TAX_YES = 1;
    public static String[] strIncludeTax = {"No", "Yes"};

    public DbReceive() {
    }

    public DbReceive(int i) throws CONException {
        super(new DbReceive());
    }

    public DbReceive(String sOid) throws CONException {
        super(new DbReceive(0));
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public DbReceive(long lOid) throws CONException {
        super(new DbReceive(0));
        String sOid = "0";
        try {
            sOid = String.valueOf(lOid);
        } catch (Exception e) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        }
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public int getFieldSize() {
        return colNames.length;
    }

    public String getTableName() {
        return DB_RECEIVE;
    }

    public String[] getFieldNames() {
        return colNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new DbReceive().getClass().getName();
    }

    public long fetchExc(Entity ent) throws Exception {
        Receive receive = fetchExc(ent.getOID());
        ent = (Entity) receive;
        return receive.getOID();
    }

    public long insertExc(Entity ent) throws Exception {
        return insertExc((Receive) ent);
    }

    public long updateExc(Entity ent) throws Exception {
        return updateExc((Receive) ent);
    }

    public long deleteExc(Entity ent) throws Exception {
        if (ent == null) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        }
        return deleteExc(ent.getOID());
    }

    public static Receive fetchExc(long oid) throws CONException {
        try {
            Receive receive = new Receive();
            DbReceive dbReceiveRequest = new DbReceive(oid);
            receive.setOID(oid);
            receive.setApproval1(dbReceiveRequest.getlong(COL_APPROVAL_1));
            receive.setApproval2(dbReceiveRequest.getlong(COL_APPROVAL_2));
            receive.setApproval3(dbReceiveRequest.getlong(COL_APPROVAL_3));
            receive.setCounter(dbReceiveRequest.getInt(COL_COUNTER));
            receive.setIncluceTax(dbReceiveRequest.getInt(COL_INCLUDE_TAX));
            receive.setNote(dbReceiveRequest.getString(COL_NOTE));
            receive.setNumber(dbReceiveRequest.getString(COL_NUMBER));
            receive.setDate(dbReceiveRequest.getDate(COL_DATE));
            receive.setStatus(dbReceiveRequest.getString(COL_STATUS));
            receive.setUserId(dbReceiveRequest.getlong(COL_USER_ID));
            receive.setTotalTax(dbReceiveRequest.getdouble(COL_TOTAL_TAX));
            receive.setTotalAmount(dbReceiveRequest.getdouble(COL_TOTAL_AMOUNT));
            receive.setTaxPercent(dbReceiveRequest.getdouble(COL_TAX_PERCENT));
            receive.setDiscountTotal(dbReceiveRequest.getdouble(COL_DISCOUNT_TOTAL));
            receive.setDiscountPercent(dbReceiveRequest.getdouble(COL_DISCOUNT_PERCENT));
            receive.setPaymentType(dbReceiveRequest.getString(COL_PAYMENT_TYPE));
            receive.setLocationId(dbReceiveRequest.getlong(COL_LOCATION_ID));
            receive.setVendorId(dbReceiveRequest.getlong(COL_VENDOR_ID));            
            receive.setCurrencyId(dbReceiveRequest.getlong(COL_CURRENCY_ID));
            receive.setPrefixNumber(dbReceiveRequest.getString(COL_PREFIX_NUMBER));
            receive.setClosedReason(dbReceiveRequest.getString(COL_CLOSED_REASON));
            
            receive.setApproval1Date(dbReceiveRequest.getDate(COL_APPROVAL_1_DATE));
            receive.setApproval2Date(dbReceiveRequest.getDate(COL_APPROVAL_2_DATE));
            receive.setApproval3Date(dbReceiveRequest.getDate(COL_APPROVAL_3_DATE));
            
            receive.setPurchaseId(dbReceiveRequest.getlong(COL_PURCHASE_ID));
            receive.setDueDate(dbReceiveRequest.getDate(COL_DUE_DATE));
            receive.setPaymentAmount(dbReceiveRequest.getdouble(COL_PAYMENT_AMOUNT));
            receive.setDoNumber(dbReceiveRequest.getString(COL_DO_NUMBER));
            receive.setInvoiceNumber(dbReceiveRequest.getString(COL_INVOICE_NUMBER));
            receive.setPaymentStatus(dbReceiveRequest.getInt(COL_PAYMENT_STATUS));
            
            receive.setUnitUsahaId(dbReceiveRequest.getlong(COL_UNIT_USAHA_ID));

            return receive;
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceive(0), CONException.UNKNOWN);
        }
    }

    public static long insertExc(Receive receive) throws CONException {
        try {
            DbReceive dbReceiveRequest = new DbReceive(0);

            dbReceiveRequest.setLong(COL_APPROVAL_1, receive.getApproval1());
            dbReceiveRequest.setLong(COL_APPROVAL_2, receive.getApproval2());
            dbReceiveRequest.setLong(COL_APPROVAL_3, receive.getApproval3());
            dbReceiveRequest.setInt(COL_COUNTER, receive.getCounter());
            dbReceiveRequest.setInt(COL_INCLUDE_TAX, receive.getIncluceTax());
            dbReceiveRequest.setString(COL_NOTE, receive.getNote());
            dbReceiveRequest.setString(COL_NUMBER, receive.getNumber());
            dbReceiveRequest.setDate(COL_DATE, receive.getDate());
            dbReceiveRequest.setString(COL_STATUS, receive.getStatus());
            dbReceiveRequest.setLong(COL_USER_ID, receive.getUserId());
            dbReceiveRequest.setDouble(COL_TOTAL_TAX, receive.getTotalTax());
            dbReceiveRequest.setDouble(COL_TOTAL_AMOUNT, receive.getTotalAmount());
            dbReceiveRequest.setDouble(COL_TAX_PERCENT, receive.getTaxPercent());
            dbReceiveRequest.setDouble(COL_DISCOUNT_TOTAL, receive.getDiscountTotal());
            dbReceiveRequest.setDouble(COL_DISCOUNT_PERCENT, receive.getDiscountPercent());
            dbReceiveRequest.setString(COL_PAYMENT_TYPE, receive.getPaymentType());
            dbReceiveRequest.setLong(COL_LOCATION_ID, receive.getLocationId());
            dbReceiveRequest.setLong(COL_VENDOR_ID, receive.getVendorId());
            dbReceiveRequest.setLong(COL_CURRENCY_ID, receive.getCurrencyId());
            dbReceiveRequest.setString(COL_PREFIX_NUMBER, receive.getPrefixNumber());
            dbReceiveRequest.setString(COL_CLOSED_REASON, receive.getClosedReason());
            
            dbReceiveRequest.setDate(COL_APPROVAL_1_DATE, receive.getApproval1Date());
            dbReceiveRequest.setDate(COL_APPROVAL_2_DATE, receive.getApproval2Date());
            dbReceiveRequest.setDate(COL_APPROVAL_3_DATE, receive.getApproval3Date());
            
            dbReceiveRequest.setLong(COL_PURCHASE_ID, receive.getPurchaseId());
            dbReceiveRequest.setDate(COL_DUE_DATE, receive.getDueDate());
            dbReceiveRequest.setDouble(COL_PAYMENT_AMOUNT, receive.getPaymentAmount());
            dbReceiveRequest.setString(COL_DO_NUMBER, receive.getDoNumber());
            dbReceiveRequest.setString(COL_INVOICE_NUMBER, receive.getInvoiceNumber());
            dbReceiveRequest.setInt(COL_PAYMENT_STATUS, receive.getPaymentStatus());
            
            dbReceiveRequest.setLong(COL_UNIT_USAHA_ID, receive.getUnitUsahaId());

            dbReceiveRequest.insert();
            receive.setOID(dbReceiveRequest.getlong(COL_RECEIVE_ID));
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceive(0), CONException.UNKNOWN);
        }
        return receive.getOID();
    }

    public static long updateExc(Receive receive) throws CONException {
        try {
            if (receive.getOID() != 0) {
                DbReceive dbReceiveRequest = new DbReceive(receive.getOID());

                dbReceiveRequest.setLong(COL_APPROVAL_1, receive.getApproval1());
                dbReceiveRequest.setLong(COL_APPROVAL_2, receive.getApproval2());
                dbReceiveRequest.setLong(COL_APPROVAL_3, receive.getApproval3());
                dbReceiveRequest.setInt(COL_COUNTER, receive.getCounter());
                dbReceiveRequest.setInt(COL_INCLUDE_TAX, receive.getIncluceTax());
                dbReceiveRequest.setString(COL_NOTE, receive.getNote());
                dbReceiveRequest.setString(COL_NUMBER, receive.getNumber());
                dbReceiveRequest.setDate(COL_DATE, receive.getDate());
                dbReceiveRequest.setString(COL_STATUS, receive.getStatus());
                dbReceiveRequest.setLong(COL_USER_ID, receive.getUserId());
                dbReceiveRequest.setDouble(COL_TOTAL_TAX, receive.getTotalTax());
                dbReceiveRequest.setDouble(COL_TOTAL_AMOUNT, receive.getTotalAmount());
                dbReceiveRequest.setDouble(COL_TAX_PERCENT, receive.getTaxPercent());
                dbReceiveRequest.setDouble(COL_DISCOUNT_TOTAL, receive.getDiscountTotal());
                dbReceiveRequest.setDouble(COL_DISCOUNT_PERCENT, receive.getDiscountPercent());
                dbReceiveRequest.setString(COL_PAYMENT_TYPE, receive.getPaymentType());
                dbReceiveRequest.setLong(COL_LOCATION_ID, receive.getLocationId());
                dbReceiveRequest.setLong(COL_VENDOR_ID, receive.getVendorId());
                dbReceiveRequest.setLong(COL_CURRENCY_ID, receive.getCurrencyId());
                dbReceiveRequest.setString(COL_PREFIX_NUMBER, receive.getPrefixNumber());
                dbReceiveRequest.setString(COL_CLOSED_REASON, receive.getClosedReason());
                
                dbReceiveRequest.setDate(COL_APPROVAL_1_DATE, receive.getApproval1Date());
                dbReceiveRequest.setDate(COL_APPROVAL_2_DATE, receive.getApproval2Date());
                dbReceiveRequest.setDate(COL_APPROVAL_3_DATE, receive.getApproval3Date());
                
                dbReceiveRequest.setLong(COL_PURCHASE_ID, receive.getPurchaseId());
                dbReceiveRequest.setDate(COL_DUE_DATE, receive.getDueDate());
                dbReceiveRequest.setDouble(COL_PAYMENT_AMOUNT, receive.getPaymentAmount());
                dbReceiveRequest.setString(COL_DO_NUMBER, receive.getDoNumber());
                dbReceiveRequest.setString(COL_INVOICE_NUMBER, receive.getInvoiceNumber()); 
                dbReceiveRequest.setInt(COL_PAYMENT_STATUS, receive.getPaymentStatus());
                
                dbReceiveRequest.setLong(COL_UNIT_USAHA_ID, receive.getUnitUsahaId());

                dbReceiveRequest.update();
                return receive.getOID();

            }
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceive(0), CONException.UNKNOWN);
        }
        return 0;
    }

    public static long deleteExc(long oid) throws CONException {
        try {
            DbReceive pstReceiveRequest = new DbReceive(oid);
            pstReceiveRequest.delete();
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceive(0), CONException.UNKNOWN);
        }
        return oid;
    }

    public static Vector listAll() {
        return list(0, 500, "", "");
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        CONResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + DB_RECEIVE;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            if (limitStart == 0 && recordToGet == 0) {
                sql = sql + "";
            } else {
                sql = sql + " LIMIT " + limitStart + "," + recordToGet;
            }
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Receive receive = new Receive();
                resultToObject(rs, receive);
                lists.add(receive);
            }
            rs.close();
            return lists;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            CONResultSet.close(dbrs);
        }
        return new Vector();
    }
    
    
    public static Vector list(int limitStart, int recordToGet, String status, long vendorId, 
        int ignore, Date startDate, Date endDate, long unitUsahaId) {
            
        Vector lists = new Vector();
        CONResultSet dbrs = null;
        try {
            String sql = "select distinct pr.* from "+DB_RECEIVE+" pr "+
                        " where pr."+colNames[COL_STATUS]+" = '"+status+"'";
            
                        if(vendorId!=0){
                            sql = sql + " and "+colNames[COL_VENDOR_ID]+"="+vendorId;
                        }
                        if(ignore==0){
                            sql = sql + " and (pr."+colNames[COL_DATE]+" between "+
                                "'"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                        }
                        if(unitUsahaId!=0){
                            sql = sql + " and pr."+colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
                        }
            
                        sql = sql + " order by pr."+colNames[COL_DATE];
                        
                        if (limitStart == 0 && recordToGet == 0) {
                            sql = sql + "";
                        } else {
                            sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                        }
            
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Receive receive = new Receive();
                resultToObject(rs, receive);
                lists.add(receive);
            }
            rs.close();
            return lists;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            CONResultSet.close(dbrs);
        }
        return new Vector();
    }
    
    public static int getCount(String status, long vendorId, 
        int ignore, Date startDate, Date endDate, long unitUsahaId) {
            
        int result = 0;
        CONResultSet dbrs = null;
        try {
            String sql = "select distinct count(pr."+colNames[COL_RECEIVE_ID]+") from "+DB_RECEIVE+" pr "+
                        " where pr."+colNames[COL_STATUS]+" = '"+status+"'";
            
                        if(vendorId!=0){
                            sql = sql + " and "+colNames[COL_VENDOR_ID]+"="+vendorId;
                        }
                        if(ignore==0){
                            sql = sql + " and (pr."+colNames[COL_DATE]+" between "+
                                "'"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                        }
                        if(unitUsahaId!=0){
                            sql = sql + " and pr."+colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
                        }
            
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getInt(1);
            }
            rs.close();
            return result;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            CONResultSet.close(dbrs);
        }
        return 0;
    }
    
    public static Vector listVendorForAging(long vendorId, long unitUsahaId) {
            
        Vector lists = new Vector();
        CONResultSet dbrs = null;
        try {
            String sql = "select distinct v.* from " +DbVendor.DB_VENDOR+" v "+
                        " inner join "+DB_RECEIVE+" pr "+
                        " on v."+DbVendor.colNames[DbVendor.COL_VENDOR_ID]+" = pr."+colNames[COL_VENDOR_ID]+
                        " where pr."+colNames[COL_STATUS]+" = '"+I_Project.DOC_STATUS_CHECKED+"'";
            
                        if(vendorId!=0){
                            sql = sql + " and pr."+colNames[COL_VENDOR_ID]+"="+vendorId;
                        }
                        
                        if(unitUsahaId!=0){
                            sql = sql + " and pr."+colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
                        }
            
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                Vendor vnd = new Vendor();
                DbVendor.resultToObject(rs, vnd);
                lists.add(vnd);
            }
            rs.close();
            return lists;

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            CONResultSet.close(dbrs);
        }
        return new Vector();
    }
    

    public static void resultToObject(ResultSet rs, Receive receive) {
        try {
            receive.setOID(rs.getLong(DbReceive.colNames[DbReceive.COL_RECEIVE_ID]));
            receive.setApproval1(rs.getLong(DbReceive.colNames[DbReceive.COL_APPROVAL_1]));
            receive.setApproval2(rs.getLong(DbReceive.colNames[DbReceive.COL_APPROVAL_2]));
            receive.setApproval3(rs.getLong(DbReceive.colNames[DbReceive.COL_APPROVAL_3]));
            receive.setCounter(rs.getInt(DbReceive.colNames[DbReceive.COL_COUNTER]));
            receive.setIncluceTax(rs.getInt(DbReceive.colNames[DbReceive.COL_INCLUDE_TAX]));
            receive.setNote(rs.getString(DbReceive.colNames[DbReceive.COL_NOTE]));
            receive.setNumber(rs.getString(DbReceive.colNames[DbReceive.COL_NUMBER]));
            receive.setDate(rs.getDate(DbReceive.colNames[DbReceive.COL_DATE]));
            receive.setStatus(rs.getString(DbReceive.colNames[DbReceive.COL_STATUS]));
            receive.setUserId(rs.getLong(DbReceive.colNames[DbReceive.COL_USER_ID]));
            receive.setTotalTax(rs.getDouble(DbReceive.colNames[DbReceive.COL_TOTAL_TAX]));
            receive.setTotalAmount(rs.getDouble(DbReceive.colNames[DbReceive.COL_TOTAL_AMOUNT]));
            receive.setTaxPercent(rs.getDouble(DbReceive.colNames[DbReceive.COL_TAX_PERCENT]));
            receive.setDiscountTotal(rs.getDouble(DbReceive.colNames[DbReceive.COL_DISCOUNT_TOTAL]));
            receive.setDiscountPercent(rs.getDouble(DbReceive.colNames[DbReceive.COL_DISCOUNT_PERCENT]));
            receive.setPaymentType(rs.getString(DbReceive.colNames[DbReceive.COL_PAYMENT_TYPE]));
            receive.setLocationId(rs.getLong(DbReceive.colNames[DbReceive.COL_LOCATION_ID]));
            receive.setVendorId(rs.getLong(DbReceive.colNames[DbReceive.COL_VENDOR_ID]));
            receive.setCurrencyId(rs.getLong(DbReceive.colNames[DbReceive.COL_CURRENCY_ID]));
            receive.setPrefixNumber(rs.getString(DbReceive.colNames[DbReceive.COL_PREFIX_NUMBER]));
            receive.setClosedReason(rs.getString(DbReceive.colNames[DbReceive.COL_CLOSED_REASON]));
            
            receive.setApproval1Date(rs.getDate(DbReceive.colNames[DbReceive.COL_APPROVAL_1_DATE]));
            receive.setApproval2Date(rs.getDate(DbReceive.colNames[DbReceive.COL_APPROVAL_2_DATE]));
            receive.setApproval3Date(rs.getDate(DbReceive.colNames[DbReceive.COL_APPROVAL_3_DATE]));
            
            receive.setPurchaseId(rs.getLong(DbReceive.colNames[DbReceive.COL_PURCHASE_ID]));
            receive.setDueDate(rs.getDate(DbReceive.colNames[DbReceive.COL_DUE_DATE]));
            receive.setPaymentAmount(rs.getDouble(DbReceive.colNames[DbReceive.COL_PAYMENT_AMOUNT]));
            receive.setDoNumber(rs.getString(DbReceive.colNames[DbReceive.COL_DO_NUMBER]));
            receive.setInvoiceNumber(rs.getString(DbReceive.colNames[DbReceive.COL_INVOICE_NUMBER]));
            receive.setPaymentStatus(rs.getInt(DbReceive.colNames[DbReceive.COL_PAYMENT_STATUS]));
            
            receive.setUnitUsahaId(rs.getLong(DbReceive.colNames[DbReceive.COL_UNIT_USAHA_ID]));
            
        } catch (Exception e) {
        }
    }

    public static boolean checkOID(long receiveId) {
        CONResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + DB_RECEIVE + " WHERE " +
                    DbReceive.colNames[DbReceive.COL_RECEIVE_ID] + " = " + receiveId;

            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                result = true;
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("err : " + e.toString());
        } finally {
            CONResultSet.close(dbrs);
            return result;
        }
    }

    public static int getCount(String whereClause) {
        CONResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT(" + DbReceive.colNames[DbReceive.COL_RECEIVE_ID] + ") FROM " + DB_RECEIVE;
            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }

            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }

            rs.close();
            return count;
        } catch (Exception e) {
            return 0;
        } finally {
            CONResultSet.close(dbrs);
        }
    }


    /* This method used to find current data */
    public static int findLimitStart(long oid, int recordToGet, String whereClause) {
        String order = "";
        int size = getCount(whereClause);
        int start = 0;
        boolean found = false;
        for (int i = 0; (i < size) && !found; i = i + recordToGet) {
            Vector list = list(i, recordToGet, whereClause, order);
            start = i;
            if (list.size() > 0) {
                for (int ls = 0; ls < list.size(); ls++) {
                    Receive receive = (Receive) list.get(ls);
                    if (oid == receive.getOID()) {
                        found = true;
                    }
                }
            }
        }
        if ((start >= size) && (size > 0)) {
            start = start - recordToGet;
        }

        return start;
    }

    public static int getNextCounter(){
                int result = 0;
                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max("+colNames[COL_COUNTER]+") from "+DB_RECEIVE+" where "+
                        colNames[COL_PREFIX_NUMBER]+"='"+getNumberPrefix()+"' ";
                    
                    System.out.println(sql);
                    
                    dbrs = CONHandler.execQueryResult(sql);
                    ResultSet rs = dbrs.getResultSet();
                    while(rs.next()){
                        result = rs.getInt(1);
                    }
                    
                    result = result + 1;
                    
                }
                catch(Exception e){
                    
                }
                finally{
                    CONResultSet.close(dbrs);
                }
                
                return result;
        }
        
        public static String getNumberPrefix(){
                String code = "";
                Company sysCompany = DbCompany.getCompany();
                code = code + sysCompany.getInvoiceCode(); 
                
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
        
        
        public static void validateReceiveItem(Receive receive){
                Vector outVendorItem = QrVendorItem.getOutVendorItems(receive); 
                if(outVendorItem!=null && outVendorItem.size()>0){
                    for(int i=0; i<outVendorItem.size(); i++){
                        ReceiveItem pi = (ReceiveItem)outVendorItem.get(i);
                        try{
                            DbReceiveItem.deleteExc(pi.getOID());
                        }
                        catch(Exception e){
                            System.out.println(e.toString());
                        }
                    }
                }
        }
        
        public static void fixGrandTotalAmount(long oidReceive){
            
            if(oidReceive!=0){
                Receive p = new Receive();
                try{
                    p = DbReceive.fetchExc(oidReceive);
                    p.setTotalAmount(DbReceiveItem.getTotalReceiveAmount(oidReceive));
                    double disPercent = p.getDiscountPercent();
                    double taxPercent = p.getTaxPercent();
                    if(disPercent>0){
                        p.setDiscountTotal((disPercent/100) * p.getTotalAmount());
                    }
                    else{
                        p.setDiscountPercent(0);
                        p.setDiscountTotal(0);
                    }
                    if(taxPercent>0){
                        p.setTotalTax((p.getTotalAmount()-p.getDiscountTotal()) * taxPercent/100);
                    }
                    else{
                        p.setTaxPercent(0);
                        p.setTotalTax(0);
                    }
                    
                    DbReceive.updateExc(p);
                    
                }
                catch(Exception e){

                }
            }
            
        }
        
        //POSTING ke journal - pengakuan pihutang
        public static void postJournal(Receive cr){
            
                System.out.println("\n---- bean start posting journal purchase ---");
                
                Company comp = DbCompany.getCompany();
                
                ExchangeRate er = DbExchangeRate.getStandardRate();
                
                String memo = "Pembelian : "+cr.getNumber();
                               
                if(cr.getOID()!=0 && cr.getStatus().equals(I_Project.DOC_STATUS_CHECKED)){
                    
                    long oid = DbGl.postJournalMain(0, cr.getApproval1Date(), cr.getCounter(), cr.getNumber(), cr.getPrefixNumber(), 
                        I_Project.JOURNAL_TYPE_PURCHASE_ORDER, 
                        memo, cr.getApproval1(), "", cr.getOID(), "", cr.getDate());
                    
                    //pengakuan pihutang    
                    if(oid!=0){
                        
                        memo = ""; 
                        
                        //journal debet pada inventory, tax
                        //penambahan inventory
                        
                        Vector v = DbReceiveItem.list(0,0, colNames[COL_RECEIVE_ID]+"="+cr.getOID(), "");
                        
                        double subTotal = DbReceiveItem.getTotalReceiveAmount(cr.getOID());
                        
                        if(v!=null && v.size()>0){
                            for(int i=0; i<v.size(); i++){
                                
                                ReceiveItem ri = (ReceiveItem)v.get(i);
                                try{
                                    ItemMaster im = DbItemMaster.fetchExc(ri.getItemMasterId());
                                    ItemGroup ig = DbItemGroup.fetchExc(im.getItemGroupId());
                                    
                                    Vector invCoa = new Vector();
                                    //if it is stock - tambah stock
                                    if(im.getNeedRecipe()==0){
                                        invCoa = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_CODE]+"='"+ig.getAccountInv()+"'", "");
                                    }
                                    //kalau bukan stock - lakukan ke biaya
                                    else{
                                        //jika belum diisi, larikan ke inventory saja
                                        if(ig.getAccountExpenseJasa()!=null && ig.getAccountExpenseJasa().length()>0){
                                            invCoa = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_CODE]+"='"+ig.getAccountExpenseJasa()+"'", "");
                                        }
                                        else{
                                            invCoa = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_CODE]+"='"+ig.getAccountInv()+"'", "");
                                        }
                                    }
                                    
                                    Coa coa = new Coa();
                                    if(invCoa!=null && invCoa.size()>0){
                                        coa = (Coa)invCoa.get(0);
                                    }

                                    double amount = ri.getTotalAmount();
                                    if(cr.getDiscountTotal()>0){
                                        amount = ri.getTotalAmount() - ((ri.getTotalAmount()/subTotal) * cr.getDiscountTotal());
                                    }
                                    
                                    memo = "Purchase : "+ig.getName()+"/"+im.getCode()+"-"+ig.getName();
                                    DbGl.postJournalDetail(er.getValueIdr(), coa.getOID(), 0, amount,             
                                                amount, comp.getBookingCurrencyId(), oid, memo, 0);//non departmenttal item, department id = 0
                                    
                                    //=============================== jurnal suspense accounnt/AP ==================================
                                                
                                    //journal credit hutang
                                    memo = "Hutang/AP";
                                    double apamount = 0;
                                    double discount = 0;
                                    
                                    apamount = ri.getTotalAmount();
                                    
                                    if(cr.getDiscountPercent()!=0){
                                        discount = apamount * (cr.getDiscountPercent()/100);                                        
                                        apamount = apamount - discount;
                                    }
                                    
                                    if(cr.getTotalTax()>0){
                                        apamount = apamount + ((apamount * comp.getGovernmentVat()/100));
                                    }
                                    else{
                                        apamount = apamount;//ri.getTotalAmount();
                                    }
                                    
                                    DbGl.postJournalDetail(er.getValueIdr(), ri.getApCoaId(), apamount, 0,             
                                            apamount, comp.getBookingCurrencyId(), oid, memo, 0); 

                                    
                                }
                                catch(Exception e){
                                    
                                }
                                
                            }
                        }

                        //jika ada pajak, masukkan ke pajak masukan
                        
                        AccLink al = new AccLink();
                        
                        if(cr.getTotalTax()>0){
                            Vector temp = DbAccLink.list(0,0, DbAccLink.colNames[DbAccLink.COL_TYPE]+"='"+I_Project.ACC_LINK_GROUP_PURCHASING_TAX+"'", "");
                            al = new AccLink();
                            if(temp!=null && temp.size()>0){
                                al = (AccLink)temp.get(0);
                            }

                            //journal debet tax
                            memo = "Pajak pembelian";
                            DbGl.postJournalDetail(er.getValueIdr(), al.getCoaId(), 0, cr.getTotalTax(),             
                                    cr.getTotalTax(), comp.getBookingCurrencyId(), oid, memo, 0); 

                        }
                        
                        
                        //debet discount
                        /*if(cr.getDiscountTotal()>0){
                            Vector temp = DbAccLink.list(0,0, DbAccLink.colNames[DbAccLink.COL_TYPE]+"='"+I_Project.ACC_LINK_GROUP_PURCHASING_DISCOUNT+"'", "");
                            al = new AccLink();
                            if(temp!=null && temp.size()>0){
                                al = (AccLink)temp.get(0);
                            }

                            //journal debet tax
                            memo = "Discount pembelian";
                            DbGl.postJournalDetail(er.getValueIdr(), al.getCoaId(), 0, cr.getDiscountTotal(),             
                                    cr.getDiscountTotal(), comp.getBookingCurrencyId(), oid, memo, 0); 

                        }
                         */
                        
                        //=============================== jurnal suspense accounnt/AP ==================================
                        /*                        
                        Vector temp = DbAccLink.list(0,0, DbAccLink.colNames[DbAccLink.COL_TYPE]+"='"+I_Project.ACC_LINK_GROUP_SUSPENSE_ACCOUNT+"'", "");
                        al = new AccLink();
                        if(temp!=null && temp.size()>0){
                            al = (AccLink)temp.get(0);
                        }
                        
                        //journal credit hutang
                        memo = "Hutang/AP";
                        //DbGl.postJournalDetail(er.getValueIdr(), al.getCoaId(), cr.getTotalAmount()+cr.getTotalTax()-cr.getDiscountTotal(), 0,             
                        //        cr.getTotalAmount()+cr.getTotalTax()-cr.getDiscountTotal(), comp.getBookingCurrencyId(), oid, memo, 0); 
                        DbGl.postJournalDetail(er.getValueIdr(), al.getCoaId(), cr.getTotalAmount()+cr.getTotalTax()-cr.getDiscountTotal(), 0,             
                                cr.getTotalAmount()+cr.getTotalTax()-cr.getDiscountTotal(), comp.getBookingCurrencyId(), oid, memo, 0); 
                                
                        */
                    }
                     
                    //optimalkan, jika akunnya sama digabung    
                    DbGl.optimizeJournal(oid);
                    
                }
                
                
        }
        
        public static double getInvoiceBalance(long recOID){
            double result = 0;
            
            Receive r = new Receive();
            try{
                r = DbReceive.fetchExc(recOID);
                result = r.getTotalAmount() - r.getDiscountTotal() + r.getTotalTax();
            }
            catch(Exception e){
                
            }
            
            double payment = DbBankpoPayment.getTotalPaymentByInvoiceFin(recOID);
            
            return result - payment;
            
        }
        
        
        public static void checkForClosed(long bankpoPaymantId, Vector bpoDetails){
            if(bpoDetails!=null && bpoDetails.size()>0){
                for(int i=0; i<bpoDetails.size(); i++){
                    BankpoPaymentDetail bpod = (BankpoPaymentDetail)bpoDetails.get(i);
                    
                    Receive r = new Receive();
                    try{
                        r = DbReceive.fetchExc(bpod.getInvoiceId());
                        double payment = DbBankpoPayment.getTotalPaymentByInvoiceFin(bpod.getInvoiceId());
                        double amount = r.getTotalAmount() - r.getDiscountTotal() + r.getTotalTax();
                        if(payment!=0){
                            if(amount>payment){
                                r.setPaymentStatus(I_Project.INV_STATUS_PARTIALY_PAID);
                            }
                            else{
                                r.setPaymentStatus(I_Project.INV_STATUS_FULL_PAID);
                            }
                            
                            DbReceive.updateExc(r);
                        }
                    }
                    catch(Exception e){                    
                    }
                    
                }
            }
        }
        
        
        public static double getTotalInvoiceByVendor(long oidVendor, Date startDate, Date endDate, long unitUsahaId){
            
            String sql = "SELECT sum("+colNames[COL_TOTAL_AMOUNT]+"-"+colNames[COL_DISCOUNT_TOTAL]+"+"+colNames[COL_TOTAL_TAX]+")"+
                        " FROM "+DB_RECEIVE+" where "+colNames[COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"'"+
                        //" and "+colNames[COL_PAYMENT_STATUS]+"<>"+I_Project.INV_STATUS_FULL_PAID+
                        " and "+colNames[COL_VENDOR_ID]+"="+ oidVendor;

                        if(startDate!=null && endDate!=null){
                            sql = sql + " and "+colNames[COL_DUE_DATE]+
                                " >= '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                " and "+colNames[COL_DUE_DATE]+"<='"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"'";
                        }else if(startDate!=null){
                            sql = sql + " and "+colNames[COL_DUE_DATE]+
                                "<='"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'";
                        }else if(endDate!=null){
                            sql = sql + " and "+colNames[COL_DUE_DATE]+
                                ">='"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"'";
                        }
                        if(unitUsahaId!=0){
                            sql = sql + " and "+colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
                        }
        
            System.out.println("\n\n"+sql);
                        
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
        
        
        public static Vector getReceiveMainData(Date startDate, Date endDate, String vendorNames, int order, String status){
            
            String sql = "select * from "+DB_RECEIVE+" po "+
                        " inner join "+DbVendor.DB_VENDOR+
                        " v on v."+DbVendor.colNames[DbVendor.COL_VENDOR_ID]+"=po."+colNames[COL_VENDOR_ID]+
                        " where po."+colNames[COL_DATE]+ 
                        " between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+" 00:00:00'"+
                        " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+" 23:59:59' ";
                        
                        if(vendorNames!=null && vendorNames.length()>0){
                            sql = sql + " and v."+DbVendor.colNames[DbVendor.COL_NAME]+" like '%"+vendorNames+"%' ";
                        }
            
                        if(status!=null && status.length()>0){
                            sql = sql + " and po."+colNames[COL_STATUS]+" = '"+status+"' ";
                        }
            
                        if(order==0){
                            sql = sql + " order by v."+DbVendor.colNames[DbVendor.COL_NAME];
                        }
                        else{
                            sql = sql + " order by po."+colNames[COL_DATE];
                        }
                        
                        System.out.println(sql);
                                                
            CONResultSet crs = null;
            Vector result = new Vector();
            
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Vector temp = new Vector();
                    Receive pur = new Receive();
                    Vendor ven = new Vendor();
                    resultToObject(rs, pur);
                    DbVendor.resultToObject(rs, ven);
                    temp.add(pur);
                    temp.add(ven);
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
        
        public static Vector getReceiveByItemGroup(Date startDate, Date endDate, String vendorNames, int order, String status, long itemGroupId){
            
            String sql = "select * from "+DB_RECEIVE+" po "+
                        " inner join "+DbReceiveItem.DB_RECEIVE_ITEM+
                        " poi on poi."+DbReceiveItem.colNames[DbReceiveItem.COL_RECEIVE_ID]+
                        " =po."+colNames[COL_RECEIVE_ID]+
                        " inner join "+DbItemMaster.DB_ITEM_MASTER+
                        " im on im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+
                        " =poi."+DbReceiveItem.colNames[DbReceiveItem.COL_ITEM_MASTER_ID]+
                        " inner join "+DbVendor.DB_VENDOR+
                        " v on v."+DbVendor.colNames[DbVendor.COL_VENDOR_ID]+"=po."+colNames[COL_VENDOR_ID]+
                        " where po."+colNames[COL_DATE]+ 
                        " between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+" 00:00:00'"+
                        " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+" 23:59:59' ";
                        
                        if(vendorNames!=null && vendorNames.length()>0){
                            sql = sql + " and v."+DbVendor.colNames[DbVendor.COL_NAME]+" like '%"+vendorNames+"%' ";
                        }
            
                        if(status!=null && status.length()>0){
                            sql = sql + " and po."+colNames[COL_STATUS]+" = '"+status+"' ";
                        }
            
                        if(itemGroupId!=0){
                            sql = sql + " and im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]+"="+itemGroupId;
                        }                        
            
                        if(order==0){
                            sql = sql + " order by v."+DbVendor.colNames[DbVendor.COL_NAME];
                        }
                        else if(order==1){
                            sql = sql + " order by po."+colNames[COL_DATE];
                        }
                        else{
                            sql = sql + " order by im."+DbItemMaster.colNames[DbItemMaster.COL_NAME];
                        }
                        
                        System.out.println(sql);
                                                
            CONResultSet crs = null;
            Vector result = new Vector();
            
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Vector temp = new Vector();
                    Receive pur = new Receive();
                    ReceiveItem purItem = new ReceiveItem();
                    ItemMaster im = new ItemMaster();                    
                    Vendor ven = new Vendor();
                    resultToObject(rs, pur);
                    DbReceiveItem.resultToObject(rs, purItem);
                    DbVendor.resultToObject(rs, ven);
                    DbItemMaster.resultToObject(rs, im);
                    temp.add(pur);
                    temp.add(ven);
                    temp.add(im);
                    temp.add(purItem);
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
        
}
