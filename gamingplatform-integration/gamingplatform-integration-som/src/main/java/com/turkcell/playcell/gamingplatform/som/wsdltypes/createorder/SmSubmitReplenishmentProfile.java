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
 * <p>Java class for sm-submit-replenishment-profile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-submit-replenishment-profile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="replenishmentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="extOfferId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="extCampaignId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="subscriptionBcCount" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="chargingBcCount" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-submit-replenishment-profile", propOrder = {
    "replenishmentStatus",
    "extOfferId",
    "extCampaignId",
    "startDate",
    "endDate",
    "subscriptionBcCount",
    "chargingBcCount"
})
public class SmSubmitReplenishmentProfile {

    @XmlElement(required = true)
    protected String replenishmentStatus;
    @XmlElement(required = true)
    protected String extOfferId;
    @XmlElement(required = true)
    protected String extCampaignId;
    @XmlElement(required = true)
    protected String startDate;
    @XmlElement(required = true)
    protected String endDate;
    @XmlElement(required = true)
    protected String subscriptionBcCount;
    @XmlElement(required = true)
    protected String chargingBcCount;

    /**
     * Gets the value of the replenishmentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReplenishmentStatus() {
        return replenishmentStatus;
    }

    /**
     * Sets the value of the replenishmentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReplenishmentStatus(String value) {
        this.replenishmentStatus = value;
    }

    /**
     * Gets the value of the extOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtOfferId() {
        return extOfferId;
    }

    /**
     * Sets the value of the extOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtOfferId(String value) {
        this.extOfferId = value;
    }

    /**
     * Gets the value of the extCampaignId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtCampaignId() {
        return extCampaignId;
    }

    /**
     * Sets the value of the extCampaignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtCampaignId(String value) {
        this.extCampaignId = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the subscriptionBcCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionBcCount() {
        return subscriptionBcCount;
    }

    /**
     * Sets the value of the subscriptionBcCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionBcCount(String value) {
        this.subscriptionBcCount = value;
    }

    /**
     * Gets the value of the chargingBcCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargingBcCount() {
        return chargingBcCount;
    }

    /**
     * Sets the value of the chargingBcCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargingBcCount(String value) {
        this.chargingBcCount = value;
    }

}
