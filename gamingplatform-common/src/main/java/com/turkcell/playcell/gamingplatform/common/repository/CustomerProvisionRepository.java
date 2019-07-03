package com.turkcell.playcell.gamingplatform.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision;

public interface CustomerProvisionRepository extends JpaRepository<CustomerProvision, Long> {
	
	Optional<CustomerProvision> findCustomerByMsisdn(String msisdn);

}
