
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

public class BanknonpoPaymentDetail extends Entity { 

	private long banknonpoPaymentId;
	private long coaId;
	private double amount;
	private String memo = "";

        /**
         * Holds value of property foreignCurrencyId.
         */
        private long foreignCurrencyId;
        
        /**
         * Holds value of property foreignAmount.
         */
        private double foreignAmount;
        
        /**
         * Holds value of property bookedRate.
         */
        private double bookedRate;
        
        /**
         * Holds value of property departmentId.
         */
        private long departmentId;
        
        /**
         * Holds value of property coaIdTemp.
         */
        private long coaIdTemp;
        
        /**
         * Holds value of property type.
         */
        private int type;
        
	public long getBanknonpoPaymentId(){ 
		return banknonpoPaymentId; 
	} 

	public void setBanknonpoPaymentId(long banknonpoPaymentId){ 
		this.banknonpoPaymentId = banknonpoPaymentId; 
	} 

	public long getCoaId(){ 
		return coaId; 
	} 

	public void setCoaId(long coaId){ 
		this.coaId = coaId; 
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
         * Getter for property foreignCurrencyId.
         * @return Value of property foreignCurrencyId.
         */
        public long getForeignCurrencyId() {
            return this.foreignCurrencyId;
        }
        
        /**
         * Setter for property foreignCurrencyId.
         * @param foreignCurrencyId New value of property foreignCurrencyId.
         */
        public void setForeignCurrencyId(long foreignCurrencyId) {
            this.foreignCurrencyId = foreignCurrencyId;
        }
        
        /**
         * Getter for property foreignAmount.
         * @return Value of property foreignAmount.
         */
        public double getForeignAmount() {
            return this.foreignAmount;
        }
        
        /**
         * Setter for property foreignAmount.
         * @param foreignAmount New value of property foreignAmount.
         */
        public void setForeignAmount(double foreignAmount) {
            this.foreignAmount = foreignAmount;
        }
        
        /**
         * Getter for property bookedRate.
         * @return Value of property bookedRate.
         */
        public double getBookedRate() {
            return this.bookedRate;
        }
        
        /**
         * Setter for property bookedRate.
         * @param bookedRate New value of property bookedRate.
         */
        public void setBookedRate(double bookedRate) {
            this.bookedRate = bookedRate;
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
         * Getter for property coaIdTemp.
         * @return Value of property coaIdTemp.
         */
        public long getCoaIdTemp() {
            return this.coaIdTemp;
        }
        
        /**
         * Setter for property coaIdTemp.
         * @param coaIdTemp New value of property coaIdTemp.
         */
        public void setCoaIdTemp(long coaIdTemp) {
            this.coaIdTemp = coaIdTemp;
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
