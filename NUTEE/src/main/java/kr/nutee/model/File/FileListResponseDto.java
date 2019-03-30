package kr.nutee.model.File;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Table의 해당 column에 첨부된 File 전체 List 조회시 데이터를 담을 Dto
 * @author choiyk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileListResponseDto {

	private BigInteger id;
	private String fileName;
	private String fileType;

}
