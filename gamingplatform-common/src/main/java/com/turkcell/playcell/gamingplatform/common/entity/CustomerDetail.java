package com.turkcell.playcell.gamingplatform.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cust_detail_seq")
	@SequenceGenerator(
			name="cust_detail_seq",
			sequenceName="sys_cust_det_seq",
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
    @Column(name = "STATUS")
    private Boolean status;
	
	@NotNull @NotEmpty
    @Column(name = "MSISDN")
    private String msisdn;
	
	@NotNull
    @Column(name = "CPCM_OFFER_ID")
    private Integer cpcmOfferId;
	
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
