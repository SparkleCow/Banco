/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import modelo.*;
import vistas.*;

public class controlador implements ActionListener{
    
    private ventanaPrincipal principal;
    private ventanaInicioSesion inicioSesion;
    private ventanaRegistro registro;
    private usuarios persona;
    private accionesJDBC acciones;
    private bancoPrincipal menuBanco;
    private int numeroVentana = 0;
    
    
    public controlador(){
         iniciarBanco();
         acciones = new accionesJDBC();
        
    }
    
    public void iniciarMenuBanco(){
        menuBanco = new bancoPrincipal();
        menuBanco.setVisible(true);
        menuBanco.btnConsignar.addActionListener(this);
        menuBanco.btnRetirar.addActionListener(this);
    }
    
    public void iniciarBanco(){
        principal = new ventanaPrincipal();      
        principal.setVisible(true);
        principal.botonInicio.addActionListener(this);
        principal.botonRegistro.addActionListener(this);  
    }
    
    public void iniciarInicioSesion(){
        inicioSesion = new ventanaInicioSesion();
        inicioSesion.setVisible(true);
        inicioSesion.botonIniciar.addActionListener(this);
        inicioSesion.botonRegresar.addActionListener(this);
    }
    
    public void iniciarRegistro(){
        registro = new ventanaRegistro();
        registro.setVisible(true);
        registro.botonRegistro.addActionListener(this);
        registro.botonRegresar.addActionListener(this);
    }
    
    public void limpiarRegistro(){
        registro.textoNombre.setText("");
        registro.textoContraseña.setText("");
        registro.textoRepetir.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((numeroVentana==2)&&(e.getSource() == registro.botonRegistro)) {
            if (registro.textoContraseña.getText().equals(registro.textoRepetir.getText())) {
                persona = new usuarios(registro.textoNombre.getText(), registro.textoContraseña.getText(), 0.0);
                limpiarRegistro();
                acciones.ingresarNuevoUsuario(persona);
                JOptionPane.showMessageDialog(null, "Registro realizado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }
        }if(e.getSource()==principal.botonInicio){
            principal.dispose();
            iniciarInicioSesion();
            numeroVentana = 1;          
        }if((numeroVentana==1)&&(e.getSource()==inicioSesion.botonIniciar)){     
                persona = new usuarios(inicioSesion.textoNombreInicio.getText(),inicioSesion.textoContraseñaInicio.getText(), 0.0);
                if(acciones.encontrarUsuario(persona)){ 
                    inicioSesion.dispose();
                    iniciarMenuBanco();
                    menuBanco.setPersona(persona);
                    menuBanco.setDinero();
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                }           
        }if((numeroVentana==1)&&(e.getSource()==inicioSesion.botonRegresar)){
            inicioSesion.dispose();
            iniciarBanco();
            numeroVentana = 0;
        }if(e.getSource()==principal.botonRegistro){
            principal.dispose();
            iniciarRegistro();
            numeroVentana = 2;
        }if((numeroVentana==2)&&(e.getSource()==registro.botonRegresar)){
            registro.dispose();
            iniciarBanco();
            numeroVentana = 0;
        }       
        if(menuBanco!=null && e.getSource()==menuBanco.btnConsignar){
            int dineroConsignado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la cantidad de dinero que deseas consignar a tu cuenta"));
            acciones.consignar(persona, dineroConsignado);
            acciones.actualizarUsuario(persona);
            menuBanco.setDinero();
        }
        if(menuBanco!=null && e.getSource()==menuBanco.btnRetirar){
            int dineroRetirado = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa la cantidad de dinero que deseas retirar de tu cuenta"));
            acciones.retirar(persona, dineroRetirado);
            acciones.actualizarUsuario(persona);
            menuBanco.setDinero();
        }
    }
           
}

    

