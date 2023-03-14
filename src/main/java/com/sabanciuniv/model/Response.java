package com.sabanciuniv.model;

public class Response <T> {
	String message;
	T data;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}

	public Response(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
