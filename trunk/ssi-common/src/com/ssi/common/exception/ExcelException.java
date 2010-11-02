package com.ssi.common.exception;

/**
 * Excel异常类
 * @author bluestome
 *
 */
public class ExcelException extends RuntimeException {

	public static final int CHAPTER_TITLE_EXISTS = 100001;
	public static final int CHAPTER_ORDER_NUM_EXISTS = 100002;
	
	private int errorCode;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6682794186220675767L;

	public ExcelException() {
		super();
	}

	public ExcelException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ExcelException(String message) {
		super(message);
	}

	public ExcelException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ExcelException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
