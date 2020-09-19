
package com.project.fms.activity; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*; 
import com.project.*;

public class DbModule extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc { 

	public static final  String DB_MODULE = "module";

	public static final  int COL_MODULE_ID = 0;
	public static final  int COL_PARENT_ID = 1;
	public static final  int COL_CODE = 2;
	public static final  int COL_LEVEL = 3;
	public static final  int COL_DESCRIPTION = 4;
	public static final  int COL_OUTPUT_DELIVER = 5;
	public static final  int COL_PERFORM_INDICATOR = 6;
	public static final  int COL_ASSUM_RISK = 7;
	public static final  int COL_STATUS = 8;
	public static final  int COL_TYPE = 9;
        public static final  int COL_COST_IMPLICATION = 10;
        
        public static final  int COL_INITIAL = 11;
        public static final  int COL_EXPIRED_DATE = 12;
        public static final  int COL_POSITION_LEVEL = 13;
        public static final  int COL_ACTIVITY_PERIOD_ID = 14;

	public static final  String[] colNames = {
		"module_id",
		"parent_id",
		"code",
		"level",
		"description",
		"output_deliver",
		"perform_indicator",
		"assum_risk",
		"status",
		"type",
                "cost_implication",
                "initial",
                "expired_date",
                "position_level",
                "activity_period_id"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
                TYPE_STRING,
                
                TYPE_STRING,
                TYPE_DATE,
                TYPE_STRING,
                TYPE_LONG
	 }; 

	public DbModule(){
	}

	public DbModule(int i) throws CONException { 
		super(new DbModule()); 
	}

