package com.democonvertavro.demo.controller;


import com.democonvertavro.demo.repository.UsersRepository;
import com.democonvertavro.demo.service.AvroMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping
public class AvroController {

    @Autowired
    public AvroMakerService avroMakerService;

    @GetMapping("/avro-maker")
    public void getHelloWorld() throws IOException {
        this.avroMakerService.getAvroFile();

    }
}
