package kr.nutee.dao;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * File 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class File {

	private BigInteger id;
	private String fileName;
	private String fileType;
	private int fileSize;

}
