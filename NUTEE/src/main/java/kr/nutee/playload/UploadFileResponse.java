package kr.nutee.playload;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UploadFileResponse {

	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

}
