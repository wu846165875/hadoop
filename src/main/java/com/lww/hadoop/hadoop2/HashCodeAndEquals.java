package com.lww.hadoop.hadoop2;

import java.util.HashSet;

import org.junit.Test;

public class HashCodeAndEquals {
	//未重写equals和hashCode
	@Test
	public void noOverWrite() {
		Persion p1 = new Persion("张三", 23);
		Persion p2 = new Persion("张三", 23);
		System.out.printf("pi==p2:%s,  p1.equals(p2):%s,  p1.hashCode:%s,  p2.hashCode:%s",p1==p2,p1.equals(p2),p1.hashCode(),p2.hashCode());
		//pi==p2:false,  p1.equals(p2):false,  p1.hashCode:366712642,  p2.hashCode:1829164700
	}
	
	//重写equsl
	@Test
	public void overWriteEquals() {
		Persion p1 = new Persion("张三", 23);
		Persion p2 = new Persion("张三", 23);
		System.out.printf("pi==p2:%s,  p1.equals(p2):%s,  p1.hashCode:%s,  p2.hashCode:%s",p1==p2,p1.equals(p2),p1.hashCode(),p2.hashCode());
		//pi==p2:false,  p1.equals(p2):true,  p1.hashCode:366712642,  p2.hashCode:1829164700
	}
	
	
	//hashset测试，未重写hashcode
	@Test
	public void testHashSet() {
		Persion p1 = new Persion("张三", 23);
		Persion p2 = new Persion("张三", 23);
		HashSet<Persion> set = new HashSet<Persion>();
		set.add(p1);
		set.add(p2);
		System.out.println(set);
		//set集合是根据hashcode判断是否重复 
		//[Persion [name=张三, age=23], Persion [name=张三, age=23]]
	}
	
	//重写hashcode
	/**
	 * @Override
	 *	public int hashCode() {
	 *		return name.hashCode() ^ age;
	 *	}
	 */
	@Test
	public void testHashSetOverHashCode() {
		Persion p1 = new Persion("张三", 23);
		Persion p2 = new Persion("张三", 23);
		HashSet<Persion> set = new HashSet<Persion>();
		set.add(p1);
		set.add(p2);
		System.out.println(set);
		//set集合是根据hashcode判断是否重复 
		//[Persion [name=张三, age=23]]
	}
	
	//重写hashcode
	/**
	 * @Override
	 *	public int hashCode() {
	 *		return name.toUpperCase().hashCode() ^ age;
	 *	}
	 */
	@Test
	public void testHashSetOverHashCode2() {
		Persion p1 = new Persion("aaa", 23);
		Persion p2 = new Persion("aaa", 23);
		Persion p3 = new Persion("AAA", 23);
		HashSet<Persion> set = new HashSet<Persion>();
		set.add(p1);
		set.add(p2);
		set.add(p3);
		System.out.println(set);
		System.out.printf("p1.hashCode():%s,  p2.hashCode():%s,  p3.hashCode():%s",p1.hashCode(),p2.hashCode(),p3.hashCode());
		//set集合是根据hashcode判断是否重复 
		//[Persion [name=张三, age=23]]
	}
	
	@Test
	public void testDog() {
		Dog d1 = new Dog("a");
		Dog d2 = new Dog("a");
		Dog d3 = new Dog("A");
		HashSet<Dog> set = new HashSet<Dog>();
		set.add(d1);
		set.add(d2);
		set.add(d3);
		System.out.println(set);
		System.out.printf("d1.hashCode():%s,  d2.hashCode():%s,  d3.hashCode():%s",d1.hashCode(),d2.hashCode(),d3.hashCode());
	}
}
