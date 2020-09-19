
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

public class BankDepositDetail extends Entity { 

	private long bankDepositId;
	private long coaId;
	private long foreignCurrencyId;
	private double foreignAmount;
	private double bookedRate;
	private String memo = "";
	private double amount;

	public long getBankDepositId(){ 
		return bankDepositId; 
	} 

	public void setBankDepositId(long bankDepositId){ 
		this.bankDepositId = bankDepositId; 
	} 

	public long getCoaId(){ 
		return coaId; 
	} 

	public void setCoaId(long coaId){ 
		this.coaId = coaId; 
	} 

	public long getForeignCurrencyId(){ 
		return foreignCurrencyId; 
	} 

	public void setForeignCurrencyId(long foreignCurrencyId){ 
		this.foreignCurrencyId = foreignCurrencyId; 
	} 

	public double getForeignAmount(){ 
		return foreignAmount; 
	} 

	public void setForeignAmount(double foreignAmount){ 
		this.foreignAmount = foreignAmount; 
	} 

	public double getBookedRate(){ 
		return bookedRate; 
	} 

	public void setBookedRate(double bookedRate){ 
		this.bookedRate = bookedRate; 
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

	public double getAmount(){ 
		return amount; 
	} 

	public void setAmount(double amount){ 
		this.amount = amount; 
	} 

}
