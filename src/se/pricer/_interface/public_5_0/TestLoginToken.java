
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour testLoginToken complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="testLoginToken">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authenticationToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testLoginToken", propOrder = {
    "user",
    "authenticationToken"
})
public class TestLoginToken {

    protected String user;
    protected String authenticationToken;

    /**
     * Obtient la valeur de la propri�t� user.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * D�finit la valeur de la propri�t� user.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Obtient la valeur de la propri�t� authenticationToken.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     * D�finit la valeur de la propri�t� authenticationToken.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationToken(String value) {
        this.authenticationToken = value;
    }

}
