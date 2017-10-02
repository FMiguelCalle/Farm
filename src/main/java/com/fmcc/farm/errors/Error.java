package com.fmcc.farm.errors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fmcc.farm.dto.MessageErrorDTO;

@ControllerAdvice(basePackages="com.fmcc.farm.controller")
@ResponseBody
public class Error {

	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NullPointerException.class)
	public MessageErrorDTO NullPointerException(NullPointerException e) {
		return new MessageErrorDTO("Bad request.");
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public MessageErrorDTO ConstraintViolationException(ConstraintViolationException e) {
		return new MessageErrorDTO("No nullable field.");
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(org.springframework.orm.ObjectOptimisticLockingFailureException.class)
	public MessageErrorDTO objectOptimisticLockingFailureException(org.springframework.orm.ObjectOptimisticLockingFailureException e) {
		return new MessageErrorDTO("Bad request.");
	}
}
