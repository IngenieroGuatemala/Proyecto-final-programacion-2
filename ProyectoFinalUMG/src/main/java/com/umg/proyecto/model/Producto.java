package com.umg.proyecto.model;

public class Producto {
    private Integer id;
    private String nombre;
    private Integer marcaId;
    private int stock;
    private double precio;
    private String imagenUrl;

    public Producto() {}

    public Producto(Integer id, String nombre, Integer marcaId, int stock, double precio, String imagenUrl){
        this.id=id; this.nombre=nombre; this.marcaId=marcaId; this.stock=stock; this.precio=precio; this.imagenUrl=imagenUrl;
    }
    public Integer getId(){ return id; }
    public void setId(Integer id){ this.id=id; }
    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre=nombre; }
    public Integer getMarcaId(){ return marcaId; }
    public void setMarcaId(Integer marcaId){ this.marcaId=marcaId; }
    public int getStock(){ return stock; }
    public void setStock(int stock){ this.stock=stock; }
    public double getPrecio(){ return precio; }
    public void setPrecio(double precio){ this.precio=precio; }
    public String getImagenUrl(){ return imagenUrl; }
    public void setImagenUrl(String imagenUrl){ this.imagenUrl=imagenUrl; }
}