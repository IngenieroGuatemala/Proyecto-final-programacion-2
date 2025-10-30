/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author excal
 */
public class compras_detalle {
   int cantidad;
   float precio_costo_unitario;

    public compras_detalle(int cantidad, float precio_costo_unitario) {
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
    }
    
   
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(float precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }
    
}
