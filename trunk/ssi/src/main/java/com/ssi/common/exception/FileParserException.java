package com.ssi.common.exception;

/**
 * 文件解析失败异常类
 * @author bluestome
 *
 */
public class FileParserException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorCode;
	
	public FileParserException(){
		super();
	}
	
	public FileParserException(int errorCode){
		super();
		this.errorCode = errorCode;
	}
	
	public FileParserException(String message){
		super(message);
	}
	
	public FileParserException(int errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public FileParserException(String message, Throwable cause){
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
