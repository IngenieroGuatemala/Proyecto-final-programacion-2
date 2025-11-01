/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import config.Conexion;
import modelo.Menu;
import java.sql.*;
import java.util.*;

public class MenuDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Menu> listar() {
    List<Menu> lista = new ArrayList<>();
    String sql = "SELECT * FROM menu";
    try {
        Connection con = Conexion.getConexion();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Menu m = new Menu();
            m.setId_menu(rs.getInt("id_menu"));
            m.setNombre(rs.getString("nombre"));
            m.setUrl(rs.getString("url"));
            Object idPadreObj = rs.getObject("id_padre");
            if (idPadreObj != null) {
                m.setId_padre(rs.getInt("id_padre"));
            } else {
                m.setId_padre(null);
            }
            lista.add(m);
        }
        con.close();
    } catch (Exception e) {
        System.out.println("Error al listar menú: " + e.getMessage());
    }
    return lista;
    }
}
