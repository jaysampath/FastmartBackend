package com.services.fastmart.helpers;

public class ResponseJson {
	
    private int status;
	
	private String message;
	
	private String timeStamp;
	
	public ResponseJson() {
		
	}

	public ResponseJson(int status, String message, String timeStamp) {
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "ResponseJson [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}
    
	
	

}
