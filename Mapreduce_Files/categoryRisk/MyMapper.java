package com.AvgRiskcat;
import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class MyMapper extends Mapper<LongWritable,Text,Text,DoubleWritable> {
public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException
{
String line = value.toString();
String[] linePart = line.split(",");
//problem -1 -avg risk
Double risk = Double.parseDouble(linePart[7]);
//problem-2 - avg risk per location
//String loc = linePart[8].toString();
String cat = linePart[2].toString().substring(1, 3);
if(cat.equals("HL"))
{
cat = "Home Loan";
}
else if(cat.equals("PL"))
{
cat = "Personal Loan";
}
else if(cat.equals("VL"))
{
cat = "Viechel Loan";
}
else
{
cat = "Retailer Loan";
}
con.write(new Text(cat), new DoubleWritable(risk));
}
}

