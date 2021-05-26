# MobiLogLeak

This Tool works for Taint-Flow Analysis on Android apps, and generates the recommandation to solve the data leakage.

It's necessary to have the basic knowledge of Shell and taint flow analysis to work with this tool. 
If it's required to modify this tool, it's better to have a good understanding of git, operating Java with Shell command, Jimple code and Decompilation.

For more details, please check the publication below.

Saner 2020: [MobiLogLeak: A Preliminary Study on Data Leakage Caused by Poor Logging Practices](https://ieeexplore.ieee.org/document/9054831)

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
