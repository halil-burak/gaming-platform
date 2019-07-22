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
 * <p>Java class for sm-customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-customer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="payerMsisdn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="crmCustomerId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="individual" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-individual-customer"/&gt;
 *           &lt;element name="corporate" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-corporate-customer"/&gt;
 *           &lt;element name="individualCorporate" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-individual-corporate-customer"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-customer", propOrder = {
    "payerMsisdn",
    "crmCustomerId",
    "individual",
    "corporate",
    "individualCorporate"
})
public class SmCustomer {

    @XmlElement(required = true)
    protected String payerMsisdn;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger crmCustomerId;
    protected SmIndividualCustomer individual;
    protected SmCorporateCustomer corporate;
    protected SmIndividualCorporateCustomer individualCorporate;

    /**
     * Gets the value of the payerMsisdn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayerMsisdn() {
        return payerMsisdn;
    }

    /**
     * Sets the value of the payerMsisdn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayerMsisdn(String value) {
        this.payerMsisdn = value;
    }

    /**
     * Gets the value of the crmCustomerId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCrmCustomerId() {
        return crmCustomerId;
    }

    /**
     * Sets the value of the crmCustomerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCrmCustomerId(BigInteger value) {
        this.crmCustomerId = value;
    }

    /**
     * Gets the value of the individual property.
     * 
     * @return
     *     possible object is
     *     {@link SmIndividualCustomer }
     *     
     */
    public SmIndividualCustomer getIndividual() {
        return individual;
    }

    /**
     * Sets the value of the individual property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmIndividualCustomer }
     *     
     */
    public void setIndividual(SmIndividualCustomer value) {
        this.individual = value;
    }

    /**
     * Gets the value of the corporate property.
     * 
     * @return
     *     possible object is
     *     {@link SmCorporateCustomer }
     *     
     */
    public SmCorporateCustomer getCorporate() {
        return corporate;
    }

    /**
     * Sets the value of the corporate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmCorporateCustomer }
     *     
     */
    public void setCorporate(SmCorporateCustomer value) {
        this.corporate = value;
    }

    /**
     * Gets the value of the individualCorporate property.
     * 
     * @return
     *     possible object is
     *     {@link SmIndividualCorporateCustomer }
     *     
     */
    public SmIndividualCorporateCustomer getIndividualCorporate() {
        return individualCorporate;
    }

    /**
     * Sets the value of the individualCorporate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmIndividualCorporateCustomer }
     *     
     */
    public void setIndividualCorporate(SmIndividualCorporateCustomer value) {
        this.individualCorporate = value;
    }

}
