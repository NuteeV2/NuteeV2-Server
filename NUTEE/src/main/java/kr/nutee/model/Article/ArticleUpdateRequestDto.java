package kr.nutee.model.Article;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import kr.nutee.dao.File;
import lombok.Data;

/*
 * 게시글 수정을 위한 Dto
 * @author choiyk
 */
@Data
public class ArticleUpdateRequestDto {

	@NotBlank(message="Article title requires not blank value")
	private String title;

	@NotEmpty(message="Article contents cannot be empty")
	private String contents;

	private String anonymous;
	private int categoryId;
	private List<File> fileInfs;

}
