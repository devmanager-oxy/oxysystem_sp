
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

public class PettycashPaymentDetail extends Entity { 

	private long pettycashPaymentId;
	private long coaId;
	private double amount;
	private String memo = "";

        /**
         * Holds value of property departmentId.
         */
        private long departmentId;
        
	public long getPettycashPaymentId(){ 
		return pettycashPaymentId; 
	} 

	public void setPettycashPaymentId(long pettycashPaymentId){ 
		this.pettycashPaymentId = pettycashPaymentId; 
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
        
}
