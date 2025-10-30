/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 *
 * @author excal
 */
public class ventas {
     int  idventa,nofactura,idcliente,idempleado,fechaingreso; 
     char serie;
     Date fechafactura ;
     Timestamp fecha_ingreso;

    public ventas(int idventa, int nofactura, int idcliente, int idempleado, int fechaingreso, char serie, Date fechafactura, Timestamp fecha_ingreso) {
        this.idventa = idventa;
        this.nofactura = nofactura;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.fechaingreso = fechaingreso;
        this.serie = serie;
        this.fechafactura = fechafactura;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

    public int getNofactura() {
        return nofactura;
    }

    public void setNofactura(int nofactura) {
        this.nofactura = nofactura;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(int fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public char getSerie() {
        return serie;
    }

    public void setSerie(char serie) {
        this.serie = serie;
    }

    public Date getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(Date fechafactura) {
        this.fechafactura = fechafactura;
    }

    public Timestamp getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Timestamp fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
   
    
   
     
     
}
