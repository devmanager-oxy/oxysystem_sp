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
import com.project.ccs.postransaction.stock.*;
import com.project.ccs.posmaster.*;

/**
 *
 * @author Adnyana Eka Yasa
 */
public class SessStockReport {

    public static Vector getStockByLocation(long oidLocation, int groupBy) {
        CONResultSet dbrs = null;
        Vector list = new Vector();
        try {
            String sql = "select im.code, im.name, sum(st.qty*st.in_out) as total_qty, im.cogs, sum(st.qty*st.in_out*im.cogs) as amounth from pos_stock as st "+
                         "inner join pos_item_master as im on st.item_master_id = im.item_master_id "+
                         "inner join pos_item_group as ig on im.item_group_id = ig.item_group_id "+
                         "inner join pos_item_category as ic on im.item_category_id = ic.item_category_id ";

            String where = ""; 
            if(oidLocation!=0){
                where = "st.location_id = "+oidLocation;
            }

            if(where.length()>0){
                where = " where "+where;
            }

            sql = sql + where;
            
            sql = sql + " group by ";

            switch(groupBy){
                case 0:
                    sql = sql + "st.item_master_id ";
                    break;
                case 1:
                    sql = sql + "im.item_group_id ";
                    break;
                case 2:
                    sql = sql + "im.item_category_id ";
                    break;
            }

            System.out.println("SQL : " + sql);
            dbrs = CONHandler.execQueryResult(sql);
            ResultSet rs = dbrs.getResultSet();
            while(rs.next()){
                /*
                Stock stock = new Stock();
                //DbStock.resultToObject(rs, stock); 
                 */
                SrcStockReportL srcReportL = new SrcStockReportL();
                
                srcReportL.setCode(rs.getString(1));
                srcReportL.setDescription(rs.getString(2));
                srcReportL.setQty(rs.getDouble(3));
                srcReportL.setPrice(rs.getDouble(4));
                srcReportL.setAmount(rs.getDouble(5));
                
                list.add(srcReportL);
            }

        }
        catch(Exception e){
        }
        finally{
            CONResultSet.close(dbrs);
        }

        return list;
    }
    
    
    public static Vector getItemStock(String itmCode, String itmName, long locationId, long groupId, int orderBy){
        
        String sql = "select " +
            " l."+DbLocation.colNames[DbLocation.COL_NAME]+" as loc "+
            ", g."+DbItemGroup.colNames[DbItemGroup.COL_NAME]+" as ig "+
            ", s."+DbStock.colNames[DbStock.COL_ITEM_MASTER_ID]+
            ", s."+DbStock.colNames[DbStock.COL_LOCATION_ID]+
            ", m."+DbItemMaster.colNames[DbItemMaster.COL_CODE]+
            ", m."+DbItemMaster.colNames[DbItemMaster.COL_NAME]+
            ", sum(s."+DbStock.colNames[DbStock.COL_QTY]+"*s."+DbStock.colNames[DbStock.COL_IN_OUT]+") qty " +
            ", m."+DbItemMaster.colNames[DbItemMaster.COL_COGS]+" as price " +
            " from "+DbStock.DB_POS_STOCK+" s "+
            " inner join "+DbItemMaster.DB_ITEM_MASTER+" m "+
            " on m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_MASTER_ID]+" = s."+DbStock.colNames[DbStock.COL_ITEM_MASTER_ID]+
            " inner join "+DbItemGroup.DB_ITEM_GROUP+" g "+
            " on g."+DbItemGroup.colNames[DbItemGroup.COL_ITEM_GROUP_ID]+" = m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]+
            " inner join "+DbLocation.DB_LOCATION+" l "+
            " on l."+DbLocation.colNames[DbLocation.COL_LOCATION_ID]+" = s."+DbStock.colNames[DbStock.COL_LOCATION_ID];
        
            String where = "";
            if(itmCode.length()>0){
                where = " m."+DbItemMaster.colNames[DbItemMaster.COL_CODE]+" like '%"+itmCode+"%'";
            }
            
            if(itmName.length()>0){
                if(where.length()>0){
                    where = "("+where +" or ";
                }
                
                where = where + " m."+DbItemMaster.colNames[DbItemMaster.COL_NAME]+" like '%"+itmName+"%'";
                
                if(itmCode.length()>0){
                    where = where +")";
                }
            }
            
            //0 = all location, 1=group by location
            if(locationId!=0 && locationId!=1){
                if(where.length()>0){
                    where = where +" and ";
                }
                
                where  = where + " s."+DbStock.colNames[DbStock.COL_LOCATION_ID]+"="+locationId;
            }
            
            if(groupId!=0){
                if(where.length()>0){
                    where = where +" and ";
                }
                
                where  = where + " m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID]+" = "+groupId;
            }
            
            if(where.length()>0){
                sql = sql + " where "+where;
            }
            
            sql = sql + " group by s."+DbStock.colNames[DbStock.COL_ITEM_MASTER_ID];
            
            if(locationId!=0){
                sql = sql + ", s."+DbStock.colNames[DbStock.COL_LOCATION_ID];
            }
            
            sql = sql + " order by ";
            if(orderBy==0){
                sql = sql + " s."+DbStock.colNames[DbStock.COL_LOCATION_ID];
            }
            else if(orderBy==1){
                sql = sql + " m."+DbItemMaster.colNames[DbItemMaster.COL_CODE];
            }
            else if(orderBy==2){                
                sql = sql + " m."+DbItemMaster.colNames[DbItemMaster.COL_NAME];
            }
            else{
                sql = sql + " m."+DbItemMaster.colNames[DbItemMaster.COL_ITEM_GROUP_ID];
            }
            
            System.out.println("\n\n"+sql);
            
            Vector result = new Vector();
            
            CONResultSet crs = null;
            try{
                crs = CONHandler.execQueryResult(sql);
                ResultSet rs = crs.getResultSet();
                while(rs.next()){
                    SrcStockReportL srcReportL = new SrcStockReportL();
                
                    srcReportL.setLocationName(rs.getString(1));
                    srcReportL.setGroupName(rs.getString(2));
                    srcReportL.setItemMasterId(rs.getLong(3));
                    srcReportL.setLocation(rs.getLong(4));
                    srcReportL.setCode(rs.getString(5));
                    srcReportL.setDescription(rs.getString(6));
                    srcReportL.setQty(rs.getDouble(7));
                    srcReportL.setPrice(rs.getDouble(8));
                    srcReportL.setAmount(srcReportL.getQty()*srcReportL.getPrice());

                    result.add(srcReportL);
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
    
    
}