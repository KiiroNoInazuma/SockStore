package com.example.sockstore.service;

import com.example.sockstore.model.Socks;

import java.util.Map;

public interface IService {
    void addSocks(Socks socks, int quantity);

    void delSocks(Socks socks, int quantity);

    int getQuantity(String color, int size, double cotton);

    Map<Socks, Integer> getMapSocks();
}
