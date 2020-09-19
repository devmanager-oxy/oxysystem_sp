
package com.project.general; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*; 

public class DbVendor extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc { 

	public static final  String DB_VENDOR = "vendor";

	public static final  int COL_NAME = 0;
	public static final  int COL_VENDOR_ID = 1;
	public static final  int COL_CODE = 2;
	public static final  int COL_ADDRESS = 3;
	public static final  int COL_CITY = 4;
	public static final  int COL_STATE = 5;
	public static final  int COL_PHONE = 6;
	public static final  int COL_HP = 7;
	public static final  int COL_FAX = 8;
	public static final  int COL_DUE_DATE = 9;
	public static final  int COL_CONTACT = 10;
	public static final  int COL_COUNTRY_NAME = 11;
	public static final  int COL_COUNTRY_ID = 12;
	public static final  int COL_TYPE = 13;
        
        public static final  int COL_DISCOUNT = 14;
        public static final  int COL_EMAIL = 15;
        public static final  int COL_NPWP = 16;
        public static final  int COL_VENDOR_TYPE = 17;
        public static final  int COL_PREV_LIABILITY = 18;
        public static final  int COL_WEB_PAGE = 19;
        

	public static final  String[] colNames = {
		"name",
		"vendor_id",
		"code",
		"address",
		"city",
		"state",
		"phone",
		"hp",
		"fax",
		"due_date",
		"cperson",
		"country_name",
		"country_id",
		"type",
                
                "disc",
                "email",
                "npwp",
                "vendor_type",
                "prev_liability",
                "wpage"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_STRING,
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_INT,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_LONG,
		TYPE_INT,
                
                TYPE_FLOAT,
                TYPE_STRING,
                TYPE_STRING,
                TYPE_STRING,
                TYPE_FLOAT,
                TYPE_STRING
	 }; 
         
         
         public static int VENDOR_TYPE_SUPPLIER = 0;
         public static int VENDOR_TYPE_PENITIP = 1;
         public static int VENDOR_TYPE_BYMHD = 2;
         public static int VENDOR_TYPE_DP = 3;

	public DbVendor(){
	}

	public DbVendor(int i) throws CONException { 
		super(new DbVendor()); 
	}

