package com.fmcc.farm.validators.dtoidpathid;

import org.springframework.stereotype.Component;

@Component
public class PathIdAndDTOIdMatchValidatorImpl implements PathIdAndDTOIdMatchValidator{

	@Override
	public Boolean validateMatchingIds(Integer objectId, Integer pathId) throws NullPointerException{
		if(objectId != null && pathId != null) {
			if(objectId.equals(pathId)) {
				return new Boolean(true);
			}
			else {
				throw new NullPointerException();
			}
		} else {
			throw new NullPointerException();
		}
	}

}
