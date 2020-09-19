
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

public class GlDetail extends Entity { 

	private long glId;
	private long coaId;
	private double debet;
	private double credit;
	private long foreignCurrencyId;
	private double foreignCurrencyAmount;
	private String memo = "";
	private double bookedRate;

        /**
         * Holds value of property isDebet.
         */
        private int isDebet;
        
        /**
         * Holds value of property departmentId.
         */
        private long departmentId;
        
        /**
         * Holds value of property sectionId.
         */
        private long sectionId;
        
        /**
         * Holds value of property subSectionId.
         */
        private long subSectionId;
        
        /**
         * Holds value of property jobId.
         */
        private long jobId;
        
        /**
         * Holds value of property statusTransaction.
         */
        private int statusTransaction;
        
        /**
         * Holds value of property customerId.
         */
        private long customerId;
        
	public long getGlId(){ 
		return glId; 
	} 

	public void setGlId(long glId){ 
		this.glId = glId; 
	} 

	public long getCoaId(){ 
		return coaId; 
	} 

	public void setCoaId(long coaId){ 
		this.coaId = coaId; 
	} 

	public double getDebet(){ 
		return debet; 
	} 

	public void setDebet(double debet){ 
		this.debet = debet; 
	} 

	public double getCredit(){ 
		return credit; 
	} 

	public void setCredit(double credit){ 
		this.credit = credit; 
	} 

	public long getForeignCurrencyId(){ 
		return foreignCurrencyId; 
	} 

	public void setForeignCurrencyId(long foreignCurrencyId){ 
		this.foreignCurrencyId = foreignCurrencyId; 
	} 

	public double getForeignCurrencyAmount(){ 
		return foreignCurrencyAmount; 
	} 

	public void setForeignCurrencyAmount(double foreignCurrencyAmount){ 
		this.foreignCurrencyAmount = foreignCurrencyAmount; 
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

	public double getBookedRate(){ 
		return bookedRate; 
	} 

	public void setBookedRate(double bookedRate){ 
		this.bookedRate = bookedRate; 
	} 

        /**
         * Getter for property isDebet.
         * @return Value of property isDebet.
         */
        public int getIsDebet() {
            return this.isDebet;
        }
        
        /**
         * Setter for property isDebet.
         * @param isDebet New value of property isDebet.
         */
        public void setIsDebet(int isDebet) {
            this.isDebet = isDebet;
        }
        
        /**
         * Getter for property departmentId.
         * @return Value of property departmentId.
         */
        public long getDepartmentId() {
            return this.departmentId;
        }
        
        /**
         * Setter for property departmentId.
         * @param departmentId New value of property departmentId.
         */
        public void setDepartmentId(long departmentId) {
            this.departmentId = departmentId;
        }
        
        /**
         * Getter for property sectionId.
         * @return Value of property sectionId.
         */
        public long getSectionId() {
            return this.sectionId;
        }
        
        /**
         * Setter for property sectionId.
         * @param sectionId New value of property sectionId.
         */
        public void setSectionId(long sectionId) {
            this.sectionId = sectionId;
        }
        
        /**
         * Getter for property subSectionId.
         * @return Value of property subSectionId.
         */
        public long getSubSectionId() {
            return this.subSectionId;
        }
        
        /**
         * Setter for property subSectionId.
         * @param subSectionId New value of property subSectionId.
         */
        public void setSubSectionId(long subSectionId) {
            this.subSectionId = subSectionId;
        }
        
        /**
         * Getter for property jobId.
         * @return Value of property jobId.
         */
        public long getJobId() {
            return this.jobId;
        }
        
        /**
         * Setter for property jobId.
         * @param jobId New value of property jobId.
         */
        public void setJobId(long jobId) {
            this.jobId = jobId;
        }
        
        /**
         * Getter for property statusTitipan.
         * @return Value of property statusTitipan.
         */
        public int getStatusTransaction() {
            return this.statusTransaction;
        }
        
        /**
         * Setter for property statusTitipan.
         * @param statusTitipan New value of property statusTitipan.
         */
        public void setStatusTransaction(int statusTransaction) {
            this.statusTransaction = statusTransaction;
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
        
}