	public DbVendor(String sOid) throws CONException { 
		super(new DbVendor(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbVendor(long lOid) throws CONException { 
		super(new DbVendor(0)); 
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
		return DB_VENDOR;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbVendor().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Vendor vendor = fetchExc(ent.getOID()); 
		ent = (Entity)vendor; 
		return vendor.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Vendor) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Vendor) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Vendor fetchExc(long oid) throws CONException{ 
		try{ 
			Vendor vendor = new Vendor();
			DbVendor dbVendor = new DbVendor(oid); 
			vendor.setOID(oid);

			vendor.setName(dbVendor.getString(COL_NAME));
			vendor.setCode(dbVendor.getString(COL_CODE));
			vendor.setAddress(dbVendor.getString(COL_ADDRESS));
			vendor.setCity(dbVendor.getString(COL_CITY));
			vendor.setState(dbVendor.getString(COL_STATE));
			vendor.setPhone(dbVendor.getString(COL_PHONE));
			vendor.setHp(dbVendor.getString(COL_HP));
			vendor.setFax(dbVendor.getString(COL_FAX));
			vendor.setDueDate(dbVendor.getInt(COL_DUE_DATE));
			vendor.setContact(dbVendor.getString(COL_CONTACT));
			vendor.setCountryName(dbVendor.getString(COL_COUNTRY_NAME));
			vendor.setCountryId(dbVendor.getlong(COL_COUNTRY_ID));
			vendor.setType(dbVendor.getInt(COL_TYPE));
                        
                        vendor.setDiscount(dbVendor.getdouble(COL_DISCOUNT));
                        vendor.setEmail(dbVendor.getString(COL_EMAIL));
                        vendor.setNpwp(dbVendor.getString(COL_NPWP));
                        vendor.setVendorType(dbVendor.getString(COL_VENDOR_TYPE));
                        vendor.setPrevLiability(dbVendor.getdouble(COL_PREV_LIABILITY));
                        vendor.setWebPage(dbVendor.getString(COL_WEB_PAGE));

			return vendor; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendor(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Vendor vendor) throws CONException{ 
		try{ 
			DbVendor dbVendor = new DbVendor(0);

			dbVendor.setString(COL_NAME, vendor.getName());
			dbVendor.setString(COL_CODE, vendor.getCode());
			dbVendor.setString(COL_ADDRESS, vendor.getAddress());
			dbVendor.setString(COL_CITY, vendor.getCity());
			dbVendor.setString(COL_STATE, vendor.getState());
			dbVendor.setString(COL_PHONE, vendor.getPhone());
			dbVendor.setString(COL_HP, vendor.getHp());
			dbVendor.setString(COL_FAX, vendor.getFax());
			dbVendor.setInt(COL_DUE_DATE, vendor.getDueDate());
			dbVendor.setString(COL_CONTACT, vendor.getContact());
			dbVendor.setString(COL_COUNTRY_NAME, vendor.getCountryName());
			dbVendor.setLong(COL_COUNTRY_ID, vendor.getCountryId());
			dbVendor.setInt(COL_TYPE, vendor.getType());
                        
                        dbVendor.setDouble(COL_DISCOUNT, vendor.getDiscount());
                        dbVendor.setString(COL_EMAIL ,vendor.getEmail());
                        dbVendor.setString(COL_NPWP, vendor.getNpwp());
                        dbVendor.setString(COL_VENDOR_TYPE, vendor.getVendorType());
                        dbVendor.setDouble(COL_PREV_LIABILITY, vendor.getPrevLiability());
                        dbVendor.setString(COL_WEB_PAGE, vendor.getWebPage());

			dbVendor.insert(); 
			vendor.setOID(dbVendor.getlong(COL_VENDOR_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendor(0),CONException.UNKNOWN); 
		}
		return vendor.getOID();
	}

	public static long updateExc(Vendor vendor) throws CONException{ 
		try{ 
			if(vendor.getOID() != 0){ 
				DbVendor dbVendor = new DbVendor(vendor.getOID());

				dbVendor.setString(COL_NAME, vendor.getName());
				dbVendor.setString(COL_CODE, vendor.getCode());
				dbVendor.setString(COL_ADDRESS, vendor.getAddress());
				dbVendor.setString(COL_CITY, vendor.getCity());
				dbVendor.setString(COL_STATE, vendor.getState());
				dbVendor.setString(COL_PHONE, vendor.getPhone());
				dbVendor.setString(COL_HP, vendor.getHp());
				dbVendor.setString(COL_FAX, vendor.getFax());
				dbVendor.setInt(COL_DUE_DATE, vendor.getDueDate());
				dbVendor.setString(COL_CONTACT, vendor.getContact());
				dbVendor.setString(COL_COUNTRY_NAME, vendor.getCountryName());
				dbVendor.setLong(COL_COUNTRY_ID, vendor.getCountryId());
				dbVendor.setInt(COL_TYPE, vendor.getType());
                                
                                dbVendor.setDouble(COL_DISCOUNT, vendor.getDiscount());
                                dbVendor.setString(COL_EMAIL ,vendor.getEmail());
                                dbVendor.setString(COL_NPWP, vendor.getNpwp());
                                dbVendor.setString(COL_VENDOR_TYPE, vendor.getVendorType());
                                dbVendor.setDouble(COL_PREV_LIABILITY, vendor.getPrevLiability());
                                dbVendor.setString(COL_WEB_PAGE, vendor.getWebPage());

				dbVendor.update(); 
				return vendor.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendor(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbVendor dbVendor = new DbVendor(oid);
			dbVendor.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbVendor(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_VENDOR; 
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
				Vendor vendor = new Vendor();
				resultToObject(rs, vendor);
				lists.add(vendor);
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

	public static void resultToObject(ResultSet rs, Vendor vendor){
		try{
			vendor.setOID(rs.getLong(DbVendor.colNames[DbVendor.COL_VENDOR_ID]));
			vendor.setName(rs.getString(DbVendor.colNames[DbVendor.COL_NAME]));
			vendor.setCode(rs.getString(DbVendor.colNames[DbVendor.COL_CODE]));
			vendor.setAddress(rs.getString(DbVendor.colNames[DbVendor.COL_ADDRESS]));
			vendor.setCity(rs.getString(DbVendor.colNames[DbVendor.COL_CITY]));
			vendor.setState(rs.getString(DbVendor.colNames[DbVendor.COL_STATE]));
			vendor.setPhone(rs.getString(DbVendor.colNames[DbVendor.COL_PHONE]));
			vendor.setHp(rs.getString(DbVendor.colNames[DbVendor.COL_HP]));
			vendor.setFax(rs.getString(DbVendor.colNames[DbVendor.COL_FAX]));
			vendor.setDueDate(rs.getInt(DbVendor.colNames[DbVendor.COL_DUE_DATE]));
			vendor.setContact(rs.getString(DbVendor.colNames[DbVendor.COL_CONTACT]));
			vendor.setCountryName(rs.getString(DbVendor.colNames[DbVendor.COL_COUNTRY_NAME]));
			vendor.setCountryId(rs.getLong(DbVendor.colNames[DbVendor.COL_COUNTRY_ID]));
			vendor.setType(rs.getInt(DbVendor.colNames[DbVendor.COL_TYPE]));
                        
                        vendor.setDiscount(rs.getDouble(DbVendor.colNames[DbVendor.COL_DISCOUNT]));
                        vendor.setEmail(rs.getString(DbVendor.colNames[DbVendor.COL_EMAIL]));
                        vendor.setNpwp(rs.getString(DbVendor.colNames[DbVendor.COL_NPWP]));
                        vendor.setVendorType(rs.getString(DbVendor.colNames[DbVendor.COL_VENDOR_TYPE]));
                        vendor.setPrevLiability(rs.getDouble(DbVendor.colNames[DbVendor.COL_PREV_LIABILITY]));
                        vendor.setWebPage(rs.getString(DbVendor.colNames[DbVendor.COL_WEB_PAGE]));
                        
		}catch(Exception e){ }
	}

	public static int getCount(String whereClause){
		CONResultSet dbrs = null;
		try {
			String sql = "SELECT COUNT("+ DbVendor.colNames[DbVendor.COL_VENDOR_ID] + ") FROM " + DB_VENDOR;
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
			  	   Vendor vendor = (Vendor)list.get(ls);
				   if(oid == vendor.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
}
