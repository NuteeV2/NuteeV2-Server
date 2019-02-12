package kr.nutee.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;

/*
 * SQLException을 위한 Handler
 * @author choiyk
 */
public class CustomDataAccessException extends DataAccessException{

	private static final long serialVersionUID = 1L;

	//Logging
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public CustomDataAccessException(String msg, Throwable e) {
		super(msg, e);

		//DB insert나 update 시 키 제약에 위반할 경우 발생하는 Exception을 위한 Handler
		//무결성 제약 조건 위반(중복항목)
		if(e instanceof DataIntegrityViolationException) {
			logger.debug("DataIntegrityViolationException[중복 항목]: " + e.getMessage());
		}
		//SQL이 유효하지 않은 경우 예외가 발생
		else if (e instanceof BadSqlGrammarException){
			SQLException se = (SQLException)e.getCause();
			int errorCode = se.getErrorCode();

			//알 수 없는 칼럼일 경우 발생
			if(errorCode == 1054) {
				logger.debug("BadSqlGrammarException[UnKnown Column]: " + e.getMessage());
			}
			//문법 오류
			else if(errorCode == 1064) {
				logger.debug("BadSqlGrammarException[SQL Syntax Error]: " + e.getMessage());
			}
			//존재하지 않는 테이블일 경우 발생
			else if(errorCode == 1064) {
				logger.debug("BadSqlGrammarException[Table doesn't exist]: " + e.getMessage());
			}
		}
		//JDBC를 사용하여 데이터베이스에 연결할 수 없는경우
		else if(e instanceof DataAccessResourceFailureException){
			logger.debug("DataAccessResourceFailureException: " + e.getMessage());
		}
		//누군가 DB물고있는경우, 누가 커밋안했을때 - DB락
		else if(e instanceof CannotAcquireLockException){
			logger.debug("CannotAcquireLockException: " + e.getMessage());
		}
		//현재 프로세스가 교착 상태
		else if(e instanceof DeadlockLoserDataAccessException){
			logger.debug("DataIntegrityViolationException: " + e.getMessage());
		}
	}

}
