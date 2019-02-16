package kr.nutee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dao.Comment;
import kr.nutee.repository.mapper.RecommentMapper;
import kr.nutee.service.RecommentService;

/**
 * RecommentService Implementation
 */
@Service
public class RecommentServiceImpl implements RecommentService {

	private RecommentMapper recommentMapper;

	@Autowired
	public RecommentServiceImpl(final RecommentMapper recommentMapper) {
		this.recommentMapper = recommentMapper;
	}

	@Override
	public void create(final Comment recomment) {
		recommentMapper.create(recomment);
	}

	@Override
	public void update(Comment recomment) {
		recommentMapper.update(recomment);
	}

	@Override
	public void delete(int id) {
		recommentMapper.delete(id);
	}

}
