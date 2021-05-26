package com.stl.Logdroid;

public class LogDef {

	public static boolean isLLOC(String s) {
		String log = s.toLowerCase();
		//list of log library
		//* many other log library is dressed by android default log
		//TODO add more to make it closer to real world
		String[] logSymbol = { "android.util.log:"};
		for (String i : logSymbol) {
			if (log.contains(i))
				return true;
		}
		return false;
	}
}
