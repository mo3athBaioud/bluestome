package com.ssi.common.exception;

/**
 * Service异常
 * @author bluestome
 *
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496199208647571574L;
	private int errorCode;
	
	public ServiceException(){
		super();
	}
	
	public ServiceException(int errorCode){
		super();
		this.errorCode = errorCode;
	}
	
	public ServiceException(String message){
		super(message);
	}
	
	public ServiceException(int errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public ServiceException(String message, Throwable cause){
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
