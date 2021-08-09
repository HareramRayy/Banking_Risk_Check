package BankingAvrageRisk;

import java.io.IOException;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;

public class MyDriver
{
    public static void main(final String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        final Configuration conf = new Configuration();
        final Job job = Job.getInstance(conf, "Calculate Avg Risk");
        job.setJarByClass((Class)MyDriver.class);
        job.setMapperClass((Class)MyMapper.class);
        job.setMapOutputKeyClass((Class)Text.class);
        job.setMapOutputValueClass((Class)DoubleWritable.class);
        job.setReducerClass((Class)MyReducer.class);
        job.setNumReduceTasks(1);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
