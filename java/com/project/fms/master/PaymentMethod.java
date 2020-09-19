
package com.project.fms.master; 
 
import java.util.Date;
import com.project.main.entity.*;

public class PaymentMethod extends Entity { 

	private String description = "";

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
