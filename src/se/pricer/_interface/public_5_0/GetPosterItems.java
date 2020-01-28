
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getPosterItems complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getPosterItems">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="posterId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPosterItems", propOrder = {
    "posterId"
})
public class GetPosterItems {

    protected String posterId;

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

}
