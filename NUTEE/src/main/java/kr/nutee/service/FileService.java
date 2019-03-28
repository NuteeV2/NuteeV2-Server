package kr.nutee.service;

import java.math.BigInteger;

import kr.nutee.dao.File;

/*
 * File Service Interface
 *
 * @author choiyk
 */
public interface FileService {

	File findOne(BigInteger id);
	BigInteger insertAndGetId(File file);
	void delete(BigInteger id);

}
