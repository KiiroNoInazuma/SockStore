package com.example.sockstore.service;

import com.example.sockstore.model.Color;
import com.example.sockstore.model.Size;
import com.example.sockstore.model.Socks;

import java.util.Map;

public interface IService {
    void addSocks(Socks socks, int quantity);

    int delSocks(Socks socks, int quantity);

    void delSocks(Socks socks);

    Socks getQuantity(Color color, Size size, double cotton);

    Map<Socks, Integer> getMapSocks();

    void getMapSocks(Map<Socks, Integer> back);
}
