package com.cking.phss.xml.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

 
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlTag {
	String name();
	boolean hasSubTag() default true;
	boolean isListWithoutGroupTag() default false;
}
