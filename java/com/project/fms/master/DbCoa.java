
package com.project.fms.master; 

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.fms.master.*;
import com.project.general.*;
import com.project.system.*; 
import com.project.fms.transaction.*; 
import com.project.I_Project;
import com.project.util.*;

public class DbCoa extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc { 

	public static final  String DB_COA = "coa";

	public static final  int COL_COA_ID         = 0;
	public static final  int COL_ACC_REF_ID     = 1;
	public static final  int COL_DEPARTMENT_ID  = 2;
	public static final  int COL_SECTION_ID     = 3;
	public static final  int COL_ACCOUNT_GROUP  = 4;
	public static final  int COL_CODE           = 5;
	public static final  int COL_NAME           = 6;
	public static final  int COL_LEVEL          = 7;
	public static final  int COL_SALDO_NORMAL   = 8;
	public static final  int COL_STATUS         = 9;
	public static final  int COL_DEPARTMENT_NAME = 10;
	public static final  int COL_SECTION_NAME   = 11;
	public static final  int COL_USER_ID        = 12;
        public static final  int COL_REG_DATE       = 13;
        public static final  int COL_OPENING_BALANCE = 14;
        public static final  int COL_LOCATION_ID    = 15;
        public static final  int COL_DEPARTMENTAL_COA    = 16;
        public static final  int COL_COA_CATEGORY_ID    = 17;
        public static final  int COL_COA_GROUP_ALIAS_ID    = 18;
        
        public static final  int COL_IS_NEED_EXTRA    = 19;
        public static final  int COL_DEBET_PREFIX_CODE    = 20;
        public static final  int COL_CREDIT_PREFIX_CODE    = 21;
        public static final  int COL_COMPANY_ID    = 22;
        
        public static final  int COL_ACCOUNT_CLASS    = 23;
        
        
	public static final  String[] colNames = {
		"coa_id",
		"acc_ref_id",
		"department_id",
		"section_id",
		"account_group",
		"code",
		"name",
		"level",
		"saldo_normal",
		"status",
		"department_name",
		"section_name",
		"user_id",
                "reg_date",
                "opening_balance",
                "location_id",
                "departmental_coa",
                "coa_category_id",
                "coa_group_alias_id",
                "is_need_extra",
                "debet_prefix_code",
                "credit_prefix_code",
                "company_id",
                "account_class"
	 }; 

	public static final  int[] fieldTypes = {
		TYPE_LONG + TYPE_PK + TYPE_ID,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_LONG,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_INT,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_STRING,
		TYPE_LONG,
                TYPE_DATE,
                TYPE_FLOAT,
                TYPE_LONG,
                TYPE_INT,
                TYPE_LONG,
                TYPE_LONG,
                TYPE_INT,
                TYPE_STRING,
                TYPE_STRING,
                TYPE_LONG,
                TYPE_INT
	 }; 
         
         
         public static final int ACCOUNT_CLASS_GENERAL = 0;
         public static final int ACCOUNT_CLASS_NONSP = 1;
         public static final int ACCOUNT_CLASS_SP = 2;
         
         public static final String[] accClassStr = {"-", "Non SP", "SP"};

	public DbCoa(){
	}

	public DbCoa(int i) throws CONException { 
		super(new DbCoa()); 
	}

	public DbCoa(String sOid) throws CONException { 
		super(new DbCoa(0)); 
		if(!locate(sOid)) 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		else 
			return; 
	}

	public DbCoa(long lOid) throws CONException { 
		super(new DbCoa(0)); 
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
		return DB_COA;
	}

	public String[] getFieldNames(){ 
		return colNames; 
	}

	public int[] getFieldTypes(){ 
		return fieldTypes; 
	}

	public String getPersistentName(){ 
		return new DbCoa().getClass().getName(); 
	}

	public long fetchExc(Entity ent) throws Exception{ 
		Coa coa = fetchExc(ent.getOID()); 
		ent = (Entity)coa; 
		return coa.getOID(); 
	}

	public long insertExc(Entity ent) throws Exception{ 
		return insertExc((Coa) ent); 
	}

	public long updateExc(Entity ent) throws Exception{ 
		return updateExc((Coa) ent); 
	}

	public long deleteExc(Entity ent) throws Exception{ 
		if(ent==null){ 
			throw new CONException(this,CONException.RECORD_NOT_FOUND); 
		} 
		return deleteExc(ent.getOID()); 
	}

