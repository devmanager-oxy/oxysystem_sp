
package com.project.general; 
 
import java.util.Date;
import com.project.main.entity.*;

public class Vendor extends Entity { 

	private String name = "";
	private String code = "";
	private String address = "";
	private String city = "";
	private String state = "";
	private String phone = "";
	private String hp = "";
	private String fax = "";
	private int dueDate = 0;
	private String contact = "";
	private String countryName = "";
	private long countryId = 0;
        private int type;
        private String npwp;
        private double discount;
        private double prevLiability;
        private String email;
        private String vendorType;
        private String webPage;
        
        
	public String getName(){ 
		return name; 
	} 

	public void setName(String name){ 
		if ( name == null ) {
			name = ""; 
		} 
		this.name = name; 
	} 

	public String getCode(){ 
		return code; 
	} 

	public void setCode(String code){ 
		if ( code == null ) {
			code = ""; 
		} 
		this.code = code; 
	} 

	public String getAddress(){ 
		return address; 
	} 

	public void setAddress(String address){ 
		if ( address == null ) {
			address = ""; 
		} 
		this.address = address; 
	} 

	public String getCity(){ 
		return city; 
	} 

	public void setCity(String city){ 
		if ( city == null ) {
			city = ""; 
		} 
		this.city = city; 
	} 

	public String getState(){ 
		return state; 
	} 

	public void setState(String state){ 
		if ( state == null ) {
			state = ""; 
		} 
		this.state = state; 
	} 

	public String getPhone(){ 
		return phone; 
	} 

	public void setPhone(String phone){ 
		if ( phone == null ) {
			phone = ""; 
		} 
		this.phone = phone; 
	} 

	public String getHp(){ 
		return hp; 
	} 

	public void setHp(String hp){ 
		if ( hp == null ) {
			hp = ""; 
		} 
		this.hp = hp; 
	} 

	public String getFax(){ 
		return fax; 
	} 

	public void setFax(String fax){ 
		if ( fax == null ) {
			fax = ""; 
		} 
		this.fax = fax; 
	} 

	public int getDueDate(){ 
		return dueDate; 
	} 

	public void setDueDate(int dueDate){ 
		this.dueDate = dueDate; 
	} 

	public String getContact(){ 
		return contact; 
	} 

	public void setContact(String contact){ 
		if ( contact == null ) {
			contact = ""; 
		} 
		this.contact = contact; 
	} 

	public String getCountryName(){ 
		return countryName; 
	} 

	public void setCountryName(String countryName){ 
		if ( countryName == null ) {
			countryName = ""; 
		} 
		this.countryName = countryName; 
	} 

	public long getCountryId(){ 
		return countryId; 
	} 

	public void setCountryId(long countryId){ 
		this.countryId = countryId; 
	} 

        /**
         * Getter for property type.
         * @return Value of property type.
         */
        public int getType() {
            return this.type;
        }
        
        /**
         * Setter for property type.
         * @param type New value of property type.
         */
        public void setType(int type) {
            this.type = type;
        }
        
        /**
         * Getter for property npwp.
         * @return Value of property npwp.
         */
        public String getNpwp() {
            return this.npwp;
        }
        
        /**
         * Setter for property npwp.
         * @param npwp New value of property npwp.
         */
        public void setNpwp(String npwp) {
            this.npwp = npwp;
        }
        
        /**
         * Getter for property discount.
         * @return Value of property discount.
         */
        public double getDiscount() {
            return this.discount;
        }
        
        /**
         * Setter for property discount.
         * @param discount New value of property discount.
         */
        public void setDiscount(double discount) {
            this.discount = discount;
        }
        
        /**
         * Getter for property prevLiability.
         * @return Value of property prevLiability.
         */
        public double getPrevLiability() {
            return this.prevLiability;
        }
        
        /**
         * Setter for property prevLiability.
         * @param prevLiability New value of property prevLiability.
         */
        public void setPrevLiability(double prevLiability) {
            this.prevLiability = prevLiability;
        }
        
        /**
         * Getter for property email.
         * @return Value of property email.
         */
        public String getEmail() {
            return this.email;
        }
        
        /**
         * Setter for property email.
         * @param email New value of property email.
         */
        public void setEmail(String email) {
            this.email = email;
        }
        
        /**
         * Getter for property vendorType.
         * @return Value of property vendorType.
         */
        public String getVendorType() {
            return this.vendorType;
        }
        
        /**
         * Setter for property vendorType.
         * @param vendorType New value of property vendorType.
         */
        public void setVendorType(String vendorType) {
            this.vendorType = vendorType;
        }
        
        /**
         * Getter for property webPage.
         * @return Value of property webPage.
         */
        public String getWebPage() {
            return this.webPage;
        }
        
        /**
         * Setter for property webPage.
         * @param webPage New value of property webPage.
         */
        public void setWebPage(String webPage) {
            this.webPage = webPage;
        }
        
}
