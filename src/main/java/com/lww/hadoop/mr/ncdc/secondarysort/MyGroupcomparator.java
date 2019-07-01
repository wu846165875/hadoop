package com.lww.hadoop.mr.ncdc.secondarysort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 按年份分组
 * 一定要加组合key的构造方法 要不然无法实例化
 *
 */
public class MyGroupcomparator extends WritableComparator{
	

	public MyGroupcomparator() {
		super(Comboxkey.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		Comboxkey k1 = (Comboxkey) a;
		Comboxkey k2 = (Comboxkey) b;
		return k1.getYear() - k2.getYear();
	}

}
