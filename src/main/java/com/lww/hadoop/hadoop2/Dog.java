package com.lww.hadoop.hadoop2;

import java.io.Serializable;

public class Dog implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7056504246254970038L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dog(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}
	
}
