package com.lww.hadoop.mr.grade;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 根据性别，组，求每门课程的平均分
 * 
 *
 */
public class GradeApp {
	public static void main(String[] args) {
		try {
			Job job = Job.getInstance();
			job.setJobName("按组和性别算每门成绩的平局分");
			
			FileSystem fs = FileSystem.get(job.getConfiguration());
			fs.delete(new Path("D:\\out\\grade"),true);
			
			FileInputFormat.addInputPath(job, new Path("D:\\gitdata\\grade\\grade.txt"));
			FileOutputFormat.setOutputPath(job, new Path("D:\\out\\grade"));
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(GradeWritable.class);
			
			job.setMapperClass(GradeMapper.class);
			job.setReducerClass(GradeReduce.class);
			//job.setNumReduceTasks(0);
			System.exit(job.waitForCompletion(true)?1:0);;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
