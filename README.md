# kafka

### kafka webUI -- using docker

```
docker run --restart=always -it --add-host  kafka:172.31.38.68 -d -p 9000:9000 -e KAFKA_BROKERCONNECT=172.31.38.68:9092  -e JVM_OPTS="-Xms32M -Xmx64M"  -e SERVER_SERVLET_CONTEXTPATH="/"  obsidiandynamics/kafdrop
```

