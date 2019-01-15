package kr.nutee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dto.Board;
import kr.nutee.repository.mapper.BoardMapper;
import kr.nutee.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<Board> findAll() {
		return boardMapper.findAll();
	}

}
