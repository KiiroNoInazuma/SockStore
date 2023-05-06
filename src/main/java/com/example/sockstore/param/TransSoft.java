package com.example.sockstore.param;

import com.example.sockstore.model.Color;
import com.example.sockstore.model.Size;

public class TransSoft {

    public static Color transform(String color) {
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

    public static Size transform(int size) {
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
