
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour print complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="print">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batch" type="{http://public_5_0.interface.pricer.se/}PrintBatch" minOccurs="0"/>
 *         &lt;element name="printerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hostname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "print", propOrder = {
    "batch",
    "printerName",
    "hostname"
})
public class Print {

    protected PrintBatch batch;
    protected String printerName;
    protected String hostname;

    /**
     * Obtient la valeur de la propri�t� batch.
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
     * D�finit la valeur de la propri�t� batch.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintBatch }
     *     
     */
    public void setBatch(PrintBatch value) {
        this.batch = value;
    }

    /**
     * Obtient la valeur de la propri�t� printerName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrinterName() {
        return printerName;
    }

    /**
     * D�finit la valeur de la propri�t� printerName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrinterName(String value) {
        this.printerName = value;
    }

    /**
     * Obtient la valeur de la propri�t� hostname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * D�finit la valeur de la propri�t� hostname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostname(String value) {
        this.hostname = value;
    }

}
