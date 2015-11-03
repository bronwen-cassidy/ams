package com.xioq.dasacumen.lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Droppable
{
	
//	Class<? extends ScratchpadDraggable> accepts();
	/**
	 * The name of the draggable this droppable field accepts. An initialisation exception
	 * will be thrown if a draggable is not found with the name
	 * @return
	 */
	String accepts();
	
	/**
	 * The name of the field to copy from the draggable. An initialisation exception
	 * will be thrown if the field is not found on the mentioned draggable
	 * @return
	 */
	String fieldToCopy();

	/**
	 * Additional fields on the droppable model to pass the draggable to.
	 * @return
	 */
	String[] deepFields() default {};

	String acceptsModelName() default "";
}
