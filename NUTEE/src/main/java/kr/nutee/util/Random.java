package kr.nutee.util;

public class Random {
	
	public static String makeRandomNumber() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 6; i++) {
		    double dValue = Math.random();
		    sb.append((int)(dValue * 10));
		}
		
		return sb.toString();
	}
}
