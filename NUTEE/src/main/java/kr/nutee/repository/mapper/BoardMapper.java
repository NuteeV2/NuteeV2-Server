package kr.nutee.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.nutee.dao.Board;
import kr.nutee.model.Board.BoardInsertAndUpdateRequestDto;

/*
 * Board Mapper Interface
 * @author choiyk
 */
@Mapper
public interface BoardMapper {

	//전체 게시판 조회
	List<Board> findAll();

	//해당 id에 해당하는 게시판 조회
	Board findOne(int id);

	//게시판 추가
	void insert(BoardInsertAndUpdateRequestDto board);

	//게시판 삭제
	void delete(int id);

	//게시판 이름 변경
	void update(@Param("id") int id, @Param("board") BoardInsertAndUpdateRequestDto board);

}
