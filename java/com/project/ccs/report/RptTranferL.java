/*
 * RptTranferL.java
 *
 * Created on July 19, 2009, 1:18 PM
 */

package com.project.ccs.report;

import java.util.Date;
import com.project.main.entity.*;

/**
 *
 * @author  Kyo
 */
public class RptTranferL {
    
    /**
     * Holds value of property name.
     */
    private String name;
    private double price;
    private double qty;
    private double total;
    
    /** Creates a new instance of RptTranferL */
    public RptTranferL() {
    }
    
    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Getter for property price.
     * @return Value of property price.
     */
    public double getPrice() {
        return this.price;
    }
    
    /**
     * Setter for property price.
     * @param price New value of property price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Getter for property qty.
     * @return Value of property qty.
     */
    public double getQty() {
        return this.qty;
    }
    
    /**
     * Setter for property qty.
     * @param qty New value of property qty.
     */
    public void setQty(double qty) {
        this.qty = qty;
    }
    
    /**
     * Getter for property total.
     * @return Value of property total.
     */
    public double getTotal() {
        return this.total;
    }
    
    /**
     * Setter for property total.
     * @param total New value of property total.
     */
    public void setTotal(double total) {
        this.total = total;
    }
    
}
