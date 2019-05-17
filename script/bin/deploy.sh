#!/bin/sh

cd $(dirname $0)/../..

mkdir -p "logs"

echo =================================
echo Starting to run sofastack samples...
echo =================================
echo Begin to execute 'mvn clean package' .
mvn clean package -DskipTests
echo Project package finished. Begin to start each system.

# sofastack-cloud-sys-static
echo =================================
echo Begin to start sofastack-cloud-sys-static system
start_time=`date +%s`
java -jar sofastack-cloud-sys-static/target/sofastack-cloud-sys-static-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
sh script/bin/healthcheck.sh "http://localhost:8088/actuator/readiness"
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"

# sofastack-cloud-sys-user
echo =================================
echo Begin to start sofastack-cloud-sys-user system
start_time=`date +%s`
java -jar sofastack-cloud-sys-user/target/sofastack-cloud-sys-user-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
sh script/bin/healthcheck.sh "http://localhost:8044/actuator/readiness"
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"

# sofastack-cloud-sys-trading
echo =================================
echo Begin to start sofastack-cloud-sys-trading system
start_time=`date +%s`
java -jar sofastack-cloud-sys-trading/target/sofastack-cloud-sys-trading-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
sh script/bin/healthcheck.sh "http://localhost:8055/actuator/readiness"
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"

# sofastack-cloud-sys-auth
echo =================================
echo Begin to start sofastack-cloud-sys-auth system
start_time=`date +%s`
java -jar sofastack-cloud-sys-auth/target/sofastack-cloud-sys-auth-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
sh script/bin/healthcheck.sh "http://localhost:8033/actuator/readiness"
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"

# sofastack-cloud-sys-accounting
echo =================================
echo Begin to start sofastack-cloud-sys-accounting system
start_time=`date +%s`
java -jar sofastack-cloud-sys-accounting/target/sofastack-cloud-sys-accounting-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
echo Do not need to readiness check
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"

# sofastack-cloud-sys-mq-server
echo =================================
echo Begin to start sofastack-cloud-sys-mq-server system
start_time=`date +%s`
java -jar sofastack-cloud-sys-mq-server/target/sofastack-cloud-sys-mq-server-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
echo Do not need to readiness check
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"

# sofastack-cloud-biz-web
echo =================================
echo Begin to start sofastack-cloud-biz-web system
start_time=`date +%s`
java -jar sofastack-cloud-biz-web/target/sofastack-cloud-biz-web-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
sh script/bin/healthcheck.sh "http://localhost:8011/actuator/readiness"
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"

# sofastack-cloud-api-gateway
echo =================================
echo Begin to start sofastack-cloud-api-gateway system
start_time=`date +%s`
java -jar sofastack-cloud-api-gateway/target/sofastack-cloud-api-gateway-0.0.1-SNAPSHOT.jar >> ./logs/start-up.log &
sh script/bin/healthcheck.sh "http://localhost:8080/actuator/readiness"
end_time=`date +%s`
elapse_time=$((${end_time}-${start_time}))
echo -e "\n exec jar takes ${elapse_time} seconds\n"
echo =================================
echo deploy end



