# MobiLogLean

This Tool works for Taint-Flow Analysis on Android apps, and generates the recommandation to solve the data leakage.
It's necessary to have the knowledge of taint flow analysis to work with this tool. 
If it's required to modify this tool, it's better to have a good understanding of operating Java with Shell command.

For more details, please check the publication below.

Saner 2020: [MobiLogLeak: A Preliminary Study on Data Leakage Caused by Poor Logging Practices](https://ieeexplore.ieee.org/document/9054831)

# LogDroid Usage

1. Put the APK in the folder 'LogDroid/bin'

2. Use CMD and run start.sh

```
sudo bash start.sh
```

3. Check the file in the folder 'LogDroid/result' - the results of Analysis

# LogDroid Structure

## bin\

start.sh - main shell file to run the tool

00....apk - an app example with data leakage

## lib\ - Include all the jar file of this LogDroid

LogDroid.jar 
- To extend the library of Log, go to 'com.stl.Logdroid.LogDef.java'
- To modify the power of different source categorization, go to 'com.stl.Logdroid.App.java.'

flowdroid.jar - This file includes all the dependencies of [Flowdroid](https://github.com/secure-software-engineering/FlowDroid) for Taint Flow Analysis. 
