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
 * <p>Java class for sm-dealer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sm-dealer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dealerCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="subDealerCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sm-dealer", propOrder = {
    "dealerCode",
    "subDealerCode"
})
public class SmDealer {

    @XmlElement(required = true)
    protected String dealerCode;
    @XmlElement(required = true)
    protected String subDealerCode;

    /**
     * Gets the value of the dealerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealerCode() {
        return dealerCode;
    }

    /**
     * Sets the value of the dealerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealerCode(String value) {
        this.dealerCode = value;
    }

    /**
     * Gets the value of the subDealerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubDealerCode() {
        return subDealerCode;
    }

    /**
     * Sets the value of the subDealerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubDealerCode(String value) {
        this.subDealerCode = value;
    }

}
