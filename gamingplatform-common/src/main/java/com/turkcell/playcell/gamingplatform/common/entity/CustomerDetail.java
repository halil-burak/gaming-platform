package com.turkcell.playcell.gamingplatform.common.entity;

import java.time.Instant;

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
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "SYS_CUSTOMER_DETAIL", uniqueConstraints = @UniqueConstraint(columnNames={"MSISDN", "CPCM_OFFER_ID"}))
public class CustomerDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	
	@Column(name = "CREATE_DATE")
    private Instant createdDate;
	
	@Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate;
	
	@NotNull @NotEmpty
    @Column(name = "STATUS")
    private Boolean status;
	
	@NotNull @NotEmpty
    @Column(name = "MSISDN")
    private String msisdn;
	
	@NotNull @NotEmpty
    @Column(name = "CPCM_OFFER_ID")
    private String cpcmOfferId;
	
	@NotNull @NotEmpty
    @Column(name = "PROV_SERVICE_ID")
    private String provServiceId;
	
    @PrePersist
    protected void onCreate() {
        this.createdDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = Instant.now();
    }

}
