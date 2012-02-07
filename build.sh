#!/usr/bin/env sh
cp build.xml build.xml.bkp
cp nbproject/project.properties project.properties.bkp
sed "s|file.reference.antlr3.jar=lib/antlr-3.2.jar|file.reference.antlr3.jar=/usr/share/java/antlr3.jar|" nbproject/project.properties > tmp.properties
mv tmp.properties nbproject/project.properties
cp build-linux.xml build.xml
ant clean jar #build the project
mv build.xml.bkp build.xml
mv project.properties.bkp nbproject/project.properties
