/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class Menu {
    private int id_menu;
    private String nombre;
    private String url;
    private Integer id_padre;

    public Menu() {}

    public Menu(int id_menu, String nombre, String url, Integer id_padre) {
        this.id_menu = id_menu;
        this.nombre = nombre;
        this.url = url;
        this.id_padre = id_padre;
    }

    // Getters y Setters
    public int getId_menu() { return id_menu; }
    public void setId_menu(int id_menu) { this.id_menu = id_menu; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Integer getId_padre() { return id_padre; }
    public void setId_padre(Integer id_padre) { this.id_padre = id_padre; }
}
