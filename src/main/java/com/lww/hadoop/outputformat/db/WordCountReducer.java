package com.lww.hadoop.outputformat.db;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Word, NullWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> value,
			Reducer<Text, IntWritable, Word, NullWritable>.Context context) throws IOException, InterruptedException {
		int count = 0;
		for(IntWritable in : value) {
			count += in.get();
		}
		Word w = new Word();
		w.setNum(count);
		w.setWord(key.toString());
		context.write(w, NullWritable.get());
	}

}
