package com.project.ccs.postransaction.transfer;

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
import com.project.ccs.postransaction.*;
import com.project.ccs.postransaction.stock.*;

public class DbTransferItem extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {

    public static final String DB_POS_TRANSFER_ITEM = "pos_transfer_item";
    public static final int COL_TRANSFER_ITEM_ID = 0;
    public static final int COL_TRANSFER_ID = 1;
    public static final int COL_ITEM_MASTER_ID = 2;
    public static final int COL_QTY = 3;
    public static final int COL_PRICE = 4;
    public static final int COL_AMOUNT = 5;
    public static final int COL_NOTE = 6;
    public static final int COL_EXP_DATE = 7;
    
    public static final String[] colNames = {
        "transfer_item_id", 
        "transfer_id",
        "item_master_id",
        "qty",
        "price",
        "amount",
        "note",
        "exp_date"
    };
    
    public static final int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_FLOAT,
        TYPE_STRING,
        TYPE_DATE
    };

    public DbTransferItem() {
    }

    public DbTransferItem(int i) throws CONException {
        super(new DbTransferItem());
    }

    public DbTransferItem(String sOid) throws CONException {
        super(new DbTransferItem(0));
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public DbTransferItem(long lOid) throws CONException {
        super(new DbTransferItem(0));
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
        return DB_POS_TRANSFER_ITEM;
    }

    public String[] getFieldNames() {
        return colNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new DbTransferItem().getClass().getName();
    }

    public long fetchExc(Entity ent) throws Exception {
        TransferItem transferitem = fetchExc(ent.getOID());
        ent = (Entity) transferitem;
        return transferitem.getOID();
    }

    public long insertExc(Entity ent) throws Exception {
        return insertExc((TransferItem) ent);
    }

    public long updateExc(Entity ent) throws Exception {
        return updateExc((TransferItem) ent);
    }

    public long deleteExc(Entity ent) throws Exception {
        if (ent == null) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        }
        return deleteExc(ent.getOID());
    }

    public static TransferItem fetchExc(long oid) throws CONException {
        try {
            TransferItem transferitem = new TransferItem();
            DbTransferItem pstTransferItem = new DbTransferItem(oid);
            transferitem.setOID(oid);

            transferitem.setTransferId(pstTransferItem.getlong(COL_TRANSFER_ID));
            transferitem.setItemMasterId(pstTransferItem.getlong(COL_ITEM_MASTER_ID));
            transferitem.setQty(pstTransferItem.getdouble(COL_QTY));
            transferitem.setPrice(pstTransferItem.getdouble(COL_PRICE));
            transferitem.setAmount(pstTransferItem.getdouble(COL_AMOUNT));
            transferitem.setNote(pstTransferItem.getString(COL_NOTE));
            transferitem.setExpDate(pstTransferItem.getDate(COL_EXP_DATE));

            return transferitem;
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbTransferItem(0), CONException.UNKNOWN);
        }
    }

    public static long insertExc(TransferItem transferitem) throws CONException {
        try {
            DbTransferItem pstTransferItem = new DbTransferItem(0);

            pstTransferItem.setLong(COL_TRANSFER_ID, transferitem.getTransferId());
            pstTransferItem.setLong(COL_ITEM_MASTER_ID, transferitem.getItemMasterId());
            pstTransferItem.setDouble(COL_QTY, transferitem.getQty());
            pstTransferItem.setDouble(COL_PRICE, transferitem.getPrice());
            pstTransferItem.setDouble(COL_AMOUNT, transferitem.getAmount());
            pstTransferItem.setString(COL_NOTE, transferitem.getNote());
            pstTransferItem.setDate(COL_EXP_DATE, transferitem.getExpDate());

            pstTransferItem.insert();
            transferitem.setOID(pstTransferItem.getlong(COL_TRANSFER_ITEM_ID));
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbTransferItem(0), CONException.UNKNOWN);
        }
        return transferitem.getOID();
    }

    public static long updateExc(TransferItem transferitem) throws CONException {
        try {
            if (transferitem.getOID() != 0) {
                DbTransferItem pstTransferItem = new DbTransferItem(transferitem.getOID());

                pstTransferItem.setLong(COL_TRANSFER_ID, transferitem.getTransferId());
                pstTransferItem.setLong(COL_ITEM_MASTER_ID, transferitem.getItemMasterId());
                pstTransferItem.setDouble(COL_QTY, transferitem.getQty());
                pstTransferItem.setDouble(COL_PRICE, transferitem.getPrice());
                pstTransferItem.setDouble(COL_AMOUNT, transferitem.getAmount());
                pstTransferItem.setString(COL_NOTE, transferitem.getNote());
                pstTransferItem.setDate(COL_EXP_DATE, transferitem.getExpDate());

                pstTransferItem.update();
                return transferitem.getOID();

            }
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbTransferItem(0), CONException.UNKNOWN);
        }
        return 0;
    }

    public static long deleteExc(long oid) throws CONException {
        try {
            DbTransferItem pstTransferItem = new DbTransferItem(oid);
            pstTransferItem.delete();
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbTransferItem(0), CONException.UNKNOWN);
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
            String sql = "SELECT * FROM " + DB_POS_TRANSFER_ITEM;
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
                TransferItem transferitem = new TransferItem();
                resultToObject(rs, transferitem);
                lists.add(transferitem);
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

    private static void resultToObject(ResultSet rs, TransferItem transferitem) {
        try {
            transferitem.setOID(rs.getLong(DbTransferItem.colNames[DbTransferItem.COL_TRANSFER_ITEM_ID]));
            transferitem.setTransferId(rs.getLong(DbTransferItem.colNames[DbTransferItem.COL_TRANSFER_ID]));
            transferitem.setItemMasterId(rs.getLong(DbTransferItem.colNames[DbTransferItem.COL_ITEM_MASTER_ID]));
            transferitem.setQty(rs.getDouble(DbTransferItem.colNames[DbTransferItem.COL_QTY]));
            transferitem.setPrice(rs.getDouble(DbTransferItem.colNames[DbTransferItem.COL_PRICE]));
            transferitem.setAmount(rs.getDouble(DbTransferItem.colNames[DbTransferItem.COL_AMOUNT]));
            transferitem.setNote(rs.getString(DbTransferItem.colNames[DbTransferItem.COL_NOTE]));
            transferitem.setExpDate(rs.getDate(DbTransferItem.colNames[DbTransferItem.COL_EXP_DATE]));

        } catch (Exception e) {
        }
    }

    public static boolean checkOID(long transferItemId) {
        CONResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + DB_POS_TRANSFER_ITEM + " WHERE " +
                    DbTransferItem.colNames[DbTransferItem.COL_TRANSFER_ITEM_ID] + " = " + transferItemId;

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
            String sql = "SELECT COUNT(" + DbTransferItem.colNames[DbTransferItem.COL_TRANSFER_ITEM_ID] + ") FROM " + DB_POS_TRANSFER_ITEM;
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
                    TransferItem transferitem = (TransferItem) list.get(ls);
                    if (oid == transferitem.getOID()) {
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

    public static double getTotalTransferAmount(long transferOID){
        double result = 0;
        CONResultSet crs = null;
        try{
            String sql = " select sum("+DbTransferItem.colNames[DbTransferItem.COL_AMOUNT]+") from "+DB_POS_TRANSFER_ITEM+
                         " where "+colNames[COL_TRANSFER_ID]+"="+transferOID;

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
    
    public static int deleteAllItem(long oidTransfer){
        
        int result = 0;
        
        String sql = "delete from "+DB_POS_TRANSFER_ITEM+" where "+colNames[COL_TRANSFER_ID]+" = "+oidTransfer;
        try{
            CONHandler.execUpdate(sql);
        }
        catch(Exception e){
            result = -1;
        }
        
        return result;
    }
    
    
    public static void proceedStock(Transfer transfer){
        
        System.out.println("in inserting stock - transfer ...");    
            
        Vector temp = DbTransferItem.list(0,0, colNames[COL_TRANSFER_ID]+"="+transfer.getOID(), "");
        
        //System.out.println("temp : "+temp);    
        
        if(temp!=null && temp.size()>0){
            for(int i=0; i<temp.size(); i++){
                TransferItem ri = (TransferItem)temp.get(i);                
                DbStock.insertTransferGoods(transfer, ri);                
            }            
        }
        
    }  
    
    
}
