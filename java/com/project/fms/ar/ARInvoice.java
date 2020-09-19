
package com.project.fms.ar; 
 
import java.util.Date;
import com.project.main.entity.*;

public class ARInvoice extends Entity { 

	private long currencyId;
	private String invoiceNumber = "";
	private long termOfPaymentId;
	private Date date;
	private Date transDate;
	private String journalNumber = "";
	private String journalPrefix = "";
	private int journalCounter;
	private Date dueDate;
	private int vat;
	private String memo = "";
	private double discountPercent;
	private double discount;
	private double vatPercent;
	private double vatAmount;
	private double total;
	private int status;
	private long operatorId;
	private long customerId;
	private long companyId;
	private long projectId;
	private long projectTermId;

        /**
         * Holds value of property bankAccountId.
         */
        private long bankAccountId;
        
        /**
         * Holds value of property lastPaymentDate.
         */
        private Date lastPaymentDate;
        
        /**
         * Holds value of property lastPaymentAmount.
         */
        private double lastPaymentAmount;
        
        /**
         * Holds value of property salesSource.
         */
        private int salesSource;
        
	public long getCurrencyId(){ 
		return currencyId; 
	} 

	public void setCurrencyId(long currencyId){ 
		this.currencyId = currencyId; 
	} 

	public String getInvoiceNumber(){ 
		return invoiceNumber; 
	} 

	public void setInvoiceNumber(String invoiceNumber){ 
		if ( invoiceNumber == null ) {
			invoiceNumber = ""; 
		} 
		this.invoiceNumber = invoiceNumber; 
	} 

	public long getTermOfPaymentId(){ 
		return termOfPaymentId; 
	} 

	public void setTermOfPaymentId(long termOfPaymentId){ 
		this.termOfPaymentId = termOfPaymentId; 
	} 

	public Date getDate(){ 
		return date; 
	} 

	public void setDate(Date date){ 
		this.date = date; 
	} 

	public Date getTransDate(){ 
		return transDate; 
	} 

	public void setTransDate(Date transDate){ 
		this.transDate = transDate; 
	} 

	public String getJournalNumber(){ 
		return journalNumber; 
	} 

	public void setJournalNumber(String journalNumber){ 
		if ( journalNumber == null ) {
			journalNumber = ""; 
		} 
		this.journalNumber = journalNumber; 
	} 

	public String getJournalPrefix(){ 
		return journalPrefix; 
	} 

	public void setJournalPrefix(String journalPrefix){ 
		if ( journalPrefix == null ) {
			journalPrefix = ""; 
		} 
		this.journalPrefix = journalPrefix; 
	} 

	public int getJournalCounter(){ 
		return journalCounter; 
	} 

	public void setJournalCounter(int journalCounter){ 
		this.journalCounter = journalCounter; 
	} 

	public Date getDueDate(){ 
		return dueDate; 
	} 

	public void setDueDate(Date dueDate){ 
		this.dueDate = dueDate; 
	} 

	public int getVat(){ 
		return vat; 
	} 

	public void setVat(int vat){ 
		this.vat = vat; 
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

	public double getDiscountPercent(){ 
		return discountPercent; 
	} 

	public void setDiscountPercent(double discountPercent){ 
		this.discountPercent = discountPercent; 
	} 

	public double getDiscount(){ 
		return discount; 
	} 

	public void setDiscount(double discount){ 
		this.discount = discount; 
	} 

	public double getVatPercent(){ 
		return vatPercent; 
	} 

	public void setVatPercent(double vatPercent){ 
		this.vatPercent = vatPercent; 
	} 

	public double getVatAmount(){ 
		return vatAmount; 
	} 

	public void setVatAmount(double vatAmount){ 
		this.vatAmount = vatAmount; 
	} 

	public double getTotal(){ 
		return total; 
	} 

	public void setTotal(double total){ 
		this.total = total; 
	} 

	public int getStatus(){ 
		return status; 
	} 

	public void setStatus(int status){ 
		this.status = status; 
	} 

	public long getOperatorId(){ 
		return operatorId; 
	} 

	public void setOperatorId(long operatorId){ 
		this.operatorId = operatorId; 
	} 

	public long getCustomerId(){ 
		return customerId; 
	} 

	public void setCustomerId(long customerId){ 
		this.customerId = customerId; 
	} 

	public long getCompanyId(){ 
		return companyId; 
	} 

	public void setCompanyId(long companyId){ 
		this.companyId = companyId; 
	} 

	public long getProjectId(){ 
		return projectId; 
	} 

	public void setProjectId(long projectId){ 
		this.projectId = projectId; 
	} 

	public long getProjectTermId(){ 
		return projectTermId; 
	} 

	public void setProjectTermId(long projectTermId){ 
		this.projectTermId = projectTermId; 
	} 

        /**
         * Getter for property bankAccountId.
         * @return Value of property bankAccountId.
         */
        public long getBankAccountId() {
            return this.bankAccountId;
        }
        
        /**
         * Setter for property bankAccountId.
         * @param bankAccountId New value of property bankAccountId.
         */
        public void setBankAccountId(long bankAccountId) {
            this.bankAccountId = bankAccountId;
        }
        
        /**
         * Getter for property lastPaymentDate.
         * @return Value of property lastPaymentDate.
         */
        public Date getLastPaymentDate() {
            return this.lastPaymentDate;
        }
        
        /**
         * Setter for property lastPaymentDate.
         * @param lastPaymentDate New value of property lastPaymentDate.
         */
        public void setLastPaymentDate(Date lastPaymentDate) {
            this.lastPaymentDate = lastPaymentDate;
        }
        
        /**
         * Getter for property lastPaymentAmount.
         * @return Value of property lastPaymentAmount.
         */
        public double getLastPaymentAmount() {
            return this.lastPaymentAmount;
        }
        
        /**
         * Setter for property lastPaymentAmount.
         * @param lastPaymentAmount New value of property lastPaymentAmount.
         */
        public void setLastPaymentAmount(double lastPaymentAmount) {
            this.lastPaymentAmount = lastPaymentAmount;
        }
        
        /**
         * Getter for property salesSource.
         * @return Value of property salesSource.
         */
        public int getSalesSource() {
            return this.salesSource;
        }
        
        /**
         * Setter for property salesSource.
         * @param salesSource New value of property salesSource.
         */
        public void setSalesSource(int salesSource) {
            this.salesSource = salesSource;
        }
        
}
