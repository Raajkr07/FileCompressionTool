package com.example.compression.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompressRequest {
    private String text;
    private String algorithm;
}
