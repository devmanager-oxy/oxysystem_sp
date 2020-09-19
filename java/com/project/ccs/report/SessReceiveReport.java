/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.ccs.report;

import com.project.general.DbVendor; 
import com.project.general.Vendor;
import com.project.main.db.CONHandler;
import com.project.main.db.CONResultSet;
import com.project.util.JSPFormater;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Adnyana Eka Yasa
 */
public class SessReceiveReport {

    public static Vector getReceiveReport(SrcReceiveReport srcReceiveReport) {
        Vector list = new Vector();
        switch (srcReceiveReport.getTypeReport()) {
            case SrcReceiveReport.GROUP_BY_SUPPLIER:
                list = getPOBySupplier(srcReceiveReport);
                break;
            case SrcReceiveReport.GROUP_BY_SUB_CATEGORY:
                list = getPOBySubCategory(srcReceiveReport);
                break;
            case SrcReceiveReport.GROUP_BY_CATEGORY:
                list = getPOByCategory(srcReceiveReport);
                break;
            case SrcReceiveReport.GROUP_BY_ITEM:
                list = getPOByItem(srcReceiveReport);
                break;
        }
        return list;
    }

    public static Vector getPOBySupplier(SrcReceiveReport srcReceiveReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector(); 
        try {
            String sql = "SELECT number, count(receive_id) as cnt, vendor_id, sum(total_amount) as total_amount " +
                    "FROM `pos_receive` ";

            String where = "";
            if(srcReceiveReport.getVendorId()!=0){
                where = "vendor_id="+srcReceiveReport.getVendorId();
            }

            if(srcReceiveReport.getLocationId()!=0){
                if(where.length()>0){
                    where = where + " and location_id="+srcReceiveReport.getLocationId();
                }else{
                    where = "location_id="+srcReceiveReport.getLocationId();
                }
            }

            if(srcReceiveReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcReceiveReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and status='"+srcReceiveReport.getStatus()+"'";
                }else{
                    where = "status='"+srcReceiveReport.getStatus()+"'";
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            sql = sql + " group by ";

            switch(srcReceiveReport.getGroupBy()){
                case SrcReceiveReport.GROUP_TRANSAKSI:
                    sql = sql + "receive_id ";   
                    break;
                case SrcReceiveReport.GROUP_SUPPLIER:
                    sql = sql + "vendor_id ";
                    break;
            }

            System.out.println("SQL : " + sql); 
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet(); 
            while (rs.next()) {
                ReceiveReport receiveReport = new ReceiveReport();
                switch(srcReceiveReport.getGroupBy()){
                    case SrcReceiveReport.GROUP_TRANSAKSI:
                        receiveReport.setPurchNumber(rs.getString("number"));
                        break;
                    case SrcReceiveReport.GROUP_SUPPLIER:
                        receiveReport.setPurchNumber(""+rs.getInt("cnt"));
                        break;
                }

                receiveReport.setPurchAmount(rs.getDouble("total_amount"));
                
                //long vendorOid = rs.getLong("vendor_id");
                receiveReport.setVendorId(rs.getLong("vendor_id")); 
                /*
                Vendor vendor = new Vendor();
                try {
                    vendor = DbVendor.fetchExc(vendorOid);
                } catch (Exception ex) {
                    System.out.println("Err >>> : " + ex.toString());
                }
                receiveReport.setVendorName(vendor.getName());
                 */

                list.add(receiveReport);
            }
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        
        finally{
            CONResultSet.close(dbrs);
        }
        
        return list;
    }

    public static Vector getPOByCategory(SrcReceiveReport srcReceiveReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {
            String sql = "SELECT sum(pi.qty) as qty, ic.name, p.number, p.vendor_id, " +
                    "sum(pi.total_amount) as total_amount, pi.amount " +
                    "FROM `pos_receive_item` as pi " +
                    "inner join pos_receive p on pi.receive_id=p.receive_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id " +
                    "inner join pos_item_group as ic on im.item_group_id=ic.item_group_id ";

            String where = "";
            if(srcReceiveReport.getVendorId()!=0){
                where = "p.vendor_id="+srcReceiveReport.getVendorId();
            }

            if(srcReceiveReport.getLocationId()!=0){
                if(where.length()>0){ 
                    where = where + " and p.location_id="+srcReceiveReport.getLocationId();
                }else{
                    where = "p.location_id="+srcReceiveReport.getLocationId();
                }
            }

            if(srcReceiveReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and p.date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "p.date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcReceiveReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and p.status='"+srcReceiveReport.getStatus()+"'";
                }else{
                    where = "p.status='"+srcReceiveReport.getStatus()+"'";
                }
            }

            if(srcReceiveReport.getItemCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_group_id="+srcReceiveReport.getItemCategoryId();
                }else{
                    where = "im.item_group_id="+srcReceiveReport.getItemCategoryId();
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            sql = sql + " group by ";

            switch(srcReceiveReport.getGroupBy()){
                case SrcReceiveReport.GROUP_TRANSAKSI:
                    sql = sql + "p.receive_id ";
                    break;
                case SrcReceiveReport.GROUP_CATEGORY:
                    sql = sql + "ic.item_group_id ";
                    break;
            }

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                ReceiveReport receiveReport = new ReceiveReport();
                receiveReport.setItemCategory(rs.getString("name"));
                receiveReport.setTotalQty(rs.getDouble("qty"));
                receiveReport.setPurchAmount(rs.getDouble("total_amount"));

                if(srcReceiveReport.getGroupBy()==SrcReceiveReport.GROUP_TRANSAKSI){
                    receiveReport.setPurchNumber(rs.getString("number"));
                }else{
                    receiveReport.setPurchNumber("");
                }
 
                list.add(receiveReport);
            }   
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        
        finally{
            CONResultSet.close(dbrs);
        }
        
        return list;
    }

    public static Vector getPOBySubCategory(SrcReceiveReport srcReceiveReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {
            String sql = "SELECT sum(pi.qty) as qty, ic.name, p.number, p.vendor_id, " +
                    "sum(pi.total_amount) as total_amount, pi.amount " +
                    "FROM `pos_receive_item` as pi " +
                    "inner join pos_receive p on pi.receive_id=p.receive_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id " +
                    "inner join pos_item_category as ic on im.item_category_id=ic.item_category_id ";

            String where = "";
            if(srcReceiveReport.getVendorId()!=0){
                where = "p.vendor_id="+srcReceiveReport.getVendorId();
            }

            if(srcReceiveReport.getLocationId()!=0){
                if(where.length()>0){
                    where = where + " and p.location_id="+srcReceiveReport.getLocationId();
                }else{
                    where = "p.location_id="+srcReceiveReport.getLocationId();
                }
            }

            if(srcReceiveReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and p.date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "p.date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcReceiveReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and p.status='"+srcReceiveReport.getStatus()+"'";
                }else{
                    where = "p.status='"+srcReceiveReport.getStatus()+"'";
                }
            }

            if(srcReceiveReport.getItemCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_category_id="+srcReceiveReport.getItemCategoryId();
                }else{
                    where = "im.item_category_id="+srcReceiveReport.getItemCategoryId();
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            sql = sql + " group by ";  

            switch(srcReceiveReport.getGroupBy()){
                case SrcReceiveReport.GROUP_TRANSAKSI:
                    sql = sql + "p.receive_id ";
                    break;
                case SrcReceiveReport.GROUP_SUB_CATEGORY:
                    sql = sql + "ic.item_category_id ";
                    break;   
            } 

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet(); 
            while (rs.next()) {
                ReceiveReport receiveReport = new ReceiveReport();
                receiveReport.setItemCategory(rs.getString("name"));
                receiveReport.setTotalQty(rs.getDouble("qty"));
                receiveReport.setPurchAmount(rs.getDouble("total_amount"));

                if(srcReceiveReport.getGroupBy()==SrcReceiveReport.GROUP_TRANSAKSI){
                    receiveReport.setPurchNumber(rs.getString("number"));
                }else{
                    receiveReport.setPurchNumber("");
                }

                list.add(receiveReport);
            }
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        
        finally{
            CONResultSet.close(dbrs);
        }
        
        return list;
    }

    public static Vector getPOByItem(SrcReceiveReport srcReceiveReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {
            
            String sql = "";
            if(srcReceiveReport.getGroupBy()==SrcReceiveReport.GROUP_TRANSAKSI){
              sql = "SELECT pi.qty, im.code, im.barcode, im.name, p.number, p.vendor_id, " +
                    "pi.total_amount, pi.amount " +
                    "FROM `pos_receive_item` as pi " +
                    "inner join pos_receive p on pi.receive_id=p.receive_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id ";
            }else{
              sql = "SELECT sum(pi.qty) as qty, im.code, im.barcode, im.name, p.number, p.vendor_id, " +
                    "sum(pi.total_amount) as total_amount, pi.amount " +
                    "FROM `pos_receive_item` as pi " +
                    "inner join pos_receive p on pi.receive_id=p.receive_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id ";
            }

            String where = "";
            if(srcReceiveReport.getVendorId()!=0){
                where = "p.vendor_id="+srcReceiveReport.getVendorId();
            }

            if(srcReceiveReport.getLocationId()!=0){
                if(where.length()>0){
                    where = where + " and p.location_id="+srcReceiveReport.getLocationId();
                }else{
                    where = "p.location_id="+srcReceiveReport.getLocationId();
                }
            }

            if(srcReceiveReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and p.date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "p.date between '"+JSPFormater.formatDate(srcReceiveReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcReceiveReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcReceiveReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and p.status='"+srcReceiveReport.getStatus()+"'";
                }else{
                    where = "p.status='"+srcReceiveReport.getStatus()+"'";
                }
            }

            if(srcReceiveReport.getItemCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_group_id="+srcReceiveReport.getItemCategoryId();
                }else{
                    where = "im.item_group_id="+srcReceiveReport.getItemCategoryId();
                }
            }

            if(srcReceiveReport.getItemSubCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_category_id="+srcReceiveReport.getItemSubCategoryId();
                }else{
                    where = "im.item_category_id="+srcReceiveReport.getItemSubCategoryId();
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            //sql = sql + " group by ";

            /*
            switch(srcReceiveReport.getGroupBy()){
                case SrcReceiveReport.GROUP_TRANSAKSI:
                    sql = sql + "p.receive_id ";
                    break;
                case SrcReceiveReport.GROUP_ITEM:
                    sql = sql + "im.item_master_id ";
                    break;
            }
             */
            
            if(srcReceiveReport.getGroupBy()==SrcReceiveReport.GROUP_ITEM){
                sql = sql + "group by im.item_master_id ";
            }

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                ReceiveReport receiveReport = new ReceiveReport();
                receiveReport.setItemCode(rs.getString("code"));
                receiveReport.setItemName(rs.getString("name"));
                receiveReport.setTotalQty(rs.getDouble("qty"));
                receiveReport.setPurchAmount(rs.getDouble("total_amount"));

                if(srcReceiveReport.getGroupBy()==SrcReceiveReport.GROUP_TRANSAKSI){
                    receiveReport.setPurchNumber(rs.getString("number"));
                }else{
                    receiveReport.setPurchNumber("");
                }

                list.add(receiveReport); 
            } 
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        
        finally{
            CONResultSet.close(dbrs);
        }
        
        return list;
    }

}
