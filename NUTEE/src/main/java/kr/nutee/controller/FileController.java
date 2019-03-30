package kr.nutee.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.nutee.dao.File;
import kr.nutee.playload.UploadFileResponse;
import kr.nutee.service.impl.FileServiceImpl;
import kr.nutee.service.impl.FileStorageServiceImpl;

@RestController
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageServiceImpl fileStorageService;
    private final FileServiceImpl fileService;

    @Autowired
	public FileController(FileStorageServiceImpl fileStorageService, FileServiceImpl fileService) {
		this.fileStorageService = fileStorageService;
		this.fileService = fileService;
	}

    /*
     * 파일 업로드
     */
    @PostMapping("uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    	UploadFileResponse ufr = fileStorageService.storeFile(file);
    	BigInteger id = fileService.insertAndGetId(new File(null, ufr.getFileName(), ufr.getFileType(), ufr.getSize()));
    	ufr.setId(id);
    	return ufr;
    }

    /*
     * 파일 여러개 업로드
     */
    @PostMapping("uploadMultipleFiles")
    public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return new ResponseEntity<>(
        		Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList()), HttpStatus.CREATED);
    }

    /*
     * 파일 다운로드
     */
    @GetMapping("downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /*
     * 파일 삭제
     */
    @DeleteMapping("files/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") BigInteger id){
    	File file = fileService.findOne(id);
		fileStorageService.deleteFile(file.getFileName());
		fileService.delete(id);
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}

}
