#!/bin/sh

#!/bin/sh

cd /opt/kafka_2.12-2.2.0/
echo "stopping zookeeper"
bin/zookeeper-server-stop.sh -daemon config/zookeeper.properties

echo "stopping kafka server"
bin/kafka-server-stop.sh -daemon config/server.properties

