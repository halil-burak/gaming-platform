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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sm-life-cycle-campaign-profile-item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-life-cycle-campaign-profile-item"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="campaignId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="campaignName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="campaignStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="campaignEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="statusId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="campaignIntervalId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="inactivationReason" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="commitmentDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nextChargingDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="defaultCampaign" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="subscriptionBcCount" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="chargingBcCount" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="chargingIntervalStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="subscriptionPaymentType" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-life-cycle-campaign-profile-item", propOrder = {
    "campaignId",
    "campaignName",
    "campaignStartDate",
    "campaignEndDate",
    "statusId",
    "campaignIntervalId",
    "inactivationReason",
    "commitmentDate",
    "nextChargingDate",
    "defaultCampaign",
    "subscriptionBcCount",
    "chargingBcCount",
    "chargingIntervalStartDate",
    "subscriptionPaymentType"
})
public class SmLifeCycleCampaignProfileItem {

    @XmlElement(required = true)
    protected String campaignId;
    @XmlElement(required = true)
    protected String campaignName;
    @XmlElement(required = true)
    protected String campaignStartDate;
    @XmlElement(required = true)
    protected String campaignEndDate;
    protected int statusId;
    @XmlElement(required = true)
    protected String campaignIntervalId;
    protected int inactivationReason;
    @XmlElement(required = true)
    protected String commitmentDate;
    @XmlElement(required = true)
    protected String nextChargingDate;
    protected int defaultCampaign;
    @XmlElement(required = true)
    protected String subscriptionBcCount;
    @XmlElement(required = true)
    protected String chargingBcCount;
    @XmlElement(required = true)
    protected String chargingIntervalStartDate;
    @XmlElement(required = true)
    protected BigInteger subscriptionPaymentType;

    /**
     * Gets the value of the campaignId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaignId() {
        return campaignId;
    }

    /**
     * Sets the value of the campaignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaignId(String value) {
        this.campaignId = value;
    }

    /**
     * Gets the value of the campaignName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaignName() {
        return campaignName;
    }

    /**
     * Sets the value of the campaignName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaignName(String value) {
        this.campaignName = value;
    }

    /**
     * Gets the value of the campaignStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaignStartDate() {
        return campaignStartDate;
    }

    /**
     * Sets the value of the campaignStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaignStartDate(String value) {
        this.campaignStartDate = value;
    }

    /**
     * Gets the value of the campaignEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaignEndDate() {
        return campaignEndDate;
    }

    /**
     * Sets the value of the campaignEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaignEndDate(String value) {
        this.campaignEndDate = value;
    }

    /**
     * Gets the value of the statusId property.
     * 
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * Sets the value of the statusId property.
     * 
     */
    public void setStatusId(int value) {
        this.statusId = value;
    }

    /**
     * Gets the value of the campaignIntervalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaignIntervalId() {
        return campaignIntervalId;
    }

    /**
     * Sets the value of the campaignIntervalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaignIntervalId(String value) {
        this.campaignIntervalId = value;
    }

    /**
     * Gets the value of the inactivationReason property.
     * 
     */
    public int getInactivationReason() {
        return inactivationReason;
    }

    /**
     * Sets the value of the inactivationReason property.
     * 
     */
    public void setInactivationReason(int value) {
        this.inactivationReason = value;
    }

    /**
     * Gets the value of the commitmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitmentDate() {
        return commitmentDate;
    }

    /**
     * Sets the value of the commitmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitmentDate(String value) {
        this.commitmentDate = value;
    }

    /**
     * Gets the value of the nextChargingDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextChargingDate() {
        return nextChargingDate;
    }

    /**
     * Sets the value of the nextChargingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextChargingDate(String value) {
        this.nextChargingDate = value;
    }

    /**
     * Gets the value of the defaultCampaign property.
     * 
     */
    public int getDefaultCampaign() {
        return defaultCampaign;
    }

    /**
     * Sets the value of the defaultCampaign property.
     * 
     */
    public void setDefaultCampaign(int value) {
        this.defaultCampaign = value;
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

    /**
     * Gets the value of the chargingIntervalStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargingIntervalStartDate() {
        return chargingIntervalStartDate;
    }

    /**
     * Sets the value of the chargingIntervalStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargingIntervalStartDate(String value) {
        this.chargingIntervalStartDate = value;
    }

    /**
     * Gets the value of the subscriptionPaymentType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSubscriptionPaymentType() {
        return subscriptionPaymentType;
    }

    /**
     * Sets the value of the subscriptionPaymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSubscriptionPaymentType(BigInteger value) {
        this.subscriptionPaymentType = value;
    }

}