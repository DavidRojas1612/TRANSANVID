package modelo;


import clases.viajes;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author andres alcaraz
 *         david  herrera
 */
public class conexion {

    private Connection con;
    static String db = "proyecto";
    static String user = "davand";
    static String password = "12345";
    static String server = "jdbc:mariadb://localhost/" + db;

/**
 * Constructor para cargar el driver MySQL.
 */   
    public conexion() {
    }

    public static Connection establecerConexionSQL() {
        Connection con = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(server, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("#### No se puede conectar con la base de datos ###");
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public static ResultSet validarUsuario(Connection con, String usuario, String clave) {
        ResultSet rs = null;
        Statement st;
        try {
            st = con.createStatement();
            String strSql = "SELECT 1 FROM LOGIN WHERE LCEDULA = '"
                    + usuario + "' AND LCONTRASENA = '" + clave + "'";
            rs = st.executeQuery(strSql);
        } catch (SQLException ex ) {
            System.out.println("Error: " + ex);
        }
        return rs;
    }

   


    /**
     * Cierra la conexion de la base de datos.
     */   
    public void cerrarConexionSQL() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Database connecion terminated");
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
