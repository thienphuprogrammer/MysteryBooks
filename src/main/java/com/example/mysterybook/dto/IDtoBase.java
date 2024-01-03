package com.example.mysterybook.dto;

import com.example.mysterybook.errors.ValidationError;

import java.util.List;

public interface IDtoBase<T> {
    List<ValidationError> validate();
    void loadFromModel(T t);
}
