# MobiLogLeak

MobiLogLeak is a tool that detects logging statements in Android apps that may leak sensitive data. The tool operates on apk files and does not require the presence of the source code.
It combines decompilation technology and taint flow analysis to analyse the content of apps, looking for logging statements that may reveal sensitive data.

For more details, please refer to the following paper. Also please cite this paper if you use the tool:

R. Zhou, M. Hamdaqa, H. Cai, and A. Hamou-Lhadj, <a href ="http://users.encs.concordia.ca/~abdelw/papers/saner20_mobilogloeak_preprint.pdf"> 
"MobiLogLeak: A preliminary study on data leakage caused by poor logging practices,"</a> In Proc. of the IEEE 27th  International Conference on Software Analysis, Evolution, and Reengineering (SANER'20), pp. 577-581, 2020. 




# Instructions


*Please do not PUSH to 'main' branch. Create an new branch for yourself, and change the code or file structure on it, many thanks ;)*


# 1. LogDroid Usage Steps:

1. Put the APKs in the folder "bin" - I improve the shell command, now it can make the taint flow analysis for **all** the apks in "bin" folder. Don't have to run it one by one.
2. Use CMD and run start.sh

```
sudo bash start.sh
```
3. Check the file in the folder "result" - the results of Analysis

# 2. LogDroid Modification Steps:

1. Change the code in **src/**
2. Generate jar file and name it as Logdroid.jar
3. Put it into "lib\" and replace the original one

# 3. LogDroid Structure

## bin\

start.sh - main shell file to run the tool

00....apk - an app example with data leakage

## lib\ - Include all the jar files of this LogDroid

LogDroid.jar 
- To extend the library of Log, go to 'com.stl.Logdroid.LogDef.java'
- To modify the power of different source categorization, go to 'com.stl.Logdroid.App.java.'

flowdroid.jar - This file includes all the dependencies of [Flowdroid](https://github.com/secure-software-engineering/FlowDroid) for Taint Flow Analysis. 

## src\ - Java source code

# Developers:

MobiLogLeak was developed at the SRT-Lab, ECE, Concordia University. The tool was designed and developed by the following individuals:

<ul>
<li> Rui Zhou (Main developer) </li>
<li> Wahab Hamou-Lhadj (Science director and project supervisor) </li>
<li> Haipeng Cai (Science director and project co-supervisor) </li>
</ul>


# Contact Information

If you have questions about the tool implementation, please contact Rui Zhou (tonyzhou@live.cn). If you have questions about the scientific approach behind MobiLogLeak, please contact
Dr. Wahab Hamou-Lhadj at wahab.hamou-lhadj@concordia.ca
