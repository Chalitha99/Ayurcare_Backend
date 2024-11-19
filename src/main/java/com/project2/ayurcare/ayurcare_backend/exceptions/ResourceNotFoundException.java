package com.project2.ayurcare.ayurcare_backend.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	//for the error

	public ResourceNotFoundException(String message) {
		super(message);
	}
	//this method goig to give the error message if there are no any users with required uid
}
