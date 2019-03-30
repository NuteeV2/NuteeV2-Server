package kr.nutee.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dao.ArticleFile;
import kr.nutee.dao.File;
import kr.nutee.dao.FileLink;
import kr.nutee.repository.mapper.ArticleFileMapper;
import kr.nutee.service.FileLinkService;

/*
 * ArticleFile Service
 * FileLink Service 구현 클래스
 * @author choiyk
 */
@Service
public class ArticleFileServiceImpl implements FileLinkService{

	@Autowired
	ArticleFileMapper articleFileMapper;

	@Override
	public List<File> findAll(BigInteger colId) {
		return articleFileMapper.findAllByArticleId(colId);
	}

	@Override
	public void insert(FileLink fileLink) {
		articleFileMapper.insert((ArticleFile) fileLink);
	}

	@Override
	public void deleteAll(BigInteger colId) {
		articleFileMapper.deleteAll(colId);
	}

}
