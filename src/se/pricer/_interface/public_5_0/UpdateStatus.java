
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour UpdateStatus complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="UpdateStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageFilename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startProcessingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endProcessingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="noOfItemUpdates" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noOfESLsAffected" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noOfOKESLs" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="eslsInWaitingForUpdate" type="{http://public_5_0.interface.pricer.se/}ItemESLLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eslsInRoaming" type="{http://public_5_0.interface.pricer.se/}ItemESLLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eslsInFailState" type="{http://public_5_0.interface.pricer.se/}ItemESLLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="otherPFIFileUnderProcessing" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="eslsInSyntaxFailState" type="{http://public_5_0.interface.pricer.se/}ItemESLLink" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isUnknownSyntaxError" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateStatus", propOrder = {
    "messageFilename",
    "startProcessingDate",
    "endProcessingDate",
    "noOfItemUpdates",
    "noOfESLsAffected",
    "noOfOKESLs",
    "eslsInWaitingForUpdate",
    "eslsInRoaming",
    "eslsInFailState",
    "otherPFIFileUnderProcessing",
    "eslsInSyntaxFailState",
    "isUnknownSyntaxError"
})
public class UpdateStatus {

    protected String messageFilename;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startProcessingDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endProcessingDate;
    protected int noOfItemUpdates;
    protected int noOfESLsAffected;
    protected int noOfOKESLs;
    protected List<ItemESLLink> eslsInWaitingForUpdate;
    protected List<ItemESLLink> eslsInRoaming;
    protected List<ItemESLLink> eslsInFailState;
    protected boolean otherPFIFileUnderProcessing;
    protected List<ItemESLLink> eslsInSyntaxFailState;
    protected boolean isUnknownSyntaxError;

    /**
     * Obtient la valeur de la propriété messageFilename.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageFilename() {
        return messageFilename;
    }

    /**
     * Définit la valeur de la propriété messageFilename.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageFilename(String value) {
        this.messageFilename = value;
    }

    /**
     * Obtient la valeur de la propriété startProcessingDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartProcessingDate() {
        return startProcessingDate;
    }

    /**
     * Définit la valeur de la propriété startProcessingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartProcessingDate(XMLGregorianCalendar value) {
        this.startProcessingDate = value;
    }

    /**
     * Obtient la valeur de la propriété endProcessingDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndProcessingDate() {
        return endProcessingDate;
    }

    /**
     * Définit la valeur de la propriété endProcessingDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndProcessingDate(XMLGregorianCalendar value) {
        this.endProcessingDate = value;
    }

    /**
     * Obtient la valeur de la propriété noOfItemUpdates.
     * 
     */
    public int getNoOfItemUpdates() {
        return noOfItemUpdates;
    }

    /**
     * Définit la valeur de la propriété noOfItemUpdates.
     * 
     */
    public void setNoOfItemUpdates(int value) {
        this.noOfItemUpdates = value;
    }

    /**
     * Obtient la valeur de la propriété noOfESLsAffected.
     * 
     */
    public int getNoOfESLsAffected() {
        return noOfESLsAffected;
    }

    /**
     * Définit la valeur de la propriété noOfESLsAffected.
     * 
     */
    public void setNoOfESLsAffected(int value) {
        this.noOfESLsAffected = value;
    }

    /**
     * Obtient la valeur de la propriété noOfOKESLs.
     * 
     */
    public int getNoOfOKESLs() {
        return noOfOKESLs;
    }

    /**
     * Définit la valeur de la propriété noOfOKESLs.
     * 
     */
    public void setNoOfOKESLs(int value) {
        this.noOfOKESLs = value;
    }

    /**
     * Gets the value of the eslsInWaitingForUpdate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eslsInWaitingForUpdate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEslsInWaitingForUpdate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemESLLink }
     * 
     * 
     */
    public List<ItemESLLink> getEslsInWaitingForUpdate() {
        if (eslsInWaitingForUpdate == null) {
            eslsInWaitingForUpdate = new ArrayList<ItemESLLink>();
        }
        return this.eslsInWaitingForUpdate;
    }

    /**
     * Gets the value of the eslsInRoaming property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eslsInRoaming property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEslsInRoaming().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemESLLink }
     * 
     * 
     */
    public List<ItemESLLink> getEslsInRoaming() {
        if (eslsInRoaming == null) {
            eslsInRoaming = new ArrayList<ItemESLLink>();
        }
        return this.eslsInRoaming;
    }

    /**
     * Gets the value of the eslsInFailState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eslsInFailState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEslsInFailState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemESLLink }
     * 
     * 
     */
    public List<ItemESLLink> getEslsInFailState() {
        if (eslsInFailState == null) {
            eslsInFailState = new ArrayList<ItemESLLink>();
        }
        return this.eslsInFailState;
    }

    /**
     * Obtient la valeur de la propriété otherPFIFileUnderProcessing.
     * 
     */
    public boolean isOtherPFIFileUnderProcessing() {
        return otherPFIFileUnderProcessing;
    }

    /**
     * Définit la valeur de la propriété otherPFIFileUnderProcessing.
     * 
     */
    public void setOtherPFIFileUnderProcessing(boolean value) {
        this.otherPFIFileUnderProcessing = value;
    }

    /**
     * Gets the value of the eslsInSyntaxFailState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eslsInSyntaxFailState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEslsInSyntaxFailState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemESLLink }
     * 
     * 
     */
    public List<ItemESLLink> getEslsInSyntaxFailState() {
        if (eslsInSyntaxFailState == null) {
            eslsInSyntaxFailState = new ArrayList<ItemESLLink>();
        }
        return this.eslsInSyntaxFailState;
    }

    /**
     * Obtient la valeur de la propriété isUnknownSyntaxError.
     * 
     */
    public boolean isIsUnknownSyntaxError() {
        return isUnknownSyntaxError;
    }

    /**
     * Définit la valeur de la propriété isUnknownSyntaxError.
     * 
     */
    public void setIsUnknownSyntaxError(boolean value) {
        this.isUnknownSyntaxError = value;
    }

}
