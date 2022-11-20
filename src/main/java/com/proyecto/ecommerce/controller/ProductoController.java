package com.proyecto.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.ecommerce.model.Producto;
import com.proyecto.ecommerce.model.Usuario;
import com.proyecto.ecommerce.service.IProductoService;
import com.proyecto.ecommerce.service.UploadFileService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger looger = LoggerFactory.getLogger(ProductoController.class);   

    @Autowired
    private IProductoService productoService;

    /*Para la imagens */
    @Autowired
    private UploadFileService uploadFileService;

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
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException{
        looger.info("Este es objeto producto {}", producto);
        Usuario usuario = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(usuario);

        /*Lógica para subir imagen y guardarla */
        if(producto.getId()==null){//Cuando se crea un producto
            String nombreImagen = uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);
        }else{
            if(file.isEmpty()){//editamos el producto pero no cambiamos la imagen
                Producto producto2 = new Producto();
                producto2=productoService.get(producto.getId()).get();
                producto.setImagen(producto2.getImagen());
            }else{
                String nombreImagen = uploadFileService.saveImage(file);
                producto.setImagen(nombreImagen);
            }
        }

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
