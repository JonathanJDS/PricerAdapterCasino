
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getESLs complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getESLs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="limit" type="{http://public_5_0.interface.pricer.se/}Limit" minOccurs="0"/>
 *         &lt;element name="subset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getESLs", propOrder = {
    "limit",
    "subset"
})
public class GetESLs {

    protected Limit limit;
    protected int subset;

    /**
     * Obtient la valeur de la propriété limit.
     * 
     * @return
     *     possible object is
     *     {@link Limit }
     *     
     */
    public Limit getLimit() {
        return limit;
    }

    /**
     * Définit la valeur de la propriété limit.
     * 
     * @param value
     *     allowed object is
     *     {@link Limit }
     *     
     */
    public void setLimit(Limit value) {
        this.limit = value;
    }

    /**
     * Obtient la valeur de la propriété subset.
     * 
     */
    public int getSubset() {
        return subset;
    }

    /**
     * Définit la valeur de la propriété subset.
     * 
     */
    public void setSubset(int value) {
        this.subset = value;
    }

}
