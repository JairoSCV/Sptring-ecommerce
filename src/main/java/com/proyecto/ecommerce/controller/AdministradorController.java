package com.proyecto.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.ecommerce.model.Producto;
import com.proyecto.ecommerce.service.IProductoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private IProductoService productoService;

    @RequestMapping("/")
    public String inicio(Model model){
        List<Producto> productos=productoService.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }


}
