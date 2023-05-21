package com.example.sockstore.service;

import com.example.sockstore.model.Socks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataBase {
    private final File file;

    public DataBase() {
        this.file = new File("test.json");
    }

    private Map<Integer, Socks> reverseMap(Map<Socks, Integer> map) {
        Map<Integer, Socks> tempMap = new HashMap<>();
        for (Map.Entry<Socks, Integer> temp : map.entrySet()) {
            tempMap.put(temp.getValue(), temp.getKey());
        }
        return tempMap;
    }


    private String getMapSerial(Map<Socks, Integer> map) {
        try {
            return new ObjectMapper().writeValueAsString(reverseMap(map));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public void save(Map<Socks, Integer> map) {
        try {
            FileWriter save = new FileWriter(file);
            save.write(getMapSerial(map));
            save.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String read() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            return bf.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    private Map<Integer, Socks> getMapDeSerial() {
        try {
            return new ObjectMapper().readValue(read(), new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Socks, Integer> loadMap() {
        Map<Integer, Socks> tempMap = getMapDeSerial();
        Map<Socks, Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Socks> temp : tempMap.entrySet()) {
            map.put(temp.getValue(), temp.getKey());
        }
        return map;
    }

}
