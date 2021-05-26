# MobiLogLean
Provide instructions on how to run the tool in Readme. Also refer to the SANER paper.

## LogDroid Usage

1. Put the APK in the folder 'LogDroid/bin'

2. Use CMD and run start.sh

```
sudo bash start.sh
```

3. Check the file in the folder 'LogDroid/result' - the results of Analysis

## bin\ - Include all the java file of this LogDroid

LogDroid.jar 
- To extend the library of Log, go to 'com.stl.Logdroid.LogDef.java'
- To modify the power of different source categorization, go to 'com.stl.Logdroid.App.java.'
 
flowdroid.jar - This file includes all the dependencies for Taint Flow Analysis. 
