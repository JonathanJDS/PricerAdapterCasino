
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour generatePrc complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="generatePrc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="configuration" type="{http://public_5_0.interface.pricer.se/}BackupConfiguration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generatePrc", propOrder = {
    "configuration"
})
public class GeneratePrc {

    protected BackupConfiguration configuration;

    /**
     * Obtient la valeur de la propri�t� configuration.
     * 
     * @return
     *     possible object is
     *     {@link BackupConfiguration }
     *     
     */
    public BackupConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * D�finit la valeur de la propri�t� configuration.
     * 
     * @param value
     *     allowed object is
     *     {@link BackupConfiguration }
     *     
     */
    public void setConfiguration(BackupConfiguration value) {
        this.configuration = value;
    }

}
