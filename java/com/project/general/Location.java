
package com.project.general; 
 
import java.util.Date;
import com.project.main.entity.*;

public class Location extends Entity { 

	private String type = "";
	private String name = "";
	private String addressStreet = "";
	private String addressCountry = "";
	private String addressCity = "";
	private String telp = "";
	private String pic = "";

        /**
         * Holds value of property code.
         */
        private String code = "";
        
        /**
         * Holds value of property description.
         */
        private String description = "";
        
	public String getType(){ 
		return type; 
	} 

	public void setType(String type){ 
		if ( type == null ) {
			type = ""; 
		} 
		this.type = type; 
	} 

	public String getName(){ 
		return name; 
	} 

	public void setName(String name){ 
		if ( name == null ) {
			name = ""; 
		} 
		this.name = name; 
	} 

	public String getAddressStreet(){ 
		return addressStreet; 
	} 

	public void setAddressStreet(String addressStreet){ 
		if ( addressStreet == null ) {
			addressStreet = ""; 
		} 
		this.addressStreet = addressStreet; 
	} 

	public String getAddressCountry(){ 
		return addressCountry; 
	} 

	public void setAddressCountry(String addressCountry){ 
		if ( addressCountry == null ) {
			addressCountry = ""; 
		} 
		this.addressCountry = addressCountry; 
	} 

	public String getAddressCity(){ 
		return addressCity; 
	} 

	public void setAddressCity(String addressCity){ 
		if ( addressCity == null ) {
			addressCity = ""; 
		} 
		this.addressCity = addressCity; 
	} 

	public String getTelp(){ 
		return telp; 
	} 

	public void setTelp(String telp){ 
		if ( telp == null ) {
			telp = ""; 
		} 
		this.telp = telp; 
	} 

	public String getPic(){ 
		return pic; 
	} 

	public void setPic(String pic){ 
		if ( pic == null ) {
			pic = ""; 
		} 
		this.pic = pic; 
	} 

        /**
         * Getter for property code.
         * @return Value of property code.
         */
        public String getCode() {
            return this.code;
        }
        
        /**
         * Setter for property code.
         * @param code New value of property code.
         */
        public void setCode(String code) {
            this.code = code;
        }
        
        /**
         * Getter for property description.
         * @return Value of property description.
         */
        public String getDescription() {
            return this.description;
        }
        
        /**
         * Setter for property description.
         * @param description New value of property description.
         */
        public void setDescription(String description) {
            this.description = description;
        }
        
}
