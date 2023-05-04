package com.example.sockstore.controller;

import com.example.sockstore.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/socks")
@AllArgsConstructor
public class Controller {
    private IService iService;

}
