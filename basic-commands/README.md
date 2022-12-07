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

