
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

public class PettycashReplenishment extends Entity { 

	private long replaceCoaId;
	private long replaceFromCoaId;
	private String memo = "";
	private Date date;
	private Date transDate;
	private long operatorId;
	private String operatorName = "";
	private double replaceAmount;
	private String journalNumber = "";
	private String journalPrefix = "";
	private int journalCounter;

        /**
         * Holds value of property status.
         */
        private String status;
        
	public long getReplaceCoaId(){ 
		return replaceCoaId; 
	} 

	public void setReplaceCoaId(long replaceCoaId){ 
		this.replaceCoaId = replaceCoaId; 
	} 

	public long getReplaceFromCoaId(){ 
		return replaceFromCoaId; 
	} 

	public void setReplaceFromCoaId(long replaceFromCoaId){ 
		this.replaceFromCoaId = replaceFromCoaId; 
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

	public double getReplaceAmount(){ 
		return replaceAmount; 
	} 

	public void setReplaceAmount(double replaceAmount){ 
		this.replaceAmount = replaceAmount; 
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

	public String getJournalPrefix(){ 
		return journalPrefix; 
	} 

	public void setJournalPrefix(String journalPrefix){ 
		if ( journalPrefix == null ) {
			journalPrefix = ""; 
		} 
		this.journalPrefix = journalPrefix; 
	} 

	public int getJournalCounter(){ 
		return journalCounter; 
	} 

	public void setJournalCounter(int journalCounter){ 
		this.journalCounter = journalCounter; 
	} 

        /**
         * Getter for property status.
         * @return Value of property status.
         */
        public String getStatus() {
            return this.status;
        }
        
        /**
         * Setter for property status.
         * @param status New value of property status.
         */
        public void setStatus(String status) {
            this.status = status;
        }
        
}
