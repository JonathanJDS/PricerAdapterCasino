package com.pricer.product;

public class ProductPrice extends ProductBase{
	
	private String price;
	private String unitPrice;
	private String francPrice;
	private String priceECOPART;
	private String previousSalePrice;
	
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getFrancPrice() {
		return francPrice;
	}
	public void setFrancPrice(String francPrice) {
		this.francPrice = francPrice;
	}
	public String getPriceECOPART() {
		return priceECOPART;
	}
	public void setPriceECOPART(String priceECOPART) {
		this.priceECOPART = priceECOPART;
	}
	public String getPreviousSalePrice() {
		return previousSalePrice;
	}
	public void setPreviousSalePrice(String previousSalePrice) {
		this.previousSalePrice = previousSalePrice;
	}
	
	

}
