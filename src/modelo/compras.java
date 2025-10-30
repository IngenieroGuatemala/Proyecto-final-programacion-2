/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author excal
 */
public class compras {
   int no_orden_compra;
    Date fecha_orden ;
    Timestamp fechaingreso;

    public compras(int no_orden_compra, Date fecha_orden, Timestamp fechaingreso) {
        this.no_orden_compra = no_orden_compra;
        this.fecha_orden = fecha_orden;
        this.fechaingreso = fechaingreso;
    }

    public int getNo_orden_compra() {
        return no_orden_compra;
    }

    public void setNo_orden_compra(int no_orden_compra) {
        this.no_orden_compra = no_orden_compra;
    }

    public Date getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public Timestamp getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Timestamp fechaingreso) {
        this.fechaingreso = fechaingreso;
    }
    
    
}
