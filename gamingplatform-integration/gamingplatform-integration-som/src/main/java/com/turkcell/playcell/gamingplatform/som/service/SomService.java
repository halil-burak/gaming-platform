package com.turkcell.playcell.gamingplatform.som.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

import com.turkcell.playcell.gamingplatform.som.config.SomApplicationProperties;
import com.turkcell.playcell.gamingplatform.som.model.BusinessInteraction;
import com.turkcell.playcell.gamingplatform.som.model.BusinessInteractionApproval;
import com.turkcell.playcell.gamingplatform.som.model.BusinessInteractionError;
import com.turkcell.playcell.gamingplatform.som.model.BusinessInteractionInfo;
import com.turkcell.playcell.gamingplatform.som.model.SomCreateOrderRequest;
import com.turkcell.playcell.gamingplatform.som.model.SomCreateOrderResponse;
import com.turkcell.playcell.gamingplatform.som.model.SomServiceException;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.CreateOrderRequest;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.ServiceOrderManagementResponse;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.ServiceOrderManagementResponseLineItem;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmAttribute;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmBusinessInteraction;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmBusinessInteractionApproval;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmBusinessInteractionError;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmBusinessInteractionInfo;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmChannelApplication;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmDealer;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmOrderHeader;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmOrderLine;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmOrderLineItem;
import com.turkcell.playcell.gamingplatform.som.wsdltypes.createorder.SmUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SomService extends WebServiceGatewaySupport  {

	private final SomApplicationProperties applicationproperties;
	
	@Qualifier("createOrder")
	@Autowired
	private Jaxb2Marshaller marshaller;
	
    @PostConstruct
    public void setMarshaller() {
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
    }
    
    private static final String SILENT_KEY= "NOTIFICATION_CATEGORY";
    private static final String SILENT_VALUE = "NO_SMS";
    
	@WsAudit
    public SomCreateOrderResponse createOrder(SomCreateOrderRequest somOrderRequest) throws SomServiceException {
		
		log.debug("SomService : createOrder called ..");

        CreateOrderRequest request = createOrderRequest(somOrderRequest, false);
        
        ServiceOrderManagementResponse response;

        try {
            response = (ServiceOrderManagementResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(applicationproperties.getUri(), request);
        } catch (SoapFaultClientException ex) {
            throw new SomServiceException(ex.getFaultStringOrReason(), ex);
        }

        return convertSOMResponseToSomCreateOrderResponse(response);
    }
	
	private CreateOrderRequest createOrderRequest(SomCreateOrderRequest somOrderRequest, boolean silently) {
		
		log.debug("SomService : createOrderRequest called ..");
        
		CreateOrderRequest request = new CreateOrderRequest();

        SmOrderHeader orderHeader = new SmOrderHeader();

        SmChannelApplication channelApplication = new SmChannelApplication();
        channelApplication.setApplicationId(applicationproperties.getApplicationId()); // 758 -> playcell 
        channelApplication.setChannelId(somOrderRequest.getSomChannelType().getValue()); //15 -> iphone, 16 -> android, 36 -> mobile, 37 -> web

        orderHeader.setChannelApplication(channelApplication);

        orderHeader.setRequestDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        orderHeader.setSessionId(somOrderRequest.getTransactionId());
        orderHeader.setTransactionId(somOrderRequest.getTransactionId());

        SmUser smUser = new SmUser();
        smUser.setUserName(applicationproperties.getUsername());

        SmDealer smDealer = new SmDealer();
        smDealer.setDealerCode(applicationproperties.getUsername());

        smUser.setDealer(smDealer);
        orderHeader.setUser(smUser);
        request.setHeader(orderHeader);

        request.setSynchronous(true);

        SmOrderLine smOrderLine = new SmOrderLine();
        smOrderLine.setMsisdn(somOrderRequest.getMsisdn());

        SmOrderLineItem orderLineItem = new SmOrderLineItem();
        orderLineItem.setOfferId(new BigInteger(somOrderRequest.getOfferId())); 
        orderLineItem.setAction(somOrderRequest.getSomActionType().getValue());

        if(silently) {
            SmAttribute attribute = new SmAttribute();
            attribute.setKey(SILENT_KEY);
            attribute.setValue(SILENT_VALUE);
            orderLineItem.getAttribute().add(attribute);
        }
        
        smOrderLine.getOrderLineItem().add(orderLineItem);
        request.getOrderLine().add(smOrderLine);

        return request;
    }
	
    @WsAudit
    public SomCreateOrderResponse createOrderSilently(SomCreateOrderRequest somOrderRequest) throws SomServiceException {
    	
        log.info("SomService createOrderSilently called ..");
        
        CreateOrderRequest request = createOrderRequest(somOrderRequest, true);

        ServiceOrderManagementResponse response;

        try {
            response = (ServiceOrderManagementResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(applicationproperties.getUri(), request);
        } catch (SoapFaultClientException ex) {
            throw new SomServiceException(ex.getFaultStringOrReason(), ex);
        }

        return convertSOMResponseToSomCreateOrderResponse(response);
    }
	
    private SomCreateOrderResponse convertSOMResponseToSomCreateOrderResponse (ServiceOrderManagementResponse serviceOrderManagementResponse) {
    	
		log.debug("SomService : convertSOMResponseToSomCreateOrderResponse metmod is called.");
    	
    	ServiceOrderManagementResponseLineItem serviceOrderManagementResponseLineItem =
		        serviceOrderManagementResponse.getLine().get(0).getLineItem().get(0);
		
		SomCreateOrderResponse somCreateOrderResponse = SomCreateOrderResponse.builder()
		        .orderId(serviceOrderManagementResponseLineItem.getOfferId())
		        .productId(serviceOrderManagementResponseLineItem.getProductId())
		        ._continue(serviceOrderManagementResponseLineItem.isContinue())
		        .action(serviceOrderManagementResponseLineItem.getAction().intValue())
		        .build();
		
		if (serviceOrderManagementResponseLineItem.getBusinessInteraction() != null) {
			
			SmBusinessInteraction smBusinessInteraction = serviceOrderManagementResponseLineItem.getBusinessInteraction();
			
			BusinessInteraction businessInteraction = new BusinessInteraction();
			
			if (smBusinessInteraction.getApproval() != null) {
				
				List <SmBusinessInteractionApproval> smBusinessInteractionApprovals = smBusinessInteraction.getApproval();
				
		        if (!smBusinessInteractionApprovals.isEmpty()) {
		            SmBusinessInteractionApproval smBusinessInteractionApproval = smBusinessInteractionApprovals.get(0);
		            businessInteraction.setBusinessInteractionApproval(BusinessInteractionApproval.builder()
		                    .approveLabel(smBusinessInteractionApproval.getApproveLabel())
		                    .criteriaId(smBusinessInteractionApproval.getCriteriaId())
		                    .messageText(smBusinessInteractionApproval.getMessageText())
		                    .notificationId(smBusinessInteractionApproval.getNotificationId())
		                    .rejectLabel(smBusinessInteractionApproval.getRejectLabel())
		                    .build());
		        }	
			}
			
			if (smBusinessInteraction.getError() != null) {
				
				SmBusinessInteractionError smBusinessInteractionError = smBusinessInteraction.getError();
				
		        businessInteraction.setBusinessInteractionError(BusinessInteractionError.builder()
		                .errorDetail(smBusinessInteractionError.getErrorDetail())
		                .errorId(smBusinessInteractionError.getErrorId())
		                .errorType(smBusinessInteractionError.getType()) // TODO make enum
		                .notificationId(smBusinessInteractionError.getNotificationId())
		                .systemWillRetry(smBusinessInteractionError.isSystemWillRetry())
		                .userText(smBusinessInteractionError.getUserText())
		                .build());
				
			}

			if (smBusinessInteraction.getInformation() != null) {
				
				List<SmBusinessInteractionInfo> smBusinessInteractionInfos = smBusinessInteraction.getInformation();

		        if (!smBusinessInteractionInfos.isEmpty()) {
		            SmBusinessInteractionInfo smBusinessInteractionInfo = smBusinessInteractionInfos.get(0);
		            businessInteraction.setBusinessInteractionInfo(BusinessInteractionInfo.builder()
		                    .messageText(smBusinessInteractionInfo.getMessageText())
		                    .type(smBusinessInteractionInfo.getType()) // TODO make enum
		                    .build());
		        }
			}
		
		    somCreateOrderResponse.setBusinessInteraction(businessInteraction);

		}

		return somCreateOrderResponse;
    }
}
