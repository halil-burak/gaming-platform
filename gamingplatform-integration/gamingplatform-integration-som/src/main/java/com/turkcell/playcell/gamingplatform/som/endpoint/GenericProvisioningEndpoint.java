package com.turkcell.playcell.gamingplatform.som.endpoint;


import com.accenture.assets.sdp.commondatamodel.asynch.TSODATA;
import com.accenture.assets.sdp.commondatamodel.asynch.TSOresult;
import com.turkcell.playcell.gamingplatform.som.model.GenericProvisioningServiceResponse;
import com.turkcell.playcell.gamingplatform.som.model.TSOConverter;
import com.turkcell.playcell.gamingplatform.som.service.CustomerProvisionService;
import com.turkcell.playcell.gamingplatform.som.service.WsAudit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
@RequiredArgsConstructor
public class GenericProvisioningEndpoint {
	
	private static final String NAMESPACE_URI = "http://www.accenture.com/assets/sdp/commonDataModel/asynch";

	private final CustomerProvisionService customerProvisionService;
    private final TSOConverter tsoConverter;

    @WsAudit
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "TSO_DATA")
    @ResponsePayload
    public TSOresult TSO_DATA(@RequestPayload TSODATA tsodata) {

        TSOresult tsoResult = new TSOresult();
        //GenericProvisioningServiceLogger logger;
        log.info("Getting TSO_DATA : " + tsodata.toString());

        // TODO set necessary fields
        GenericProvisioningServiceResponse response = tsoConverter.convertTSODATAToGPServiceRequest(tsodata);

        customerProvisionService.insertCustomerProvisionRecord(response);

        tsoResult.setErrorCode("0");
        tsoResult.setStatusCode("Status:" + response.getWorkflowType().getValue());
        return  tsoResult;
    }
    
}
