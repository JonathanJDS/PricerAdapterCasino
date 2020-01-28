
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour PickListItem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PickListItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfItems" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="allowReplacement" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actualItemIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pickTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PickListItem", propOrder = {
    "itemId",
    "numberOfItems",
    "allowReplacement",
    "comment",
    "actualItemIds",
    "pickTime"
})
public class PickListItem {

    protected String itemId;
    protected int numberOfItems;
    protected boolean allowReplacement;
    protected String comment;
    protected List<String> actualItemIds;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickTime;

    /**
     * Obtient la valeur de la propriété itemId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Définit la valeur de la propriété itemId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemId(String value) {
        this.itemId = value;
    }

    /**
     * Obtient la valeur de la propriété numberOfItems.
     * 
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Définit la valeur de la propriété numberOfItems.
     * 
     */
    public void setNumberOfItems(int value) {
        this.numberOfItems = value;
    }

    /**
     * Obtient la valeur de la propriété allowReplacement.
     * 
     */
    public boolean isAllowReplacement() {
        return allowReplacement;
    }

    /**
     * Définit la valeur de la propriété allowReplacement.
     * 
     */
    public void setAllowReplacement(boolean value) {
        this.allowReplacement = value;
    }

    /**
     * Obtient la valeur de la propriété comment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Définit la valeur de la propriété comment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the actualItemIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actualItemIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActualItemIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getActualItemIds() {
        if (actualItemIds == null) {
            actualItemIds = new ArrayList<String>();
        }
        return this.actualItemIds;
    }

    /**
     * Obtient la valeur de la propriété pickTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPickTime() {
        return pickTime;
    }

    /**
     * Définit la valeur de la propriété pickTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickTime(XMLGregorianCalendar value) {
        this.pickTime = value;
    }

}
