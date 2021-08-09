package BankingAvrageRisk;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, Text, DoubleWritable>
{
    public void map(final LongWritable key, final Text value, final Mapper.Context con) throws IOException, InterruptedException {
        final String line = value.toString();
        final String[] linePart = line.split(",");
        final Double risk = Double.parseDouble(linePart[7]);
        con.write((Object)new Text(""), (Object)new DoubleWritable((double)risk));
    }
}
