package com.project.general; 
 
import java.util.Date;
import com.project.main.entity.*;

public class Bank extends Entity { 

	private String name;
	private String adress = "";
	private double defaultBunga;

	public String getName(){ 
		return name; 
	} 

	public void setName(String name){ 
		this.name = name; 
	} 

	public String getAdress(){ 
		return adress; 
	} 

	public void setAdress(String adress){ 
		if ( adress == null ) {
			adress = ""; 
		} 
		this.adress = adress; 
	} 

	public double getDefaultBunga(){ 
		return defaultBunga; 
	} 

	public void setDefaultBunga(double defaultBunga){ 
		this.defaultBunga = defaultBunga; 
	} 

}
