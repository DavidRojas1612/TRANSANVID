/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.LogicaDeNegocio;
import modelo.conexion;
import static modelo.maincho.validar;

public class viajes {
      static Scanner teclado = new Scanner(System.in);

    public String cedula;
    public String nombre;
    public String Apellido1;
    public int celular;

    public String placa;
    
    public String marca;

    public String codigo_r;
    public String mun_origen;
    public String mun_destino;

    String chofer_cedula;
    String bus_placa;
    String fecha_v;
    public String ruta_codigo_r;
    private Connection con;

    public viajes() {

    }
    
     
   /**
    * metodo que se encarga de solicitar la informacion para eliminar un viaje.
    */
    public void DeleteTrip() throws ClassNotFoundException, SQLException
    {
        
        String cedula;
        String fecha1;
        String fecha2;
        String fecha3;
        teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* CEDULA: ");
        cedula = teclado.nextLine();
        System.out.println("*******************");
        System.out.println("* FECHA *");
        System.out.print("AÑO: ");
        fecha1 = teclado.nextLine();
        System.out.print("MES: ");
        fecha2 = teclado.nextLine();
        System.out.print("DIA: ");
        fecha3 = teclado.nextLine();
        String fecha = fecha1+"/"+fecha2+"/"+fecha3;
        eliminarRegistro(cedula, fecha);
        
        
    }
    
     
   /**
    * metodo que se encarga de listar los datos de la tabla VIAJES.
    */     
    public  void ConsultTrip() throws ClassNotFoundException
    {
        ArrayList<viajes> viaje;
        
        LogicaDeNegocio con = new LogicaDeNegocio();
        viaje = ListViajes();
            for(viajes vj: viaje)
        {

            System.out.println("["+vj.getCedula()+"]\t["+vj.getNombre()+"]\t["+vj.getPlaca()+"]\t["+vj.getFecha_v()+"]\t["+vj.getRuta_codigo_r()+"]\t["+vj.getMun_origen()+"]\t["+vj.getMun_destino()+"\t\n");
            
        }
        
    }
    
      public  void ConsultTripch(String usuario) throws ClassNotFoundException, SQLException
    {
        ArrayList<viajes> viaje;
        
        LogicaDeNegocio con = new LogicaDeNegocio();
        viaje = ListViajesch(usuario);
            for(viajes vj: viaje)
        {
            
            System.out.println("["+vj.getCedula()+"]\t["+vj.getNombre()+"]\t["+vj.getPlaca()+"]\t["+vj.getFecha_v()+"]\t["+vj.getRuta_codigo_r()+"]\t["+vj.getMun_origen()+"]\t["+vj.getMun_destino()+"]\t\n");
            
        }
            validar();
    }
    
    
   /**
    * metodo que se encarga de solicitar la informacion para insertar un viaje.
    */
    public  void InsertTrip()
    {
        String cedula;
        int ruta;
        String placa ;
        String fecha1;
        String fecha2;
        String fecha3;
        System.out.println("*******************");
        System.out.print("* CEDULA: ");
        cedula = teclado.nextLine();
        System.out.println("*******************");
        System.out.print("* RUTA: ");
        ruta=teclado.nextInt();
        System.out.println("*******************");
        teclado.nextLine();
        System.out.print("* PLACA: ");
        placa = teclado.nextLine();
        System.out.println("*******************");
        System.out.println("* FECHA *");
        System.out.print("DIA: ");
        fecha1 = teclado.nextLine();
        System.out.print("MES: ");
        fecha2 = teclado.nextLine();
        System.out.print("AÑO: ");
        fecha3 = teclado.nextLine();
        
        
        String fecha = fecha1+"/"+fecha2+"/"+fecha3;
        String fechav = fecha3+"-"+fecha2+"-"+fecha1;
        LogicaDeNegocio ln = new LogicaDeNegocio();
        if(!ln.validarSiExistevj(cedula,ruta,placa,fechav))
        {
            insertar(cedula, ruta, placa, fecha);
            System.out.println("******************************************");
            System.out.println("****** Viaje Insertado con Exito *********");
            System.out.println("******************************************");
        }else
        {
            System.out.println("");
            System.out.println(" Viaje ya Existe");
            System.out.println("");
        }
        
        
    }
     
