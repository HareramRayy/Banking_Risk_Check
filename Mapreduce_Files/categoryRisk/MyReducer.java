package com.AvgRiskcat;
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class MyReducer extends Reducer<Text,DoubleWritable,Text,DoubleWritable> {
double glSum = 0;
int glcount = 0;
//double avg = 0;
public void reduce(Text key,Iterable<DoubleWritable> val,Context con) throws IOException, InterruptedException
{
for (DoubleWritable values : val) {
glSum = glSum + values.get();
glcount = glcount +1;
}
con.write(new Text(key), new DoubleWritable(glSum/glcount));
}

}


