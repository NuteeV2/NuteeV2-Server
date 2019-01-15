package kr.nutee.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dto.Board;

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

}
