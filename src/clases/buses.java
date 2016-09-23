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
import java.util.Scanner;
import modelo.LogicaDeNegocio;
import modelo.conexion;

/**
 *
 * @author andres alcaraz
 *         david herrera
 */


public class buses {
        static Scanner teclado = new Scanner(System.in);
    public String placa;
    public String marca;
    Connection con;

    /**
    * Get del atributo placa de la clase buses.
    */
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
     
    /**
    * metodo que se encarga listar los datos de la tabla BUS.
    */    
    public void ConsultBus() throws ClassNotFoundException, SQLException
    {
        ArrayList<buses> bus;
        
        bus = LisBuses();
           for(buses bs: bus)
        {
            System.out.println("");
            System.out.println(""+bs.getPlaca()+" - "+bs.getMarca()+"\n");
            
        }
        
    }
    
    
    /**
    * metodo que se encarga de solicitar la informacion para insertar un vehiculo ó bus.
    */  
    public  void InsertBus()
    {
        
        String placa;
        String MC;
        teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* PLACA: ");
        placa = teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* MARCA_CODIGO: ");
        MC=teclado.nextLine();
        
        LogicaDeNegocio ln = new LogicaDeNegocio();
        if(!ln.validarSiExistebs(placa))
        {
            insertarbus(placa, MC);
            System.out.println("******************************************");
            System.out.println("********* Bus Insertado con Exito ********");
            System.out.println("******************************************");
            
        }else
        {
            System.out.println("");
            System.out.println(" Bus ya Existe");
            System.out.println("");
        }
        
    }
    
    
   /**
    * metodo que se encarga de solicitar la informacion para eliminar un vehiculo ó bus.
    */ 
    public void DeleteBus() throws ClassNotFoundException, SQLException
    {
        LogicaDeNegocio cnx = new LogicaDeNegocio();
        String placa;
        String marca;
        teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* PLACA: ");
        placa = teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* MARCA_CODIGO: ");
        marca=teclado.nextLine();
        
        eliminarBus(placa, marca);
    }
    
    
   /**
    * metodo que se encarga de ejecutar la secuencia sql para eliminar un bus.
    */ 
    public void eliminarBus(String placa,String marca) throws ClassNotFoundException, SQLException {
           
        
           Class.forName("org.mariadb.jdbc.Driver");
           con = conexion.establecerConexionSQL();
        
        try {
            Statement st = con.createStatement();
            String strSQL = "DELETE FROM BUS WHERE PLACA='"+placa+"'and MARCA_CODIGO = '"+marca+"'";
            st.executeUpdate(strSQL);
            System.out.println("******************************************");
            System.out.println("********* Bus Eliminado con Exito ********");
            System.out.println("******************************************");
        } catch (SQLException ex) {
            System.out.println("");
            System.out.println("\nError al ELIMINAR: Primero debe eliminar los registros con la placa "+placa+" existentes en la tabla VIAJES");
            System.out.println("");
        }
        
    }
    
   /**
    * Metodo que realia la consulta en la base de datos para validar si esxiste un bus.
    */
     public static ResultSet validarSiExistebus(Connection con, String placa) {
        ResultSet rs = null;
        Statement st;
        try {
            st = con.createStatement();
            String strSql = "SELECT 1 FROM BUS WHERE PLACA = '" + placa + "'";
            rs = st.executeQuery(strSql);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return rs;
    }
    
    
    
    /**
     * Metodo que realiza la insercion del bus en la base de datos.
     */
    public  void insertarbus(String placa, String marca) {
        Statement st;
        try {

            String sql = "Insert into BUS (PLACA,MARCA_CODIGO) values ('" + placa + "','" +marca + "');";
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

   /**
    * Metodo que lista los datos de la tabla buses de la base de datos.
    */
    public ArrayList<buses> LisBuses() throws ClassNotFoundException {
        ArrayList<buses> busesito;
        busesito = new ArrayList();
        Statement st;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            String sql1 = "SELECT * FROM BUS;";

            ResultSet rs = st.executeQuery(sql1);

            while (rs.next()) {
                buses bs = new buses();
                bs.setPlaca(rs.getString(1));
                bs.setMarca(rs.getString(2));
   

                busesito.add(bs);

            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Listado");
        }

        return busesito;
    }
}
