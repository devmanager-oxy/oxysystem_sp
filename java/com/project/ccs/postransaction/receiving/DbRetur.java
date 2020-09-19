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

public class DbRetur extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {

    public static final String DB_RETUR = "pos_retur";
    public static final int COL_RETUR_ID = 0;
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
    public static final int COL_RECEIVE_ID  = 30;    
    
    public static final String[] colNames = {
        "retur_id",
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
        "receive_id"
        
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
        TYPE_LONG
    };
    
    public static int INCLUDE_TAX_NO = 0;
    public static int INCLUDE_TAX_YES = 1;
    public static String[] strIncludeTax = {"No", "Yes"};

    public DbRetur() {
    }

    public DbRetur(int i) throws CONException {
        super(new DbRetur());
    }

    public DbRetur(String sOid) throws CONException {
        super(new DbRetur(0));
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public DbRetur(long lOid) throws CONException {
        super(new DbRetur(0));
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
        return DB_RETUR;
    }

    public String[] getFieldNames() {
        return colNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new DbRetur().getClass().getName();
    }

    public long fetchExc(Entity ent) throws Exception {
        Retur retur = fetchExc(ent.getOID());
        ent = (Entity) retur;
        return retur.getOID();
    }

    public long insertExc(Entity ent) throws Exception {
        return insertExc((Retur) ent);
    }

    public long updateExc(Entity ent) throws Exception {
        return updateExc((Retur) ent);
    }

    public long deleteExc(Entity ent) throws Exception {
        if (ent == null) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        }
        return deleteExc(ent.getOID());
    }

