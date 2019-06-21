package com.lww.hadoop.mr.grade;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GradeReduce extends Reducer<Text, GradeWritable, Text, GradeWritable> {

	@Override
	protected void reduce(Text key, Iterable<GradeWritable> value,
			Reducer<Text, GradeWritable, Text, GradeWritable>.Context context) throws IOException, InterruptedException {
		Double math = 0.00;
		Double english = 0.00;
		Double chain = 0.00;
		int count = 0;
		for(GradeWritable d : value) {
			math += d.getMath();
			english += d.getEnglish();
			chain += d.getChain();
			count++;
		}
		GradeWritable g = new GradeWritable();
		g.setMath(math/count);
		g.setChain(chain/count);
		g.setEnglish(english/count);
		context.write(key, g);
	}

}
