package com.example.sockstore.controller;

import com.example.sockstore.model.Socks;
import com.example.sockstore.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
public class Controller {
    private IService iService;


    @GetMapping
    public int getCountSocks(@RequestBody Socks socks){
        return iService.getMapSocks().get(iService.getQuantity(socks.getColor(), socks.getSize(), socks.getCottonPart()));
    }



    @PostMapping
    public String add(@RequestBody Socks socks, @RequestParam int quantity) {
        iService.addSocks(socks, quantity);
        return "Носки в количестве " + quantity + " шт. добавлены на склад.";
    }

    @PutMapping
    public String del(@RequestBody Socks socks, @RequestParam int quantity) {
        Map<Socks, Integer> back = new HashMap<>(Map.copyOf(iService.getMapSocks()));
        int res = iService.delSocks(socks, quantity);
        if (res == 0) {
            return "Носки в количестве " + quantity + " шт. удалены со склада. На складе не осталось носков данного типа.";
        } else if (res < 0) {
            iService.getMapSocks(back);
            return "Такого типа носков в количестве " + quantity + " нет на складе.";
        } else {
            return "Носки в количестве " + quantity + " шт. удалены со склада.";
        }
    }


    @DeleteMapping
    public String delAll(@RequestBody Socks socks) {
        iService.delSocks(socks);
        return "Все <" + socks.getColor() + "> носки удалены со склада.";
    }

    @GetMapping("/all")
    public String all() {
        return iService.getMapSocks().toString();
    }
}
