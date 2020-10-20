package org.nivi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
	
	try {
	    InputStream input = App.class.getClassLoader().getResourceAsStream("common.properties");
            if (input == null) {
                System.out.println("Sorry, unable to find common.properties");
                return;
            }

            Properties prop = new Properties();

            // load a properties file from class path, inside static method
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("message"));
            System.out.println(prop.getProperty("additional"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
