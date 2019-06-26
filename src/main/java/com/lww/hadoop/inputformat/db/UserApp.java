package com.lww.hadoop.inputformat.db;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.lww.hadoop.mr.grade.GradeMapper;


/**
 * 使用 DBInputFormat 读取mysql数据
 *
 */
public class UserApp {
	public static void main(String[] args) {
		try {
			Job job = Job.getInstance();
			job.setJobName("读取mysql用户表数据");
			
			FileSystem fs = FileSystem.get(job.getConfiguration());
			fs.delete(new Path("D:\\output\\user"),true);
			
			FileOutputFormat.setOutputPath(job, new Path("D:\\output\\user"));
			job.setInputFormatClass(DBInputFormat.class);
			
			DBConfiguration.configureDB(job.getConfiguration(), "com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/bigdata?serverTimezone=UTC","root","root");
			DBInputFormat.setInput(job, User.class, "select id,name,age from user", "select count(*) from user");
			
			job.setMapperClass(UserDBMapper.class);
			
			
			job.setOutputKeyClass(IntWritable.class);
			job.setOutputValueClass(Text.class);

			//job.setNumReduceTasks(0);
			System.exit(job.waitForCompletion(true)?1:0);;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
