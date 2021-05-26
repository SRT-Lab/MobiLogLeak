package com.stl.Logdroid;
/**
 * @author Rui Zhou
 * @author Mohammad Hamdaqa
 * @author Wahab Hamou-Lhadj
 *
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App 
{
	
	private static final Logger logger = LoggerFactory.getLogger(App.class.getName());
	public static String App_Name=null;
	public static String Out_Path=null;
	
	/**
	 * @param XML file path
	 */
    public static void main( String[] args )
    {
    	
    	App_Name=args[0];
    	ArrayList<String> reports= Deal_XML.XML(args[0]);

    	try {
			ReaderandWriter.writeToFile(reports, args[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    //test the program inside the code
    //set the static text for input and ouput file path
    public static void main_test( String[] args )
    {
    	App_Name="*.apk";//give the apk path here
    	Out_Path="";//give the result folder path here
    	
    	ArrayList<String> reports= Deal_XML.XML(App_Name);

    	try {
			ReaderandWriter.writeToFile(reports, Out_Path);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
