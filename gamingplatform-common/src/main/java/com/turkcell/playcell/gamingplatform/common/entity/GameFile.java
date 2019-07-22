package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GameFile extends BaseEntity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8753228059431638845L;

    @Column(name = "CDN_URL")
	private String cdnUrl;

    @Column(name = "PATH")
	private String path;

    @ManyToOne
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private FtpAccount ftpAccount;

    public GameFile(Long id) {
        super.setId(id);
    }
}
