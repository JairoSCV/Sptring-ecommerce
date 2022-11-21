package com.proyecto.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.ecommerce.model.Producto;
import com.proyecto.ecommerce.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @Autowired
    private IProductoService productoService;

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("")
    public String inicio(Model model){
        model.addAttribute("listaProductos", productoService.findAll());
        return "usuario/home";
    }

    @RequestMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){
        log.info("ID enviado como párametro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(){
        return "usuario/carrito";
    }
}
