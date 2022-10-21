package com.services.fastmart.entity;


public class CartItem {
    
	private long itemId;
	
	private String itemName;
	
	private String itemImageUrl;
	
	private int itemStock;
	
	private String itemManufacturer;
	
	private int itemQuantity;
	
	private int itemPrice;
	
	public CartItem() {
		
	}

	

	public CartItem(long itemId, String itemName, String itemImageUrl, int itemStock, String itemManufacturer,
			int itemQuantity, int itemPrice) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemImageUrl = itemImageUrl;
		this.itemStock = itemStock;
		this.itemManufacturer = itemManufacturer;
		this.itemQuantity = itemQuantity;
		this.itemPrice=itemPrice;
	}



	public int getItemPrice() {
		return itemPrice;
	}



	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getItemImageUrl() {
		return itemImageUrl;
	}



	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}



	public int getItemStock() {
		return itemStock;
	}



	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}



	public String getItemManufacturer() {
		return itemManufacturer;
	}



	public void setItemManufacturer(String itemManufacturer) {
		this.itemManufacturer = itemManufacturer;
	}



	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	
	
}
