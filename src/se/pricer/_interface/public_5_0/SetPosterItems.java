
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour setPosterItems complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="setPosterItems">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="posterId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="items" type="{http://public_5_0.interface.pricer.se/}PosterItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setPosterItems", propOrder = {
    "posterId",
    "items"
})
public class SetPosterItems {

    protected String posterId;
    protected List<PosterItem> items;

    /**
     * Obtient la valeur de la propriété posterId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosterId() {
        return posterId;
    }

    /**
     * Définit la valeur de la propriété posterId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosterId(String value) {
        this.posterId = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PosterItem }
     * 
     * 
     */
    public List<PosterItem> getItems() {
        if (items == null) {
            items = new ArrayList<PosterItem>();
        }
        return this.items;
    }

}
