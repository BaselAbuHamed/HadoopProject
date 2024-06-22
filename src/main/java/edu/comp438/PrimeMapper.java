package edu.comp438;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PrimeMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] numbers = line.split(",");

        for (String numberStr : numbers) {
            int number = Integer.parseInt(numberStr.trim());
            boolean isPrime = isPrime(number);
            int closestPrime = nextPrime(number);
            context.write(new Text(numberStr), new Text(isPrime + "," + closestPrime));
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private int nextPrime(int number) {
        int next = number + 1;
        while (!isPrime(next)) {
            next++;
        }
        return next;
    }
}
