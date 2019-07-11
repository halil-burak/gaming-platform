package com.turkcell.playcell.gamingplatform.cms.controller;

import com.turkcell.playcell.gamingplatform.cms.dto.GameFileDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.ImageDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;
import com.turkcell.playcell.gamingplatform.cms.exception.FileUploadException;
import com.turkcell.playcell.gamingplatform.cms.exception.UnzipException;
import com.turkcell.playcell.gamingplatform.cms.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/image")
    public ImageDTO saveImage(@RequestParam(value = "sizeId", required = false) Long sizeId, @RequestParam("ftpAccountId") Long ftpAccountId, @RequestParam("file") MultipartFile file) throws FileUploadException {
        return fileUploadService.saveImage(sizeId, ftpAccountId, file);
    }

    @PostMapping("/gameFile")
    public GameFileDTO saveGameFile(@RequestParam("ftpAccountId") Long ftpAccountId, @RequestParam("file") MultipartFile file) throws FileUploadException, UnzipException, IOException {
        return fileUploadService.saveGameFile(ftpAccountId, file);
    }
    @DeleteMapping("/gameFile/{id}")
    public void deleteGameFile( @PathVariable("id") Long id ) throws FileDeleteException {
        fileUploadService.deleteGameFile(id);
    }

    @DeleteMapping("/image/{id}")
    public void deleteFile(@PathVariable("id") Long id ) throws FileDeleteException {
        fileUploadService.deleteFile(id);
    }

    @DeleteMapping("platform/{platformId}/image/{imageId}/")
    public void deleteFile(@PathVariable("platformId") Long platformId, @PathVariable("imageId") Long imageId) throws FileDeleteException {
        fileUploadService.deleteFile(platformId, imageId);
    }

}
