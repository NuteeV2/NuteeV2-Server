package kr.nutee.dao;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * articlefile 테이블의 데이터를 담기 위한 DTO 클래스
 * 다른 table과 file 테이블을 연결하는 테이블의 DTO의 경우 FileLink를 implements해야 함
 *
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class ArticleFile extends FileLink{

	private BigInteger id;
	private BigInteger articleId;
	private BigInteger fileId;

}
