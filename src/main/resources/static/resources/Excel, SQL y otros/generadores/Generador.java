package generadores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Generador {
	
	public static void lecturaNombresCompletos() throws IOException {
	   try {
		      File ficheroApellidos = new File("./src/main/resources/static/resources/Excel, SQL y otros/ListaApellidos.txt");
		      File ficheroNombre = new File("./src/main/resources/static/resources/Excel, SQL y otros/ListaNombres.txt");
		      BufferedReader readerApellidos = new BufferedReader(new FileReader(ficheroApellidos));
		      BufferedReader readerNombres = new BufferedReader(new FileReader(ficheroNombre));
		      
		      String[] apellidos=readerApellidos.lines().toArray(String[]::new);
		      String[] nombres=readerNombres.lines().toArray(String[]::new);
		      Random generate = new Random();
		      Set<String> noRepeats= new HashSet<String>();
		      while(noRepeats.size()!=apellidos.length) {
		    	  String apellido1 = apellidos[generate.nextInt(apellidos.length)];
		    	  String apellido2 = apellidos[generate.nextInt(apellidos.length)];
		    	  if(!apellido1.equals(apellido2)) {
		    		  String nombreCompleto=nombres[generate.nextInt(nombres.length)] + " " + apellido1 + " " + apellido2;
		    		  noRepeats.add(nombreCompleto);
		    	  }
		      }
		      Iterator<String> it = noRepeats.iterator();
		      while(it.hasNext()){
		    	    System.out.println(it.next());
		      }
		      
		      readerApellidos.close();
		      readerNombres.close();
		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    }
	}
	public static void main(String[] args) throws IOException {
		lecturaNombresCompletos();
		
	}

	
}
