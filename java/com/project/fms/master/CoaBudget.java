
package com.project.fms.master; 
 
import java.util.Date;
import com.project.main.entity.*;

public class CoaBudget extends Entity { 

	private long coaId;
	private long periodeId;
	private double amount;

        /**
         * Holds value of property bgtYear.
         */
        private int bgtYear;
        
        /**
         * Holds value of property bgtMonth.
         */
        private int bgtMonth;
        
	public long getCoaId(){ 
		return coaId; 
	} 

	public void setCoaId(long coaId){ 
		this.coaId = coaId; 
	} 

	public long getPeriodeId(){ 
		return periodeId; 
	} 

	public void setPeriodeId(long periodeId){ 
		this.periodeId = periodeId; 
	} 

	public double getAmount(){ 
		return amount; 
	} 

	public void setAmount(double amount){ 
		this.amount = amount; 
	} 

        /**
         * Getter for property bgtYear.
         * @return Value of property bgtYear.
         */
        public int getBgtYear() {
            return this.bgtYear;
        }
        
        /**
         * Setter for property bgtYear.
         * @param bgtYear New value of property bgtYear.
         */
        public void setBgtYear(int bgtYear) {
            this.bgtYear = bgtYear;
        }
        
        /**
         * Getter for property bgtMonth.
         * @return Value of property bgtMonth.
         */
        public int getBgtMonth() {
            return this.bgtMonth;
        }
        
        /**
         * Setter for property bgtMonth.
         * @param bgtMonth New value of property bgtMonth.
         */
        public void setBgtMonth(int bgtMonth) {
            this.bgtMonth = bgtMonth;
        }
        
}
