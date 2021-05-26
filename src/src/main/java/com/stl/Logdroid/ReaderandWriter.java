/**
 * 
 */
package com.stl.Logdroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @author Rui Zhou
 * @author Mohammad Hamdaqa
 * @author Wahab Hamou-Lhadj
 *
 */
public class ReaderandWriter {

	public static void main(String[] args) {
		ArrayList<String> file = readToString("/Users/tonyzhou/Desktop/University/logging/merge.xml");

		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).contains(".xml") && LogDef.isLLOC(file.get(i + 1))) {
				// System.out.println(file.get(i));
				// System.out.println(file.get(i+1)); //taint point with log
				try {
					SAXReader sax = new SAXReader();
					StringReader read = new StringReader(file.get(i + 1));
					Document document = sax.read(read);
					Element root = document.getRootElement();

					List<Element> results = root.element("Results").elements();
					for (Element result : results) {
						System.out.println("Sink Point: " + result.element("Sink").attribute("Statement").getData());// get
																														// the
																														// Sink
						System.out.println("Sink Method: " + result.element("Sink").attribute("Statement").getData());// get
																														// the
																														// Sink
						System.out.println();
						List<Element> sources = result.element("Sources").elements();
						for (Element source : sources) {
							System.out.println("Source Statement: " + source.attribute("Statement").getData());
							System.out.println("Source Method: " + source.attribute("Method").getData());
						}
						System.out.println();
					}

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	// All of file convert to list, each line is one object
	public static ArrayList<String> readToString(String filePath) {
		File file = new File(filePath);
		ArrayList<String> res = new ArrayList<String>();
		if (!file.exists()) {
			return res;
		}
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			while ((str = br.readLine()) != null) {
				res.add(str);
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return res;
	}
	
	public static void writeToFile(ArrayList<String> sourceArrayList, String out_path) throws IOException {
		Writer writer = new FileWriter(out_path,true);
		
		for(int j=0;j<sourceArrayList.size();j++)
			writer.write(sourceArrayList.get(j)+"\n");
		writer.close();
	}
}

