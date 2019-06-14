#!/bin/sh

cd /opt/kafka_2.12-2.2.0/
echo "starting zookeeper"
bin/zookeeper-server-start.sh -daemon config/zookeeper.properties

echo "starting kafka server"
bin/kafka-server-start.sh -daemon config/server.properties

cd /home/shubhanshukumar/paytmdemo
echo "build paytmdemo"
./mvnw clean install

echo "starting app"
./mvnw spring-boot:run

