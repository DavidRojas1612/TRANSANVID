package SERVIDOR;

 import java.net.*;
import java.io.*;
import modelo.LogicaDeNegocio;
import clases.chofer;

public class servidorTCP {
static ServerSocket ss;
static Socket s;
static DataOutputStream sa;
static DataInputStream entrada;



public static void escuchar() throws IOException{
    System.out.println("..Esperando cliente...");
    s=ss.accept();
    System.out.println("..conectado - "+s.getInetAddress()+s.getPort());
    recibir();
}//escuchar
   
public static void recibir() throws IOException{
    try{
    entrada = new DataInputStream(s.getInputStream());
    String cadena = entrada.readUTF();
    System.out.println(".."+cadena);
    String a;
    LogicaDeNegocio ln = new LogicaDeNegocio();
    a = ln.contraseña(cadena);
        System.out.println(a);
            // enviar("el_cuadrado");
    enviar(a);        
    s.close();
    escuchar();
    
    }catch(Exception ioe){
        System.out.println("..Cliente desconectado");
        escuchar();
    }
  
}//recibir

    static void enviar(String dato) throws IOException{
        sa = new DataOutputStream(s.getOutputStream());
        sa.writeUTF(dato);
    }//enviar
        
public static void main(String[] arg) throws IOException {
ss = new ServerSocket(12345,30000);
escuchar();

}//main
}//clase