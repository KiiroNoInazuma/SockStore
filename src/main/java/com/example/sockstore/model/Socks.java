package com.example.sockstore.model;

import lombok.Data;

@Data
public class Socks {
    Color color;
    Size size;
    double cottonPart;

    public Socks(String color, int size, double cottonPart) {
        this.color = transform(color);
        this.size = transform(size);
        this.cottonPart = cottonPart;
    }
    private Color transform(String color) {
        switch (color) {
            case "Красный" -> {
                return Color.RED;
            }
            case "Синий" -> {
                return Color.BLUE;
            }
            case "Зеленый" -> {
                return Color.GREEN;
            }
        }
        return null;
    }

    private Size transform(int size) {
        switch (size) {
            case 39 -> {
                return Size.A;
            }
            case 40 -> {
                return Size.B;
            }
            case 41 -> {
                return Size.C;
            }
            case 42 -> {
                return Size.D;
            }
            case 43 -> {
                return Size.E;
            }
        }
        return null;
    }
}
