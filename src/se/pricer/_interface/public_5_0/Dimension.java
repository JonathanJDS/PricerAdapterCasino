
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Dimension complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Dimension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="width" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dimension", propOrder = {
    "width",
    "height"
})
public class Dimension {

    protected int width;
    protected int height;

    /**
     * Obtient la valeur de la propriété width.
     * 
     */
    public int getWidth() {
        return width;
    }

    /**
     * Définit la valeur de la propriété width.
     * 
     */
    public void setWidth(int value) {
        this.width = value;
    }

    /**
     * Obtient la valeur de la propriété height.
     * 
     */
    public int getHeight() {
        return height;
    }

    /**
     * Définit la valeur de la propriété height.
     * 
     */
    public void setHeight(int value) {
        this.height = value;
    }

}
