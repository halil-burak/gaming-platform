package com.turkcell.playcell.gamingplatform.common.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image extends BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1935992408851076922L;
	private Long sizeId;
    private String cdnUrl;
    private String path;

    @ManyToOne
    @JoinColumn(name = "FTP_ACCOUNT_ID")
    private FtpAccount ftpAccount;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GAME_DETAIL_TRANSLATION_IMAGE",
            joinColumns = @JoinColumn(name = "IMAGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GAME_DETAIL_TRANSLATION_ID"))
    private List<GameDetailTranslation> gameDetailTranslations = new ArrayList<>();
}
