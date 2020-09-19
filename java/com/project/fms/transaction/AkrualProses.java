package com.project.fms.transaction;

import java.util.Date;
import com.project.main.entity.*;


public class AkrualProses extends Entity { 

	private Date regDate;
	private double jumlah;
	private long creditCoaId;
	private long debetCoaId;
	private long periodeId;
	private long userId;

        /**
         * Holds value of property akrualSetupId.
         */
        private long akrualSetupId;
        
	public Date getRegDate(){ 
		return regDate; 
	} 

	public void setRegDate(Date regDate){ 
		this.regDate = regDate; 
	} 

	public double getJumlah(){ 
		return jumlah; 
	} 

	public void setJumlah(double jumlah){ 
		this.jumlah = jumlah; 
	} 

	public long getCreditCoaId(){ 
		return creditCoaId; 
	} 

	public void setCreditCoaId(long creditCoaId){ 
		this.creditCoaId = creditCoaId; 
	} 

	public long getDebetCoaId(){ 
		return debetCoaId; 
	} 

	public void setDebetCoaId(long debetCoaId){ 
		this.debetCoaId = debetCoaId; 
	} 

	public long getPeriodeId(){ 
		return periodeId; 
	} 

	public void setPeriodeId(long periodeId){ 
		this.periodeId = periodeId; 
	} 

	public long getUserId(){ 
		return userId; 
	} 

	public void setUserId(long userId){ 
		this.userId = userId; 
	} 

        /**
         * Getter for property akrualSetupId.
         * @return Value of property akrualSetupId.
         */
        public long getAkrualSetupId() {
            return this.akrualSetupId;
        }
        
        /**
         * Setter for property akrualSetupId.
         * @param akrualSetupId New value of property akrualSetupId.
         */
        public void setAkrualSetupId(long akrualSetupId) {
            this.akrualSetupId = akrualSetupId;
        }
        
}
