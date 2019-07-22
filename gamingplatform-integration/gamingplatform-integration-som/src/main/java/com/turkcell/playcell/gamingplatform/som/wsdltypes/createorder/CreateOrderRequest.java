//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.10 at 11:48:52 AM EET 
//


package com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="header" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-order-header"/&gt;
 *         &lt;element name="synchronous" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="callBackSystemID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="resellerId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="externalDealerCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="triggeringOrderId" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="operatorCompanyId" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="documentControlType" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="customer" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-customer"/&gt;
 *         &lt;element name="orderLine" type="{http://extranet.turkcell.com/ordermanagement/processes/serviceordermanagement/ServiceOrderManagementTypes}sm-order-line" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "header",
    "synchronous",
    "callBackSystemID",
    "resellerId",
    "externalDealerCode",
    "triggeringOrderId",
    "operatorCompanyId",
    "dueDate",
    "documentControlType",
    "customer",
    "orderLine"
})
@XmlRootElement(name = "CreateOrderRequest")
public class CreateOrderRequest {

    @XmlElement(required = true)
    protected SmOrderHeader header;
    @XmlElement(defaultValue = "true")
    protected boolean synchronous;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger callBackSystemID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger resellerId;
    @XmlElement(required = true)
    protected String externalDealerCode;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger triggeringOrderId;
    @XmlElement(required = true)
    protected BigInteger operatorCompanyId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger documentControlType;
    @XmlElement(required = true)
    protected SmCustomer customer;
    @XmlElement(required = true)
    protected List<SmOrderLine> orderLine;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link SmOrderHeader }
     *     
     */
    public SmOrderHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmOrderHeader }
     *     
     */
    public void setHeader(SmOrderHeader value) {
        this.header = value;
    }

    /**
     * Gets the value of the synchronous property.
     * 
     */
    public boolean isSynchronous() {
        return synchronous;
    }

    /**
     * Sets the value of the synchronous property.
     * 
     */
    public void setSynchronous(boolean value) {
        this.synchronous = value;
    }

    /**
     * Gets the value of the callBackSystemID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCallBackSystemID() {
        return callBackSystemID;
    }

    /**
     * Sets the value of the callBackSystemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCallBackSystemID(BigInteger value) {
        this.callBackSystemID = value;
    }

    /**
     * Gets the value of the resellerId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getResellerId() {
        return resellerId;
    }

    /**
     * Sets the value of the resellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setResellerId(BigInteger value) {
        this.resellerId = value;
    }

    /**
     * Gets the value of the externalDealerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDealerCode() {
        return externalDealerCode;
    }

    /**
     * Sets the value of the externalDealerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDealerCode(String value) {
        this.externalDealerCode = value;
    }

    /**
     * Gets the value of the triggeringOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTriggeringOrderId() {
        return triggeringOrderId;
    }

    /**
     * Sets the value of the triggeringOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTriggeringOrderId(BigInteger value) {
        this.triggeringOrderId = value;
    }

    /**
     * Gets the value of the operatorCompanyId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOperatorCompanyId() {
        return operatorCompanyId;
    }

    /**
     * Sets the value of the operatorCompanyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOperatorCompanyId(BigInteger value) {
        this.operatorCompanyId = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the documentControlType property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDocumentControlType() {
        return documentControlType;
    }

    /**
     * Sets the value of the documentControlType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDocumentControlType(BigInteger value) {
        this.documentControlType = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link SmCustomer }
     *     
     */
    public SmCustomer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmCustomer }
     *     
     */
    public void setCustomer(SmCustomer value) {
        this.customer = value;
    }

    /**
     * Gets the value of the orderLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmOrderLine }
     * 
     * 
     */
    public List<SmOrderLine> getOrderLine() {
        if (orderLine == null) {
            orderLine = new ArrayList<SmOrderLine>();
        }
        return this.orderLine;
    }

}
