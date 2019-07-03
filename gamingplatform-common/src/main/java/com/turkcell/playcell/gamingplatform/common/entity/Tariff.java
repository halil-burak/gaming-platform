package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Tariff extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2322514156659774504L;

	@Column(name = "NAME")
    @NotNull @NotEmpty
    private String name;

    @NotNull
    @Column(name = "GRADE")
    private int grade;

    @Column(name = "LABEL")
    private String label = "";

    @Column(name = "PACKAGE_NAME")
    private String packageName = "";

    @Column(name = "OFFER_ID")
    private Long offerId = 0L;

    @Column(name = "PROVISIONING_ID")
    private Long provisioningId = 0L;

    @ManyToMany(mappedBy="tariffs")
    private List<GameDetail> gameDetails = new ArrayList<>();

    public Tariff(Long id) {
        super.setId(id);
    }
}
