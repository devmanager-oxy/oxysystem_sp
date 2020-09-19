
package com.project.ccs.posmaster; 
 
import java.util.Date;
import com.project.main.entity.*;

public class VendorItem extends Entity { 

	private long itemMasterId;
	private long vendorId;
	private double lastPrice;
	private double lastDiscount;
	private Date updateDate;

	public long getItemMasterId(){ 
		return itemMasterId; 
	} 

	public void setItemMasterId(long itemMasterId){ 
		this.itemMasterId = itemMasterId; 
	} 

	public long getVendorId(){ 
		return vendorId; 
	} 

	public void setVendorId(long vendorId){ 
		this.vendorId = vendorId; 
	} 

	public double getLastPrice(){ 
		return lastPrice; 
	} 

	public void setLastPrice(double lastPrice){ 
		this.lastPrice = lastPrice; 
	} 

	public double getLastDiscount(){ 
		return lastDiscount; 
	} 

	public void setLastDiscount(double lastDiscount){ 
		this.lastDiscount = lastDiscount; 
	} 

	public Date getUpdateDate(){ 
		return updateDate; 
	} 

	public void setUpdateDate(Date updateDate){ 
		this.updateDate = updateDate; 
	} 

}
