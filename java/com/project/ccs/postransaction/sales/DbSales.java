/***********************************\
|  Create by Dek-Ndut               |
|  Karya kami mohon jangan dibajak  |
|                                   |
|  9/29/2008 3:16:36 PM
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
import com.project.util.*;
import com.project.util.lang.I_Language;
import com.project.*;
import com.project.system.*;
import com.project.general.Currency;
import com.project.ccs.posmaster.*;
import com.project.fms.transaction.*;
import com.project.fms.ar.*;
import com.project.fms.master.*;
import com.project.general.DbCompany;
import com.project.general.Company;
import com.project.ccs.postransaction.stock.*;

public class DbSales extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {

	public static final  String DB_SALES = "pos_sales";

	public static final  int COL_SALES_ID = 0;
	public static final  int COL_DATE = 1;
	public static final  int COL_NUMBER = 2;
	public static final  int COL_NUMBER_PREFIX = 3;
	public static final  int COL_COUNTER = 4;
	public static final  int COL_NAME = 5;
	public static final  int COL_CUSTOMER_ID = 6;
	public static final  int COL_CUSTOMER_PIC = 7;
	public static final  int COL_CUSTOMER_PIC_PHONE = 8;
	public static final  int COL_CUSTOMER_ADDRESS = 9;
	public static final  int COL_START_DATE = 10;
	public static final  int COL_END_DATE = 11;
	public static final  int COL_CUSTOMER_PIC_POSITION = 12;
	public static final  int COL_EMPLOYEE_ID = 13;
	public static final  int COL_USER_ID = 14;
	public static final  int COL_EMPLOYEE_HP = 15;
	public static final  int COL_DESCRIPTION = 16;
	public static final  int COL_STATUS = 17;
	public static final  int COL_AMOUNT = 18;
	public static final  int COL_CURRENCY_ID = 19;
	public static final  int COL_COMPANY_ID = 20;
	public static final  int COL_CATEGORY_ID = 21;

        public static final  int COL_DISCOUNT_PERCENT = 22;
        public static final  int COL_DISCOUNT_AMOUNT = 23;
        public static final  int COL_VAT = 24;
        public static final  int COL_DISCOUNT = 25;

        public static final  int COL_WARRANTY_STATUS = 26;
        public static final  int COL_WARRANTY_DATE = 27;
        public static final  int COL_WARRANTY_RECEIVE = 28;
        public static final  int COL_MANUAL_STATUS = 29;
        public static final  int COL_MANUAL_DATE = 30;
        public static final  int COL_MANUAL_RECEIVE = 31;
        public static final  int COL_NOTE_CLOSING = 32;

        public static final  int COL_BOOKING_RATE = 33;
        public static final  int COL_EXCHANGE_AMOUNT = 34;
        public static final  int COL_PROPOSAL_ID = 35;
        
        public static final  int COL_UNIT_USAHA_ID = 36;
        public static final  int COL_ITEM_GROUP_ID = 37;
        public static final  int COL_VAT_PERCENT = 38;
        public static final  int COL_VAT_AMOUNT = 39;
        public static final  int COL_TYPE = 40;
        
        public static final  int COL_PPH_PERCENT = 41;
        public static final  int COL_PPH_AMOUNT = 42;
        public static final  int COL_PPH_TYPE = 43;
        
	public static final  String[] colNames = {
		"sales_id",
		"date",
		"number",
		"number_prefix",
		"counter",
		"name",
		"customer_id",
		"customer_pic",
		"customer_pic_phone",
		"customer_address",
		"start_date",
		"end_date",
		"customer_pic_position",
		"employee_id",
		"user_id",
		"employee_hp",
		"description",
		"status",
		"amount",
		"currency_id",
		"company_id",
		"category_id",
                
                "discount_percent",
                "discount_amount",
                "vat",
                "discount",
                
                "warranty_status",
                "warranty_date",
                "warranty_receive",
                "manual_status",
                "manual_date",
                "manual_receive",
                "note_closing",
                
                "booking_rate",
                "exchange_amount",
                "proposal_id",
                
                "unit_usaha_id",
                "item_group_id",
                "vat_percent",
                "vat_amount",
                
                "type",
                
                "pph_percent",
                "pph_amount",
                "pph_type"
	};

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_DATE,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_INT,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_DATE,
		TYPE_DATE,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_INT,
		TYPE_FLOAT,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
                
                TYPE_FLOAT,
                TYPE_FLOAT,
                TYPE_FLOAT,
                TYPE_INT,
                
                TYPE_INT,
                TYPE_DATE,
                TYPE_STRING,
                TYPE_INT,
                TYPE_DATE,
                TYPE_STRING,
                TYPE_STRING,
                
                TYPE_FLOAT,
                TYPE_FLOAT,
                TYPE_LONG,
                
                TYPE_LONG,
                TYPE_LONG,
                TYPE_FLOAT,
                TYPE_FLOAT,
                
                TYPE_INT,
                
                TYPE_FLOAT,
                TYPE_FLOAT,
                TYPE_INT
	};
        
        
        public static final int TYPE_JASA = 0;
        public static final int TYPE_MATERIAL = 1;
        
        
        public static final int TYPE_CASH = 0;
        public static final int TYPE_CREDIT = 1;
        
        public static final String[] typeStr = {"CASH", "CREDIT"};
             
	public DbSales(){
	}

	public DbSales(int i) throws CONException {
		super(new DbSales());
	}

	public DbSales(String sOid) throws CONException {
		super(new DbSales(0));
		if(!locate(sOid))
			throw new CONException(this,CONException.RECORD_NOT_FOUND);
		else
			return;
	}

	public DbSales(long lOid) throws CONException {
		super(new DbSales(0));
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
		return DB_SALES;
	}

	public String[] getFieldNames(){
		return colNames;
	}

	public int[] getFieldTypes(){
		return fieldTypes;
	}

	public String getPersistentName(){
		return new DbSales().getClass().getName();
	}

	public long fetchExc(Entity ent) throws Exception {
		Sales sales = fetchExc(ent.getOID());
		ent = (Entity)sales;
		return sales.getOID();
	}

	public long insertExc(Entity ent) throws Exception {
		return insertExc((Sales) ent);
	}

	public long updateExc(Entity ent) throws Exception {
		return updateExc((Sales) ent);
	}

	public long deleteExc(Entity ent) throws Exception {
		if(ent==null){
			throw new CONException(this,CONException.RECORD_NOT_FOUND);
		}
			return deleteExc(ent.getOID());
	}

	public static Sales fetchExc(long oid) throws CONException{
		try{
			Sales sales = new Sales();
			DbSales dbSales = new DbSales(oid);
			sales.setOID(oid);

			sales.setDate(dbSales.getDate(COL_DATE));
			sales.setNumber(dbSales.getString(COL_NUMBER));
			sales.setNumberPrefix(dbSales.getString(COL_NUMBER_PREFIX));
			sales.setCounter(dbSales.getInt(COL_COUNTER));
			sales.setName(dbSales.getString(COL_NAME));
			sales.setCustomerId(dbSales.getlong(COL_CUSTOMER_ID));
			sales.setCustomerPic(dbSales.getString(COL_CUSTOMER_PIC));
			sales.setCustomerPicPhone(dbSales.getString(COL_CUSTOMER_PIC_PHONE));
			sales.setCustomerAddress(dbSales.getString(COL_CUSTOMER_ADDRESS));
			//sales.setStartDate(dbSales.getDate(COL_START_DATE));
			//sales.setEndDate(dbSales.getDate(COL_END_DATE));
			sales.setCustomerPicPosition(dbSales.getString(COL_CUSTOMER_PIC_POSITION));
			sales.setEmployeeId(dbSales.getlong(COL_EMPLOYEE_ID));
			sales.setUserId(dbSales.getlong(COL_USER_ID));
			sales.setEmployeeHp(dbSales.getString(COL_EMPLOYEE_HP));
			sales.setDescription(dbSales.getString(COL_DESCRIPTION));
			sales.setStatus(dbSales.getInt(COL_STATUS));
			sales.setAmount(dbSales.getdouble(COL_AMOUNT));
			sales.setCurrencyId(dbSales.getlong(COL_CURRENCY_ID));
			sales.setCompanyId(dbSales.getlong(COL_COMPANY_ID));
			sales.setCategoryId(dbSales.getlong(COL_CATEGORY_ID));
                        
                        sales.setDiscountPercent(dbSales.getdouble(COL_DISCOUNT_PERCENT));
                        sales.setDiscountAmount(dbSales.getdouble(COL_DISCOUNT_AMOUNT));
                        sales.setVat(dbSales.getInt(COL_VAT));
                        sales.setVatPercent(dbSales.getdouble(COL_VAT_PERCENT));
                        sales.setVatAmount(dbSales.getdouble(COL_VAT_AMOUNT));
                        sales.setDiscount(dbSales.getInt(COL_DISCOUNT));
                        
                        sales.setWarrantyStatus(dbSales.getInt(COL_WARRANTY_STATUS));
                        sales.setWarrantyDate(dbSales.getDate(COL_WARRANTY_DATE));
                        sales.setWarrantyReceive(dbSales.getString(COL_WARRANTY_RECEIVE));
                        sales.setManualStatus(dbSales.getInt(COL_MANUAL_STATUS));
                        sales.setManualDate(dbSales.getDate(COL_MANUAL_DATE));
                        sales.setManualReceive(dbSales.getString(COL_MANUAL_RECEIVE));
                        sales.setNoteClosing(dbSales.getString(COL_NOTE_CLOSING));
                        
                        sales.setBookingRate(dbSales.getdouble(COL_BOOKING_RATE));
                        sales.setExchangeAmount(dbSales.getdouble(COL_EXCHANGE_AMOUNT));
                        sales.setProposalId(dbSales.getlong(COL_PROPOSAL_ID));
                        
                        sales.setUnitUsahaId(dbSales.getlong(COL_UNIT_USAHA_ID));
                        sales.setItemGroupId(dbSales.getlong(COL_ITEM_GROUP_ID));
                        sales.setType(dbSales.getInt(COL_TYPE));
                        
                        sales.setPphType(dbSales.getInt(COL_TYPE));
                        sales.setPphPercent(dbSales.getdouble(COL_PPH_PERCENT)); 
                        sales.setPphAmount(dbSales.getdouble(COL_PPH_AMOUNT));
 
			return sales;
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSales(0),CONException.UNKNOWN);
		}
	}

	public static long insertExc(Sales sales) throws CONException{
		try{
			DbSales dbSales = new DbSales(0);

			dbSales.setDate(COL_DATE, sales.getDate());
			dbSales.setString(COL_NUMBER, sales.getNumber());
			dbSales.setString(COL_NUMBER_PREFIX, sales.getNumberPrefix());
			dbSales.setInt(COL_COUNTER, sales.getCounter());
			dbSales.setString(COL_NAME, sales.getName());
			dbSales.setLong(COL_CUSTOMER_ID, sales.getCustomerId());
			dbSales.setString(COL_CUSTOMER_PIC, sales.getCustomerPic());
			dbSales.setString(COL_CUSTOMER_PIC_PHONE, sales.getCustomerPicPhone());
			dbSales.setString(COL_CUSTOMER_ADDRESS, sales.getCustomerAddress());
			dbSales.setDate(COL_START_DATE, sales.getStartDate());
			dbSales.setDate(COL_END_DATE, sales.getEndDate());
			dbSales.setString(COL_CUSTOMER_PIC_POSITION, sales.getCustomerPicPosition());
			dbSales.setLong(COL_EMPLOYEE_ID, sales.getEmployeeId());
			dbSales.setLong(COL_USER_ID, sales.getUserId());
			dbSales.setString(COL_EMPLOYEE_HP, sales.getEmployeeHp());
			dbSales.setString(COL_DESCRIPTION, sales.getDescription());
			dbSales.setInt(COL_STATUS, sales.getStatus());
			dbSales.setDouble(COL_AMOUNT, sales.getAmount());
			dbSales.setLong(COL_CURRENCY_ID, sales.getCurrencyId());
			dbSales.setLong(COL_COMPANY_ID, sales.getCompanyId());
			dbSales.setLong(COL_CATEGORY_ID, sales.getCategoryId());

                        dbSales.setDouble(COL_DISCOUNT_PERCENT, sales.getDiscountPercent());
                        dbSales.setDouble(COL_DISCOUNT_AMOUNT, sales.getDiscountAmount());
                        dbSales.setInt(COL_VAT, sales.getVat());
                        dbSales.setDouble(COL_VAT_PERCENT, sales.getVatPercent());
                        dbSales.setDouble(COL_VAT_AMOUNT, sales.getVatAmount());
                        dbSales.setInt(COL_DISCOUNT, sales.getDiscount());
                        
                        dbSales.setInt(COL_WARRANTY_STATUS, sales.getWarrantyStatus());
                        dbSales.setDate(COL_WARRANTY_DATE, sales.getWarrantyDate());
                        dbSales.setString(COL_WARRANTY_RECEIVE, sales.getWarrantyReceive());
                        dbSales.setInt(COL_MANUAL_STATUS, sales.getManualStatus());
                        dbSales.setDate(COL_MANUAL_DATE, sales.getManualDate());
                        dbSales.setString(COL_MANUAL_RECEIVE, sales.getManualReceive());
                        dbSales.setString(COL_NOTE_CLOSING, sales.getNoteClosing());
                        
                        dbSales.setDouble(COL_BOOKING_RATE, sales.getBookingRate());
                        dbSales.setDouble(COL_EXCHANGE_AMOUNT, sales.getExchangeAmount());
                        dbSales.setLong(COL_PROPOSAL_ID, sales.getProposalId());
                        
                        dbSales.setLong(COL_UNIT_USAHA_ID, sales.getUnitUsahaId());
                        dbSales.setLong(COL_ITEM_GROUP_ID, sales.getItemGroupId());
                        dbSales.setInt(COL_TYPE, sales.getType());
                        
                        dbSales.setInt(COL_PPH_TYPE, sales.getPphType());
                        dbSales.setDouble(COL_PPH_PERCENT, sales.getPphPercent());
                        dbSales.setDouble(COL_PPH_AMOUNT, sales.getPphAmount());
                        
			dbSales.insert();
			sales.setOID(dbSales.getlong(COL_SALES_ID));
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSales(0),CONException.UNKNOWN);
		}
		return sales.getOID();
	}

	public static long updateExc(Sales sales) throws CONException{
		try{
			if(sales.getOID() != 0){
				DbSales dbSales = new DbSales(sales.getOID());

				dbSales.setDate(COL_DATE, sales.getDate());
				dbSales.setString(COL_NUMBER, sales.getNumber());
				dbSales.setString(COL_NUMBER_PREFIX, sales.getNumberPrefix());
				dbSales.setInt(COL_COUNTER, sales.getCounter());
				dbSales.setString(COL_NAME, sales.getName());
				dbSales.setLong(COL_CUSTOMER_ID, sales.getCustomerId());
				dbSales.setString(COL_CUSTOMER_PIC, sales.getCustomerPic());
				dbSales.setString(COL_CUSTOMER_PIC_PHONE, sales.getCustomerPicPhone());
				dbSales.setString(COL_CUSTOMER_ADDRESS, sales.getCustomerAddress());
				dbSales.setDate(COL_START_DATE, sales.getStartDate());
				dbSales.setDate(COL_END_DATE, sales.getEndDate());
				dbSales.setString(COL_CUSTOMER_PIC_POSITION, sales.getCustomerPicPosition());
				dbSales.setLong(COL_EMPLOYEE_ID, sales.getEmployeeId());
				dbSales.setLong(COL_USER_ID, sales.getUserId());
				dbSales.setString(COL_EMPLOYEE_HP, sales.getEmployeeHp());
				dbSales.setString(COL_DESCRIPTION, sales.getDescription());
				dbSales.setInt(COL_STATUS, sales.getStatus());
				dbSales.setDouble(COL_AMOUNT, sales.getAmount());
				dbSales.setLong(COL_CURRENCY_ID, sales.getCurrencyId());
				dbSales.setLong(COL_COMPANY_ID, sales.getCompanyId());
				dbSales.setLong(COL_CATEGORY_ID, sales.getCategoryId());
                                
                                dbSales.setDouble(COL_DISCOUNT_PERCENT, sales.getDiscountPercent());
                                dbSales.setDouble(COL_DISCOUNT_AMOUNT, sales.getDiscountAmount());
                                dbSales.setInt(COL_VAT, sales.getVat());
                                dbSales.setDouble(COL_VAT_PERCENT, sales.getVatPercent());
                                dbSales.setDouble(COL_VAT_AMOUNT, sales.getVatAmount());
                                dbSales.setInt(COL_DISCOUNT, sales.getDiscount());
                                
                                dbSales.setInt(COL_WARRANTY_STATUS, sales.getWarrantyStatus());
                                dbSales.setDate(COL_WARRANTY_DATE, sales.getWarrantyDate());
                                dbSales.setString(COL_WARRANTY_RECEIVE, sales.getWarrantyReceive());
                                dbSales.setInt(COL_MANUAL_STATUS, sales.getManualStatus());
                                dbSales.setDate(COL_MANUAL_DATE, sales.getManualDate());
                                dbSales.setString(COL_MANUAL_RECEIVE, sales.getManualReceive());
                                dbSales.setString(COL_NOTE_CLOSING, sales.getNoteClosing());
                                
                                dbSales.setDouble(COL_BOOKING_RATE, sales.getBookingRate());
                                dbSales.setDouble(COL_EXCHANGE_AMOUNT, sales.getExchangeAmount());
                                dbSales.setLong(COL_PROPOSAL_ID, sales.getProposalId());
                                
                                dbSales.setLong(COL_UNIT_USAHA_ID, sales.getUnitUsahaId());
                                dbSales.setLong(COL_ITEM_GROUP_ID, sales.getItemGroupId());
                                dbSales.setInt(COL_TYPE, sales.getType());
                                
                                dbSales.setInt(COL_PPH_TYPE, sales.getPphType());
                                dbSales.setDouble(COL_PPH_PERCENT, sales.getPphPercent());
                                dbSales.setDouble(COL_PPH_AMOUNT, sales.getPphAmount());
                                
				dbSales.update();
				return sales.getOID();

			}
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSales(0),CONException.UNKNOWN);
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{
		try{
			DbSales dbSales = new DbSales(oid);
			dbSales.delete();
		}catch(CONException dbe){
			throw dbe;
		}catch(Exception e){
			throw new CONException(new DbSales(0),CONException.UNKNOWN);
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
			String sql = "SELECT * FROM " + DB_SALES;
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
				Sales sales = new Sales();
				resultToObject(rs, sales);
				lists.add(sales);
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

	public static void resultToObject(ResultSet rs, Sales sales){
		try{
			sales.setOID(rs.getLong(DbSales.colNames[DbSales.COL_SALES_ID]));
			sales.setDate(rs.getDate(DbSales.colNames[DbSales.COL_DATE]));
			sales.setNumber(rs.getString(DbSales.colNames[DbSales.COL_NUMBER]));
			sales.setNumberPrefix(rs.getString(DbSales.colNames[DbSales.COL_NUMBER_PREFIX]));
			sales.setCounter(rs.getInt(DbSales.colNames[DbSales.COL_COUNTER]));
			sales.setName(rs.getString(DbSales.colNames[DbSales.COL_NAME]));
			sales.setCustomerId(rs.getLong(DbSales.colNames[DbSales.COL_CUSTOMER_ID]));
			sales.setCustomerPic(rs.getString(DbSales.colNames[DbSales.COL_CUSTOMER_PIC]));
			sales.setCustomerPicPhone(rs.getString(DbSales.colNames[DbSales.COL_CUSTOMER_PIC_PHONE]));
			sales.setCustomerAddress(rs.getString(DbSales.colNames[DbSales.COL_CUSTOMER_ADDRESS]));
			sales.setStartDate(rs.getDate(DbSales.colNames[DbSales.COL_START_DATE]));
			sales.setEndDate(rs.getDate(DbSales.colNames[DbSales.COL_END_DATE]));
			sales.setCustomerPicPosition(rs.getString(DbSales.colNames[DbSales.COL_CUSTOMER_PIC_POSITION]));
			sales.setEmployeeId(rs.getLong(DbSales.colNames[DbSales.COL_EMPLOYEE_ID]));
			sales.setUserId(rs.getLong(DbSales.colNames[DbSales.COL_USER_ID]));
			sales.setEmployeeHp(rs.getString(DbSales.colNames[DbSales.COL_EMPLOYEE_HP]));
			sales.setDescription(rs.getString(DbSales.colNames[DbSales.COL_DESCRIPTION]));
			sales.setStatus(rs.getInt(DbSales.colNames[DbSales.COL_STATUS]));
			sales.setAmount(rs.getDouble(DbSales.colNames[DbSales.COL_AMOUNT]));
			sales.setCurrencyId(rs.getLong(DbSales.colNames[DbSales.COL_CURRENCY_ID]));
			sales.setCompanyId(rs.getLong(DbSales.colNames[DbSales.COL_COMPANY_ID]));
			sales.setCategoryId(rs.getLong(DbSales.colNames[DbSales.COL_CATEGORY_ID]));
                        
                        sales.setDiscountPercent(rs.getDouble(DbSales.colNames[DbSales.COL_DISCOUNT_PERCENT]));
                        sales.setDiscountAmount(rs.getDouble(DbSales.colNames[DbSales.COL_DISCOUNT_AMOUNT]));
                        sales.setVat(rs.getInt(DbSales.colNames[DbSales.COL_VAT]));
                        sales.setVatPercent(rs.getDouble(DbSales.colNames[DbSales.COL_VAT_PERCENT]));
                        sales.setVatAmount(rs.getDouble(DbSales.colNames[DbSales.COL_VAT_AMOUNT]));
                        sales.setDiscount(rs.getInt(DbSales.colNames[DbSales.COL_DISCOUNT]));

                        sales.setWarrantyStatus(rs.getInt(DbSales.colNames[DbSales.COL_WARRANTY_STATUS]));
                        sales.setWarrantyDate(rs.getDate(DbSales.colNames[DbSales.COL_WARRANTY_DATE]));
                        sales.setWarrantyReceive(rs.getString(DbSales.colNames[DbSales.COL_WARRANTY_RECEIVE]));
                        sales.setManualStatus(rs.getInt(DbSales.colNames[DbSales.COL_MANUAL_STATUS]));
                        sales.setManualDate(rs.getDate(DbSales.colNames[DbSales.COL_MANUAL_DATE]));
                        sales.setManualReceive(rs.getString(DbSales.colNames[DbSales.COL_MANUAL_RECEIVE]));
                        sales.setNoteClosing(rs.getString(DbSales.colNames[DbSales.COL_NOTE_CLOSING]));
                        
                        sales.setBookingRate(rs.getDouble(DbSales.colNames[DbSales.COL_BOOKING_RATE]));
                        sales.setExchangeAmount(rs.getDouble(DbSales.colNames[DbSales.COL_EXCHANGE_AMOUNT]));
                        sales.setProposalId(rs.getLong(DbSales.colNames[DbSales.COL_PROPOSAL_ID]));
                        
                        sales.setUnitUsahaId(rs.getLong(DbSales.colNames[DbSales.COL_UNIT_USAHA_ID]));
                        sales.setItemGroupId(rs.getLong(DbSales.colNames[DbSales.COL_ITEM_GROUP_ID]));
                        sales.setType(rs.getInt(DbSales.colNames[DbSales.COL_TYPE]));
                        
                        sales.setPphType(rs.getInt(DbSales.colNames[DbSales.COL_PPH_TYPE]));
                        sales.setPphPercent(rs.getDouble(DbSales.colNames[DbSales.COL_PPH_PERCENT]));
                        sales.setPphAmount(rs.getDouble(DbSales.colNames[DbSales.COL_PPH_AMOUNT]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long salesId)
	{
		CONResultSet dbrs = null;
		boolean result = false;
		try
		{
			String sql = "SELECT * FROM " + DB_SALES + " WHERE " + 
				DbSales.colNames[DbSales.COL_SALES_ID] + " = " + salesId;
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
			String sql = "SELECT COUNT("+ DbSales.colNames[DbSales.COL_SALES_ID] + ") FROM " + DB_SALES;
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
				Sales sales = (Sales)list.get(ls);
				if(oid == sales.getOID())
					found=true;
				}
			}
		}
		if((start >= size) && (size > 0))
			start = start - recordToGet;

		return start;
	}

        
                public static int getNextCounter(long systemCompanyId){
                int result = 0;
                
                CONResultSet dbrs = null;
                try{
                    String sql = "select max(counter) from "+DB_SALES+" where "+
                        " number_prefix='"+getNumberPrefix(systemCompanyId)+"' ";
                    
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
        
        public static String getNumberPrefix(long systemCompanyId){
            
                //Company sysCompany = DbCompany.getCompany();
                Company sysCompany = new Company();
                try {
                    sysCompany  = DbCompany.fetchExc(systemCompanyId);
                }catch(Exception e){
                    System.out.println(e);
                }

                String code = DbSystemProperty.getValueByName("SALES_CODE");//sysCompany.getSalesCode();
               
                //System.out.println(code);
                
                Date dt = new Date();
                
                code = code +"/"+JSPFormater.formatDate(new Date(), "yyyy");
                
                return code;
        }

        public static String getNextNumber(int ctr, long systemCompanyId){
            
                String code = getNumberPrefix(systemCompanyId);
                
                if(ctr<10){
                    code = "000"+ctr+"/"+code;
                }
                else if(ctr<100){
                    code = "00"+ctr+"/"+code;
                }
                else if(ctr<1000){
                    code = "0"+ctr+"/"+code;
                }
                else{
                    code = ctr+"/"+code;
                }
                
                return code;
                
        }
        
        /*
        public static void updateSalesAmount(long proposalId, Sales sales){
                
            int vatCheck = sales.getVat();
            int discCheck = sales.getDiscount();
            double discountPercent = sales.getDiscountPercent();
            
            double subtotal = DbSalesDetail.getSubTotalSalesAmount(proposalId);
            double vat = 0;
            double discount = 0;
            if(discCheck==1 && discountPercent!=0){
                discount = subtotal * (discountPercent/100);
            }
            if(vatCheck==1){
                Company c = new Company();
                try{
                    c = DbCompany.fetchExc(sales.getCompanyId());
                }
                catch(Exception e){
                }
                vat = (subtotal - discount) * (c.getGovernmentVat()/100);
            }
            
            try{
                sales.setDiscount(discCheck);
                sales.setVat(vatCheck);
                sales.setDiscountPercent(discountPercent);
                sales.setDiscountAmount(discount);
                
                sales.setAmount(subtotal - discount + vat);
                
                System.out.println("sales currency : "+sales.getCurrencyId());
                Currency c = new Currency();
                try{
                    c = DbCurrency.fetchExc(sales.getCurrencyId());
                }
                catch(Exception e){
                }
                ExchangeRate er = DbExchangeRate.getStandardRate();

                if(c.getCurrencyCode().equalsIgnoreCase(DbSystemProperty.getValueByName("CURRENCY_CODE_IDR"))){
                    sales.setBookingRate(er.getValueIdr());
                    sales.setExchangeAmount(sales.getAmount());
                }
                else if(c.getCurrencyCode().equalsIgnoreCase(DbSystemProperty.getValueByName("CURRENCY_CODE_USD"))){
                    sales.setBookingRate(er.getValueUsd());
                    sales.setExchangeAmount(sales.getAmount() * er.getValueUsd());
                }
                else{
                    sales.setBookingRate(er.getValueEuro());
                    sales.setExchangeAmount(sales.getAmount() * er.getValueEuro());
                }                
                
                DbSales.updateExc(sales);
            }
            catch(Exception e){
            }
            
            
        }
         */
        
        public static void updateSalesAmount(long salesId, Sales sales){
                
            int vatCheck = sales.getVat();
            int discCheck = sales.getDiscount();
            double discountPercent = sales.getDiscountPercent();
            
            double subtotal = DbSalesDetail.getSubTotalSalesAmount(salesId); 
            double vat = 0;
            double discount = 0;
            
            if(discCheck==1 && discountPercent!=0){
                discount = subtotal * (discountPercent/100);
            }
            
            if(vatCheck==1){
                Company c = new Company();
                try{
                    c = DbCompany.fetchExc(sales.getCompanyId());
                }
                catch(Exception e){
                }
                //vat = (subtotal - discount) * (c.getGovernmentVat()/100);
                vat = (subtotal - discount) * (sales.getVatPercent()/100);
            }
            
            try{
                sales.setDiscount(discCheck);
                sales.setVat(vatCheck);
                sales.setVatAmount(vat);
                sales.setDiscountPercent(discountPercent);
                sales.setDiscountAmount(discount);
                
                sales.setAmount(subtotal);// - discount + vat);
                
                System.out.println("sales currency : "+sales.getCurrencyId());
                Currency c = new Currency();
                try{
                    c = DbCurrency.fetchExc(sales.getCurrencyId());
                }
                catch(Exception e){
                }
                ExchangeRate er = DbExchangeRate.getStandardRate();

                if(c.getCurrencyCode().equalsIgnoreCase(DbSystemProperty.getValueByName("CURRENCY_CODE_IDR"))){
                    sales.setBookingRate(er.getValueIdr());
                    sales.setExchangeAmount(sales.getAmount() - discount + vat);
                }
                else if(c.getCurrencyCode().equalsIgnoreCase(DbSystemProperty.getValueByName("CURRENCY_CODE_USD"))){
                    sales.setBookingRate(er.getValueUsd());
                    sales.setExchangeAmount((sales.getAmount()- discount + vat) * er.getValueUsd());
                }
                else{
                    sales.setBookingRate(er.getValueEuro());
                    sales.setExchangeAmount((sales.getAmount()- discount + vat) * er.getValueEuro());
                }                
                
                DbSales.updateExc(sales);
            }
            catch(Exception e){
            }
            
            
        }
        
        public static Vector getListSales(int start, int recordToGet, long customerId, String salesName, String number, Date startDate, Date endDate, int ignoreDate, int status){
            
            /*
            String sql = " select * from "+DB_SALES+" p inner join "+DbProposal.DB_CRM_PROPOSAL+" pro "+
                         " on p."+colNames[COL_PROPOSAL_ID]+" = pro."+DbProposal.colNames[DbProposal.COL_PROPOSAL_ID]+
                         " where pro."+DbProposal.colNames[DbProposal.COL_STATUS]+" = "+I_Crm.PROPOSAL_STATUS_PROJECT;
             */
            
            String sql = " select * from "+ DB_SALES;
                         //" where "+DbSales.colNames[DbSales.COL_STATUS]+" = "+I_Crm.PROPOSAL_STATUS_PROJECT;
                         
                         String where = "";
                         if(customerId!=0){
                                //sql = sql + " and p."+colNames[COL_CUSTOMER_ID]+" = "+customerId;
                                where = DbSales.colNames[DbSales.COL_CUSTOMER_ID]+" = "+customerId;
                         }
                         
                         if(salesName!=null && salesName.length()>0){
                             if(where.length()>0){
                                //sql = sql + " and p."+colNames[COL_NAME]+" like '%"+salesName+"%'";
                                where = where + " and "+DbSales.colNames[DbSales.COL_NAME]+" like '%"+salesName+"%'";
                             }else{
                                 where = DbSales.colNames[DbSales.COL_NAME]+" like '%"+salesName+"%'";
                             }
                         }
                         
                         if(number!=null && number.length()>0){
                             if(where.length()>0){
                                //sql = sql + " and p."+colNames[COL_NUMBER]+" like '%"+number+"%'";
                                 where = where + " and "+DbSales.colNames[DbSales.COL_NUMBER]+" like '%"+number+"%'";
                             }else{
                                 where = DbSales.colNames[DbSales.COL_NUMBER]+" like '%"+number+"%'";
                             }
                         }
            
                         if(status!=-1){
                             if(where.length()>0){
                                //sql = sql + " and p."+colNames[COL_STATUS]+"  = "+status;
                                where = where + " and "+DbSales.colNames[DbSales.COL_STATUS]+"  = "+status;
                             }else{
                                where = DbSales.colNames[DbSales.COL_STATUS]+"  = "+status;
                             }
                         }
            
                         if(ignoreDate==0){
                             if(where!=null && where.length()>0){
                                //sql = sql + " and (p."+colNames[COL_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                    //" and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                                where = where + " and ("+DbSales.colNames[DbSales.COL_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                    " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                             }else{
                                 where = where + "("+DbSales.colNames[DbSales.COL_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                    " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                             }
                         }
                         
                         if(where.length()>0){
                            sql = sql + " where "+where;
                         }
            
                         if(recordToGet>0){
                             sql = sql + " limit "+start+","+recordToGet;
                         }
            
                         System.out.println(sql);
            
                         
            CONResultSet crs = null;
            Vector result  = new Vector();
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Sales p = new Sales();
                    DbSales.resultToObject(rs, p);
                    result.add(p);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
        
        public static int getCountSales(long customerId, String salesName, String number, Date startDate, Date endDate, int ignoreDate, int status){
            /*
            String sql = " select count(p."+colNames[COL_SALES_ID]+") from "+DB_SALES+" p inner join "+DbProposal.DB_CRM_PROPOSAL+" pro "+
                         " on p."+colNames[COL_PROPOSAL_ID]+" = pro."+DbProposal.colNames[DbProposal.COL_PROPOSAL_ID]+
                         " where pro."+DbProposal.colNames[DbProposal.COL_STATUS]+" = "+I_Crm.PROPOSAL_STATUS_PROJECT;
             */
            
             String sql = " select count("+DbSales.colNames[DbSales.COL_SALES_ID]+") from "+DB_SALES;
                         
                         String where = "";
                         if(customerId!=0){
                                //sql = sql + " and p."+colNames[COL_CUSTOMER_ID]+" = "+customerId;
                                where = DbSales.colNames[DbSales.COL_CUSTOMER_ID]+" = "+customerId;
                         }
            
                         if(salesName!=null && salesName.length()>0){
                             if(where.length()>0){
                                //sql = sql + " and p."+DbSales.colNames[DbSales.COL_NAME]+" like '%"+salesName+"%'";
                                where = where + " and "+DbSales.colNames[DbSales.COL_NAME]+" like '%"+salesName+"%'";
                             }else{
                                 where = DbSales.colNames[DbSales.COL_NAME]+" like '%"+salesName+"%'";
                             }
                         }
                         
                         if(number!=null && number.length()>0){
                             if(where.length()>0){
                                //sql = sql + " and p."+DbSales.colNames[DbSales.COL_NUMBER]+" like '%"+number+"%'";
                                where = where + " and "+DbSales.colNames[DbSales.COL_NUMBER]+" like '%"+number+"%'";
                             }else{
                                 where = DbSales.colNames[DbSales.COL_NUMBER]+" like '%"+number+"%'";
                             }
                         }
            
                         if(status!=-1){
                             if(where.length()>0){
                                //sql = sql + " and p."+DbSales.colNames[DbSales.COL_STATUS]+"  = "+status;
                                where = where + " and "+DbSales.colNames[DbSales.COL_STATUS]+"  = "+status;
                             }else{
                                 where = DbSales.colNames[DbSales.COL_STATUS]+"  = "+status;
                             }
                         }
            
                         if(ignoreDate==0){
                             if(where!=null && where.length()>0){
                                //sql = sql + " and (p."+DbSales.colNames[DbSales.COL_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                  //  " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                                where = where + " and ("+DbSales.colNames[DbSales.COL_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                    " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                             }else{
                                 where = where + " ("+DbSales.colNames[DbSales.COL_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                                    " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                             }
                         }
                         
                         if(where.length()>0){
                            sql = sql + " where "+where;
                         }
            
                         System.out.println(sql);
            
                         
            CONResultSet crs = null;
            int result  = 0;
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
        
        
        public static Vector getCustomerBySales(long customerId, long unitUsahaId){
            
            String sql = "select distinct c.* from "+DB_SALES+" p "+
                         "inner join "+DbCustomer.DB_CUSTOMER+" c "+
                         "on c."+DbCustomer.colNames[DbCustomer.COL_CUSTOMER_ID]+" = p."+colNames[COL_CUSTOMER_ID];
            
            String where  = "";
            if(customerId!=0){
                where = "p."+colNames[COL_CUSTOMER_ID]+"="+customerId;
            }
            
            if(unitUsahaId!=0){
                if(where!=null && where.length()>0){
                    where = where + " and "; 
                }
                
                where = where + colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
            }
            
            if(where!=null && where.length()>0){
                sql = sql + " where "+where;
            }
            
            Vector result = new Vector();
            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Customer c = new Customer();
                    resultToObject(rs, c);
                    result.add(c);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
        
        public static Vector getSalesReport(Date startDate, Date endDate, long unitUsahaId){
            
            String sql = " select psd."+DbSalesDetail.colNames[DbSalesDetail.COL_PRODUCT_MASTER_ID]+
                        ", sum(psd."+DbSalesDetail.colNames[DbSalesDetail.COL_QTY]+")" +
                        ", sum(psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+")" +
                        ", sum(psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+" * ps."+colNames[COL_DISCOUNT_PERCENT]+"/100)" +
                        ", sum((psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+" - (psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+" * ps."+colNames[COL_DISCOUNT_PERCENT]+"/100))"+
                        " * ps."+colNames[COL_VAT_PERCENT]+"/100) " +
                        " from "+DbSalesDetail.DB_SALES_DETAIL+" psd "+
                        " inner join "+DB_SALES+" ps on " +
                        " ps."+colNames[COL_SALES_ID]+" = psd."+DbSalesDetail.colNames[DbSalesDetail.COL_SALES_ID]+
                        " where ps."+colNames[COL_DATE]+" between " +
                        " '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"' and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"'";
                        
                        if(unitUsahaId!=0){
                            sql = sql + " and ps."+colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
                        }
			
                        sql = sql + " group by psd."+DbSalesDetail.colNames[DbSalesDetail.COL_PRODUCT_MASTER_ID];
                        
                        System.out.println(sql);
                                        
            Vector result = new Vector();            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    RptSales rpt = new RptSales();
                    rpt.setItemMasterId(rs.getLong(1));
                    rpt.setQty(rs.getDouble(2));
                    rpt.setAmount(rs.getDouble(3));
                    rpt.setDiscount(rs.getDouble(4));
                    rpt.setVat(rs.getDouble(5));
                    
                    ItemMaster imx = new ItemMaster();
                    try{
                        imx = DbItemMaster.fetchExc(rpt.getItemMasterId());
                        rpt.setItemName(imx.getCode()+" - "+imx.getName());
                        ItemGroup ig = DbItemGroup.fetchExc(imx.getItemGroupId());
                        rpt.setItemGroupName(ig.getName());                        
                    }
                    catch(Exception ex){
                    }
                    
                    result.add(rpt);
                    
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
        }
        
        public static Vector getSalesReport(Date startDate, Date endDate, long unitUsahaId, int salesType){
            
            String sql = " select psd."+DbSalesDetail.colNames[DbSalesDetail.COL_PRODUCT_MASTER_ID]+
                        ", sum(psd."+DbSalesDetail.colNames[DbSalesDetail.COL_QTY]+")" +
                        ", sum(psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+")" +
                        ", sum(psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+" * ps."+colNames[COL_DISCOUNT_PERCENT]+"/100)" +
                        ", sum((psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+" - (psd."+DbSalesDetail.colNames[DbSalesDetail.COL_TOTAL]+" * ps."+colNames[COL_DISCOUNT_PERCENT]+"/100))"+
                        " * ps."+colNames[COL_VAT_PERCENT]+"/100) " +
                        " from "+DbSalesDetail.DB_SALES_DETAIL+" psd "+
                        " inner join "+DB_SALES+" ps on " +
                        " ps."+colNames[COL_SALES_ID]+" = psd."+DbSalesDetail.colNames[DbSalesDetail.COL_SALES_ID]+
                        " where ps."+colNames[COL_DATE]+" between " +
                        " '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"' and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"'";
                        
                        if(unitUsahaId!=0){
                            sql = sql + " and ps."+colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
                        }
			
                        sql = sql + " group by psd."+DbSalesDetail.colNames[DbSalesDetail.COL_PRODUCT_MASTER_ID];
                        
                        System.out.println(sql);
                                        
            Vector result = new Vector();            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    RptSales rpt = new RptSales();
                    rpt.setItemMasterId(rs.getLong(1));
                    rpt.setQty(rs.getDouble(2));
                    rpt.setAmount(rs.getDouble(3));
                    rpt.setDiscount(rs.getDouble(4));
                    rpt.setVat(rs.getDouble(5));
                    
                    ItemMaster imx = new ItemMaster();
                    try{
                        imx = DbItemMaster.fetchExc(rpt.getItemMasterId());
                        rpt.setItemName(imx.getCode()+" - "+imx.getName());
                        ItemGroup ig = DbItemGroup.fetchExc(imx.getItemGroupId());
                        rpt.setItemGroupName(ig.getName());                        
                    }
                    catch(Exception ex){
                    }
                    
                    result.add(rpt);
                    
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
        }
        
        public static void postJournal(long unitID){
            
            Company comp = DbCompany.getCompany();
                
            ExchangeRate er = DbExchangeRate.getStandardRate();
            
            Vector v = list(0,0, colNames[COL_UNIT_USAHA_ID]+"="+unitID+" and "+colNames[COL_STATUS]+"=0", "");
            
            if(v!=null && v.size()>0){
                
                for(int i=0; i<v.size(); i++){
                    
                    Sales sales = (Sales)v.get(i);
                    
                    UnitUsaha us = new UnitUsaha();
                    try{
                        us = DbUnitUsaha.fetchExc(sales.getUnitUsahaId());
                    }
                    catch(Exception ex){
                    }
                    
                    Vector dtls = DbSalesDetail.list(0,0, DbSalesDetail.colNames[DbSalesDetail.COL_SALES_ID]+"="+sales.getOID(),  "");
                    
                    //jurnal main
                    String memo = "Cash sales, "+us.getName()+", "+JSPFormater.formatDate(sales.getDate(), "dd/MM/yyyy")+", nomor : "+sales.getNumber();
                    
                    long oid = DbGl.postJournalMain(0, sales.getDate(), sales.getCounter(), sales.getNumber(), sales.getNumberPrefix(), 
                        I_Project.JOURNAL_TYPE_GENERAL_LEDGER, 
                        memo, sales.getUserId(), "", sales.getOID(), "", sales.getDate());
                                        
                    //jurnal debet
                    memo = "Cash";                    
                    String salesCode = DbSystemProperty.getValueByName("SALES_ACC_CODE").trim();                    
                    Coa coa = DbCoa.getCoaByCode(salesCode);
                    
                    double amount = sales.getAmount() - sales.getDiscountAmount() + sales.getVatAmount();
                    
                    DbGl.postJournalDetail(er.getValueIdr(), coa.getOID(), 0, amount,             
                                amount, comp.getBookingCurrencyId(), oid, memo, 0);//non departmenttal item, department id = 0
                    
                    //jurnal credit vat
                    if(sales.getVatPercent()>0){
                        memo = "ppn";                            
                        DbGl.postJournalDetail(er.getValueIdr(), us.getCoaPpnId(), sales.getVatAmount(), 0,             
                                            sales.getVatAmount(), comp.getBookingCurrencyId(), oid, memo, 0); 
                    }
                                                        
                    for(int x=0; x<dtls.size(); x++){
                        
                        SalesDetail sd = (SalesDetail)dtls.get(x);
                        
                        ItemMaster im = new ItemMaster();
                        ItemGroup ig = new ItemGroup();
                        try{
                            im = DbItemMaster.fetchExc(sd.getProductMasterId());
                            ig = DbItemGroup.fetchExc(im.getItemGroupId());
                        }
                        catch(Exception e){
                        }
                        
                        String igs = ig.getAccountSalesCash();
                        Coa coaSales = DbCoa.getCoaByCode(ig.getAccountSalesCash().trim());
                        Coa coaInv = DbCoa.getCoaByCode(ig.getAccountInv().trim());
                        Coa coaCogs = DbCoa.getCoaByCode(ig.getAccountCogs().trim());
                               
                        //jurnal credit sales
                        if(sales.getDiscountPercent()==0){
                            memo = "sales item "+im.getName();         
                            DbGl.postJournalDetail(er.getValueIdr(), coaSales.getOID(), sd.getTotal(), 0,             
                                                sd.getTotal(), comp.getBookingCurrencyId(), oid, memo, 0); 
                        }
                        else{
                            amount = sd.getTotal() - (sd.getTotal()*sales.getDiscountPercent()/100); 
                            memo = "sales item "+im.getName();         
                            DbGl.postJournalDetail(er.getValueIdr(), coaSales.getOID(), amount, 0,             
                                                amount, comp.getBookingCurrencyId(), oid, memo, 0); 
                        }
                        
                        //hpp & inventory - and stockable only
                        if(sd.getCogs()>0 && im.getNeedRecipe()==0){
                            //journal inventory credit
                            memo = "inventory : "+im.getName();         
                            DbGl.postJournalDetail(er.getValueIdr(), coaInv.getOID(), sd.getCogs()*sd.getQty(), 0,             
                                                im.getCogs()*sd.getQty(), comp.getBookingCurrencyId(), oid, memo, 0); 

                            //journal hpp
                            memo = "hpp : "+im.getName();         
                            DbGl.postJournalDetail(er.getValueIdr(), coaCogs.getOID(), 0, sd.getCogs()*sd.getQty(),             
                                                im.getCogs()*sd.getQty(), comp.getBookingCurrencyId(), oid, memo, 0); 

                        }
                        
                        //proses stock
                        DbStock.insertSalesItem(sales, sd); 
                            
                    }
                    
                    DbGl.optimizeJournal(oid);
                    
                    sales.setStatus(1);
                    try{
                        DbSales.updateExc(sales);
                    }
                    catch(Exception e){
                    }
                    
                }
                
            }
            
        }
        
        public static void postJournal(Vector temp){
            
            Company comp = DbCompany.getCompany();
                
            ExchangeRate er = DbExchangeRate.getStandardRate();
            
            Vector v = temp;//list(0,0, colNames[COL_UNIT_USAHA_ID]+"="+unitID+" and "+colNames[COL_STATUS]+"=0", "");
            
            if(v!=null && v.size()>0){
                
                for(int i=0; i<v.size(); i++){
                    
                    Sales sales = (Sales)v.get(i);
                    
                    UnitUsaha us = new UnitUsaha();
                    try{
                        us = DbUnitUsaha.fetchExc(sales.getUnitUsahaId());
                    }
                    catch(Exception ex){
                    }
                    
                    Customer cust = new Customer();
                    if(sales.getType()==DbSales.TYPE_CREDIT){
                        try{
                            cust = DbCustomer.fetchExc(sales.getCustomerId());
                        }
                        catch(Exception ex){
                        }
                    }
                    
                    Vector dtls = DbSalesDetail.list(0,0, DbSalesDetail.colNames[DbSalesDetail.COL_SALES_ID]+"="+sales.getOID(),  "");
                    
                    //jurnal main
                    String memo = "";
                    if(sales.getType()==DbSales.TYPE_CASH){
                        memo = "Cash sales, "+us.getName();//+", "+JSPFormater.formatDate(sales.getDate(), "dd/MM/yyyy")+", nomor : "+sales.getNumber();
                    }
                    else{
                        memo = "Credit sales, "+us.getName()+
                        " : "+((cust.getCode()!=null && cust.getCode().length()>0) ? cust.getCode()+"/"+cust.getName() : cust.getName());
                        ;//+", "+JSPFormater.formatDate(sales.getDate(), "dd/MM/yyyy")+", nomor : "+sales.getNumber();
                    }
                    
                    //cek nomor, jika sama cari nomor lain
                    int count = DbGl.getCount(DbGl.colNames[DbGl.COL_JOURNAL_NUMBER]+"='"+sales.getNumber()+"'");
                    String number = sales.getNumber();
                    if(count>0){
                        number = number + " - "+DbGl.getNextNumber(DbGl.getNextCounter());
                    }
                    
                    //post jurnal
                    long oid = DbGl.postJournalMain(0, sales.getDate(), sales.getCounter(), number, sales.getNumberPrefix(), 
                        I_Project.JOURNAL_TYPE_GENERAL_LEDGER, 
                        memo, sales.getUserId(), "", sales.getOID(), "", sales.getDate());
                        
                    //jika sukses input gl
                    if(oid!=0){
                        
                        //jurnal debet
                        Coa coa = new Coa();
                        if(sales.getType()==DbSales.TYPE_CASH){
                            memo = "Cash";                    
                            String salesCode = DbSystemProperty.getValueByName("SALES_ACC_CODE").trim();                    
                            coa = DbCoa.getCoaByCode(salesCode);
                        }
                        else{
                            memo = "Credit Sales";                    
                            coa.setOID(us.getCoaArId());
                        }

                        double amount = sales.getAmount() - sales.getDiscountAmount() + sales.getVatAmount();
                        
                        if(sales.getPphAmount()!=0){
                            //Debet AR
                            Coa coaPph = new Coa();
                            String str = "";
                            if(sales.getPphType()==DbSales.TYPE_JASA){
                                coaPph = DbCoa.getCoaByCode(DbSystemProperty.getValueByName("PROJECT_PPH_PASAL_23"));
                                str = "PPH pasal 23 ";
                            }
                            else{
                                coaPph = DbCoa.getCoaByCode(DbSystemProperty.getValueByName("PROJECT_PPH_PASAL_22"));
                                str = "PPH pasal 22 ";
                            }
                            DbGl.postJournalDetail(sales.getBookingRate(), coaPph.getOID(), 0, sales.getPphAmount(),             
                                    sales.getPphAmount(), comp.getBookingCurrencyId(), oid, str , 0);

                            amount = amount - sales.getPphAmount();
                       }

                        DbGl.postJournalDetail(er.getValueIdr(), coa.getOID(), 0, amount,             
                                    amount, comp.getBookingCurrencyId(), oid, memo, 0);//non departmenttal item, department id = 0

                        //jurnal credit vat
                        if(sales.getVatPercent()>0){
                            memo = "ppn";                            
                            DbGl.postJournalDetail(er.getValueIdr(), us.getCoaPpnId(), sales.getVatAmount(), 0,             
                                                sales.getVatAmount(), comp.getBookingCurrencyId(), oid, memo, 0); 
                        }

                        for(int x=0; x<dtls.size(); x++){

                            SalesDetail sd = (SalesDetail)dtls.get(x);

                            ItemMaster im = new ItemMaster();
                            ItemGroup ig = new ItemGroup();
                            try{
                                im = DbItemMaster.fetchExc(sd.getProductMasterId());
                                ig = DbItemGroup.fetchExc(im.getItemGroupId());
                            }
                            catch(Exception e){
                            }

                            String igs = ig.getAccountSalesCash();
                            Coa coaSales = DbCoa.getCoaByCode(ig.getAccountSalesCash().trim());
                            Coa coaSalesCredit = DbCoa.getCoaByCode(ig.getAccountSales().trim());
                            Coa coaInv = DbCoa.getCoaByCode(ig.getAccountInv().trim());
                            Coa coaCogs = DbCoa.getCoaByCode(ig.getAccountCogs().trim());

                            //jurnal credit sales
                            if(sales.getDiscountPercent()==0){
                                if(sales.getType()==DbSales.TYPE_CASH){
                                    memo = "sales item "+im.getName();         
                                    DbGl.postJournalDetail(er.getValueIdr(), coaSales.getOID(), sd.getTotal(), 0,             
                                                        sd.getTotal(), comp.getBookingCurrencyId(), oid, memo, 0); 
                                }
                                else{
                                    memo = "sales item "+im.getName();         
                                    DbGl.postJournalDetail(er.getValueIdr(), coaSalesCredit.getOID(), sd.getTotal(), 0,             
                                                        sd.getTotal(), comp.getBookingCurrencyId(), oid, memo, 0); 
                                }
                            }
                            else{
                                if(sales.getType()==DbSales.TYPE_CASH){
                                    amount = sd.getTotal() - (sd.getTotal()*sales.getDiscountPercent()/100); 
                                    memo = "sales item "+im.getName();         
                                    DbGl.postJournalDetail(er.getValueIdr(), coaSales.getOID(), amount, 0,             
                                                        amount, comp.getBookingCurrencyId(), oid, memo, 0); 
                                }
                                else{
                                    amount = sd.getTotal() - (sd.getTotal()*sales.getDiscountPercent()/100); 
                                    memo = "sales item "+im.getName();         
                                    DbGl.postJournalDetail(er.getValueIdr(), coaSalesCredit.getOID(), amount, 0,             
                                                        amount, comp.getBookingCurrencyId(), oid, memo, 0); 
                                }
                            }

                            //journal inventory credit only for stockable item
                            if(sd.getCogs()>0 && im.getNeedRecipe()==0){
                                memo = "inventory : "+im.getName();         
                                DbGl.postJournalDetail(er.getValueIdr(), coaInv.getOID(), sd.getCogs()*sd.getQty(), 0,             
                                                    im.getCogs()*sd.getQty(), comp.getBookingCurrencyId(), oid, memo, 0); 

                                //journal hpp
                                memo = "hpp : "+im.getName();         
                                DbGl.postJournalDetail(er.getValueIdr(), coaCogs.getOID(), 0, sd.getCogs()*sd.getQty(),             
                                                    im.getCogs()*sd.getQty(), comp.getBookingCurrencyId(), oid, memo, 0); 

                            }

                            //proses stock
                            DbStock.insertSalesItem(sales, sd); 

                        }

                        DbGl.optimizeJournal(oid);

                        sales.setStatus(1);
                        try{
                            DbSales.updateExc(sales);
                        }
                        catch(Exception e){
                        }
                        
                        //jika credit post ar
                        if(sales.getType()!=DbSales.TYPE_CASH){
                            postReceivable(sales, dtls);
                        }
                        
                    }//end berhasil
                    
                }
                
            }
            
        }
        
        
        public static void postReceivable(Sales sales, Vector dtls){
            
            if(sales.getOID()!=0){
                
                ARInvoice ar = new ARInvoice();  
                ar.setSalesSource(1);
                ar.setDate(sales.getDate());
                ar.setProjectId(sales.getOID());
                ar.setDueDate(sales.getDate());
                ar.setTransDate(sales.getDate());
                ar.setCompanyId(sales.getCompanyId());
                ar.setOperatorId(sales.getUserId());
                ar.setMemo(sales.getNumber()+" - "+sales.getDescription());
                ar.setCurrencyId(sales.getCurrencyId());
                ar.setCustomerId(sales.getCustomerId());
                ar.setJournalCounter(DbARInvoice.getNextCounter(sales.getCompanyId()));
                ar.setDiscount(sales.getDiscount());
                ar.setDiscountPercent(sales.getDiscountPercent());
                ar.setJournalPrefix(DbARInvoice.getNumberPrefix(sales.getCompanyId()));
                ar.setJournalNumber(DbARInvoice.getNextNumber(ar.getJournalCounter(), ar.getCompanyId()));
                ar.setProjectTermId(sales.getOID());
                ar.setTotal(sales.getAmount());
                ar.setVat(sales.getVat());
                ar.setVatAmount(sales.getVatAmount());
                ar.setVatPercent(sales.getVatPercent());
                ar.setInvoiceNumber(sales.getNumber());
                
                try{
                    
                    long oid = DbARInvoice.insertExc(ar);
                    
                    if(oid!=0){
                        ARInvoiceDetail ard = new ARInvoiceDetail();

                        ard.setArInvoiceId(oid);                        
                        ard.setItemName("Credit Sales from sales module - "+sales.getNumber());
                        //ard.setCurrencyId(projectTerm.getCurrencyId());
                        ard.setQty(1);
                        ard.setPrice(sales.getAmount()-sales.getDiscountAmount()+sales.getVatAmount());
                        ard.setTotalAmount(sales.getAmount()-sales.getDiscountAmount()+sales.getVatAmount());
                        ard.setCompanyId(sales.getCompanyId());

                        try{
                            DbARInvoiceDetail.insertExc(ard);
                        }
                        catch(Exception e){
                        }
                    }
                }
                catch(Exception e){
                }
                
            }                
        }
        
        public static Vector getCustomerBySalesCredit(long customerId, long unitUsahaId){
            
            String sql = "select distinct c.* from "+DB_SALES+" p "+
                         "inner join "+DbCustomer.DB_CUSTOMER+" c "+
                         "on c."+DbCustomer.colNames[DbCustomer.COL_CUSTOMER_ID]+" = p."+colNames[COL_CUSTOMER_ID];
            
            String where  = "";
            
            where = " p."+DbSales.colNames[DbSales.COL_TYPE]+"="+DbSales.TYPE_CREDIT;
            
            if(customerId!=0){
                where = " and p."+colNames[COL_CUSTOMER_ID]+"="+customerId;
            }
            
            if(unitUsahaId!=0){
                if(where!=null && where.length()>0){
                    where = where + " and "; 
                }
                
                where = where + " p."+colNames[COL_UNIT_USAHA_ID]+"="+unitUsahaId;
            }
            
            if(where!=null && where.length()>0){
                sql = sql + " where "+where;
            }
            
            sql = sql + " order by c."+colNames[COL_NAME];
                       
            
            Vector result = new Vector();
            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    Customer c = new Customer();
                    resultToObject(rs, c);
                    result.add(c);
                }
            }
            catch(Exception e){
            }
            finally{
                CONResultSet.close(crs);
            }
            
            return result;
            
        }
        
        private static void resultToObject(ResultSet rs, Customer customer) {
        try {

            customer.setOID(rs.getLong(DbCustomer.colNames[DbCustomer.COL_CUSTOMER_ID]));
            customer.setName(rs.getString(DbCustomer.colNames[DbCustomer.COL_NAME]));
            customer.setType(rs.getInt(DbCustomer.colNames[DbCustomer.COL_TYPE]));
            customer.setAddress1(rs.getString(DbCustomer.colNames[DbCustomer.COL_ADDRESS_1]));
            customer.setAddress2(rs.getString(DbCustomer.colNames[DbCustomer.COL_ADDRESS_2]));
            customer.setCity(rs.getString(DbCustomer.colNames[DbCustomer.COL_CITY]));
            customer.setCountryId(rs.getLong(DbCustomer.colNames[DbCustomer.COL_COUNTRY_ID]));            
            customer.setContactPerson(rs.getString(DbCustomer.colNames[DbCustomer.COL_CONTACT_PERSON]));
            customer.setPosisiContactPerson(rs.getString(DbCustomer.colNames[DbCustomer.COL_POSISI_CONTACT_PERSON]));
            customer.setCountryCode(rs.getString(DbCustomer.colNames[DbCustomer.COL_COUNTRY_CODE]));            
            customer.setAreaCode(rs.getString(DbCustomer.colNames[DbCustomer.COL_AREA_CODE]));
            customer.setPhone(rs.getString(DbCustomer.colNames[DbCustomer.COL_PHONE]));
            customer.setWebsite(rs.getString(DbCustomer.colNames[DbCustomer.COL_WEBSITE]));
            customer.setEmail(rs.getString(DbCustomer.colNames[DbCustomer.COL_EMAIL]));
            customer.setStatus(rs.getString(DbCustomer.colNames[DbCustomer.COL_STATUS]));
            customer.setIndukCustomerId(rs.getLong(DbCustomer.colNames[DbCustomer.COL_INDUK_CUSTOMER_ID]));
            customer.setFax(rs.getString(DbCustomer.colNames[DbCustomer.COL_FAX]));
            customer.setNationalityId(rs.getLong(DbCustomer.colNames[DbCustomer.COL_NATIONALITY_ID]));
            customer.setState(rs.getString(DbCustomer.colNames[DbCustomer.COL_STATE]));
            customer.setCountryName(rs.getString(DbCustomer.colNames[DbCustomer.COL_COUNTRY_NAME]));
            customer.setNationalityName(rs.getString(DbCustomer.colNames[DbCustomer.COL_NATIONALITY_NAME]));            
            customer.setIdType(rs.getString(DbCustomer.colNames[DbCustomer.COL_ID_TYPE]));
            customer.setIdNumber(rs.getString(DbCustomer.colNames[DbCustomer.COL_ID_NUMBER]));                                   
            customer.setDob(rs.getDate(DbCustomer.colNames[DbCustomer.COL_DOB]));
            customer.setInterest(rs.getString(DbCustomer.colNames[DbCustomer.COL_INTEREST]));
            customer.setSalutation(rs.getString(DbCustomer.colNames[DbCustomer.COL_SALUTATION]));
            customer.setDobIgnore(rs.getInt(DbCustomer.colNames[DbCustomer.COL_DOB_IGNORE]));
            customer.setCode(rs.getString(DbCustomer.colNames[DbCustomer.COL_CODE]));
            customer.setPhoneArea(rs.getString(DbCustomer.colNames[DbCustomer.COL_PHONE_AREA]));
            customer.setFaxArea(rs.getString(DbCustomer.colNames[DbCustomer.COL_FAX_AREA]));
            customer.setMiddleName(rs.getString(DbCustomer.colNames[DbCustomer.COL_MIDDLE_NAME]));
            customer.setLastName(rs.getString(DbCustomer.colNames[DbCustomer.COL_LAST_NAME]));
            customer.setContactMiddleName(rs.getString(DbCustomer.colNames[DbCustomer.COL_CONTACT_MIDDLE_NAME]));
            customer.setContactLastName(rs.getString(DbCustomer.colNames[DbCustomer.COL_CONTACT_LAST_NAME]));
            customer.setHp(rs.getString(DbCustomer.colNames[DbCustomer.COL_HP]));
            customer.setZipCode(rs.getString(DbCustomer.colNames[DbCustomer.COL_ZIP_CODE]));            
            customer.setContactPhone(rs.getString(DbCustomer.colNames[DbCustomer.COL_CONTACT_PHONE]));
            customer.setContactPosition(rs.getString(DbCustomer.colNames[DbCustomer.COL_CONTACT_POSITION]));
            customer.setContactEmail(rs.getString(DbCustomer.colNames[DbCustomer.COL_CONTACT_EMAIL]));
            customer.setCompanyId(rs.getLong(DbCustomer.colNames[DbCustomer.COL_COMPANY_ID]));
            customer.setGolPrice(rs.getString(DbCustomer.colNames[DbCustomer.COL_GOL_PRICE]));

        } catch (Exception e) {
        }
    }
        
}
