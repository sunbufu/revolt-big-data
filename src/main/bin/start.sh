#!/bin/sh
echo "server starting"
BIN_DIR=`pwd`
echo $BIN_DIR
nohup java -jar $BIN_DIR/revolt-big-data-0.0.1-SNAPSHOT.jar &
echo $! > rbd.pid
echo "success"