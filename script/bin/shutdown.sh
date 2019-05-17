#!/bin/sh
echo =================================
echo kill all sofastack samples process...
echo =================================

echo shutdown sofastack-cloud-sys-static
pid0=`ps aux | grep java | grep sofastack-cloud-sys-static | awk '{print $2;}'`
for pid in "${pid0[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown sofastack-cloud-sys-user
pid1=`ps aux | grep java | grep sofastack-cloud-sys-user | awk '{print $2;}'`
for pid in "${pid1[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown sofastack-cloud-sys-trading
pid2=`ps aux | grep java | grep sofastack-cloud-sys-trading | awk '{print $2;}'`
for pid in "${pid2[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown sofastack-cloud-sys-auth
pid3=`ps aux | grep java | grep sofastack-cloud-sys-auth | awk '{print $2;}'`
for pid in "${pid3[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown sofastack-cloud-sys-accounting
pid4=`ps aux | grep java | grep sofastack-cloud-sys-accounting | awk '{print $2;}'`
for pid in "${pid4[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown sofastack-cloud-sys-mq-server
pid5=`ps aux | grep java | grep sofastack-cloud-sys-mq-server | awk '{print $2;}'`
for pid in "${pid5[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown sofastack-cloud-biz-web
pid6=`ps aux | grep java | grep sofastack-cloud-biz-web | awk '{print $2;}'`
for pid in "${pid6[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown sofastack-cloud-api-gateway
pid7=`ps aux | grep java | grep sofastack-cloud-api-gateway | awk '{print $2;}'`
for pid in "${pid7[@]}"
do
    if [ -z "$pid" ]; then
        continue;
    fi
kill $pid
done

echo shutdown end



