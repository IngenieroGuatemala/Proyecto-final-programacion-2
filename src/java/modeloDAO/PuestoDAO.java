/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloDAO;

import config.Conexion;
import modelo.Puesto;
import java.sql.*;
import java.util.*;

public class PuestoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public List<Puesto> listar() {
        List<Puesto> lista = new ArrayList<>();
        String sql = "SELECT * FROM puestos";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Puesto p = new Puesto();
                p.setIdpuesto(rs.getInt("idpuesto"));
                p.setPuesto(rs.getString("puesto"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar puestos: " + e);
        }
        return lista;
    }

    public void agregar(Puesto p) {
        String sql = "INSERT INTO puestos(puesto) VALUES(?)";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getPuesto());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al agregar puesto: " + e);
        }
    }

    public Puesto obtenerPorId(int id) {
        Puesto p = new Puesto();
        String sql = "SELECT * FROM puestos WHERE idpuesto = ?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setIdpuesto(rs.getInt("idpuesto"));
                p.setPuesto(rs.getString("puesto"));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener puesto: " + e);
        }
        return p;
    }

    public void actualizar(Puesto p) {
        String sql = "UPDATE puestos SET puesto=? WHERE idpuesto=?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getPuesto());
            ps.setInt(2, p.getIdpuesto());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar puesto: " + e);
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM puestos WHERE idpuesto=?";
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar puesto: " + e);
        }
    }
}
