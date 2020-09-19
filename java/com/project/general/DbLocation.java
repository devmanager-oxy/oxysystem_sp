
package com.project.general; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.util.lang.I_Language;
import com.project.general.*; 
public class DbLocation extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language { 

	public static final  String DB_LOCATION = "pos_location";

	public static final  int COL_LOCATION_ID = 0;
	public static final  int COL_TYPE = 1;
	public static final  int COL_NAME = 2;
	public static final  int COL_ADDRESS_STREET = 3;
	public static final  int COL_ADDRESS_COUNTRY = 4;
	public static final  int COL_ADDRESS_CITY = 5;
	public static final  int COL_TELP = 6;
	public static final  int COL_PIC = 7;
        
        public static final  int COL_CODE = 8;
        public static final  int COL_DESCRIPTION = 9;

	public static final  String[] colNames = {
		"location_id",
		"type",
		"name",
		"address_street",
		"address_country",
		"address_city",
		"telp",
		"pic",
                "code",
                "description"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
                TYPE_STRING,
                TYPE_STRING
	 }; 
         
        public static final String TYPE_OFFICE = "Office"; 
        public static final String TYPE_WAREHOUSE = "Warehouse"; 
        public static final String TYPE_STORE_ROOM = "Store Room"; 
        public static final String TYPE_STORE = "Store"; 
        
        public static String[] strLocTypes = {TYPE_OFFICE, TYPE_WAREHOUSE, TYPE_STORE_ROOM, TYPE_STORE};
         

	public DbLocation(){
	}

	public DbLocation(int i) throws CONException { 
		super(new DbLocation()); 
	}

	public DbLocation(String sOid) throws CONException { 
		super(new DbLocation(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbLocation(long lOid) throws CONException { 
		super(new DbLocation(0)); 
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
		return DB_LOCATION;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbLocation().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Location location = fetchExc(ent.getOID()); 
		ent = (Entity)location; 
		return location.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Location) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Location) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Location fetchExc(long oid) throws CONException{ 
		try{ 
			Location location = new Location();
			DbLocation pstLocation = new DbLocation(oid); 
			location.setOID(oid);

			location.setType(pstLocation.getString(COL_TYPE));
			location.setName(pstLocation.getString(COL_NAME));
			location.setAddressStreet(pstLocation.getString(COL_ADDRESS_STREET));
			location.setAddressCountry(pstLocation.getString(COL_ADDRESS_COUNTRY));
			location.setAddressCity(pstLocation.getString(COL_ADDRESS_CITY));
			location.setTelp(pstLocation.getString(COL_TELP));
			location.setPic(pstLocation.getString(COL_PIC));
                        location.setCode(pstLocation.getString(COL_CODE));
                        location.setDescription(pstLocation.getString(COL_DESCRIPTION));

			return location; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbLocation(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Location location) throws CONException{ 
		try{ 
			DbLocation pstLocation = new DbLocation(0);

			pstLocation.setString(COL_TYPE, location.getType());
			pstLocation.setString(COL_NAME, location.getName());
			pstLocation.setString(COL_ADDRESS_STREET, location.getAddressStreet());
			pstLocation.setString(COL_ADDRESS_COUNTRY, location.getAddressCountry());
			pstLocation.setString(COL_ADDRESS_CITY, location.getAddressCity());
			pstLocation.setString(COL_TELP, location.getTelp());
			pstLocation.setString(COL_PIC, location.getPic());
                        pstLocation.setString(COL_CODE, location.getCode());
                        pstLocation.setString(COL_DESCRIPTION, location.getDescription());

			pstLocation.insert(); 
			location.setOID(pstLocation.getlong(COL_LOCATION_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbLocation(0),CONException.UNKNOWN); 
		}
		return location.getOID();
	}

	public static long updateExc(Location location) throws CONException{ 
		try{ 
			if(location.getOID() != 0){ 
				DbLocation pstLocation = new DbLocation(location.getOID());

				pstLocation.setString(COL_TYPE, location.getType());
				pstLocation.setString(COL_NAME, location.getName());
				pstLocation.setString(COL_ADDRESS_STREET, location.getAddressStreet());
				pstLocation.setString(COL_ADDRESS_COUNTRY, location.getAddressCountry());
				pstLocation.setString(COL_ADDRESS_CITY, location.getAddressCity());
				pstLocation.setString(COL_TELP, location.getTelp());
				pstLocation.setString(COL_PIC, location.getPic());
                                pstLocation.setString(COL_CODE, location.getCode());
                                pstLocation.setString(COL_DESCRIPTION, location.getDescription());

				pstLocation.update(); 
				return location.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbLocation(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbLocation pstLocation = new DbLocation(oid);
			pstLocation.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbLocation(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_LOCATION; 
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
				Location location = new Location();
				resultToObject(rs, location);
				lists.add(location);
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

	public static void resultToObject(ResultSet rs, Location location){
		try{
			location.setOID(rs.getLong(DbLocation.colNames[DbLocation.COL_LOCATION_ID]));
			location.setType(rs.getString(DbLocation.colNames[DbLocation.COL_TYPE]));
			location.setName(rs.getString(DbLocation.colNames[DbLocation.COL_NAME]));
			location.setAddressStreet(rs.getString(DbLocation.colNames[DbLocation.COL_ADDRESS_STREET]));
			location.setAddressCountry(rs.getString(DbLocation.colNames[DbLocation.COL_ADDRESS_COUNTRY]));
			location.setAddressCity(rs.getString(DbLocation.colNames[DbLocation.COL_ADDRESS_CITY]));
			location.setTelp(rs.getString(DbLocation.colNames[DbLocation.COL_TELP]));
			location.setPic(rs.getString(DbLocation.colNames[DbLocation.COL_PIC]));
                        location.setCode(rs.getString(DbLocation.colNames[DbLocation.COL_CODE]));
                        location.setDescription(rs.getString(DbLocation.colNames[DbLocation.COL_DESCRIPTION]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long locationId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_LOCATION + " WHERE " + 
						DbLocation.colNames[DbLocation.COL_LOCATION_ID] + " = " + locationId;

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
			String sql = "SELECT COUNT("+ DbLocation.colNames[DbLocation.COL_LOCATION_ID] + ") FROM " + DB_LOCATION;
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
			  	   Location location = (Location)list.get(ls);
				   if(oid == location.getOID())
					  found=true; 
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        
        public static Location getLocationById(long oidLocation){
            if(oidLocation!=0){
                try{
                    return fetchExc(oidLocation);
                }
                catch(Exception e){
                    
                }
            }
            
            return new Location();
        }
}
