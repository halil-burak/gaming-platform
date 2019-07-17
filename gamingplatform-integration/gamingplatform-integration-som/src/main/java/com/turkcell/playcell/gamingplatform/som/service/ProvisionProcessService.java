package com.turkcell.playcell.gamingplatform.som.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerDetail;
import com.turkcell.playcell.gamingplatform.common.repository.CustomerDetailRepository;
import com.turkcell.playcell.gamingplatfrom.som.dto.CustomerDetailDTO;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProvisionProcessService {
	
	@Autowired
	private CustomerDetailRepository customerDetailRepository;
	
	private void setNewMsisdnCustomerDetail(String msisdn, Integer offerId, String newMsisdn) {
		CustomerDetail cutomerDetailEntity = customerDetailRepository.findCustomerDetail(msisdn, offerId);
		cutomerDetailEntity.setMsisdn(newMsisdn);
		customerDetailRepository.save(cutomerDetailEntity);
		log.info("setMsisdnCustomerDetail : Oldmsisdn {}, Newmsisdn {}, offerId {} Customer msisdn updated.", msisdn, newMsisdn, offerId);
	}
	
	
	private void setActiveCustomerDetailStatus(String msisdn, Integer offerId) {
        CustomerDetail cutomerDetailEntity = customerDetailRepository.findCustomerDetail(msisdn, offerId);
        cutomerDetailEntity.setStatus(true);
        customerDetailRepository.save(cutomerDetailEntity);
        log.info("setActiveCustomerDetailStatus : msisdn {}, offerId {} Customer set inactive.", msisdn, offerId);
	}
	
	private void setInactiveCustomerDetailStatus(String msisdn, Integer offerId) {		
        CustomerDetail cutomerDetailEntity = customerDetailRepository.findCustomerDetail(msisdn, offerId);
        cutomerDetailEntity.setStatus(false);
        customerDetailRepository.save(cutomerDetailEntity);
        log.info("setInactiveCustomerDetailStatus : msisdn {}, offerId {} Customer set inactive.", msisdn, offerId);
	}
	
	
	public void createSubscription(CustomerDetailDTO customerDetailDTO) {
		
		CustomerDetail customerDetail = CustomerDetail.builder().cpcmOfferId(customerDetailDTO.getCpcmOfferId())
				.msisdn(customerDetailDTO.getMsisdn())
				.provServiceId(customerDetailDTO.getProvServiceId())
				.status(true)
				.build();
		
		customerDetailRepository.save(customerDetail);
        
    }

    public void suspendSubscription(CustomerDetailDTO customerDetailDTO) {
        log.info("suspendSubscription called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
        this.setInactiveCustomerDetailStatus(customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    }

    public void activateSubscription(CustomerDetailDTO customerDetailDTO) {
    	log.info("activateSubscription called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    	this.setActiveCustomerDetailStatus(customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    }

    public void inActivateSubscription(CustomerDetailDTO customerDetailDTO) {
    	log.info("inActivateSubscription called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    	this.setInactiveCustomerDetailStatus(customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    }

    public void suspendAccount(CustomerDetailDTO customerDetailDTO) {
    	log.info("suspendAccount called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    	this.setInactiveCustomerDetailStatus(customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    }

    public void activateAccount(CustomerDetailDTO customerDetailDTO) {
    	log.info("activateAccount called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    	this.setActiveCustomerDetailStatus(customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    }

    public void inActivateAccount(CustomerDetailDTO customerDetailDTO) {
    	log.info("inActivateAccount called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    	this.setInactiveCustomerDetailStatus(customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    }

    public void takeOverAccount(CustomerDetailDTO customerDetailDTO) {
    	log.info("takeOverAccount called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    	this.setInactiveCustomerDetailStatus(customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    }

    public void changeMsisdn(CustomerDetailDTO customerDetailDTO) {
    	log.info("changeMsisdn called with msisdn: {}, OfferId: {}", customerDetailDTO.getMsisdn(), customerDetailDTO.getCpcmOfferId());
    	this.setNewMsisdnCustomerDetail(customerDetailDTO.getOldMsisdn(), customerDetailDTO.getCpcmOfferId(), customerDetailDTO.getMsisdn());
    }

}
