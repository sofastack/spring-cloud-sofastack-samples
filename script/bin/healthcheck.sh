#!/bin/sh
HEALTH_URL=$1
HEALTH_CHECK_COMMOND="curl -s --connect-timeout 3 --max-time 5 ${HEALTH_URL}"
echo "        -- SOFA Boot CheckService"
echo "        -- HealthCheck URL : ${HEALTH_URL}"
#success:0;failure:1;timeout:2,and default value is failure=1
status=1
#default 120s
times=120
for num in $(seq $times);
 do
    sleep 1
    COSTTIME=$(($times - $num ))
    HEALTH_CHECK_CODE=`${HEALTH_CHECK_COMMOND} -o /dev/null -w %{http_code}`
    if [ "$HEALTH_CHECK_CODE" == "200" ]; then
        #success
        status=0;
        break;
    elif [ "$HEALTH_CHECK_CODE" == "503" ] ; then
        echo -n -e  "\r        -- HealthCheck Cost Time `expr $num` seconds."
        status=1;
        break;
    else
        status=2;
    fi
done

if [ $status -eq 0 ]; then
    echo "        -- HealthCheck Success."
fi
if [ $status -eq 1 ]; then
    echo "        -- HealthCheck Failed."
fi
if [ $status -eq 2 ]; then
    echo "        -- HealthCheck Timeout."
fi