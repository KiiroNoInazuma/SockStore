package com.example.sockstore.model;

public enum Color {
    RED("Красные"), GREEN("Зеленые"), BLUE("Синии");
    private final String name;

    Color(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
