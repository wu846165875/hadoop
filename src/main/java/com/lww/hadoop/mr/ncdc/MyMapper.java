package com.lww.hadoop.mr.ncdc;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private static final int MISSING = 9999;

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//计数器
		context.getCounter("MyMapper", "setup "+InetAddress.getLocalHost()).increment(1);
	}
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		//计数器
		context.getCounter("MyMapper", "map "+InetAddress.getLocalHost()).increment(1);;
		
		//key值是文本文件中，行数据的偏移量，没有什么实际意义
		//value 是一整行数据
		String line = value.toString();
		String year = line.substring(15,19);
		int airTemperature;
		if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
			airTemperature = Integer.parseInt(line.substring(88, 92));
		} else {
			airTemperature = Integer.parseInt(line.substring(87, 92));
		}
		String quality = line.substring(92, 93);
		if (airTemperature != MISSING && quality.matches("[01459]")) {
			context.write(new Text(year), new IntWritable(airTemperature));
		}
	}
	
	@Override
	protected void cleanup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//计数器
		context.getCounter("MyMapper", "cleanup "+InetAddress.getLocalHost()).increment(1);
	}

}
