
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour LinkInformation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="LinkInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modelId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="modelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeSubcellId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemMustExist" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allowMovingPL" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="eslProperties" type="{http://public_5_0.interface.pricer.se/}PropertyValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinkInformation", propOrder = {
    "modelId",
    "modelName",
    "homeSubcellId",
    "itemMustExist",
    "allowMovingPL",
    "position",
    "eslProperties"
})
public class LinkInformation {

    protected Integer modelId;
    protected String modelName;
    protected String homeSubcellId;
    protected Boolean itemMustExist;
    protected Boolean allowMovingPL;
    protected Integer position;
    protected List<PropertyValue> eslProperties;

    /**
     * Obtient la valeur de la propriété modelId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getModelId() {
        return modelId;
    }

    /**
     * Définit la valeur de la propriété modelId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setModelId(Integer value) {
        this.modelId = value;
    }

    /**
     * Obtient la valeur de la propriété modelName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Définit la valeur de la propriété modelName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelName(String value) {
        this.modelName = value;
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
     * Obtient la valeur de la propriété itemMustExist.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isItemMustExist() {
        return itemMustExist;
    }

    /**
     * Définit la valeur de la propriété itemMustExist.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setItemMustExist(Boolean value) {
        this.itemMustExist = value;
    }

    /**
     * Obtient la valeur de la propriété allowMovingPL.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowMovingPL() {
        return allowMovingPL;
    }

    /**
     * Définit la valeur de la propriété allowMovingPL.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowMovingPL(Boolean value) {
        this.allowMovingPL = value;
    }

    /**
     * Obtient la valeur de la propriété position.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Définit la valeur de la propriété position.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosition(Integer value) {
        this.position = value;
    }

    /**
     * Gets the value of the eslProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eslProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEslProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyValue }
     * 
     * 
     */
    public List<PropertyValue> getEslProperties() {
        if (eslProperties == null) {
            eslProperties = new ArrayList<PropertyValue>();
        }
        return this.eslProperties;
    }

}
