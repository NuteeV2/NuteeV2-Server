package kr.nutee.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import kr.nutee.exception.FileStorageException;
import kr.nutee.exception.MyFileNotFoundException;
import kr.nutee.playload.UploadFileResponse;
import kr.nutee.property.FileStorageProperties;
import kr.nutee.service.FileStorageService;

/*
 * File Storage Service Implements
 * 파일 저장
 * @author choiyk
 */
@Service
public class FileStorageServiceImpl implements FileStorageService{

	private final Path fileStorageLocation;

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /*
     * 파일 하나 저장
     * @param MultipartFile
     * @return 파일 이름, 파일 다운로드 uri, 파일 타입, 파일 사이즈
     */
    @Override
	public UploadFileResponse storeFile(MultipartFile file) {
    	//파일의 이름 중복을 방지하기 위하여 파일 이름 재정의 (현재시간+파일이름)
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileName = sdf.format(timestamp) + StringUtils.cleanPath(file.getOriginalFilename());

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(fileName)
                .toUriString();

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return new UploadFileResponse(null, fileName, fileDownloadUri,
                    file.getContentType(), (int) file.getSize());
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /*
     * 파일 가져오기
     * @param 파일 이름
     * @return 요청한 파일의 resource
     */
    @Override
	public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    /*
     * 파일 삭제
     * @param 파일 이름
     */
    @Override
	public void deleteFile(String fileName) {
    	try {
    		Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
    		Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
    	}catch(IOException e) {
    		throw new FileStorageException("Could not delete file " + fileName + ". Please try again!", e);
    	}
    }

    /*
     * 파일 저장 경로 가져오기
     * @return 파일 저장 경로
     */
    @Override
	public String getFileStorageLocation() {
    	return this.fileStorageLocation.toString();
    }

}
