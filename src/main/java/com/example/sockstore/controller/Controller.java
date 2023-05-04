package com.example.sockstore.controller;

import com.example.sockstore.model.Socks;
import com.example.sockstore.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks/")
@AllArgsConstructor
public class Controller {
    private IService iService;

@PostMapping("add")
public String add(@RequestBody Socks socks){
    iService.addSocks(socks,12);
    return "Добавлено";
}
}
