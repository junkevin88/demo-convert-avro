package com.democonvertavro.demo.controller;


import com.democonvertavro.demo.service.AvroMakerService;
import com.democonvertavro.demo.service.AvroToCsvConverter;
import com.democonvertavro.demo.service.AvroToJsonToCsvConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class AvroController {

    @Autowired
    public AvroMakerService avroMakerService;
    @Autowired
    public AvroToCsvConverter avroToCsvConverter;
    @Autowired
    public AvroToJsonToCsvConverter avroToJsonToCsvConverter;

    @GetMapping("/avro-maker")
    public void avroMaker() throws IOException {
        this.avroMakerService.getAvroFile();
    }

    @GetMapping("/convert-csv")
    public void convertCsv() {
        this.avroToCsvConverter.convertToCsv();
    }

    @GetMapping("/convert-csv-via-json")
    public void convertCsvViaJson() {
        this.avroToJsonToCsvConverter.convertAvroToJsonToCsv();
    }
}
