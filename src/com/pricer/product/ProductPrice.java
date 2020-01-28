package com.pricer.product;
import java.util.List;
public class ProductPrice {



    //opcode = 03
    private String codeInterne;
    private String EAN;
    private List<String> lstEANsic;
    private String libelle;
    private String prix;
    private String prixUnitaire;
    private String prixEnFranc;
    private String flagPromo;
    private String uniteDeVente;
    private String famille;
    private String contenance;
    private String eanAimprimer;
    private String codeLogo;
    private String sticker;
    private String typeEEG;
    private String pav;
    private String montantECOPART;
    private String flagECOPART;
    private String flagPrixKilo;
    private String prixDeVentePrecedent;
    private String prixDeVenteaLunite;


    //opcode = 14
    private String dateApplication;





    public String getPrixDeVentePrecedent() 							{	return prixDeVentePrecedent;						}
    public void setPrixDeVentePrecedent(String prixDeVentePrecedent) 	{	this.prixDeVentePrecedent = prixDeVentePrecedent;	}


    public String getPrixDeVenteaLunite() 							{	return prixDeVenteaLunite;						}
    public void setPrixDeVenteaLunite(String prixDeVenteaLunite) 	{	this.prixDeVenteaLunite = prixDeVenteaLunite;	}

    public String getFlagPrixKilo() 							{	return flagPrixKilo;					}
    public void setFlagPrixKilo(String flagPrixKilo) 			{	this.flagPrixKilo = flagPrixKilo;		}

    public String getCodeInterne() 								{	return codeInterne;						}
    public void setCodeInterne(String codeInterne) 				{	this.codeInterne = codeInterne;			}

    public String getEAN() 										{	return EAN;								}
    public void setEAN(String eAN) 								{	EAN = eAN;								}


    public String getLibelle() 									{	return libelle;							}
    public void setLibelle(String libelle) 						{	this.libelle = libelle;					}

    public String getPrix() 									{	return prix;							}
    public void setPrix(String prix) 							{	this.prix = prix;						}

    public String getPrixUnitaire() 							{	return prixUnitaire;					}
    public void setPrixUnitaire(String prixUnitaire) 			{	this.prixUnitaire = prixUnitaire;		}

    public String getPrixEnFranc() 								{	return prixEnFranc;						}
    public void setPrixEnFranc(String prixEnFranc) 				{	this.prixEnFranc = prixEnFranc;			}

    public String getFlagPromo() 								{	return flagPromo;						}
    public void setFlagPromo(String flagPromo) 					{	this.flagPromo = flagPromo;				}

    public String getUniteDeVente() 							{	return uniteDeVente;					}
    public void setUniteDeVente(String uniteDeVente) 			{	this.uniteDeVente = uniteDeVente;		}

    public String getFamille() 									{	return famille;							}
    public void setFamille(String famille) 						{	this.famille = famille;					}

    public String getContenance() 								{	return contenance;						}
    public void setContenance(String contenance) 				{	this.contenance = contenance;			}

    public String getEanAimprimer() 							{	return eanAimprimer;					}
    public void setEanAimprimer(String eanAimprimer) 			{	this.eanAimprimer = eanAimprimer;		}

    public String getCodeLogo() 								{	return codeLogo;						}
    public void setCodeLogo(String codeLogo) 					{	this.codeLogo = codeLogo;				}

    public String getSticker() 									{	return sticker;							}
    public void setSticker(String sticker) 						{	this.sticker = sticker;					}

    public String getTypeEEG() 									{	return typeEEG;							}
    public void setTypeEEG(String typeEEG) 						{	this.typeEEG = typeEEG;					}

    public String getDateApplication() 							{	return dateApplication;					}
    public void setDateApplication(String dateApplication) 		{	this.dateApplication = dateApplication;	}

    public List<String> getLstEANsic() 							{	return lstEANsic;						}
    public void setLstEANsic(List<String> lstEANsic) 			{	this.lstEANsic = lstEANsic;				}

    public String getPav() 										{	return pav;								}
    public void setPav(String pav) 								{	this.pav = pav;							}


    public String getMontantECOPART() 							{	return montantECOPART;					}
    public void setMontantECOPART(String montantECOPART) 		{	this.montantECOPART = montantECOPART;	}

    public String getFlagECOPART() 								{	return flagECOPART;						}
    public void setFlagECOPART(String flagECOPART)				{	this.flagECOPART = flagECOPART;			}




}
