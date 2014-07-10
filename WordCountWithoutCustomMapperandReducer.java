package com.pvn;

//Hadoop : A Wordcount Without Explicit Mapper/Reducer

//Source: http://bigdatacircus.com/2012/06/15/hadoop-a-wordcount-without-explicit-mapperreducer/

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.map.TokenCounterMapper;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountWithoutCustomMapperandReducer extends Configured implements Tool{
  public static void main(String[] args) throws Exception {
    //Configuration configuration = new Configuration();
    ToolRunner.run(new WordCountWithoutCustomMapperandReducer(),args);
  }
 
  @Override
  public int run(String[] arg0) throws Exception {
    Job job = new Job();
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.setJarByClass(WordCountWithoutCustomMapperandReducer.class);
    job.setMapperClass(TokenCounterMapper.class);
    job.setReducerClass(IntSumReducer.class);
    FileInputFormat.addInputPath(job, new Path(arg0[0]));
    FileOutputFormat.setOutputPath(job,new Path(arg0[1]));

	 
    System.exit(job.waitForCompletion(true) ? 0:1); 
	 boolean success = job.waitForCompletion(true);
	 return success ? 0 : 1;
  }
}
