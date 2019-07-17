//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.10 at 11:48:52 AM EET 
//


package com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sm-business-interaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-business-interaction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="error" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-business-interaction-error"/&gt;
 *           &lt;element name="approval" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-business-interaction-approval" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="information" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-business-interaction-info" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-business-interaction", propOrder = {
    "error",
    "approval",
    "information"
})
public class SmBusinessInteraction {

    protected SmBusinessInteractionError error;
    protected List<SmBusinessInteractionApproval> approval;
    protected List<SmBusinessInteractionInfo> information;

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link SmBusinessInteractionError }
     *     
     */
    public SmBusinessInteractionError getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmBusinessInteractionError }
     *     
     */
    public void setError(SmBusinessInteractionError value) {
        this.error = value;
    }

    /**
     * Gets the value of the approval property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the approval property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApproval().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmBusinessInteractionApproval }
     * 
     * 
     */
    public List<SmBusinessInteractionApproval> getApproval() {
        if (approval == null) {
            approval = new ArrayList<SmBusinessInteractionApproval>();
        }
        return this.approval;
    }

    /**
     * Gets the value of the information property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the information property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmBusinessInteractionInfo }
     * 
     * 
     */
    public List<SmBusinessInteractionInfo> getInformation() {
        if (information == null) {
            information = new ArrayList<SmBusinessInteractionInfo>();
        }
        return this.information;
    }

}
