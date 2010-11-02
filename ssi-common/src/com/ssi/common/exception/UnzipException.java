package com.ssi.common.exception;

/**
 * UNZIP异常类
 * @author bluestome
 *
 */
public class UnzipException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public UnzipException(){
		super();
	}
	
	public UnzipException(int errorCode){
		super();
		this.errorCode = errorCode;
	}
	
	public UnzipException(String message){
		super(message);
	}
	
	public UnzipException(int errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public UnzipException(String message, Throwable cause){
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
