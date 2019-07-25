package com.turkcell.playcell.gamingplatform.som.service;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision;
import com.turkcell.playcell.gamingplatform.common.enums.StatusTypes;
import com.turkcell.playcell.gamingplatform.common.repository.CustomerProvisionRepository;
import com.turkcell.playcell.gamingplatform.som.model.GenericProvisioningServiceResponse;
import com.turkcell.playcell.gamingplatfrom.som.dto.CustomerDetailDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CustomerProvisionService {
	
	private final CustomerProvisionRepository customerProvisionRepository;
	
	private final ProvisionProcessService provisionProcessService;
	
	public void insertCustomerProvisionRecord(GenericProvisioningServiceResponse response) {
		
		log.info(String.format("insertCustomerProvisionRecord called with msisdn: %s", response.getMsisdn()));
        
        try {
        	
        	CustomerProvision prov = CustomerProvision.builder()
        			.msisdn(response.getMsisdn())
        			.cpcpmOfferId(Integer.parseInt(response.getCpcmOfferId()))
        			.workflowLabel(response.getWorkflowType().getValue())
        			.provServiceId(Integer.parseInt(response.getSdpDetailValue()))
        			.oldMsisdn(response.getOldMsisdn())
        			.processStatus(StatusTypes.READY_TO_PROCESS).build();
        			
        	customerProvisionRepository.save(prov);

        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
        }
		
	}
	
	public CustomerProvision tryToLockNextEntityForProcessing() {
		
		final CustomerProvision customerProvisionEntity = customerProvisionRepository.findNextEntityForProcessing(StatusTypes.READY_TO_PROCESS.getValue());

		if (customerProvisionEntity == null) {
			return null;
		}
		
		log.info(String.format("tryToLockNextEntityForProcessing called with msisdn: %s", customerProvisionEntity.getMsisdn()));
		
		if (!customerProvisionRepository.findEntityMsisdnForStatus(customerProvisionEntity.getId(), customerProvisionEntity.getMsisdn()).isEmpty()) {
			log.info("tryToLockNextEntityForProcessing : Worker thread found active records msisdn: {}, find next record.", customerProvisionEntity.getMsisdn());
			return null;
		}

		int updatedRowCount = customerProvisionRepository.tryLockEntity(
				customerProvisionEntity.getId(),
				customerProvisionEntity.getProcessStatus().getValue(),
				StatusTypes.PROCESSING.getValue(),
				new Date());

		if (updatedRowCount == 0) {
			log.debug("tryToLockNextEntityForProcessing : customerProvisionEntity updateRowCount equals zero.");
			return null;
		}

		return customerProvisionEntity;
	}
	
	public boolean setProvisionStatusDone(Long provId) {
		
		final CustomerProvision customerProvisionEntity = customerProvisionRepository.findById(provId).get();
		customerProvisionEntity.setProcessStatus(StatusTypes.DONE);

		if (customerProvisionRepository.save(customerProvisionEntity) != null) {
			log.debug("processCustomerProvisionRecord : CustomerProvision record is updated to DONE.");
			return true;
		}
		
		return false;
	}
	
	public void processCustomerProvisionRecord(CustomerProvision customerProvision) {
		
		log.debug("Executing CustomerProvisionEntity, id: {}, status: {} ", customerProvision.getId(), customerProvision.getProcessStatus());
			
			CustomerDetailDTO customerDto = CustomerDetailDTO.builder().cpcmOfferId(customerProvision.getCpcpmOfferId())
					.msisdn(customerProvision.getMsisdn())
					.provServiceId(customerProvision.getProvServiceId())
					.oldMsisdn(customerProvision.getOldMsisdn())
					.build();
			
			switch (customerProvision.getWorkflowLabel()) {
			
			case "ACCOUNT_ACTIVATION":
				provisionProcessService.activateAccount(customerDto);
				break;
			case "CHANGE_MSISDN":
				provisionProcessService.changeMsisdn(customerDto);
				break;
			case "ACCOUNT_INACTIVATION":
				provisionProcessService.inActivateAccount(customerDto);
				break;
			case "ACCOUNT_SUSPENSION":
				provisionProcessService.suspendAccount(customerDto);
				break;
			case "SUBSCRIPTION_ACTIVATION":
				provisionProcessService.activateSubscription(customerDto);
				break;
			case "ACCOUNT_TAKEOVER":
				provisionProcessService.takeOverAccount(customerDto);
				break;
			case "SUBSCRIPTION_CREATION":
				provisionProcessService.createSubscription(customerDto);
				break;
			case "SUBSCRIPTION_INACTIVATION":
				provisionProcessService.inActivateSubscription(customerDto);
				break;
			case "SUBSCRIPTION_SUSPENSION":
				provisionProcessService.suspendSubscription(customerDto);
				break;
			default:
				log.error("Invalid WorkFlowLabel is received {} for msisdn: {}", customerProvision.getWorkflowLabel(), customerProvision.getMsisdn());
				break;
			}
	}

}
