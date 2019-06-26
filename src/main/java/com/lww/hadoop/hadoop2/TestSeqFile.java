package com.lww.hadoop.hadoop2;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.hadoop.io.SequenceFile.Reader;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

public class TestSeqFile {
	
	/**
	 * 写入sequenceFile
	 * 文件存储的值类型为key-value
	 */
	@Test
	public void testWrite() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.101:8020");
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path("/user/sequencefile.seq");
		IntWritable key = new IntWritable();
		Text val = new Text();
		Writer writer = SequenceFile.createWriter(fs, conf, path, key.getClass(), val.getClass());
		key.set(1);
		val.set("lalala");
		writer.append(key,val);
		writer.close();
	}
	
	
	/**
	 * 读取sequenceFile文件
	 * @throws Exception 
	 */
	@Test
	public void testReader() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.101:8020");
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path("/user/sequencefile.seq");
		Reader reader = new SequenceFile.Reader(fs,path,conf);	
		IntWritable key = new IntWritable();
		Text val = new Text();
		while (reader.next(key, val)) {
			System.out.println(key+"="+val);
		}
		reader.close();
	}
	
	
	/**
	 * 读取sequenceFile文件
	 * 获取压缩类型，压缩格式
	 */
	@Test
	public void readerType() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.101:8020");
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path("/user/sequencefile.seq");
		Reader reader = new SequenceFile.Reader(fs,path,conf);	
		//压缩格式
		CompressionCodec codec = reader.getCompressionCodec();
		//获取压缩类型
		CompressionType tpe = reader.getCompressionType();
		//获取建值对类型
		Class key = reader.getKeyClass();
		Class value = reader.getValueClass();
		//获取定位
		long pos = reader.getPosition();
		//获取最近的一个同步点
		reader.sync(pos);
	}
	
	
	/**
	 * 写入sequenceFile
	 * 文件存储的值类型为key-value
	 * 使用block压缩和bzip2压缩
	 */
	@Test
	public void writeWithBlockAndBzip2() throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.100.101:8020");
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path("/user/sequencefile2.seq");
		IntWritable key = new IntWritable();
		Text val = new Text();
		CompressionCodec codec = ReflectionUtils.newInstance(BZip2Codec.class, conf);
		Writer writer = SequenceFile.createWriter(conf, fs.create(path), key.getClass(), val.getClass(), CompressionType.BLOCK, codec);
		key.set(1);
		val.set("lalala");
		writer.append(key,val);
		writer.close();
	}
	
	@Test
	public void wirteSeqFile()  throws Exception {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path("d://gitdata//sampler/seq.seq");
		IntWritable key = new IntWritable();
		Text value = new Text();
		CompressionCodec codec = ReflectionUtils.newInstance(BZip2Codec.class, conf);
		Writer write = SequenceFile.createWriter(fs, conf, path, key.getClass(), value.getClass(), CompressionType.NONE, codec);
		for(int i = 1;i<=100;i++) {
			key.set(i);
			value.set("test"+i);
			write.append(key, value);
		}
		write.close();
	}
	
}
