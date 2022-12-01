# Understanding 

### apache kafka 

<ol>
    <li>An open Source project for event store and stream processing platform </li>
    <li> created by LinkedIn in </li>
    <li> Written in Java & Scala </li>
    <li> horizentally scalable & can scale millions of message per second  </li>
    <li> latency in real time is less than 10 ms </li>
    
</ol>

### use cases 

<img src="usec.png">

### use cases examples 

<img src="example.png">

## lets understand kafa in an other way 

### problems with companies while sharing data 

<img src="prob1.png">

### understanding complexity again 

<img src="prob2.png">

### SO need data decoupling stream & Systems 

<img src="decouple.png">

### Understading data sources and target system in example 

<img src="ddss.png">

## Understanding core concepts 

### Kafka Topic 

<img src="topic1.png">

### Kafka partitions & offsets 

<p> Each kafka topics can have many partitions and each partitions gonna have order number called offsets </p>

<img src="partoff.png">

### Understanding using truck GPS system 

<img src="exampletopic.png">

### partitions and offsets -- logic for timeout and reuse

<img src="reuse.png">

## Introduction to Producers 

<p> The One who sends data kafka broker , targeting a topic and its partitions <p/>

<img src="producers1.png">

### Producer message concept for sending data to kafka broker 

<p> if kafka message is not having any fixed key in message , then it will send message to a topic's partition in round robin manner </p>

<img src="rr.png">

<p> but if producer message is gonna have some key , so the same key will send data to same partition of the topic </p>

<img src="hash.png">

### producer  message look like 

<img src="message.png">

### How Kafka Message gets accept ---> Using KAFKA Message SERIALIZER

<p> Kafka message get created by producers but Kafka server can't accept a general text message </p>
<p> So Kafka Message Serializer converting messages into bytes  </p>
<p> Note: Kafka producers comes with common serializers like -- int , string ,float ,avro , protobuf and so on </p>

### how the message serializer works on producer side 

<img src="serializer.png">

## Kafka COnsumers 

### Understanding 

<p>THe one who reads messages from kafka topics --few more info is given below </p>

<img src="producer1.png">

### Decoding message by consumer 

<p> Note: consumer has to know in advanced that message is having which data type of message for keys and values so that it can use that serializer to decode <p/>


<img src="deserializer.png">


## KAFKA Broker / server 

<p> Kafka broker are those servers with in kafka cluster who receives message </p>

### some more info about kafka brokers 

<img src="broker1.png">

### topic with broker 

<img src="topicbr.png">

### kafka broker discovery

<p> SO kafka client will connect to any one broker -- that is known as KAFKA Bootstrap server </p>
<p> that kafka bootstrap server after connected from client will send the list of all brokers </p>

### more info is given below 

<img src="kafkacl.png">

### producer to -- partition 

<img src="leader.png">

### fetching details by consumers from a non leader partition broker

<img src="fetch1.png">

### consumer ack system 

<img src="ack1.png">

# ROle of Zookeeper -- the Kafka broker manager till kafka version 2.x 

### some more info about zookeeper

<img src="zoo1.png">

### zookeeper with kafka brokers 

<img src="zoo2.png">

### do not use zookeeper from kafka client

<img src="zoo3.png">

### final zookeeper with kafka 

<img src="zoo4.png">

### kafka architecture with and without zookeeper 

<img src="kafka-arch1.png">







