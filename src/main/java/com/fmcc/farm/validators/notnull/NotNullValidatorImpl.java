package com.fmcc.farm.validators.notnull;

import org.springframework.stereotype.Component;

@Component
public class NotNullValidatorImpl implements NotNullValidator{

	@Override
	public Boolean validateNotNull(Object o) throws NullPointerException {
		if(o != null) {
			return new Boolean(true);
		}else {
			throw new NullPointerException();
		}
	}

}
