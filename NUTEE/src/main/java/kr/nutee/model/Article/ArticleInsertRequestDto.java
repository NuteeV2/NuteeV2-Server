package kr.nutee.model.Article;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/*
 * 게시글 작성 시 사용할 Dto
 * @author choiyk
 */
@Data
public class ArticleInsertRequestDto {

	@NotBlank(message="Article title requires not blank value")
	private String title;

	@NotEmpty(message="Article contents cannot be empty")
	private String contents;

	@NotNull(message="User id cannot be null")
	private int userId;

	@NotNull(message="Board id cannot be null")
	private int boardId;

	private String anonymous;
	private int categoryId;

}
