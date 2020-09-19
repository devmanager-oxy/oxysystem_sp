

package com.project.fms.transaction; 
 
import java.util.Date;
import com.project.main.entity.*;


public class CashReceive extends Entity { 

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
	private long receiveFromId;
	private String receiveFromName = "";

        /**
         * Holds value of property type.
         */
        private int type;
        
        /**
         * Holds value of property customerId.
         */
        private long customerId;
        
        /**
         * Holds value of property inOut.
         */
        private int inOut;
        
        
        private int postedStatus;
        private long postedById;
        private Date postedDate;
        private Date effectiveDate;
        
    public Date getEffectiveDate(){ 
		return effectiveDate; 
	} 

	public void setEffectiveDate(Date effectiveDate){ 
		this.effectiveDate = effectiveDate; 
	}
    
    public Date getPostedDate(){ 
		return postedDate; 
	} 

	public void setPostedDate(Date postedDate){ 
		this.postedDate = postedDate; 
	}
    
    public long getPostedById(){ 
		return postedById; 
	} 

	public void setPostedById(long postedById){ 
		this.postedById = postedById; 
	}    
	
	public int getPostedStatus(){ 
		return postedStatus; 
	} 

	public void setPostedStatus(int postedStatus){ 
		this.postedStatus = postedStatus; 
	}
	
	
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

	public long getReceiveFromId(){ 
		return receiveFromId; 
	} 

	public void setReceiveFromId(long receiveFromId){ 
		this.receiveFromId = receiveFromId; 
	} 

	public String getReceiveFromName(){ 
		return receiveFromName; 
	} 

	public void setReceiveFromName(String receiveFromName){ 
		if ( receiveFromName == null ) {
			receiveFromName = ""; 
		} 
		this.receiveFromName = receiveFromName; 
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
        
}
