package com.ing.springboot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
 
/**
 * This class reads a resource file from classpath.
 * 
 * @author Hussein
 *
 */
public class ClasspathFileReader {
 
    public static String readFileFromClasspath(String bankId) throws IOException
    {
        URL fileUrl = ClasspathFileReader.class.getResource(bankId+".mt940.txt");
        File file = new File(fileUrl.getFile());
    	
    	//File file = new File("E:\\Java\\inghackathon\\src\\main\\java\\com\\ing\\springboot\\util\\"+bankId+".mt940.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br  = new BufferedReader(fr);
        String line = null;
        StringBuilder output = new StringBuilder();
        while((line=br.readLine())!=null)
        {
        	output.append(line);
        }
        
        if(output.length()==0)
        	throw new RuntimeException("No data founin swift file");
        
		return output.toString();
    }
    

}