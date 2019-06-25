package com.turkcell.playcell.gamingplatform.som.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.SoapFaultClientException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SomService extends WebServiceGatewaySupport {
	
	@Qualifier("createOrder")
    @Autowired
    private Jaxb2Marshaller marshaller;

    private static final String SILENT_KEY= "NOTIFICATION_CATEGORY";
    private static final String SILENT_VALUE = "NO_SMS";

    @PostConstruct
    public void setMarshaller() {
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);
        /* For Custom Interceptor
        ClientInterceptor [] clientInterceptors = new ClientInterceptor[]{clientInterceptor};
        this.setInterceptors(clientInterceptors);
        */
    }

	@WsAudit
    public SomCreateOrderResponse createOrder(SomCreateOrderRequest somOrderRequest) throws SomServiceException {

        CreateOrderRequest request = createOrderRequest(somOrderRequest, false);


        ServiceOrderManagementResponse response;


        try {
            response = (ServiceOrderManagementResponse) getWebServiceTemplate()
                    .marshalSendAndReceive(createOrderURI, request);
        } catch (SoapFaultClientException ex) {
            // TODO - throw customized exc. for meaningful for us!
            throw new SomServiceException(ex.getFaultStringOrReason(), ex);
        }

        return convertSOMResponseToSomCreateOrderResponse(response);
    }

    private SomCreateOrderResponse convertSOMResponseToSomCreateOrderResponse
            (ServiceOrderManagementResponse serviceOrderManagementResponse) {

        // we need only lineItem
        ServiceOrderManagementResponseLineItem serviceOrderManagementResponseLineItem =
                serviceOrderManagementResponse.getLine().get(0).getLineItem().get(0);


        SomCreateOrderResponse somCreateOrderResponse = SomCreateOrderResponse.builder()
                .orderId(serviceOrderManagementResponseLineItem.getOfferId())
                .productId(serviceOrderManagementResponseLineItem.getProductId())
                ._continue(serviceOrderManagementResponseLineItem.isContinue())
                .action(serviceOrderManagementResponseLineItem.getAction().intValue())
                .build();

        serviceOrderManagementResponseLineItem.getBusinessInteraction().ifPresent(smBusinessInteraction -> {

            BusinessInteraction businessInteraction = new BusinessInteraction();

            smBusinessInteraction.getApproval().ifPresent(smBusinessInteractionApprovals -> {
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
            });

            smBusinessInteraction.getError().ifPresent(smBusinessInteractionError -> {
                businessInteraction.setBusinessInteractionError(BusinessInteractionError.builder()
                        .errorDetail(smBusinessInteractionError.getErrorDetail())
                        .errorId(smBusinessInteractionError.getErrorId())
                        .errorType(smBusinessInteractionError.getType()) // TODO make enum
                        .notificationId(smBusinessInteractionError.getNotificationId())
                        .systemWillRetry(smBusinessInteractionError.isSystemWillRetry())
                        .userText(smBusinessInteractionError.getUserText())
                        .build());
            });

            smBusinessInteraction.getInformation().ifPresent(smBusinessInteractionInfos -> {
                if (!smBusinessInteractionInfos.isEmpty()) {
                    SmBusinessInteractionInfo smBusinessInteractionInfo = smBusinessInteractionInfos.get(0);
                    businessInteraction.setBusinessInteractionInfo(BusinessInteractionInfo.builder()
                            .messageText(smBusinessInteractionInfo.getMessageText())
                            .type(smBusinessInteractionInfo.getType()) // TODO make enum
                            .build());
                }
            });

            somCreateOrderResponse.setBusinessInteraction(businessInteraction);

        });


        return somCreateOrderResponse;
    }

    private CreateOrderRequest createOrderRequest(SomCreateOrderRequest somOrderRequest, boolean silently) {
        CreateOrderRequest request = new CreateOrderRequest();


        SmOrderHeader orderHeader = new SmOrderHeader();

        SmChannelApplication channelApplication = new SmChannelApplication();
        channelApplication.setApplicationId(this.applicationId); //must, const
        channelApplication.setChannelId(somOrderRequest.getSomChannelType().getValue()); //must, const 15,16  vs

        orderHeader.setChannelApplication(channelApplication);

        orderHeader.setRequestDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        orderHeader.setSessionId(somOrderRequest.getTransactionId());
        orderHeader.setTransactionId(somOrderRequest.getTransactionId());

        SmUser smUser = new SmUser();
        smUser.setUserName(this.userName);

        SmDealer smDealer = new SmDealer();
        smDealer.setDealerCode(this.userName); // const must

        smUser.setDealer(smDealer);
        orderHeader.setUser(smUser);
        request.setHeader(orderHeader);

        request.setSynchronous(true); // TODO -- ask

        SmOrderLine smOrderLine = new SmOrderLine();
        smOrderLine.setMsisdn(somOrderRequest.getMsisdn()); // must, dif for user

        SmOrderLineItem orderLineItem = new SmOrderLineItem();
        orderLineItem.setOfferId(new BigInteger(somOrderRequest.getOfferId()));  //catalogOfferId that customer requested to buy iddaa paketi örn:1 /süper2/std paket3 (sadece android'te faturama yansıt butonunda mevcut)
        orderLineItem.setAction(somOrderRequest.getSomActionType().getValue()); // const must

        if(silently){
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
                    .marshalSendAndReceive(createOrderURI, request);
        } catch (SoapFaultClientException ex) {
            // TODO - throw customized exc. for meaningful for us!
            throw new SomServiceException(ex.getFaultStringOrReason(), ex);
        }

        return convertSOMResponseToSomCreateOrderResponse(response);
    }


}
