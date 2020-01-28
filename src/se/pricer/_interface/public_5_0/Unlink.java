
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour unlink complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="unlink">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeSubcellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linkOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "unlink", propOrder = {
    "barcode",
    "homeSubcellId",
    "linkOrder"
})
public class Unlink {

    protected String barcode;
    protected String homeSubcellId;
    protected Integer linkOrder;

    /**
     * Obtient la valeur de la propriété barcode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Définit la valeur de la propriété barcode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarcode(String value) {
        this.barcode = value;
    }

    /**
     * Obtient la valeur de la propriété homeSubcellId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeSubcellId() {
        return homeSubcellId;
    }

    /**
     * Définit la valeur de la propriété homeSubcellId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeSubcellId(String value) {
        this.homeSubcellId = value;
    }

    /**
     * Obtient la valeur de la propriété linkOrder.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLinkOrder() {
        return linkOrder;
    }

    /**
     * Définit la valeur de la propriété linkOrder.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLinkOrder(Integer value) {
        this.linkOrder = value;
    }

}
