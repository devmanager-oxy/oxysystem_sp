

package com.project.payroll; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.project.main.db.*;
import com.project.main.entity.*;

import com.project.payroll.*; 

public class DbDepartment extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc { 

	public static final  String DB_DEPARTMET = "department";

	public static final  int COL_DEPARTMENT_ID  = 0;
	public static final  int COL_NAME           = 1;
	public static final  int COL_DESCRIPTION    = 2;
        public static final  int COL_CODE           = 3;
        public static final  int COL_LEVEL          = 4;
        public static final  int COL_REF_ID         = 5;
        public static final  int COL_TYPE           = 6;

	public static final  String[] colNames = {
		"department_id",
		"name",
		"description",
                "code",
                "level",
                "ref_id",
                "type"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_STRING,
		TYPE_STRING,
                TYPE_STRING,
                TYPE_INT,
                TYPE_LONG,
                TYPE_STRING
	 }; 
         
         
         public static int LEVEL_DEPARTMENT = 0;
         public static int LEVEL_SECTION = 1;
         public static int LEVEL_SUB_SECTION = 2;
         public static int LEVEL_JOB = 3;
         
         public static String[] strLevel = {"Department", "Section", "Sub Section", "Job"};

	public DbDepartment(){
	}

	public DbDepartment(int i) throws CONException { 
		super(new DbDepartment()); 
	}

	public DbDepartment(String sOid) throws CONException { 
		super(new DbDepartment(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbDepartment(long lOid) throws CONException { 
		super(new DbDepartment(0)); 
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
		return DB_DEPARTMET;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbDepartment().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Department department = fetchExc(ent.getOID()); 
		ent = (Entity)department; 
		return department.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Department) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Department) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Department fetchExc(long oid) throws CONException{ 
		try{ 
			Department department = new Department();
			DbDepartment pstDepartment = new DbDepartment(oid); 
			department.setOID(oid);

			department.setName(pstDepartment.getString(COL_NAME));
			department.setDescription(pstDepartment.getString(COL_DESCRIPTION));
                        
                        department.setCode(pstDepartment.getString(COL_CODE));
                        department.setLevel(pstDepartment.getInt(COL_LEVEL));
                        department.setRefId(pstDepartment.getlong(COL_REF_ID));
                        
                        department.setType(pstDepartment.getString(COL_TYPE));

			return department; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbDepartment(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Department department) throws CONException{ 
		try{ 
			DbDepartment pstDepartment = new DbDepartment(0);

			pstDepartment.setString(COL_NAME, department.getName());
			pstDepartment.setString(COL_DESCRIPTION, department.getDescription());
                        
                        pstDepartment.setString(COL_CODE, department.getCode());
                        pstDepartment.setInt(COL_LEVEL, department.getLevel());
                        pstDepartment.setLong(COL_REF_ID, department.getRefId());
                        
                        pstDepartment.setString(COL_TYPE, department.getType());

			pstDepartment.insert(); 
			department.setOID(pstDepartment.getlong(COL_DEPARTMENT_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbDepartment(0),CONException.UNKNOWN); 
		}
		return department.getOID();
	}

	public static long updateExc(Department department) throws CONException{ 
		try{ 
			if(department.getOID() != 0){ 
				DbDepartment pstDepartment = new DbDepartment(department.getOID());

				pstDepartment.setString(COL_NAME, department.getName());
				pstDepartment.setString(COL_DESCRIPTION, department.getDescription());
                                
                                pstDepartment.setString(COL_CODE, department.getCode());
                                pstDepartment.setInt(COL_LEVEL, department.getLevel());
                                pstDepartment.setLong(COL_REF_ID, department.getRefId());
                                
                                pstDepartment.setString(COL_TYPE, department.getType());

				pstDepartment.update(); 
				return department.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbDepartment(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbDepartment pstDepartment = new DbDepartment(oid);
			pstDepartment.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbDepartment(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_DEPARTMET; 
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
				Department department = new Department();
				resultToObject(rs, department);
				lists.add(department);
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

	private static void resultToObject(ResultSet rs, Department department){
		try{
			department.setOID(rs.getLong(DbDepartment.colNames[DbDepartment.COL_DEPARTMENT_ID]));
			department.setName(rs.getString(DbDepartment.colNames[DbDepartment.COL_NAME]));
			department.setDescription(rs.getString(DbDepartment.colNames[DbDepartment.COL_DESCRIPTION]));
                        
                        department.setCode(rs.getString(DbDepartment.colNames[DbDepartment.COL_CODE]));
                        department.setLevel(rs.getInt(DbDepartment.colNames[DbDepartment.COL_LEVEL]));
                        department.setRefId(rs.getLong(DbDepartment.colNames[DbDepartment.COL_REF_ID]));
                        department.setType(rs.getString(DbDepartment.colNames[DbDepartment.COL_TYPE]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long departmentId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_DEPARTMET + " WHERE " + 
						DbDepartment.colNames[DbDepartment.COL_DEPARTMENT_ID] + " = " + departmentId;

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
			String sql = "SELECT COUNT("+ DbDepartment.colNames[DbDepartment.COL_DEPARTMENT_ID] + ") FROM " + DB_DEPARTMET;
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
			  	   Department department = (Department)list.get(ls);
				   if(oid == department.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
}
