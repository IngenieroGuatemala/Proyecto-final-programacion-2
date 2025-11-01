/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static Connection con;
    private static final String url = "jdbc:mysql://localhost:3306/tienda";
    private static final String user = "root";
    private static final String pass = "bryan123";

    public static Connection getConexion() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
            }
        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e);
        }
        return con;
    }
}
