package com.example.compression.service;

import com.example.compression.algorithm.HuffmanCompression;
import com.example.compression.algorithm.HuffmanCompression.HuffmanResult;
import com.example.compression.algorithm.LZWCompression;
import com.example.compression.dto.*;

import org.springframework.stereotype.Service;

@Service
public class CompressionService {

    public CompressResponse compress(CompressRequest req) {
        CompressResponse res = new CompressResponse();
        switch (req.getAlgorithm().toUpperCase()) {
            case "HUFFMAN" -> {
                HuffmanResult hr = HuffmanCompression.compress(req.getText());
                res.setAlgorithm("HUFFMAN");
                res.setBitString(hr.bitString());
                res.setFreqMap(hr.freqMap());
            }
            case "LZW" -> {
                res.setAlgorithm("LZW");
                res.setCodes(LZWCompression.compress(req.getText()));
            }
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + req.getAlgorithm());
        }
        return res;
    }

    public DecompressResponse decompress(DecompressRequest req) {
        DecompressResponse res = new DecompressResponse();
        res.setAlgorithm(req.getAlgorithm().toUpperCase());
        switch (req.getAlgorithm().toUpperCase()) {
            case "HUFFMAN" -> {
                String text = HuffmanCompression.decompress(
                        req.getBitString(),
                        req.getFreqMap());
                res.setText(text);
            }
            case "LZW" -> {
                String text = LZWCompression.decompress(req.getCodes());
                res.setText(text);
            }
            default -> throw new IllegalArgumentException("Unsupported algorithm: " + req.getAlgorithm());
        }
        return res;
    }
}
