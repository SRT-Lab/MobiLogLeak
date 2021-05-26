#echo "Folder name:  "$route;
# set fileformat=unix

# go to the apps folder get the taint point and callback

for apps in `ls *.apk`
    do
        appname=${apps%.*} # same extension name
        sudo java -jar ../lib/Flowdroid.jar -s ../lib/SourcesAndSinks.txt -p ../lib/android.jar -o sootOutput/$appname.xml -a $apps
    done


cd sootOutput/

for taints in `ls *.xml`
    do
        appname=${taints%.*}
        sudo java -jar ../../lib/Logdroid.jar $taints ../../result/$appname.csv
    done
