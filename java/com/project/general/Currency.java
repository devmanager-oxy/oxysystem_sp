

package com.project.general; 
 
/* package java */ 
import java.util.Date;

/* package qdep */
import com.project.main.entity.*;

public class Currency extends Entity { 

	private String currencyCode = "";
	private String description = "";

	public String getCurrencyCode(){ 
		return currencyCode; 
	} 

	public void setCurrencyCode(String currencyCode){ 
		if ( currencyCode == null ) {
			currencyCode = ""; 
		} 
		this.currencyCode = currencyCode; 
	} 

	public String getDescription(){ 
		return description; 
	} 

	public void setDescription(String description){ 
		if ( description == null ) {
			description = ""; 
		} 
		this.description = description; 
	} 

}