	public DbModule(String sOid) throws CONException { 
		super(new DbModule(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbModule(long lOid) throws CONException { 
		super(new DbModule(0)); 
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
		return DB_MODULE;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbModule().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Module module = fetchExc(ent.getOID()); 
		ent = (Entity)module; 
		return module.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Module) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Module) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Module fetchExc(long oid) throws CONException{ 
		try{ 
			Module module = new Module();
			DbModule dbModule = new DbModule(oid); 
			module.setOID(oid);

			module.setParentId(dbModule.getlong(COL_PARENT_ID));
			module.setCode(dbModule.getString(COL_CODE));
			module.setLevel(dbModule.getString(COL_LEVEL));
			module.setDescription(dbModule.getString(COL_DESCRIPTION));
			module.setOutputDeliver(dbModule.getString(COL_OUTPUT_DELIVER));
			module.setPerformIndicator(dbModule.getString(COL_PERFORM_INDICATOR));
			module.setAssumRisk(dbModule.getString(COL_ASSUM_RISK));
			module.setStatus(dbModule.getString(COL_STATUS));
			module.setType(dbModule.getString(COL_TYPE));
                        module.setCostImplication(dbModule.getString(COL_COST_IMPLICATION));
                        
                        module.setInitial(dbModule.getString(COL_INITIAL));
                        module.setExpiredDate(dbModule.getDate(COL_EXPIRED_DATE));
                        module.setPositionLevel(dbModule.getString(COL_POSITION_LEVEL));
                        module.setActivityPeriodId(dbModule.getlong(COL_ACTIVITY_PERIOD_ID));

			return module; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbModule(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Module module) throws CONException{ 
		try{ 
			DbModule dbModule = new DbModule(0);

			dbModule.setLong(COL_PARENT_ID, module.getParentId());
			dbModule.setString(COL_CODE, module.getCode());
			dbModule.setString(COL_LEVEL, module.getLevel());
			dbModule.setString(COL_DESCRIPTION, module.getDescription());
			dbModule.setString(COL_OUTPUT_DELIVER, module.getOutputDeliver());
			dbModule.setString(COL_PERFORM_INDICATOR, module.getPerformIndicator());
			dbModule.setString(COL_ASSUM_RISK, module.getAssumRisk());
			dbModule.setString(COL_STATUS, module.getStatus());
			dbModule.setString(COL_TYPE, module.getType());
                        dbModule.setString(COL_COST_IMPLICATION, module.getCostImplication());
                        
                        dbModule.setString(COL_INITIAL, module.getInitial());
                        dbModule.setDate(COL_EXPIRED_DATE, module.getExpiredDate());
                        dbModule.setString(COL_POSITION_LEVEL, module.getPositionLevel());
                        dbModule.setLong(COL_ACTIVITY_PERIOD_ID, module.getActivityPeriodId());

			dbModule.insert(); 
			module.setOID(dbModule.getlong(COL_MODULE_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbModule(0),CONException.UNKNOWN); 
		}
		return module.getOID();
	}

	public static long updateExc(Module module) throws CONException{ 
		try{ 
			if(module.getOID() != 0){ 
				DbModule dbModule = new DbModule(module.getOID());

				dbModule.setLong(COL_PARENT_ID, module.getParentId());
				dbModule.setString(COL_CODE, module.getCode());
				dbModule.setString(COL_LEVEL, module.getLevel());
				dbModule.setString(COL_DESCRIPTION, module.getDescription());
				dbModule.setString(COL_OUTPUT_DELIVER, module.getOutputDeliver());
				dbModule.setString(COL_PERFORM_INDICATOR, module.getPerformIndicator());
				dbModule.setString(COL_ASSUM_RISK, module.getAssumRisk());
				dbModule.setString(COL_STATUS, module.getStatus());
				dbModule.setString(COL_TYPE, module.getType());
                                dbModule.setString(COL_COST_IMPLICATION, module.getCostImplication());
                                
                                dbModule.setString(COL_INITIAL, module.getInitial());
                                dbModule.setDate(COL_EXPIRED_DATE, module.getExpiredDate());
                                dbModule.setString(COL_POSITION_LEVEL, module.getPositionLevel());
                                dbModule.setLong(COL_ACTIVITY_PERIOD_ID, module.getActivityPeriodId());

				dbModule.update(); 
				return module.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbModule(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbModule dbModule = new DbModule(oid);
			dbModule.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbModule(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_MODULE; 
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
				Module module = new Module();
				resultToObject(rs, module);
				lists.add(module);
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

	private static void resultToObject(ResultSet rs, Module module){
		try{
			module.setOID(rs.getLong(DbModule.colNames[DbModule.COL_MODULE_ID]));
			module.setParentId(rs.getLong(DbModule.colNames[DbModule.COL_PARENT_ID]));
			module.setCode(rs.getString(DbModule.colNames[DbModule.COL_CODE]));
			module.setLevel(rs.getString(DbModule.colNames[DbModule.COL_LEVEL]));
			module.setDescription(rs.getString(DbModule.colNames[DbModule.COL_DESCRIPTION]));
			module.setOutputDeliver(rs.getString(DbModule.colNames[DbModule.COL_OUTPUT_DELIVER]));
			module.setPerformIndicator(rs.getString(DbModule.colNames[DbModule.COL_PERFORM_INDICATOR]));
			module.setAssumRisk(rs.getString(DbModule.colNames[DbModule.COL_ASSUM_RISK]));
			module.setStatus(rs.getString(DbModule.colNames[DbModule.COL_STATUS]));
			module.setType(rs.getString(DbModule.colNames[DbModule.COL_TYPE]));
                        module.setCostImplication(rs.getString(DbModule.colNames[DbModule.COL_COST_IMPLICATION]));
                        
                        module.setInitial(rs.getString(DbModule.colNames[DbModule.COL_INITIAL]));
                        module.setExpiredDate(rs.getDate(DbModule.colNames[DbModule.COL_EXPIRED_DATE]));
                        module.setPositionLevel(rs.getString(DbModule.colNames[DbModule.COL_POSITION_LEVEL]));
                        module.setActivityPeriodId(rs.getLong(DbModule.colNames[DbModule.COL_ACTIVITY_PERIOD_ID]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long moduleId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_MODULE + " WHERE " + 
						DbModule.colNames[DbModule.COL_MODULE_ID] + " = " + moduleId;

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
			String sql = "SELECT COUNT("+ DbModule.colNames[DbModule.COL_MODULE_ID] + ") FROM " + DB_MODULE;
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
			  	   Module module = (Module)list.get(ls);
				   if(oid == module.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static Vector getActivities(long parentId){
            
            Vector result = new Vector();
            
            Vector v = DbModule.list(0,0, "parent_id="+parentId, "code");
            
            if(v!=null && v.size()>0){
                for(int i=0; i<v.size(); i++){
                    Module m = (Module)v.get(i);
                    result.add(m);
                    Vector vx = DbModule.list(0,0, "parent_id="+m.getOID(), "code");
                    if(vx!=null && vx.size()>0){
                        for(int x=0; x<vx.size(); x++){
                            Module mx = (Module)vx.get(x);
                            result.add(mx);
                            if(mx.getLevel().equals(I_Project.ACTIVITY_CODE_A)){
                                Vector vxx = DbModule.list(0,0, "parent_id="+mx.getOID(), "code");
                                if(vxx!=null && vxx.size()>0){
                                    result.addAll(vxx);
                                }
                            }
                        }
                        
                    }
                }            
            }
            
            return result;
        }
}
