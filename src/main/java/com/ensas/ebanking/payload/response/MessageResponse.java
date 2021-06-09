package com.ensas.ebanking.payload.response;

public class MessageResponse {
	private String message;
	private Object obj;

	public MessageResponse(String message,Object obj) {
	    this.obj = obj;
		this.message = message;
	  }

	 public MessageResponse(String message) {
		this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
