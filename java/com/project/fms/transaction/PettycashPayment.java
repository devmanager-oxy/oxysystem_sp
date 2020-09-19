
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

public class PettycashPayment extends Entity { 

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
	private int replaceStatus;

        /**
         * Holds value of property accountBalance.
         */
        private double accountBalance;
        
        /**
         * Holds value of property activityStatus.
         */
        private String activityStatus;
        
        /**
         * Holds value of property shareGroupId.
         */
        private long shareGroupId;
        
        /**
         * Holds value of property shareCategoryId.
         */
        private long shareCategoryId;
        
        /**
         * Holds value of property status.
         */
        private int status;
        
        /**
         * Holds value of property expenseCategoryId.
         */
        private long expenseCategoryId;
        
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

	public int getReplaceStatus(){ 
		return replaceStatus; 
	} 

	public void setReplaceStatus(int replaceStatus){ 
		this.replaceStatus = replaceStatus; 
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
         * Getter for property shareGroupId.
         * @return Value of property shareGroupId.
         */
        public long getShareGroupId() {
            return this.shareGroupId;
        }
        
        /**
         * Setter for property shareGroupId.
         * @param shareGroupId New value of property shareGroupId.
         */
        public void setShareGroupId(long shareGroupId) {
            this.shareGroupId = shareGroupId;
        }
        
        /**
         * Getter for property shareCategoryId.
         * @return Value of property shareCategoryId.
         */
        public long getShareCategoryId() {
            return this.shareCategoryId;
        }
        
        /**
         * Setter for property shareCategoryId.
         * @param shareCategoryId New value of property shareCategoryId.
         */
        public void setShareCategoryId(long shareCategoryId) {
            this.shareCategoryId = shareCategoryId;
        }
        
        /**
         * Getter for property status.
         * @return Value of property status.
         */
        public int getStatus() {
            return this.status;
        }
        
        /**
         * Setter for property status.
         * @param status New value of property status.
         */
        public void setStatus(int status) {
            this.status = status;
        }
        
        /**
         * Getter for property expenseCategoryId.
         * @return Value of property expenseCategoryId.
         */
        public long getExpenseCategoryId() {
            return this.expenseCategoryId;
        }
        
        /**
         * Setter for property expenseCategoryId.
         * @param expenseCategoryId New value of property expenseCategoryId.
         */
        public void setExpenseCategoryId(long expenseCategoryId) {
            this.expenseCategoryId = expenseCategoryId;
        }
        
}
