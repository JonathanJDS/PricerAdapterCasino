
package se.pricer._interface.public_5_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

//import se.pricer.interfaces.public_5_0.PrintRequest_5_0;
import se.pricer._interface.public_5_0.PrintRequest;

/**
 * <p>Classe Java pour PrintBatch complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PrintBatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="batchId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="batchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requests" type="{http://public_5_0.interface.pricer.se/}PrintRequest" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrintBatch", propOrder = {
    "batchId",
    "batchName",
    "userID",
    "requests"
})
public class PrintBatch {

    protected Integer batchId;
    protected String batchName;
    protected String userID;
    protected List<PrintRequest> requests;

    /**
     * Obtient la valeur de la propriété batchId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBatchId() {
        return batchId;
    }

    /**
     * Définit la valeur de la propriété batchId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBatchId(Integer value) {
        this.batchId = value;
    }

    /**
     * Obtient la valeur de la propriété batchName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchName() {
        return batchName;
    }

    /**
     * Définit la valeur de la propriété batchName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchName(String value) {
        this.batchName = value;
    }

    /**
     * Obtient la valeur de la propriété userID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Définit la valeur de la propriété userID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the requests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrintRequest }
     * 
     * 
     */
    public List<PrintRequest> getRequests() {
        if (requests == null) {
            requests = new ArrayList<PrintRequest>();
        }
        return this.requests;
    }

	public void setRequests(List<PrintRequest> lstRemotePrint) {
		// TODO Auto-generated method stub
		this.requests = lstRemotePrint;
	}

}
