package com.example.sockstore.controller;

import com.example.sockstore.model.Socks;
import com.example.sockstore.service.IService;
import com.example.sockstore.setup.SettingSwagger;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor


public class Controller implements SettingSwagger {

    private IService iService;


    @Override
    public ResponseEntity<String> getCountSocks(@RequestParam String color, @RequestParam int size, @RequestParam int cottonPath) {
        try {
            int getSocks = iService.getMapSocks()
                    .get(iService.getQuantity(color, size, cottonPath));
            return ResponseEntity.ok().body("Требуемых носков на складе - "
                    + getSocks + " шт.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Таких носков нет!");
        }
    }


    @Override
    public ResponseEntity<String> add(@RequestBody Socks socks, @RequestParam int quantity) throws IOException {
        if (socks.color() == null || socks.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Параметры запроса отсутствуют или имеют некорректный формат.");
        }
        return ResponseEntity.ok(iService.addSocks(socks, quantity));
    }

    @Override
    public ResponseEntity<String> del(@RequestBody Socks socks, @RequestParam int quantity) {
        int res = iService.delSocks(socks, quantity);
        if (res == 0) {
            return ResponseEntity
                    .ok(socks.color() + " носки в количестве " + quantity + " шт. отгружены со склада. На складе не осталось носков данного типа.");
        } else if (res < 0 || iService.getMapSocks().size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Такого типа носков в количестве " + quantity + " нет на складе.");
        } else {
            return ResponseEntity.ok(socks.color() + " носки в количестве " + quantity + " шт. отгружены со склада.");
        }
    }


    @Override
    public ResponseEntity<String> delAll(@RequestBody Socks socks) {
        if (socks.color() == null || socks.size() == 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Таких носков нет на складе.");
        return ResponseEntity.ok(iService.delSocks(socks));

    }

    @Override
    public ResponseEntity<StringBuilder> all() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Socks, Integer> it : iService.getMapSocks().entrySet())
            result.append(it.getKey()).append(" - ").append(it.getValue()).append(" шт.\t").append(Socks.transform(it.getKey())).append("\n");
        if (result.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StringBuilder("На складе нет носков!"));
        return ResponseEntity.ok(result);
    }

}
