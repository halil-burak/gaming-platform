//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.10 at 11:48:52 AM EET 
//


package com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sm-approval-order-header complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-approval-order-header"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="channelApplication" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-approval-channel-application"/&gt;
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="user" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-approval-user"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-approval-order-header", propOrder = {
    "channelApplication",
    "transactionId",
    "user"
})
public class SmApprovalOrderHeader {

    @XmlElement(required = true)
    protected SmApprovalChannelApplication channelApplication;
    @XmlElement(required = true)
    protected String transactionId;
    @XmlElement(required = true)
    protected SmApprovalUser user;

    /**
     * Gets the value of the channelApplication property.
     * 
     * @return
     *     possible object is
     *     {@link SmApprovalChannelApplication }
     *     
     */
    public SmApprovalChannelApplication getChannelApplication() {
        return channelApplication;
    }

    /**
     * Sets the value of the channelApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmApprovalChannelApplication }
     *     
     */
    public void setChannelApplication(SmApprovalChannelApplication value) {
        this.channelApplication = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link SmApprovalUser }
     *     
     */
    public SmApprovalUser getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmApprovalUser }
     *     
     */
    public void setUser(SmApprovalUser value) {
        this.user = value;
    }

}