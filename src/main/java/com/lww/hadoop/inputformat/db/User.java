package com.lww.hadoop.inputformat.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class User implements DBWritable{
	
	private int id;
	private String name;
	private int age;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void write(PreparedStatement statement) throws SQLException {
		statement.setDouble(1, this.id);
		statement.setString(2, this.name);
		statement.setDouble(3, this.age);
	}

	public void readFields(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt("id");
		this.name = resultSet.getString("name");
		this.age = resultSet.getInt("age");
	}
	
}
