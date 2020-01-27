
package se.pricer._interface.public_5_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour postImage complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="postImage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="resize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="updateTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postImage", propOrder = {
    "itemId",
    "page",
    "image",
    "resize",
    "updateTime"
})
public class PostImage {

    protected String itemId;
    protected byte page;
    protected byte[] image;
    protected int resize;
    protected Long updateTime;

    /**
     * Obtient la valeur de la propri�t� itemId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * D�finit la valeur de la propri�t� itemId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemId(String value) {
        this.itemId = value;
    }

    /**
     * Obtient la valeur de la propri�t� page.
     * 
     */
    public byte getPage() {
        return page;
    }

    /**
     * D�finit la valeur de la propri�t� page.
     * 
     */
    public void setPage(byte value) {
        this.page = value;
    }

    /**
     * Obtient la valeur de la propri�t� image.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * D�finit la valeur de la propri�t� image.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImage(byte[] value) {
        this.image = value;
    }

    /**
     * Obtient la valeur de la propri�t� resize.
     * 
     */
    public int getResize() {
        return resize;
    }

    /**
     * D�finit la valeur de la propri�t� resize.
     * 
     */
    public void setResize(int value) {
        this.resize = value;
    }

    /**
     * Obtient la valeur de la propri�t� updateTime.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * D�finit la valeur de la propri�t� updateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUpdateTime(Long value) {
        this.updateTime = value;
    }

}
