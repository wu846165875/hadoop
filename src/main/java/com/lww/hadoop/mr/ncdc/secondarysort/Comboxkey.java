package com.lww.hadoop.mr.ncdc.secondarysort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * 年份和温度的组合key  按照年份升序  气温降序排序
 * 必须有空的构造方法，因为运行过程中会反射创建此对象
 *
 */
public class Comboxkey implements WritableComparable<Comboxkey>{
	
	private int year;
	private int temp;

	public Comboxkey() {
	}

	public Comboxkey(int year, int temp) {
		this.year = year;
		this.temp = temp;
	}
	
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void write(DataOutput out) throws IOException {
		out.writeInt(year);
		out.writeInt(temp);
	}
	public void readFields(DataInput in) throws IOException {
		this.year = in.readInt();
		this.temp = in.readInt();
	}
	//按照年份升序，气温降序进行排序
	public int compareTo(Comboxkey o) {
		if(o.getYear() == this.year) {
			return o.getTemp() - this.temp;
		}else {
			return this.year -o.getYear();
		}
	}

	@Override
	public String toString() {
		return year + "	" + temp;
	}
}
