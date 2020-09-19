package com.project.ccs.postransaction.stock; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
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
import com.project.ccs.posmaster.*;
import com.project.ccs.postransaction.receiving.*;
import com.project.ccs.postransaction.transfer.*;
import com.project.ccs.postransaction.adjusment.*;
import com.project.ccs.postransaction.opname.*;
import com.project.general.*;
import com.project.ccs.*;
import com.project.fms.asset.*;
import com.project.ccs.postransaction.sales.*;
import com.project.system.*;

public class DbStock extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_POS_STOCK = "pos_stock";

	public static final  int COL_STOCK_ID = 0;
	public static final  int COL_LOCATION_ID = 1;
	public static final  int COL_TYPE = 2;
	public static final  int COL_QTY = 3;
	public static final  int COL_PRICE = 4;
	public static final  int COL_TOTAL = 5;
	public static final  int COL_ITEM_MASTER_ID = 6;
	public static final  int COL_ITEM_CODE = 7;
	public static final  int COL_ITEM_BARCODE = 8;
	public static final  int COL_ITEM_NAME = 9;
	public static final  int COL_UNIT_ID = 10;
	public static final  int COL_UNIT = 11;
	public static final  int COL_IN_OUT = 12;
	public static final  int COL_DATE = 13;
	public static final  int COL_USER_ID = 14;
	public static final  int COL_NO_FAKTUR = 15;
	public static final  int COL_INCOMING_ID = 16;
	public static final  int COL_RETUR_ID = 17;
	public static final  int COL_TRANSFER_ID = 18;
	public static final  int COL_TRANSFER_IN_ID = 19;
	public static final  int COL_ADJUSTMENT_ID = 20;
        
        public static final  int COL_OPNAME_ID = 21;
        public static final  int COL_SALES_DETAIL_ID = 22;

	public static final  String[] colNames = {
		"stock_id",
                "location_id",
                "type",
                "qty",
                "price",
                "total",
                "item_master_id",
                "item_code",
                "item_barcode",
                "item_name",
                "unit_id",
                "unit",
                "in_out",
                "date",
                "user_id",
                "no_faktur",
                "incoming_id",
                "retur_id",
                "transfer_id",
                "transfer_in_id",
                "adjustment_id",
                "opname_id",
                "sales_detail_id"

	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_INT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_FLOAT,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_INT,
		TYPE_DATE,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
                TYPE_LONG,
                TYPE_LONG
	 }; 
         
        public static int TYPE_INCOMING_GOODS   = 0;
        public static int TYPE_RETUR_GOODS      = 1;
        public static int TYPE_TRANSFER         = 2;
        public static int TYPE_TRANSFER_IN      = 3;
        public static int TYPE_ADJUSTMENT       = 4;
        public static int TYPE_OPNAME       = 5;
        public static int TYPE_PROJECT_INSTALL  = 6;
        public static int TYPE_SALES  = 7;
        
        public static String[] strType = {
            "Incoming Goods", "Retur PO", "Transfer Out", "Transfer In", "Adjustment", "Opname", "Sales"
        };
        
        public static int STOCK_IN       = 1;
        public static int STOCK_OUT      = -1;        
        
	public DbStock(){
	}

	public DbStock(int i) throws CONException { 
		super(new DbStock()); 
	}

	public DbStock(String sOid) throws CONException { 
		super(new DbStock(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbStock(long lOid) throws CONException { 
		super(new DbStock(0)); 
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
		return DB_POS_STOCK;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbStock().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Stock stock = fetchExc(ent.getOID()); 
		ent = (Entity)stock; 
		return stock.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Stock) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Stock) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Stock fetchExc(long oid) throws CONException{ 
		try{ 
			Stock stock = new Stock();
			DbStock pstStock = new DbStock(oid); 
			stock.setOID(oid);

			stock.setLocationId(pstStock.getlong(COL_LOCATION_ID));
			stock.setType(pstStock.getInt(COL_TYPE));
			stock.setQty(pstStock.getdouble(COL_QTY));
			stock.setPrice(pstStock.getdouble(COL_PRICE));
			stock.setTotal(pstStock.getdouble(COL_TOTAL));
			stock.setItemMasterId(pstStock.getlong(COL_ITEM_MASTER_ID));
			stock.setItemCode(pstStock.getString(COL_ITEM_CODE));
			stock.setItemBarcode(pstStock.getString(COL_ITEM_BARCODE));
			stock.setItemName(pstStock.getString(COL_ITEM_NAME));
			stock.setUnitId(pstStock.getlong(COL_UNIT_ID));
			stock.setUnit(pstStock.getString(COL_UNIT));
			stock.setInOut(pstStock.getInt(COL_IN_OUT));
			stock.setDate(pstStock.getDate(COL_DATE));
			stock.setUserId(pstStock.getlong(COL_USER_ID));
			stock.setNoFaktur(pstStock.getString(COL_NO_FAKTUR));
			stock.setIncomingId(pstStock.getlong(COL_INCOMING_ID));
			stock.setReturId(pstStock.getlong(COL_RETUR_ID));
			stock.setTransferId(pstStock.getlong(COL_TRANSFER_ID));
			stock.setTransferInId(pstStock.getlong(COL_TRANSFER_IN_ID));
			stock.setAdjustmentId(pstStock.getlong(COL_ADJUSTMENT_ID));
                        stock.setOpnameId(pstStock.getlong(COL_OPNAME_ID));
                        stock.setSalesDetailId(pstStock.getlong(COL_SALES_DETAIL_ID));

			return stock; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbStock(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Stock stock) throws CONException{ 
		try{ 
			DbStock pstStock = new DbStock(0);

			pstStock.setLong(COL_LOCATION_ID, stock.getLocationId());
			pstStock.setInt(COL_TYPE, stock.getType());
			pstStock.setDouble(COL_QTY, stock.getQty());
			pstStock.setDouble(COL_PRICE, stock.getPrice());
			pstStock.setDouble(COL_TOTAL, stock.getTotal());
			pstStock.setLong(COL_ITEM_MASTER_ID, stock.getItemMasterId());
			pstStock.setString(COL_ITEM_CODE, stock.getItemCode());
			pstStock.setString(COL_ITEM_BARCODE, stock.getItemBarcode());
			pstStock.setString(COL_ITEM_NAME, stock.getItemName());
			pstStock.setLong(COL_UNIT_ID, stock.getUnitId());
			pstStock.setString(COL_UNIT, stock.getUnit());
			pstStock.setInt(COL_IN_OUT, stock.getInOut());
			pstStock.setDate(COL_DATE, stock.getDate());
			pstStock.setLong(COL_USER_ID, stock.getUserId());
			pstStock.setString(COL_NO_FAKTUR, stock.getNoFaktur());
			pstStock.setLong(COL_INCOMING_ID, stock.getIncomingId());
			pstStock.setLong(COL_RETUR_ID, stock.getReturId());
			pstStock.setLong(COL_TRANSFER_ID, stock.getTransferId());
			pstStock.setLong(COL_TRANSFER_IN_ID, stock.getTransferInId());
			pstStock.setLong(COL_ADJUSTMENT_ID, stock.getAdjustmentId());
                        pstStock.setLong(COL_OPNAME_ID, stock.getOpnameId());
                        pstStock.setLong(COL_SALES_DETAIL_ID, stock.getSalesDetailId());

			pstStock.insert(); 
			stock.setOID(pstStock.getlong(COL_STOCK_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbStock(0),CONException.UNKNOWN); 
		}
		return stock.getOID();
	}

	public static long updateExc(Stock stock) throws CONException{ 
		try{ 
			if(stock.getOID() != 0){ 
				DbStock pstStock = new DbStock(stock.getOID());

				pstStock.setLong(COL_LOCATION_ID, stock.getLocationId());
				pstStock.setInt(COL_TYPE, stock.getType());
				pstStock.setDouble(COL_QTY, stock.getQty());
				pstStock.setDouble(COL_PRICE, stock.getPrice());
				pstStock.setDouble(COL_TOTAL, stock.getTotal());
				pstStock.setLong(COL_ITEM_MASTER_ID, stock.getItemMasterId());
				pstStock.setString(COL_ITEM_CODE, stock.getItemCode());
				pstStock.setString(COL_ITEM_BARCODE, stock.getItemBarcode());
				pstStock.setString(COL_ITEM_NAME, stock.getItemName());
				pstStock.setLong(COL_UNIT_ID, stock.getUnitId());
				pstStock.setString(COL_UNIT, stock.getUnit());
				pstStock.setInt(COL_IN_OUT, stock.getInOut());
				pstStock.setDate(COL_DATE, stock.getDate());
				pstStock.setLong(COL_USER_ID, stock.getUserId());
				pstStock.setString(COL_NO_FAKTUR, stock.getNoFaktur());
				pstStock.setLong(COL_INCOMING_ID, stock.getIncomingId());
				pstStock.setLong(COL_RETUR_ID, stock.getReturId());
				pstStock.setLong(COL_TRANSFER_ID, stock.getTransferId());
				pstStock.setLong(COL_TRANSFER_IN_ID, stock.getTransferInId());
				pstStock.setLong(COL_ADJUSTMENT_ID, stock.getAdjustmentId());
                                pstStock.setLong(COL_OPNAME_ID, stock.getOpnameId());
                                pstStock.setLong(COL_SALES_DETAIL_ID, stock.getSalesDetailId());

				pstStock.update(); 
				return stock.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbStock(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbStock pstStock = new DbStock(oid);
			pstStock.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbStock(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_POS_STOCK; 
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
				Stock stock = new Stock();
				resultToObject(rs, stock);
				lists.add(stock);
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

	public static void resultToObject(ResultSet rs, Stock stock){
		try{
			stock.setOID(rs.getLong(DbStock.colNames[DbStock.COL_STOCK_ID]));
			stock.setLocationId(rs.getLong(DbStock.colNames[DbStock.COL_LOCATION_ID]));
			stock.setType(rs.getInt(DbStock.colNames[DbStock.COL_TYPE]));
			stock.setQty(rs.getDouble(DbStock.colNames[DbStock.COL_QTY]));
			stock.setPrice(rs.getDouble(DbStock.colNames[DbStock.COL_PRICE]));
			stock.setTotal(rs.getDouble(DbStock.colNames[DbStock.COL_TOTAL]));
			stock.setItemMasterId(rs.getLong(DbStock.colNames[DbStock.COL_ITEM_MASTER_ID]));
			stock.setItemCode(rs.getString(DbStock.colNames[DbStock.COL_ITEM_CODE]));
			stock.setItemBarcode(rs.getString(DbStock.colNames[DbStock.COL_ITEM_BARCODE]));
			stock.setItemName(rs.getString(DbStock.colNames[DbStock.COL_ITEM_NAME]));
			stock.setUnitId(rs.getLong(DbStock.colNames[DbStock.COL_UNIT_ID]));
			stock.setUnit(rs.getString(DbStock.colNames[DbStock.COL_UNIT]));
			stock.setInOut(rs.getInt(DbStock.colNames[DbStock.COL_IN_OUT]));
			stock.setDate(rs.getDate(DbStock.colNames[DbStock.COL_DATE]));
			stock.setUserId(rs.getLong(DbStock.colNames[DbStock.COL_USER_ID]));
			stock.setNoFaktur(rs.getString(DbStock.colNames[DbStock.COL_NO_FAKTUR]));
			stock.setIncomingId(rs.getLong(DbStock.colNames[DbStock.COL_INCOMING_ID]));
			stock.setReturId(rs.getLong(DbStock.colNames[DbStock.COL_RETUR_ID]));
			stock.setTransferId(rs.getLong(DbStock.colNames[DbStock.COL_TRANSFER_ID]));
			stock.setTransferInId(rs.getLong(DbStock.colNames[DbStock.COL_TRANSFER_IN_ID]));
			stock.setAdjustmentId(rs.getLong(DbStock.colNames[DbStock.COL_ADJUSTMENT_ID]));
                        stock.setOpnameId(rs.getLong(DbStock.colNames[DbStock.COL_OPNAME_ID]));
                        stock.setSalesDetailId(rs.getLong(DbStock.colNames[DbStock.COL_SALES_DETAIL_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long stockId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_POS_STOCK + " WHERE " + 
						DbStock.colNames[DbStock.COL_STOCK_ID] + " = " + stockId;

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
			String sql = "SELECT COUNT("+ DbStock.colNames[DbStock.COL_STOCK_ID] + ") FROM " + DB_POS_STOCK;
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
			  	   Stock stock = (Stock)list.get(ls);
				   if(oid == stock.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static void insertReceiveGoods(Receive rec, ReceiveItem ri){
            
            try{
                
                System.out.println("inserting new stock ...");
                
                ItemMaster im = new ItemMaster();
                im = DbItemMaster.fetchExc(ri.getItemMasterId());
                
                if(im.getNeedRecipe()==0){
                    Uom uom = DbUom.fetchExc(ri.getUomId());

                    Stock stock = new Stock();
                    stock.setIncomingId(ri.getReceiveId());
                    stock.setInOut(STOCK_IN);
                    stock.setItemBarcode(im.getBarcode());
                    stock.setItemCode(im.getCode());
                    stock.setItemMasterId(im.getOID());
                    stock.setItemName(im.getName());
                    stock.setLocationId(rec.getLocationId());
                    stock.setDate(rec.getDate());
                    stock.setNoFaktur(rec.getDoNumber());
                    stock.setPrice(ri.getTotalAmount()/ri.getQty());
                    stock.setTotal(ri.getTotalAmount());
                    stock.setQty(ri.getQty());
                    stock.setType(TYPE_INCOMING_GOODS);
                    stock.setUnitId(ri.getUomId());
                    stock.setUnit(uom.getUnit());
                    stock.setUserId(rec.getUserId());

                    DbStock.insertExc(stock);

                    System.out.println("inserting new stock ... done ");
                }
            }
            catch(Exception e){
                
            }
            
        }
        
        
        public static void insertTransferGoods(Transfer rec, TransferItem ri){
            
            ItemMaster im = new ItemMaster();
            Uom uom = new Uom();
            
            //---- keluarkan stock --------
            
            try{
                
                System.out.println("inserting new stock ...");
                
                im = DbItemMaster.fetchExc(ri.getItemMasterId());
                uom = DbUom.fetchExc(im.getUomStockId());
                
                Stock stock = new Stock();
                stock.setTransferId(ri.getTransferId());
                stock.setInOut(STOCK_OUT);
                stock.setItemBarcode(im.getBarcode());
                stock.setItemCode(im.getCode());
                stock.setItemMasterId(im.getOID());
                stock.setItemName(im.getName());
                stock.setLocationId(rec.getFromLocationId());
                stock.setDate(rec.getDate());
                stock.setNoFaktur(rec.getNumber());
                
                //harga ambil harga average terakhir
                stock.setPrice(im.getCogs());
                stock.setTotal(im.getCogs()*ri.getQty());
                
                stock.setQty(ri.getQty());
                stock.setType(TYPE_TRANSFER);
                stock.setUnitId(im.getUomStockId());
                stock.setUnit(uom.getUnit());
                stock.setUserId(rec.getUserId());
                
                DbStock.insertExc(stock);
                
                System.out.println("inserting new stock ... done ");
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
            //---------- masukkan stock ----------------
            
            try{
                
                System.out.println("inserting new stock ...");
                
                Stock stock = new Stock();
                stock.setTransferId(ri.getTransferId());
                stock.setInOut(STOCK_IN);
                stock.setItemBarcode(im.getBarcode());
                stock.setItemCode(im.getCode());
                stock.setItemMasterId(im.getOID());
                stock.setItemName(im.getName());
                stock.setLocationId(rec.getToLocationId());
                stock.setDate(rec.getDate());
                stock.setNoFaktur(rec.getNumber());
                
                //harga ambil harga average terakhir
                stock.setPrice(im.getCogs());
                stock.setTotal(im.getCogs()*ri.getQty());
                
                stock.setQty(ri.getQty());
                stock.setType(TYPE_TRANSFER);
                stock.setUnitId(im.getUomStockId());
                stock.setUnit(uom.getUnit());
                stock.setUserId(rec.getUserId());
                
                DbStock.insertExc(stock);
                
                System.out.println("inserting new stock ... done ");
            }
            catch(Exception e){
                
                System.out.println(e.toString());
            }
            
        }
        
        
        public static void insertReturGoods(Retur ret, ReturItem ri){
            
            try{
                
                System.out.println("inserting new retur stock ...");
                
                ItemMaster im = new ItemMaster();
                im = DbItemMaster.fetchExc(ri.getItemMasterId());
                
                //jika stockable
                if(im.getNeedRecipe()==0){
                    Uom uom = DbUom.fetchExc(ri.getUomId());

                    Stock stock = new Stock();
                    stock.setReturId(ret.getOID());
                    stock.setInOut(STOCK_OUT);
                    stock.setItemBarcode(im.getBarcode());
                    stock.setItemCode(im.getCode());
                    stock.setItemMasterId(im.getOID());
                    stock.setItemName(im.getName());
                    stock.setLocationId(ret.getLocationId());
                    stock.setDate(ret.getDate());
                    stock.setNoFaktur(ret.getDoNumber());
                    stock.setPrice(ri.getTotalAmount()/ri.getQty());
                    stock.setTotal(ri.getTotalAmount());
                    stock.setQty(ri.getQty());
                    stock.setType(TYPE_RETUR_GOODS);
                    stock.setUnitId(ri.getUomId());
                    stock.setUnit(uom.getUnit());
                    stock.setUserId(ret.getUserId());

                    DbStock.insertExc(stock);

                    System.out.println("inserting new stock ... done ");
                }
            }
            catch(Exception e){
                
            }
            
        }
        
        public static void insertProjectInstall(long itemId, long installLocId, long userId, Date installDate, double qty){
            
            try{
                
                System.out.println("inserting new insertProjectInstall stock ...");
                
                ItemMaster im = new ItemMaster();
                im = DbItemMaster.fetchExc(itemId);
                
                //jika bukan jasa
                if(im.getNeedRecipe()==0){
                    Uom uom = DbUom.fetchExc(im.getUomStockId());

                    Stock stock = new Stock();
                    //stock.setReturId(ret.getOID());
                    stock.setInOut(STOCK_OUT);
                    stock.setItemBarcode(im.getBarcode());
                    stock.setItemCode(im.getCode());
                    stock.setItemMasterId(im.getOID());
                    stock.setItemName(im.getName());
                    stock.setLocationId(installLocId);
                    stock.setDate(installDate);
                    stock.setNoFaktur("[auto] prj install");
                    stock.setPrice(im.getCogs());
                    stock.setTotal(im.getCogs()*qty);
                    stock.setQty(qty);
                    stock.setType(TYPE_PROJECT_INSTALL);
                    stock.setUnitId(im.getUomStockId());
                    stock.setUnit(uom.getUnit());
                    stock.setUserId(userId);

                    DbStock.insertExc(stock);

                    System.out.println("inserting new stock for install product out... done ");
                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
        }
        
        public static void insertProjectInstallRevised(long itemId, long installLocId, long userId, Date installDate, double qty){
            
            try{
                
                System.out.println("inserting new insertProjectInstallRevised stock ...");
                
                ItemMaster im = new ItemMaster();
                im = DbItemMaster.fetchExc(itemId);
                //jika bukan jasa
                if(im.getNeedRecipe()==0){
                    Uom uom = DbUom.fetchExc(im.getUomStockId());

                    Stock stock = new Stock();
                    //stock.setReturId(ret.getOID());
                    stock.setInOut(STOCK_IN);
                    stock.setItemBarcode(im.getBarcode());
                    stock.setItemCode(im.getCode());
                    stock.setItemMasterId(im.getOID());
                    stock.setItemName(im.getName());
                    stock.setLocationId(installLocId);
                    stock.setDate(installDate);
                    stock.setNoFaktur("[auto] prj install - rev");
                    stock.setPrice(im.getCogs());
                    stock.setTotal(im.getCogs()*qty);
                    stock.setQty(qty);
                    stock.setType(TYPE_PROJECT_INSTALL);
                    stock.setUnitId(im.getUomStockId());
                    stock.setUnit(uom.getUnit());
                    stock.setUserId(userId);

                    DbStock.insertExc(stock);

                    System.out.println("inserting new stock for install product out... done ");
                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            
        }
        
        
        public static void insertAdjustmentGoods(Adjusment ret, AdjusmentItem ri){
            
            try{
                
                System.out.println("inserting new retur stock ...");
                
                ItemMaster im = new ItemMaster();
                im = DbItemMaster.fetchExc(ri.getItemMasterId());
                Uom uom = DbUom.fetchExc(im.getUomStockId());
                
                Stock stock = new Stock();
                stock.setAdjustmentId(ret.getOID());
                double adj = ri.getQtyReal() - ri.getQtySystem();
                if(adj<0){
                    stock.setInOut(STOCK_OUT);
                    adj = adj * -1;
                }
                else{
                    stock.setInOut(STOCK_IN);
                }
                stock.setItemBarcode(im.getBarcode());
                stock.setItemCode(im.getCode());
                stock.setItemMasterId(im.getOID());
                stock.setItemName(im.getName());
                stock.setLocationId(ret.getLocationId());
                stock.setDate(ret.getDate());
                stock.setNoFaktur(ret.getNumber());
                stock.setPrice(im.getCogs());
                stock.setTotal(im.getCogs() * adj);
                stock.setQty(adj);
                stock.setType(TYPE_ADJUSTMENT);
                stock.setUnitId(im.getUomStockId());
                stock.setUnit(uom.getUnit());
                stock.setUserId(ret.getUserId());
                
                DbStock.insertExc(stock);
                
                System.out.println("inserting new stock ... done ");
            }
            catch(Exception e){
                
            }
            
        }
        
        public static void insertOpnameGoods(Opname ret, OpnameItem ri){
            
            try{
                
                System.out.println("inserting new opname stock ...");
                
                //jika ada selisih lakukan proses stock
                double adj = ri.getQtyReal() - ri.getQtySystem();
                
                if(adj!=0){
                    
                    ItemMaster im = new ItemMaster();
                    im = DbItemMaster.fetchExc(ri.getItemMasterId());
                    Uom uom = DbUom.fetchExc(im.getUomStockId());

                    Stock stock = new Stock();
                    stock.setOpnameId(ret.getOID());

                    if(adj<0){
                        stock.setInOut(STOCK_OUT);
                        adj = adj * -1;
                    }
                    else{
                        stock.setInOut(STOCK_IN);
                    }
                    stock.setItemBarcode(im.getBarcode());
                    stock.setItemCode(im.getCode());
                    stock.setItemMasterId(im.getOID());
                    stock.setItemName(im.getName());
                    stock.setLocationId(ret.getLocationId());
                    stock.setDate(ret.getDate());
                    stock.setNoFaktur(ret.getNumber());
                    stock.setPrice(im.getCogs());
                    stock.setTotal(im.getCogs() * adj);
                    stock.setQty(adj);
                    stock.setType(TYPE_OPNAME);
                    stock.setUnitId(im.getUomStockId());
                    stock.setUnit(uom.getUnit());
                    stock.setUserId(ret.getUserId());

                    DbStock.insertExc(stock);
                }
                
                System.out.println("inserting new stock ... done ");
            }
            catch(Exception e){
                
            }
            
        }
        
        //commandType = 0, insert new
        public static void insertSalesItem(Sales ret, SalesDetail ri){
            
            try{
                
                    System.out.println("\n\n\n=============== inserting new sales item ========================\n");
                    System.out.println("\n\n\n=================================================================\n");
                
                    ItemMaster im = new ItemMaster();
                    im = DbItemMaster.fetchExc(ri.getProductMasterId());
                    
                    //jika stockable ---------0=barang, 1=jasa-------
                    if(im.getNeedRecipe()==0){
                        
                        Uom uom = DbUom.fetchExc(im.getUomStockId());

                        Stock stock = new Stock();
                        stock.setOpnameId(ret.getOID());

                        stock.setInOut(STOCK_OUT);

                        long locId = Long.parseLong(DbSystemProperty.getValueByName("SALES_LOCATION_ID"));

                        stock.setSalesDetailId(ri.getOID());
                        stock.setItemBarcode(im.getBarcode());
                        stock.setItemCode(im.getCode());
                        stock.setItemMasterId(im.getOID());
                        stock.setItemName(im.getName());
                        stock.setLocationId(locId);
                        stock.setDate(ret.getDate());
                        stock.setNoFaktur(ret.getNumber());
                        stock.setPrice(im.getCogs());
                        stock.setTotal(im.getCogs() * ri.getQty());
                        stock.setQty(ri.getQty());
                        stock.setType(TYPE_SALES);
                        stock.setUnitId(im.getUomStockId());
                        stock.setUnit(uom.getUnit());
                        stock.setUserId(ret.getUserId());

                        DbStock.insertExc(stock);


                        System.out.println("inserting new stock ... done ");
                    }
                    
            }
            catch(Exception e){
                
            }
            
        }
        
        public static Vector getStock(long locationId){
            Vector result = new Vector();
            
            CONResultSet crs = null;
            try{
                String sql = "select s."+colNames[COL_ITEM_MASTER_ID]+", s."+colNames[COL_ITEM_CODE]+", s."+
                    colNames[COL_ITEM_NAME]+", s."+colNames[COL_LOCATION_ID]+", "+
                    " sum(s."+colNames[COL_QTY]+" * s."+colNames[COL_IN_OUT]+") as qty, s."+colNames[COL_UNIT]+ 
                    " from "+DB_POS_STOCK+" s "+
                    " inner join "+DbItemMaster.DB_ITEM_MASTER+" m on "+
                    " m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+
                    " =s."+colNames[COL_ITEM_MASTER_ID]+
                    " where s."+colNames[COL_LOCATION_ID]+"="+locationId+
                    " group by s."+colNames[COL_ITEM_MASTER_ID]+
                    " order by " +
                    " m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]+
                    ", m."+DbItemMaster.colNames[DbItemMaster.COL_NAME];
                    
                
                
                System.out.println("\n\n----- getting stock ----- \n"+sql+"\n------------------------\n\n");
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Stock s = new Stock();
                    s.setItemMasterId(rs.getLong(1));
                    s.setItemCode(rs.getString(2));
                    s.setItemName(rs.getString(3));
                    s.setLocationId(rs.getLong(4));
                    s.setQty(rs.getDouble(5));
                    s.setUnit(rs.getString(6));
                    //DbStock.resultToObject(rs, s);
                    result.add(s);
                }
                
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;            
        }
        
        public static Vector getStockList(int start, int recordToGet, String code, String name, long groupid, long categoryid){
            
            String sql = "select st."+colNames[COL_ITEM_MASTER_ID]+", st."+colNames[COL_ITEM_CODE]+
                " , st."+colNames[COL_ITEM_NAME]+", sum(st."+colNames[COL_QTY]+" * st."+colNames[COL_IN_OUT]+") as qty "+
                " from "+DB_POS_STOCK+" st "+
                " inner join "+DbItemMaster.DB_ITEM_MASTER+" m "+
                " on m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+" = st."+colNames[COL_ITEM_MASTER_ID]+
                " where st."+colNames[COL_QTY]+">0";
                
                if(code!=null && code.length()>0){
                    sql = sql + " and st."+colNames[COL_ITEM_CODE]+" like '%"+code+"%'"; 
                }
                if(name!=null && name.length()>0){
                    sql = sql + " and st."+colNames[COL_ITEM_NAME]+" like '%"+name+"%'"; 
                }
                if(groupid!=0){
                    sql = sql + " and m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]+"="+groupid;
                }
                if(categoryid!=0){
                    sql = sql + " and m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_CATEGORY_ID]+"="+categoryid;
                }
              
                sql = sql + " group by st."+colNames[COL_ITEM_MASTER_ID];
                
                if(recordToGet!=0){
                    sql = sql + " limit "+start+","+recordToGet;
                }
                
                System.out.println("\n=================\n"+sql);
                
            CONResultSet crs = null;
            Vector result = new Vector();            
            
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Stock s = new Stock();
                    s.setItemMasterId(rs.getLong(1));
                    s.setItemCode(rs.getString(2));
                    s.setItemName(rs.getString(3));
                    s.setQty(rs.getDouble(4));
                    result.add(s);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
                
        }
        
        public static double getItemTotalStock(long itemOID){
            
            String sql = " select sum("+colNames[COL_QTY]+" * "+colNames[COL_IN_OUT]+") "+
                         " from "+DB_POS_STOCK+" where "+colNames[COL_ITEM_MASTER_ID]+"= "+itemOID;
            
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
                System.out.println(e.toString());    
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
        
        public static Vector getAssetList(){
            
            Vector result = new Vector();
            
            String sql = "select g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+" as group_id, " +
                "g."+DbItemGroup.colNames[DbItemGroup.COL_NAME]+" as group_name, " +
                "im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+" as item_id, " +
                "im."+DbItemMaster.colNames[DbItemMaster.COL_CODE]+" as item_code, " +
                "im."+DbItemMaster.colNames[DbItemMaster.COL_NAME]+" as item_name, " +
                "im."+DbItemMaster.colNames[DbItemMaster.COL_SELLING_PRICE]+" as item_price, " +
                "im."+DbItemMaster.colNames[DbItemMaster.COL_COGS]+" as item_cost, " +
                "l."+DbLocation.colNames[DbLocation.COL_LOCATION_ID]+" as loc_id, " +
                "l."+DbLocation.colNames[DbLocation.COL_NAME]+" as loc_name, " +
                "s."+DbStock.colNames[DbStock.COL_STOCK_ID]+" as stock_id, " +
                "s."+DbStock.colNames[DbStock.COL_DATE]+" as stock_date, " +
                "sum(s."+colNames[COL_QTY]+" * s."+colNames[COL_IN_OUT]+") as qtya "+
                " from "+DB_POS_STOCK+" s "+
                " inner join "+DbItemMaster.DB_ITEM_MASTER+" im "+
                " on im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+" = s."+colNames[COL_ITEM_MASTER_ID]+
                " inner join "+DbItemGroup.DB_ITEM_GROUP+" g "+
                " on g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+" = im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]+
                " inner join "+DbLocation.DB_LOCATION+" l "+
                " on l."+DbLocation.colNames[DbLocation.COL_LOCATION_ID]+"= s."+colNames[COL_LOCATION_ID]+
                " where g."+DbItemGroup.colNames[DbItemGroup.COL_TYPE]+"="+I_Ccs.TYPE_CATEGORY_ASSET+
                " group by g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+", s."+colNames[COL_LOCATION_ID]+", s."+colNames[COL_ITEM_MASTER_ID]+
                " order by g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+", s."+colNames[COL_LOCATION_ID];
            
            System.out.println("\n\nget asset sql : "+sql);
            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    ItemGroup ig = new ItemGroup();
                    ig.setOID(rs.getLong("group_id"));
                    ig.setName(rs.getString("group_name"));
                    //DbItemGroup.resultToObject(rs, ig);
                    ItemMaster im = new ItemMaster();
                    im.setOID(rs.getLong("item_id"));
                    im.setCode(rs.getString("item_code"));
                    im.setName(rs.getString("item_name"));
                    im.setSellingPrice(rs.getDouble("item_price"));
                    im.setCogs(rs.getDouble("item_cost"));
                    //DbItemMaster.resultToObject(rs, im);
                    Location loc = new Location();
                    loc.setOID(rs.getLong("loc_id"));
                    loc.setName(rs.getString("loc_name"));
                    //DbLocation.resultToObject(rs, loc);
                    Stock s = new Stock();
                    s.setOID(rs.getLong("stock_id"));
                    s.setQty(rs.getDouble("qtya"));
                    s.setDate(rs.getDate("stock_date"));
                    
                    if(s.getQty()>0){
                    
                        Vector temp = new Vector();
                        temp.add(ig);
                        temp.add(im);
                        temp.add(loc);
                        temp.add(s);

                        result.add(temp);
                    }
                    
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
        
        public static Vector getAssetListDepreciation(long oidPeriode){
            
            Vector result = new Vector();
            
            String sql = "select g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+" as group_id, " +
                " g."+DbItemGroup.colNames[DbItemGroup.COL_NAME]+" as group_name, " +
                " im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+" as item_id, " +
                " im."+DbItemMaster.colNames[DbItemMaster.COL_CODE]+" as item_code, " +
                " im."+DbItemMaster.colNames[DbItemMaster.COL_NAME]+" as item_name, " +
                " im."+DbItemMaster.colNames[DbItemMaster.COL_SELLING_PRICE]+" as item_price, " +
                " im."+DbItemMaster.colNames[DbItemMaster.COL_COGS]+" as item_cost, " +
                " l."+DbLocation.colNames[DbLocation.COL_LOCATION_ID]+" as loc_id, " +
                " l."+DbLocation.colNames[DbLocation.COL_NAME]+" as loc_name, " +
                " s."+DbStock.colNames[DbStock.COL_STOCK_ID]+" as stock_id, " +
                " s."+DbStock.colNames[DbStock.COL_DATE]+" as stock_date, " +
                " sum(s."+colNames[COL_QTY]+" * s."+colNames[COL_IN_OUT]+") as qtya "+
                " from "+DbAssetDataDepresiasi.DB_ASSET_DATA_DEPRESIASI+" ass "+
                " inner join "+DbAssetData.DB_ASSET_DATA+" ad "+
                " on ass."+DbAssetDataDepresiasi.colNames[DbAssetDataDepresiasi.COL_ASSET_DATA_ID]+
                " = ad."+DbAssetData.colNames[DbAssetData.COL_ASSET_DATA_ID]+                                
                " inner join "+DbItemMaster.DB_ITEM_MASTER+" im "+
                " on im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+" = ad."+colNames[COL_ITEM_MASTER_ID]+
                " inner join "+DbStock.DB_POS_STOCK+" s "+
                " on im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+"= s."+DbStock.colNames[DbStock.COL_ITEM_MASTER_ID]+
                " inner join "+DbItemGroup.DB_ITEM_GROUP+" g "+
                " on g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+" = im."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]+
                " inner join "+DbLocation.DB_LOCATION+" l "+
                " on l."+DbLocation.colNames[DbLocation.COL_LOCATION_ID]+"= s."+colNames[COL_LOCATION_ID]+
                " where g."+DbItemGroup.colNames[DbItemGroup.COL_TYPE]+"="+I_Ccs.TYPE_CATEGORY_ASSET+
                " and ass."+DbAssetDataDepresiasi.colNames[DbAssetDataDepresiasi.COL_PERIODE_ID]+"="+oidPeriode+
                " group by g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+", s."+colNames[COL_LOCATION_ID]+", s."+colNames[COL_ITEM_MASTER_ID]+
                " order by g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+", s."+colNames[COL_LOCATION_ID];
            
            System.out.println("\n\nget asset sql : "+sql);
            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    ItemGroup ig = new ItemGroup();
                    ig.setOID(rs.getLong("group_id"));
                    ig.setName(rs.getString("group_name"));
                    //DbItemGroup.resultToObject(rs, ig);
                    ItemMaster im = new ItemMaster();
                    im.setOID(rs.getLong("item_id"));
                    im.setCode(rs.getString("item_code"));
                    im.setName(rs.getString("item_name"));
                    im.setSellingPrice(rs.getDouble("item_price"));
                    im.setCogs(rs.getDouble("item_cost"));
                    //DbItemMaster.resultToObject(rs, im);
                    Location loc = new Location();
                    loc.setOID(rs.getLong("loc_id"));
                    loc.setName(rs.getString("loc_name"));
                    //DbLocation.resultToObject(rs, loc);
                    Stock s = new Stock();
                    s.setOID(rs.getLong("stock_id"));
                    s.setQty(rs.getDouble("qtya"));
                    s.setDate(rs.getDate("stock_date"));
                    
                    if(s.getQty()>0){
                    
                        Vector temp = new Vector();
                        temp.add(ig);
                        temp.add(im);
                        temp.add(loc);
                        temp.add(s);

                        result.add(temp);
                    }
                    
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
