package com.testyantra.datatransfer.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponceDto {
	
	
	/**
	 * to check the exception present or not returns true if error present or else
	 * false
	 */
	private boolean error;

	/** to store the Object which is set by Dto object */
	private Object data;

	
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	

}
