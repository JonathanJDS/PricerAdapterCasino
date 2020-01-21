package com.pricer.product;

import java.util.List;

public class ProductBase {
	
	private String itemDptIdRef;
	private String itemID;
	private String itemName;
	private String masterEAN;
	private List<String> lstEANsic;
	private String itemIPF;
	private String flagPromo;
	private String pav;
	private String flagPrixKilo;
	private String unitCode;
	private String itemSize;
	private String codeLogo;

	
	
	
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
	
	public String getMasterEAN() {
		return masterEAN;
	}
	public void setMasterEAN(String masterEAN) {
		this.masterEAN = masterEAN;
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
	
	
	
	

}
