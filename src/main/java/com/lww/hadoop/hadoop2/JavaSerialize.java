package com.lww.hadoop.hadoop2;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class JavaSerialize {

	/**
	 * 对象序列化
	 */
	@Test
	public void serialize() throws Exception, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:\\test.txt"));
		oos.writeObject(new Dog("大黄"));
		oos.close();
		System.out.println("序列化成功！");
	}
	
	/**
	 * 对象反序列化
	 */
	@Test
	public void deSerialize() throws Exception, IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:\\test.txt"));
		Dog dog = (Dog) ois.readObject();
		System.out.println(dog.getName());
		System.out.println("反序列化成功！");
	}
	
	/**
	 * 字节数组输出流
	 */
	@Test
	public void serialize2() throws Exception, IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(100);
		bos.close();
		byte[] by = bos.toByteArray();
		System.out.println(by.length);
	}
	
}
