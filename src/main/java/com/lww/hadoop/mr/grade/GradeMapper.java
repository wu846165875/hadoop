package com.lww.hadoop.mr.grade;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GradeMapper extends Mapper<LongWritable, Text, Text, GradeWritable> {

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, GradeWritable>.Context context)
			throws IOException, InterruptedException {
		String[] arrs = value.toString().split("\t");
		GradeWritable grad = new GradeWritable();
		grad.setChain(Double.parseDouble(arrs[2]));
		grad.setMath(Double.parseDouble(arrs[3]));
		grad.setEnglish(Double.parseDouble(arrs[4]));
		context.write(new Text(arrs[0]+"."+arrs[1]), grad);
	}

}
