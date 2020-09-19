
package com.project.ccs.postransaction.transfer; 
 
import java.util.Date;
import com.project.main.entity.*;

public class TransferItem extends Entity { 

	private long transferId;
	private long itemMasterId;
	private double qty;
	private double price;
	private double amount;
	private String note = "";
	private Date expDate;

	public long getTransferId(){ 
		return transferId; 
	} 

	public void setTransferId(long transferId){ 
		this.transferId = transferId; 
	} 

	public long getItemMasterId(){ 
		return itemMasterId; 
	} 

	public void setItemMasterId(long itemMasterId){ 
		this.itemMasterId = itemMasterId; 
	} 

	public double getQty(){ 
		return qty; 
	} 

	public void setQty(double qty){ 
		this.qty = qty; 
	} 

	public double getPrice(){ 
		return price; 
	} 

	public void setPrice(double price){ 
		this.price = price; 
	} 

	public double getAmount(){ 
		return amount; 
	} 

	public void setAmount(double amount){ 
		this.amount = amount; 
	} 

	public String getNote(){ 
		return note; 
	} 

	public void setNote(String note){ 
		if ( note == null ) {
			note = ""; 
		} 
		this.note = note; 
	} 

	public Date getExpDate(){ 
		return expDate; 
	} 

	public void setExpDate(Date expDate){ 
		this.expDate = expDate; 
	} 

}
