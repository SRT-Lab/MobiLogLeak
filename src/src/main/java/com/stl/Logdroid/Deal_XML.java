package com.stl.Logdroid;

import java.io.StringReader;
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
public class Deal_XML {

	public static String sink_rationale = null;
	public static String sink_level = null;
	public static String source_type = null;
	public static String app_report = null;
	public static int level = 0;

	public static ArrayList<String> XML(String input_file) {

		// get the array for all lines in file
		ArrayList<String> file = ReaderandWriter.readToString(input_file);

		// how many log for sink, refer how many source data
		int log_sink = 0, log_source = 0;
		ArrayList<String> reports =new ArrayList<String>();
		reports.add("LogDroid Version: 0.0.1");
		reports.add("APP Name,"+ App.App_Name);
		
		reports.add("\n");
		
		reports.add("Possible Leakages");
		reports.add("Class Path,Function name,Log Level,Sink Rantionale,Source Type,Security Level,Leakage Reason,Suggestion");

		for (int i = 0; i < file.size(); i++) {
			// check if this app has sink
			if (file.get(i).contains("Sink")) {

				// for each app, init one array list for them and write to the output_file
				// can't move it after judge has log or not, the final file write need it
				ArrayList<String> sourceArrayList = new ArrayList<String>();
				

				try {
					// init the part to read XML
					SAXReader sax = new SAXReader();
					StringReader read = new StringReader(file.get(i));
					Document document = sax.read(read);
					Element root = document.getRootElement();

					// init the write for each app
					// Writer writer = new FileWriter(output_file,true);

					List<Element> results = root.element("Results").elements();

					for (Element result : results) {
						Element sink_undf = result.element("Sink");
						if (LogDef.isLLOC(sink_undf.attribute("Statement").toString())) {

							// if it contains log for sink, count it
							log_sink++;
							
							String sink_method=sink_undf.attribute("Method").toString();
							String[] sink_pre=sink_method.split("<|>");
							String[] sink_e=sink_pre[1].split(" ");
							
							sink_e[0]=sink_e[0].substring(0, sink_e[0].indexOf(":"));
							sink_e[2]=sink_e[2].substring(0, sink_e[2].indexOf("("));
							

							

							List<Element> sources = result.element("Sources").elements();

							// Loop all the sources
							for (Element source : sources) {
								// count how many sources are refered
								log_source++;

								
								report(sink_undf, source);
								
								String source_method=source.attribute("Method").toString();
								String[] source_pre=source_method.split("<|>");
								String[] source_e=source_pre[1].split(" ");
								
								// source class
								source_e[0]=source_e[0].substring(0, source_e[0].indexOf(":"));
								
								String reason = "The Developers may not know there is a log";
								if(sink_e[0].equals(source_e[0])){
									reason="The Developers may forget to delete log";
								}
								
								
								String recommandtion="Obfuscate";
								if(level<5) {
									recommandtion= "Be awared";
								} else if (level>7) {
									recommandtion = "Delete";
								} 
								
								//init the report for each path
								app_report=sink_e[0]+","+sink_e[2]+",";
								app_report+= sink_level+","+sink_rationale+","+source_type+","+level+","+reason+","+recommandtion;
								
								reports.add(app_report);
								//System.out.println(app_report);

							}
							
						}
					}

					// add the ALRS name to the file
					/**
					 * writer.write("ALRS_NAME: "+file.get(i)+"\n"); for(int
					 * j=0;j<sourceArrayList.size();j++) writer.write(sourceArrayList.get(j)+"\n");
					 * writer.write("\n"); writer.close();
					 **/

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		reports.add("\n");
		reports.add("This app has " + log_sink + " Log Sinks. "+ log_source + " Sources are leaked!");
		reports.add("*Security Problem: Low (0~5) Middle(5~8) High (8~10)");
		reports.add("\n");
		reports.add("*Copyright: Rui Zhou | Mohammad Hamdaqa | Wahab Hamou-Lhadj");

		
		return reports;
	}

	// Report the detail
	public static void report(Element sink_undf, Element source_undf) {

		int sink_t = 0; // the row
		int source_t = 0; // which category for source, the column
		level=0; // each time, the security level should be 0
		String cate_compl = SourceDefind.SourceDefind(source_undf.attribute("Statement").getData().toString());

		
		if (sink_undf.attribute("Statement").toString().contains("android.util.Log: int i")) {
			sink_level="Info";
			sink_rationale = "Other";
			level+=3;
		} else if (sink_undf.attribute("Statement").toString().contains("android.util.Log: int v")) {
			sink_level="Verbose";
			sink_rationale = "Others";
			level+=3;
		} else if (sink_undf.attribute("Statement").toString().contains("android.util.Log: int d")) {
			sink_level="Debug";
			sink_rationale = "Debug";
			level+=5;
		} else if (sink_undf.attribute("Statement").toString().contains("android.util.Log: int w")) {
			sink_level="Warn";
			sink_rationale = "Anomaly Detection";
			level+=1;
		} else if (sink_undf.attribute("Statement").toString().contains("android.util.Log: int e")) {
			sink_level="Error";
			sink_rationale = "Anomaly Detection ";
			level+=1;
		} else {
			System.out.println(sink_undf.attribute("Statement").toString());
		}

		if (cate_compl.contains("android.database.Cursor")) {
			source_type = "Database";
			level+=5;
		} else if (cate_compl.contains("LOCATION_INFORMATION")) {
			source_type = "Location";
			level+=3;
		} else if (cate_compl.contains("BLUETOOTH_INFORMATION")) {
			source_type = "Bluetooth";
			level+=1;
		} else if (cate_compl.contains("ACCOUNT_INFORMATION")) {
			source_type = "Account";
			level+=1;
		} else {
			source_type = "Network";
			level+=1;
		}
		
		// set Database 5 points, Debug 5 points
		// set Location 3 points, Other 3 points
		// set Network, Account, Bluetooth 1 point, Anomaly Detection 1 points
		
		// over 8, High
		// 5-8, middle
		// less than 5, low
		
		//System.out.println(level);
	}
}
