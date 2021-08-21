package com.spring5app.animalclinic.rest.api.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-01-19T22:53:48.929Z")

public class ApiException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
    
	public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }

	public int getCode() {
		return code;
	}
}
