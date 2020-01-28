
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour BasicServerStatus complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété totalNumBasestations.
     * 
     */
    public int getTotalNumBasestations() {
        return totalNumBasestations;
    }

    /**
     * Définit la valeur de la propriété totalNumBasestations.
     * 
     */
    public void setTotalNumBasestations(int value) {
        this.totalNumBasestations = value;
    }

    /**
     * Obtient la valeur de la propriété totalNumErrorBasestations.
     * 
     */
    public int getTotalNumErrorBasestations() {
        return totalNumErrorBasestations;
    }

    /**
     * Définit la valeur de la propriété totalNumErrorBasestations.
     * 
     */
    public void setTotalNumErrorBasestations(int value) {
        this.totalNumErrorBasestations = value;
    }

    /**
     * Obtient la valeur de la propriété totalNumTranceivers.
     * 
     */
    public int getTotalNumTranceivers() {
        return totalNumTranceivers;
    }

    /**
     * Définit la valeur de la propriété totalNumTranceivers.
     * 
     */
    public void setTotalNumTranceivers(int value) {
        this.totalNumTranceivers = value;
    }

    /**
     * Obtient la valeur de la propriété totalNumErrorTranceivers.
     * 
     */
    public int getTotalNumErrorTranceivers() {
        return totalNumErrorTranceivers;
    }

    /**
     * Définit la valeur de la propriété totalNumErrorTranceivers.
     * 
     */
    public void setTotalNumErrorTranceivers(int value) {
        this.totalNumErrorTranceivers = value;
    }

    /**
     * Obtient la valeur de la propriété totalAllocatedNumTranceivers.
     * 
     */
    public int getTotalAllocatedNumTranceivers() {
        return totalAllocatedNumTranceivers;
    }

    /**
     * Définit la valeur de la propriété totalAllocatedNumTranceivers.
     * 
     */
    public void setTotalAllocatedNumTranceivers(int value) {
        this.totalAllocatedNumTranceivers = value;
    }

    /**
     * Obtient la valeur de la propriété totalNumESLs.
     * 
     */
    public int getTotalNumESLs() {
        return totalNumESLs;
    }

    /**
     * Définit la valeur de la propriété totalNumESLs.
     * 
     */
    public void setTotalNumESLs(int value) {
        this.totalNumESLs = value;
    }

    /**
     * Obtient la valeur de la propriété totalNumESLsInWaitingForUpdate.
     * 
     */
    public int getTotalNumESLsInWaitingForUpdate() {
        return totalNumESLsInWaitingForUpdate;
    }

    /**
     * Définit la valeur de la propriété totalNumESLsInWaitingForUpdate.
     * 
     */
    public void setTotalNumESLsInWaitingForUpdate(int value) {
        this.totalNumESLsInWaitingForUpdate = value;
    }

    /**
     * Obtient la valeur de la propriété totalNumESLsInRoaming.
     * 
     */
    public int getTotalNumESLsInRoaming() {
        return totalNumESLsInRoaming;
    }

    /**
     * Définit la valeur de la propriété totalNumESLsInRoaming.
     * 
     */
    public void setTotalNumESLsInRoaming(int value) {
        this.totalNumESLsInRoaming = value;
    }

    /**
     * Obtient la valeur de la propriété totalNumESLsWithLowBattery.
     * 
     */
    public int getTotalNumESLsWithLowBattery() {
        return totalNumESLsWithLowBattery;
    }

    /**
     * Définit la valeur de la propriété totalNumESLsWithLowBattery.
     * 
     */
    public void setTotalNumESLsWithLowBattery(int value) {
        this.totalNumESLsWithLowBattery = value;
    }

}
