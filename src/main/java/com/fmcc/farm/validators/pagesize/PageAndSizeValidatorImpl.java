package com.fmcc.farm.validators.pagesize;

import org.springframework.stereotype.Component;

@Component
public class PageAndSizeValidatorImpl implements PageAndSizeValidator{

	@Override
	public Boolean validatePageAndSize(Integer page, Integer size) {
		if(page > 0 && size <= 10 && size > 0) {
			return new Boolean(true);
		} else {
			return new Boolean(false);
		}
	}

}
