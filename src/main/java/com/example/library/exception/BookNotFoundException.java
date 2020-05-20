package com.example.library.exception;

/**
 * 
 * @author IBMADMIN
 *
 */
public class BookNotFoundException extends Exception{
	
	String code;
	String description;
	
	public BookNotFoundException(String code) {
		super();
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
