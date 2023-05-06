package com.example.sockstore.model;

import com.example.sockstore.param.TransSoft;
import lombok.Data;

@Data
public class Socks {
    Color color;
    Size size;
    double cottonPart;

    public Socks(String color, int size, double cottonPart) {
        this.color = TransSoft.transform(color);
        this.size = TransSoft.transform(size);
        this.cottonPart = cottonPart;
    }


}
