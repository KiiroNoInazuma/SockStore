package com.example.sockstore.service;

import com.example.sockstore.model.Socks;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IService {
    String addSocks(Socks socks, int quantity) throws IOException;

    int delSocks(Socks socks, int quantity);

    String delSocks(Socks socks);

    Socks getQuantity(String color, int size, int cottonPart);

    Map<Socks, Integer> getMapSocks();

    String up(MultipartFile fileUp) throws IOException;
}
