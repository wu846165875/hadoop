package com.lww.hadoop.outputformat.db;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 统计单词出现次数
 * 将统计后的结果插入数据库
 *
 */
public class WordCountApp {
	public static void main(String[] args) throws Exception {
		if(args.length != 2) {
			System.err.println("Usage: WordCountApp <input path> <output path>");
			System.exit(-1);
		}
		Job job = Job.getInstance();

		FileInputFormat.addInputPath(job, new Path(args[0]));
		//设置输出格式类
		job.setOutputFormatClass(DBOutputFormat.class);
		DBConfiguration.configureDB(job.getConfiguration(), "com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/bigdata", "root", "root");
		DBOutputFormat.setOutput(job, "words", "word","num");
		//设置mapper类
		job.setMapperClass(WordCountMapper.class);
		//设置reduce类
		job.setReducerClass(WordCountReducer.class);
		
		//设置mapper输出格式
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		//设置reduce输出格式
		job.setOutputKeyClass(Word.class);
		job.setOutputValueClass(NullWritable.class);
		
		System.exit(job.waitForCompletion(true)?1:0);
	}
}
