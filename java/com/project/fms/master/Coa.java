
package com.project.fms.master; 
 
import java.util.Date;
import com.project.main.entity.*;

public class Coa extends Entity { 

	private long accRefId = 0;
	private long departmentId = 0;
	private long sectionId = 0;
	private String accountGroup = "";
	private String code = "";
	private String name = "";
	private int level = 0;
	private String saldoNormal = "";
	private String status = "";
	private String departmentName = "";
	private String sectionName = "";
	private long userId = 0;

        /**
         * Holds value of property regDate.
         */
        private Date regDate = new Date();
        
        /**
         * Holds value of property openingBalance.
         */
        private double openingBalance = 0;
        
        /**
         * Holds value of property locationId.
         */
        private long locationId;
        
        /**
         * Holds value of property departmentalCoa.
         */
        private int departmentalCoa;
        
        /**
         * Holds value of property coaCategoryId.
         */
        private long coaCategoryId;
        
        /**
         * Holds value of property coaGroupAliasId.
         */
        private long coaGroupAliasId;
        
        /**
         * Holds value of property isNeedExtra.
         */
        private int isNeedExtra;
        
        /**
         * Holds value of property debetPrefixCode.
         */
        private String debetPrefixCode;
        
        /**
         * Holds value of property creditPrefixCode.
         */
        private String creditPrefixCode;
        
        /**
         * Holds value of property companyId.
         */
        private long companyId;
        
        /**
         * Holds value of property accountClass.
         */
        private int accountClass;
        
	public long getAccRefId(){ 
		return accRefId; 
	} 

	public void setAccRefId(long accRefId){ 
		this.accRefId = accRefId; 
	} 

	public long getDepartmentId(){ 
		return departmentId; 
	} 

	public void setDepartmentId(long departmentId){ 
		this.departmentId = departmentId; 
	} 

	public long getSectionId(){ 
		return sectionId; 
	} 

	public void setSectionId(long sectionId){ 
		this.sectionId = sectionId; 
	} 

	public String getAccountGroup(){ 
		return accountGroup; 
	} 

