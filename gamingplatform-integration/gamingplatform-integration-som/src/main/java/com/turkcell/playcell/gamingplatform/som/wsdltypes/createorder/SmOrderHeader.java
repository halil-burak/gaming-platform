//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.10 at 11:48:52 AM EET 
//


package com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for sm-order-header complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-order-header"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="channelApplication" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-channel-application"/&gt;
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="requestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="user" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-user"/&gt;
 *         &lt;element name="salesAgent" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-sales-agent"/&gt;
 *         &lt;element name="touchId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-order-header", propOrder = {
    "channelApplication",
    "transactionId",
    "requestDate",
    "sessionId",
    "user",
    "salesAgent",
    "touchId"
})
public class SmOrderHeader {

    @XmlElement(required = true)
    protected SmChannelApplication channelApplication;
    @XmlElement(required = true)
    protected String transactionId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected String requestDate;
    @XmlElement(required = true)
    protected String sessionId;
    @XmlElement(required = true)
    protected SmUser user;
    @XmlElement(required = true)
    protected SmSalesAgent salesAgent;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger touchId;

    /**
     * Gets the value of the channelApplication property.
     * 
     * @return
     *     possible object is
     *     {@link SmChannelApplication }
     *     
     */
    public SmChannelApplication getChannelApplication() {
        return channelApplication;
    }

    /**
     * Sets the value of the channelApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmChannelApplication }
     *     
     */
    public void setChannelApplication(SmChannelApplication value) {
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
     * Gets the value of the requestDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * Sets the value of the requestDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDate(String value) {
        this.requestDate = value;
    }

    /**
     * Gets the value of the sessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionId(String value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link SmUser }
     *     
     */
    public SmUser getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmUser }
     *     
     */
    public void setUser(SmUser value) {
        this.user = value;
    }

    /**
     * Gets the value of the salesAgent property.
     * 
     * @return
     *     possible object is
     *     {@link SmSalesAgent }
     *     
     */
    public SmSalesAgent getSalesAgent() {
        return salesAgent;
    }

    /**
     * Sets the value of the salesAgent property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmSalesAgent }
     *     
     */
    public void setSalesAgent(SmSalesAgent value) {
        this.salesAgent = value;
    }

    /**
     * Gets the value of the touchId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTouchId() {
        return touchId;
    }

    /**
     * Sets the value of the touchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTouchId(BigInteger value) {
        this.touchId = value;
    }

}
