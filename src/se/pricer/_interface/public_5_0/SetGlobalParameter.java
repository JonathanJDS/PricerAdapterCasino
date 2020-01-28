
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour setGlobalParameter complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="setGlobalParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="globalParameter" type="{http://public_5_0.interface.pricer.se/}GlobalParameter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setGlobalParameter", propOrder = {
    "globalParameter"
})
public class SetGlobalParameter {

    protected GlobalParameter globalParameter;

    /**
     * Obtient la valeur de la propriété globalParameter.
     * 
     * @return
     *     possible object is
     *     {@link GlobalParameter }
     *     
     */
    public GlobalParameter getGlobalParameter() {
        return globalParameter;
    }

    /**
     * Définit la valeur de la propriété globalParameter.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalParameter }
     *     
     */
    public void setGlobalParameter(GlobalParameter value) {
        this.globalParameter = value;
    }

}
