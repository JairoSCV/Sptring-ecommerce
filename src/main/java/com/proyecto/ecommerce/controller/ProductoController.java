package com.proyecto.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    @RequestMapping("/")
    public String inicio(){
        return "productos/show";
    }
}
