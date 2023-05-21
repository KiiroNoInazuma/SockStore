package com.example.sockstore.service;

import com.example.sockstore.model.Socks;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
public class SocksService implements IService {


    private Map<Socks, Integer> mapSocks = new HashMap<>();

    private int count;
    private final DataBase dataBase;


    public SocksService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @PostConstruct
    public void init() {
        try {
            load();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public String addSocks(Socks socks, int quantity) {
        count = quantity;
        for (Socks check : mapSocks.keySet()) {
            if (socks.equals(check)) count = mapSocks.get(socks) + quantity;
        }
        mapSocks.put(socks, count);

        if (quantity < 0) throw new NumberFormatException("Значение не может быть отрицательным числом");
        dataBase.save(mapSocks);
        return socks.color() + " носки в количестве " + quantity + " шт. добавлены на склад.";
    }

    @Override
    public int delSocks(Socks socks, int quantity) {

        count = quantity;
        for (Socks check : mapSocks.keySet()) {
            if (socks.equals(check)) count = mapSocks.get(socks) - quantity;
        }
        if (count == 0) {
            mapSocks.remove(socks);
            dataBase.save(mapSocks);
        } else if (mapSocks.size() != 0) {
            mapSocks.put(socks, count);
            dataBase.save(mapSocks);
        }
        if (quantity < 0) throw new NumberFormatException("Значение не может быть отрицательным числом");
        return count;
    }

    @Override
    public String delSocks(Socks socks) {
        mapSocks.remove(socks);
        dataBase.save(mapSocks);
        return "Все " + socks.color() + " носки с введенными параметрами удалены со склада.";
    }

    @Override
    public Socks getQuantity(String color, int size, int cottonPart) {
        return mapSocks.keySet().stream()
                .filter(a -> Objects.equals(a.color(), color))
                .filter(b -> Objects.equals(b.size(), size))
                .filter(c -> Objects.equals(c.cottonPart(), cottonPart))
                .findFirst().orElseThrow();
    }

    @Override
    public Map<Socks, Integer> getMapSocks() {
        return mapSocks;
    }

    @Override
    public void getMapSocks(Map<Socks, Integer> back) {
        mapSocks = back;
    }

    @Override
    public String up(MultipartFile fileUp) throws IOException {
        File file = new File("test.json");
        if (!file.exists()) {
            Files.copy(fileUp.getInputStream(), file.toPath());
        } else {
            Files.delete(file.toPath());
            Files.copy(fileUp.getInputStream(), file.toPath());
            load();
        }
        return "\uD83D\uDC4C\uD83C\uDFFB Файл успешно загружен!";
    }


    private void load() {
        mapSocks = dataBase.loadMap();
    }
}
