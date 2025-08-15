package com.example.compression.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DecompressResponse {
    private String algorithm;
    private String text;

}
