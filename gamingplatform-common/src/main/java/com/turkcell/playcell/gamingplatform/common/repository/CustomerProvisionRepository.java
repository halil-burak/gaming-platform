package com.turkcell.playcell.gamingplatform.common.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.turkcell.playcell.gamingplatform.common.entity.CustomerProvision;

public interface CustomerProvisionRepository extends JpaRepository<CustomerProvision, Long> {
	
	@Query(value = 	" SELECT * " +
			" FROM SYS_CUSTOMER_PROVISION " +
			" WHERE PROCESS_STATUS = :status " +
			"   AND ROWNUM = 1 " +
			" ORDER BY LAST_MODIFIED_DATE ASC",
			nativeQuery = true)
	CustomerProvision findNextEntityForProcessing(@Param("status") String status);
	
	@Query(value = 	" SELECT * " +
			" FROM SYS_CUSTOMER_PROVISION prov " +
			" WHERE PROCESS_STATUS IN ('PROCESSING') " +
			" AND prov.MSISDN = :p_msisdn " +
			" AND prov.ID != :rowId ",
			nativeQuery = true)
	List<CustomerProvision> findEntityMsisdnForStatus(@Param("rowId") Long id, @Param("p_msisdn") String msisdn);
	
	@Modifying
	@Query(value = 	" UPDATE SYS_CUSTOMER_PROVISION " +
			" SET PROCESS_STATUS = :nextStatus, " +
			" LAST_MODIFIED_DATE = :updatedAt " +
			" WHERE ID = :rowId " +
			" AND PROCESS_STATUS = :currentStatus ",
			nativeQuery = true)
	public int tryLockEntity(
			@Param("rowId") Long rowId,
			@Param("currentStatus") String currentStatus,
			@Param("nextStatus") String nextStatus,
			@Param("updatedAt") Date updatedAt);
	
	@Modifying
	@Query(value = 	" UPDATE SYS_CUSTOMER_PROVISION " +
			" SET PROCESS_STATUS = :nextStatus, " +
			" LAST_MODIFIED_DATE = :updatedAt " +
			" WHERE ID = :rowId ",
			nativeQuery = true)
	public int setDoneStatustoEntity(
			@Param("rowId") Long rowId,
			@Param("nextStatus") String nextStatus,
			@Param("updatedAt") Date updatedAt);

}
