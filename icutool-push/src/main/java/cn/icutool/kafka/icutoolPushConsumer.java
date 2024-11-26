package cn.icutool.kafka;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Component
public class icutoolPushConsumer {
    @KafkaListener(topics = "icutool-push",groupId = "1")
    public void listen1(ConsumerRecord<String, String> record){
        System.out.println("listen1 收到key " + record.key());
        System.out.println("listen1 收到value " + record.value());
    }

    @KafkaListener(topics = "icutool-push",groupId = "2")
    public void listen2(ConsumerRecord<String, String> record){
        System.out.println("listen2 收到key " + record.key());
        System.out.println("listen2 收到value " + record.value());
    }
}
