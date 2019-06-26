package com.turkcell.playcell.gamingplatform.common.entity;


import javax.persistence.*;
import java.time.Instant;

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
//        this.createdDate = Instant.now();
//        try{
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            this.LastModifiedBy = userDetails.getUsername();
//        }catch(RuntimeException e) {
//            this.LastModifiedBy = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        }
    }

    @PreUpdate
    protected void onUpdate() {
//        this.lastModifiedDate = Instant.now();
//        try{
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            this.LastModifiedBy = userDetails.getUsername();
//        }catch(RuntimeException e) {
//            this.LastModifiedBy = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return LastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        LastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
