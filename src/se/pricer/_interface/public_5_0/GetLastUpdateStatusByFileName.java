
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getLastUpdateStatusByFileName complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getLastUpdateStatusByFileName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLastUpdateStatusByFileName", propOrder = {
    "dataFileName"
})
public class GetLastUpdateStatusByFileName {

    protected String dataFileName;

    /**
     * Obtient la valeur de la propriété dataFileName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFileName() {
        return dataFileName;
    }

    /**
     * Définit la valeur de la propriété dataFileName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFileName(String value) {
        this.dataFileName = value;
    }

}
