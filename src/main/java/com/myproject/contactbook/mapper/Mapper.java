package com.myproject.contactbook.mapper;


import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<T, V> {

    public abstract V map(T elem);

    public List<V> map(List<T> toMap) {
        List<V> mapped = new ArrayList<>();
        for (T elem : toMap) {
            mapped.add(map(elem));
        }
        return mapped;
    }
}