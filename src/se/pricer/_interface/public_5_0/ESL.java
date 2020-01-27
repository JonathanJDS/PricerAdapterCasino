
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour ESL complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ESL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eslType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subcell" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="batteryState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemOrder" type="{http://public_5_0.interface.pricer.se/}ItemOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preloaded" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="roamInfo" type="{http://public_5_0.interface.pricer.se/}ESLRoamInfo" minOccurs="0"/>
 *         &lt;element name="firstLinkTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currentLinkTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="eslProperties" type="{http://public_5_0.interface.pricer.se/}PropertyValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ESL", propOrder = {
    "barcode",
    "eslType",
    "subcell",
    "state",
    "batteryState",
    "itemOrder",
    "preloaded",
    "roamInfo",
    "firstLinkTime",
    "currentLinkTime",
    "lastUpdate",
    "model",
    "eslProperties"
})
public class ESL {

    protected String barcode;
    protected String eslType;
    protected String subcell;
    protected String state;
    protected String batteryState;
    protected List<ItemOrder> itemOrder;
    protected boolean preloaded;
    protected ESLRoamInfo roamInfo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar firstLinkTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar currentLinkTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    protected int model;
    protected List<PropertyValue> eslProperties;

    /**
     * Obtient la valeur de la propri�t� barcode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * D�finit la valeur de la propri�t� barcode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarcode(String value) {
        this.barcode = value;
    }

    /**
     * Obtient la valeur de la propri�t� eslType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEslType() {
        return eslType;
    }

    /**
     * D�finit la valeur de la propri�t� eslType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEslType(String value) {
        this.eslType = value;
    }

    /**
     * Obtient la valeur de la propri�t� subcell.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubcell() {
        return subcell;
    }

    /**
     * D�finit la valeur de la propri�t� subcell.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubcell(String value) {
        this.subcell = value;
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
     * Obtient la valeur de la propri�t� batteryState.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatteryState() {
        return batteryState;
    }

    /**
     * D�finit la valeur de la propri�t� batteryState.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatteryState(String value) {
        this.batteryState = value;
    }

    /**
     * Gets the value of the itemOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemOrder }
     * 
     * 
     */
    public List<ItemOrder> getItemOrder() {
        if (itemOrder == null) {
            itemOrder = new ArrayList<ItemOrder>();
        }
        return this.itemOrder;
    }

    /**
     * Obtient la valeur de la propri�t� preloaded.
     * 
     */
    public boolean isPreloaded() {
        return preloaded;
    }

    /**
     * D�finit la valeur de la propri�t� preloaded.
     * 
     */
    public void setPreloaded(boolean value) {
        this.preloaded = value;
    }

    /**
     * Obtient la valeur de la propri�t� roamInfo.
     * 
     * @return
     *     possible object is
     *     {@link ESLRoamInfo }
     *     
     */
    public ESLRoamInfo getRoamInfo() {
        return roamInfo;
    }

    /**
     * D�finit la valeur de la propri�t� roamInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link ESLRoamInfo }
     *     
     */
    public void setRoamInfo(ESLRoamInfo value) {
        this.roamInfo = value;
    }

    /**
     * Obtient la valeur de la propri�t� firstLinkTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFirstLinkTime() {
        return firstLinkTime;
    }

    /**
     * D�finit la valeur de la propri�t� firstLinkTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFirstLinkTime(XMLGregorianCalendar value) {
        this.firstLinkTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� currentLinkTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCurrentLinkTime() {
        return currentLinkTime;
    }

    /**
     * D�finit la valeur de la propri�t� currentLinkTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurrentLinkTime(XMLGregorianCalendar value) {
        this.currentLinkTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� lastUpdate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * D�finit la valeur de la propri�t� lastUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    /**
     * Obtient la valeur de la propri�t� model.
     * 
     */
    public int getModel() {
        return model;
    }

    /**
     * D�finit la valeur de la propri�t� model.
     * 
     */
    public void setModel(int value) {
        this.model = value;
    }

    /**
     * Gets the value of the eslProperties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eslProperties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEslProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropertyValue }
     * 
     * 
     */
    public List<PropertyValue> getEslProperties() {
        if (eslProperties == null) {
            eslProperties = new ArrayList<PropertyValue>();
        }
        return this.eslProperties;
    }

}
