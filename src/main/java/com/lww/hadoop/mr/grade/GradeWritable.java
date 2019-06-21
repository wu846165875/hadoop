package com.lww.hadoop.mr.grade;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class GradeWritable implements WritableComparable<GradeWritable>{

	private double english;
	private double math;
	private double chain;
	
	
	
	public double getEnglish() {
		return english;
	}

	public void setEnglish(double english) {
		this.english = english;
	}

	public double getMath() {
		return math;
	}

	public void setMath(double math) {
		this.math = math;
	}

	public double getChain() {
		return chain;
	}

	public void setChain(double chain) {
		this.chain = chain;
	}
	
	@Override
	public String toString() {
		return "GradeWritable [english=" + english + ", math=" + math + ", chain=" + chain + "]";
	}

	public void write(DataOutput out) throws IOException {
		out.writeDouble(this.math);
		out.writeDouble(this.chain);
		out.writeDouble(this.english);
	}

	public void readFields(DataInput in) throws IOException {
		this.math = in.readDouble();
		this.chain = in.readDouble();
		this.english = in.readDouble();
	}

	public int compareTo(GradeWritable o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
