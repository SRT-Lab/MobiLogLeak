# MobiLogLean
Provide instructions on how to run the tool in Readme. Also refer to the SANER paper.

## LogDroid Usage

1. Put the APK in the folder 'LogDroid/bin'

2. Use CMD and run start.sh

```
sudo bash start.sh
```

3. Check the file in the folder 'LogDroid/result'

## LogDroid Extension

To extend the library of Log, go to 'com.stl.Logdroid.LogDef.java'

To modify the power of different source categorization, go to 'com.stl.Logdroid.App.java.'
 
## Flowdroid_EXTENSION

This folder is used to package all the dependencies for Taint Flow. To operate it, please use the command:
```
java -jar soot-infoflow-cmd-jar-with-dependencies-print.jar -p android.jar -s SourcesAndSinks.txt -a @application -o sootOutput/
```

-p,--platformsdir <arg> Path to the platforms directory from the Android SDK
