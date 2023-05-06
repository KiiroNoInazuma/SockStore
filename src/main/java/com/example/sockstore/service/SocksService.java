package com.example.sockstore.service;

import com.example.sockstore.model.Color;
import com.example.sockstore.model.Size;
import com.example.sockstore.model.Socks;
import com.example.sockstore.param.TransSoft;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocksService implements IService {
    private Map<Socks, Integer> mapSocks = new HashMap<>();


    private int count;


    @Override
    public void addSocks(Socks socks, int quantity) {
        count = quantity;
        for (Socks check : mapSocks.keySet()) {
            if (socks.equals(check)) count = mapSocks.get(socks) + quantity;
        }
        mapSocks.put(socks, count);

    }

    @Override
    public int delSocks(Socks socks, int quantity) {

        count = quantity;
        for (Socks check : mapSocks.keySet()) {
            if (socks.equals(check)) count = mapSocks.get(socks) - quantity;
        }
        mapSocks.put(socks, count);
        if (count == 0) {
            mapSocks.remove(socks);
        }
        return count;
    }

    @Override
    public void delSocks(Socks socks) {
        mapSocks.remove(socks);
    }

    @Override
    public Socks getQuantity(Color color, Size size, double cotton) {
        return mapSocks.keySet().stream()
                .filter(a -> Objects.equals(a.getColor(), color))
                .filter(b -> Objects.equals(b.getSize(), size))
                .filter(c -> Objects.equals(c.getCottonPart(), cotton)).findFirst().orElseThrow();
    }

    @Override
    public Map<Socks, Integer> getMapSocks() {
        return mapSocks;
    }

    @Override
    public void getMapSocks(Map<Socks, Integer> back) {
        mapSocks = back;
    }
}



