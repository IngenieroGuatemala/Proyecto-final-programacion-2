/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author excal
 */
public class ventas_detalle {
   long  idventa_detalle ;
   String cantidad; 
   float precio_unitario ;

    public ventas_detalle(long idventa_detalle, String cantidad, float precio_unitario) {
        this.idventa_detalle = idventa_detalle;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public long getIdventa_detalle() {
        return idventa_detalle;
    }

    public void setIdventa_detalle(long idventa_detalle) {
        this.idventa_detalle = idventa_detalle;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
   
   
   
   
}
