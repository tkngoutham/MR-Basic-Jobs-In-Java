package june_6_2013;



import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class MaxLengthWordJob {

  /**
	 * @param args
	 */
	public static void main(String[] args)
	throws Exception
	{
	
		Configuration conf=new Configuration();
		if(args.length !=2)
		{
			System.err.print("MaxLengthWordJob takes 2 argumets");
			System.exit(2);
		}
		Job max_len_job=new Job(conf,"Max Length Word Job");
		max_len_job.setJarByClass(MaxLengthWordJob.class);
		max_len_job.setMapperClass(MaxLengthWordMapper.class);
		max_len_job.setMapOutputKeyClass(Text.class);
		max_len_job.setMapOutputValueClass(IntWritable.class);
				
		max_len_job.setCombinerClass(MaxLengthWordReducer.class);
		
		max_len_job.setReducerClass(MaxLengthWordReducer.class);
		max_len_job.setOutputKeyClass(Text.class);
		max_len_job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(max_len_job,new Path(args[0]));
		FileOutputFormat.setOutputPath(max_len_job,new Path(args[1]));
		System.exit(max_len_job.waitForCompletion(true)?0:1);	
		
		
		
	}

}
