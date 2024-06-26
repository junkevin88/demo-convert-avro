package com.democonvertavro.demo.service;

import com.democonvertavro.demo.entity.User;
import com.democonvertavro.demo.repository.UsersRepository;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class AvroMakerService {


    @Autowired
    public UsersRepository usersRepository;


    public void getAvroFile() throws IOException {
        System.out.println(this.usersRepository.findAll());
        // Define Avro schema
        InputStream schemaInputStream = AvroMakerService.class.getResourceAsStream("/users.avsc");
        if (schemaInputStream == null) {
            throw new IOException("Unable to load Avro schema file.");
        }
        Schema schema = new Schema.Parser().parse(schemaInputStream);
        schemaInputStream.close();

        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(new GenericDatumWriter<>(schema))) {
            dataFileWriter.create(schema, new File("users.avro"));

            // Fetch sample records from the database
            List<User> users = usersRepository.findAll(); // Assuming getAllUsers() returns a list of User objects
            System.out.println(usersRepository.findAll());
            // Convert User objects to Avro GenericRecord and write to Avro file
            for (User user : users) {
                GenericRecord genericRecord = new GenericData.Record(schema);
                genericRecord.put("name", user.getName());
                genericRecord.put("city", user.getCity());
                genericRecord.put("code", user.getCode());

                dataFileWriter.append(genericRecord);
            }
        }
    }
}