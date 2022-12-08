package org.example;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Mainwithcallback {
    private static  final Logger log = LoggerFactory.getLogger(Mainwithcallback.class.getSimpleName());
    public static void main(String[] args) {

        //System.out.println("Hello world!");
        // after logger use
        log.info("hello world !");
        // create producer properties
        Properties properties = new Properties() ;
        properties.setProperty("bootstrap.servers","ec2-13-126-196-174.ap-south-1.compute.amazonaws.com:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        properties.setProperty("acks","all");
        // create producer
        KafkaProducer<String, String>  producer = new KafkaProducer<>(properties);
        // send data by creating producer record


        for (int i=0;i<10;i++){
            ProducerRecord<String, String> producerRecord =
            new ProducerRecord<>("ashu-logs","Hello world java code ack "+i);
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    // execute everytime a message successfully sent
                    if (e == null){
                        log.info("received new metdata \n" +
                                "topic : " +metadata.topic() + "\n" +
                                "partition : "+metadata.partition() +"\n" +
                                "Offset : " + metadata.offset());
                    } else {
                        log.error("Error while producing : ",e);
                    }
                }
            });
        }
        // send data synchronous way

        // above program is going to shut down to data won't be sent
        producer.flush(); // this will make code live

        // close producer
        producer.close();
    }
}