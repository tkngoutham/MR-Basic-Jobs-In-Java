package june_6_2013;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;

public class SedJob {

  public static void main(String[] args)throws Exception {

Configuration conf=new Configuration();
if(args.length!=4)
{
	System.err.println("Sed Job needs 4 args");
	System.exit(2);
}
conf.set("sed-arg1",args[2]);
conf.set("sed-arg2",args[3]);
Job job=new Job(conf,"Sed Job");
job.setJarByClass(SedJob.class);
job.setMapperClass(SedMapper.class);
job.setOutputKeyClass(NullWritable.class);
job.setOutputValueClass(Text.class);
job.setInputFormatClass(TextInputFormat.class);
job.setOutputFormatClass(TextOutputFormat.class);
FileInputFormat.addInputPath(job,new Path(args[0]));
FileOutputFormat.setOutputPath(job, new Path(args[1]));

System.exit(job.waitForCompletion(true)?0:1);
	}

}

