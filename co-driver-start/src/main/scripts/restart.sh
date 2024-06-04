#!/bin/sh
project=co-driver-start
version=1.0
port=8087
profile=prod
ps -ef|grep ${project}|grep ${port} |grep java |awk '{print $2}' |xargs -I {} kill -9 {}
listen=`lsof -i:${port}|wc -l`
while [ $listen -ne 0 ] ; do
    echo "等待java进程结束..."
    sleep 1
    listen=`lsof -i:${port}|wc -l`
done
echo "java进程退出"

nohup /opt/java/jdk-17.0.11/bin/java -Xdebug -Xms8g -Xmx8g  -XX:MetaspaceSize=1024m -jar -Dspring.profiles.active=${profile} -Dserver.port=${port} ${project}-${version}.jar  >/dev/null 2>&1 &

listen=`lsof -i:${port}|wc -l`
while [ $listen -eq 0 ] ; do
    echo "等待java进程启动..."
    sleep 1
    listen=`lsof -i:${port}|wc -l`
done
echo "java进程启动完毕"

echo `lsof -i:${port}`
