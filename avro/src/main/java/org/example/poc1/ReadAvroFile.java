package org.example.poc1;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadAvroFile {
    public static void main(String[] args) throws IOException {
        // Create a file object for the Avro file
        File avroFile = new File(ReadAvroFile.class.getClassLoader().getResource("part-00063-1d7b95e2-6146-4c14-b4ce-e5a65509b474-c000.avro").getPath());

        File file = new File(ReadAvroFile.class.getClassLoader().getResource("avro_plain_text.txt").getPath());

        // Create a buffered writer to write to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        // Create a datum reader for the Avro file
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>();

        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(avroFile, datumReader);
        // Iterate over the records in the Avro file

        int counter = 1;

        while (dataFileReader.hasNext() && counter <= 100) {
            // Read the next record
            GenericRecord record = dataFileReader.next();

            // Do something with the record
            System.out.println(record);
            System.out.println("counter => " + counter);
            counter++;
            writer.write(record.toString());
        }

        System.out.println(file.getAbsoluteFile());

        // Close the data file reader
        dataFileReader.close();
        writer.close();

    }
}
