package com.turkcell.playcell.gamingplatform.som.job;

import javax.annotation.PreDestroy;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision;
import com.turkcell.playcell.gamingplatform.som.service.CustomerProvisionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerProvisionFixedDelayTask {
	
	private final ThreadPoolTaskExecutor customerProvisionApiThreadPoolTaskExecutor;
	
	private final CustomerProvisionService customerProvisionService;
	
	private boolean isShuttingDown = false;
	
	@PreDestroy
	public void beforeShutdown() {
		log.info("Shuttind down CustomerProvisionFixedDelayTask job");

		this.isShuttingDown = true;
	}

	@Scheduled(fixedDelay = 15000)
	public void execute() {
		
		if (this.isShuttingDown) {
			return;
		}
		
		int activeCount = customerProvisionApiThreadPoolTaskExecutor.getActiveCount();
		int maxPoolSize = customerProvisionApiThreadPoolTaskExecutor.getMaxPoolSize();

		if (activeCount >= maxPoolSize) {
			return;
		}
		
		while (! this.isShuttingDown && customerProvisionApiThreadPoolTaskExecutor.getActiveCount() < customerProvisionApiThreadPoolTaskExecutor.getMaxPoolSize()) {
			
			log.debug("CustomerProvisionFixedDelayTask -> execute");
			final CustomerProvision customerProvisionEntity = customerProvisionService.tryToLockNextEntityForProcessing();

			if (customerProvisionEntity == null) {
				log.debug("There is no customerProvision entity waiting to be processed.");
				break;
			}

			customerProvisionApiThreadPoolTaskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {	
					log.debug("CustomerProvisionFixedDelayTask:customerProvisionApiThreadPoolTaskExecutor -> execute");
					customerProvisionService.processCustomerProvisionRecord(customerProvisionEntity);
				}
				
			});
			
			if (!customerProvisionService.setProvisionStatusDone(customerProvisionEntity.getId())) {
				log.error("Process status failed to set DONE !!");
			}

		}
		
	}
}
