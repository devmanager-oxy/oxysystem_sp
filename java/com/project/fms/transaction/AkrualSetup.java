package com.project.fms.transaction;

import java.util.Date;
import com.project.main.entity.*;


public class AkrualSetup extends Entity { 

	private String nama = "";
	private double anggaran;
	private int pembagi;
	private long creditCoaId;
	private long debetCoaId;
	private Date regDate;
	private Date lastUpdate;
	private long userId;

        /**
         * Holds value of property status.
         */
        private int status;
        
	public String getNama(){ 
		return nama; 
	} 

	public void setNama(String nama){ 
		if ( nama == null ) {
			nama = ""; 
		} 
		this.nama = nama; 
	} 

	public double getAnggaran(){ 
		return anggaran; 
	} 

	public void setAnggaran(double anggaran){ 
		this.anggaran = anggaran; 
	} 

	public int getPembagi(){ 
		return pembagi; 
	} 

	public void setPembagi(int pembagi){ 
		this.pembagi = pembagi; 
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

	public Date getRegDate(){ 
		return regDate; 
	} 

	public void setRegDate(Date regDate){ 
		this.regDate = regDate; 
	} 

	public Date getLastUpdate(){ 
		return lastUpdate; 
	} 

	public void setLastUpdate(Date lastUpdate){ 
		this.lastUpdate = lastUpdate; 
	} 

	public long getUserId(){ 
		return userId; 
	} 

	public void setUserId(long userId){ 
		this.userId = userId; 
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
        
}
