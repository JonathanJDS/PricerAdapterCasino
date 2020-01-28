
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour BasicServerStatus complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BasicServerStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="totalNumBasestations" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalNumErrorBasestations" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalNumTranceivers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalNumErrorTranceivers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalAllocatedNumTranceivers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalNumESLs" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalNumESLsInWaitingForUpdate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalNumESLsInRoaming" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalNumESLsWithLowBattery" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicServerStatus", propOrder = {
    "totalNumBasestations",
    "totalNumErrorBasestations",
    "totalNumTranceivers",
    "totalNumErrorTranceivers",
    "totalAllocatedNumTranceivers",
    "totalNumESLs",
    "totalNumESLsInWaitingForUpdate",
    "totalNumESLsInRoaming",
    "totalNumESLsWithLowBattery"
})
public class BasicServerStatus {

    protected int totalNumBasestations;
    protected int totalNumErrorBasestations;
    protected int totalNumTranceivers;
    protected int totalNumErrorTranceivers;
    protected int totalAllocatedNumTranceivers;
    protected int totalNumESLs;
    protected int totalNumESLsInWaitingForUpdate;
    protected int totalNumESLsInRoaming;
    protected int totalNumESLsWithLowBattery;

    /**
     * Obtient la valeur de la propri�t� totalNumBasestations.
     * 
     */
    public int getTotalNumBasestations() {
        return totalNumBasestations;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumBasestations.
     * 
     */
    public void setTotalNumBasestations(int value) {
        this.totalNumBasestations = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalNumErrorBasestations.
     * 
     */
    public int getTotalNumErrorBasestations() {
        return totalNumErrorBasestations;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumErrorBasestations.
     * 
     */
    public void setTotalNumErrorBasestations(int value) {
        this.totalNumErrorBasestations = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalNumTranceivers.
     * 
     */
    public int getTotalNumTranceivers() {
        return totalNumTranceivers;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumTranceivers.
     * 
     */
    public void setTotalNumTranceivers(int value) {
        this.totalNumTranceivers = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalNumErrorTranceivers.
     * 
     */
    public int getTotalNumErrorTranceivers() {
        return totalNumErrorTranceivers;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumErrorTranceivers.
     * 
     */
    public void setTotalNumErrorTranceivers(int value) {
        this.totalNumErrorTranceivers = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalAllocatedNumTranceivers.
     * 
     */
    public int getTotalAllocatedNumTranceivers() {
        return totalAllocatedNumTranceivers;
    }

    /**
     * D�finit la valeur de la propri�t� totalAllocatedNumTranceivers.
     * 
     */
    public void setTotalAllocatedNumTranceivers(int value) {
        this.totalAllocatedNumTranceivers = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalNumESLs.
     * 
     */
    public int getTotalNumESLs() {
        return totalNumESLs;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumESLs.
     * 
     */
    public void setTotalNumESLs(int value) {
        this.totalNumESLs = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalNumESLsInWaitingForUpdate.
     * 
     */
    public int getTotalNumESLsInWaitingForUpdate() {
        return totalNumESLsInWaitingForUpdate;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumESLsInWaitingForUpdate.
     * 
     */
    public void setTotalNumESLsInWaitingForUpdate(int value) {
        this.totalNumESLsInWaitingForUpdate = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalNumESLsInRoaming.
     * 
     */
    public int getTotalNumESLsInRoaming() {
        return totalNumESLsInRoaming;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumESLsInRoaming.
     * 
     */
    public void setTotalNumESLsInRoaming(int value) {
        this.totalNumESLsInRoaming = value;
    }

    /**
     * Obtient la valeur de la propri�t� totalNumESLsWithLowBattery.
     * 
     */
    public int getTotalNumESLsWithLowBattery() {
        return totalNumESLsWithLowBattery;
    }

    /**
     * D�finit la valeur de la propri�t� totalNumESLsWithLowBattery.
     * 
     */
    public void setTotalNumESLsWithLowBattery(int value) {
        this.totalNumESLsWithLowBattery = value;
    }

}
