/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.conexion;

/**
 *
 * @author david herrera
 *         andres alcaraz
 */
public class marca {
    public String codigo;
    public String marca;
        private Connection con;
    
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
   /**
    * Metodo que muestra la marca del codigo que corresponde con un nombre.
    */
     public String MarcaCodigo(String nombre) {
        ResultSet rs = null;
        marca chf = new marca();
        Statement st;
            try {
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            String strSql = "SELECT CODIGO_MAR FROM MARCA WHERE NOMBRE_MAR = '" + nombre + "'";
            rs = st.executeQuery(strSql);
              while (rs.next()) {
                
                chf.setCodigo(rs.getString("CODIGO_MAR"));
                
                
            }
            rs.close();
            st.close();
            con.close();
          
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return chf.getCodigo();
    }
    

   /**
    * Metodo que lista los datos de la tabla marca.
    */
     public ArrayList<marca> Lismarca() throws ClassNotFoundException {
        ArrayList<marca> marca;
        marca = new ArrayList();
        Statement st;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            String sql1 = "SELECT * FROM MARCA;";

            ResultSet rs = st.executeQuery(sql1);

            while (rs.next()) {
                marca mar = new marca();
                mar.setCodigo(rs.getString(1));
                mar.setMarca(rs.getString(2));
                marca.add(mar);

            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Listado");
        }

        return marca;
    }
}
