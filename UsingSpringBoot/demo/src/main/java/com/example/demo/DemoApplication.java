package com.example.demo;

import com.example.demo.entity.ArticuloEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        ArticuloEntity articuloEntity = new ArticuloEntity();
        articuloEntity.setTitulo("abc");
    }
}
