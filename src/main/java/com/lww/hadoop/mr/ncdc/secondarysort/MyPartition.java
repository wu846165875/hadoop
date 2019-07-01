package com.lww.hadoop.mr.ncdc.secondarysort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartition extends Partitioner<Comboxkey, NullWritable>{

	@Override
	public int getPartition(Comboxkey key, NullWritable value, int numPartitions) {
		return key.getYear() % numPartitions;
	}

}
