package com.lww.hadoop.mr.wordcount2;

import java.io.IOException;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<NullWritable, BytesWritable, Text, IntWritable>{

	@Override
	protected void map(NullWritable key, BytesWritable value,
			Mapper<NullWritable, BytesWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
			byte[] bytes = value.copyBytes();
			String str = new String(bytes);
			String[] values = str.replaceAll("\r\n", " ").split(" ");
			Text k = new Text();
			IntWritable v = new IntWritable(1);
			for(String s : values) {
				k.set(s);
				context.write(k, v);
			}
	}

	
}
