package com.project.ccs.postransaction.adjusment;

import com.project.general.Company;
import com.project.general.DbCompany;
import java.sql.*;
import java.util.*;
import com.project.main.db.*;
import com.project.main.entity.*;
import com.project.util.JSPFormater;
import com.project.util.lang.I_Language;
import java.util.Date;

public class DbAdjusment extends CONHandler implements I_CONInterface, I_CONType, I_PersintentExc, I_Language {

    public static final String DB_POS_ADJUSMENT = "pos_adjusment";
    public static final int COL_ADJUSMENT_ID = 0;
    public static final int COL_COUNTER = 1;
    public static final int COL_NUMBER = 2;
    public static final int COL_DATE = 3;
    public static final int COL_STATUS = 4;
    public static final int COL_NOTE = 5;
    public static final int COL_APPROVAL_1 = 6;
    public static final int COL_APPROVAL_2 = 7;
    public static final int COL_APPROVAL_3 = 8;
    public static final int COL_USER_ID = 9;
    public static final int COL_LOCATION_ID = 10;
    public static final int COL_PREFIX_NUMBER = 11;
    public static final int COL_APPROVAL_1_DATE = 12;
    public static final int COL_APPROVAL_2_DATE = 13;
    public static final int COL_APPROVAL_3_DATE = 14;
    public static final String[] colNames = {
        "adjusment_id",
        "counter",
        "number",
        "date",
        "status",
        "note",
        "approval_1",
        "approval_2",
        "approval_3",
        "user_id",
        "location_id",
        "prefix_number",
        "approval_1_date",
        "approval_2_date",
        "approval_3_date"
    };
    public static final int[] fieldTypes = {
        TYPE_LONG + TYPE_PK + TYPE_ID,
        TYPE_INT,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_STRING,
        TYPE_STRING,
        TYPE_INT,
        TYPE_INT,
        TYPE_INT,
        TYPE_LONG,
        TYPE_LONG,
        TYPE_STRING,
        TYPE_DATE,
        TYPE_DATE,
        TYPE_DATE
    };

    public DbAdjusment() {
    }

    public DbAdjusment(int i) throws CONException {
        super(new DbAdjusment());
    }

