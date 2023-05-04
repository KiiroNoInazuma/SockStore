package com.example.sockstore.model;

public enum Size {
    A(39), B(40), C(41), D(42), E(43);
    private final int size;

    Size(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.valueOf(size);
    }
}