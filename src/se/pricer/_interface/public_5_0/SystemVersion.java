
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour SystemVersion complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SystemVersion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="revision" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="versionTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="javaVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dbVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SystemVersion", propOrder = {
    "version",
    "revision",
    "versionTitle",
    "javaVersion",
    "dbVersion"
})
public class SystemVersion {

    protected String version;
    protected String revision;
    protected String versionTitle;
    protected String javaVersion;
    protected String dbVersion;

    /**
     * Obtient la valeur de la propri�t� version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * D�finit la valeur de la propri�t� version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propri�t� revision.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevision() {
        return revision;
    }

    /**
     * D�finit la valeur de la propri�t� revision.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevision(String value) {
        this.revision = value;
    }

    /**
     * Obtient la valeur de la propri�t� versionTitle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionTitle() {
        return versionTitle;
    }

    /**
     * D�finit la valeur de la propri�t� versionTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionTitle(String value) {
        this.versionTitle = value;
    }

    /**
     * Obtient la valeur de la propri�t� javaVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJavaVersion() {
        return javaVersion;
    }

    /**
     * D�finit la valeur de la propri�t� javaVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJavaVersion(String value) {
        this.javaVersion = value;
    }

    /**
     * Obtient la valeur de la propri�t� dbVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbVersion() {
        return dbVersion;
    }

    /**
     * D�finit la valeur de la propri�t� dbVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbVersion(String value) {
        this.dbVersion = value;
    }

}
