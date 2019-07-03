package com.turkcell.playcell.gamingplatform.som.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerDetail;
import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision;
import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision.CustomerProvisionBuilder;
import com.turkcell.playcell.gamingplatform.common.repository.CustomerDetailRepository;
import com.turkcell.playcell.gamingplatform.common.repository.CustomerProvisionRepository;
import com.turkcell.playcell.gamingplatform.som.model.GenericProvisioningServiceResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProvisionProcessService {
	
	@Autowired
	private CustomerDetailRepository customerDetailRepository;
	
	
	public void createSubscription(GenericProvisioningServiceResponse response) {
        log.info(String.format("createSubscription called with %s", response.getMsisdn()));
    }

    public void renewSubscription(GenericProvisioningServiceResponse response) {
        //NO NEED PLATFORM UPDATE
        log.info("renewSubscription called");

    }

    public void suspendSubscription(GenericProvisioningServiceResponse response) {
        log.info(String.format("suspendSubscription called with %s", response.getMsisdn()));
    }

    public void activateSubscription(GenericProvisioningServiceResponse response) {
        log.info(String.format("activateSubscription called with %s", response.getMsisdn()));

    }

    public void inActivateSubscription(GenericProvisioningServiceResponse response) {
        log.info(String.format("inActivateSubscription called with %s", response.getMsisdn()));

    }

    public void suspendAccount(GenericProvisioningServiceResponse response) {
        log.info(String.format("suspendAccount called with %s", response.getMsisdn()));

    }

    public void activateAccount(GenericProvisioningServiceResponse response) {
        log.info(String.format("activateAccount called with %s", response.getMsisdn()));

    }

    public void inActivateAccount(GenericProvisioningServiceResponse response) {
        log.info(String.format("inActivateAccount called with %s", response.getMsisdn()));

    }

    public void takeOverAccount(GenericProvisioningServiceResponse response) {
        log.info(String.format("takeOverAccount called with %s", response.getMsisdn()));

    }

    public void changeMsisdn(GenericProvisioningServiceResponse response) {
        log.info(String.format("changeMsisdn called %s", response.getMsisdn()));

    }

}
