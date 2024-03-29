package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Complaint extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 776215363266476298L;
	private String agent;
    private String complaint;

    @ManyToOne
    @JoinColumn(name = "GAME_DETAIL_ID")
    private GameDetail gameDetail;
}

