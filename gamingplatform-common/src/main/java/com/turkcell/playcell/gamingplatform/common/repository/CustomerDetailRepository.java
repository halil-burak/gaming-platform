package com.turkcell.playcell.gamingplatform.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerDetail;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long>{

	List <CustomerDetail> findCustomerByMsisdn(String msisdn);
}
