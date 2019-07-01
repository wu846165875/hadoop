package com.lww.hadoop.mr.ncdc.secondarysort;

import java.io.IOException;
import java.net.InetAddress;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReduce extends Reducer<Comboxkey, NullWritable, Comboxkey, NullWritable>{

	@Override
	protected void reduce(Comboxkey key, Iterable<NullWritable> val,
			Reducer<Comboxkey, NullWritable, Comboxkey, NullWritable>.Context context) throws IOException, InterruptedException {
		context.write(key, NullWritable.get());
	}
	
}
