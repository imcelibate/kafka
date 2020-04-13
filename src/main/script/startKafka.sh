#!/bin/bash
# Removes old revisions of snaps
# CLOSE ALL SNAPS BEFORE RUNNING THIS
cd /usr/local
sudo kafka/bin/zookeeper-server-start.sh kafka/config/zookeeper.properties

cd /usr/local
sudo kafka/bin/kafka-server-start.sh kafka/config/server0.properties

cd /usr/local
sudo kafka/bin/kafka-server-start.sh kafka/config/server1.properties

cd /usr/local
sudo kafka/bin/kafka-server-start.sh kafka/config/server2.properties

cd /usr/local
kafka/bin/kafka-topics.sh --create \
  --zookeeper localhost:2181 \
  --replication-factor 1 --partitions 13 \
  --topic AMMA
  
cd /usr/local  
  
kafka/bin/kafka-topics.sh --create \
  --zookeeper localhost:2181 \
  --replication-factor 1 --partitions 13 \
  --topic GURU