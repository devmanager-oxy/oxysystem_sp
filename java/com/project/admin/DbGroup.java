package com.project.admin;

import com.project.main.db.CONException;
import com.project.main.db.CONHandler;
import com.project.main.db.CONResultSet;
import com.project.main.db.I_CONInterface;
import com.project.main.db.I_CONType;
import com.project.main.entity.Entity;
import com.project.main.entity.I_Persintent;
import com.project.util.JSPFormater;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

public class DbGroup extends CONHandler implements I_CONInterface, I_CONType, I_Persintent {

    public static final String DB_APP_GROUP = "grp";
    public static final int COL_GROUP_ID = 0;
    public static final int COL_GROUP_NAME = 1;
    public static final int COL_REG_DATE = 2;
    public static final int COL_DESCRIPTION = 3;
    public static final String[] colNames = {
        "group_id", "group_name", "reg_date", "description"
    };
    public static int[] fieldTypes = {
        TYPE_PK + TYPE_LONG + TYPE_ID, TYPE_STRING, TYPE_DATE, TYPE_STRING
    };

    public DbGroup() {
    }

    public DbGroup(int i) throws CONException {
        super(new DbGroup());
    }

    public DbGroup(String sOid) throws CONException {
        super(new DbGroup(0));
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public DbGroup(long lOid) throws CONException {
        super(new DbGroup(0));
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
        return DB_APP_GROUP;
    }

    public String getPersistentName() {
        return new DbGroup().getClass().getName();
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String[] getFieldNames() {
        return colNames;
    }

    public long delete(Entity ent) {
        return delete((Group) ent);
    }

    public long insert(Entity ent) {
        return DbGroup.insert((Group) ent);
    }

    public long update(Entity ent) {
        return update((Group) ent);
    }

    public long fetch(Entity ent) {
        Group entObj = DbGroup.fetch(ent.getOID());
        ent = (Entity) entObj;
        return ent.getOID();
    }

    public static Group fetch(long oid) {
        Group entObj = new Group();
        try {
            DbGroup pstObj = new DbGroup(oid);
            entObj.setOID(oid);
            entObj.setGroupName(pstObj.getString(COL_GROUP_NAME));
            entObj.setDescription(pstObj.getString(COL_DESCRIPTION));
            entObj.setRegDate(pstObj.getDate(COL_REG_DATE));
        } catch (CONException e) {
            System.out.println("[Exception] " + e.toString());
        }
        return entObj;
    }

    public static long insert(Group entObj) {
        try {
            DbGroup pstObj = new DbGroup(0);
            pstObj.setString(COL_GROUP_NAME, entObj.getGroupName());
            pstObj.setDate(COL_REG_DATE, entObj.getRegDate());
            pstObj.setString(COL_DESCRIPTION, entObj.getDescription());

            pstObj.insert();
            entObj.setOID(pstObj.getlong(COL_GROUP_ID));
            return entObj.getOID();
        } catch (CONException e) {
            System.out.println("[Exception] " + e.toString());
        }
        return 0;
    }

    public static long update(Group entObj) {
        if ((entObj != null) && (entObj.getOID() != 0)) {
            try {
                DbGroup pstObj = new DbGroup(entObj.getOID());
                pstObj.setString(COL_GROUP_NAME, entObj.getGroupName());
                pstObj.setDate(COL_REG_DATE, entObj.getRegDate());
                pstObj.setString(COL_DESCRIPTION, entObj.getDescription());

                pstObj.update();
                return entObj.getOID();
            } catch (Exception e) {
                System.out.println("[Exception] " + e.toString());
            }
        }
        return 0;
    }

    public static long delete(long oid) {
        try {
            DbGroup pstObj = new DbGroup(oid);
            pstObj.delete();
            return oid;
        } catch (Exception e) {
            System.out.println("[Exception] " + e.toString());
        }
        return 0;
    }

    public static int getCount(String whereClause) {
        CONResultSet dbrs = null;
        int count = 0;

        try {

            String sql = " SELECT COUNT(" + colNames[COL_GROUP_ID] + ") AS NRCOUNT" +
                    " FROM " + DB_APP_GROUP;

            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }
System.out.println(">>>>>>>>>>> "+sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();

            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("[Exception] " + e.toString());
            return 0;
        } finally {
            CONResultSet.close(dbrs);
        }

        return count;

    }

    public static Vector list(int limitStart, int recordToGet, String whereClause, String order) {
        Vector lists = new Vector();
        CONResultSet dbrs = null;
        try {
            String sql = "SELECT " + colNames[COL_GROUP_ID] +
                    ", " + colNames[COL_GROUP_NAME] +
                    ", " + colNames[COL_REG_DATE] +
                    ", " + colNames[COL_DESCRIPTION] +
                    " FROM " + DB_APP_GROUP;

            if (whereClause != null && whereClause.length() > 0) {
                sql = sql + " WHERE " + whereClause;
            }

            if (order != null && order.length() > 0) {
                sql = sql + " ORDER BY " + order;
            }
            
            switch (CONHandler.CONSVR_TYPE) {
                case CONHandler.CONSVR_MYSQL:
                    if (limitStart == 0 && recordToGet == 0) {
                        sql = sql + "";
                    } else {
                        sql = sql + " LIMIT " + limitStart + "," + recordToGet;
                    }
                    break;

                case CONHandler.CONSVR_POSTGRESQL:
                    if (limitStart == 0 && recordToGet == 0) {
                        sql = sql + "";
                    } else {
                        sql = sql + " LIMIT " + recordToGet + " OFFSET " + limitStart;
                    }

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
            while (rs.next()) {
                Group appPriv = new Group();
                resultToObject(rs, appPriv);
                lists.add(appPriv);
            }


        } catch (Exception e) {
            System.out.println("[Exception] " + e.toString());
        } finally {
            CONResultSet.close(dbrs);
        }
        return lists;
    }

    private static void resultToObject(ResultSet rs, Group appPriv) {
        try {
            appPriv.setOID(rs.getLong(colNames[COL_GROUP_ID]));
            appPriv.setGroupName(rs.getString(colNames[COL_GROUP_NAME]));
            String str = rs.getString(colNames[COL_REG_DATE]);
            Date dt = JSPFormater.formatDate(str, "yyyy-MM-dd hh:mm:ss");            
            appPriv.setRegDate(dt);
            appPriv.setDescription(rs.getString(colNames[COL_DESCRIPTION]));
        } catch (Exception e) {
            System.out.println("[Exception] " + e.toString());
        }
    }

    public static boolean isGroupExist(String groupName, long groupOID) {
        boolean isExist = false;
        try {
            String where = colNames[COL_GROUP_NAME] + "='" + groupName + "'";
            if (groupOID != 0) {
                where = where + " AND " + colNames[COL_GROUP_ID] + "!=" + groupOID;
            }
            Vector v = list(0, 0, where, null);
            if (v != null && v.size() > 0) {
                isExist = true;
            }
        } catch (Exception e) {
        }

        return isExist;
    }
}
