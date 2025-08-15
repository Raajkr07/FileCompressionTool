package com.example.compression.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CompressResponse {
    private String algorithm;

    /* This is for Huffman algo*/
    private String bitString;
    private Map<Character, Integer> freqMap;

    /* This is for LZW algo*/
    private List<Integer> codes;
}
