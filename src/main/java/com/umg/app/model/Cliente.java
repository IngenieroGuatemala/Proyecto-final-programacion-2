package com.umg.app.model;

public class Cliente {
    private Integer id;
    private String nit;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;
    private boolean activo;

    public Cliente() {}

    public Cliente(Integer id, String nit, String nombres, String apellidos, String telefono,
                   String correo, String direccion, boolean activo) {
        this.id = id; this.nit = nit; this.nombres = nombres; this.apellidos = apellidos;
        this.telefono = telefono; this.correo = correo; this.direccion = direccion; this.activo = activo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
