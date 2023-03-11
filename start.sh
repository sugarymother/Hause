#!/usr/bin/env bash
p=$(ps -ef | grep Hause | grep -v 'grep' | awk '{print $2}')
if [ "$p"  != '' ]; then
  kill -9 "$p"
fi
java -jar -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn32m -Xss256k -XX:SurvivorRatio=8 Hause-1.0.0.jar --spring.profiles.active=prod --add-opens=java.base/java.lang=ALL-UNNAMED