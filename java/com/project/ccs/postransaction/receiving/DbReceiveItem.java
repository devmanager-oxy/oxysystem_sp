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
import java.util.*;
import com.project.ccs.postransaction.stock.*;
import com.project.system.*;
import com.project.ccs.posmaster.*;
import com.project.ccs.postransaction.stock.*;

public class DbReceiveItem extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {

    public static final String DB_RECEIVE_ITEM = "pos_receive_item";
    public static final int COL_RECEIVE_ITEM_ID = 0;
    public static final int COL_RECEIVE_ID = 1;
    public static final int COL_ITEM_MASTER_ID = 2;
    public static final int COL_QTY = 3;
    public static final int COL_AMOUNT = 4;
    public static final int COL_TOTAL_AMOUNT = 5;
    public static final int COL_TOTAL_DISCOUNT = 6;
    public static final int COL_DELIVERY_DATE = 7;
    public static final int COL_UOM_ID = 8;
    public static final int COL_STATUS = 9;
    
    public static final int COL_PURCHASE_ITEM_ID = 10;
    public static final int COL_EXPIRED_DATE = 11;
    public static final int COL_AP_COA_ID = 12;
    
    public static final String[] colNames = {
        "receive_item_id",
        "receive_id",
        "item_master_id",
        "qty",
        "amount", 
        "total_amount",
        "discount_amount",
        "delivery_date",
        "uom_id",
        "status",
        
        "purchase_item_id",
        "expired_date",
        "ap_coa_id"
    };
    public static final int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_DATE,
        TYPE_LONG,
        TYPE_STRING,
        