   /**
    * Metodo que realiza la consulta en la base de datos para validar si existe un viaje.
    */
     public static ResultSet validarSiExistevj(Connection con, String cedula, int ruta, String placa, String fecha) {
        ResultSet rs = null;
        Statement st;
            try {
            st = con.createStatement();
            String strSql = "SELECT 1 FROM VIAJES WHERE CHOFER_CEDULA = '"+cedula+"' AND RUTA_CODIGO_R = "+ruta+" AND BUS_PLACA= '"+placa+"' AND FECHA_V= '"+fecha+"';";
            rs = st.executeQuery(strSql);
        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return rs;
    }
    
    
   /**
    * Metodo que realiza la insercion de datos en la tabla viajes.
    */
    public void insertar(String cedula, int ruta, String placa, String fecha) {
        Statement st;
        try {

            String sql = "Insert into VIAJES (CHOFER_CEDULA,RUTA_CODIGO_R,BUS_PLACA,FECHA_V) values ('" + cedula + "'," + ruta + ",'" + placa + "',STR_TO_DATE('" + fecha + "','%d/%m/%Y'));";
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

   /**
    * Metodo que lista los datos de la tabla viajes.
    */
    public ArrayList<viajes> ListViajes() throws ClassNotFoundException {
        ArrayList<viajes> viaje;
        viaje = new ArrayList();
        Statement st;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            String sql1 = "SELECT CEDULA, NOMBRE, PLACA, FECHA_V,RUTA_CODIGO_R,MUNICIPIO_CODIGO_ORINGEN, MUNICIPIO_CODIGO_DESTINO FROM CHOFER JOIN VIAJES ON CEDULA=CHOFER_CEDULA JOIN BUS ON BUS_PLACA=PLACA JOIN RUTA ON RUTA_CODIGO_R=CODIGO_R GROUP BY CEDULA,RUTA_CODIGO_R,BUS_PLACA;";
            String sql2 = "SELECT NOMBRE_MUN FROM CHOFER JOIN VIAJES ON CEDULA=CHOFER_CEDULA JOIN BUS ON BUS_PLACA=PLACA JOIN RUTA ON RUTA_CODIGO_R=CODIGO_R JOIN MUNICIPIOS ON MUNICIPIO_CODIGO_ORINGEN = CODIGO_MUN GROUP BY CEDULA,RUTA_CODIGO_R,BUS_PLACA;";
            String sql3 = "SELECT NOMBRE_MUN FROM CHOFER JOIN VIAJES ON CEDULA=CHOFER_CEDULA JOIN BUS ON BUS_PLACA=PLACA JOIN RUTA ON RUTA_CODIGO_R=CODIGO_R JOIN MUNICIPIOS ON MUNICIPIO_CODIGO_DESTINO = CODIGO_MUN GROUP BY CEDULA,RUTA_CODIGO_R,BUS_PLACA;";
            ResultSet rs = st.executeQuery(sql1);
            ResultSet rs2 = st.executeQuery(sql2);
            ResultSet rs3 = st.executeQuery(sql3);
            while (rs.next() && rs2.next() && rs3.next()) {
                viajes vj = new viajes();
                vj.setCedula(rs.getString("CEDULA"));
                vj.setNombre(rs.getString("NOMBRE"));
                vj.setPlaca(rs.getString("PLACA"));
                vj.setFecha_v(rs.getString("FECHA_V").substring(0, 10));
                vj.setRuta_codigo_r(rs.getString("RUTA_CODIGO_R"));
                vj.setMun_origen(rs2.getString("NOMBRE_MUN"));
                vj.setMun_destino(rs3.getString("NOMBRE_MUN"));
                viaje.add(vj);

            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Listado");
        }

        return viaje;
    }
    
   /**
    * Metodo que lista los datos de los choferes junto con las rutas que se le asignan. 
    */
      public ArrayList<viajes> ListViajesch(String cedula) throws ClassNotFoundException {
        ArrayList<viajes> viaje;
        viaje = new ArrayList();
        Statement st;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = conexion.establecerConexionSQL();
            st = con.createStatement();
            String sql1 = "SELECT CEDULA, NOMBRE, PLACA, FECHA_V,RUTA_CODIGO_R,MUNICIPIO_CODIGO_ORINGEN, MUNICIPIO_CODIGO_DESTINO FROM CHOFER JOIN VIAJES ON CEDULA=CHOFER_CEDULA JOIN BUS ON BUS_PLACA=PLACA JOIN RUTA ON RUTA_CODIGO_R=CODIGO_R WHERE CEDULA='"+cedula+"' GROUP BY CEDULA,RUTA_CODIGO_R,BUS_PLACA;";
            String sql2 = "SELECT NOMBRE_MUN FROM CHOFER JOIN VIAJES ON CEDULA=CHOFER_CEDULA JOIN BUS ON BUS_PLACA=PLACA JOIN RUTA ON RUTA_CODIGO_R=CODIGO_R JOIN MUNICIPIOS ON MUNICIPIO_CODIGO_ORINGEN = CODIGO_MUN WHERE CEDULA='"+cedula+"' GROUP BY CEDULA,RUTA_CODIGO_R,BUS_PLACA;";
            String sql3 = "SELECT NOMBRE_MUN FROM CHOFER JOIN VIAJES ON CEDULA=CHOFER_CEDULA JOIN BUS ON BUS_PLACA=PLACA JOIN RUTA ON RUTA_CODIGO_R=CODIGO_R JOIN MUNICIPIOS ON MUNICIPIO_CODIGO_DESTINO = CODIGO_MUN WHERE CEDULA='"+cedula+"' GROUP BY CEDULA,RUTA_CODIGO_R,BUS_PLACA;";
            ResultSet rs = st.executeQuery(sql1);
            ResultSet rs2 = st.executeQuery(sql2);
            ResultSet rs3 = st.executeQuery(sql3);
            while (rs.next() && rs2.next() && rs3.next()) {
                viajes vj = new viajes();
                vj.setCedula(rs.getString("CEDULA"));
                vj.setNombre(rs.getString("NOMBRE"));
                vj.setPlaca(rs.getString("PLACA"));
                vj.setFecha_v(rs.getString("FECHA_V").substring(0, 10));
                vj.setRuta_codigo_r(rs.getString("RUTA_CODIGO_R"));
                vj.setMun_origen(rs2.getString("NOMBRE_MUN"));
                vj.setMun_destino(rs3.getString("NOMBRE_MUN"));
                viaje.add(vj);

            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error en Listado");
        }

        return viaje;
    }

      /**
        * Metodo que elimina registros de la tabla viajes.
        */
       public void eliminarRegistro(String ecedula,String efecha) throws ClassNotFoundException, SQLException {
           
        
           Class.forName("org.mariadb.jdbc.Driver");
           con = conexion.establecerConexionSQL();
        
        try {
            Statement st = con.createStatement();
            String strSQL = "DELETE FROM VIAJES WHERE CHOFER_CEDULA='"+ecedula+"'and FECHA_V = '"+efecha+"'";
            st.executeUpdate(strSQL);
        } catch (SQLException ex) {
            System.out.println("\nError al ELIMINAR: " + ex);
        }
        
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

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRuta_codigo_r() {
        return ruta_codigo_r;
    }

    public void setRuta_codigo_r(String ruta_codigo_r) {
        this.ruta_codigo_r = ruta_codigo_r;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigo_r() {
        return codigo_r;
    }

    public void setCodigo_r(String codigo_r) {
        this.codigo_r = codigo_r;
    }

    public String getMun_origen() {
        return mun_origen;
    }

    public void setMun_origen(String mun_origen) {
        this.mun_origen = mun_origen;
    }

    public String getMun_destino() {
        return mun_destino;
    }

    public void setMun_destino(String mun_destino) {
        this.mun_destino = mun_destino;
    }

    public String getChofer_cedula() {
        return chofer_cedula;
    }

    public void setChofer_cedula(String chofer_cedula) {
        this.chofer_cedula = chofer_cedula;
    }

    public String getBus_placa() {
        return bus_placa;
    }

    public void setBus_placa(String bus_placa) {
        this.bus_placa = bus_placa;
    }

    public String getFecha_v() {
        return fecha_v;
    }

    public void setFecha_v(String fecha_v) {
        this.fecha_v = fecha_v;
    }

}
