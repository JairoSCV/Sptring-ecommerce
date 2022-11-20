package com.proyecto.ecommerce.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.ecommerce.model.Producto;
import com.proyecto.ecommerce.model.Usuario;
import com.proyecto.ecommerce.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger looger = LoggerFactory.getLogger(ProductoController.class);   

    @Autowired
    private IProductoService productoService;

    @RequestMapping("/")
    public String inicio(){
        return "productos/show";
    }

    @RequestMapping("crear")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto){
        looger.info("Este es objeto producto {}", producto);
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario);

        productoService.saveProducto(producto);
        return "redirect:/productos/";
    }

}
