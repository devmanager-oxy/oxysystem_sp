/***********************************\
|  Create by Dek-Ndut               |
|  Karya kami mohon jangan dibajak  |
|                                   |
|  11/14/2008 9:58:07 AM
\***********************************/

package com.project.ccs.postransaction.sales;

import java.util.Date;
import com.project.main.entity.*;


public class SalesDetail extends Entity {

	private long salesId = 0;
	private long productMasterId = 0;
	private int squence = 0;
	private double sellingPrice = 0;
	private int status = 0;
	private long currencyId = 0;
	private long companyId = 0;
	private double qty = 0;
	private double total = 0;
        
        private double discountPercent = 0;
        private double discountAmount = 0;
        
        /**
         * Holds value of property proposalId.
         */
        private long proposalId;
        
        /**
         * Holds value of property cogs.
         */
        private double cogs;
        
	public long getSalesId(){
            return salesId;
        }

	public void setSalesId(long salesId){
            this.salesId = salesId;
        }

	public long getProductMasterId(){
		return productMasterId;
	}

	public void setProductMasterId(long productMasterId){
		this.productMasterId = productMasterId;
	}

	public int getSquence(){
		return squence;
	}

	public void setSquence(int squence){
		this.squence = squence;
	}

	public double getSellingPrice(){
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice){
		this.sellingPrice = sellingPrice;
	}

	public int getStatus(){
		return status;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public long getCurrencyId(){
		return currencyId;
	}

	public void setCurrencyId(long currencyId){
		this.currencyId = currencyId;
	}

	public long getCompanyId(){
		return companyId;
	}

	public void setCompanyId(long companyId){
		this.companyId = companyId;
	}

	public double getQty(){
		return qty;
	}

	public void setQty(double qty){
		this.qty = qty;
	}

	public double getTotal(){
		return total;
	}

	public void setTotal(double total){
		this.total = total;
	}
        
        public double getDiscountPercent() {
            return this.discountPercent;
        }
        
        public void setDiscountPercent(double discountPercent) {
            this.discountPercent = discountPercent;
        }
        
        public double getDiscountAmount() {
            return this.discountAmount;
        }
        
        public void setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
        }      
        
        /**
         * Getter for property proposalId.
         * @return Value of property proposalId.
         */
        public long getProposalId() {
            return this.proposalId;
        }
        
        /**
         * Setter for property proposalId.
         * @param proposalId New value of property proposalId.
         */
        public void setProposalId(long proposalId) {
            this.proposalId = proposalId;
        }
        
        /**
         * Getter for property cogs.
         * @return Value of property cogs.
         */
        public double getCogs() {
            return this.cogs;
        }
        
        /**
         * Setter for property cogs.
         * @param cogs New value of property cogs.
         */
        public void setCogs(double cogs) {
            this.cogs = cogs;
        }
        
}
