package com.project.fms.transaction; 
 
import java.util.Date;
import com.project.main.entity.*;

public class CashReceiveDetail extends Entity { 

	private long cashReceiveId;
	private long coaId;
	private long foreignCurrencyId;
	private double foreignAmount;
	private double bookedRate;
	private double amount;
	private String memo = "";

        /**
         * Holds value of property inOut.
         */
        private int inOut;
        
        /**
         * Holds value of property customerId.
         */
        private long customerId;
        
        /**
         * Holds value of property bymhdCoaId.
         */
        private long bymhdCoaId;
        
	public long getCashReceiveId(){ 
		return cashReceiveId; 
	} 

	public void setCashReceiveId(long cashReceiveId){ 
		this.cashReceiveId = cashReceiveId; 
	} 

	public long getCoaId(){ 
		return coaId; 
	} 

	public void setCoaId(long coaId){ 
		this.coaId = coaId; 
	} 

	public long getForeignCurrencyId(){ 
		return foreignCurrencyId; 
	} 

	public void setForeignCurrencyId(long foreignCurrencyId){ 
		this.foreignCurrencyId = foreignCurrencyId; 
	} 

	public double getForeignAmount(){ 
		return foreignAmount; 
	} 

	public void setForeignAmount(double foreignAmount){ 
		this.foreignAmount = foreignAmount; 
	} 

	public double getBookedRate(){ 
		return bookedRate; 
	} 

	public void setBookedRate(double bookedRate){ 
		this.bookedRate = bookedRate; 
	} 

	public double getAmount(){ 
		return amount; 
	} 

	public void setAmount(double amount){ 
		this.amount = amount; 
	} 

	public String getMemo(){ 
		return memo; 
	} 

	public void setMemo(String memo){ 
		if ( memo == null ) {
			memo = ""; 
		} 
		this.memo = memo; 
	} 

        /**
         * Getter for property inOut.
         * @return Value of property inOut.
         */
        public int getInOut() {
            return this.inOut;
        }
        
        /**
         * Setter for property inOut.
         * @param inOut New value of property inOut.
         */
        public void setInOut(int inOut) {
            this.inOut = inOut;
        }
        
        /**
         * Getter for property customerId.
         * @return Value of property customerId.
         */
        public long getCustomerId() {
            return this.customerId;
        }
        
        /**
         * Setter for property customerId.
         * @param customerId New value of property customerId.
         */
        public void setCustomerId(long customerId) {
            this.customerId = customerId;
        }
        
        /**
         * Getter for property bymhdCoaId.
         * @return Value of property bymhdCoaId.
         */
        public long getBymhdCoaId() {
            return this.bymhdCoaId;
        }
        
        /**
         * Setter for property bymhdCoaId.
         * @param bymhdCoaId New value of property bymhdCoaId.
         */
        public void setBymhdCoaId(long bymhdCoaId) {
            this.bymhdCoaId = bymhdCoaId;
        }
        
}
