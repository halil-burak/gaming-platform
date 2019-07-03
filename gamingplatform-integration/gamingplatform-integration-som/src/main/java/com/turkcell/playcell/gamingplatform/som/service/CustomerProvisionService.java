package com.turkcell.playcell.gamingplatform.som.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision;
import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision.CustomerProvisionBuilder;
import com.turkcell.playcell.gamingplatform.common.repository.CustomerProvisionRepository;
import com.turkcell.playcell.gamingplatform.som.model.GenericProvisioningServiceResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerProvisionService {
	
	@Autowired
	private CustomerProvisionRepository customerProvisionRepository;
	
	public void insertCustomerProvisionRecord(GenericProvisioningServiceResponse response) {
		
		log.info(String.format("insertCustomerProvisionRecord called with %s", response.getMsisdn()));
        
        try {
        	
        	CustomerProvisionBuilder provision = CustomerProvision.builder()
        			.msisdn(response.getMsisdn())
        			.cpcpmOfferId(response.getCpcmOfferId())
        			.workflowLabel(response.getWorkflowType().getValue())
        			.isProcessed(false);
        			
        	customerProvisionRepository.save(provision.build());

        }catch (Exception ex){
            log.error(ex.getMessage());
        }
		
	}

}
