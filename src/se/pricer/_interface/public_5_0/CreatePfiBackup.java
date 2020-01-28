
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour createPfiBackup complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="createPfiBackup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="includeItems" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="includeLinks" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createPfiBackup", propOrder = {
    "includeItems",
    "includeLinks"
})
public class CreatePfiBackup {

    protected boolean includeItems;
    protected boolean includeLinks;

    /**
     * Obtient la valeur de la propri�t� includeItems.
     * 
     */
    public boolean isIncludeItems() {
        return includeItems;
    }

    /**
     * D�finit la valeur de la propri�t� includeItems.
     * 
     */
    public void setIncludeItems(boolean value) {
        this.includeItems = value;
    }

    /**
     * Obtient la valeur de la propri�t� includeLinks.
     * 
     */
    public boolean isIncludeLinks() {
        return includeLinks;
    }

    /**
     * D�finit la valeur de la propri�t� includeLinks.
     * 
     */
    public void setIncludeLinks(boolean value) {
        this.includeLinks = value;
    }

}
