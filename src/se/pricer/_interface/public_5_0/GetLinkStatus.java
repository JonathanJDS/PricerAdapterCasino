
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getLinkStatus complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getLinkStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="linkStatus" type="{http://public_5_0.interface.pricer.se/}LinkStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLinkStatus", propOrder = {
    "linkStatus"
})
public class GetLinkStatus {

    protected LinkStatus linkStatus;

    /**
     * Obtient la valeur de la propri�t� linkStatus.
     * 
     * @return
     *     possible object is
     *     {@link LinkStatus }
     *     
     */
    public LinkStatus getLinkStatus() {
        return linkStatus;
    }

    /**
     * D�finit la valeur de la propri�t� linkStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkStatus }
     *     
     */
    public void setLinkStatus(LinkStatus value) {
        this.linkStatus = value;
    }

}
