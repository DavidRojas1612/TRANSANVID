/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import clases.buses;
import clases.chofer;
import clases.viajes;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author david
 */
public class maincho {

  static private Connection con;
  public static viajes vj = new viajes();
  public static chofer chf = new chofer();
  public static buses bs = new buses();
  static String usuario;
  static String contraseña;
  static Scanner teclado = new Scanner(System.in);

    public maincho() {
      
     }
   

   
    
    
    /**
    * metodo que valida el usuario y la contraseña.
    */
    public static void validar() throws ClassNotFoundException, SQLException
    {
            System.out.println("Digite s para Salir");
            System.out.print("Debe Ingresar un usuario: ");
            usuario = teclado.nextLine();
             if(usuario.equals("s"))
        {
            System.exit(0);
        }
            System.out.print("Debe Ingresar una clave: ");
            contraseña = teclado.nextLine();
               if(contraseña.equals("s"))
        {
            System.exit(0);
        }
       
            
        LogicaDeNegocio ln = new LogicaDeNegocio();
        if (!ln.validarUsuario(usuario, contraseña)) {
            System.out.println("Usuaio o clave no valida");
            System.out.println("");
            validar();
            
        }else
              if(ln.validarROL(usuario))  { 
                    menu();
                }else{
                 System.out.println("CEDULA \t\t  NOMBRE  \t PLACA  \t FECHA  \t RUTA  \t ORIGEN  \t DESTINO \t");

                     vj.ConsultTripch(usuario);                   
              }
    }
    
    
   /**
    * metodo que muestra el menu principal y llama a los demas metodos segun lo que seleccione el usuario.
    */ 
   public static void menu() throws ClassNotFoundException, SQLException
   {
                     con = conexion.establecerConexionSQL();
         int opc = 0;
        
         
         do
         {
            System.out.println("*************************");
            System.out.println("*   QUE DESEA HACER     *");
            System.out.println("*************************");
            System.out.println("  ------- VIAJES ------  ");
            System.out.println("* 1] CONSULTAR          *");
            System.out.println("* 2] INGRESAR           *");
            System.out.println("* 3] ELIMINAR           *");
            System.out.println("*************************");
            System.out.println("  ----- CHOFERES -----   ");
            System.out.println("* 4] CONSULTAR          *");
            System.out.println("* 5] INGRESAR           *");
            System.out.println("* 6] ELIMINAR           *");
            System.out.println("*************************");
            System.out.println("  ------- BUSES -------  ");
            System.out.println("* 7] CONSULTAR          *");
            System.out.println("* 8] INGRESAR           *");
            System.out.println("* 9] ELIMINAR           *");
            System.out.println("* 10] CERRAR SESION     *");
            System.out.println("*************************");
            System.out.print(" -Opcion: ");
            opc = teclado.nextInt();
            
            
            String fecha;
            switch(opc)
            {
                case 1:
                   System.out.println("CEDULA  \t NOMBRE  \t PLACA  \t FECHA  \t RUTA  \t ORIGEN  \t DESTINO \t ");
                   vj.ConsultTrip(); 
                    break;                 
                case 2:
                    vj.InsertTrip();
                   break;
                case 3: 
                    vj.DeleteTrip();
                    System.out.println("******************************************");
                    System.out.println("****** Viaje Eliminado con Exito *********");
                    System.out.println("******************************************");
                   break;
                case 4:
                    System.out.println("CEDULA - NOMBRE - APELLIDO1 - APELLIDO2 - CELULAR");
                    chf.ConsultDriver();
                   break;
                case 5:
                    chf.InsertDriver();
                   break;
                case 6:
                   chf.DeleteDriver();
                   break;
                case 7:
                    System.out.println("PLACA - MARCA_CODIGO");
                   bs.ConsultBus();
                   break;
                case 8:
                   bs.InsertBus();
                   break;
                case 9:
                   bs.DeleteBus();                   
                       
            }
             
             
         }while (opc != 10);
         teclado.nextLine();
         validar();

       
   }
    
    /**
     * Main del proyecto.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException { 
    
        validar();
    }  
    }
        