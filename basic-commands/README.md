## This section is for connecting zookeeper & kafka 

### connecting a zookeeper remotely 

## connecting using zookeeper shell

```
[root@control-plane ~]# zookeeper-shell.sh  ip-172-31-13-141.ap-south-1.compute.internal:2181
Connecting to ip-172-31-13-141.ap-south-1.compute.internal:2181
Welcome to ZooKeeper!
JLine support is disabled

WATCHER::

WatchedEvent state:SyncConnected type:None path:null

ls /
[ashudata, kafka, zookeeper]
ls /zookeeper
[config, quota]
ls /zookeeper/config
[]
get /zookeeper/config
server.1=ip-172-31-13-141.ap-south-1.compute.internal:2888:3888:participant
server.2=ip-172-31-8-169.ap-south-1.compute.internal:2888:3888:participant
server.3=ip-172-31-1-166.ap-south-1.compute.internal:2888:3888:participant
version=0

```

### testing 4lw in zookeeper 

```
[root@control-plane ~]#  echo "stat"  |  nc ip-172-31-13-141.ap-south-1.compute.internal 2181
Zookeeper version: 3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT
Clients:
 /172.31.38.68:42174[0](queued=0,recved=1,sent=0)
 /172.31.13.141:46946[1](queued=0,recved=196,sent=196)

Latency min/avg/max: 0/0.557/13
Received: 230
Sent: 229
Connections: 2
Outstanding: 0
Zxid: 0x4000000b5
Mode: follower
Node count: 155

---
[root@control-plane ~]# 
[root@control-plane ~]#  echo "ruok"  |  nc ip-172-31-13-141.ap-south-1.compute.internal 2181
imok[root@control-plane ~]# 
[root@control-plane ~]#  echo "ruok"  |  nc ip-172-31-13-141.ap-south-1.compute.internal 2181 ; echo 
imok

---
[root@control-plane ~]#  echo "dump"  |  nc ip-172-31-13-141.ap-south-1.compute.internal 2181 ; echo 
SessionTracker dump:
Global Sessions(3):
0x10000008d960001	18000ms
0x2000000427a0000	18000ms
0x300000023890000	18000ms
ephemeral nodes dump:
Sessions with Ephemerals (3):
0x10000008d960001:
	/kafka/brokers/ids/1
0x300000023890000:
	/kafka/brokers/ids/3
0x2000000427a0000:
	/kafka/controller
	/kafka/brokers/ids/2
Connections dump:
Connections Sets (2)/(2):
0 expire at Wed Dec 07 03:47:21 UTC 2022:
2 expire at Wed Dec 07 03:47:31 UTC 2022:
	ip: /172.31.38.68:58940 sessionId: 0x0
	ip: /172.31.13.141:46946 sessionId: 0x10000008d960001


```

## connecting kafka from kafka client --make kafka client can reach to kafka server with hostname of kafka 

### listing topics

```
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --list
__consumer_offsets
ashu-app-logs
ashu-logs
```

### creating topics 

```
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --create --topic   ashu-app-analytics  --partitions 5 --replication-factor  2
Created topic ashu-app-analytics.
[root@control-plane ~]# 
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --list
__consumer_offsets
ashu-app-analytics
ashu-app-logs
ashu-logs

```

### describing topics 

```
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --describe --topic  ashu-app-logs
Topic: ashu-app-logs	TopicId: AoNAvR49QK6eapT_nwMzZQ	PartitionCount: 4	ReplicationFactor: 2	Configs: 
	Topic: ashu-app-logs	Partition: 0	Leader: 3	Replicas: 3,1	Isr: 3,1
	Topic: ashu-app-logs	Partition: 1	Leader: 1	Replicas: 1,2	Isr: 1,2
	Topic: ashu-app-logs	Partition: 2	Leader: 2	Replicas: 2,3	Isr: 2,3
	Topic: ashu-app-logs	Partition: 3	Leader: 3	Replicas: 3,2	Isr: 3,2
```

## Note: here replicas means for each partition how many replicas we are having and  they are stored in which brokers



### using producer to generate data in a topic 

```
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --list
__consumer_offsets
ashu-app-analytics
ashu-app-logs
ashu-logs
---

[root@control-plane ~]# 
[root@control-plane ~]# 
[root@control-plane ~]# kafka-console-producer.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092 --topic  ashu-app-logs 
>hey this is me ashutoshh
>^C[root@control-plane ~]
```

### lets use consumer 

```
kafka-console-consumer.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --topic  ashu-app-logs --from-beginning
hey this is me ashutoshh
hey there i am ahsutoshh writing data to you
```

## Note : every consumer creates a consumer group to read message -- and kafka automatically creates a consumer group 

### lets check consumer group 

```
[root@control-plane ~]# kafka-consumer-groups.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --list
console-consumer-87873
```

### checking it again : note : make sure consumer is active then only it will work 

```
[root@control-plane ~]# kafka-consumer-groups.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --list
console-consumer-87873


[root@control-plane ~]# kafka-consumer-groups.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --describe  --group console-consumer-87873

Consumer group 'console-consumer-87873' has no active members.


[root@control-plane ~]# kafka-consumer-groups.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --list
console-consumer-87873
console-consumer-85406


[root@control-plane ~]# 
[root@control-plane ~]# kafka-consumer-groups.sh  --bootstrap-server  ip-172-31-13-141.ap-south-1.compute.internal:9092  --describe  --group console-consumer-85406

GROUP                  TOPIC           PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG             CONSUMER-ID                                           HOST            CLIENT-ID
console-consumer-85406 ashu-app-logs   0          -               6               -               console-consumer-45cbc838-779c-4df6-9e56-bb3822882d44 /172.31.38.68   console-consumer
console-consumer-85406 ashu-app-logs   1          -               0               -               console-consumer-45cbc838-779c-4df6-9e56-bb3822882d44 /172.31.38.68   console-consumer
console-consumer-85406 ashu-app-logs   2          -               2               -               console-consumer-45cbc838-779c-4df6-9e56-bb3822882d44 /172.31.38.68   console-consumer
console-consumer-85406 ashu-app-logs   3          -               3               -               console-consumer-45cbc838-779c-4df6-9e56-bb3822882d44 /172.31.38.68   console-consumer
[root@control-plane ~]# 



```
