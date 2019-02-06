package kr.nutee.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.model.CustomResponseBody;

/*
 * Error 발생 시 ResponseEntity 형태로 반환하기 위한 ExceptionHandler
 * @author choiyk
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	//Logging
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * 잘못된 url 요청이 있을 때 발생하는 Exception인 BadREquestException Handler
	 * @param BadRequestException
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<CustomResponseBody> handleBadRequestException(BadRequestException e) {
		logger.info("BadRequestException: {}", e.getMessage());
		CustomResponseBody body = new CustomResponseBody(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.BAD_REQUEST);
	}

	/*
	 * parameter 값이 invalid한 경우 발생하는 Exception을 위한 Handler
	 * @param MethodArgumentNotValidException
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<CustomResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		logger.debug("MethodArgumentNotValidException: {}", errorMessage);
		CustomResponseBody body = new CustomResponseBody(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.UNPROCESSABLE_ENTITY);
	}

/*
	 * DB insert나 update 시 키 제약에 위반할 경우 발생하는 Exception을 위한 Handler
	 * @param DataIntegrityViolationException
	 * @return ResponseEntity<CustomResponseBody>

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<CustomResponseBody> handleDataIntegrityViolationException(DataIntegrityViolationException e){
		String errorMessage = e.getMessage();
		logger.debug("DataIntegrityViolationException: {}", errorMessage);
		CustomResponseBody body = new CustomResponseBody(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.UNPROCESSABLE_ENTITY);
	}*/

	/*
	 * SQLException을 위한 Handler
	 * @param DataAccessException
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@ExceptionHandler(value = DataAccessException.class)
	public ResponseEntity<CustomResponseBody> handleDataAccessException(DataAccessException e){
		String errorMessage = null;

		//DB insert나 update 시 키 제약에 위반할 경우 발생하는 Exception을 위한 Handler
		//무결성 제약 조건 위반(중복항목)
		if(e instanceof DataIntegrityViolationException) {
			errorMessage = "DataIntegrityViolationException[붕복 항목]: " + e.getMessage();
			logger.debug(errorMessage);
		}
		//SQL이 유효하지 않은 경우 예외가 발생
		else if (e instanceof BadSqlGrammarException){
			SQLException se = (SQLException)e.getRootCause();
			int errorCode = se.getErrorCode();

			//알 수 없는 칼럼일 경우 발생
			if(errorCode == 1054) {
				errorMessage = "BadSqlGrammarException[UnKnown Column]: " + e.getMessage();
				logger.debug(errorMessage);
			}
			//문법 오류
			else if(errorCode == 1064) {
				errorMessage = "BadSqlGrammarException[SQL Syntax Error]: " + e.getMessage();
				logger.debug(errorMessage);
			}
			//존재하지 않는 테이블일 경우 발생
			else if(errorCode == 1064) {
				errorMessage = "BadSqlGrammarException[Table doesn't exist]: " + e.getMessage();
				logger.debug(errorMessage);
			}
		}
		//JDBC를 사용하여 데이터베이스에 연결할 수 없는경우
		else if(e instanceof DataAccessResourceFailureException){
			errorMessage = "DataAccessResourceFailureException: " + e.getMessage();
			logger.debug(errorMessage);
		}
		//누군가 DB물고있는경우, 누가 커밋안했을때 - DB락
		else if(e instanceof CannotAcquireLockException){
			errorMessage = "CannotAcquireLockException: " + e.getMessage();
			logger.debug(errorMessage);
		}
		//현재 프로세스가 교착 상태
		else if(e instanceof DeadlockLoserDataAccessException){
			errorMessage = "DataIntegrityViolationException: " + e.getMessage();
			logger.debug(errorMessage);
		}

		CustomResponseBody body = new CustomResponseBody(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
