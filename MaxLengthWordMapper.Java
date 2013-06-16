package june_6_2013;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxLengthWordMapper extends
  	Mapper<LongWritable, Text, Text, IntWritable> {
	
	private int max_length=0;
	private String max_length_word;
	private int curr_length=0;
	private String curr_length_word;
	
	StringTokenizer st;
	
	
	public void map(LongWritable key,Text value,Context context)
	{
		st=new StringTokenizer(value.toString());
		while(st.hasMoreTokens())
		{
		 curr_length_word=st.nextToken();
		 curr_length=curr_length_word.length();	
		if(max_length<curr_length)
		{
			max_length=curr_length;
			max_length_word=curr_length_word;
		}
		}
	}
	
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
			context.write(new Text(max_length_word),new IntWritable(max_length));
			  }
		
}
