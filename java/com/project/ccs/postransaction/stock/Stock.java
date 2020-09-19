
package com.project.ccs.postransaction.stock; 
 
import java.util.Date;
import com.project.main.entity.Entity;

public class Stock extends Entity { 

	private long locationId;
	private int type;
	private double qty;
	private double price;
	private double total;
	private long itemMasterId;
	private String itemCode = "";
	private String itemBarcode = "";
	private String itemName = "";
	private long unitId;
	private String unit = "";
	private int inOut;
	private Date date;
	private long userId;
	private String noFaktur = "";
	private long incomingId;
	private long returId;
	private long transferId;
	private long transferInId;
	private long adjustmentId;

        /**
         * Holds value of property opnameId.
         */
        private long opnameId;
        
        /**
         * Holds value of property salesDetailId.
         */
        private long salesDetailId;
        
	public long getLocationId(){ 
		return locationId; 
	} 

	public void setLocationId(long locationId){ 
		this.locationId = locationId; 
	} 

	public int getType(){ 
		return type; 
	} 

	public void setType(int type){ 
		this.type = type; 
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

	public double getTotal(){ 
		return total; 
	} 

	public void setTotal(double total){ 
		this.total = total; 
	} 

	public long getItemMasterId(){ 
		return itemMasterId; 
	} 

	public void setItemMasterId(long itemMasterId){ 
		this.itemMasterId = itemMasterId; 
	} 

	public String getItemCode(){ 
		return itemCode; 
	} 

	public void setItemCode(String itemCode){ 
		if ( itemCode == null ) {
			itemCode = ""; 
		} 
		this.itemCode = itemCode; 
	} 

	public String getItemBarcode(){ 
		return itemBarcode; 
	} 

	public void setItemBarcode(String itemBarcode){ 
		if ( itemBarcode == null ) {
			itemBarcode = ""; 
		} 
		this.itemBarcode = itemBarcode; 
	} 

	public String getItemName(){ 
		return itemName; 
	} 

	public void setItemName(String itemName){ 
		if ( itemName == null ) {
			itemName = ""; 
		} 
		this.itemName = itemName; 
	} 

	public long getUnitId(){ 
		return unitId; 
	} 

	public void setUnitId(long unitId){ 
		this.unitId = unitId; 
	} 

	public String getUnit(){ 
		return unit; 
	} 

	public void setUnit(String unit){ 
		if ( unit == null ) {
			unit = ""; 
		} 
		this.unit = unit; 
	} 

	public int getInOut(){ 
		return inOut; 
	} 

	public void setInOut(int inOut){ 
		this.inOut = inOut; 
	} 

	public Date getDate(){ 
		return date; 
	} 

	public void setDate(Date date){ 
		this.date = date; 
	} 

	public long getUserId(){ 
		return userId; 
	} 

	public void setUserId(long userId){ 
		this.userId = userId; 
	} 

	public String getNoFaktur(){ 
		return noFaktur; 
	} 

	public void setNoFaktur(String noFaktur){ 
		if ( noFaktur == null ) {
			noFaktur = ""; 
		} 
		this.noFaktur = noFaktur; 
	} 

	public long getIncomingId(){ 
		return incomingId; 
	} 

	public void setIncomingId(long incomingId){ 
		this.incomingId = incomingId; 
	} 

	public long getReturId(){ 
		return returId; 
	} 

	public void setReturId(long returId){ 
		this.returId = returId; 
	} 

	public long getTransferId(){ 
		return transferId; 
	} 

	public void setTransferId(long transferId){ 
		this.transferId = transferId; 
	} 

	public long getTransferInId(){ 
		return transferInId; 
	} 

	public void setTransferInId(long transferInId){ 
		this.transferInId = transferInId; 
	} 

	public long getAdjustmentId(){ 
		return adjustmentId; 
	} 

	public void setAdjustmentId(long adjustmentId){ 
		this.adjustmentId = adjustmentId; 
	} 

        /**
         * Getter for property opnameId.
         * @return Value of property opnameId.
         */
        public long getOpnameId() {
            return this.opnameId;
        }
        
        /**
         * Setter for property opnameId.
         * @param opnameId New value of property opnameId.
         */
        public void setOpnameId(long opnameId) {
            this.opnameId = opnameId;
        }
        
        /**
         * Getter for property salesDetailId.
         * @return Value of property salesDetailId.
         */
        public long getSalesDetailId() {
            return this.salesDetailId;
        }
        
        /**
         * Setter for property salesDetailId.
         * @param salesDetailId New value of property salesDetailId.
         */
        public void setSalesDetailId(long salesDetailId) {
            this.salesDetailId = salesDetailId;
        }
        
}
