
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

public class BankpoPaymentDetail extends Entity { 

	private long bankpoPaymentId;
	private long coaId;
	private String memo = "";
	private long invoiceId;
	private long currencyId;
	private double bookedRate;
	private double paymentByInvCurrencyAmount;
	private double paymentAmount;

	public long getBankpoPaymentId(){ 
		return bankpoPaymentId; 
	} 

	public void setBankpoPaymentId(long bankpoPaymentId){ 
		this.bankpoPaymentId = bankpoPaymentId; 
	} 

	public long getCoaId(){ 
		return coaId; 
	} 

	public void setCoaId(long coaId){ 
		this.coaId = coaId; 
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

	public long getInvoiceId(){
            return invoiceId;
        } 

	public void setInvoiceId(long invoiceId){
            this.invoiceId = invoiceId;
        } 

	public long getCurrencyId(){ 
		return currencyId; 
	} 

	public void setCurrencyId(long currencyId){ 
		this.currencyId = currencyId; 
	} 

	public double getBookedRate(){ 
		return bookedRate; 
	} 

	public void setBookedRate(double bookedRate){ 
		this.bookedRate = bookedRate; 
	} 

	public double getPaymentByInvCurrencyAmount(){
            return paymentByInvCurrencyAmount;
        } 

	public void setPaymentByInvCurrencyAmount(double paymentByInvCurrencyAmount){
            this.paymentByInvCurrencyAmount = paymentByInvCurrencyAmount;
        } 

	public double getPaymentAmount(){ 
		return paymentAmount; 
	} 

	public void setPaymentAmount(double paymentAmount){ 
		this.paymentAmount = paymentAmount; 
	} 

}
