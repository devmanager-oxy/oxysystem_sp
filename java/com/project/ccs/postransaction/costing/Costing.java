
package com.project.ccs.postransaction.costing; 
 
import java.util.Date;
import com.project.main.entity.*;

public class Costing extends Entity { 

	private Date date;
	private int counter;
	private String number = "";
	private String note = "";
	private int approval1;
	private int approval2;
	private int approval3;
	private String status = "";
	private long locationId;
	private long userId;

	public Date getDate(){ 
		return date; 
	} 

	public void setDate(Date date){ 
		this.date = date; 
	} 

	public int getCounter(){ 
		return counter; 
	} 

	public void setCounter(int counter){ 
		this.counter = counter; 
	} 

	public String getNumber(){ 
		return number; 
	} 

	public void setNumber(String number){ 
		if ( number == null ) {
			number = ""; 
		} 
		this.number = number; 
	} 

	public String getNote(){ 
		return note; 
	} 

	public void setNote(String note){ 
		if ( note == null ) {
			note = ""; 
		} 
		this.note = note; 
	} 

	public int getApproval1(){ 
		return approval1; 
	} 

	public void setApproval1(int approval1){ 
		this.approval1 = approval1; 
	} 

	public int getApproval2(){ 
		return approval2; 
	} 

	public void setApproval2(int approval2){ 
		this.approval2 = approval2; 
	} 

	public int getApproval3(){ 
		return approval3; 
	} 

	public void setApproval3(int approval3){ 
		this.approval3 = approval3; 
	} 

	public String getStatus(){ 
		return status; 
	} 

	public void setStatus(String status){ 
		if ( status == null ) {
			status = ""; 
		} 
		this.status = status; 
	} 

	public long getLocationId(){ 
		return locationId; 
	} 

	public void setLocationId(long locationId){ 
		this.locationId = locationId; 
	} 

	public long getUserId(){ 
		return userId; 
	} 

	public void setUserId(long userId){ 
		this.userId = userId; 
	} 

}
