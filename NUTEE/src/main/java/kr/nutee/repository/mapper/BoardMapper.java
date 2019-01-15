package kr.nutee.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dto.Board;

@Mapper
public interface BoardMapper {

	List<Board> findAll();

}
