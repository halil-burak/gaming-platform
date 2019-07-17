package com.turkcell.playcell.gamingplatform.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerDetail;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetail, Long>{

	@Query(value = 	" SELECT * " +
			" FROM SYS_CUSTOMER_DETAIL " +
			" WHERE MSISDN = :p_msisdn " +
			" AND CPCM_OFFER_ID = :p_offerId",
			nativeQuery = true)
	 CustomerDetail findCustomerDetail(@Param("p_msisdn") String msisdn, @Param("p_offerId") Integer OfferId);
	
}
