package kr.nutee.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Json Util Class
 * @author choiyk
 */
public class JsonUtils {

	/*
	 * Json 객체로 변환
	 * @param Object 객체
	 * @return Object를 Json 형태로 변환하여 반환
	 */
	public static String toJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

}
