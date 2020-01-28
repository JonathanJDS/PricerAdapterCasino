
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour ESLRoamInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ESLRoamInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sweep" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="enteredIntoRoaming" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lastUpdateAtempt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pricerViewState" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ESLRoamInfo", propOrder = {
    "level",
    "sweep",
    "enteredIntoRoaming",
    "lastUpdateAtempt",
    "pricerViewState"
})
public class ESLRoamInfo {

    protected int level;
    protected int sweep;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar enteredIntoRoaming;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdateAtempt;
    protected int pricerViewState;

    /**
     * Obtient la valeur de la propriété level.
     * 
     */
    public int getLevel() {
        return level;
    }

    /**
     * Définit la valeur de la propriété level.
     * 
     */
    public void setLevel(int value) {
        this.level = value;
    }

    /**
     * Obtient la valeur de la propriété sweep.
     * 
     */
    public int getSweep() {
        return sweep;
    }

    /**
     * Définit la valeur de la propriété sweep.
     * 
     */
    public void setSweep(int value) {
        this.sweep = value;
    }

    /**
     * Obtient la valeur de la propriété enteredIntoRoaming.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEnteredIntoRoaming() {
        return enteredIntoRoaming;
    }

    /**
     * Définit la valeur de la propriété enteredIntoRoaming.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEnteredIntoRoaming(XMLGregorianCalendar value) {
        this.enteredIntoRoaming = value;
    }

    /**
     * Obtient la valeur de la propriété lastUpdateAtempt.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdateAtempt() {
        return lastUpdateAtempt;
    }

    /**
     * Définit la valeur de la propriété lastUpdateAtempt.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdateAtempt(XMLGregorianCalendar value) {
        this.lastUpdateAtempt = value;
    }

    /**
     * Obtient la valeur de la propriété pricerViewState.
     * 
     */
    public int getPricerViewState() {
        return pricerViewState;
    }

    /**
     * Définit la valeur de la propriété pricerViewState.
     * 
     */
    public void setPricerViewState(int value) {
        this.pricerViewState = value;
    }

}
