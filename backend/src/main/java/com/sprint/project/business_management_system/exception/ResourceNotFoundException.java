package com.sprint.project.business_management_system.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(final String message) {
        super(message);
    }

}
