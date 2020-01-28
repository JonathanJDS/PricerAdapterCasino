
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour TransceiverInfo complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TransceiverInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="basestationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hwId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="failTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransceiverInfo", propOrder = {
    "address",
    "basestationName",
    "hwId",
    "state",
    "failTime",
    "type"
})
public class TransceiverInfo {

    protected String address;
    protected String basestationName;
    protected String hwId;
    protected String state;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar failTime;
    protected String type;

    /**
     * Obtient la valeur de la propri�t� address.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * D�finit la valeur de la propri�t� address.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Obtient la valeur de la propri�t� basestationName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasestationName() {
        return basestationName;
    }

    /**
     * D�finit la valeur de la propri�t� basestationName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasestationName(String value) {
        this.basestationName = value;
    }

    /**
     * Obtient la valeur de la propri�t� hwId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHwId() {
        return hwId;
    }

    /**
     * D�finit la valeur de la propri�t� hwId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHwId(String value) {
        this.hwId = value;
    }

    /**
     * Obtient la valeur de la propri�t� state.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * D�finit la valeur de la propri�t� state.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Obtient la valeur de la propri�t� failTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFailTime() {
        return failTime;
    }

    /**
     * D�finit la valeur de la propri�t� failTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFailTime(XMLGregorianCalendar value) {
        this.failTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * D�finit la valeur de la propri�t� type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
