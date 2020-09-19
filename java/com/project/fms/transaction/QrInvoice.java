/*
 * QrInvoice.java
 *
 * Created on January 16, 2008, 3:38 PM
 */

package com.project.fms.transaction;

import java.util.*;
import java.sql.*;
//import com.project.fms.transaction.*;
import java.util.Date;
import com.project.main.db.*;
import com.project.general.*;
import com.project.*;
import com.project.util.*;
import com.project.general.Company;
import com.project.general.DbCompany;
import com.project.general.Location;
import com.project.general.DbLocation;
import com.project.ccs.postransaction.receiving.*;
import com.project.ccs.postransaction.purchase.Purchase;
import com.project.ccs.postransaction.purchase.DbPurchase;

/**
 *
 * @author  Valued Customer
 */
public class QrInvoice {
    
    /** Creates a new instance of QrInvoice */
    public QrInvoice() {
    }
    
    public static Vector searchForInvoice(InvoiceSrc invSrc){
        
        Vector result = new Vector();        
        
        /*String sql = "select distinct v.* from "+DbVendor.DB_VENDOR+" v inner join "+DbInvoice.DB_INVOICE+" i "+
            " on i."+DbInvoice.colNames[DbInvoice.COL_VENDOR_ID]+" = "+
            " v."+DbVendor.colNames[DbVendor.COL_VENDOR_ID];
         */
        String sql = "select distinct v.* from "+DbVendor.DB_VENDOR+" v inner join "+DbReceive.DB_RECEIVE+" i "+
            " on i."+DbReceive.colNames[DbReceive.COL_VENDOR_ID]+" = "+
            " v."+DbVendor.colNames[DbVendor.COL_VENDOR_ID];
        
        sql = sql + " where i."+DbReceive.colNames[DbReceive.COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"'";
        
        if(invSrc.getVendorId()!=0){
            sql = sql + " and i."+DbReceive.colNames[DbReceive.COL_VENDOR_ID]+"="+invSrc.getVendorId();
        }
        
        if(invSrc.getOverDue()==1){
            sql = sql + " and i."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+"<='"+JSPFormater.formatDate(invSrc.getDueDate(), "yyyy-MM-dd")+"'";            
        }
        else{
            sql = sql + " and i."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+"='"+JSPFormater.formatDate(invSrc.getDueDate(), "yyyy-MM-dd")+"'";            
        }
        
        if(invSrc.getVndInvNumber().length()>0){
            sql = sql + " and (i."+DbReceive.colNames[DbReceive.COL_INVOICE_NUMBER]+" like '%"+invSrc.getVndInvNumber()+"%'"+
                " or i."+DbReceive.colNames[DbReceive.COL_DO_NUMBER]+" like '%"+invSrc.getVndInvNumber()+"%')";
        }
        
        System.out.println(sql);
        
        CONResultSet crs = null;
        try{
            crs = CONHandler.execQueryResult(sql);
            ResultSet rs = crs.getResultSet();
            while(rs.next()){
                Vendor v = new Vendor();
                DbVendor.resultToObject(rs, v);
                //Invoice i = new Invoice();
                //DbInvoice.resultToObject(rs, i);
                
                Vector openInvoices = getOpenInvoiceByVendor(v.getOID(), invSrc);
                if(openInvoices!=null && openInvoices.size()>0){
                    Vector vx = new Vector();
                    vx.add(v);
                    vx.add(openInvoices);
                    result.add(vx);
                }
                
            }
        }
        catch(Exception e){
        }
        finally{
            CONResultSet.close(crs);
        }
        
        return result;
        
    }
    
    public static Vector getOpenInvoiceByVendor(long vendorId, InvoiceSrc invSrc){
        
        Vector result = new Vector();
        
        /*String sql = "select p."+DbPurchase.colNames[DbPurchase.COL_PURCHASE_ID]+
            ", i.* from "+DbReceive.DB_RECEIVE+" i inner join "+DbPurchase.DB_PURCHASE+" p "+
            " on i."+DbReceive.colNames[DbReceive.COL_PURCHASE_ID]+" = "+
            " p."+DbPurchase.colNames[DbPurchase.COL_PURCHASE_ID]+
            " where i."+DbReceive.colNames[DbReceive.COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"' "+
            " and i."+DbReceive.colNames[DbReceive.COL_PAYMENT_STATUS]+"<>"+I_Project.INV_STATUS_FULL_PAID+
            " and i."+DbReceive.colNames[DbReceive.COL_VENDOR_ID]+"="+vendorId;
         */
        
        String sql = //"select p."+DbPurchase.colNames[DbPurchase.COL_PURCHASE_ID]+
            "select * from "+DbReceive.DB_RECEIVE+" i "+
            " where i."+DbReceive.colNames[DbReceive.COL_STATUS]+"='"+I_Project.DOC_STATUS_CHECKED+"' "+
            " and i."+DbReceive.colNames[DbReceive.COL_PAYMENT_STATUS]+"<>"+I_Project.INV_STATUS_FULL_PAID;
        
        if(vendorId!=0){
            sql = sql +" and i."+DbReceive.colNames[DbReceive.COL_VENDOR_ID]+"="+vendorId;
        }
        
        
        if(invSrc.getOverDue()==1){
            sql = sql + " and i."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+"<='"+JSPFormater.formatDate(invSrc.getDueDate(), "yyyy-MM-dd")+"'";            
        }
        else{
            sql = sql + " and i."+DbReceive.colNames[DbReceive.COL_DUE_DATE]+"='"+JSPFormater.formatDate(invSrc.getDueDate(), "yyyy-MM-dd")+"'";            
        }
        
        if(invSrc.getVndInvNumber().length()>0){
            sql = sql + " and (i."+DbReceive.colNames[DbReceive.COL_INVOICE_NUMBER]+" like '%"+invSrc.getVndInvNumber()+"%'"+
                " or i."+DbReceive.colNames[DbReceive.COL_DO_NUMBER]+" like '%"+invSrc.getVndInvNumber()+"%')";            
        }
        
        sql = sql + " order by i."+DbReceive.colNames[DbReceive.COL_VENDOR_ID];
        
        System.out.println("\n"+sql);
        
        CONResultSet crs = null;
        try{
            crs = CONHandler.execQueryResult(sql);
            ResultSet rs = crs.getResultSet();
            while(rs.next()){
                Purchase purc = new Purchase();
                //DbPurchase.resultToObject(rs, purc);  
                
                try{
                    purc = DbPurchase.fetchExc(purc.getOID());
                }
                catch(Exception e){
                    
                }
                
                Receive i = new Receive();
                DbReceive.resultToObject(rs, i);
                Vector vx = new Vector();
                vx.add(purc);
                vx.add(i);
                result.add(vx);
            }
        }
        catch(Exception e){
        }
        finally{
            CONResultSet.close(crs);
        }
        
        return result;
        
    }
    
}
