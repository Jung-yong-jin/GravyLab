package co.kr.gravy.common.util;

public class ClassUtil {

	public static String getClassInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("ClassFileName : " + getClassFileName());
		sb.append(System.getProperty("line.separator"));
		sb.append("ClassName : " + getClassName());
		sb.append(System.getProperty("line.separator"));
		sb.append("MethodName : " + getMethodName());
		sb.append(System.getProperty("line.separator"));
		sb.append("LineNumber : " + getLineNumber());
		return sb.toString();
	}
	
	
	/**
	* 실행중인 클래스 이름을 취득
	* @return 클래스명
	*/
	public static String getClassName() {
		return Thread.currentThread().getStackTrace()[3].getClassName();
	}
	
	/**
	* 실행중인 함수를 취득。
	* @return 함수명
	*/
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}
	
	/**
	* 실행중인 클래스 파일 이름을 취득。
	* @return 함수명
	*/
	public static String getClassFileName() {
		return Thread.currentThread().getStackTrace()[3].getFileName();
	}
	
	/**
	* 실행중인 라인 번호。
	* @return 함수명
	*/
	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[3].getLineNumber();
	}
}
