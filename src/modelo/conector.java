/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import javax.swing.JOptionPane;

public class conector {
    
    public String url="jdbc:mysql://localhost:3306/banco?"+"autoreconnect=true&useSSL=false";
    public String usuario="root";
    public String contraseña="Borman15";
    
    public Connection getConnection(){
        Connection conexion = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion= DriverManager.getConnection(url,usuario, contraseña);
                    
        }catch(ClassNotFoundException | SQLException e){
            System.err.println("Error "+e);
        }
        return conexion;
    }
    
}
