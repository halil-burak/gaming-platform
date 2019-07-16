package com.turkcell.playcell.gamingplatform.som.model;

import com.accenture.assets.sdp.commondatamodel.asynch.Attribute;
import com.accenture.assets.sdp.commondatamodel.asynch.TSODATA;
import com.turkcell.playcell.gamingplatform.som.enumtypes.WorkflowTypes;

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

                case "WORKFLOW_LABEL":
                    request.setWorkflowType(WorkflowTypes.get(attribute.getValue()));
                    break;

                case "CPCM_OFFERID":
                    request.setCpcmOfferId(attribute.getValue());
                    break;

                case "DETAIL_NAME":
                    request.setSdpDetailName(attribute.getValue());
                    break;

                case "VALUE":
                    request.setSdpDetailValue(attribute.getValue());
                    break;
                    
				case "OLD_MSISDN":
					request.setOldMsisdn(attribute.getValue());
					break;

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
