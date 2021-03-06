package com.pricer.product;

import java.util.List;

public class ProductBase {
	
	private String itemDptIdRef;
	private String itemID;
	private String itemName;
	private String EAN;
	private List<String> lstEANsic;
	private String itemIPF;
	private String flagPromo;
	private String pav;
	private String flagPrixKilo;
	private String unitCode;
	private String itemSize;
	private String codeLogo;
	private String price;
	private String unitPrice;
	private String francPrice;
	private String priceECOPART;
	private String previousSalePrice;

	
	
	
	public List<String> getLstEANsic() {
		return lstEANsic;
	}
	public void setLstEANsic(List<String> lstEANsic) {
		this.lstEANsic = lstEANsic;
	}
	
	public String getItemDptIdRef() {
		return itemDptIdRef;
	}
	public void setItemDptIdRef(String itemDptIdRef) {
		this.itemDptIdRef = itemDptIdRef;
	}
	
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getEAN() {
		return EAN;
	}
	public void setEAN(String EAN) {
		this.EAN = EAN;
	}
	
	public String getItemIPF() {
		return itemIPF;
	}
	public void setItemIPF(String itemIPF) {
		this.itemIPF = itemIPF;
	}
	
	public String getFlagPromo() {
		return flagPromo;
	}
	public void setFlagPromo(String flagPromo) {
		this.flagPromo = flagPromo;
	}
	
	public String getPav() {
		return pav;
	}
	public void setPav(String pav) {
		this.pav = pav;
	}
	
	public String getFlagPrixKilo() {
		return flagPrixKilo;
	}
	public void setFlagPrixKilo(String flagPrixKilo) {
		this.flagPrixKilo = flagPrixKilo;
	}
	
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
	public String getItemSize() {
		return itemSize;
	}
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	
	public String getCodeLogo() {
		return codeLogo;
	}
	public void setCodeLogo(String codeLogo) {
		this.codeLogo = codeLogo;
	}
	
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
