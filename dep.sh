#!/bin/bash
echo  "start clean..."
mvn clean

echo  "start install..."
mvn install
echo "install end"

echo "destroy files in web contents"
# rm -Rf /opt/apache-tomcat-8.0.17/wtpwebapps/RESTfulItu
echo "destroy completed!!"


