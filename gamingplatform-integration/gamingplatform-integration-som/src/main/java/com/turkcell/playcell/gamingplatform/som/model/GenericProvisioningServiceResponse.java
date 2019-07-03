package com.turkcell.playcell.gamingplatform.som.model;

import java.util.Date;

import com.turkcell.playcell.gamingplatform.som.enumtypes.WorkflowTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GenericProvisioningServiceResponse {
	
	 	private WorkflowTypes workflowType;

	    private String msisdn;

	    private String oldMsisdn;

	    private String transactionId;

	    private String catalogVariantId;

	    private String catalogOfferId;

	    private String cpcmOfferId;

	    private String cpcmProductId;

	    private String userId;

	    private String statusId;

	    private String campaignName;

	    private Date commitmentDate;

	    private Date campaignStartDate;

	    private Date campaignEndDate;

	    private Date nextChargingDate;

	    private String sdpDetailName;

	    private String sdpDetailValue;

}
