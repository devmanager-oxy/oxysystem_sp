/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.ccs.postransaction.receiving;
import com.project.main.entity.Entity;
import java.util.Date;

public class ReceiveItem extends Entity {

    private long receiveId;
    private long itemMasterId;
    private double qty;
    private long uomId;
    private String status;
    private double amount;
    private double totalAmount;
    private double totalDiscount;
    private Date deliveryDate;

    /**
     * Holds value of property purchaseItemId.
     */
    private long purchaseItemId;
    
    /**
     * Holds value of property expiredDate.
     */
    private Date expiredDate;
    
    /**
     * Holds value of property apCoaId.
     */
    private long apCoaId;
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    } 

    public long getItemMasterId() {
        return itemMasterId;
    }

    public void setItemMasterId(long itemMasterId) {
        this.itemMasterId = itemMasterId;
    }

    public long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(long receiveId) {
        this.receiveId = receiveId;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public long getUomId() {
        return uomId;
    }

    public void setUomId(long uomId) {
        this.uomId = uomId;
    }
    
    /**
     * Getter for property purchaseItemId.
     * @return Value of property purchaseItemId.
     */
    public long getPurchaseItemId() {
        return this.purchaseItemId;
    }
    
    /**
     * Setter for property purchaseItemId.
     * @param purchaseItemId New value of property purchaseItemId.
     */
    public void setPurchaseItemId(long purchaseItemId) {
        this.purchaseItemId = purchaseItemId;
    }
    
    /**
     * Getter for property expiredDate.
     * @return Value of property expiredDate.
     */
    public Date getExpiredDate() {
        return this.expiredDate;
    }
    
    /**
     * Setter for property expiredDate.
     * @param expiredDate New value of property expiredDate.
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
    
    /**
     * Getter for property apCoaId.
     * @return Value of property apCoaId.
     */
    public long getApCoaId() {
        return this.apCoaId;
    }
    
    /**
     * Setter for property apCoaId.
     * @param apCoaId New value of property apCoaId.
     */
    public void setApCoaId(long apCoaId) {
        this.apCoaId = apCoaId;
    }
    
}
