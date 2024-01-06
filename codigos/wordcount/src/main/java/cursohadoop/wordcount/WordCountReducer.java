package cursohadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context ctxt) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values)  {
            sum += value.get();
        }
        ctxt.write(key, new IntWritable(sum));
    }
}
