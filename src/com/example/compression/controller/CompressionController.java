package com.example.compression.controller;

import com.example.compression.dto.*;
import com.example.compression.service.CompressionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CompressionController {

    private final CompressionService service;

    public CompressionController(CompressionService service) {
        this.service = service;
    }

    @PostMapping("/compress")
    public ResponseEntity<CompressResponse> compress(@RequestBody CompressRequest request) {
        return ResponseEntity.ok(service.compress(request));
    }

    @PostMapping("/decompress")
    public ResponseEntity<DecompressResponse> decompress(@RequestBody DecompressRequest request) {
        return ResponseEntity.ok(service.decompress(request));
    }
}
