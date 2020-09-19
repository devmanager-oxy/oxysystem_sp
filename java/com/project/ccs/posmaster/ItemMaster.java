
package com.project.ccs.posmaster; 
 
import java.util.Date;
import com.project.main.entity.*;

public class ItemMaster extends Entity { 

	private long itemGroupId;
	private long itemCategoryId;
	private long uomPurchaseId;
	private long uomRecipeId;
	private long uomStockId;
	private long uomSalesId;
	private String code = "";
	private String barcode = "";
	private String name = "";
	private double uomPurchaseStockQty;
	private double uomStockRecipeQty;
	private double uomStockSalesQty;
	private int forSale;
	private int forBuy;
	private int isActive;
	private double sellingPrice;
	private double cogs;
	private int recipeItem;

        /**
         * Holds value of property needRecipe.
         */
        private int needRecipe;
        
        /**
         * Holds value of property defaultVendorId.
         */
        private long defaultVendorId;
        
        /**
         * Holds value of property minStock.
         */
        private double minStock;
        
        /**
         * Holds value of property type.
         */
        private int type;
        
	public long getItemGroupId(){ 
		return itemGroupId; 
	} 

	public void setItemGroupId(long itemGroupId){ 
		this.itemGroupId = itemGroupId; 
	} 

	public long getItemCategoryId(){ 
		return itemCategoryId; 
	} 

	public void setItemCategoryId(long itemCategoryId){ 
		this.itemCategoryId = itemCategoryId; 
	} 

	public long getUomPurchaseId(){ 
		return uomPurchaseId; 
	} 

	public void setUomPurchaseId(long uomPurchaseId){ 
		this.uomPurchaseId = uomPurchaseId; 
	} 

	public long getUomRecipeId(){ 
		return uomRecipeId; 
	} 

	public void setUomRecipeId(long uomRecipeId){ 
		this.uomRecipeId = uomRecipeId; 
	} 

	public long getUomStockId(){ 
		return uomStockId; 
	} 

	public void setUomStockId(long uomStockId){ 
		this.uomStockId = uomStockId; 
	} 

	public long getUomSalesId(){ 
		return uomSalesId; 
	} 

	public void setUomSalesId(long uomSalesId){ 
		this.uomSalesId = uomSalesId; 
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

	public String getBarcode(){ 
		return barcode; 
	} 

	public void setBarcode(String barcode){ 
		if ( barcode == null ) {
			barcode = ""; 
		} 
		this.barcode = barcode; 
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

	public double getUomPurchaseStockQty(){ 
		return uomPurchaseStockQty; 
	} 

	public void setUomPurchaseStockQty(double uomPurchaseStockQty){ 
		this.uomPurchaseStockQty = uomPurchaseStockQty; 
	} 

	public double getUomStockRecipeQty(){ 
		return uomStockRecipeQty; 
	} 

	public void setUomStockRecipeQty(double uomStockRecipeQty){ 
		this.uomStockRecipeQty = uomStockRecipeQty; 
	} 

	public double getUomStockSalesQty(){ 
		return uomStockSalesQty; 
	} 

	public void setUomStockSalesQty(double uomStockSalesQty){ 
		this.uomStockSalesQty = uomStockSalesQty; 
	} 

	public int getForSale(){ 
		return forSale; 
	} 

	public void setForSale(int forSale){ 
		this.forSale = forSale; 
	} 

	public int getForBuy(){ 
		return forBuy; 
	} 

	public void setForBuy(int forBuy){ 
		this.forBuy = forBuy; 
	} 

	public int getIsActive(){ 
		return isActive; 
	} 

	public void setIsActive(int isActive){ 
		this.isActive = isActive; 
	} 

	public double getSellingPrice(){ 
		return sellingPrice; 
	} 

	public void setSellingPrice(double sellingPrice){ 
		this.sellingPrice = sellingPrice; 
	} 

	public double getCogs(){ 
		return cogs; 
	} 

	public void setCogs(double cogs){ 
		this.cogs = cogs; 
	} 

	public int getRecipeItem(){ 
		return recipeItem; 
	} 

	public void setRecipeItem(int recipeItem){ 
		this.recipeItem = recipeItem; 
	} 

        /**
         * Getter for property needRecipe.
         * @return Value of property needRecipe.
         */
        public int getNeedRecipe() {
            return this.needRecipe;
        }
        
        /**
         * Setter for property needRecipe.
         * @param needRecipe New value of property needRecipe.
         */
        public void setNeedRecipe(int needRecipe) {
            this.needRecipe = needRecipe;
        }
        
        /**
         * Getter for property defaultVendor.
         * @return Value of property defaultVendor.
         */
        public long getDefaultVendorId() {
            return this.defaultVendorId;
        }
        
        /**
         * Setter for property defaultVendor.
         * @param defaultVendor New value of property defaultVendor.
         */
        public void setDefaultVendorId(long defaultVendorId) {
            this.defaultVendorId = defaultVendorId;
        }
        
        /**
         * Getter for property minStock.
         * @return Value of property minStock.
         */
        public double getMinStock() {
            return this.minStock;
        }
        
        /**
         * Setter for property minStock.
         * @param minStock New value of property minStock.
         */
        public void setMinStock(double minStock) {
            this.minStock = minStock;
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
        
}
