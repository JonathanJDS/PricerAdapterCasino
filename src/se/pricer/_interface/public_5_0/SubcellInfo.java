
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SubcellInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SubcellInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseStation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="backoffice" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="disabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="transceivers" type="{http://public_5_0.interface.pricer.se/}TransceiverInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubcellInfo", propOrder = {
    "id",
    "baseStation",
    "alias",
    "backoffice",
    "disabled",
    "transceivers"
})
public class SubcellInfo {

    protected String id;
    protected String baseStation;
    protected String alias;
    protected boolean backoffice;
    protected boolean disabled;
    protected List<TransceiverInfo> transceivers;

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété baseStation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseStation() {
        return baseStation;
    }

    /**
     * Définit la valeur de la propriété baseStation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseStation(String value) {
        this.baseStation = value;
    }

    /**
     * Obtient la valeur de la propriété alias.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Définit la valeur de la propriété alias.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Obtient la valeur de la propriété backoffice.
     * 
     */
    public boolean isBackoffice() {
        return backoffice;
    }

    /**
     * Définit la valeur de la propriété backoffice.
     * 
     */
    public void setBackoffice(boolean value) {
        this.backoffice = value;
    }

    /**
     * Obtient la valeur de la propriété disabled.
     * 
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Définit la valeur de la propriété disabled.
     * 
     */
    public void setDisabled(boolean value) {
        this.disabled = value;
    }

    /**
     * Gets the value of the transceivers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transceivers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransceivers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransceiverInfo }
     * 
     * 
     */
    public List<TransceiverInfo> getTransceivers() {
        if (transceivers == null) {
            transceivers = new ArrayList<TransceiverInfo>();
        }
        return this.transceivers;
    }

}
