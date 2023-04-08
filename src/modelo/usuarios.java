/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USER
 */
public class usuarios {
    
    public String nombre;
    public String contraseña;
    public Double dinero;
    
    public usuarios(String nombre, String contraseña, Double dinero){
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.dinero = dinero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public Double getDinero(){
        return dinero;
    }
    
    public void setDinero(Double dinero){
        this.dinero = dinero;
    }
    
    
}
