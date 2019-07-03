package com.turkcell.playcell.gamingplatform.common.entity;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@Column(name = "CREATE_DATE")
    private Instant createdDate;
	
	@Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate;
	
	@NotNull
    @Column(name = "PROCESS_STATUS", nullable = false)
    private Boolean isProcessed;
	
	@NotNull @NotEmpty
    @Column(name = "MSISDN")
    private String msisdn;
	
	@NotNull @NotEmpty
    @Column(name = "CPCM_OFFER_ID")
    private String cpcpmOfferId;
	
	@NotNull @NotEmpty
    @Column(name = "WORKFLOW_LABEL")
    private String workflowLabel;
	
    @Column(name = "OLD_MSISDN")
    private String oldMsisdn;
	
    @PrePersist
    protected void onCreate() {
        this.createdDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = Instant.now();
    }

}
