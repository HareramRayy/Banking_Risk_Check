package BankingAvrageRisk;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>
{
    double glSum;
    int glcount;
    double avg;
    
    public MyReducer() {
        this.glSum = 0.0;
        this.glcount = 0;
        this.avg = 0.0;
    }
    
    public void reduce(final Text key, final Iterable<DoubleWritable> val, final Reducer.Context con) throws IOException, InterruptedException {
        for (final DoubleWritable values : val) {
            this.glSum += values.get();
            ++this.glcount;
        }
    }
    
    public void cleanup(final Reducer.Context con) throws IOException, InterruptedException {
        this.avg = this.glSum / this.glcount;
        con.write((Object)new Text(""), (Object)new DoubleWritable(this.avg));
    }
}
