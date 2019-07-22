package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount.FtpAccountCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount.FtpAccountGetDTO;

import java.util.List;

public interface FtpAccountService {
    Long saveFfpAccount(FtpAccountCreateDTO ftpAccountCreateDTO);

    void updateFtpAccount(Long id, FtpAccountCreateDTO ftpAccountCreateDTO);

    void deleteFtpAccount(Long id);

    List<FtpAccountGetDTO> getFtpAccounts();
}
