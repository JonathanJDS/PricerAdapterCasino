
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ESLModel complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ESLModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultModel" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="disabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="multiItem" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="itemPositions" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hasOverlay" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="types" type="{http://public_5_0.interface.pricer.se/}ESLType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ESLModel", propOrder = {
    "id",
    "name",
    "defaultModel",
    "disabled",
    "multiItem",
    "itemPositions",
    "hasOverlay",
    "types"
})
public class ESLModel {

    protected int id;
    protected String name;
    protected boolean defaultModel;
    protected boolean disabled;
    protected boolean multiItem;
    protected int itemPositions;
    protected boolean hasOverlay;
    protected List<ESLType> types;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété name.
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
     * Définit la valeur de la propriété name.
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
     * Obtient la valeur de la propriété defaultModel.
     * 
     */
    public boolean isDefaultModel() {
        return defaultModel;
    }

    /**
     * Définit la valeur de la propriété defaultModel.
     * 
     */
    public void setDefaultModel(boolean value) {
        this.defaultModel = value;
    }

    /**
     * Obtient la valeur de la propriété disabled.
     * 
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Définit la valeur de la propriété disabled.
     * 
     */
    public void setDisabled(boolean value) {
        this.disabled = value;
    }

    /**
     * Obtient la valeur de la propriété multiItem.
     * 
     */
    public boolean isMultiItem() {
        return multiItem;
    }

    /**
     * Définit la valeur de la propriété multiItem.
     * 
     */
    public void setMultiItem(boolean value) {
        this.multiItem = value;
    }

    /**
     * Obtient la valeur de la propriété itemPositions.
     * 
     */
    public int getItemPositions() {
        return itemPositions;
    }

    /**
     * Définit la valeur de la propriété itemPositions.
     * 
     */
    public void setItemPositions(int value) {
        this.itemPositions = value;
    }

    /**
     * Obtient la valeur de la propriété hasOverlay.
     * 
     */
    public boolean isHasOverlay() {
        return hasOverlay;
    }

    /**
     * Définit la valeur de la propriété hasOverlay.
     * 
     */
    public void setHasOverlay(boolean value) {
        this.hasOverlay = value;
    }

    /**
     * Gets the value of the types property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the types property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ESLType }
     * 
     * 
     */
    public List<ESLType> getTypes() {
        if (types == null) {
            types = new ArrayList<ESLType>();
        }
        return this.types;
    }

}
