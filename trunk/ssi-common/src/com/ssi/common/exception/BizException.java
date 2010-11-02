package com.ssi.common.exception;

/**
 * 业务异常类
 * @author bluestome
 *
 */
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496199208647571574L;
	private int errorCode;
	
	public BizException(){
		super();
	}
	
	public BizException(int errorCode){
		super();
		this.errorCode = errorCode;
	}
	
	public BizException(String message){
		super(message);
	}
	
	public BizException(int errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public BizException(String message, Throwable cause){
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
