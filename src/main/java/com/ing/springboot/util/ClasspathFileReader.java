package com.ing.springboot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
 
    private static final String CONFIG_FILE = "/example.xml";
    
    public String readFileFromClasspath(String bankId) throws IOException
    {
        URL fileUrl = getClass().getResource(bankId+".mt940.txt");
        //System.out.println("url="+fileUrl.getPath());
        File file = new File(fileUrl.getFile());
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