	public void setAccountGroup(String accountGroup){ 
		if ( accountGroup == null ) {
			accountGroup = ""; 
		} 
		this.accountGroup = accountGroup; 
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

	public String getName(){ 
		return name; 
	} 

	public void setName(String name){ 
		if ( name == null ) {
			name = ""; 
		} 
		this.name = name; 
	} 

	public int getLevel(){ 
		return level; 
	} 

	public void setLevel(int level){ 
		this.level = level; 
	} 

	public String getSaldoNormal(){ 
		return saldoNormal; 
	} 

	public void setSaldoNormal(String saldoNormal){ 
		if ( saldoNormal == null ) {
			saldoNormal = ""; 
		} 
		this.saldoNormal = saldoNormal; 
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

	public String getDepartmentName(){ 
		return departmentName; 
	} 

	public void setDepartmentName(String departmentName){ 
		if ( departmentName == null ) {
			departmentName = ""; 
		} 
		this.departmentName = departmentName; 
	} 

	public String getSectionName(){ 
		return sectionName; 
	} 

	public void setSectionName(String sectionName){ 
		if ( sectionName == null ) {
			sectionName = ""; 
		} 
		this.sectionName = sectionName; 
	} 

	public long getUserId(){ 
		return userId; 
	} 

	public void setUserId(long userId){ 
		this.userId = userId; 
	} 

        /**
         * Getter for property regDate.
         * @return Value of property regDate.
         */
        public Date getRegDate() {
            return this.regDate;
        }
        
        /**
         * Setter for property regDate.
         * @param regDate New value of property regDate.
         */
        public void setRegDate(Date regDate) {
            this.regDate = regDate;
        }
        
        /**
         * Getter for property openingBalance.
         * @return Value of property openingBalance.
         */
        public double getOpeningBalance() {
            return this.openingBalance;
        }
        
        /**
         * Setter for property openingBalance.
         * @param openingBalance New value of property openingBalance.
         */
        public void setOpeningBalance(double openingBalance) {
            this.openingBalance = openingBalance;
        }
        
        /**
         * Getter for property location_id.
         * @return Value of property location_id.
         */
        public long getLocationId() {
            return this.locationId;
        }
        
        /**
         * Setter for property location_id.
         * @param location_id New value of property location_id.
         */
        public void setLocationId(long locationId) {
            this.locationId = locationId;
        }
        
        /**
         * Getter for property departmentalCoa.
         * @return Value of property departmentalCoa.
         */
        public int getDepartmentalCoa() {
            return this.departmentalCoa;
        }
        
        /**
         * Setter for property departmentalCoa.
         * @param departmentalCoa New value of property departmentalCoa.
         */
        public void setDepartmentalCoa(int departmentalCoa) {
            this.departmentalCoa = departmentalCoa;
        }
        
        /**
         * Getter for property coaCategoryId.
         * @return Value of property coaCategoryId.
         */
        public long getCoaCategoryId() {
            return this.coaCategoryId;
        }
        
        /**
         * Setter for property coaCategoryId.
         * @param coaCategoryId New value of property coaCategoryId.
         */
        public void setCoaCategoryId(long coaCategoryId) {
            this.coaCategoryId = coaCategoryId;
        }
        
        /**
         * Getter for property coaGroupAliasId.
         * @return Value of property coaGroupAliasId.
         */
        public long getCoaGroupAliasId() {
            return this.coaGroupAliasId;
        }
        
        /**
         * Setter for property coaGroupAliasId.
         * @param coaGroupAliasId New value of property coaGroupAliasId.
         */
        public void setCoaGroupAliasId(long coaGroupAliasId) {
            this.coaGroupAliasId = coaGroupAliasId;
        }
        
        /**
         * Getter for property isNeedExtra.
         * @return Value of property isNeedExtra.
         */
        public int getIsNeedExtra() {
            return this.isNeedExtra;
        }
        
        /**
         * Setter for property isNeedExtra.
         * @param isNeedExtra New value of property isNeedExtra.
         */
        public void setIsNeedExtra(int isNeedExtra) {
            this.isNeedExtra = isNeedExtra;
        }
        
        /**
         * Getter for property debetPrefixCode.
         * @return Value of property debetPrefixCode.
         */
        public String getDebetPrefixCode() {
            return this.debetPrefixCode;
        }
        
        /**
         * Setter for property debetPrefixCode.
         * @param debetPrefixCode New value of property debetPrefixCode.
         */
        public void setDebetPrefixCode(String debetPrefixCode) {
            this.debetPrefixCode = debetPrefixCode;
        }
        
        /**
         * Getter for property creditPrefixCode.
         * @return Value of property creditPrefixCode.
         */
        public String getCreditPrefixCode() {
            return this.creditPrefixCode;
        }
        
        /**
         * Setter for property creditPrefixCode.
         * @param creditPrefixCode New value of property creditPrefixCode.
         */
        public void setCreditPrefixCode(String creditPrefixCode) {
            this.creditPrefixCode = creditPrefixCode;
        }
        
        /**
         * Getter for property companyId.
         * @return Value of property companyId.
         */
        public long getCompanyId() {
            return this.companyId;
        }
        
        /**
         * Setter for property companyId.
         * @param companyId New value of property companyId.
         */
        public void setCompanyId(long companyId) {
            this.companyId = companyId;
        }
        
        /**
         * Getter for property accountClass.
         * @return Value of property accountClass.
         */
        public int getAccountClass() {
            return this.accountClass;
        }
        
        /**
         * Setter for property accountClass.
         * @param accountClass New value of property accountClass.
         */
        public void setAccountClass(int accountClass) {
            this.accountClass = accountClass;
        }
        
}
