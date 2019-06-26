package com.lww.hadoop.outputformat.db;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class Word implements WritableComparable<Word>, DBWritable {

	private String word;
	private int num;
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void write(PreparedStatement statement) throws SQLException {
		statement.setString(1, this.word);
		statement.setInt(2, this.num);
	}

	public void readFields(ResultSet resultSet) throws SQLException {
		this.word = resultSet.getString("word");
		this.num = resultSet.getInt("num");
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(word);
		out.write(num);
	}

	public void readFields(DataInput in) throws IOException {
		this.word = in.readUTF();
		this.num = in.readInt();
	}

	public int compareTo(Word o) {
		return 0;
	}

}
