package CLIENTE;

import java.net.*;
import java.io.*;

public class clienteTCP{
	
	static Socket s;
	static DataOutputStream sa;
	static DataInputStream en;
	static String cadena;


	static void conectar(String laIp, String elP) 
	throws IOException, UnknownHostException{
		s = new Socket(laIp,Integer.parseInt(elP));
	}//conectar

	static void enviar(String dato) throws IOException{
	    sa = new DataOutputStream(s.getOutputStream());
	    sa.writeUTF(dato);
    }//enviar

    static void recibir() throws IOException{
	    en = new DataInputStream(s.getInputStream());
	    cadena = en.readUTF();
    	System.out.println("s-> "+cadena);
	    
    }//enviar

    public static void cerrar() throws IOException{
    	s.close();        
    }//cerrar
    
    
    
    
	public static void main(String[] arg) throws IOException{
		
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String laIp= "127.0.0.1";
		String elPuerto="12345";
		cadena="";
		
		//ciclo
		conectar(laIp, elPuerto);
		System.out.println("Conectado..");
		System.out.println("\nDigite Número ->");
		String num=br.readLine();
		//
		enviar(num);
		recibir();


	}

}//class