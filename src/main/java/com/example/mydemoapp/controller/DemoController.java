package com.example.mydemoapp.controller;

import com.example.mydemoapp.service.ClientService;
import com.example.mydemoapp.serviceImpl.OkHttpImpl;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Data
public class DemoController {
    private final ClientService clientService;
    private final OkHttpImpl okHttp;

    @GetMapping("/fetch-todos/{id}")
    public ResponseEntity<String> fetchData(@PathVariable String id) throws IOException, InterruptedException {
        return ResponseEntity.ok(clientService.fetchDataFromApi(id));
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<String> fetchPersonBy(@PathVariable String id) throws IOException {
        return ResponseEntity.ok( okHttp.fetchDataFromApi(id));
    }

    @GetMapping("/person/async/{id}")
    public ResponseEntity<String> fetchPersonAsync(@PathVariable String id) {
        okHttp.fetchDataFromApiAsync(id);

        return ResponseEntity.accepted().body("Request is being processed for ID: " + id);
    }
}
