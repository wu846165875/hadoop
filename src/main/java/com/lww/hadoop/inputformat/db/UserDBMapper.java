package com.lww.hadoop.inputformat.db;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserDBMapper extends Mapper<LongWritable, User, IntWritable, Text> {

	@Override
	protected void map(LongWritable key, User value, Mapper<LongWritable, User, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(new IntWritable(value.getId()), new Text(value.getName()+" " + value.getAge()));
	}


}
