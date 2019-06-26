package com.lww.hadoop.mr.sort.sample;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

/**
 * 使用采样器 实现多个reduce结果的全排序
 * 只能读取sequencefile
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		
		Job job = Job.getInstance();
		job.setJobName("全排序");
		job.setJarByClass(App.class);
		
		FileSystem fs = FileSystem.get(job.getConfiguration());
		fs.delete(new Path("file:///D:/out/sampler"), true);

		// 创建采样器  参数(每个元素被采样的几率,共采取多少样本,切片数)
		InputSampler.Sampler<IntWritable, Text> sampler = new InputSampler.RandomSampler(0.5, 50, 4);
		
		//分区文件写入位置
		TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), new Path("file:///D:/gitdata/sampler/par"));
		
		job.setMapperClass(FileMapper.class);
		job.setReducerClass(FileReducer.class);
		
		//设置输入文件格式类
		job.setInputFormatClass(SequenceFileInputFormat.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);
		
		//设置reduce任务数  不要超过上面采样的切片数，可能会报错，最好才用相同数字
		job.setNumReduceTasks(5);
		
		//设置分区函数类
		job.setPartitionerClass(TotalOrderPartitioner.class);
		
		FileInputFormat.addInputPath(job, new Path("file:///D:/gitdata/sampler/seq.seq"));
		FileOutputFormat.setOutputPath(job, new Path("file:///D:/out/sampler"));
		
		//写分区文件到mapreduce中
		InputSampler.writePartitionFile(job, sampler);
		
		System.exit(job.waitForCompletion(true)?1:0);
	}
}
