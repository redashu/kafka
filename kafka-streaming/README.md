## introduction 

### Understanding stream 

<img src="stream.png">


### stream example 

<img src="stream1.png">

### now kafka stream

<p> this is an API that integrates kafka cluster to data processing applications written in java/scala </p>
<p> this api leverage data processing capability of kafka </p>

## so apache kafka is 

<img src="stream2.png">

### architecture of kafka stream 

<p> kafka stream uses internally kafka producer and consumer library </p>

<img src="karch.png">

### lets do a sample stream job in kafka

```
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server localhost:9092  --list

[root@control-plane ~]# kafka-topics.sh  --bootstrap-server localhost:9092  --create --topic ashu-t1 --partitions 3 --replication-factor 1 
Created topic ashu-t1.
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server localhost:9092  --list
ashu-t1
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server localhost:9092  --create --topic streams-file-input --partitions 3 --replication-factor 1 
Created topic streams-file-input.
[root@control-plane ~]# 
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server localhost:9092  --create --topic streams-wordcount-output --partitions 3 --replication-factor 1 
Created topic streams-wordcount-output.
[root@control-plane ~]# kafka-topics.sh  --bootstrap-server localhost:9092  --list
ashu-t1
streams-file-input
streams-wordcount-output
[root@control-plane ~]# 

```

### lets read data in topic what we written 

```
[root@control-plane ~]# kafka-console-consumer.sh --bootstrap-server localhost:9092   --topic streams-file-input  --from-beginning 
hey i am ashu
my name is ashu
i am ashu for kafka stream
```

