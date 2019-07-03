package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public class GameFile extends BaseEntity implements Serializable{

    private String cdnUrl;
    private String path;

    @ManyToOne
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private FtpAccount ftpAccount;

    public GameFile(Long id) {
        super.setId(id);
    }
}
