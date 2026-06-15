package com.example.interfaz_con_crud_javafx.mitienda.controller;

import com.example.interfaz_con_crud_javafx.mitienda.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DashboardController {

    private final ObservableList<Producto> productos = FXCollections.observableArrayList();

    public DashboardController() {
        // Datos de ejemplo precargados
        productos.add(new Producto("P001", "Zapato Deportivo",  "Calzado",     35.00, 20, "Activo"));
        productos.add(new Producto("P002", "Camisa Manga Larga","Ropa",        25.00, 15, "Activo"));
        productos.add(new Producto("P003", "Jean Clásico",      "Ropa",        40.00, 10, "Activo"));
        productos.add(new Producto("P004", "Gorra Deportiva",   "Accesorios",  12.00, 30, "Activo"));
        productos.add(new Producto("P005", "Cinturón de Cuero", "Accesorios",  18.00, 25, "Inactivo"));
    }

    public ObservableList<Producto> getProductos() {
        return productos;
    }

    public String guardar(Producto nuevo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(nuevo.getCodigo())) {
                return "Ya existe un producto con ese código.";
            }
        }
        productos.add(nuevo);
        return "Producto guardado correctamente.";
    }

    public String actualizar(Producto actualizado) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(actualizado.getCodigo())) {
                p.setNombre(actualizado.getNombre());
                p.setCategoria(actualizado.getCategoria());
                p.setPrecio(actualizado.getPrecio());
                p.setStock(actualizado.getStock());
                p.setEstado(actualizado.getEstado());
                return "Producto actualizado correctamente.";
            }
        }
        return "No se encontró el producto con ese código.";
    }

    public void eliminar(Producto producto) {
        productos.remove(producto);
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }
}