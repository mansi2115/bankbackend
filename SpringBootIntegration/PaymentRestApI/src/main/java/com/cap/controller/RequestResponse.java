package com.cap.controller;



public class RequestResponse {


	private boolean success;
	private Object result;
	private String errors;

	public RequestResponse() {
		super();
	}

	public RequestResponse(boolean success, Object result) {
		this.success = success;
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

}
