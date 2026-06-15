package com.example.interfaz_con_crud_javafx.mitienda.model;

import javafx.beans.property.*;

public class Producto {

    private final StringProperty codigo;
    private final StringProperty nombre;
    private final StringProperty categoria;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final StringProperty estado;

    public Producto(String codigo, String nombre, String categoria, double precio, int stock, String estado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.categoria = new SimpleStringProperty(categoria);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.estado = new SimpleStringProperty(estado);
    }

    public String getCodigo() { return codigo.get(); }
    public void setCodigo(String v) { codigo.set(v); }
    public StringProperty codigoProperty() { return codigo; }

    public String getNombre() { return nombre.get(); }
    public void setNombre(String v) { nombre.set(v); }
    public StringProperty nombreProperty() { return nombre; }

    public String getCategoria() { return categoria.get(); }
    public void setCategoria(String v) { categoria.set(v); }
    public StringProperty categoriaProperty() { return categoria; }

    public double getPrecio() { return precio.get(); }
    public void setPrecio(double v) { precio.set(v); }
    public DoubleProperty precioProperty() { return precio; }

    public int getStock() { return stock.get(); }
    public void setStock(int v) { stock.set(v); }
    public IntegerProperty stockProperty() { return stock; }

    public String getEstado() { return estado.get(); }
    public void setEstado(String v) { estado.set(v); }
    public StringProperty estadoProperty() { return estado; }
}