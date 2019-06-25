package com.turkcell.playcell.gamingplatform.common.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "CREATED_BY")
    private String  createdBy;

    @Column(name = "CREATE_DATE")
    private Instant createdDate;

    @Column(name = "LAST_MODIFIED_BY")
    private String LastModifiedBy;

    @Column(name = "LAST_MODIFIED_DATE")
    private Instant lastModifiedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = Instant.now();
    }
    
}
