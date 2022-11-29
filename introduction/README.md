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



