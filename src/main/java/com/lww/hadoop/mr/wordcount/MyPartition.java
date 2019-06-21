package com.lww.hadoop.mr.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition extends Partitioner<Text, IntWritable>{

	@Override
	public int getPartition(Text key, IntWritable value, int numPartitions) {
		// return 0  让所有的数据都在第一个分区，
		//这样会造成数据倾斜，实际开发中尽可能避免这种情况
		//最好使用key进行一些运算，返回分区
		return 0;
	}

}
