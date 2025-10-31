package com.umg.app.model;

public class Proveedor {
    private Integer id;
    private String nit;
    private String nombre;
    private String contacto;
    private String telefono;
    private String correo;
    private String direccion;
    private boolean activo;

    public Proveedor() {}

    public Proveedor(Integer id, String nit, String nombre, String contacto, String telefono,
                     String correo, String direccion, boolean activo) {
        this.id = id; this.nit = nit; this.nombre = nombre; this.contacto = contacto;
        this.telefono = telefono; this.correo = correo; this.direccion = direccion; this.activo = activo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
