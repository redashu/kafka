## setup multinode cluster 

## steps 

## Node 1 

### Setting up zookeeper 

#### Install jdk 11 

```
sudo amazon-linux-extras install java-openjdk11

---
[root@ip-172-31-13-141 ~]# java --version 
openjdk 11.0.16 2022-07-19 LTS
OpenJDK Runtime Environment (Red_Hat-11.0.16.0.8-1.amzn2.0.1) (build 11.0.16+8-LTS)
OpenJDK 64-Bit Server VM (

```

### Disable swap memory 

```
 echo "vm.swappiness = 0"  >>/etc/sysctl.conf
```

### Downlaod kafka from apache.kafka.org 

```
cd /opt
 wget https://downloads.apache.org/kafka/3.3.1/kafka_2.13-3.3.1.tgz
```

### note: this bundle has zookeeper and kafka both 

### setup zookeeper 

```

tar xvzf kafka_2.13-3.3.1.tgz

```

### verify 

```
[root@ip-172-31-13-141 ~]# cd /opt/
[root@ip-172-31-13-141 opt]# ls
aws  kafka_2.13-3.3.1  rh
[root@ip-172-31-13-141 opt]# ls kafka_2.13-3.3.1/
LICENSE  NOTICE  bin  config  libs  licenses  logs  site-docs
[root@ip-172-31-13-141 opt]# 


```

### setting path 

```
[root@ip-172-31-13-141 opt]# cat /root/.bashrc 
# .bashrc

# User specific aliases and functions

alias rm='rm -i'
alias cp='cp -i'
alias mv='mv -i'

# Source global definitions
if [ -f /etc/bashrc ]; then
	. /etc/bashrc
fi

KAFKA_HOME=/opt/kafka_2.13-3.3.1
PATH=$PATH:$KAFKA_HOME/bin
export PATH
```

### lets configure zookeeper 

```
[root@ip-172-31-13-141 opt]# cd /opt/kafka_2.13-3.3.1/
[root@ip-172-31-13-141 kafka_2.13-3.3.1]# ls
LICENSE  NOTICE  bin  config  libs  licenses  logs  site-docs
[root@ip-172-31-13-141 kafka_2.13-3.3.1]# cd config/
[root@ip-172-31-13-141 config]# ls
connect-console-sink.properties    connect-file-source.properties   consumer.properties  server.properties
connect-console-source.properties  connect-log4j.properties         kraft                tools-log4j.properties
connect-distributed.properties     connect-mirror-maker.properties  log4j.properties     trogdor.conf
connect-file-sink.properties       connect-standalone.properties    producer.properties  zookeeper.properties


==== changing in zookeeper.properties 

dataDir=/var/lib/zookeeper
# the port at which the clients will connect
clientPort=2181
# disable the per-ip limit on the number of connections since this is a non-production config
maxClientCnxns=0

```

### lets start zookeeper 

```
[root@ip-172-31-13-141 ~]# zookeeper-server-start.sh   -daemon  /opt/kafka_2.13-3.3.1/config/zookeeper.properties 
[root@ip-172-31-13-141 ~]# 
[root@ip-172-31-13-141 ~]# netstat -nlpt
Active Internet connections (only servers)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 0.0.0.0:111             0.0.0.0:*               LISTEN      2655/rpcbind        
tcp        0      0 0.0.0.0:22              0.0.0.0:*               LISTEN      3152/sshd           
tcp        0      0 127.0.0.1:25            0.0.0.0:*               LISTEN      3091/master         
tcp6       0      0 :::2181                 :::*                    LISTEN      15578/java 
```

### lets connection to zookeeper and check its file-system 

```
[root@ip-172-31-13-141 ~]# zookeeper-shell.sh   127.0.0.1:2181
Connecting to 127.0.0.1:2181
Welcome to ZooKeeper!
JLine support is disabled

WATCHER::

WatchedEvent state:SyncConnected type:None path:null

ls /
[zookeeper]

```

### another way of connecting 

```
[root@ip-172-31-13-141 ~]# echo "ruok"  |  nc 127.0.0.1  2181 ; echo 
ruok is not executed because it is not in the whitelist.


```

### so lets enable 4 word letter commands 

```
[root@ip-172-31-13-141 ~]# cat /opt/kafka_2.13-3.3.1/config/zookeeper.properties
4lw.commands.whitelist=*

---
zookeeper-server-stop.sh
zookeeper-server-start.sh   -daemon  /opt/kafka_2.13-3.3.1/config/zookeeper.properties
```

### lets test it 

```
[root@ip-172-31-13-141 ~]# echo "ruok"  |  nc 127.0.0.1  2181 ; echo 
imok
[root@ip-172-31-13-141 ~]# echo "stat"  |  nc 127.0.0.1  2181 ; echo 
Zookeeper version: 3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT
Clients:
 /127.0.0.1:35102[0](queued=0,recved=1,sent=0)

Latency min/avg/max: 0/0.0/0
Received: 5
Sent: 4
Connections: 1
Outstanding: 0
Zxid: 0x2
Mode: standalone
Node count: 5

```


