#!/bin/sh
echo "server stopping"
PID=$(cat rbd.pid)
kill -9 $PID
echo "sucess"
rm rbd.pid