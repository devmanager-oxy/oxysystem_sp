
package com.project.ccs.postransaction.costing; 
 
import java.util.Date;
import com.project.main.entity.*;

public class CostingItem extends Entity { 

	private long costingId;
	private long itemMasterId;
	private int qty;
	private double price;
	private double amount;
	private double recipe;
	private double recipeAmount;

	public long getCostingId(){ 
		return costingId; 
	} 

	public void setCostingId(long costingId){ 
		this.costingId = costingId; 
	} 

	public long getItemMasterId(){ 
		return itemMasterId; 
	} 

	public void setItemMasterId(long itemMasterId){ 
		this.itemMasterId = itemMasterId; 
	} 

	public int getQty(){ 
		return qty; 
	} 

	public void setQty(int qty){ 
		this.qty = qty; 
	} 

	public double getPrice(){ 
		return price; 
	} 

	public void setPrice(double price){ 
		this.price = price; 
	} 

	public double getAmount(){ 
		return amount; 
	} 

	public void setAmount(double amount){ 
		this.amount = amount; 
	} 

	public double getRecipe(){ 
		return recipe; 
	} 

	public void setRecipe(double recipe){ 
		this.recipe = recipe; 
	} 

	public double getRecipeAmount(){ 
		return recipeAmount; 
	} 

	public void setRecipeAmount(double recipeAmount){ 
		this.recipeAmount = recipeAmount; 
	} 

}
