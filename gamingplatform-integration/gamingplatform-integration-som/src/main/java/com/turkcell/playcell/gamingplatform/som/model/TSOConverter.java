package com.turkcell.playcell.gamingplatform.som.model;

import com.accenture.assets.sdp.commondatamodel.asynch.Attribute;
import com.accenture.assets.sdp.commondatamodel.asynch.TSODATA;
import com.turkcell.playcell.gamingplatform.som.enumtypes.WorkflowTypes;
import com.turkcell.playcell.gamingplatform.som.util.DateUtil;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TSOConverter {
	
	public GenericProvisioningServiceResponse convertTSODATAToGPServiceRequest(TSODATA tsodata){

        GenericProvisioningServiceResponse request = new GenericProvisioningServiceResponse();

         setTSOAttributes(tsodata.getTSOattributes().getAttribute(), request);
         setRecursiveAttributesOrList(tsodata.getTSOattributes().getList(), request);

        return request;
    }



    private void setTSOAttributes(List<Attribute> tsoAttributes, GenericProvisioningServiceResponse request){

        tsoAttributes.forEach(attribute -> {
            switch (attribute.getName()){

                case "MSISDN":
                    request.setMsisdn(attribute.getValue());
                    break;
                    
                /*case "TRANSACTION_ID":
                    request.setTransactionId(attribute.getValue());
                    break;*/

                case "WORKFLOW_LABEL":
                    request.setWorkflowType(WorkflowTypes.get(attribute.getValue()));
                    break;
                    
                /*case "CATALOG_SERVICE_VARIANT_ID":
                	request.setCatalogVariantId(attribute.getValue());
                    break;*/
                /*case "CATALOG_OFFER_ID":
                    request.setCatalogOfferId(attribute.getValue());
                    break;*/

                /*case "USER_ID":
                    request.setUserId(attribute.getValue());
                    break;*/

                case "CPCM_OFFERID":
                    request.setCpcmOfferId(attribute.getValue());
                    break;

               /*case "CPCM_PRODUCTID":
                    request.setCpcmProductId(attribute.getValue());
                    break;*/

                /*case "STATUS_ID":
                    request.setStatusId(attribute.getValue());
                    break;*/

                /*case "CAMPAIGN_NAME":
                    request.setCampaignName(attribute.getValue());
                    break;*/

                /*case "COMMITMENT_DATE":
                    request.setCommitmentDate(DateUtil.parseDate("yyyyMMddHHmmss",attribute.getValue()).get());
                    break;

                case "CAMPAIGN_START_DATE":
                    request.setCampaignStartDate(DateUtil.parseDate("yyyyMMddHHmmss",attribute.getValue()).get());
                    break;

                case "NEXT_CHARGING_DATE":
                    request.setNextChargingDate(DateUtil.parseDate("yyyyMMddHHmmss",attribute.getValue()).get());
                    break;*/

                case "DETAIL_NAME":
                    request.setSdpDetailName(attribute.getValue());
                    break;

                case "VALUE":
                    request.setSdpDetailValue(attribute.getValue());
                    break;
                    
				case "OLD_MSISDN":
					request.setOldMsisdn(attribute.getValue());
					break;

               /* case "CAMPAIGN_END_DATE":
                    request.setCampaignEndDate(DateUtil.parseDate("yyyyMMddHHmmss",attribute.getValue()).get());*/

            }

        });
    }


    private  void setRecursiveAttributesOrList(java.util.List<com.accenture.assets.sdp.commondatamodel.asynch.List> attributesOrList , GenericProvisioningServiceResponse request){
        if(attributesOrList != null &&!attributesOrList.isEmpty()){
            for(com.accenture.assets.sdp.commondatamodel.asynch.List listOfList: attributesOrList){
                setTSOAttributes(listOfList.getAttribute(), request);
                setRecursiveAttributesOrList(listOfList.getList(),request);
            }
        }

    }

}
