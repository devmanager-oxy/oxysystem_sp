package com.project.coorp.pinjaman;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.jsp.*;
import com.project.general.*;
import com.project.util.lang.*;
import com.project.general.*;
import com.project.fms.master.*;
import com.project.util.*;
import com.project.coorp.member.*;
import com.project.fms.transaction.*;
import com.project.*;
import com.project.coorp.pinjaman.JenisPinjaman;
import com.project.fms.master.*;
import com.project.general.Company;
import com.project.general.DbCompany;

public class DbJenisPinjaman extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {
 
    public static final String DB_JENIS_PINJAMAM = "sp_jenis_pinjaman";
    public static final int COL_JENIS_PINJAMAN_ID = 0;
    public static final int COL_JENIS_PINJAMAN = 1;;

    public static final String[] colNames = {
        "jenis_pinjaman_id",
        "jenis_pinjaman"
    };
    public static final int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_STRING
    };

    public DbJenisPinjaman() {
    }

    public DbJenisPinjaman(int i) throws CONException {
        super(new DbJenisPinjaman());
    }

    public DbJenisPinjaman(String sOid) throws CONException {
        super(new DbJenisPinjaman(0));
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public DbJenisPinjaman(long lOid) throws CONException {
        super(new DbJenisPinjaman(0));
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
        return DB_JENIS_PINJAMAM;
    }

    public String[] getFieldNames() {
        return colNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new DbJenisPinjaman().getClass().getName();
    }

    public long fetchExc(Entity ent) throws Exception {
        JenisPinjaman jenisPinjaman = fetchExc(ent.getOID());
        ent = (Entity) jenisPinjaman;
        return jenisPinjaman.getOID();
    }

    public long insertExc(Entity ent) throws Exception {
        return insertExc((JenisPinjaman) ent);
    }

    public long updateExc(Entity ent) throws Exception {
        return updateExc((JenisPinjaman) ent);
    }

    public long deleteExc(Entity ent) throws Exception {
        if (ent == null) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        }
        return deleteExc(ent.getOID());
    }

    public static JenisPinjaman fetchExc(long oid) throws CONException {
        try {
            JenisPinjaman jenisPinjaman = new JenisPinjaman();
            DbJenisPinjaman pstJenisPinjaman = new DbJenisPinjaman(oid);
            jenisPinjaman.setOID(oid);
            jenisPinjaman.setJenisPinjaman(pstJenisPinjaman.getString(COL_JENIS_PINJAMAN));

            return jenisPinjaman;
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbJenisPinjaman(0), CONException.UNKNOWN);
        }
    }

    public static long insertExc(JenisPinjaman jenisPinjaman) throws CONException {
        try {
            DbJenisPinjaman pstJenisPinjaman = new DbJenisPinjaman(0);
            pstJenisPinjaman.setString(COL_JENIS_PINJAMAN, jenisPinjaman.getJenisPinjaman());
            pstJenisPinjaman.insert();
            jenisPinjaman.setOID(pstJenisPinjaman.getlong(COL_JENIS_PINJAMAN_ID));
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbJenisPinjaman(0), CONException.UNKNOWN);
        }
        return jenisPinjaman.getOID();
    }

    public static long updateExc(JenisPinjaman jenisPinjaman) throws CONException {
        try {
            if (jenisPinjaman.getOID() != 0) {
                DbJenisPinjaman pstJenisPinjaman = new DbJenisPinjaman(jenisPinjaman.getOID());
                pstJenisPinjaman.setString(COL_JENIS_PINJAMAN, jenisPinjaman.getJenisPinjaman());
                pstJenisPinjaman.update();
                return jenisPinjaman.getOID();

            }
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbJenisPinjaman(0), CONException.UNKNOWN);
        }
        return 0;
    }

    public static long deleteExc(long oid) throws CONException {
        try {
            DbJenisPinjaman pstJenisPinjaman = new DbJenisPinjaman(oid);
            pstJenisPinjaman.delete();
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbJenisPinjaman(0), CONException.UNKNOWN);
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
            String sql = "SELECT * FROM " + DB_JENIS_PINJAMAM;
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
                JenisPinjaman jenisPinjaman = new JenisPinjaman();
                resultToObject(rs, jenisPinjaman);
                lists.add(jenisPinjaman);
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

    public static void resultToObject(ResultSet rs, JenisPinjaman jenisPinjaman) {
        try {
            jenisPinjaman.setOID(rs.getLong(DbJenisPinjaman.colNames[DbJenisPinjaman.COL_JENIS_PINJAMAN_ID]));
            jenisPinjaman.setJenisPinjaman(rs.getString(DbJenisPinjaman.colNames[DbJenisPinjaman.COL_JENIS_PINJAMAN]));

        } catch (Exception e) {
        }
    }

    public static int getCount(String whereClause) {
        CONResultSet dbrs = null;
        try {
            String sql = "SELECT COUNT(" + DbJenisPinjaman.colNames[DbJenisPinjaman.COL_JENIS_PINJAMAN] + ") FROM " + DB_JENIS_PINJAMAM;
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
                    JenisPinjaman jenisPinjaman = (JenisPinjaman) list.get(ls);
                    if (oid == jenisPinjaman.getOID()) {
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
}

