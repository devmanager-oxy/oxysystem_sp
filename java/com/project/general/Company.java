
package com.project.general;

import java.util.Date;
import com.project.main.entity.*;

public class Company extends Entity {

	private String name = "";
	private String serialNumber = "";
	private String address = "";
	private int fiscalYear = 0;
	private int endFiscalMonth = 0;
	private int entryStartMonth = 0;
	private int numberOfPeriod = 0;
	private String cashReceiveCode = "";
	private String pettycashPaymentCode = "";
	private String pettycashReplaceCode = "";
	private String bankDepositCode = "";
	private String bankPaymentPoCode = "";
	private String bankPaymentNonpoCode = "";
	private String purchaseOrderCode = "";
	private String generalLedgerCode = "";
	private double maxPettycashReplenis = 0;
	private double maxPettycashTransaction = 0;
	private String bookingCurrencyCode = "";
	private long bookingCurrencyId = 0;
	private long systemLocation = 0;
	private String activationCode = "";
	private String systemLocationCode = "";
	private String contact = "";

        private String address2 = "";
        private String invoiceCode = "";
        private int departmentLevel = 0;
        private double governmentVat = 0;
        private String projectCode = "";
        private Date lastUpdate = new Date();
        private String paymentCode = "";

        private String installBudgetCode = "";
        private String installTravelCode = "";
        private String installSettlementCode = "";

        private int categoryLevel = 0;

        private String customerRequestCode = "";
        private String introLetterCode = "";

        private String proposalCode = "";

        /**
         * Holds value of property phone.
         */
        private String phone = "";
        
        /**
         * Holds value of property fax.
         */
        private String fax = "";
        
        /**
         * Holds value of property email.
         */
        private String email = "";
        
        /**
         * Holds value of property website.
         */
        private String website = "";
        
        /**
         * Holds value of property purchaseRequestCode.
         */
        private String purchaseRequestCode;
        
        /**
         * Holds value of property defaultSalesMargin.
         */
        private double defaultSalesMargin;
        
        /**
         * Holds value of property returnGoodsCode.
         */
        private String returnGoodsCode;
        
        /**
         * Holds value of property transferGoodsCode.
         */
        private String transferGoodsCode;
        
        /**
         * Holds value of property costingGoodsCode.
         */
        private String costingGoodsCode;
        
        /**
         * Holds value of property adjustmentCode.
         */
        private String adjustmentCode;
        
        /**
         * Holds value of property opnameCode.
         */
        private String opnameCode;
        
        /**
         * Holds value of property integratedHotelSystem.
         */
        private int integratedHotelSystem;
        
        /**
         * Holds value of property integratedFinanceSystem.
         */
        private int integratedFinanceSystem;
        
        /**
         * Holds value of property shopOrderCode.
         */
        private String shopOrderCode;
        
        /**
         * Holds value of property pinjamanKoperasiCode.
         */
        private String pinjamanKoperasiCode;
        
        /**
         * Holds value of property pinjamanBankCode.
         */
        private String pinjamanBankCode;
        
        /**
         * Holds value of property bayarAngsuranCode.
         */
        private String bayarAngsuranCode;
        
        /**
         * Holds value of property bayarAngsuranBankCode.
         */
        private String bayarAngsuranBankCode;
        
        /**
         * Holds value of property pinjamanKoperasiBankCode.
         */
        private String pinjamanKoperasiBankCode;
        
        /**
         * Holds value of property bayarAngsuranKopBankCode.
         */
        private String bayarAngsuranKopBankCode;
        
        public String getName(){
		return name;
	}

	public void setName(String name){
		if ( name == null ) {
			name = "";
		}
		this.name = name;
	}