	public static Coa fetchExc(long oid) throws CONException{ 
		try{ 
			Coa coa = new Coa();
			DbCoa dbCoa = new DbCoa(oid); 
			coa.setOID(oid);

			coa.setAccRefId(dbCoa.getlong(COL_ACC_REF_ID));
			coa.setDepartmentId(dbCoa.getlong(COL_DEPARTMENT_ID));
			coa.setSectionId(dbCoa.getlong(COL_SECTION_ID));
			coa.setAccountGroup(dbCoa.getString(COL_ACCOUNT_GROUP));
			coa.setCode(dbCoa.getString(COL_CODE));
			coa.setName(dbCoa.getString(COL_NAME));
			coa.setLevel(dbCoa.getInt(COL_LEVEL));
			coa.setSaldoNormal(dbCoa.getString(COL_SALDO_NORMAL));
			coa.setStatus(dbCoa.getString(COL_STATUS));
			coa.setDepartmentName(dbCoa.getString(COL_DEPARTMENT_NAME));
			coa.setSectionName(dbCoa.getString(COL_SECTION_NAME));
			coa.setUserId(dbCoa.getlong(COL_USER_ID));
                        coa.setRegDate(dbCoa.getDate(COL_REG_DATE));
                        coa.setOpeningBalance(dbCoa.getdouble(COL_OPENING_BALANCE));
                        coa.setLocationId(dbCoa.getlong(COL_LOCATION_ID));
                        
                        coa.setDepartmentalCoa(dbCoa.getInt(COL_DEPARTMENTAL_COA));
                        
                        coa.setCoaCategoryId(dbCoa.getlong(COL_COA_CATEGORY_ID));
                        coa.setCoaGroupAliasId(dbCoa.getlong(COL_COA_GROUP_ALIAS_ID));
                        
                        coa.setIsNeedExtra(dbCoa.getInt(COL_IS_NEED_EXTRA));
                        coa.setDebetPrefixCode(dbCoa.getString(COL_DEBET_PREFIX_CODE));
                        coa.setCreditPrefixCode(dbCoa.getString(COL_CREDIT_PREFIX_CODE));
                        coa.setCompanyId(dbCoa.getlong(COL_COMPANY_ID));

                        coa.setAccountClass(dbCoa.getInt(COL_ACCOUNT_CLASS));
                        
			return coa; 
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoa(0),CONException.UNKNOWN); 
		} 
	}

	public static long insertExc(Coa coa) throws CONException{ 
		try{ 
			DbCoa dbCoa = new DbCoa(0);

			dbCoa.setLong(COL_ACC_REF_ID, coa.getAccRefId());
			dbCoa.setLong(COL_DEPARTMENT_ID, coa.getDepartmentId());
			dbCoa.setLong(COL_SECTION_ID, coa.getSectionId());
			dbCoa.setString(COL_ACCOUNT_GROUP, coa.getAccountGroup());
			dbCoa.setString(COL_CODE, coa.getCode());
			dbCoa.setString(COL_NAME, coa.getName());
			dbCoa.setInt(COL_LEVEL, coa.getLevel());
			dbCoa.setString(COL_SALDO_NORMAL, coa.getSaldoNormal());
			dbCoa.setString(COL_STATUS, coa.getStatus());
			dbCoa.setString(COL_DEPARTMENT_NAME, coa.getDepartmentName());
			dbCoa.setString(COL_SECTION_NAME, coa.getSectionName());
			dbCoa.setLong(COL_USER_ID, coa.getUserId());
                        dbCoa.setDate(COL_REG_DATE, coa.getRegDate());
                        dbCoa.setDouble(COL_OPENING_BALANCE, coa.getOpeningBalance());
                        dbCoa.setLong(COL_LOCATION_ID, coa.getLocationId());           
                        
                        dbCoa.setInt(COL_DEPARTMENTAL_COA, coa.getDepartmentalCoa());
                        
                        dbCoa.setLong(COL_COA_CATEGORY_ID,coa.getCoaCategoryId());
                        dbCoa.setLong(COL_COA_GROUP_ALIAS_ID,coa.getCoaGroupAliasId());
                        
                        dbCoa.setInt(COL_IS_NEED_EXTRA,coa.getIsNeedExtra());
                        dbCoa.setString(COL_DEBET_PREFIX_CODE,coa.getDebetPrefixCode());
                        dbCoa.setString(COL_CREDIT_PREFIX_CODE,coa.getCreditPrefixCode());
                        dbCoa.setLong(COL_COMPANY_ID,coa.getCompanyId());
                        
			dbCoa.setInt(COL_ACCOUNT_CLASS,coa.getAccountClass());
                        
                        dbCoa.insert(); 
			coa.setOID(dbCoa.getlong(COL_COA_ID));
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoa(0),CONException.UNKNOWN); 
		}
		return coa.getOID();
	}

	public static long updateExc(Coa coa) throws CONException{ 
		try{ 
			if(coa.getOID() != 0){ 
				DbCoa dbCoa = new DbCoa(coa.getOID());

				dbCoa.setLong(COL_ACC_REF_ID, coa.getAccRefId());
				dbCoa.setLong(COL_DEPARTMENT_ID, coa.getDepartmentId());
				dbCoa.setLong(COL_SECTION_ID, coa.getSectionId());
				dbCoa.setString(COL_ACCOUNT_GROUP, coa.getAccountGroup());
				dbCoa.setString(COL_CODE, coa.getCode());
				dbCoa.setString(COL_NAME, coa.getName());
				dbCoa.setInt(COL_LEVEL, coa.getLevel());
				dbCoa.setString(COL_SALDO_NORMAL, coa.getSaldoNormal());
				dbCoa.setString(COL_STATUS, coa.getStatus());
				dbCoa.setString(COL_DEPARTMENT_NAME, coa.getDepartmentName());
				dbCoa.setString(COL_SECTION_NAME, coa.getSectionName());
				dbCoa.setLong(COL_USER_ID, coa.getUserId());
                                dbCoa.setDate(COL_REG_DATE, coa.getRegDate());
                                dbCoa.setDouble(COL_OPENING_BALANCE, coa.getOpeningBalance());
                                dbCoa.setLong(COL_LOCATION_ID, coa.getLocationId());
                                
                                dbCoa.setInt(COL_DEPARTMENTAL_COA, coa.getDepartmentalCoa());
                                
                                dbCoa.setLong(COL_COA_CATEGORY_ID, coa.getCoaCategoryId());
                                dbCoa.setLong(COL_COA_GROUP_ALIAS_ID,coa.getCoaGroupAliasId());
                                
                                dbCoa.setInt(COL_IS_NEED_EXTRA,coa.getIsNeedExtra());
                                dbCoa.setString(COL_DEBET_PREFIX_CODE,coa.getDebetPrefixCode());
                                dbCoa.setString(COL_CREDIT_PREFIX_CODE,coa.getCreditPrefixCode());
                                dbCoa.setLong(COL_COMPANY_ID,coa.getCompanyId());
                                
                                dbCoa.setInt(COL_ACCOUNT_CLASS,coa.getAccountClass());
                                
				dbCoa.update(); 
				return coa.getOID();

			}
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoa(0),CONException.UNKNOWN); 
		}
		return 0;
	}

	public static long deleteExc(long oid) throws CONException{ 
		try{ 
			DbCoa dbCoa = new DbCoa(oid);
			dbCoa.delete();
		}catch(CONException dbe){ 
			throw dbe; 
		}catch(Exception e){ 
			throw new CONException(new DbCoa(0),CONException.UNKNOWN); 
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
			String sql = "SELECT * FROM " + DB_COA; 
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
				Coa coa = new Coa();
				resultToObject(rs, coa);
				lists.add(coa);
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

	public static void resultToObject(ResultSet rs, Coa coa){
		try{
			coa.setOID(rs.getLong(DbCoa.colNames[DbCoa.COL_COA_ID]));
			coa.setAccRefId(rs.getLong(DbCoa.colNames[DbCoa.COL_ACC_REF_ID]));
			coa.setDepartmentId(rs.getLong(DbCoa.colNames[DbCoa.COL_DEPARTMENT_ID]));
			coa.setSectionId(rs.getLong(DbCoa.colNames[DbCoa.COL_SECTION_ID]));
			coa.setAccountGroup(rs.getString(DbCoa.colNames[DbCoa.COL_ACCOUNT_GROUP]));
			coa.setCode(rs.getString(DbCoa.colNames[DbCoa.COL_CODE]));
			coa.setName(rs.getString(DbCoa.colNames[DbCoa.COL_NAME]));
			coa.setLevel(rs.getInt(DbCoa.colNames[DbCoa.COL_LEVEL]));
			coa.setSaldoNormal(rs.getString(DbCoa.colNames[DbCoa.COL_SALDO_NORMAL]));
			coa.setStatus(rs.getString(DbCoa.colNames[DbCoa.COL_STATUS]));
			coa.setDepartmentName(rs.getString(DbCoa.colNames[DbCoa.COL_DEPARTMENT_NAME]));
			coa.setSectionName(rs.getString(DbCoa.colNames[DbCoa.COL_SECTION_NAME]));
			coa.setUserId(rs.getLong(DbCoa.colNames[DbCoa.COL_USER_ID]));
                        coa.setRegDate(rs.getDate(DbCoa.colNames[DbCoa.COL_REG_DATE]));
                        coa.setOpeningBalance(rs.getDouble(DbCoa.colNames[DbCoa.COL_OPENING_BALANCE]));
                        coa.setLocationId(rs.getLong(DbCoa.colNames[DbCoa.COL_LOCATION_ID]));
                        
                        coa.setDepartmentalCoa(rs.getInt(DbCoa.colNames[DbCoa.COL_DEPARTMENTAL_COA]));
                        
                        coa.setCoaCategoryId(rs.getLong(DbCoa.colNames[DbCoa.COL_COA_CATEGORY_ID]));
                        coa.setCoaGroupAliasId(rs.getLong(DbCoa.colNames[DbCoa.COL_COA_GROUP_ALIAS_ID])); 
                        
                        coa.setIsNeedExtra(rs.getInt(DbCoa.colNames[DbCoa.COL_IS_NEED_EXTRA]));
                        coa.setDebetPrefixCode(rs.getString(DbCoa.colNames[DbCoa.COL_DEBET_PREFIX_CODE]));
                        coa.setCreditPrefixCode(rs.getString(DbCoa.colNames[DbCoa.COL_CREDIT_PREFIX_CODE]));
                        coa.setCompanyId(rs.getLong(DbCoa.colNames[DbCoa.COL_COMPANY_ID]));
                        
                        coa.setAccountClass(rs.getInt(DbCoa.colNames[DbCoa.COL_ACCOUNT_CLASS]));

		}catch(Exception e){ }
	}

	public static boolean checkOID(long coaId){
		CONResultSet dbrs = null;
		boolean result = false;
		try{
			String sql = "SELECT * FROM " + DB_COA + " WHERE " + 
						DbCoa.colNames[DbCoa.COL_COA_ID] + " = " + coaId;

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
			String sql = "SELECT COUNT("+ DbCoa.colNames[DbCoa.COL_COA_ID] + ") FROM " + DB_COA;
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
			  	   Coa coa = (Coa)list.get(ls);
				   if(oid == coa.getOID())
					  found=true;
			  }
		  }
		}
		if((start >= size) && (size > 0))
		    start = start - recordToGet;

		return start;
	}
        
        public static void updateLocation(long coaId, long locationId){
            if(coaId!=0){
                try{
                    Coa coa = DbCoa.fetchExc(coaId);
                    coa.setLocationId(locationId);
                    DbCoa.updateExc(coa);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
            }
        }
        
        public static double getCoaBalanceByGroup(String coaGroup, String type){
            double result = 0;                      
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"account_group='"+coaGroup+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    if(type.equals("CD"))
                        result = result+DbCoa.getCoaBalanceCD(coa.getOID());
                    else
                        result = result+DbCoa.getCoaBalance(coa.getOID());
                }
            }
            
            return result;
        } 
        
        
        public static double getCoaBalanceByGroupPrevYear(String coaGroup, String type){
            double result = 0;                      
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"account_group='"+coaGroup+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    if(type.equals("CD"))
                        result = result+DbCoa.getCoaBalanceCDPrevYear(coa.getOID());
                    else
                        result = result+DbCoa.getCoaBalancePrevYear(coa.getOID());
                }
            }
            
            return result;
        } 
        
        public static double getCoaBalanceByGroup(String coaGroup, String type, Vector periods){
            double result = 0;                      
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"account_group='"+coaGroup+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    if(type.equals("CD"))
                        result = result+DbCoa.getCoaBalanceCD(coa.getOID(), periods);
                    else
                        result = result+DbCoa.getCoaBalance(coa.getOID(), periods);
                }
            }
            
            return result;
        } 
        
        public static double getCoaOpeningBalanceByGroup(String coaGroup, String type){
            double result = 0;                      
            Periode p = DbPeriode.getOpenPeriod();
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"account_group='"+coaGroup+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);                    
                    result = result+DbCoaOpeningBalance.getOpeningBalance(p,coa.getOID());
                }
            }
            
            return result;
        }           
        
        public static double getCoaBalanceByHeader(long coaId, String type){
            
            //System.out.println("\n\n===== start sum amount ===============");
            
            double result = 0;  
            double amount = 0;
            
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            
            listCoa = DbCoa.list(0,0,"acc_ref_id='"+coaId+"'","code");
            
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    
                    //System.out.println("i = "+i+", coa : "+coa.getOID()+", status : "+coa.getStatus());
                    //jika bukan header lakukan penghitungan
                    if(!coa.getStatus().equals("HEADER")){
                       
                        System.out.println("status not header");
                        
                        if(type.equals("CD"))
                        {
                            amount = DbCoa.getCoaBalanceCD(coa.getOID());
                            
                            //Retained Earnings
                            if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")))
                            {
                                double totalIncome = 0;								
                                Coa coax = new Coa();
                                for(int ix=0; ix<listCoa.size(); ix++)
                                {											
                                    coax= (Coa)listCoa.get(ix);																			
                                    if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE))
                                    {
                                            totalIncome = totalIncome + DbCoa.getCoaBalanceCD(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES))
                                    {
                                            totalIncome = totalIncome + DbCoa.getCoaBalance(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE))
                                    {
                                            totalIncome = totalIncome - DbCoa.getCoaBalance(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE))
                                    {
                                            totalIncome = totalIncome + DbCoa.getCoaBalanceCD(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                                    {
                                            totalIncome = totalIncome - DbCoa.getCoaBalance(coax.getOID());	
                                    }
                                }									
                                
                                amount = totalIncome;
                                
                                //amount = totalIncome + DbCoaOpeningBalance.getSumOpeningBalance();
                            }
                            // Bagining Balance                        
                            //if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_BEGINING_BALANCE"))){
                            //    amount = DbCoaOpeningBalance.getSumOpeningBalance();
                            //}

                            result = result + amount;
                        }
                        else{
                            result = result + DbCoa.getCoaBalance(coa.getOID());
                        }
                    }
                }
            }
            
            return result;
        }
        
        
        public static double getCoaBalanceByHeader(long coaId, String type, int accClass){
            
            //System.out.println("\n\n===== start sum amount ===============");
            
            double result = 0;  
            double amount = 0;
            
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            
            listCoa = DbCoa.list(0,0,"acc_ref_id='"+coaId+"'","code");
            
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    
                    boolean ok = false;
                    
                    if(accClass==DbCoa.ACCOUNT_CLASS_SP){
                        if(coa.getAccountClass()==accClass){                    
                            ok = true;
                        }
                    }
                    else{
                        if(coa.getAccountClass()!=DbCoa.ACCOUNT_CLASS_SP){
                            ok = true;
                        }
                    }
                    
                    if(ok){
                    
                    //System.out.println("i = "+i+", coa : "+coa.getOID()+", status : "+coa.getStatus());
                    //jika bukan header lakukan penghitungan
                        if(!coa.getStatus().equals("HEADER")){

                            //System.out.println("status not header");

                            if(type.equals("CD"))
                            {
                                amount = DbCoa.getCoaBalanceCD(coa.getOID());
                                //Retained Earnings
                                if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")))
                                {
                                    double totalIncome = 0;								
                                    Coa coax = new Coa();
                                    for(int ix=0; ix<listCoa.size(); ix++)
                                    {											
                                        coax= (Coa)listCoa.get(ix);																			
                                        if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE))
                                        {
                                                totalIncome = totalIncome + DbCoa.getCoaBalanceCD(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES))
                                        {
                                                totalIncome = totalIncome + DbCoa.getCoaBalance(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE))
                                        {
                                                totalIncome = totalIncome - DbCoa.getCoaBalance(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE))
                                        {
                                                totalIncome = totalIncome + DbCoa.getCoaBalanceCD(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                                        {
                                                totalIncome = totalIncome - DbCoa.getCoaBalance(coax.getOID());	
                                        }
                                    }									
                                    amount = totalIncome;
                                }
                                // Bagining Balance                        
                                if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_BEGINING_BALANCE"))){
                                    amount = DbCoaOpeningBalance.getSumOpeningBalance();
                                }

                                result = result + amount;
                            }
                            else{
                                result = result + DbCoa.getCoaBalance(coa.getOID());
                            }
                        }
                    }
                }
            }
            
            return result;
        }
        
        public static double getCoaBalanceByHeaderPrevYear(long coaId, String type){
            
            //System.out.println("\n\n=============> in prev year ====================");
            
            double result = 0;  
            double amount = 0;
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            
            listCoa = DbCoa.list(0,0,"acc_ref_id='"+coaId+"'","code");
            
            if(listCoa!=null && listCoa.size()>0){
                
                for(int i=0; i<listCoa.size(); i++){
                    
                    coa = (Coa)listCoa.get(i);
                    
                    if(!coa.getStatus().equals("HEADER")){
                    
                        if(type.equals("CD"))
                        {
                            amount = DbCoa.getCoaBalanceCDPrevYear(coa.getOID());
                            //Retained Earnings
                            if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")))
                            {
                                double totalIncome = 0;								
                                Coa coax = new Coa();
                                for(int ix=0; ix<listCoa.size(); ix++)
                                {											
                                    coax= (Coa)listCoa.get(ix);																			
                                    if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE))
                                    {
                                            totalIncome = totalIncome + DbCoa.getCoaBalanceCDPrevYear(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES))
                                    {
                                            totalIncome = totalIncome + DbCoa.getCoaBalancePrevYear(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE))
                                    {
                                            totalIncome = totalIncome - DbCoa.getCoaBalancePrevYear(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE))
                                    {
                                            totalIncome = totalIncome + DbCoa.getCoaBalanceCDPrevYear(coax.getOID());	
                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                                    {
                                            totalIncome = totalIncome - DbCoa.getCoaBalancePrevYear(coax.getOID());	
                                    }
                                }									
                                amount = totalIncome;
                            }
                            // Bagining Balance                        
                            if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_BEGINING_BALANCE"))){
                                amount = DbCoaOpeningBalance.getSumOpeningBalancePrevYear();
                            }

                            result = result + amount;
                        }
                        else{
                            result = result + DbCoa.getCoaBalancePrevYear(coa.getOID());
                        }
                    }
                    
                }
            }
            
            return result;
        }
        
        public static double getCoaBalanceByHeaderPrevYear(long coaId, String type, int accClass){
            
            //System.out.println("\n\n=============> in prev year ====================");
            
            double result = 0;  
            double amount = 0;
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"acc_ref_id='"+coaId+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    
                    boolean ok = false;
                    
                    if(accClass==DbCoa.ACCOUNT_CLASS_SP){
                        if(coa.getAccountClass()==accClass){                    
                            ok = true;
                        }
                    }
                    else{
                        if(coa.getAccountClass()!=DbCoa.ACCOUNT_CLASS_SP){
                            ok = true;
                        }
                    }
                    
                    if(ok){
                    
                        if(!coa.getStatus().equals("HEADER")){

                            if(type.equals("CD"))
                            {
                                amount = DbCoa.getCoaBalanceCDPrevYear(coa.getOID());
                                //Retained Earnings
                                if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")))
                                {
                                    double totalIncome = 0;								
                                    Coa coax = new Coa();
                                    for(int ix=0; ix<listCoa.size(); ix++)
                                    {											
                                        coax= (Coa)listCoa.get(ix);																			
                                        if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE))
                                        {
                                                totalIncome = totalIncome + DbCoa.getCoaBalanceCDPrevYear(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES))
                                        {
                                                totalIncome = totalIncome + DbCoa.getCoaBalancePrevYear(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE))
                                        {
                                                totalIncome = totalIncome - DbCoa.getCoaBalancePrevYear(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE))
                                        {
                                                totalIncome = totalIncome + DbCoa.getCoaBalanceCDPrevYear(coax.getOID());	
                                        }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                                        {
                                                totalIncome = totalIncome - DbCoa.getCoaBalancePrevYear(coax.getOID());	
                                        }
                                    }									
                                    amount = totalIncome;
                                }
                                // Bagining Balance                        
                                if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_BEGINING_BALANCE"))){
                                    amount = DbCoaOpeningBalance.getSumOpeningBalancePrevYear();
                                }

                                result = result + amount;
                            }
                            else{
                                result = result + DbCoa.getCoaBalancePrevYear(coa.getOID());
                            }
                        }
                    }
                }
            }
            
            return result;
        }
        
        public static double getCoaBalanceByHeader(long coaId, String type, Vector periods){
            double result = 0;  
            double amount = 0;
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"acc_ref_id='"+coaId+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    
                    if(type.equals("CD")){
                        
                        amount = amount + DbCoa.getCoaBalanceCD(coa.getOID(), periods);
                        
                        //Retained Earnings
                        if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")))
                        {
                            double totalIncome = 0;								
                            Coa coax = new Coa();
                            for(int ix=0; ix<listCoa.size(); ix++)
                            {											
                                coax= (Coa)listCoa.get(ix);																			
                                if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE))
                                {
                                        totalIncome = totalIncome + DbCoa.getCoaBalanceCD(coax.getOID(), periods);	
                                }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES))
                                {
                                        totalIncome = totalIncome + DbCoa.getCoaBalance(coax.getOID(), periods);	
                                }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE))
                                {
                                        totalIncome = totalIncome - DbCoa.getCoaBalance(coax.getOID(), periods);	
                                }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE))
                                {
                                        totalIncome = totalIncome + DbCoa.getCoaBalanceCD(coax.getOID(), periods);	
                                }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                                {
                                        totalIncome = totalIncome - DbCoa.getCoaBalance(coax.getOID(), periods);	
                                }
                            }									
                            amount = totalIncome;
                        }
                        
                        // Bagining Balance                        
                        if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_BEGINING_BALANCE"))){
                            amount = DbCoaOpeningBalance.getSumOpeningBalance();
                        }
                        
                        result = result + amount;
                        
                    }
                    else{
                        result = result + DbCoa.getCoaBalance(coa.getOID(), periods);
                    }
                    
                }
            }
            
            return result;
        }
        
        public static double getCoaBalance(long coaId){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            //run query
            try{                
                String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                
                //System.out.println("\n\n--------******* biaya--------->\n"+sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        
        public static double getCoaBalanceRecursif(Coa coa){
            double result = 0;                      
            
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
            
            if(coa.getStatus().equals("HEADER")){
                Vector v = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);
                        result = result + getCoaBalanceRecursif(cx);
                    }
                }
            }
            else{
                result = result + getCoaBalance(coa.getOID());               
            }
            
            return result;
        }
        
        public static double getCoaBalancePrevYear(long coaId){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            Date dt = new Date();
            try{
                p = DbPeriode.getOpenPeriod();
                dt = p.getStartDate();
                dt.setYear(dt.getYear()-1);//set tahun lalu
                //dt.setMonth(11);//set bulan desember                
                //dt.setDate(15);//set pertengahan bulan
                
                //System.out.println("last year day : "+dt);
                
                Vector v = DbPeriode.list(0,0, "'"+JSPFormater.formatDate(dt, "yyyy-MM-dd")+"' between "+DbPeriode.colNames[DbPeriode.COL_START_DATE]+" and "+DbPeriode.colNames[DbPeriode.COL_END_DATE], "");
                if(v!=null && v.size()>0){
                    p = (Periode)v.get(0);
                }
                else{
                    p = new Periode();
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
            
            if(p.getOID()!=0){
           
                //run query
                try{                
                    String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();

                    //System.out.println(sql);

                    crs = CONHandler.execQueryResult(sql);
                    ResultSet rs = crs.getResultSet();
                    while(rs.next()){
                        result = rs.getDouble(1);
                        //System.out.println(result);
                    }    

                    result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                finally{
                    CONResultSet.close(crs);
                }  
            }
            
            return result;
        }
        
        //untuk menghitung current year balance
        //untuk multiple balance sheet
        public static double getCYEarningCoaBalance(long coaId, Periode p){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //run query
            try{                
                String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        //untuk keperluan multiple period balance sheet
        public static double getCoaBalance(long coaId, Periode periode){
            double result = 0;                      
            CONResultSet crs = null;
              
            //jika merupakan period open,
            //ambil seperti yang balance sheet standard
            if(periode.getStatus().equals(I_Project.STATUS_PERIOD_OPEN)){
                result = getCoaBalance(coaId);
            }
            //jika tidak, ambil langsung pada coa opening balance
            else{
                String where = "to_days("+DbPeriode.colNames[DbPeriode.COL_START_DATE]+")>to_days('"+JSPFormater.formatDate(periode.getStartDate(), "yyyy-MM-dd")+"')";
                String order = DbPeriode.colNames[DbPeriode.COL_START_DATE]+" asc";
                
                Vector v = new Vector();
                v = DbPeriode.list(0,1, where, order);
                Periode abcPeriode = (Periode)v.get(0);
                                
                java.util.Date currentStart = periode.getStartDate();
                Company company = DbCompany.getCompany();
                if(company.getEndFiscalMonth()==currentStart.getMonth()){
                    try{
                        Coa coa = DbCoa.fetchExc(coaId);
                        if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING"))){
                            abcPeriode = periode;
                        }
                    }
                    catch(Exception e){
                        
                    }
                }
                
                where = DbCoaOpeningBalance.colNames[DbCoaOpeningBalance.FLD_COA_ID]+"="+coaId+
                        " and "+DbCoaOpeningBalance.colNames[DbCoaOpeningBalance.FLD_PERIODE_ID]+"="+abcPeriode.getOID();
                
                v = new Vector();
                v = DbCoaOpeningBalance.list(0,0,where, null);
                CoaOpeningBalance cob = (CoaOpeningBalance)v.get(0);
                
                result = cob.getOpeningBalance();
                
            }
            
            return result;
            
        }
        
        public static double getCoaBalance(long coaId, Vector periods){
            double result = 0;                      
            
            for(int i=0; i<periods.size(); i++){

                //get open period id
                Periode p = (Periode)periods.get(i);
                
                CONResultSet crs = null;

                //run query
                try{                
                    String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();

                    //System.out.println(sql);

                    crs = CONHandler.execQueryResult(sql);
                    ResultSet rs = crs.getResultSet();
                    while(rs.next()){
                        result = result + rs.getDouble(1);
                        //System.out.println(result);
                    }    

                    result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                finally{
                    CONResultSet.close(crs);
                }  
            }
            
            return result;
        } 

        public static double getCoaBalanceCD(long coaId){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            //run query
            try{                
                String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                
                //System.out.println("\n\n=======================> hitung biaya\n"+sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        public static double getCoaBalanceCDRecursif(Coa coa){
            
            double result = 0;                      
            
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            if(coa.getStatus().equals("HEADER")){
                
                Vector v = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);                        
                        result = result + getCoaBalanceCDRecursif(cx);
                    }
                }
                
            }
            else{
                result = result + getCoaBalanceCD(coa.getOID()); 
            }
            
            return result;
        }
        
        
        public static double getCoaBalanceYTD(long coaId, long periodId, String strType){
            
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.fetchExc(periodId);//DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
            
            Date endDate = new Date();//p.getEndDate();
            Date startDate = p.getStartDate();
            startDate.setMonth(0);
            startDate.setDate(1);
            
            System.out.println("\n\n=============== get into YTD calculation ============");
            System.out.println("start Date : "+startDate);
            System.out.println("end Date : "+endDate);
            
            //ambil periode yang kena dari jan sampe terakhir
            Vector periods = DbPeriode.list(0,0, DbPeriode.colNames[DbPeriode.COL_START_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"' and '"+JSPFormater.formatDate(p.getEndDate(), "yyyy-MM-dd")+"'", "");
            String periodeWhere = "(";
            if(periods!=null && periods.size()>0){
                for(int i=0; i<periods.size(); i++){
                    Periode per = (Periode)periods.get(i);
                    periodeWhere = periodeWhere + "gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+per.getOID()+" or ";                    
                }
                periodeWhere = periodeWhere.substring(0, periodeWhere.length()-3)+")";
            }
           
            //run query
            try{ 
                String sql = "";
                
                //income & other income
                if(strType.equals("CD")){
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and "+periodeWhere;
                        //" and (gl."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                        //" and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";                        
                }
                //else
                else{
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and "+periodeWhere;
                        //" and (gl."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                        //" and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                }
                
                System.out.println("\n\n=======================> MTD : "+sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                //result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        
        public static double getCoaBalanceYTDRecursif(Coa coa, Periode periode, String strType){
            
            double result = 0;                      
            
            CONResultSet crs = null;
           
            if(coa.getStatus().equals("HEADER")){
                
                Vector v = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);                        
                        result = result + getCoaBalanceYTDRecursif(cx, periode, strType);
                    }
                }
                
            }
            else{
                result = result + getCoaBalanceYTD(coa.getOID(), periode.getOID(), strType); 
            }
            
            return result;
        }
        
        public static double getCoaBalanceMTD(long coaId, long periodId, String strType){
            
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.fetchExc(periodId);//DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
            
            Date endDate = new Date();//p.getEndDate();
            Date startDate = p.getStartDate();
            
            System.out.println("\n\n=============== get into MTD calculation ============");
            System.out.println("start Date : "+startDate);
            System.out.println("end Date : "+endDate);
           
            //run query
            try{ 
                String sql = "";
                
                //income & other income
                if(strType.equals("CD")){
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                        //" and (gl."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                        //" and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                }
                //else
                else{
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                        //" and (gl."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                        //" and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                }
                
                System.out.println("\n\n=======================> MTD : "+sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                //result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        
        public static double getCoaBalanceMTDRecursif(Coa coa, Periode periode, String strType){
            
            double result = 0;                      
            
            CONResultSet crs = null;
           
            if(coa.getStatus().equals("HEADER")){
                
                Vector v = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);                        
                        result = result + getCoaBalanceMTDRecursif(cx, periode, strType);
                    }
                }
                
            }
            else{
                result = result + getCoaBalanceMTD(coa.getOID(), periode.getOID(), strType); 
            }
            
            return result;
        }
        
        
        public static double getCoaBalanceCDByClass(long coaId){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            //run query
            try{                
                String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        public static double getCoaBalanceCDPrevYear(long coaId){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            Date dt = new Date();
            try{
                p = DbPeriode.getOpenPeriod();
                dt = p.getStartDate();
                dt.setYear(dt.getYear()-1);//set tahun lalu
                //dt.setMonth(11);//set bulan desember                
                //dt.setDate(15);//set pertengahan bulan
                
                //System.out.println("last year day : "+dt);
                
                Vector v = DbPeriode.list(0,0, "'"+JSPFormater.formatDate(dt, "yyyy-MM-dd")+"' between "+DbPeriode.colNames[DbPeriode.COL_START_DATE]+" and "+DbPeriode.colNames[DbPeriode.COL_END_DATE], "");
                if(v!=null && v.size()>0){
                    p = (Periode)v.get(0);
                }
                else{
                    p = new Periode();
                }
                
            }catch(Exception e){
                System.out.println(e);
            }
           
            if(p.getOID()!=0){
                //run query
                try{                
                    String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();

                    //System.out.println(sql);

                    crs = CONHandler.execQueryResult(sql);
                    ResultSet rs = crs.getResultSet();
                    while(rs.next()){
                        result = rs.getDouble(1);
                        //System.out.println(result);
                    }    

                    result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                finally{
                    CONResultSet.close(crs);
                }
            }
            
            return result;
        }
       
        //u multiple bs
        public static double getCYEarningCoaBalanceCD(long coaId, Periode p){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //run query
            try{                
                String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                    " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                    " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }
        
        //untuk keperluan multiple period balance sheet
        public static double getCoaBalanceCD(long coaId, Periode periode){
            double result = 0;                      
            CONResultSet crs = null;
              
            //jika merupakan period open,
            //ambil seperti yang balance sheet standard
            if(periode.getStatus().equals(I_Project.STATUS_PERIOD_OPEN)){
                result = getCoaBalanceCD(coaId);
            }
            //jika tidak, ambil langsung pada coa opening balance
            else{
                //jika desember maka pakai periode desember
                //jika tidak pke periode bulan selanjutnya
                String where = "to_days("+DbPeriode.colNames[DbPeriode.COL_START_DATE]+")>to_days('"+JSPFormater.formatDate(periode.getStartDate(), "yyyy-MM-dd")+"')";
                String order = DbPeriode.colNames[DbPeriode.COL_START_DATE]+" asc";
                
                Vector v = new Vector();
                v = DbPeriode.list(0,1, where, order);
                Periode abcPeriode = (Periode)v.get(0);
                                
                java.util.Date currentStart = periode.getStartDate();
                Company company = DbCompany.getCompany();
                if(company.getEndFiscalMonth()==currentStart.getMonth()){
                    try{
                        Coa coa = DbCoa.fetchExc(coaId);
                        if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING"))){
                            abcPeriode = periode;
                        }
                    }
                    catch(Exception e){
                        
                    }
                }
                
                where = DbCoaOpeningBalance.colNames[DbCoaOpeningBalance.FLD_COA_ID]+"="+coaId+
                        " and "+DbCoaOpeningBalance.colNames[DbCoaOpeningBalance.FLD_PERIODE_ID]+"="+abcPeriode.getOID();
                
                v = new Vector();
                v = DbCoaOpeningBalance.list(0,0,where, null);
                CoaOpeningBalance cob = (CoaOpeningBalance)v.get(0);
                
                result = cob.getOpeningBalance();
                
            }
            
            return result;
            
        }
        
        public static double getCoaBalanceCD(long coaId, Vector periods){
            
            double result = 0;                      
            
            for(int i=0; i<periods.size(); i++){
                
                //get open period id
                Periode p = (Periode)periods.get(i);
                
                CONResultSet crs = null;               

                //run query
                try{                
                    String sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();

                    //System.out.println(sql);

                    crs = CONHandler.execQueryResult(sql);
                    ResultSet rs = crs.getResultSet();
                    while(rs.next()){
                        result = result + rs.getDouble(1);
                        //System.out.println(result);
                    }    

                    result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
                finally{
                    CONResultSet.close(crs);
                } 
            }
            
            return result;
        } 
                
        public static double getCoaBalance(long coaId, long depId, String type){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            //run query
            try{                
                String sql = "";
                if(type.equals("DC"))        
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId;
                else
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId;
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                //result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        } 
        
        
        public static double getPnlBalance(long coaId, long depId, String type, Date startDate, Date endDate){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //run query
            try{                
                String sql = "";
                if(type.equals("DC")) {       
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and (gl."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                        " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                        
                        if(depId!=0){
                            sql = sql +" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId;
                        }
                }
                else{
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and (gl."+DbGl.colNames[DbGl.COL_TRANS_DATE]+" between '"+JSPFormater.formatDate(startDate, "yyyy-MM-dd")+"'"+
                        " and '"+JSPFormater.formatDate(endDate, "yyyy-MM-dd")+"')";
                        
                        if(depId!=0){
                            sql = sql +" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId;
                        }
                        //" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId;
                }
                
                System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                //result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        } 
        
        
        public static double getCoaBalance(long coaId, long depId, long secId, String type){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            //run query
            try{                
                String sql = "";
                if(type.equals("DC"))        
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_SECTION_ID]+"="+secId;
                else
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_SECTION_ID]+"="+secId;
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                //result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }           
        
        public static double getCoaBalance(long coaId, long depId, long secId, long subSecId, String type){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            //run query
            try{                
                String sql = "";
                if(type.equals("DC"))        
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_SECTION_ID]+"="+secId +
                        " and gld."+DbGlDetail.colNames[DbGlDetail.COL_SUB_SECTION_ID]+"="+subSecId;
                else
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_SECTION_ID]+"="+secId +
                        " and gld."+DbGlDetail.colNames[DbGlDetail.COL_SUB_SECTION_ID]+"="+subSecId;
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                //result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }           
        
        public static double getCoaBalance(long coaId, long depId, long secId, long subSecId, long jobId, String type){
            double result = 0;                      
            CONResultSet crs = null;
                       
            //get open period id
            Periode p = new Periode();
            try{
                p = DbPeriode.getOpenPeriod();
            }catch(Exception e){
                System.out.println(e);
            }
           
            //run query
            try{                
                String sql = "";
                if(type.equals("DC"))        
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_SECTION_ID]+"="+secId +
                        " and gld."+DbGlDetail.colNames[DbGlDetail.COL_SUB_SECTION_ID]+"="+subSecId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_JOB_ID]+"="+jobId;
                else
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_SECTION_ID]+"="+secId +
                        " and gld."+DbGlDetail.colNames[DbGlDetail.COL_SUB_SECTION_ID]+"="+subSecId+" and gld."+DbGlDetail.colNames[DbGlDetail.COL_JOB_ID]+"="+jobId;
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                //result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }           
                
        public static double getCoaBalance(Vector listCoa, String accGroup, String type, long depId){
            double result = 0;                      
            if(listCoa!=null && listCoa.size()>0)
            {                	
                for(int i=0; i<listCoa.size(); i++)
                {
                    Coa coa = (Coa)listCoa.get(i);								
                    if (coa.getAccountGroup().equals(accGroup))
                    {                                
                        double amount = 0;
                        if(type.equals("DC"))
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,type);
                        else
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,type);
                        
                        result = result + amount;
                    }
                }
            }            
            return result;
        }  
        
        public static double getPnlBalance(Vector listCoa, String accGroup, String type, long depId, Date startDate, Date endDate){
            double result = 0;                      
            if(listCoa!=null && listCoa.size()>0)
            {                	
                for(int i=0; i<listCoa.size(); i++)
                {
                    Coa coa = (Coa)listCoa.get(i);								
                    if (coa.getAccountGroup().equals(accGroup))
                    {                                
                        double amount = 0;
                        if(type.equals("DC"))
                            amount = DbCoa.getPnlBalance(coa.getOID(),depId,type, startDate, endDate);
                        else
                            amount = DbCoa.getPnlBalance(coa.getOID(),depId,type, startDate, endDate);
                        
                        result = result + amount;
                    }
                }
            }            
            return result;
        }    

        public static double getCoaBalance(Vector listCoa, String accGroup, String type, long depId, long secId){
            double result = 0;                      
            if(listCoa!=null && listCoa.size()>0)
            {                	
                for(int i=0; i<listCoa.size(); i++)
                {
                    Coa coa = (Coa)listCoa.get(i);								
                    if (coa.getAccountGroup().equals(accGroup))
                    {                                
                        double amount = 0;
                        if(type.equals("DC"))
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,secId,type);
                        else
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,secId,type);
                        
                        result = result + amount;
                    }
                }
            }            
            return result;
        }
 
        public static double getCoaBalance(Vector listCoa, String accGroup, String type, long depId, long secId, long subSecId){
            double result = 0;                      
            if(listCoa!=null && listCoa.size()>0)
            {                	
                for(int i=0; i<listCoa.size(); i++)
                {
                    Coa coa = (Coa)listCoa.get(i);								
                    if (coa.getAccountGroup().equals(accGroup))
                    {                                
                        double amount = 0;
                        if(type.equals("DC"))
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,secId,subSecId,type);
                        else
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,secId,subSecId,type);
                        
                        result = result + amount;
                    }
                }
            }            
            return result;
        }
        
        public static double getCoaBalance(Vector listCoa, String accGroup, String type, long depId, long secId, long subSecId, long jobId){
            double result = 0;                      
            if(listCoa!=null && listCoa.size()>0)
            {                	
                for(int i=0; i<listCoa.size(); i++)
                {
                    Coa coa = (Coa)listCoa.get(i);								
                    if (coa.getAccountGroup().equals(accGroup))
                    {                                
                        double amount = 0;
                        if(type.equals("DC"))
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,secId,subSecId,jobId,type);
                        else
                            amount = DbCoa.getCoaBalance(coa.getOID(),depId,secId,subSecId,jobId,type);
                        
                        result = result + amount;
                    }
                }
            }            
            return result;
        }
        
        //ambil total transaksi + ditambah dengan opening balance
        public static double getCoaBalanceClosing(long coaId, Periode p, boolean isYearlyClosing, String type){
            double result = 0;                      
            CONResultSet crs = null;
                                 
            //run query
            try{                
                String sql = "";
                if(type.equals("DC")){
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                            " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                            " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                }else{
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                            " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                            " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                }
                
                //System.out.println(sql);
                
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = rs.getDouble(1);
                    //System.out.println(result);
                }    
                
                result = DbCoaOpeningBalance.getOpeningBalance(p, coaId)  + result;
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }            
            
            return result;
        }         
        
        
        //lakukan looping untuk menghitung saldo masing-masing account,
        //me-reset p&l dan mengeset opening balance pada periode baru.
        public static void getOpeningBalanceClosing(Periode p, boolean isYearlyClosing)
        {               
            Periode newP = DbPeriode.getOpenPeriod();
            Vector c = new Vector();
            try{
                c = DbCoa.list(0,0,"","");
                double totalIncome = 0;
                //System.out.println("Total Income = "+totalIncome);
                
                if(c!=null && c.size()>0){
                    
                    for(int i=0; i<c.size(); i++){
                    
                        Coa coa = (Coa)c.get(i);                        
                        // Add new Opening Balance
                        CoaOpeningBalance cob = new CoaOpeningBalance();
                        
                            if (coa.getAccountGroup().equals(I_Project.ACC_GROUP_LIQUID_ASSET) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_FIXED_ASSET) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_ASSET) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                            {
                                
                                //closing tahunan
                                
                                if(isYearlyClosing){
                                    //get retained earning
                                    if (coa.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE)){
                                        
                                        cob.setOpeningBalance(0);
                                        
                                    }
                                    //jika merupakan kode yearly earning, maka 0 kan, ID_RETAINED_EARNINGS => ada S
                                    else if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")) 
                                    || coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS_1"))){
                                        
                                        cob.setOpeningBalance(0);
                                        
                                    }
                                    //jika coa adalah retained earning
                                    else if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING"))
                                    || coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING_1"))
                                    || coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING_2"))
                                    ){
                                        
                                        //cek apakah menggunakan account class(sp/nsp) atau hanya single retained earning
                                        String code2 = DbSystemProperty.getValueByName("ID_RETAINED_EARNING_1");
                                        String code3 = DbSystemProperty.getValueByName("ID_RETAINED_EARNING_2");
                                        
                                        //reset total income
                                        
                                        totalIncome = 0;
                                        
                                        boolean isByAccClass = false;
                                        if((code2.length()>0 && !code2.equals("-")) || (code3.length()>0 && !code3.equals("-"))){
                                            isByAccClass = true;
                                        }
                                        
                                        //loop coa, untuk mendapatkan saldo p&l, -> sebenarnya ga kurang bagus neh
                                        for(int x=0; x<c.size(); x++){
                                                
                                                Coa coax = (Coa)c.get(x);   
                                            
                                                boolean ok = true;
                                                if(isByAccClass){
                                                    if(coax.getAccountClass()!=coa.getAccountClass()){
                                                        ok = false;
                                                    }                                                    
                                                }                                                
                                                
                                                if(ok){
                                            
                                                    if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE))
                                                    {
                                                        totalIncome = totalIncome + DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"CD");                            
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES))
                                                    {
                                                        totalIncome = totalIncome - DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"DC");
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE))
                                                    {
                                                        totalIncome = totalIncome - DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"DC");
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE))
                                                    {
                                                        totalIncome = totalIncome + DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"CD");
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                                                    {
                                                        totalIncome = totalIncome - DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"DC");
                                                    }
                                                    //--------- tambahan -----------
                                                    else if(coax.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")) 
                                                    || coax.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS_1")))
						    {
                                                        totalIncome = totalIncome + DbCoaOpeningBalance.getOpeningBalance(p, coax.getOID());
                                                    }
                                                    
                                                }
                                            
                                        }
                                        
                                        cob.setOpeningBalance(getCoaBalanceClosing(coa.getOID(),p,isYearlyClosing,"DC")+totalIncome);
                                        
                                        System.out.println("Retained Earning");
                                        
                                    }else{
                                        cob.setOpeningBalance(getCoaBalanceClosing(coa.getOID(),p,isYearlyClosing,"DC"));
                                    }
                                    
                                }
                                //closing bulanan
                                else{
                                    cob.setOpeningBalance(getCoaBalanceClosing(coa.getOID(),p,isYearlyClosing,"DC"));
                                }
                                
                            }else if (coa.getAccountGroup().equals(I_Project.ACC_GROUP_EQUITY) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_CURRENT_LIABILITIES) 
                            || coa.getAccountGroup().equals(I_Project.ACC_GROUP_LONG_TERM_LIABILITIES))
                            {                             
                                
                                //jika closing tahunan
                                if(isYearlyClosing){
                                    //get retained earning
                                    if (coa.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE) 
                                    || coa.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE)){
                                        
                                        cob.setOpeningBalance(0);
                                        
                                    }
                                    //jika merupakan kode yearly earning, maka 0 kan, ID_RETAINED_EARNINGS => ada S
                                    else if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")) 
                                    || coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS_1"))){
                                        
                                        cob.setOpeningBalance(0);
                                        
                                    }
                                    //ID_RETAINED_EARNING => reatined earning / bukan yearly earning
                                    else if(coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING"))
                                    || coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING_1"))
                                    || coa.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNING_2"))
                                    ){
                                        
                                        //cek apakah menggunakan account class(sp/nsp) atau hanya single retained earning
                                        String code2 = DbSystemProperty.getValueByName("ID_RETAINED_EARNING_1");
                                        String code3 = DbSystemProperty.getValueByName("ID_RETAINED_EARNING_2");
                                        
                                        System.out.println("\n\n============== yearly close ================\nyearly close, in retained earning : code2 = "+code2+", code3 : "+code3);
                                        
                                        //reset total income
                                        
                                        totalIncome = 0;
                                        
                                        boolean isByAccClass = false;
                                        if((code2.length()>0 && !code2.equals("-")) || (code3.length()>0 && !code3.equals("-"))){
                                            isByAccClass = true;
                                        }
                                        
                                        System.out.println("isByAccClass : "+isByAccClass+", coa.getAccountClass() : "+coa.getAccountClass());
                                        
                                        for(int x=0; x<c.size(); x++){
                                            
                                                Coa coax = (Coa)c.get(x); 
                                                
                                                boolean ok = true;
                                                if(isByAccClass){
                                                    if(coax.getAccountClass()!=coa.getAccountClass()){
                                                        ok = false;
                                                    }                                                    
                                                }  
                                                
                                                System.out.println("ok : "+ok);
                                                
                                                if(ok){
                                                
                                                    if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_REVENUE))
                                                    {
                                                        totalIncome = totalIncome + DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"CD");                            
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_COST_OF_SALES))
                                                    {
                                                        totalIncome = totalIncome - DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"DC");
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_EXPENSE))
                                                    {
                                                        totalIncome = totalIncome - DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"DC");
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_REVENUE))
                                                    {
                                                        totalIncome = totalIncome + DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"CD");
                                                    }else if (coax.getAccountGroup().equals(I_Project.ACC_GROUP_OTHER_EXPENSE))
                                                    {
                                                        totalIncome = totalIncome - DbCoa.getCoaBalanceClosing(coax.getOID(),p,isYearlyClosing,"DC");
                                                    }
                                                    //--------- tambahan -----------
                                                    else if(coax.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS")) 
                                                    || coax.getCode().equals(DbSystemProperty.getValueByName("ID_RETAINED_EARNINGS_1")))
						    {
                                                        totalIncome = totalIncome + DbCoaOpeningBalance.getOpeningBalance(p, coax.getOID());
                                                    }
                                                    
                                                }
                                            
                                        }
                                        
                                        System.out.println("totalIncome : "+totalIncome);
                                        
                                        //ambil balance dari retained earning + total p&l dan opening jika yearly earning
                                        cob.setOpeningBalance(getCoaBalanceClosing(coa.getOID(),p,isYearlyClosing,"CD")+totalIncome);
                                        
                                        System.out.println("Retained Earning");
                                        
                                    }else{
                                        cob.setOpeningBalance(getCoaBalanceClosing(coa.getOID(),p,isYearlyClosing,"CD"));
                                    }
                                    
                                }
                                //closing bulanan
                                else{
                                    cob.setOpeningBalance(getCoaBalanceClosing(coa.getOID(),p,isYearlyClosing,"CD"));
                                }                               
                            }

                        
                        cob.setPeriodeId(newP.getOID());
                        cob.setCoaId(coa.getOID());
                        long oid = DbCoaOpeningBalance.insertExc(cob);  
                        
                        //System.out.println("coa = "+coa.getOID());
                        //System.out.println("DbCoa.getCoaBalance(coa.getOID()) = "+getCoaBalance(coa.getOID()));
                    }
                }
            }
            catch(Exception e){
                System.out.println(e.toString());
            }            
        }
        
        
        // dipakai pada P&L Multiple
        public static double getCoaBalance(long coaId, Periode p, String type){            
            double result = 0;                     
            CONResultSet crs = null;               
            //run query
            try{  
                //ambil transaksi bulan itu
                String sql = "";
                if(type.equals("DC")){
                    
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                    
                    System.out.println("\nin DC coaId :"+sql);
                    
                }else{
                    
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                    
                    System.out.println("\nin CD coaId :"+sql);
                    
                }
                
                

                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = result + rs.getDouble(1);
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
        
        // dipakai pada P&L Multiple
        public static double getCoaBalance(long coaId, Periode p, String type, long depOID){            
            double result = 0;                     
            CONResultSet crs = null;               
            //run query
            try{  
                //ambil transaksi bulan itu
                String sql = "";
                if(type.equals("DC")){
                    
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+
                        " and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depOID;
                    
                    System.out.println("\nin DC coaId :"+sql);
                    
                }else{
                    
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID()+
                        " and gld."+DbGlDetail.colNames[DbGlDetail.COL_DEPARTMENT_ID]+"="+depOID;;
                    
                    System.out.println("\nin CD coaId :"+sql);
                    
                }
                
                

                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = result + rs.getDouble(1);
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
        
        
        // dipakai pada P&L Multiple
        public static double getCoaBalanceNonPL(long coaId, Periode p, String type){            
            double result = 0;                     
            CONResultSet crs = null;               
            //run query
            try{  
                //ambil transaksi bulan itu
                String sql = "";
                if(type.equals("DC")){
                    
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                    
                    //System.out.println("\nin DC coaId :"+sql);
                    
                }else{
                    
                    sql = "select (sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_CREDIT]+")-sum(gld."+DbGlDetail.colNames[DbGlDetail.COL_DEBET]+")) from "+DbGlDetail.DB_GL_DETAIL+ " as gld "+
                        " inner join "+DbGl.DB_GL+" as gl on gld."+DbGlDetail.colNames[DbGlDetail.COL_GL_ID]+"=gl."+DbGl.colNames[DbGl.COL_GL_ID]+" where gld."+DbGlDetail.colNames[DbGlDetail.COL_COA_ID]+"="+coaId +
                        " and gl."+DbGl.colNames[DbGl.COL_PERIOD_ID]+"="+p.getOID();
                    
                    //System.out.println("\nin CD coaId :"+sql);
                    
                }

                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    result = result + rs.getDouble(1);
                }  
                
                //get opening balance
                result = result + DbCoaOpeningBalance.getOpeningBalance(p, coaId);
                
            }
            catch(Exception e){
                System.out.println(e.toString());
            }
            finally{
                CONResultSet.close(crs);
            }             
            return result;
        } 
        
        
        // dipakai pada P&L Multiple
        public static double getCoaBalanceRecursif(Coa coa, Periode p, String type){            
            
            double result = 0;                     
            
            if(coa.getStatus().equals("HEADER")){
                Vector v = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);
                        result = result + getCoaBalanceRecursif(cx, p, type);
                    }
                }
            }
            else{
                result = result + getCoaBalance(coa.getOID(), p, type);       
            }
            return result;
        } 
        
        // dipakai pada P&L Multiple
        public static double getCoaBalanceRecursifNonPL(Coa coa, Periode p, String type){            
            
            double result = 0;                     
            
            if(coa.getStatus().equals("HEADER")){
                Vector v = DbCoa.list(0,0, DbCoa.colNames[DbCoa.COL_ACC_REF_ID]+"="+coa.getOID(), "");
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa cx = (Coa)v.get(i);
                        result = result + getCoaBalanceRecursifNonPL(cx, p, type);
                    }
                }
            }
            else{
                result = result + getCoaBalanceNonPL(coa.getOID(), p, type);       
            }
            return result;
        } 
        
        // dipakai pada P&L Multiple
        public static double getCoaBalance(long coaId, Vector periods, String type){            
            double result = 0;                                  
            for(int i=0; i<periods.size(); i++){
                Periode p = (Periode)periods.get(i);                
                result = result + getCoaBalance(coaId, p, type);
            }            
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getCoaBalance(long coaId, Vector periods, String type, long depId){            
            double result = 0;                                  
            for(int i=0; i<periods.size(); i++){
                Periode p = (Periode)periods.get(i);                
                result = result + getCoaBalance(coaId, p, type, depId);
            }            
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getCoaBalanceByGroup(String coaGroup, String type, Periode p){
            double result = 0;                      
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"account_group='"+coaGroup+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    result = result+DbCoa.getCoaBalance(coa.getOID(), p, type);
                }
            }
            
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getCoaBalanceByGroup(String coaGroup, String type, Periode p, long depOID){
            double result = 0;                      
            Vector listCoa = new Vector();
            Coa coa = new Coa();
            listCoa = DbCoa.list(0,0,"account_group='"+coaGroup+"'","code");
            if(listCoa!=null && listCoa.size()>0)
            {
                for(int i=0; i<listCoa.size(); i++)
                {
                    coa = (Coa)listCoa.get(i);
                    result = result+DbCoa.getCoaBalance(coa.getOID(), p, type, depOID);
                }
            }
            
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getCoaBalanceByGroup(String coaGroup, Vector periods, String type){
            double result = 0;                      
            for(int i=0; i<periods.size(); i++){
                Periode p = (Periode)periods.get(i);                
                result = result + DbCoa.getCoaBalanceByGroup(coaGroup, type, p);                
            }            
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getCoaBalanceByGroup(String coaGroup, Vector periods, String type, long depId){
            double result = 0;                      
            for(int i=0; i<periods.size(); i++){
                Periode p = (Periode)periods.get(i);                
                result = result + DbCoa.getCoaBalanceByGroup(coaGroup, type, p, depId);                
            }            
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getNetIncomeByPeriod(Periode p){
            double result = 0;                      
            result = DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_REVENUE,"CD",p) -
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_COST_OF_SALES,"DC",p)-
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_EXPENSE,"DC",p)+
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_REVENUE,"CD",p)-
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_EXPENSE,"DC",p);
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getNetIncomeByPeriod(Periode p, long depID){
            double result = 0;                      
            result = DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_REVENUE,"CD",p, depID) -
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_COST_OF_SALES,"DC",p,depID)-
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_EXPENSE,"DC",p,depID)+
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_REVENUE,"CD",p,depID)-
                     DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_EXPENSE,"DC",p,depID);
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getNetIncomeByPeriod(Vector periods){
            double result = 0;                      
            for(int i=0; i<periods.size(); i++){
                Periode p = (Periode)periods.get(i);                
                result = result + DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_REVENUE,"CD",p) -
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_COST_OF_SALES,"DC",p)-
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_EXPENSE,"DC",p)+
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_REVENUE,"CD",p)-
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_EXPENSE,"DC",p);
            }
            return result;
        }
        
        // dipakai pada P&L Multiple
        public static double getNetIncomeByPeriod(Vector periods, long depId){
            double result = 0;                      
            for(int i=0; i<periods.size(); i++){
                Periode p = (Periode)periods.get(i);                
                result = result + DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_REVENUE,"CD",p,depId) -
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_COST_OF_SALES,"DC",p,depId)-
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_EXPENSE,"DC",p,depId)+
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_REVENUE,"CD",p,depId)-
                         DbCoa.getCoaBalanceByGroup(I_Project.ACC_GROUP_OTHER_EXPENSE,"DC",p,depId);
            }
            return result;
        }
        
        
        public static double getRatioCurrYR(String accGroup, Periode periode, String strType){
            
            double result = 0;
            
            //System.out.println("where : "+colNames[COL_ACCOUNT_GROUP]+"='"+accGroup+"' and "+colNames[COL_STATUS]+"='HEADER' and "+colNames[COL_LEVEL]+"=1");
            
            //ambil postable level 1            
            Vector v = DbCoa.list(0,0, colNames[COL_ACCOUNT_GROUP]+"='"+accGroup+"' and "+colNames[COL_STATUS]+"='HEADER' and "+colNames[COL_LEVEL]+"=1", "");
            if(v!=null && v.size()>0){
                for(int i=0; i<v.size(); i++){
                    Coa coa = (Coa)v.get(i);
                    result = result + DbCoa.getCoaBalanceRecursifNonPL(coa, periode, strType);
                }
            }
				
            return result;
        }
        
        public static double getRatioPrevYR(String accGroup, Periode periode, String strType){
            
            Date dt = periode.getStartDate();            
            Date dtx = (Date)dt.clone();
            dtx.setYear(dtx.getYear()-1);
                        
            Periode periode2 = DbPeriode.getPeriodByTransDate(dtx);
            
            double result = 0;
            if(periode.getOID()!=0){
                //ambil postable level 1
                //System.out.println("where : "+colNames[COL_ACCOUNT_GROUP]+"='"+accGroup+"' and "+colNames[COL_STATUS]+"='HEADER' and "+colNames[COL_LEVEL]+"=1");
                
                Vector v = DbCoa.list(0,0, colNames[COL_ACCOUNT_GROUP]+"='"+accGroup+"' and "+colNames[COL_STATUS]+"='HEADER' and "+colNames[COL_LEVEL]+"=1", "");
                
                System.out.println(v);
                
                if(v!=null && v.size()>0){
                    for(int i=0; i<v.size(); i++){
                        Coa coa = (Coa)v.get(i);
                        result = result + DbCoa.getCoaBalanceRecursifNonPL(coa, periode2, strType);
                    }
                }
            }
				
            return result;
            
        }
        
        
        public static double getEarningByPeriod(Periode periode){
            //income
            String where = "("+colNames[COL_ACCOUNT_GROUP]+"='"+I_Project.ACC_GROUP_REVENUE+"'"+
                    " or "+colNames[COL_ACCOUNT_GROUP]+"='"+I_Project.ACC_GROUP_OTHER_REVENUE+"')"+
                    " and "+colNames[COL_STATUS]+"='HEADER' and "+colNames[COL_LEVEL]+"=1";
            
            Vector sales = DbCoa.list(0,0, where, "");
            double result = 0;
            if(sales!=null && sales.size()>0){
                for(int i=0; i<sales.size(); i++){
                    Coa coa = (Coa)sales.get(i);
                    result = result + getCoaBalanceYTDRecursif(coa, periode, "CD");
                }
            }
            
            where = "("+colNames[COL_ACCOUNT_GROUP]+"='"+I_Project.ACC_GROUP_EXPENSE+"'"+
                    " or "+colNames[COL_ACCOUNT_GROUP]+"='"+I_Project.ACC_GROUP_OTHER_EXPENSE+"')"+
                    " and "+colNames[COL_STATUS]+"='HEADER' and "+colNames[COL_LEVEL]+"=1";
            
            Vector expenses = DbCoa.list(0,0, where, "");            
            if(expenses!=null && expenses.size()>0){
                for(int i=0; i<expenses.size(); i++){
                    Coa coa = (Coa)expenses.get(i);
                    result = result - getCoaBalanceYTDRecursif(coa, periode, "DC");
                }
            }
            
            return result;
            
            
        }
        
        public static Coa getCoaByCode(String code){
            
            Vector v = list(0,0, colNames[COL_CODE]+"='"+code+"'", "");
            if(v!=null && v.size()>0){
                return (Coa)v.get(0);
            }
            
            return new Coa();
            
        }
        

}

