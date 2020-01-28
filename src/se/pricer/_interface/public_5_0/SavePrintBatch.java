
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour savePrintBatch complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="savePrintBatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batch" type="{http://public_5_0.interface.pricer.se/}PrintBatch" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "savePrintBatch", propOrder = {
    "batch"
})
public class SavePrintBatch {

    protected PrintBatch batch;

    /**
     * Obtient la valeur de la propriété batch.
     * 
     * @return
     *     possible object is
     *     {@link PrintBatch }
     *     
     */
    public PrintBatch getBatch() {
        return batch;
    }

    /**
     * Définit la valeur de la propriété batch.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintBatch }
     *     
     */
    public void setBatch(PrintBatch value) {
        this.batch = value;
    }

}
