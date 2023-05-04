package com.example.sockstore.service;

import com.example.sockstore.model.Color;
import com.example.sockstore.model.Size;
import com.example.sockstore.model.Socks;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocksService implements IService {
    Map<Socks, Integer> mapSocks;
    private int count;

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

    @Override
    public void addSocks(Socks socks, int quantity) {
        count = count + quantity;
        mapSocks.put(socks, count);
    }

    @Override
    public void delSocks(Socks socks, int quantity) {
        count = count - quantity;
        mapSocks.put(socks, count);
        if (count == 0) {
            mapSocks.remove(socks);
        }
    }

    @Override
    public int getQuantity(String color, int size, double cotton) {
        Socks check = mapSocks.keySet().stream()
                .filter(a -> Objects.equals(a.color(), transform(color)))
                .filter(b -> Objects.equals(b.size(), transform(size)))
                .filter(c -> Objects.equals(c.cottonPart(), cotton)).findFirst().orElseThrow();
        return mapSocks.get(check);
    }
}


