package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount.FtpAccountCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount.FtpAccountGetDTO;
import com.turkcell.playcell.gamingplatform.cms.service.FtpAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ftp-accounts")
@RequiredArgsConstructor
public class FtpAccountController {

    private final FtpAccountService ftpAccountService;

    @PostMapping("")
    public Long saveFfpAccount(@RequestBody @Valid FtpAccountCreateDTO ftpAccountCreateDTO) {
        return ftpAccountService.saveFfpAccount(ftpAccountCreateDTO);
    }

    @PutMapping("/{id}")
    public void updateFtpAccount(@PathVariable(name = "id") Long id, @RequestBody @Valid FtpAccountCreateDTO ftpAccountCreateDTO) {
        ftpAccountService.updateFtpAccount(id, ftpAccountCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFtpAccount(@PathVariable(name = "id")Long id) {
        ftpAccountService.deleteFtpAccount(id);
    }

    @GetMapping("")
    public List<FtpAccountGetDTO> getFtpAccounts() {
        return ftpAccountService.getFtpAccounts();
    }

}
