package june_6_2013;

import java.io.IOException;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MaxLengthWordReducer extends
  	Reducer<Text, IntWritable, Text, IntWritable> {
	
	private int max_length=0;
	private Text max_length_word;
	private int curr_length=0;
	
	

	
	
	public void reduce(Text key,Iterable<IntWritable> values,Context context)
	{
		
		for(IntWritable value : values)
		{
		  curr_length=value.get();	
		if(max_length<curr_length)
		{
			max_length=curr_length;
			max_length_word=key;
		}
		}
	}
	
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
			context.write(max_length_word,new IntWritable(max_length));
			  }

}