    public static Retur fetchExc(long oid) throws CONException {
        try {
            Retur retur = new Retur();
            DbRetur dbReturRequest = new DbRetur(oid);
            retur.setOID(oid);
            retur.setApproval1(dbReturRequest.getlong(COL_APPROVAL_1));
            retur.setApproval2(dbReturRequest.getlong(COL_APPROVAL_2));
            retur.setApproval3(dbReturRequest.getlong(COL_APPROVAL_3));
            retur.setCounter(dbReturRequest.getInt(COL_COUNTER));
            retur.setIncluceTax(dbReturRequest.getInt(COL_INCLUDE_TAX));
            retur.setNote(dbReturRequest.getString(COL_NOTE));
            retur.setNumber(dbReturRequest.getString(COL_NUMBER));
            retur.setDate(dbReturRequest.getDate(COL_DATE));
            retur.setStatus(dbReturRequest.getString(COL_STATUS));
            retur.setUserId(dbReturRequest.getlong(COL_USER_ID));
            retur.setTotalTax(dbReturRequest.getdouble(COL_TOTAL_TAX));
            retur.setTotalAmount(dbReturRequest.getdouble(COL_TOTAL_AMOUNT));
            retur.setTaxPercent(dbReturRequest.getdouble(COL_TAX_PERCENT));
            retur.setDiscountTotal(dbReturRequest.getdouble(COL_DISCOUNT_TOTAL));
            retur.setDiscountPercent(dbReturRequest.getdouble(COL_DISCOUNT_PERCENT));
            retur.setPaymentType(dbReturRequest.getString(COL_PAYMENT_TYPE));
            retur.setLocationId(dbReturRequest.getlong(COL_LOCATION_ID));
            retur.setVendorId(dbReturRequest.getlong(COL_VENDOR_ID));            
            retur.setCurrencyId(dbReturRequest.getlong(COL_CURRENCY_ID));
            retur.setPrefixNumber(dbReturRequest.getString(COL_PREFIX_NUMBER));
            retur.setClosedReason(dbReturRequest.getString(COL_CLOSED_REASON));
            
            retur.setApproval1Date(dbReturRequest.getDate(COL_APPROVAL_1_DATE));
            retur.setApproval2Date(dbReturRequest.getDate(COL_APPROVAL_2_DATE));
            retur.setApproval3Date(dbReturRequest.getDate(COL_APPROVAL_3_DATE));
            
            retur.setPurchaseId(dbReturRequest.getlong(COL_PURCHASE_ID));
            retur.setDueDate(dbReturRequest.getDate(COL_DUE_DATE));
            retur.setPaymentAmount(dbReturRequest.getdouble(COL_PAYMENT_AMOUNT));
            retur.setDoNumber(dbReturRequest.getString(COL_DO_NUMBER));
            retur.setInvoiceNumber(dbReturRequest.getString(COL_INVOICE_NUMBER));
            retur.setReceiveId(dbReturRequest.getlong(COL_RECEIVE_ID));

            return retur;
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbRetur(0), CONException.UNKNOWN);
        }
    }

    public static long insertExc(Retur retur) throws CONException {
        try {
            DbRetur dbReturRequest = new DbRetur(0);

            dbReturRequest.setLong(COL_APPROVAL_1, retur.getApproval1());
            dbReturRequest.setLong(COL_APPROVAL_2, retur.getApproval2());
            dbReturRequest.setLong(COL_APPROVAL_3, retur.getApproval3());
            dbReturRequest.setInt(COL_COUNTER, retur.getCounter());
            dbReturRequest.setInt(COL_INCLUDE_TAX, retur.getIncluceTax());
            dbReturRequest.setString(COL_NOTE, retur.getNote());
            dbReturRequest.setString(COL_NUMBER, retur.getNumber());
            dbReturRequest.setDate(COL_DATE, retur.getDate());
            dbReturRequest.setString(COL_STATUS, retur.getStatus());
            dbReturRequest.setLong(COL_USER_ID, retur.getUserId());
            dbReturRequest.setDouble(COL_TOTAL_TAX, retur.getTotalTax());
            dbReturRequest.setDouble(COL_TOTAL_AMOUNT, retur.getTotalAmount());
            dbReturRequest.setDouble(COL_TAX_PERCENT, retur.getTaxPercent());
            dbReturRequest.setDouble(COL_DISCOUNT_TOTAL, retur.getDiscountTotal());
            dbReturRequest.setDouble(COL_DISCOUNT_PERCENT, retur.getDiscountPercent());
            dbReturRequest.setString(COL_PAYMENT_TYPE, retur.getPaymentType());
            dbReturRequest.setLong(COL_LOCATION_ID, retur.getLocationId());
            dbReturRequest.setLong(COL_VENDOR_ID, retur.getVendorId());
            dbReturRequest.setLong(COL_CURRENCY_ID, retur.getCurrencyId());
            dbReturRequest.setString(COL_PREFIX_NUMBER, retur.getPrefixNumber());
            dbReturRequest.setString(COL_CLOSED_REASON, retur.getClosedReason());
            
            dbReturRequest.setDate(COL_APPROVAL_1_DATE, retur.getApproval1Date());
            dbReturRequest.setDate(COL_APPROVAL_2_DATE, retur.getApproval2Date());
            dbReturRequest.setDate(COL_APPROVAL_3_DATE, retur.getApproval3Date());
            
            dbReturRequest.setLong(COL_PURCHASE_ID, retur.getPurchaseId());
            dbReturRequest.setDate(COL_DUE_DATE, retur.getDueDate());
            dbReturRequest.setDouble(COL_PAYMENT_AMOUNT, retur.getPaymentAmount());
            dbReturRequest.setString(COL_DO_NUMBER, retur.getDoNumber());
            dbReturRequest.setString(COL_INVOICE_NUMBER, retur.getInvoiceNumber());
            dbReturRequest.setLong(COL_RECEIVE_ID, retur.getReceiveId());

            dbReturRequest.insert();
            retur.setOID(dbReturRequest.getlong(COL_RETUR_ID));
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbRetur(0), CONException.UNKNOWN);
        }
        return retur.getOID();
    }

    public static long updateExc(Retur retur) throws CONException {
        try {
            if (retur.getOID() != 0) {
                DbRetur dbReturRequest = new DbRetur(retur.getOID());

                dbReturRequest.setLong(COL_APPROVAL_1, retur.getApproval1());
                dbReturRequest.setLong(COL_APPROVAL_2, retur.getApproval2());
                dbReturRequest.setLong(COL_APPROVAL_3, retur.getApproval3());
                dbReturRequest.setInt(COL_COUNTER, retur.getCounter());
                dbReturRequest.setInt(COL_INCLUDE_TAX, retur.getIncluceTax());
                dbReturRequest.setString(COL_NOTE, retur.getNote());
                dbReturRequest.setString(COL_NUMBER, retur.getNumber());
                dbReturRequest.setDate(COL_DATE, retur.getDate());
                dbReturRequest.setString(COL_STATUS, retur.getStatus());
                dbReturRequest.setLong(COL_USER_ID, retur.getUserId());
                dbReturRequest.setDouble(COL_TOTAL_TAX, retur.getTotalTax());
                dbReturRequest.setDouble(COL_TOTAL_AMOUNT, retur.getTotalAmount());
                dbReturRequest.setDouble(COL_TAX_PERCENT, retur.getTaxPercent());
                dbReturRequest.setDouble(COL_DISCOUNT_TOTAL, retur.getDiscountTotal());
                dbReturRequest.setDouble(COL_DISCOUNT_PERCENT, retur.getDiscountPercent());
                dbReturRequest.setString(COL_PAYMENT_TYPE, retur.getPaymentType());
                dbReturRequest.setLong(COL_LOCATION_ID, retur.getLocationId());
                dbReturRequest.setLong(COL_VENDOR_ID, retur.getVendorId());
                dbReturRequest.setLong(COL_CURRENCY_ID, retur.getCurrencyId());
                dbReturRequest.setString(COL_PREFIX_NUMBER, retur.getPrefixNumber());
                dbReturRequest.setString(COL_CLOSED_REASON, retur.getClosedReason());
                
                dbReturRequest.setDate(COL_APPROVAL_1_DATE, retur.getApproval1Date());
                dbReturRequest.setDate(COL_APPROVAL_2_DATE, retur.getApproval2Date());
                dbReturRequest.setDate(COL_APPROVAL_3_DATE, retur.getApproval3Date());
                
                dbReturRequest.setLong(COL_PURCHASE_ID, retur.getPurchaseId());
                dbReturRequest.setDate(COL_DUE_DATE, retur.getDueDate());
                dbReturRequest.setDouble(COL_PAYMENT_AMOUNT, retur.getPaymentAmount());
                dbReturRequest.setString(COL_DO_NUMBER, retur.getDoNumber());
                dbReturRequest.setString(COL_INVOICE_NUMBER, retur.getInvoiceNumber()); 
                dbReturRequest.setLong(COL_RECEIVE_ID, retur.getReceiveId());                

                dbReturRequest.update();
                return retur.getOID();

            }
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbRetur(0), CONException.UNKNOWN);
        }
        return 0;
    }

    public static long deleteExc(long oid) throws CONException {
        try {
            DbRetur pstReturRequest = new DbRetur(oid);
            pstReturRequest.delete();
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbRetur(0), CONException.UNKNOWN);
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
            String sql = "SELECT * FROM " + DB_RETUR;
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
                Retur retur = new Retur();
                resultToObject(rs, retur);
                lists.add(retur);
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

    private static void resultToObject(ResultSet rs, Retur retur) {
        try {
            retur.setOID(rs.getLong(DbRetur.colNames[DbRetur.COL_RETUR_ID]));
            retur.setApproval1(rs.getLong(DbRetur.colNames[DbRetur.COL_APPROVAL_1]));
            retur.setApproval2(rs.getLong(DbRetur.colNames[DbRetur.COL_APPROVAL_2]));
            retur.setApproval3(rs.getLong(DbRetur.colNames[DbRetur.COL_APPROVAL_3]));
            retur.setCounter(rs.getInt(DbRetur.colNames[DbRetur.COL_COUNTER]));
            retur.setIncluceTax(rs.getInt(DbRetur.colNames[DbRetur.COL_INCLUDE_TAX]));
            retur.setNote(rs.getString(DbRetur.colNames[DbRetur.COL_NOTE]));
            retur.setNumber(rs.getString(DbRetur.colNames[DbRetur.COL_NUMBER]));
            retur.setDate(rs.getDate(DbRetur.colNames[DbRetur.COL_DATE]));
            retur.setStatus(rs.getString(DbRetur.colNames[DbRetur.COL_STATUS]));
            retur.setUserId(rs.getLong(DbRetur.colNames[DbRetur.COL_USER_ID]));
            retur.setTotalTax(rs.getDouble(DbRetur.colNames[DbRetur.COL_TOTAL_TAX]));
            retur.setTotalAmount(rs.getDouble(DbRetur.colNames[DbRetur.COL_TOTAL_AMOUNT]));
            retur.setTaxPercent(rs.getDouble(DbRetur.colNames[DbRetur.COL_TAX_PERCENT]));
            retur.setDiscountTotal(rs.getDouble(DbRetur.colNames[DbRetur.COL_DISCOUNT_TOTAL]));
            retur.setDiscountPercent(rs.getDouble(DbRetur.colNames[DbRetur.COL_DISCOUNT_PERCENT]));
            retur.setPaymentType(rs.getString(DbRetur.colNames[DbRetur.COL_PAYMENT_TYPE]));
            retur.setLocationId(rs.getLong(DbRetur.colNames[DbRetur.COL_LOCATION_ID]));
            retur.setVendorId(rs.getLong(DbRetur.colNames[DbRetur.COL_VENDOR_ID]));
            retur.setCurrencyId(rs.getLong(DbRetur.colNames[DbRetur.COL_CURRENCY_ID]));
            retur.setPrefixNumber(rs.getString(DbRetur.colNames[DbRetur.COL_PREFIX_NUMBER]));
            retur.setClosedReason(rs.getString(DbRetur.colNames[DbRetur.COL_CLOSED_REASON]));
            
            retur.setApproval1Date(rs.getDate(DbRetur.colNames[DbRetur.COL_APPROVAL_1_DATE]));
            retur.setApproval2Date(rs.getDate(DbRetur.colNames[DbRetur.COL_APPROVAL_2_DATE]));
            retur.setApproval3Date(rs.getDate(DbRetur.colNames[DbRetur.COL_APPROVAL_3_DATE]));
            
            retur.setPurchaseId(rs.getLong(DbRetur.colNames[DbRetur.COL_PURCHASE_ID]));
            retur.setDueDate(rs.getDate(DbRetur.colNames[DbRetur.COL_DUE_DATE]));
            retur.setPaymentAmount(rs.getDouble(DbRetur.colNames[DbRetur.COL_PAYMENT_AMOUNT]));
            retur.setDoNumber(rs.getString(DbRetur.colNames[DbRetur.COL_DO_NUMBER]));
            retur.setInvoiceNumber(rs.getString(DbRetur.colNames[DbRetur.COL_INVOICE_NUMBER]));
            retur.setReceiveId(rs.getLong(DbRetur.colNames[DbRetur.COL_RECEIVE_ID]));
            
        } catch (Exception e) {
        }
    }

    public static boolean checkOID(long returId) {
        CONResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + DB_RETUR + " WHERE " +
                    DbRetur.colNames[DbRetur.COL_RETUR_ID] + " = " + returId;

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
            String sql = "SELECT COUNT(" + DbRetur.colNames[DbRetur.COL_RETUR_ID] + ") FROM " + DB_RETUR;
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
                    Retur retur = (Retur) list.get(ls);
                    if (oid == retur.getOID()) {
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
                    String sql = "select max("+colNames[COL_COUNTER]+") from "+DB_RETUR+" where "+
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
                code = code + sysCompany.getReturnGoodsCode(); 
                
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
        
        
       /* public static void validateReturItem(Retur retur){
                Vector outVendorItem = QrVendorItem.getOutVendorItems(retur); 
                if(outVendorItem!=null && outVendorItem.size()>0){
                    for(int i=0; i<outVendorItem.size(); i++){
                        ReturItem pi = (ReturItem)outVendorItem.get(i);
                        try{
                            DbReturItem.deleteExc(pi.getOID());
                        }
                        catch(Exception e){
                            System.out.println(e.toString());
                        }
                    }
                }
        }
        */
        
        
        public static void fixGrandTotalAmount(long oidRetur){
            
            if(oidRetur!=0){
                Retur p = new Retur();
                try{
                    p = DbRetur.fetchExc(oidRetur);
                    p.setTotalAmount(DbReturItem.getTotalReturAmount(oidRetur));
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
                    
                    DbRetur.updateExc(p);
                    
                }
                catch(Exception e){

                }
            }
            
        }
        
        public static double getTotalInvoiceByVendor(long oidVendor, Date startDate, Date endDate, long unitUsahaId){
            
            String sql = "SELECT sum("+colNames[COL_TOTAL_AMOUNT]+"-"+colNames[COL_DISCOUNT_TOTAL]+"+"+colNames[COL_TOTAL_TAX]+")"+
                        " FROM "+DB_RETUR+" where "+colNames[COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"'"+
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
        
        
        public static Vector getReturMainData(Date startDate, Date endDate, String vendorNames, int order, String status){
            
            String sql = "select * from "+DB_RETUR+" po "+
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
                    Retur pur = new Retur();
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
        
        public static Vector getReturByItemGroup(Date startDate, Date endDate, String vendorNames, int order, String status, long itemGroupId){
            
            String sql = "select * from "+DB_RETUR+" po "+
                        " inner join "+DbReturItem.DB_RETUR_ITEM+
                        " poi on poi."+DbReturItem.colNames[DbReturItem.COL_RETUR_ID]+
                        " =po."+colNames[COL_RETUR_ID]+
                        " inner join "+DbItemMaster.DB_ITEM_MASTER+
                        " im on im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+
                        " =poi."+DbReturItem.colNames[DbReturItem.COL_ITEM_MASTER_ID]+
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
                    Retur pur = new Retur();
                    ReturItem purItem = new ReturItem();
                    ItemMaster im = new ItemMaster();                    
                    Vendor ven = new Vendor();
                    resultToObject(rs, pur);
                    DbReturItem.resultToObject(rs, purItem);
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
