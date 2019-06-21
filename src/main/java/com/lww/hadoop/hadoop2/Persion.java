package com.lww.hadoop.hadoop2;

public class Persion {
	private String name;
	private int age;
	
	@Override
	public String toString() {
		return "Persion [name=" + name + ", age=" + age + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Persion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Persion(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(!(obj instanceof Persion)) {
			return false;
		}
		Persion s = (Persion) obj;
		if(s.getName() != null && s.getName().equals(name) && s.getAge()==age) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		int hash = name.toUpperCase().hashCode();
		return hash ^ age;
	}
	
	
}
