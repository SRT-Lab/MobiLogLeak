package com.stl.Logdroid;

import java.util.ArrayList;

public class SourceDefind {

	public static void main(String[] args) {
		String testString = "$r9 = virtualinvoke $r7.<android.content.pm.PackageManager: java.util.List queryBroadcastReceivers(android.content.Intent,int)>($r8, 0)";
		//System.out.println(SourceDefind(testString));;
		checkSource();
	}

	public static String SourceDefind(String source) {

		// split the source and get the function
		String[] splitStrings = source.split("[<>]");

		//read the sourceCat and take out the category
		//TODO for future usage, include this file into code.
		//the abstract path will cause problem
		String source_fileString = "G:\\Source_data\\SourceCat.txt";
		ArrayList<String> file = ReaderandWriter.readToString(source_fileString);

		// check which category it is
		for (int i = 0; i < file.size(); i++) {
			String targetString=splitStrings[1];
			if (splitStrings[1].contains("android.content.pm.PackageManager")) {
				targetString  = splitStrings[1].replace("android.content.pm.PackageManager", "android.app.ApplicationPackageManager");
			}
			
			if (file.get(i).contains(targetString)) {
				return file.get(i);
			} 
		}
		return "<"+splitStrings[1]+"> (NO_CATEGORY)";
	}
	
	public static void checkSource() {
		String source_fileString = "G:\\Source_data\\2017.txt";
		ArrayList<String> file = ReaderandWriter.readToString(source_fileString);
		for (int i = 0; i < file.size(); i++) {
			if(file.get(i).contains("Source_Category") && !isChecked(file.get(i))) {
				System.out.println(file.get(i));
			}
		}
	}
	
	public static boolean isChecked(String source) {
		String[] checkArrayList= {"(NETWORK_INFORMATION)","<android.database.Cursor: java.lang.String getString(int)> (NO_CATEGORY)","(LOCATION_INFORMATION)"};
		
		for(int i =0;i<checkArrayList.length;i++) {
			if(source.contains(checkArrayList[i])) {
				return true;
			}
		}
		
		return false;
	}
}
