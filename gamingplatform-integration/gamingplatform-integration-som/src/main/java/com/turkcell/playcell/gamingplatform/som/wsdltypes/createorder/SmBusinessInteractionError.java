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


/**
 * <p>Java class for sm-business-interaction-error complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-business-interaction-error"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="notificationId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="systemWillRetry" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="userText" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="errorId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="errorDetail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-business-interaction-error", propOrder = {
    "notificationId",
    "systemWillRetry",
    "type",
    "userText",
    "errorId",
    "errorDetail"
})
public class SmBusinessInteractionError {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger notificationId;
    protected boolean systemWillRetry;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String userText;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger errorId;
    @XmlElement(required = true)
    protected String errorDetail;

    /**
     * Gets the value of the notificationId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNotificationId() {
        return notificationId;
    }

    /**
     * Sets the value of the notificationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNotificationId(BigInteger value) {
        this.notificationId = value;
    }

    /**
     * Gets the value of the systemWillRetry property.
     * 
     */
    public boolean isSystemWillRetry() {
        return systemWillRetry;
    }

    /**
     * Sets the value of the systemWillRetry property.
     * 
     */
    public void setSystemWillRetry(boolean value) {
        this.systemWillRetry = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the userText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserText() {
        return userText;
    }

    /**
     * Sets the value of the userText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserText(String value) {
        this.userText = value;
    }

    /**
     * Gets the value of the errorId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getErrorId() {
        return errorId;
    }

    /**
     * Sets the value of the errorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setErrorId(BigInteger value) {
        this.errorId = value;
    }

    /**
     * Gets the value of the errorDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorDetail() {
        return errorDetail;
    }

    /**
     * Sets the value of the errorDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorDetail(String value) {
        this.errorDetail = value;
    }

}
