package com.example.mysterybook.errors;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationError {
    private String name;
    private String message;
    private String value;
}
