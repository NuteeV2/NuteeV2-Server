package kr.nutee.model.Article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Article 전체 List 조회시 데이터를 담을 Dto
 * @author choiyk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListResponseDto {

	private int id;
	private String title;
	private String dates;
	private int userId;
	private int categoryId;
	private int hits;
	private int report;
	private String nickname;	//익명으로 작성된 게시글의 경우 '스누피'
	private int empathy;

}
