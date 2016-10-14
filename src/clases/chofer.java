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
 * @author david herrera
 *         andres alcaraz
 */
public class chofer {

    public String cedula;
    public String nombre;
    public String Apellido1;
    public String Apellido2;
    public String celular;
    public String contraseña;
    static Scanner teclado = new Scanner(System.in);
    private Connection con;
    
    
    
   /**
    * Metodo que realiza la consulta en la base de datos para validar si existe un chofer en la base de datos.
    */
     public static ResultSet validarSiExistech(Connection con, String cedula) {
        ResultSet rs = null;
        Statement st;
            try {
            st = con.createStatement();
            String strSql = "SELECT 1 FROM CHOFER WHERE CEDULA = '" + cedula + "'";
            rs = st.executeQuery(strSql);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return rs;
    }
    
     public static String password(Connection con, String user) throws ClassNotFoundException
     {
         ResultSet rs;
         Statement st;
         try
            {          
             chofer chf = new chofer();
             st = con.createStatement();
             String sqlconsult = "SELECT LCONTRASENA FROM LOGIN WHERE LCEDULA = '"+user+"';";
             rs = st.executeQuery(sqlconsult);
             while (rs.next()) {
             
             chf.setContraseña(rs.getString(1));
             }
             user=chf.getContraseña();
             
         }catch (SQLException ex)
         {
             System.out.println("Error" + ex);
         }
         return user;
     }
    
     // metodo que se encarga de solicitar la informacion para insertar un conductor ó chofer
    public void InsertDriver()
    {
        LogicaDeNegocio con = new LogicaDeNegocio();
        Connection cone = conexion.establecerConexionSQL();
        String cedula;
        String nombre;
        String apellido1;
        String apellido2;
        String celular;
        teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* CEDULA: ");
        cedula = teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* NOMBRE: ");
        nombre=teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* APELLIDO1: ");
        apellido1 = teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* APELLIDO2: ");
        apellido2 = teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* CELULAR: ");
        celular = teclado.nextLine();
        System.out.println("*******************");
        
        LogicaDeNegocio ln = new LogicaDeNegocio();
         
        if (!ln.validarSiExistech(cedula)){
            insertarCh(cedula, nombre, apellido1, apellido2, celular);
            System.out.println("******************************************");
            System.out.println("****** Chofer Insertado con Exito ********");
            System.out.println("******************************************");
        }else {
             System.out.println("");
             System.out.println("chofer ya existe");
             System.out.println("");
        }
        
    }
     
     
     
    /**
     * Metodo de insercion de datos en la tabla chofer.
     */
    public void insertarCh(String cedula, String Nombre, String apellido1, String apellido2, String celular) {
        Statement st;
        try {

            String sql = "Insert into CHOFER (CEDULA,NOMBRE,APELLIDO1,APELLIDO2,CELULAR) values ('" + cedula + "','" + Nombre + "','" + apellido1 + "','" + apellido2 + "'," + celular + ");";
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    


    /**
    * Metodo que inserta un nuevo usuario con una contraseña en la tabla login. 
    */
     public void insertarchlg(String cedula, String contrasena) {
        Statement st;
        try {

            String sql = "Insert into LOGIN (LCEDULA,LCONTRASENA,ROL) values ('" + cedula + "','" + contrasena + "','USUARIO');";
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    /**
     * Metodo que lista los datos de la tabla chofer.
     */
    public ArrayList<chofer> LisChofer() throws ClassNotFoundException {
        ArrayList<chofer> chofer;
        chofer = new ArrayList();
        Statement st;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            String sql1 = "SELECT * FROM CHOFER;";

            ResultSet rs = st.executeQuery(sql1);

            while (rs.next()) {
                chofer chf = new chofer();
                chf.setCedula(rs.getString("CEDULA"));
                chf.setNombre(rs.getString("NOMBRE"));
                chf.setApellido1(rs.getString("APELLIDO1"));
                chf.setApellido2(rs.getString("APELLIDO2"));
                chf.setCelular(rs.getString("CELULAR"));

                chofer.add(chf);

            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Listado");
        }

        return chofer;
    }

    /**
     * Metodo que elimina un registro de la tabla login.
     */         
       public void eliminarRegistrologin(String ecedula) throws ClassNotFoundException, SQLException {
           
        
           Class.forName("org.mariadb.jdbc.Driver");
           con = conexion.establecerConexionSQL();
        
        try {
            Statement st = con.createStatement();
            String strSQL = "DELETE FROM LOGIN WHERE LCEDULA='"+ecedula+"';";
            st.executeUpdate(strSQL);
        } catch (SQLException ex) {
            System.out.println("\nError al ELIMINAR: " + ex);
        }
        
    }

  /**
   * metodo que se encarga de listar los datos de la tabla CHOFER.
   */       
    public void ConsultDriver() throws ClassNotFoundException 
    {
         ArrayList<chofer> chofer;
 
        
         chofer = LisChofer();
           for(chofer ch: chofer)
        {
            System.out.println("");
            System.out.println(""+ch.getCedula()+" - "+ch.getNombre()+" - "+ch.getApellido1()+" - "+ch.getApellido2()+" - "+ch.getCelular()+"\n");
            
        }
         
    }

        
    /**
     * metodo que se encarga de solicitar la informacion para eliminar un conductor ó chofer.
     */    
    public void DeleteDriver() throws ClassNotFoundException, SQLException
    {
       
        String cedula;
        teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* CEDULA: ");
        cedula = teclado.nextLine();
        
        eliminarRegistro(cedula);
        
    }
    /**
     * Metodo que elimina un registro de la tabla chofer.
     */         
       public void eliminarRegistro(String ecedula) throws ClassNotFoundException, SQLException {
           
        
           Class.forName("org.mariadb.jdbc.Driver");
           con = conexion.establecerConexionSQL();
        
        try {
            Statement st = con.createStatement();
            String strSQL = "DELETE FROM CHOFER WHERE CEDULA='"+ecedula+"';";
            st.executeUpdate(strSQL);
        } catch (SQLException ex) {
            System.out.println("\nError al ELIMINAR: " + ex);
        }
        
    }


    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }

    public chofer() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    

}
