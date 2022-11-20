package com.proyecto.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @RequestMapping("/")
    public String inicio(){
        return "administrador/home";
    }
}