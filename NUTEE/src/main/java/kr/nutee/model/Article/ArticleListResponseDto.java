package kr.nutee.model.Article;

import lombok.Data;

/*
 * Article 전체 List 조회시 데이터를 담을 Dto
 * @author choiyk
 */
@Data
public class ArticleListResponseDto {

	private int id;
	private String title;
	private String dates;
	private int userId;	//익명의 게시글인 경우 스누피로 표기
	private int categoryId;
	private int hits;
	private int report;

}
