package com.imeguras.falter.libs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


public class Annotations {
	/**
	* @author Imeguras
	* @version 0.1.0
	* @since 0.1.0
	**/
	
	//TODO ACTUALLY DO THIS SCHEISSE
	@Retention(RetentionPolicy.SOURCE)
	@Target(ElementType.FIELD)
	public @interface Clamped {
		double min() default 0;
		double max() default 1;
		
	}

	@Retention(RetentionPolicy.SOURCE)
	@Target(ElementType.FIELD)
	public @interface Defaulted {
		double defaulted() default 0;
	}
	//
}
