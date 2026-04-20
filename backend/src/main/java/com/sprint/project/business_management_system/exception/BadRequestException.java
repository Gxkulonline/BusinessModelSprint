package com.sprint.project.business_management_system.exception;

public class BadRequestException extends RuntimeException{
	public BadRequestException(final String message) {
        super(message);
    }

}
