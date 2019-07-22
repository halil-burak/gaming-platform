package com.turkcell.playcell.gamingplatform.cms.service;

import com.turkcell.playcell.gamingplatform.cms.dto.GameFileDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.ImageDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;
import com.turkcell.playcell.gamingplatform.cms.exception.FileUploadException;
import com.turkcell.playcell.gamingplatform.cms.exception.UnzipException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    void deleteFile(Long id) throws FileDeleteException;

    void deleteFile(Long platformId, Long imageId) throws FileDeleteException;

    ImageDTO saveImage(Long sizeId, Long ftpAccountId, MultipartFile file) throws FileUploadException;

    GameFileDTO saveGameFile(Long ftpAccountId, MultipartFile file) throws FileUploadException, IOException, UnzipException;

    void deleteGameFile(Long id) throws FileDeleteException;
}
