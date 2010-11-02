package com.ssi.common.exception;

/**
 * DAO异常
 * @author bluestome
 *
 */
public class DAOException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496199208647571574L;
	private int errorCode;
	
	public DAOException(){
		super();
	}
	
	public DAOException(int errorCode){
		super();
		this.errorCode = errorCode;
	}
	
	public DAOException(String message){
		super(message);
	}
	
	public DAOException(int errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public DAOException(String message, Throwable cause){
		super(message, cause);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
