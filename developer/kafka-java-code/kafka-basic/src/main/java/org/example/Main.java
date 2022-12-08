package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Main {
    private static  final Logger log = LoggerFactory.getLogger(Main.class.getSimpleName());
    public static void main(String[] args) {

        //System.out.println("Hello world!");
        // after logger use
        log.info("hello world !");
        // create producer properties
        Properties properties = new Properties() ;
        properties.setProperty("bootstrap.servers","ec2-13-126-196-174.ap-south-1.compute.amazonaws.com:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        // create producer
        KafkaProducer<String, String>  producer = new KafkaProducer<>(properties);
        // send data by creating producer record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("ashu-logs","Hello world java code");
        // send data synchronous way
        producer.send(producerRecord);
        // above program is going to shut down to data won't be sent
        producer.flush(); // this will make code live

        // close producer
        producer.close();
    }
}