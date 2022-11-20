package com.proyecto.ecommerce.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String inicio(Model model){
        model.addAttribute("productos", productoService.findAll());
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

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);

        producto=optionalProducto.get();
        model.addAttribute("producto", producto);
        looger.info("Producto buscado {}", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto){
        productoService.update(producto);
        return "redirect:/productos/";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        productoService.delete(id);
        return "redirect:/productos/";
    }
}
