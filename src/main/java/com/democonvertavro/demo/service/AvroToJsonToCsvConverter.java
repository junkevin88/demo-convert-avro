package com.democonvertavro.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.commons.io.FileUtils;

public class AvroToJsonToCsvConverter {

    public static void readAvro(File file) {
        // Read Avro ,parse Schema to get field names and parse it to json
        try {

            GenericDatumReader<GenericData.Record> datum = new GenericDatumReader<GenericData.Record>();
            DataFileReader<GenericData.Record> reader = new DataFileReader<GenericData.Record>(file, datum);

            GenericData.Record record = new GenericData.Record(reader.getSchema());
            Schema schema = reader.getSchema();
            List<String> fieldValues = new ArrayList<>();
            JSONArray jsonArray = new JSONArray();
            for (Field field : schema.getFields()) {
                fieldValues.add(field.name());
            }

            while (reader.hasNext()) {
                reader.next(record);
                Map<String, String> jsonFileds = new HashMap<String, String>();
                for (String item : fieldValues) {
                    jsonFileds.put(item, record.get(item).toString());
                }
                jsonArray.put(jsonFileds);
            }
            System.out.println(jsonArray.toString());
            reader.close();
            jsonToCSV(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void jsonToCSV(JSONArray json) {
        File file = new File("avroToJson.csv");
        String csv;
        try {
            csv = CDL.toString(json);
            FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        File f = new File("users.avro");
        readAvro(f);
    }
}