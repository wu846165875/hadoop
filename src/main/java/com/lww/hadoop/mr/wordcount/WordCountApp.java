package com.lww.hadoop.mr.wordcount;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 统计单词出现次数
 *
 */
public class WordCountApp {
	public static void main(String[] args) throws Exception {
		if(args.length != 2) {
			System.err.println("Usage: WordCountApp <input path> <output path>");
			System.exit(-1);
		}
		Job job = Job.getInstance();

		FileSystem fs = FileSystem.get(job.getConfiguration());
		fs.delete(new Path(args[1]),true);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setCombinerClass(WordCountReducer.class);
		System.exit(job.waitForCompletion(true)?1:0);
	}
}
