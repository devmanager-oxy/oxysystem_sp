
/* Created on 	:  [date] [time] AM/PM 
 * 
 * @author	 :
 * @version	 :
 */

/*******************************************************************
 * Class Description 	: [project description ... ] 
 * Imput Parameters 	: [input parameter ...] 
 * Output 		: [output ...] 
 *******************************************************************/

package com.project.fms.transaction; 
 
/* package java */ 
import java.util.Date;

/* package qdep */
import com.project.main.entity.*;

public class BankpoPayment extends Entity { 

	private long coaId;
	private String journalNumber = "";
	private int journalCounter;
	private String journalPrefix = "";
	private Date date;
	private Date transDate;
	private String memo = "";
	private long operatorId;
	private String operatorName = "";
	private double amount;
	private String refNumber = "";
	private long vendorId;
	private long currencyId;
	private String paymentMethod = "";
        private String status = "";

        /**
         * Holds value of property accountBalance.
         */
        private double accountBalance;
        
        /**
         * Holds value of property paymentMethodId.
         */
        private long paymentMethodId;
        
	public long getCoaId(){ 
		return coaId; 
	} 

	public void setCoaId(long coaId){ 
		this.coaId = coaId; 
	} 

	public String getJournalNumber(){ 
		return journalNumber; 
	} 

	public void setJournalNumber(String journalNumber){ 
		if ( journalNumber == null ) {
			journalNumber = ""; 
		} 
		this.journalNumber = journalNumber; 
	} 

	public int getJournalCounter(){ 
		return journalCounter; 
	} 

	public void setJournalCounter(int journalCounter){ 
		this.journalCounter = journalCounter; 
	} 

	public String getJournalPrefix(){ 
		return journalPrefix; 
	} 

	public void setJournalPrefix(String journalPrefix){ 
		if ( journalPrefix == null ) {
			journalPrefix = ""; 
		} 
		this.journalPrefix = journalPrefix; 
	} 

	public Date getDate(){ 
		return date; 
	} 

	public void setDate(Date date){ 
		this.date = date; 
	} 

	public Date getTransDate(){ 
		return transDate; 
	} 

	public void setTransDate(Date transDate){ 
		this.transDate = transDate; 
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

	public long getOperatorId(){ 
		return operatorId; 
	} 

	public void setOperatorId(long operatorId){ 
		this.operatorId = operatorId; 
	} 

	public String getOperatorName(){ 
		return operatorName; 
	} 

	public void setOperatorName(String operatorName){ 
		if ( operatorName == null ) {
			operatorName = ""; 
		} 
		this.operatorName = operatorName; 
	} 

	public double getAmount(){ 
		return amount; 
	} 

	public void setAmount(double amount){ 
		this.amount = amount; 
	} 

	public String getRefNumber(){ 
		return refNumber; 
	} 

	public void setRefNumber(String refNumber){ 
		if ( refNumber == null ) {
			refNumber = ""; 
		} 
		this.refNumber = refNumber; 
	} 

	public long getVendorId(){ 
		return vendorId; 
	} 

	public void setVendorId(long vendorId){ 
		this.vendorId = vendorId; 
	} 

	public long getCurrencyId(){ 
		return currencyId; 
	} 

	public void setCurrencyId(long currencyId){ 
		this.currencyId = currencyId; 
	} 

	public String getPaymentMethod(){ 
		return paymentMethod; 
	} 

	public void setPaymentMethod(String paymentMethod){ 
		if ( paymentMethod == null ) {
			paymentMethod = ""; 
		} 
		this.paymentMethod = paymentMethod; 
	} 

        /**
         * Getter for property accountBalance.
         * @return Value of property accountBalance.
         */
        public double getAccountBalance() {
            return this.accountBalance;
        }
        
        /**
         * Setter for property accountBalance.
         * @param accountBalance New value of property accountBalance.
         */
        public void setAccountBalance(double accountBalance) {
            this.accountBalance = accountBalance;
        }
        
        /**
         * Getter for property paymentMethodId.
         * @return Value of property paymentMethodId.
         */
        public long getPaymentMethodId() {
            return this.paymentMethodId;
        }
        
        /**
         * Setter for property paymentMethodId.
         * @param paymentMethodId New value of property paymentMethodId.
         */
        public void setPaymentMethodId(long paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
        }

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		if ( status == null) {
			status = "";
		}
		this.status = status;
	}        
}
