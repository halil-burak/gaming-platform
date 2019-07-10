package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.jcraft.jsch.*;
import com.turkcell.playcell.gamingplatform.cms.ApplicationProperties;
import com.turkcell.playcell.gamingplatform.cms.dto.GameFileDTO;
import com.turkcell.playcell.gamingplatform.cms.dto.ImageDTO;
import com.turkcell.playcell.gamingplatform.cms.exception.FileDeleteException;
import com.turkcell.playcell.gamingplatform.cms.exception.FileUploadException;
import com.turkcell.playcell.gamingplatform.cms.exception.NotFoundException;
import com.turkcell.playcell.gamingplatform.cms.exception.UnzipException;
import com.turkcell.playcell.gamingplatform.cms.service.FileUploadService;
import com.turkcell.playcell.gamingplatform.common.entity.FtpAccount;
import com.turkcell.playcell.gamingplatform.common.entity.GameDetailTranslation;
import com.turkcell.playcell.gamingplatform.common.entity.GameFile;
import com.turkcell.playcell.gamingplatform.common.entity.Image;
import com.turkcell.playcell.gamingplatform.common.repository.FtpAccountRepository;
import com.turkcell.playcell.gamingplatform.common.repository.GameFileRepository;
import com.turkcell.playcell.gamingplatform.common.repository.ImageRepository;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private GameFileRepository gameFileRepository;

    @Autowired
    private FtpAccountRepository ftpAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ApplicationProperties applicationProperties;

    @Override
    public ImageDTO saveImage(Long sizeId, Long ftpAccountId, MultipartFile file) throws FileUploadException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        String random = UUID.randomUUID().toString();

        String uniqueName = random.concat(extension);
        FtpAccount ftpAccount = ftpAccountRepository.findById(ftpAccountId).orElseThrow(() -> new NotFoundException(Image.class, ftpAccountId));

        String cdnPath = ftpAccount.getPath() == null ? "images/" : ftpAccount.getPath().concat("images/");

        uploadWithFTP(ftpAccount.getHost(), ftpAccount.getPort(), ftpAccount.getUser(), ftpAccount.getPassword(), cdnPath, file , uniqueName);

        Image image = new Image(sizeId, ftpAccount.getCdnBaseUrl(), cdnPath.concat(uniqueName), new FtpAccount(ftpAccountId));
        Image savedImage = imageRepository.save(image);

        ImageDTO imageDTO = modelMapper.map(savedImage, ImageDTO.class);
        imageDTO.setFullPath(image.getCdnUrl().concat(image.getPath()));
        return imageDTO;
    }

    @Override
    public GameFileDTO saveGameFile(Long ftpAccountId, MultipartFile file) throws FileUploadException, UnzipException {
        FtpAccount ftpAccount = ftpAccountRepository.findById(ftpAccountId).orElseThrow(() -> new NotFoundException(Image.class, ftpAccountId));
        String cdnPath = ftpAccount.getPath() == null ? "games" : ftpAccount.getPath().concat("games");
        String directoryName = uploadDirectoryWithFTP(ftpAccount.getHost(), ftpAccount.getPort(), ftpAccount.getUser(), ftpAccount.getPassword(), cdnPath, file);

        GameFile gameFile = new GameFile();
        gameFile.setCdnUrl(ftpAccount.getCdnBaseUrl());
        StringBuilder stringBuilder = new StringBuilder(cdnPath);
        stringBuilder.append("/").append(directoryName).append("/").append("index.html");
        gameFile.setPath(stringBuilder.toString());
        gameFile.setFtpAccount(new FtpAccount(ftpAccountId));

        GameFile savedGameFile = gameFileRepository.save(gameFile);

        GameFileDTO gameFileDTO = new GameFileDTO();
        gameFileDTO.setId(savedGameFile.getId());
        gameFileDTO.setPath(savedGameFile.getCdnUrl().concat(savedGameFile.getPath()));

        return gameFileDTO;
    }

    @Override
    public void deleteGameFile(Long id) throws FileDeleteException {
        GameFile gameFile = gameFileRepository.findById(id).orElseThrow(() -> new NotFoundException(GameFile.class, id));
        FtpAccount ftpAccount = gameFile.getFtpAccount();
        deleteDirectoryWithFTP(ftpAccount.getHost(), ftpAccount.getPort(), ftpAccount.getUser(), ftpAccount.getPassword(), gameFile.getPath().replace("index.html",""));
    }

    @Override
    public void deleteFile(Long id) throws FileDeleteException {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException(Image.class, id));
        FtpAccount ftpAccount = image.getFtpAccount();
        deleteWithFTP(ftpAccount.getHost(), ftpAccount.getPort(), ftpAccount.getUser(), ftpAccount.getPassword(), image.getPath());
        imageRepository.deleteById(id);
    }


    //todo : gamedetails servisine taşınmalı
    //todo : deletefile'la image objesi geçir
    @Override
    public void deleteFile(Long platformId, Long imageId) throws FileDeleteException {
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException(Image.class, imageId));
        Optional<GameDetailTranslation> gameDetailTranslation = image.getGameDetailTranslations().stream().filter(g -> g.getGameDetail().getPlatform().getId().equals(platformId)).findAny();
        if(gameDetailTranslation.isPresent()){
            image.getGameDetailTranslations().remove(gameDetailTranslation);
        }
        if(image.getGameDetailTranslations().isEmpty()){
            deleteFile(imageId);
        }
    }


    private void uploadWithFTP(String serverAddress, Integer serverPort, String username, char[] password, String path,
                               MultipartFile file, String uniqueName) throws FileUploadException {
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, serverAddress, serverPort);
            session.setPassword(new String(password));

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            if(path != null && path.trim() != ""){
                channelSftp.cd(path);
            }
            channelSftp.put(file.getInputStream(), uniqueName, ChannelSftp.OVERWRITE);

        } catch (SftpException | JSchException | IOException e) {
            throw new FileUploadException("Unable to upload file", e);
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
    private String uploadDirectoryWithFTP(String serverAddress, Integer serverPort, String username, char[] password, String path,
                                        MultipartFile file) throws UnzipException, FileUploadException {

        String directoryName = extractFolder(file, applicationProperties.getTmpFolder());
        Session session = null;

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, serverAddress, serverPort);
            session.setPassword(new String(password));

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            recursiveFolderUpload(session, applicationProperties.getTmpFolder().concat(directoryName), path);
            deleteDirectoryStream(applicationProperties.getTmpFolder().concat(directoryName));
            return directoryName;

        } catch (JSchException | IOException e) {
            throw new FileUploadException("Unable to upload file", e);
        }  finally {
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }

    }

    private String extractFolder(MultipartFile multipartFile, String extractFolder) throws UnzipException {

        try {

            File file = new File(extractFolder + multipartFile.getOriginalFilename());
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();


            ZipFile zipFile = new ZipFile(file);
            String randomDirectoryName = UUID.randomUUID().toString();
            zipFile.extractAll(extractFolder + randomDirectoryName);
            file.delete();

            return randomDirectoryName;

        } catch (ZipException | IOException e) {
            throw new UnzipException("An error occurred while unzip", e);
        }


    }


    private void recursiveFolderUpload(Session session, String sourcePath, String destinationPath) throws FileUploadException, FileNotFoundException {

        ChannelSftp channelSftp = null;

        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            File sourceFile = new File(sourcePath);
            if (sourceFile.isFile()) {
                channelSftp.cd(destinationPath);
                if (!sourceFile.getName().startsWith("."))
                    channelSftp.put(new FileInputStream(sourceFile), sourceFile.getName(), ChannelSftp.OVERWRITE);
            } else {
                File[] files = sourceFile.listFiles();
                if (files != null && !sourceFile.getName().startsWith(".")) {
                    channelSftp.cd(destinationPath);
                    channelSftp.mkdir(sourceFile.getName());

                    for (File f: files) {
                        recursiveFolderUpload(session, f.getAbsolutePath(), destinationPath + "/" + sourceFile.getName());
                    }
                }
            }

        } catch (JSchException | SftpException e) {
            throw new FileUploadException("Unable to upload file", e);
        } finally{
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }
        }

    }

    private void deleteDirectoryStream(String path) throws IOException {
        Files.walk(Paths.get(path))
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }


    private void deleteWithFTP(String serverAddress, Integer serverPort, String username, char[] password, String path) throws FileDeleteException {
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, serverAddress, serverPort);
            session.setPassword(new String(password));

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            channelSftp.rm(path);

        } catch (JSchException | SftpException e) {
            throw new FileDeleteException("Unable to delete file", e);
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }

            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }

    private void deleteDirectoryWithFTP(String serverAddress, Integer serverPort, String username, char[] password, String path) throws FileDeleteException {
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, serverAddress, serverPort);
            session.setPassword(new String(password));

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            recursiveDirectoryDelete(channelSftp, path);

        } catch (JSchException | SftpException e) {
            throw new FileDeleteException("Unable to delete file", e);
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) {
                channelSftp.disconnect();
            }

            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }


    private void recursiveDirectoryDelete(ChannelSftp channelSftp,  String remoteDir) throws SftpException {
        if(channelSftp.stat(remoteDir).isDir()) {
            Vector<ChannelSftp.LsEntry> dirList = channelSftp.ls(remoteDir);
            for(ChannelSftp.LsEntry entry : dirList) {
                if(!(entry.getFilename().equals(".") || entry.getFilename().equals(".."))) {
                    if(entry.getAttrs().isDir()) {
                        recursiveDirectoryDelete(channelSftp, remoteDir + entry.getFilename() + File.separator);
                    } else {
                        channelSftp.rm(remoteDir + entry.getFilename());
                    }
                }
            }

            channelSftp.cd("..");
            channelSftp.rmdir(remoteDir);
        }
    }

}