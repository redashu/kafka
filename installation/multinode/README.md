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
 wget https://downloads.apache.org/kafka/3.3.1/kafka_2.13-3.3.1.tgz
```

### note: this bundle has zookeeper and kafka both 

### setup zookeeper 
