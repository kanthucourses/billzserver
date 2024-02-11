package com.billing.exception;
/**
 * Base exception class for the entire application
 *
 * @author Malleswari
 *
 */

public class AppException extends Exception {

	private String errorCode;

	public AppException(String errCode, String errMsg){
		super(errMsg);
		this.errorCode = errCode;
	}

	public AppException(String errCode, String errMsg, Throwable e){
		super(errMsg, e);
		this.errorCode = errCode;
	}

	public AppException(Throwable e){
		super(e);
	}

	public String getErrorCode() {
		return errorCode;
	}
}

