package kr.nutee.playload;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UploadFileResponse {

	private BigInteger id;
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private int size;

}
