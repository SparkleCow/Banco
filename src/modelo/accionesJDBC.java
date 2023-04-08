/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import javax.swing.JOptionPane;
public class accionesJDBC extends conector{
    
    private ResultSet rs;
    private PreparedStatement ps;
    
    public boolean ingresarNuevoUsuario(usuarios persona){
    
        Connection conexion = getConnection();
        try{
            ps = conexion.prepareStatement("insert into cowBank values(?,?,?,?)");
            ps.setInt(1, 0);
            ps.setString(2,persona.nombre);
            ps.setString(3, persona.contraseña);
            ps.setInt(4, 0);
            int comprobacion = ps.executeUpdate();
            if(comprobacion > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.err.println("Error "+e);
            return false;
        }finally{
             try{
                 conexion.close();
             }catch(SQLException e){
                 System.err.println("Error "+e);
             }
        }
    }
    
    public boolean encontrarUsuario(usuarios persona){
        Connection conexion = getConnection();
        try{
            ps = conexion.prepareStatement("select * from cowBank where nombre=? and contraseña=?");       
            ps.setString(1,persona.nombre);
            ps.setString(2, persona.contraseña);
            rs=ps.executeQuery();
            
            if(rs.next())
                persona.setNombre(rs.getString("nombre"));
                persona.setContraseña(rs.getString("contraseña"));
                persona.setDinero(rs.getDouble("dinero"));
                return true;
        }catch(Exception e){
            System.err.println("Error "+e);
            return false;
        }
    }
    
    public boolean consignar(usuarios persona, int cantidad){
        cantidad += persona.getDinero();
        Connection conexion = getConnection();
        try{
            ps = conexion.prepareStatement("update cowBank set dinero=? where nombre=?");
            ps.setInt(1,cantidad);
            ps.setString(2, persona.nombre);
            int comprobacion = ps.executeUpdate();
            if(comprobacion > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.err.println("Error "+e);
            return false;
        }finally{
             try{
                 conexion.close();
             }catch(SQLException e){
                 System.err.println("Error "+e);
             }
        }
    }
    
    public boolean actualizarUsuario(usuarios persona){
        Connection conexion = getConnection();
        try{
            ps = conexion.prepareStatement("select * from cowBank where nombre=? and contraseña=?");       
            ps.setString(1,persona.nombre);
            ps.setString(2, persona.contraseña);
            rs=ps.executeQuery();
            
            if(rs.next())
                persona.setNombre(rs.getString("nombre"));
                persona.setContraseña(rs.getString("contraseña"));
                persona.setDinero(rs.getDouble("dinero"));
                return true;
        }catch(Exception e){
            System.err.println("Error "+e);
            return false;
        }
    }
    
    public boolean retirar(usuarios persona, int cantidad){
        
        double retiro = persona.getDinero();
        retiro -= cantidad;
        
        Connection conexion = getConnection();
        try{
            if(retiro>=0){
                ps = conexion.prepareStatement("update cowBank set dinero=? where nombre=?");
                ps.setDouble(1,retiro);
                ps.setString(2, persona.nombre);
                int comprobacion = ps.executeUpdate();
                if(comprobacion > 0){
                    return true;
                }else{
                    return false;
                }
            }else{
                JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                return false;
            }
            
        }catch(Exception e){
            System.err.println("Error "+e);
            return false;
        }finally{
             try{
                 conexion.close();
             }catch(SQLException e){
                 System.err.println("Error "+e);
                 return false;
             }
        }
    }

}
