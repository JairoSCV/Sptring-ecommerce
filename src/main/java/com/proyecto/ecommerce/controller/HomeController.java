package com.proyecto.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.ecommerce.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private IProductoService productoService;

    @RequestMapping("")
    public String inicio(Model model){
        model.addAttribute("listaProductos", productoService.findAll());
        return "usuario/home";
    }
}
