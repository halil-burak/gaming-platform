package com.turkcell.playcell.gamingplatform.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.turkcell.playcell.gamingplatform.common.enums.StatusTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SYS_CUSTOMER_PROVISION")
public class CustomerProvision {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cust_provision_seq")
	@SequenceGenerator(
			name="cust_provision_seq",
			sequenceName="sys_cust_prov_seq",
			allocationSize=20
		)
    private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "CREATE_DATE")
    private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;
	
	@NotNull
	@Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_STATUS")
    private StatusTypes processStatus;
	
	@NotNull @NotEmpty
    @Column(name = "MSISDN")
    private String msisdn;
	
	@NotNull
    @Column(name = "CPCM_OFFER_ID")
    private Integer cpcpmOfferId;
	
	@NotNull @NotEmpty
    @Column(name = "WORKFLOW_LABEL")
    private String workflowLabel;
	
    @Column(name = "OLD_MSISDN")
    private String oldMsisdn;
    
    @NotNull
    @Column(name = "PROV_SERVICE_ID")
    private Integer provServiceId;
	
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        Date date = new Date();
        lastModifiedDate = date;
        if (createdDate == null) {
        	createdDate = date;
        }
    }

}
