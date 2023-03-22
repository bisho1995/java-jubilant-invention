package org.example.poc1;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Avro1 {
    @SneakyThrows
    public static void main(String[] args) {
        Schema clientIdentifierSchema = SchemaBuilder.record("ClientIdentifier")
                .namespace("com.baeldung.avro")
                .fields()
                .requiredString("hostName")
                .requiredString("ipAddress")
                .endRecord();

        Schema avroHttpRequestSchema = SchemaBuilder.record("AvroHttpRequest")
                .namespace("com.baeldung.avro")
                .fields().requiredLong("requestTime")
                .name("clientIdentifier")
                .type(clientIdentifierSchema)
                .noDefault()
                .name("employeeNames")
                .type()
                .array()
                .items()
                .stringType()
                .arrayDefault(null)
                .name("active")
                .type()
                .enumeration("Active")
                .symbols("YES", "NO")
                .noDefault()
                .endRecord();

        String httpRequestAvroSchema = avroHttpRequestSchema.toString();

        log.info("clientIdentifierSchema => {}", clientIdentifierSchema.toString());
        log.info("avroHttpRequestSchema => {}", httpRequestAvroSchema);

        String avroFilePath = Avro1.class.getResource("/").getPath() + "httpRequestSchema.avsc";
        log.info("avroFilePath => {}", avroFilePath);
        FileOutputStream fout = new FileOutputStream(avroFilePath);
        fout.write(httpRequestAvroSchema.getBytes(StandardCharsets.UTF_8));
    }
}
