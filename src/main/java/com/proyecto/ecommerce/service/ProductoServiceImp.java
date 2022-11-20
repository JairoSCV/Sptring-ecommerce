package com.proyecto.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.ecommerce.model.Producto;
import com.proyecto.ecommerce.repository.IProductoDAO;

@Service
public class ProductoServiceImp implements IProductoService{

    @Autowired
    private IProductoDAO productoDAO;

    @Override
    public Producto saveProducto(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoDAO.findById(id);
    }

    //Este método actualiza los objetos
    @Override
    public void update(Producto producto) {
        productoDAO.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoDAO.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoDAO.findAll();
    }
    
}
