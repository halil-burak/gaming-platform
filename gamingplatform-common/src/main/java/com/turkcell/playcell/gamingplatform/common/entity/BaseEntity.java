package com.turkcell.playcell.gamingplatform.common.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1751782326567207931L;

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
//        try{
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            this.LastModifiedBy = userDetails.getUsername();
//        }catch(RuntimeException e) {
//            this.LastModifiedBy = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = Instant.now();
//        try{
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            this.LastModifiedBy = userDetails.getUsername();
//        }catch(RuntimeException e) {
//            this.LastModifiedBy = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//        }
    }
}
