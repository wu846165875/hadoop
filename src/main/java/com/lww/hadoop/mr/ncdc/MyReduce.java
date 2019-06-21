package com.lww.hadoop.mr.ncdc;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReduce extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//计数器
		context.getCounter("MyReduce", "setup "+InetAddress.getLocalHost()).increment(1);
	}
	@Override
	protected void reduce(Text key, Iterable<IntWritable> val,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		//计数器
		context.getCounter("MyReduce", "reduce "+InetAddress.getLocalHost()).increment(1);;
		
		int max = Integer.MIN_VALUE;
		for(IntWritable in : val) {
			if(max<in.get()) {
				max = in.get();
			}
		}
		context.write(key, new IntWritable(max));
	}
	
	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		//计数器
		context.getCounter("MyReduce", "cleanup "+InetAddress.getLocalHost()).increment(1);
	}

}
