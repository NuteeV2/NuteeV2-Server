package kr.nutee.model.Article;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Article 하나 조회시 데이터를 담을 Dto
 * @author choiyk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDto {

	private BigInteger id;
	private String title;
	private String contents;
	private Timestamp dates;
	private long userId;
	private int categoryId;
	private long hits;
	private int report;
	private String nickname;	//익명으로 작성된 게시글의 경우 '스누피'

}