    public DbAdjusment(String sOid) throws CONException {
        super(new DbAdjusment(0));
        if (!locate(sOid)) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        } else {
            return;
        }
    }

    public DbAdjusment(long lOid) throws CONException {
        super(new DbAdjusment(0));
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
        return DB_POS_ADJUSMENT;
    }

    public String[] getFieldNames() {
        return colNames;
    }

    public int[] getFieldTypes() {
        return fieldTypes;
    }

    public String getPersistentName() {
        return new DbAdjusment().getClass().getName();
    }

    public long fetchExc(Entity ent) throws Exception {
        Adjusment adjusment = fetchExc(ent.getOID());
        ent = (Entity) adjusment;
        return adjusment.getOID();
    }

    public long insertExc(Entity ent) throws Exception {
        return insertExc((Adjusment) ent);
    }

    public long updateExc(Entity ent) throws Exception {
        return updateExc((Adjusment) ent);
    }

    public long deleteExc(Entity ent) throws Exception {
        if (ent == null) {
            throw new CONException(this, CONException.RECORD_NOT_FOUND);
        }
        return deleteExc(ent.getOID());
    }

    public static Adjusment fetchExc(long oid) throws CONException {
        try {
            Adjusment adjusment = new Adjusment();
            DbAdjusment pstAdjusment = new DbAdjusment(oid);
            adjusment.setOID(oid);

            adjusment.setCounter(pstAdjusment.getInt(COL_COUNTER));
            adjusment.setNumber(pstAdjusment.getString(COL_NUMBER));
            adjusment.setDate(pstAdjusment.getDate(COL_DATE));
            adjusment.setStatus(pstAdjusment.getString(COL_STATUS));
            adjusment.setNote(pstAdjusment.getString(COL_NOTE));
            adjusment.setApproval1(pstAdjusment.getlong(COL_APPROVAL_1));
            adjusment.setApproval2(pstAdjusment.getlong(COL_APPROVAL_2));
            adjusment.setApproval3(pstAdjusment.getlong(COL_APPROVAL_3));
            adjusment.setUserId(pstAdjusment.getlong(COL_USER_ID));

            adjusment.setLocationId(pstAdjusment.getlong(COL_LOCATION_ID));
            adjusment.setPrefixNumber(pstAdjusment.getString(COL_PREFIX_NUMBER));
            adjusment.setApproval1_date(pstAdjusment.getDate(COL_APPROVAL_1_DATE));
            adjusment.setApproval2_date(pstAdjusment.getDate(COL_APPROVAL_2_DATE));
            adjusment.setApproval3_date(pstAdjusment.getDate(COL_APPROVAL_3_DATE));

            return adjusment;
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbAdjusment(0), CONException.UNKNOWN);
        }
    }

    public static long insertExc(Adjusment adjusment) throws CONException {
        try {
            DbAdjusment pstAdjusment = new DbAdjusment(0);

            pstAdjusment.setInt(COL_COUNTER, adjusment.getCounter());
            pstAdjusment.setString(COL_NUMBER, adjusment.getNumber());
            pstAdjusment.setDate(COL_DATE, adjusment.getDate());
            pstAdjusment.setString(COL_STATUS, adjusment.getStatus());
            pstAdjusment.setString(COL_NOTE, adjusment.getNote());
            pstAdjusment.setLong(COL_APPROVAL_1, adjusment.getApproval1());
            pstAdjusment.setLong(COL_APPROVAL_2, adjusment.getApproval2());
            pstAdjusment.setLong(COL_APPROVAL_3, adjusment.getApproval3());
            pstAdjusment.setLong(COL_USER_ID, adjusment.getUserId());

            pstAdjusment.setLong(COL_LOCATION_ID, adjusment.getLocationId());
            pstAdjusment.setString(COL_PREFIX_NUMBER, adjusment.getPrefixNumber());
            pstAdjusment.setDate(COL_APPROVAL_1_DATE, adjusment.getApproval1_date());
            pstAdjusment.setDate(COL_APPROVAL_2_DATE, adjusment.getApproval2_date());
            pstAdjusment.setDate(COL_APPROVAL_3_DATE, adjusment.getApproval3_date());

            pstAdjusment.insert();
            adjusment.setOID(pstAdjusment.getlong(COL_ADJUSMENT_ID));
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbAdjusment(0), CONException.UNKNOWN);
        }
        return adjusment.getOID();
    }

    public static long updateExc(Adjusment adjusment) throws CONException {
        try {
            if (adjusment.getOID() != 0) {
                DbAdjusment pstAdjusment = new DbAdjusment(adjusment.getOID());

                pstAdjusment.setInt(COL_COUNTER, adjusment.getCounter());
                pstAdjusment.setString(COL_NUMBER, adjusment.getNumber());
                pstAdjusment.setDate(COL_DATE, adjusment.getDate());
                pstAdjusment.setString(COL_STATUS, adjusment.getStatus());
                pstAdjusment.setString(COL_NOTE, adjusment.getNote());
                pstAdjusment.setLong(COL_APPROVAL_1, adjusment.getApproval1());
                pstAdjusment.setLong(COL_APPROVAL_2, adjusment.getApproval2());
                pstAdjusment.setLong(COL_APPROVAL_3, adjusment.getApproval3());
                pstAdjusment.setLong(COL_USER_ID, adjusment.getUserId());

                pstAdjusment.setLong(COL_LOCATION_ID, adjusment.getLocationId());
                pstAdjusment.setString(COL_PREFIX_NUMBER, adjusment.getPrefixNumber());
                pstAdjusment.setDate(COL_APPROVAL_1_DATE, adjusment.getApproval1_date());
                pstAdjusment.setDate(COL_APPROVAL_2_DATE, adjusment.getApproval2_date());
                pstAdjusment.setDate(COL_APPROVAL_3_DATE, adjusment.getApproval3_date());

                pstAdjusment.update();
                return adjusment.getOID();

            }
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbAdjusment(0), CONException.UNKNOWN);
        }
        return 0;
    }

    public static long deleteExc(long oid) throws CONException {
        try {
            DbAdjusment pstAdjusment = new DbAdjusment(oid);
            pstAdjusment.delete();
        } catch (CONException dbe) {
            throw dbe;
        } catch (Exception e) {
            throw new CONException(new DbAdjusment(0), CONException.UNKNOWN);
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
            String sql = "SELECT * FROM " + DB_POS_ADJUSMENT;
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
                Adjusment adjusment = new Adjusment();
                resultToObject(rs, adjusment);
                lists.add(adjusment);
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

    private static void resultToObject(ResultSet rs, Adjusment adjusment) {
        try {
            adjusment.setOID(rs.getLong(DbAdjusment.colNames[DbAdjusment.COL_ADJUSMENT_ID]));
            adjusment.setCounter(rs.getInt(DbAdjusment.colNames[DbAdjusment.COL_COUNTER]));
            adjusment.setNumber(rs.getString(DbAdjusment.colNames[DbAdjusment.COL_NUMBER]));
            adjusment.setDate(rs.getDate(DbAdjusment.colNames[DbAdjusment.COL_DATE]));
            adjusment.setStatus(rs.getString(DbAdjusment.colNames[DbAdjusment.COL_STATUS]));
            adjusment.setNote(rs.getString(DbAdjusment.colNames[DbAdjusment.COL_NOTE]));
            adjusment.setApproval1(rs.getLong(DbAdjusment.colNames[DbAdjusment.COL_APPROVAL_1]));
            adjusment.setApproval2(rs.getLong(DbAdjusment.colNames[DbAdjusment.COL_APPROVAL_2]));
            adjusment.setApproval3(rs.getLong(DbAdjusment.colNames[DbAdjusment.COL_APPROVAL_3]));
            adjusment.setUserId(rs.getLong(DbAdjusment.colNames[DbAdjusment.COL_USER_ID]));

            adjusment.setUserId(rs.getLong(DbAdjusment.colNames[DbAdjusment.COL_LOCATION_ID]));
            adjusment.setPrefixNumber(rs.getString(DbAdjusment.colNames[DbAdjusment.COL_PREFIX_NUMBER]));
            adjusment.setApproval1_date(rs.getDate(DbAdjusment.colNames[DbAdjusment.COL_APPROVAL_1_DATE]));
            adjusment.setApproval2_date(rs.getDate(DbAdjusment.colNames[DbAdjusment.COL_APPROVAL_2_DATE]));
            adjusment.setApproval3_date(rs.getDate(DbAdjusment.colNames[DbAdjusment.COL_APPROVAL_3_DATE]));
            adjusment.setLocationId(rs.getLong(DbAdjusment.colNames[DbAdjusment.COL_LOCATION_ID]));

        } catch (Exception e) {
        }
    }

    public static boolean checkOID(long adjusmentId) {
        CONResultSet dbrs = null;
        boolean result = false;
        try {
            String sql = "SELECT * FROM " + DB_POS_ADJUSMENT + " WHERE " +
                    DbAdjusment.colNames[DbAdjusment.COL_ADJUSMENT_ID] + " = " + adjusmentId;

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
            String sql = "SELECT COUNT(" + DbAdjusment.colNames[DbAdjusment.COL_ADJUSMENT_ID] + ") FROM " + DB_POS_ADJUSMENT;
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
                    Adjusment adjusment = (Adjusment) list.get(ls);
                    if (oid == adjusment.getOID()) {
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

    public static int getNextCounter() {
        int result = 0;

        CONResultSet dbrs = null;
        try {
            String sql = "select max(" + colNames[COL_COUNTER] + ") from " + DB_POS_ADJUSMENT + " where " +
                    colNames[COL_PREFIX_NUMBER] + "='" + getNumberPrefix() + "' ";

            System.out.println(sql);

            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                result = rs.getInt(1);
            }

            result = result + 1;

        } catch (Exception e) {
        } finally {
            CONResultSet.close(dbrs);
        }

        return result;
    }

    public static String getNumberPrefix() {
        String code = "";
        Company sysCompany = DbCompany.getCompany();
        code = code + sysCompany.getAdjustmentCode();

        code = code + JSPFormater.formatDate(new Date(), "MMyy");

        return code;
    }

    public static String getNextNumber(int ctr) {

        String code = getNumberPrefix();   

        if (ctr < 10) {
            code = code + "000" + ctr;
        } else if (ctr < 100) {
            code = code + "00" + ctr;
        } else if (ctr < 1000) {
            code = code + "0" + ctr;
        } else {
            code = code + ctr;
        }
        return code;
    }
}
