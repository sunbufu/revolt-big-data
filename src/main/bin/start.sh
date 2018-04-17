#!/bin/sh
echo "server starting"
nohup java -jar revolt-big-data-0.0.1-SNAPSHOT.jar &
echo $! > rbd.pid
echo "success"