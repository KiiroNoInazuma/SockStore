package com.example.sockstore.controller;

import com.example.sockstore.model.Socks;
import com.example.sockstore.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
public class Controller {
    private IService iService;

    @PostMapping
    public String add(@RequestBody Socks socks, @RequestParam int quantity) {
        iService.addSocks(socks, quantity);
        return "Добавлено";
    }
    @PutMapping
    public String del(@RequestBody Socks socks, @RequestParam int quantity) {
        iService.delSocks(socks, quantity);
        return "Удалено";
    }
    @DeleteMapping
    public String delAll(@RequestBody Socks socks){
        iService.delSocks(socks);
        return "Все носки удалены";
    }
    @GetMapping
    public String all(){
        return iService.getMapSocks().toString();
    }
}
