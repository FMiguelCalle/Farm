package com.fmcc.farm.validators.notnull;

public interface NotNullValidator {

	Boolean validateNotNull(Object o) throws NullPointerException;
	
}
