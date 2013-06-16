package june_6_2013;


import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SedMapper extends Mapper<LongWritable, Text, NullWritable,Text>
{

private String str_format; 
//ivate String str_find_val="Hadoop";
//private String str_replace_val="Big Dataaaaaa   ";
private Text returnvalue=new Text();

public void map(LongWritable key,Text value,Context context) 
  	throws IOException,InterruptedException
{
	str_format=value.toString();
	if(str_format.contains(context.getConfiguration().get("sed-arg1")))
			{
		
		returnvalue.set(str_format.replaceAll(context.getConfiguration().get("sed-arg1"),context.getConfiguration().get("sed-arg2")));
		context.write(NullWritable.get(),returnvalue);
			}
}
	
}
