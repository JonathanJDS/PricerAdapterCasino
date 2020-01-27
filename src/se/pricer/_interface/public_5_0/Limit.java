
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Limit complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Limit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firstElement" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="numberOfElements" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Limit", propOrder = {
    "firstElement",
    "numberOfElements"
})
public class Limit {

    protected int firstElement;
    protected int numberOfElements;

    /**
     * Obtient la valeur de la propri�t� firstElement.
     * 
     */
    public int getFirstElement() {
        return firstElement;
    }

    /**
     * D�finit la valeur de la propri�t� firstElement.
     * 
     */
    public void setFirstElement(int value) {
        this.firstElement = value;
    }

    /**
     * Obtient la valeur de la propri�t� numberOfElements.
     * 
     */
    public int getNumberOfElements() {
        return numberOfElements;
    }

    /**
     * D�finit la valeur de la propri�t� numberOfElements.
     * 
     */
    public void setNumberOfElements(int value) {
        this.numberOfElements = value;
    }

}
