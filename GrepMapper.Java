package june_6_2013;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GrepMapper extends Mapper<LongWritable, Text, NullWritable,Text>
{

private String str_format; 
private String str_find_val="Hadoop";
private Text returnvalue=new Text();

public void map(LongWritable key,Text value,Context context) 
  	throws IOException,InterruptedException
{
	str_format=value.toString();
	if(str_format.contains(str_find_val))
			{
		returnvalue.set(str_format);
		context.write(NullWritable.get(),returnvalue);
			}
}
	
}
