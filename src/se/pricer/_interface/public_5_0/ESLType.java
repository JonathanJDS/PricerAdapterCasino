
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ESLType complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ESLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="articleNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="screenSize" type="{http://public_5_0.interface.pricer.se/}Dimension" minOccurs="0"/>
 *         &lt;element name="overlaySize" type="{http://public_5_0.interface.pricer.se/}Dimension" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ESLType", propOrder = {
    "name",
    "typeNumber",
    "articleNumber",
    "displayType",
    "screenSize",
    "overlaySize"
})
public class ESLType {

    protected String name;
    protected int typeNumber;
    protected String articleNumber;
    protected String displayType;
    protected Dimension screenSize;
    protected Dimension overlaySize;

    /**
     * Obtient la valeur de la propri�t� name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * D�finit la valeur de la propri�t� name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propri�t� typeNumber.
     * 
     */
    public int getTypeNumber() {
        return typeNumber;
    }

    /**
     * D�finit la valeur de la propri�t� typeNumber.
     * 
     */
    public void setTypeNumber(int value) {
        this.typeNumber = value;
    }

    /**
     * Obtient la valeur de la propri�t� articleNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticleNumber() {
        return articleNumber;
    }

    /**
     * D�finit la valeur de la propri�t� articleNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticleNumber(String value) {
        this.articleNumber = value;
    }

    /**
     * Obtient la valeur de la propri�t� displayType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayType() {
        return displayType;
    }

    /**
     * D�finit la valeur de la propri�t� displayType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayType(String value) {
        this.displayType = value;
    }

    /**
     * Obtient la valeur de la propri�t� screenSize.
     * 
     * @return
     *     possible object is
     *     {@link Dimension }
     *     
     */
    public Dimension getScreenSize() {
        return screenSize;
    }

    /**
     * D�finit la valeur de la propri�t� screenSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Dimension }
     *     
     */
    public void setScreenSize(Dimension value) {
        this.screenSize = value;
    }

    /**
     * Obtient la valeur de la propri�t� overlaySize.
     * 
     * @return
     *     possible object is
     *     {@link Dimension }
     *     
     */
    public Dimension getOverlaySize() {
        return overlaySize;
    }

    /**
     * D�finit la valeur de la propri�t� overlaySize.
     * 
     * @param value
     *     allowed object is
     *     {@link Dimension }
     *     
     */
    public void setOverlaySize(Dimension value) {
        this.overlaySize = value;
    }

}
