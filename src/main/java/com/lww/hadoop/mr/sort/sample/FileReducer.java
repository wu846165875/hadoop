package com.lww.hadoop.mr.sort.sample;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FileReducer extends Reducer<IntWritable, Text, IntWritable, Text>{

	@Override
	protected void reduce(IntWritable key, Iterable<Text> val,
			Reducer<IntWritable, Text, IntWritable, Text>.Context context) throws IOException, InterruptedException {
		context.write(key, val.iterator().next());
	}


}