	public String getSerialNumber(){
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber){
		if ( serialNumber == null ) {
			serialNumber = "";
		}
		this.serialNumber = serialNumber;
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

	public int getFiscalYear(){
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear){
		this.fiscalYear = fiscalYear;
	}

	public int getEndFiscalMonth(){
		return endFiscalMonth;
	}

	public void setEndFiscalMonth(int endFiscalMonth){
		this.endFiscalMonth = endFiscalMonth;
	}

	public int getEntryStartMonth(){
		return entryStartMonth;
	}

	public void setEntryStartMonth(int entryStartMonth){
		this.entryStartMonth = entryStartMonth;
	}

	public int getNumberOfPeriod(){
		return numberOfPeriod;
	}

	public void setNumberOfPeriod(int numberOfPeriod){
		this.numberOfPeriod = numberOfPeriod;
	}

	public String getCashReceiveCode(){
		return cashReceiveCode;
	}

	public void setCashReceiveCode(String cashReceiveCode){
		if ( cashReceiveCode == null ) {
			cashReceiveCode = "";
		}
		this.cashReceiveCode = cashReceiveCode;
	}

	public String getPettycashPaymentCode(){
		return pettycashPaymentCode;
	}

	public void setPettycashPaymentCode(String pettycashPaymentCode){
		if ( pettycashPaymentCode == null ) {
			pettycashPaymentCode = "";
		}
		this.pettycashPaymentCode = pettycashPaymentCode;
	}

	public String getPettycashReplaceCode(){
		return pettycashReplaceCode;
	}

	public void setPettycashReplaceCode(String pettycashReplaceCode){
		if ( pettycashReplaceCode == null ) {
			pettycashReplaceCode = "";
		}
		this.pettycashReplaceCode = pettycashReplaceCode;
	}

	public String getBankDepositCode(){
		return bankDepositCode;
	}

	public void setBankDepositCode(String bankDepositCode){
		if ( bankDepositCode == null ) {
			bankDepositCode = "";
		}
		this.bankDepositCode = bankDepositCode;
	}

	public String getBankPaymentPoCode(){
		return bankPaymentPoCode;
	}

	public void setBankPaymentPoCode(String bankPaymentPoCode){
		if ( bankPaymentPoCode == null ) {
			bankPaymentPoCode = "";
		}
		this.bankPaymentPoCode = bankPaymentPoCode;
	}

	public String getBankPaymentNonpoCode(){
		return bankPaymentNonpoCode;
	}

	public void setBankPaymentNonpoCode(String bankPaymentNonpoCode){
		if ( bankPaymentNonpoCode == null ) {
			bankPaymentNonpoCode = "";
		}
		this.bankPaymentNonpoCode = bankPaymentNonpoCode;
	}

	public String getPurchaseOrderCode(){
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode){
		if ( purchaseOrderCode == null ) {
			purchaseOrderCode = "";
		}
		this.purchaseOrderCode = purchaseOrderCode;
	}

	public String getGeneralLedgerCode(){
		return generalLedgerCode;
	}

	public void setGeneralLedgerCode(String generalLedgerCode){
		if ( generalLedgerCode == null ) {
			generalLedgerCode = "";
		}
		this.generalLedgerCode = generalLedgerCode;
	}

	public double getMaxPettycashReplenis(){
		return maxPettycashReplenis;
	}

	public void setMaxPettycashReplenis(double maxPettycashReplenis){
		this.maxPettycashReplenis = maxPettycashReplenis;
	}

	public double getMaxPettycashTransaction(){
		return maxPettycashTransaction;
	}

	public void setMaxPettycashTransaction(double maxPettycashTransaction){
		this.maxPettycashTransaction = maxPettycashTransaction;
	}

	public String getBookingCurrencyCode(){
		return bookingCurrencyCode;
	}

	public void setBookingCurrencyCode(String bookingCurrencyCode){
		if ( bookingCurrencyCode == null ) {
			bookingCurrencyCode = "";
		}
		this.bookingCurrencyCode = bookingCurrencyCode;
	}

	public long getBookingCurrencyId(){
		return bookingCurrencyId;
	}

	public void setBookingCurrencyId(long bookingCurrencyId){
		this.bookingCurrencyId = bookingCurrencyId;
	}

	public long getSystemLocation(){
		return systemLocation;
	}

	public void setSystemLocation(long systemLocation){
		this.systemLocation = systemLocation;
	}

	public String getActivationCode(){
		return activationCode;
	}

	public void setActivationCode(String activationCode){
		if ( activationCode == null ) {
			activationCode = "";
		}
		this.activationCode = activationCode;
	}

	public String getSystemLocationCode(){
		return systemLocationCode;
	}

	public void setSystemLocationCode(String systemLocationCode){
		if ( systemLocationCode == null ) {
			systemLocationCode = "";
		}
		this.systemLocationCode = systemLocationCode;
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

        public String getAddress2() {
            return this.address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getInvoiceCode() {
            return this.invoiceCode;
        }

        public void setInvoiceCode(String invoiceCode) {
            this.invoiceCode = invoiceCode;
        }

        public int getDepartmentLevel() {
            return this.departmentLevel;
        }

        public void setDepartmentLevel(int departmentLevel) {
            this.departmentLevel = departmentLevel;
        }

        public double getGovernmentVat() {
            return this.governmentVat;
        }

        public void setGovernmentVat(double governmentVat) {
            this.governmentVat = governmentVat;
        }

        public String getProjectCode() {
            return this.projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public Date getLastUpdate() {
            return this.lastUpdate;
        }

        public void setLastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
        }

        public String getPaymentCode() {
            return this.paymentCode;
        }

        public void setPaymentCode(String paymentCode) {
            this.paymentCode = paymentCode;
        }

        public String getInstallBudgetCode() {
            return this.installBudgetCode;
        }

        public void setInstallBudgetCode(String installBudgetCode) {
            this.installBudgetCode = installBudgetCode;
        }

        public String getInstallTravelCode() {
            return this.installTravelCode;
        }

        public void setInstallTravelCode(String installTravelCode) {
            this.installTravelCode = installTravelCode;
        }

        public String getInstallSettlementCode() {
            return this.installSettlementCode;
        }

        public void setInstallSettlementCode(String installSettlementCode) {
            this.installSettlementCode = installSettlementCode;
        }

        public int getCategoryLevel() {
            return this.categoryLevel;
        }

        public void setCategoryLevel(int categoryLevel) {
            this.categoryLevel = categoryLevel;
        }

        public String getCustomerRequestCode() {
            return this.customerRequestCode;
        }

        public void setCustomerRequestCode(String customerRequestCode) {
            this.customerRequestCode = customerRequestCode;
        }

        public String getIntroLetterCode() {
            return this.introLetterCode;
        }

        public void setIntroLetterCode(String introLetterCode) {
            this.introLetterCode = introLetterCode;
        }

		public String getProposalCode() {
			return this.proposalCode;
		}

		public void setProposalCode(String proposalCode) {
			this.proposalCode = proposalCode;
        }
                
                /**
                 * Getter for property phone.
                 * @return Value of property phone.
                 */
                public String getPhone() {
                    return this.phone;
                }
                
                /**
                 * Setter for property phone.
                 * @param phone New value of property phone.
                 */
                public void setPhone(String phone) {
                    this.phone = phone;
                }
                
                /**
                 * Getter for property fax.
                 * @return Value of property fax.
                 */
                public String getFax() {
                    return this.fax;
                }
                
                /**
                 * Setter for property fax.
                 * @param fax New value of property fax.
                 */
                public void setFax(String fax) {
                    this.fax = fax;
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
                 * Getter for property website.
                 * @return Value of property website.
                 */
                public String getWebsite() {
                    return this.website;
                }
                
                /**
                 * Setter for property website.
                 * @param website New value of property website.
                 */
                public void setWebsite(String website) {
                    this.website = website;
                }
                
                /**
                 * Getter for property purhaseRequestCode.
                 * @return Value of property purhaseRequestCode.
                 */
                public String getPurchaseRequestCode() {
                    return this.purchaseRequestCode;
                }
                
                /**
                 * Setter for property purhaseRequestCode.
                 * @param purhaseRequestCode New value of property purhaseRequestCode.
                 */
                public void setPurchaseRequestCode(String purchaseRequestCode) {
                    this.purchaseRequestCode = purchaseRequestCode;
                }
                
                /**
                 * Getter for property defaultSalesMargin.
                 * @return Value of property defaultSalesMargin.
                 */
                public double getDefaultSalesMargin() {
                    return this.defaultSalesMargin;
                }
                
                /**
                 * Setter for property defaultSalesMargin.
                 * @param defaultSalesMargin New value of property defaultSalesMargin.
                 */
                public void setDefaultSalesMargin(double defaultSalesMargin) {
                    this.defaultSalesMargin = defaultSalesMargin;
                }
                
                /**
                 * Getter for property returnGoodsCode.
                 * @return Value of property returnGoodsCode.
                 */
                public String getReturnGoodsCode() {
                    return this.returnGoodsCode;
                }
                
                /**
                 * Setter for property returnGoodsCode.
                 * @param returnGoodsCode New value of property returnGoodsCode.
                 */
                public void setReturnGoodsCode(String returnGoodsCode) {
                    this.returnGoodsCode = returnGoodsCode;
                }
                
                /**
                 * Getter for property transferGoodsCode.
                 * @return Value of property transferGoodsCode.
                 */
                public String getTransferGoodsCode() {
                    return this.transferGoodsCode;
                }
                
                /**
                 * Setter for property transferGoodsCode.
                 * @param transferGoodsCode New value of property transferGoodsCode.
                 */
                public void setTransferGoodsCode(String transferGoodsCode) {
                    this.transferGoodsCode = transferGoodsCode;
                }
                
                /**
                 * Getter for property costingGoodsCode.
                 * @return Value of property costingGoodsCode.
                 */
                public String getCostingGoodsCode() {
                    return this.costingGoodsCode;
                }
                
                /**
                 * Setter for property costingGoodsCode.
                 * @param costingGoodsCode New value of property costingGoodsCode.
                 */
                public void setCostingGoodsCode(String costingGoodsCode) {
                    this.costingGoodsCode = costingGoodsCode;
                }
                
                /**
                 * Getter for property adjustmentCode.
                 * @return Value of property adjustmentCode.
                 */
                public String getAdjustmentCode() {
                    return this.adjustmentCode;
                }
                
                /**
                 * Setter for property adjustmentCode.
                 * @param adjustmentCode New value of property adjustmentCode.
                 */
                public void setAdjustmentCode(String adjustmentCode) {
                    this.adjustmentCode = adjustmentCode;
                }
                
                /**
                 * Getter for property opnameCode.
                 * @return Value of property opnameCode.
                 */
                public String getOpnameCode() {
                    return this.opnameCode;
                }
                
                /**
                 * Setter for property opnameCode.
                 * @param opnameCode New value of property opnameCode.
                 */
                public void setOpnameCode(String opnameCode) {
                    this.opnameCode = opnameCode;
                }
                
                /**
                 * Getter for property integrationHotelSystem.
                 * @return Value of property integrationHotelSystem.
                 */
                public int getIntegratedHotelSystem() {
                    return this.integratedHotelSystem;
                }
                
                /**
                 * Setter for property integrationHotelSystem.
                 * @param integrationHotelSystem New value of property integrationHotelSystem.
                 */
                public void setIntegratedHotelSystem(int integratedHotelSystem) {
                    this.integratedHotelSystem = integratedHotelSystem;
                }
                
                /**
                 * Getter for property integratedFinanceSystem.
                 * @return Value of property integratedFinanceSystem.
                 */
                public int getIntegratedFinanceSystem() {
                    return this.integratedFinanceSystem;
                }
                
                /**
                 * Setter for property integratedFinanceSystem.
                 * @param integratedFinanceSystem New value of property integratedFinanceSystem.
                 */
                public void setIntegratedFinanceSystem(int integratedFinanceSystem) {
                    this.integratedFinanceSystem = integratedFinanceSystem;
                }
                
                /**
                 * Getter for property shopOrderCode.
                 * @return Value of property shopOrderCode.
                 */
                public String getShopOrderCode() {
                    return this.shopOrderCode;
                }
                
                /**
                 * Setter for property shopOrderCode.
                 * @param shopOrderCode New value of property shopOrderCode.
                 */
                public void setShopOrderCode(String shopOrderCode) {
                    this.shopOrderCode = shopOrderCode;
                }
                
                /**
                 * Getter for property pinjamanKoperasiCode.
                 * @return Value of property pinjamanKoperasiCode.
                 */
                public String getPinjamanKoperasiCode() {
                    return this.pinjamanKoperasiCode;
                }
                
                /**
                 * Setter for property pinjamanKoperasiCode.
                 * @param pinjamanKoperasiCode New value of property pinjamanKoperasiCode.
                 */
                public void setPinjamanKoperasiCode(String pinjamanKoperasiCode) {
                    this.pinjamanKoperasiCode = pinjamanKoperasiCode;
                }
                
                /**
                 * Getter for property pinjamanBankCode.
                 * @return Value of property pinjamanBankCode.
                 */
                public String getPinjamanBankCode() {
                    return this.pinjamanBankCode;
                }
                
                /**
                 * Setter for property pinjamanBankCode.
                 * @param pinjamanBankCode New value of property pinjamanBankCode.
                 */
                public void setPinjamanBankCode(String pinjamanBankCode) {
                    this.pinjamanBankCode = pinjamanBankCode;
                }
                
                /**
                 * Getter for property bayarAngsuranCode.
                 * @return Value of property bayarAngsuranCode.
                 */
                public String getBayarAngsuranCode() {
                    return this.bayarAngsuranCode;
                }
                
                /**
                 * Setter for property bayarAngsuranCode.
                 * @param bayarAngsuranCode New value of property bayarAngsuranCode.
                 */
                public void setBayarAngsuranCode(String bayarAngsuranCode) {
                    this.bayarAngsuranCode = bayarAngsuranCode;
                }
                
                /**
                 * Getter for property bayarAngsuranBankCode.
                 * @return Value of property bayarAngsuranBankCode.
                 */
                public String getBayarAngsuranBankCode() {
                    return this.bayarAngsuranBankCode;
                }
                
                /**
                 * Setter for property bayarAngsuranBankCode.
                 * @param bayarAngsuranBankCode New value of property bayarAngsuranBankCode.
                 */
                public void setBayarAngsuranBankCode(String bayarAngsuranBankCode) {
                    this.bayarAngsuranBankCode = bayarAngsuranBankCode;
                }
                
                /**
                 * Getter for property pinjamanKoperasiBankCode.
                 * @return Value of property pinjamanKoperasiBankCode.
                 */
                public String getPinjamanKoperasiBankCode() {
                    return this.pinjamanKoperasiBankCode;
                }
                
                /**
                 * Setter for property pinjamanKoperasiBankCode.
                 * @param pinjamanKoperasiBankCode New value of property pinjamanKoperasiBankCode.
                 */
                public void setPinjamanKoperasiBankCode(String pinjamanKoperasiBankCode) {
                    this.pinjamanKoperasiBankCode = pinjamanKoperasiBankCode;
                }
                
                /**
                 * Getter for property bayarAngsuranKopBankCode.
                 * @return Value of property bayarAngsuranKopBankCode.
                 */
                public String getBayarAngsuranKopBankCode() {
                    return this.bayarAngsuranKopBankCode;
                }
                
                /**
                 * Setter for property bayarAngsuranKopBankCode.
                 * @param bayarAngsuranKopBankCode New value of property bayarAngsuranKopBankCode.
                 */
                public void setBayarAngsuranKopBankCode(String bayarAngsuranKopBankCode) {
                    this.bayarAngsuranKopBankCode = bayarAngsuranKopBankCode;
                }
                
}
