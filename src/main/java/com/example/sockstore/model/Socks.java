package com.example.sockstore.model;


public record Socks(String color, int size, int cottonPart) {
    public Socks {
        color = checkColor(color);
        size = checkSize(size);
        if (cottonPart < 0 || cottonPart > 100) {
            throw new RuntimeException("Значение переменной в недопустимом диапазоне");
        }
    }

    public static String checkColor(String color) {
        switch (color) {
            case "Красные" -> {
                return Color.RED.toString();
            }
            case "Синии" -> {
                return Color.BLUE.toString();
            }
            case "Зеленые" -> {
                return Color.GREEN.toString();
            }
        }
        return null;
    }

    public static String transform(Socks socks) {
        switch (socks.color) {
            case "Красные" -> {
                return " 🔴";
            }
            case "Синии" -> {
                return "🔵";
            }
            case "Зеленые" -> {
                return "🟢";
            }
        }
        return null;
    }

    public static int checkSize(int size) {
        switch (size) {
            case 39 -> {
                return Integer.parseInt(Size.A.toString());
            }
            case 40 -> {
                return Integer.parseInt(Size.B.toString());
            }
            case 41 -> {
                return Integer.parseInt(Size.C.toString());
            }
            case 42 -> {
                return Integer.parseInt(Size.D.toString());
            }
            case 43 -> {
                return Integer.parseInt(Size.E.toString());
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "\uD83E\uDDE6 Носки ➡ " + color + "; Размер - " + size + "; Хлопок - " + cottonPart + '%';
    }


}
