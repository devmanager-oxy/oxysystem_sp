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
import com.project.general.DbLocation;
import com.project.general.Location;

/**
 *
 * @author Adnyana Eka Yasa
 */
public class SessTransferReport {

    public static Vector getTransferReport(SrcTransferReport srcTransferReport) {
        Vector list = new Vector();
        switch (srcTransferReport.getTypeReport()) {
            case SrcTransferReport.GROUP_BY_SUPPLIER:
                list = getPOBySupplier(srcTransferReport);
                break;
            case SrcTransferReport.GROUP_BY_SUB_CATEGORY:
                list = getTransferBySubCategory(srcTransferReport);
                break;
            case SrcTransferReport.GROUP_BY_CATEGORY:
                list = getPOByCategory(srcTransferReport);
                break;
            case SrcTransferReport.GROUP_BY_ITEM:
                list = getPOByItem(srcTransferReport);
                break;
            case SrcTransferReport.GROUP_BY_LOCATION:
                list = getTransferByLocation(srcTransferReport);
                break; 
        }
        return list;
    }

    public static Vector getPOBySupplier(SrcTransferReport srcTransferReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector(); 
        try {
            String sql = "SELECT number, count(transfer_id) as cnt, vendor_id, sum(total_amount) as total_amount " +
                    "FROM `pos_transfer` ";

            String where = "";
            if(srcTransferReport.getVendorId()!=0){
                where = "vendor_id="+srcTransferReport.getVendorId();
            }

            if(srcTransferReport.getLocationId()!=0){
                if(where.length()>0){
                    where = where + " and location_id="+srcTransferReport.getLocationId();
                }else{
                    where = "location_id="+srcTransferReport.getLocationId();
                }
            }

            if(srcTransferReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcTransferReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and status='"+srcTransferReport.getStatus()+"'";
                }else{
                    where = "status='"+srcTransferReport.getStatus()+"'";
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            sql = sql + " group by ";

            switch(srcTransferReport.getGroupBy()){
                case SrcTransferReport.GROUP_TRANSAKSI:
                    sql = sql + "transfer_id ";
                    break;
                case SrcTransferReport.GROUP_SUPPLIER:
                    sql = sql + "vendor_id ";
                    break;
            }

            System.out.println("SQL : " + sql); 
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet(); 
            while (rs.next()) { 
                TransferReport transferReport = new TransferReport();
                switch(srcTransferReport.getGroupBy()){
                    case SrcTransferReport.GROUP_TRANSAKSI:
                        transferReport.setPurchNumber(rs.getString("number"));
                        break;
                    case SrcTransferReport.GROUP_SUPPLIER:
                        transferReport.setPurchNumber(""+rs.getInt("cnt"));
                        break;
                }

                transferReport.setPurchAmount(rs.getDouble("total_amount"));
                long vendorOid = rs.getLong("vendor_id");
                System.out.println("vendorOid : " + vendorOid);
                transferReport.setVendorId(vendorOid);
                Vendor vendor = new Vendor();
                try {
                    vendor = DbVendor.fetchExc(vendorOid);
                } catch (Exception ex) {
                    System.out.println("Err >>> : " + ex.toString());
                }
                transferReport.setVendorName(vendor.getName());

                list.add(transferReport);
            }
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        return list;
    }

    public static Vector getTransferByLocation(SrcTransferReport srcTransferReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {
            String sql = "SELECT number, count(transfer_id) as cnt, from_location_id, to_location_id  " +
                    "FROM `pos_transfer` as pt inner join pos_location as pl " +
                    "on pt.from_location_id=pl.location_id ";

            String where = ""; 
            if(srcTransferReport.getLocationToId()!=0){
                where = "pt.to_location_id="+srcTransferReport.getLocationToId();
            }

            if(srcTransferReport.getLocationId()!=0){
                if(where.length()>0){  
                    where = where + " and pt.from_location_id="+srcTransferReport.getLocationId();
                }else{
                    where = "pt.from_location_id="+srcTransferReport.getLocationId();
                }
            }
  
            if(srcTransferReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and pt.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "pt.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcTransferReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and pt.status='"+srcTransferReport.getStatus()+"'";
                }else{
                    where = "pt.status='"+srcTransferReport.getStatus()+"'";
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            sql = sql + " group by ";

            switch(srcTransferReport.getGroupBy()){
                case SrcTransferReport.GROUP_TRANSAKSI:
                    sql = sql + "pt.transfer_id ";
                    break;
                case SrcTransferReport.GROUP_LOCATION:
                    sql = sql + "pt.from_location_id ";
                    break;
                case SrcTransferReport.GROUP_TO_LOCATION:
                    sql = sql + "pt.to_location_id ";
                    break;
            }

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                TransferReport transferReport = new TransferReport();
                switch(srcTransferReport.getGroupBy()){
                    case SrcTransferReport.GROUP_TRANSAKSI:
                        transferReport.setPurchNumber(rs.getString("number"));
                        break;
                    case SrcTransferReport.GROUP_LOCATION:
                        transferReport.setPurchNumber(""+rs.getInt("cnt"));
                        break;
                    case SrcTransferReport.GROUP_TO_LOCATION:
                        transferReport.setPurchNumber(""+rs.getInt("cnt"));
                        break;
                } 

                // transferReport.setPurchAmount(rs.getDouble("total_amount"));
                long fromLocationOid = rs.getLong("from_location_id");
                long ToLocationOid = rs.getLong("to_location_id");

                Location location = new Location();
                try {
                    location = DbLocation.fetchExc(fromLocationOid);

                } catch (Exception ex) {
                    System.out.println("Err >>> : " + ex.toString());
                }
                transferReport.setLocationName(location.getName());

                // location to
                Location toLocation = new Location();
                try {
                    toLocation = DbLocation.fetchExc(ToLocationOid);

                } catch (Exception ex) {
                    System.out.println("Err >>> : " + ex.toString());
                }
                transferReport.setLocationToName(toLocation.getName());

                list.add(transferReport);
            }
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        return list;
    }

    public static Vector getPOByCategory(SrcTransferReport srcTransferReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {   
            String sql = "SELECT sum(pi.qty) as qty, ic.name, p.number, " +
                    "sum(pi.amount) as total_amount, pi.price " +
                    "FROM `pos_transfer_item` as pi " +
                    "inner join pos_transfer p on pi.transfer_id=p.transfer_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id " +
                    "inner join pos_item_group as ic on im.item_group_id=ic.item_group_id ";

            String where = "";
            if(srcTransferReport.getLocationToId()!=0){
                where = "p.to_location_id="+srcTransferReport.getLocationToId();
            }

            if(srcTransferReport.getLocationId()!=0){
                if(where.length()>0){ 
                    where = where + " and p.from_location_id="+srcTransferReport.getLocationId();
                }else{
                    where = "p.from_location_id="+srcTransferReport.getLocationId();
                }
            }

            if(srcTransferReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and p.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "p.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcTransferReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and p.status='"+srcTransferReport.getStatus()+"'";
                }else{
                    where = "p.status='"+srcTransferReport.getStatus()+"'";
                }
            }

            if(srcTransferReport.getItemCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_group_id="+srcTransferReport.getItemCategoryId();
                }else{
                    where = "im.item_group_id="+srcTransferReport.getItemCategoryId();
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            sql = sql + " group by ";

            switch(srcTransferReport.getGroupBy()){
                case SrcTransferReport.GROUP_TRANSAKSI:
                    sql = sql + "p.transfer_id ";
                    break;
                case SrcTransferReport.GROUP_CATEGORY:
                    sql = sql + "ic.item_group_id ";
                    break;
            }

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                TransferReport transferReport = new TransferReport();
                transferReport.setItemCategory(rs.getString("name"));
                transferReport.setTotalQty(rs.getDouble("qty"));
                transferReport.setPurchAmount(rs.getDouble("total_amount"));

                if(srcTransferReport.getGroupBy()==SrcTransferReport.GROUP_TRANSAKSI){
                    transferReport.setPurchNumber(rs.getString("number"));
                }else{
                    transferReport.setPurchNumber("");
                }
 
                list.add(transferReport);
            }   
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        return list;
    }

    public static Vector getTransferBySubCategory(SrcTransferReport srcTransferReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {   
            String sql = "SELECT sum(pi.qty) as qty, ic.name, p.number, " +
                    "sum(pi.amount) as total_amount, pi.amount " +
                    "FROM `pos_transfer_item` as pi " +
                    "inner join pos_transfer p on pi.transfer_id=p.transfer_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id " +
                    "inner join pos_item_category as ic on im.item_category_id=ic.item_category_id ";

            String where = "";
            if(srcTransferReport.getLocationToId()!=0){
                where = "p.to_location_id="+srcTransferReport.getLocationToId();
            }

            if(srcTransferReport.getLocationId()!=0){
                if(where.length()>0){
                    where = where + " and p.from_location_id="+srcTransferReport.getLocationId();
                }else{
                    where = "p.from_location_id="+srcTransferReport.getLocationId();
                }
            }

            if(srcTransferReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and p.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "p.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcTransferReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and p.status='"+srcTransferReport.getStatus()+"'";
                }else{
                    where = "p.status='"+srcTransferReport.getStatus()+"'";
                }
            }

            if(srcTransferReport.getItemCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_category_id="+srcTransferReport.getItemCategoryId();
                }else{
                    where = "im.item_category_id="+srcTransferReport.getItemCategoryId();
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            sql = sql + " group by ";  

            switch(srcTransferReport.getGroupBy()){
                case SrcTransferReport.GROUP_TRANSAKSI:
                    sql = sql + "p.transfer_id ";
                    break;
                case SrcTransferReport.GROUP_SUB_CATEGORY:
                    sql = sql + "ic.item_category_id ";
                    break;   
            } 

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet(); 
            while (rs.next()) {
                TransferReport transferReport = new TransferReport();
                transferReport.setItemCategory(rs.getString("name"));
                transferReport.setTotalQty(rs.getDouble("qty"));
                transferReport.setPurchAmount(rs.getDouble("total_amount"));

                if(srcTransferReport.getGroupBy()==SrcTransferReport.GROUP_TRANSAKSI){
                    transferReport.setPurchNumber(rs.getString("number"));
                }else{
                    transferReport.setPurchNumber("");
                }

                list.add(transferReport);
            }
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        return list;
    }

    public static Vector getPOByItem(SrcTransferReport srcTransferReport) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {
            
            String sql = "";
            if(srcTransferReport.getGroupBy()==SrcTransferReport.GROUP_TRANSAKSI){
              sql = "SELECT pi.qty, im.code, im.barcode, im.name, p.number, " +
                    "pi.amount, pi.price " +
                    "FROM `pos_transfer_item` as pi " +
                    "inner join pos_transfer p on pi.transfer_id=p.transfer_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id ";
            }else{
              sql = "SELECT sum(pi.qty) as qty, im.code, im.barcode, im.name, p.number, " +
                    "sum(pi.amount) as amount, pi.price " +
                    "FROM `pos_transfer_item` as pi " +
                    "inner join pos_transfer p on pi.transfer_id=p.transfer_id " +
                    "inner join pos_item_master as im on pi.item_master_id=im.item_master_id ";
            }

            String where = "";
            if(srcTransferReport.getLocationToId()!=0){
                where = "p.to_location_id="+srcTransferReport.getLocationToId();
            }

            if(srcTransferReport.getLocationId()!=0){
                if(where.length()>0){
                    where = where + " and p.from_location_id="+srcTransferReport.getLocationId();
                }else{
                    where = "p.from_location_id="+srcTransferReport.getLocationId();
                }
            }

            if(srcTransferReport.getIgnoreDate() == 0){
                if(where.length()>0){
                    where = where + " and p.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }else{
                    where = "p.date between '"+JSPFormater.formatDate(srcTransferReport.getFromDate(), "yyyy-MM-dd 00:00:01")+"'"+
                            " and '"+JSPFormater.formatDate(srcTransferReport.getToDate(), "yyyy-MM-dd 23:59:59")+"'";
                }
            }

            if(srcTransferReport.getStatus().length()>0){
                if(where.length()>0){
                    where = where + " and p.status='"+srcTransferReport.getStatus()+"'";
                }else{
                    where = "p.status='"+srcTransferReport.getStatus()+"'";
                }
            }

            if(srcTransferReport.getItemCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_group_id="+srcTransferReport.getItemCategoryId();
                }else{
                    where = "im.item_group_id="+srcTransferReport.getItemCategoryId();
                }
            }

            if(srcTransferReport.getItemSubCategoryId()!=0){
                if(where.length()>0){
                    where = where + " and im.item_category_id="+srcTransferReport.getItemSubCategoryId();
                }else{
                    where = "im.item_category_id="+srcTransferReport.getItemSubCategoryId();
                }
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;

            /*
            sql = sql + " group by ";

            switch(srcTransferReport.getGroupBy()){
                case SrcTransferReport.GROUP_TRANSAKSI:
                    sql = sql + "p.transfer_id ";
                    break;
                case SrcTransferReport.GROUP_ITEM:
                    sql = sql + "im.item_master_id ";
                    break;
            }
             */
            
            if(srcTransferReport.getGroupBy()==SrcTransferReport.GROUP_ITEM){
                sql = sql + "group by im.item_master_id ";
            }

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while (rs.next()) {
                TransferReport transferReport = new TransferReport();
                transferReport.setItemCode(rs.getString("code"));
                transferReport.setItemName(rs.getString("name"));
                transferReport.setTotalQty(rs.getDouble("qty"));
                transferReport.setPurchAmount(rs.getDouble("amount"));

                if(srcTransferReport.getGroupBy()==SrcTransferReport.GROUP_TRANSAKSI){
                    transferReport.setPurchNumber(rs.getString("number"));
                }else{
                    transferReport.setPurchNumber(""); 
                }

                list.add(transferReport);
            } 
        } catch (Exception e) {
            System.out.println("Err >>> : " + e.toString());
        }
        return list;
    }
}