package cn.icutool.kafka;

import cn.icutool.service.PlatFormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Component
@Slf4j
public class icutoolPushConsumer {
    private final PlatFormService platFormService;

    public icutoolPushConsumer(PlatFormService platFormService) {
        this.platFormService = platFormService;
    }

    @KafkaListener(topics = "icutool-push-spider",groupId = "1")
    public void spiderListen(ConsumerRecord<String, String> record){
        log.info("spiderListen 收到key {}", record.key());
        log.info("spiderListen 收到value {}", record.value());
        platFormService.sendMsgToPlatForm(record.value());
    }

    @KafkaListener(topics = "icutool-push",groupId = "2")
    public void listen2(ConsumerRecord<String, String> record){
        System.out.println("listen2 收到key " + record.key());
        System.out.println("listen2 收到value " + record.value());
    }
}
