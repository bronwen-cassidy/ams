package com.xioq.dasacumen.lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that a class and field are draggable
 * @author Stephen Elliott
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Draggable
{
	/**
	 * By default will be the name of the field or type. But can be overridden to 
	 * give a specific name
	 * @return
	 */
	String[] names() default {};
}
