/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import clases.buses;
import clases.viajes;
import clases.chofer;
import frtransdavand.Mainchofer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static modelo.conexion.server;

/**
 *
 * @author andres alcaraz
 *         david herrera
 */
public class LogicaDeNegocio {
    
    
    private static Connection con;
 
    public LogicaDeNegocio() {
        con = conexion.establecerConexionSQL();
    }

    /**
     * metodo que llama a otro metodo con el fin de validar un usuario en la base de datos.
     */
    public boolean validarUsuario(String usuario, String clave) {
        boolean b = false;
        ResultSet rs = conexion.validarUsuario(con, usuario, clave);
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return b;
    }

        
    
    /**
     * Metodo que llama a otro metodo con el fin de validar si existe un chofer en la base de datos. 
     */
    public boolean validarSiExistech(String usuario) {
        boolean b = false;
        ResultSet rs = chofer.validarSiExistech(con, usuario);
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return b;
    }
    
    /**
    * Metodo que llama a otro metodo con el fin de validar si existe un viaje en la base de datos.
    */
    public boolean validarSiExistevj(String cedula, int ruta, String placa, String fecha) {
        boolean b = false;
        ResultSet rs = viajes.validarSiExistevj(con,cedula,ruta,placa,fecha);
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return b;
    }
    
    /**
    * Metodo que llama a otro metodo con el fin de validar si existe un bus en la base de datos.
    */
     public boolean validarSiExistebs(String usuario) {
        boolean b = false;
        ResultSet rs = buses.validarSiExistebus(con, usuario);
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return b;
    }
    
   /**
    * Metodo que devuelve un booleano para validar si el que ingresa es un usuario basico o un usuario administrador.
    */  
      public boolean validarROL(String usuario) {
        boolean b = false;
        ResultSet rs = validarROLsql(con, usuario);
        try {
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return b;
    }
    
      public static String contraseña(String cedula) throws ClassNotFoundException{

          String papito =chofer.password(con, cedula);
          System.out.println(papito);
          return papito;
      }
    /**
    * Metodo que realiza la consulta en la base de datos para diferenciar el tipo de usurio que ingresa.
    */
        public static ResultSet validarROLsql(Connection con, String cedula) {
        ResultSet rs = null;
       
        Statement st;
        try {
            st = con.createStatement();
            String strSql = "SELECT 1 FROM LOGIN WHERE LCEDULA = '" + cedula + "' AND ROL ='ADMIN'";
            rs = st.executeQuery(strSql);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return rs;
    }
    
   /**
    * Metodo que realiza la actualizacion de los registros en la base de datos.
    */
    public static int actualizarRegistros(Connection con, String Id, String Nombre,
            String Apellidos, String Telefono, String perfil, int Edad, String Direccion) {
        int cantreg = 0;
        try {
            Statement st = con.createStatement();
            String strSQL = "UPDATE Estudiantes\n"
                    + "SET Nombre = '" + Nombre + "', Edad=" + Edad + ", Direccion='" + Direccion + "'\n"
                    + "WHERE Id = '069';";
            cantreg = st.executeUpdate(strSQL);
        } catch (SQLException ex) {
            System.out.println("\nError al ACTUALIZAR: " + ex);
        }
        return cantreg;
    }
   
    
}
