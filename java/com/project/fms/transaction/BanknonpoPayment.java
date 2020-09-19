
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

public class BanknonpoPayment extends Entity { 

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

        /**
         * Holds value of property accountBalance.
         */
        private double accountBalance;
        
        /**
         * Holds value of property paymentMethodId.
         */
        private long paymentMethodId;
        
        /**
         * Holds value of property vendorId.
         */
        private long vendorId;
        
        /**
         * Holds value of property invoiceNumber.
         */
        private String invoiceNumber = "";
        
        /**
         * Holds value of property activityStatus.
         */
        private String activityStatus;
        
        /**
         * Holds value of property type.
         */
        private int type;
        
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
        
        /**
         * Getter for property vendorId.
         * @return Value of property vendorId.
         */
        public long getVendorId() {
            return this.vendorId;
        }
        
        /**
         * Setter for property vendorId.
         * @param vendorId New value of property vendorId.
         */
        public void setVendorId(long vendorId) {
            this.vendorId = vendorId;
        }
        
        /**
         * Getter for property invoiceNumber.
         * @return Value of property invoiceNumber.
         */
        public String getInvoiceNumber() {
            return this.invoiceNumber;
        }
        
        /**
         * Setter for property invoiceNumber.
         * @param invoiceNumber New value of property invoiceNumber.
         */
        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }
        
        /**
         * Getter for property activityStatus.
         * @return Value of property activityStatus.
         */
        public String getActivityStatus() {
            return this.activityStatus;
        }
        
        /**
         * Setter for property activityStatus.
         * @param activityStatus New value of property activityStatus.
         */
        public void setActivityStatus(String activityStatus) {
            this.activityStatus = activityStatus;
        }
        
        /**
         * Getter for property type.
         * @return Value of property type.
         */
        public int getType() {
            return this.type;
        }
        
        /**
         * Setter for property type.
         * @param type New value of property type.
         */
        public void setType(int type) {
            this.type = type;
        }
        
}