        TYPE_LONG,
        TYPE_DATE,
        TYPE_LONG
    };

    public DbReceiveItem() {
    }

    public DbReceiveItem(int i) throws CONException {
        super(new DbReceiveItem());
    }

    public DbReceiveItem(String sOid) throws CONException {
        super(new DbReceiveItem(0));
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public DbReceiveItem(long lOid) throws CONException {
        super(new DbReceiveItem(0));
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
        return DB_RECEIVE_ITEM;
    }

    public String[] getFieldNames() {
        return colNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new DbReceiveItem().getClass().getName();
    }

    public long fetchExc(Entity ent) throws Exception {
        ReceiveItem receiveItem = fetchExc(ent.getOID());
        ent = (Entity) receiveItem;
        return receiveItem.getOID();
    }

    public long insertExc(Entity ent) throws Exception {
        return insertExc((ReceiveItem) ent);
    }

    public long updateExc(Entity ent) throws Exception {
        return updateExc((ReceiveItem) ent);
    }

    public long deleteExc(Entity ent) throws Exception {
        if (ent == null) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        }
        return deleteExc(ent.getOID());
    }

    public static ReceiveItem fetchExc(long oid) throws CONException {
        try {
            ReceiveItem receiveItem = new ReceiveItem();
            DbReceiveItem dbReceiveItem = new DbReceiveItem(oid);
            receiveItem.setOID(oid);

            receiveItem.setReceiveId(dbReceiveItem.getlong(COL_RECEIVE_ID));
            receiveItem.setItemMasterId(dbReceiveItem.getlong(COL_ITEM_MASTER_ID));
            receiveItem.setQty(dbReceiveItem.getdouble(COL_QTY));
            receiveItem.setUomId(dbReceiveItem.getlong(COL_UOM_ID));
            receiveItem.setStatus(dbReceiveItem.getString(COL_STATUS));

            receiveItem.setAmount(dbReceiveItem.getdouble(COL_AMOUNT));
            receiveItem.setTotalAmount(dbReceiveItem.getdouble(COL_TOTAL_AMOUNT));
            receiveItem.setTotalDiscount(dbReceiveItem.getdouble(COL_TOTAL_DISCOUNT));
            receiveItem.setDeliveryDate(dbReceiveItem.getDate(COL_DELIVERY_DATE));
            
            receiveItem.setPurchaseItemId(dbReceiveItem.getlong(COL_PURCHASE_ITEM_ID));
            receiveItem.setExpiredDate(dbReceiveItem.getDate(COL_EXPIRED_DATE));
            receiveItem.setApCoaId(dbReceiveItem.getlong(COL_AP_COA_ID));

            return receiveItem;
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceiveItem(0), CONException.UNKNOWN);
        }
    }

    public static long insertExc(ReceiveItem receiveItem) throws CONException {
        try {
            DbReceiveItem dbReceiveItem = new DbReceiveItem(0);

            dbReceiveItem.setLong(COL_RECEIVE_ID, receiveItem.getReceiveId());
            dbReceiveItem.setLong(COL_ITEM_MASTER_ID, receiveItem.getItemMasterId());
            dbReceiveItem.setDouble(COL_QTY, receiveItem.getQty());
            dbReceiveItem.setLong(COL_UOM_ID, receiveItem.getUomId());
            dbReceiveItem.setString(COL_STATUS, receiveItem.getStatus());

            dbReceiveItem.setDouble(COL_AMOUNT, receiveItem.getAmount());
            dbReceiveItem.setDouble(COL_TOTAL_AMOUNT, receiveItem.getTotalAmount());
            dbReceiveItem.setDouble(COL_TOTAL_DISCOUNT, receiveItem.getTotalDiscount());
            dbReceiveItem.setDate(COL_DELIVERY_DATE, receiveItem.getDeliveryDate());
            
            dbReceiveItem.setLong(COL_PURCHASE_ITEM_ID, receiveItem.getPurchaseItemId());
            dbReceiveItem.setDate(COL_EXPIRED_DATE, receiveItem.getExpiredDate());
            dbReceiveItem.setLong(COL_AP_COA_ID, receiveItem.getApCoaId());

            dbReceiveItem.insert();
            receiveItem.setOID(dbReceiveItem.getlong(COL_RECEIVE_ITEM_ID));
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceiveItem(0), CONException.UNKNOWN);
        }
        return receiveItem.getOID();
    }

    public static long updateExc(ReceiveItem receiveItem) throws CONException {
        try {
            if (receiveItem.getOID() != 0) {
                DbReceiveItem dbReceiveItem = new DbReceiveItem(receiveItem.getOID());

                dbReceiveItem.setLong(COL_RECEIVE_ID, receiveItem.getReceiveId());
                dbReceiveItem.setLong(COL_ITEM_MASTER_ID, receiveItem.getItemMasterId());
                dbReceiveItem.setDouble(COL_QTY, receiveItem.getQty());
                dbReceiveItem.setLong(COL_UOM_ID, receiveItem.getUomId());
                dbReceiveItem.setString(COL_STATUS, receiveItem.getStatus());

                dbReceiveItem.setDouble(COL_AMOUNT, receiveItem.getAmount());
                dbReceiveItem.setDouble(COL_TOTAL_AMOUNT, receiveItem.getTotalAmount());
                dbReceiveItem.setDouble(COL_TOTAL_DISCOUNT, receiveItem.getTotalDiscount());
                dbReceiveItem.setDate(COL_DELIVERY_DATE, receiveItem.getDeliveryDate());
                
                dbReceiveItem.setLong(COL_PURCHASE_ITEM_ID, receiveItem.getPurchaseItemId());
                dbReceiveItem.setDate(COL_EXPIRED_DATE, receiveItem.getExpiredDate());
                dbReceiveItem.setLong(COL_AP_COA_ID, receiveItem.getApCoaId());

                dbReceiveItem.update();
                return receiveItem.getOID();

            }
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceiveItem(0), CONException.UNKNOWN);
        }
        return 0;
    }

    public static long deleteExc(long oid) throws CONException {
        try {
            DbReceiveItem dbReceiveItem = new DbReceiveItem(oid);
            dbReceiveItem.delete();
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceiveItem(0), CONException.UNKNOWN);
        }
        return oid;
    }

    public static long deleteAllItem(long oidMain) throws CONException {
        try {
            String sql = "DELETE FROM " + DB_RECEIVE_ITEM +
                    " WHERE " + colNames[COL_RECEIVE_ID] + "=" + oidMain;
            CONHandler.execUpdate(sql);
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbReceiveItem(0), CONException.UNKNOWN);
        }
        return oidMain;
    }

    public static Vector listAll() {
        return list(0, 500, "", "");
    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        CONResultSet dbrs = null;
        try {
            String sql = "SELECT * FROM " + DB_RECEIVE_ITEM;
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
                ReceiveItem receiveItem = new ReceiveItem();
                resultToObject(rs, receiveItem);
                lists.add(receiveItem);
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

    public static void resultToObject(ResultSet rs, ReceiveItem receiveItem) {
        try {
            receiveItem.setOID(rs.getLong(DbReceiveItem.colNames[DbReceiveItem.COL_RECEIVE_ITEM_ID]));
            receiveItem.setReceiveId(rs.getLong(DbReceiveItem.colNames[DbReceiveItem.COL_RECEIVE_ID]));
            receiveItem.setItemMasterId(rs.getLong(DbReceiveItem.colNames[DbReceiveItem.COL_ITEM_MASTER_ID]));
            receiveItem.setQty(rs.getDouble(DbReceiveItem.colNames[DbReceiveItem.COL_QTY]));
            receiveItem.setUomId(rs.getLong(DbReceiveItem.colNames[DbReceiveItem.COL_UOM_ID]));
            receiveItem.setStatus(rs.getString(DbReceiveItem.colNames[DbReceiveItem.COL_STATUS]));

            receiveItem.setAmount(rs.getDouble(DbReceiveItem.colNames[DbReceiveItem.COL_AMOUNT]));
            receiveItem.setTotalAmount(rs.getDouble(DbReceiveItem.colNames[DbReceiveItem.COL_TOTAL_AMOUNT]));
            receiveItem.setTotalDiscount(rs.getDouble(DbReceiveItem.colNames[DbReceiveItem.COL_TOTAL_DISCOUNT]));
            receiveItem.setDeliveryDate(rs.getDate(DbReceiveItem.colNames[DbReceiveItem.COL_DELIVERY_DATE]));
            
            receiveItem.setPurchaseItemId(rs.getLong(DbReceiveItem.colNames[DbReceiveItem.COL_PURCHASE_ITEM_ID]));
            receiveItem.setExpiredDate(rs.getDate(DbReceiveItem.colNames[DbReceiveItem.COL_EXPIRED_DATE]));
            receiveItem.setApCoaId(rs.getLong(DbReceiveItem.colNames[DbReceiveItem.COL_AP_COA_ID]));
            
        } catch (Exception e) {
        }
    }

    public static boolean checkOID(long receiveItemId) {
        CONResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + DB_RECEIVE_ITEM + " WHERE " +
                    DbReceiveItem.colNames[DbReceiveItem.COL_RECEIVE_ITEM_ID] + " = " + receiveItemId;

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
            String sql = "SELECT COUNT(" + DbReceiveItem.colNames[DbReceiveItem.COL_RECEIVE_ID] + ") FROM " + DB_RECEIVE_ITEM;
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
                    ReceiveItem receiveItem = (ReceiveItem) list.get(ls);
                    if (oid == receiveItem.getOID()) {
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
    
    public static double getTotalReceiveAmount(long poOID){
        double result = 0;
        CONResultSet crs = null;
        try{
            String sql = " select sum("+DbReceiveItem.colNames[DbReceiveItem.COL_TOTAL_AMOUNT]+") from "+DB_RECEIVE_ITEM+
                         " where "+colNames[COL_RECEIVE_ID]+"="+poOID;
            
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
    
    public static void deleteByReceiveId(long receiveId){
        try{
            CONHandler.execUpdate("delete from "+DB_RECEIVE_ITEM+" where "+colNames[COL_RECEIVE_ID]+"="+receiveId);            
        }
        catch(Exception e){
            
        }
    }
    
    public static int insertByItem(long oidReceive, long itemMasterId, long prItemId, double qty, double amount, double discout, double totAmount, long uomId){
        
        if(oidReceive==0 || itemMasterId==0 || prItemId==0 || qty==0){
            return -1;
        }
        else{
            try{
                ReceiveItem ri = new ReceiveItem();
                ri.setAmount(amount);
                ri.setItemMasterId(itemMasterId);
                ri.setPurchaseItemId(prItemId);
                ri.setQty(qty);
                ri.setReceiveId(oidReceive);
                ri.setTotalAmount(totAmount);
                ri.setTotalDiscount(discout);
                ri.setUomId(uomId);
                ri.setDeliveryDate(new Date());
               
                DbReceiveItem.insertExc(ri);
            }
            catch(Exception e){
            }
        }
        
        return 0;
        
    }
    
    public static double getTotalQtyRec(long prItemId){
        
        String sql = "select sum("+colNames[COL_QTY]+") from "+DB_RECEIVE_ITEM+" where "+colNames[COL_PURCHASE_ITEM_ID]+"="+prItemId;
        
        double result = 0;
        
        CONResultSet crs = null;
        try{
            crs = CONHandler.execQueryResult(sql);
            ResultSet rs = crs.getResultSet();
            while(rs.next()){
                result = rs.getInt(1);
            }
        }
        catch(Exception e){
        }
        finally{
            CONResultSet.close(crs);
        }
        
        return result;
    }
    
    public static ReceiveItem getReceiveItem(long prItemId, long oidReceive){
        String where = colNames[COL_PURCHASE_ITEM_ID]+"="+prItemId+" and "+colNames[COL_RECEIVE_ID]+"="+oidReceive;
        Vector v = DbReceiveItem.list(0,0, where, "");
        if(v!=null && v.size()>0){
            return (ReceiveItem)v.get(0);
        }
        return new ReceiveItem();
    }
    
    public static void proceedStock(Receive receive){
        
        System.out.println("in inserting stock ...");    
            
        Vector temp = DbReceiveItem.list(0,0, colNames[COL_RECEIVE_ID]+"="+receive.getOID(), "");
        
        System.out.println("temp : "+temp);    
        
        if(temp!=null && temp.size()>0){
            for(int i=0; i<temp.size(); i++){
                ReceiveItem ri = (ReceiveItem)temp.get(i); 
                
                ItemMaster im = new ItemMaster();
                try{
                    im = DbItemMaster.fetchExc(ri.getItemMasterId());
                }
                catch(Exception e){
                    
                }
                
                //jika bukan jasa (stockable) lakukan proses penambahan stock
                if(im.getNeedRecipe()==0){
                    int stockType = Integer.parseInt(DbSystemProperty.getValueByName("STOCK_MANAGEMENT_TYPE"));                
                    //average
                    if(stockType==0){
                        updateItemAverageCost(ri);
                    }
                    //update dengan harga terakhir
                    else if(stockType==1){
                        updateItemLastPriceCost(ri);
                    }

                    DbStock.insertReceiveGoods(receive, ri); 
                }
            }            
        }
        
    }  
    
    public static void updateItemAverageCost(ReceiveItem ri){
        
        ItemMaster im = new ItemMaster();
        
        try{
            im = DbItemMaster.fetchExc(ri.getItemMasterId());
                
            double totalStock = DbStock.getItemTotalStock(im.getOID()); 
            
            System.out.println("\n===== average ===========\n\ntotalStock : "+totalStock);

            double totalCost = (totalStock * im.getCogs()) + (ri.getTotalAmount());
            
            System.out.println("im.getCogs() : "+im.getCogs());
            System.out.println("ri.getTotalAmount() : "+ri.getTotalAmount());
            System.out.println("totalCost : "+totalCost);
            System.out.println("ri.getQty() : "+ri.getQty());
            System.out.println("ri.getQty() : "+ri.getQty());

            totalStock = totalStock + ri.getQty();
            
            System.out.println("totalStock : "+totalStock);
            System.out.println("totalCost/totalStock : "+(totalCost/totalStock));
            System.out.println("\n========================================");

            im.setCogs(totalCost/totalStock);
            
            DbItemMaster.updateExc(im);

        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        
    }
    
    
    public static void updateItemLastPriceCost(ReceiveItem ri){
        
        ItemMaster im = new ItemMaster();
        
        try{
            im = DbItemMaster.fetchExc(ri.getItemMasterId());
        
            double totalStock = ri.getQty();

            im.setCogs(ri.getTotalAmount()/totalStock);
            
            DbItemMaster.updateExc(im);

        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        
    }
    
    
    
}
