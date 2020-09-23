package com.agree.util;

import java.util.Collection;

public abstract class Assert {

	
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}


	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}


	
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (Object element : array) {
				if (element == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	
	public static void noNullElements(Object[] array) {
		noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}

	
	public static void isAssignable(Class<?> superType, Class<?> subType) {
		isAssignable(superType, subType, "");
	}


	public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
		notNull(superType, "Type to check against must not be null");
		if (subType == null || !superType.isAssignableFrom(subType)) {
			throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
		}
	}

	public static void state(boolean expression, String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}

	public static void state(boolean expression) {
		state(expression, "[Assertion failed] - this state invariant must be true");
	}
	
	public static void is64Size(int [] src){
		is64Size(src,"[Assertion failed] - this array length must be 64");
	}
	
	public static void is64Size(int [] src,String message){
		notNull(src);
		if (src.length != 64){
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notEmpty(Collection<? extends Object> c){
		notEmpty(c,"[Assertion failed] - this collection must be not empty");
	}
	
	public static void notEmpty(Collection<? extends Object> c,String message){
		if (c.isEmpty()){
			throw new IllegalArgumentException(message);
		}
	}
}
