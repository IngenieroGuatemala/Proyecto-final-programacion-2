/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author excal
 */
public class ventas_DAO {
     public void crear(){
        try{
          PreparedStatement parametro;
          cn = new Conexion();
          cn.abrir_conexion();
          String query = "INSERT INTO ventas(id, telefono, genero, nit,nombres, apellidos, direccion,fecha_nacimiento, codigo, salario, id_profesion) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setInt(1,getId());
          parametro.setInt(2,getTelefono());
          parametro.setString(3,String.valueOf(getGenero()));
          parametro.setString(4,String.valueOf(getNit()));
          parametro.setString(5, getNombre());
          parametro.setString(6, getApellido());
          parametro.setString(7, getDireccion());
          parametro.setString(8, getFecha_nacimiento());
          parametro.setString(9, getCodigo());
          parametro.setInt(10, (int) getSalario());
          parametro.setInt(11,  getId_profesion());
          int executar = parametro.executeUpdate();
          System.out.println("Se inserto " + Integer.toString(executar)  + " Registo");
      //   cn.cerrar_conexion();
          
      }catch(SQLException ex){
          System.out.println("Error: " + ex.getMessage());
      }
      
      
  }
     
    
}
