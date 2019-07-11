package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount.FtpAccountCreateDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.ftpAccount.FtpAccountGetDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.service.FtpAccountService;
import com.turkcell.playcell.gamingplatform.common.entity.FtpAccount;
import com.turkcell.playcell.gamingplatform.common.repository.FtpAccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FtpAccountServiceImpl implements FtpAccountService {

    private final FtpAccountRepository ftpAccountRepository;

    private final ModelMapper modelMapper;

    //@PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Long saveFfpAccount(FtpAccountCreateDTO ftpAccountCreateDTO) {
        FtpAccount ftpAccount = modelMapper.map(ftpAccountCreateDTO, FtpAccount.class);
        Long id = ftpAccountRepository.save(ftpAccount).getId();
        entityManager.flush();
        entityManager.detach(ftpAccount);
        ftpAccountCreateDTO.finalize();
        ftpAccount.finalize();
        return id;
    }

    @Override
    public void updateFtpAccount(Long id, FtpAccountCreateDTO ftpAccountCreateDTO) {
        FtpAccount ftpAccount = ftpAccountRepository.findById(id).orElseThrow( () -> new NotFoundException(FtpAccount.class, id));
        ftpAccount.setName(ftpAccountCreateDTO.getName());
        ftpAccount.setHost(ftpAccountCreateDTO.getHost());
        ftpAccount.setPort(ftpAccountCreateDTO.getPort());
        ftpAccount.setUser(ftpAccountCreateDTO.getUser());
        ftpAccount.setPassword(ftpAccountCreateDTO.getPassword());
        ftpAccount.setPath(ftpAccount.getPath());
        ftpAccount.setCdnBaseUrl(ftpAccountCreateDTO.getCdnBaseUrl());
        entityManager.flush();
        entityManager.detach(ftpAccount);
        ftpAccountCreateDTO.finalize();
        ftpAccount.finalize();
    }

    @Override
    public void deleteFtpAccount(Long id) {
        ftpAccountRepository.deleteById(id);
    }

    @Override
    public List<FtpAccountGetDTO> getFtpAccounts() {
        List<FtpAccount> ftpAccounts = ftpAccountRepository.findAll();
        return ftpAccounts.stream().map(ftpAccount -> modelMapper.map(ftpAccount, FtpAccountGetDTO.class)).collect(Collectors.toList());
    }


}
