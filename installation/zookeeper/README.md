## Setup and check zookeeper 

### INstall jdk 11 on aws ec2 and do the rest 

### setup jdk 11 

```
sudo amazon-linux-extras install java-openjdk11
```

### lets check it 

```
[root@ip-172-31-13-141 ~]# java -version 
openjdk version "11.0.16" 2022-07-19 LTS
OpenJDK Runtime Environment (Red_Hat-11.0.16.0.8-1.amzn2.0.1) (build 11.0.16+8-LTS)
OpenJDK 64-Bit Server VM (Red_Hat-11.0.16.0.8-1.amzn2.0.1) (build 11.0.16+8-LTS, mixed mode, sharing)
[root@ip-172-31-13-141 ~]# 
```

### download kafka 3.x binary then setup in a path variable 

```
 wget https://downloads.apache.org/kafka/3.3.1/kafka_2.13-3.3.1.tgz
  tar xvzf kafka_2.13-3.3.1.tgz 
```
### start zookeeper -- Note you can create systemd-unit file 

```
zookeeper-server-start.sh  /opt/kafka_2.13-3.3.1/config/zookeeper.properties
```

### Note if you want to do a version check of zookeeper then update config file

#### zookeeper.properties 

```
4lw.commands.whitelist=*
```

### you can test this using 

```
[root@ip-172-31-56-93 ~]# echo stat  |  nc localhost 2181
Zookeeper version: 3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT
Clients:
 /127.0.0.1:33724[0](queued=0,recved=1,sent=0)

Latency min/avg/max: 0/0.0/0
Received: 91
Sent: 1
Connections: 1
Outstanding: 0
Zxid: 0x0
Mode: standalone
Node count: 5

```
