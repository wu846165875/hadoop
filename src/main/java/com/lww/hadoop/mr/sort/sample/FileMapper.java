package com.lww.hadoop.mr.sort.sample;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FileMapper extends Mapper<IntWritable, Text, IntWritable, Text>{

	@Override
	protected void map(IntWritable key, Text value, Mapper<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(key, value);
		context.getCounter("m", "map").increment(1);
	}

}
