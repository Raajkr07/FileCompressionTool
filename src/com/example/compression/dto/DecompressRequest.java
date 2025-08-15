package com.example.compression.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DecompressRequest {
    private String algorithm;

    private String bitString;
    private Map<Character, Integer> freqMap;
    private List<Integer> codes;
}
