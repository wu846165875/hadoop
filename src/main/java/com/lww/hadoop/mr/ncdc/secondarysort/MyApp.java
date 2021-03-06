package com.lww.hadoop.mr.ncdc.secondarysort;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 美国气象局数据 统计每年最高气温
 *
 */
public class MyApp {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: OldMaxTemperature <input path> <output path>");
			System.exit(-1);
		}
		
		Job job = Job.getInstance();
		job.setJobName("历年最高气温");
		job.setJarByClass(MyApp.class);
		
		FileSystem fs = FileSystem.get(job.getConfiguration());
		fs.delete(new Path(args[1]),true);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));		//输入路径
		FileOutputFormat.setOutputPath(job, new Path(args[1])); 	//输出路径
		
		job.setMapperClass(MyMapper.class); 						//自定义的Mapper类
		job.setReducerClass(MyReduce.class);						//自定义的Reduce类
		job.setOutputKeyClass(Comboxkey.class);						//输出Key类型、
		job.setOutputValueClass(NullWritable.class);				//输出value类型
		
		job.setPartitionerClass(MyPartition.class); 				//设置分区类
		job.setGroupingComparatorClass(MyGroupcomparator.class);	//设置分组排序类
		
		job.setNumReduceTasks(2);									//设置reduce任务数
		
		System.exit(job.waitForCompletion(true)?1:0);
	}
}
