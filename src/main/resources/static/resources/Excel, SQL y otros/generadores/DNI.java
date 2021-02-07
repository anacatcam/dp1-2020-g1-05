package generadores;

import java.util.HashSet;
import java.util.Set;

public class DNI {
	    private static final char[] LETRAS_NIF = { 'T', 'R', 'W', 'A', 'G', 'M',
	            'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
	            'L', 'C', 'K', 'E' };
	    
	    public static String[] generaNif(String seed) {
	        if (seed != null) {
	            try {
	                Integer.parseInt(seed);
	            } catch (NumberFormatException ex) {  
	                return null;
	            }
	        } else {
	            seed = "";
	        }
	        Set<String> noRepeats= new HashSet<>();
	        int i=0;
	        while(i<200) {
	        	String numeroDNI = String.valueOf(Math.random()).concat(seed);
	        	String fullDNI = numeroDNI.substring(numeroDNI.length() - 8);

	        	int dniInt = Integer.parseInt(fullDNI);
	        	fullDNI = fullDNI + LETRAS_NIF[dniInt % 23];
	        	if(!noRepeats.contains(fullDNI)) {
	        		noRepeats.add(fullDNI);
	        	}
	        	i++;
	        }
	        return noRepeats.toArray(String[]::new);			
	    }
	        

	    public static void main(String[] args) {
	    	String[] res = generaNif(null);
	    	for(String i:res) {
	    		System.out.println(i);
	    	
	    	}
	    }
	    
}
