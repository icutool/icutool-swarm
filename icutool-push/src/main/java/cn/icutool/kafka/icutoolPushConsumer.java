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

    @KafkaListener(topics = "icutool-push-spider")
    public void spiderListen(ConsumerRecord<String, String> record){
        String key = record.key();
        log.info("spiderListen 收到key {}", key);
        String msg = record.value();
        log.info("spiderListen 收到消息 {}", msg);
        platFormService.sendMsgToPlatForm(msg);
    }

    @KafkaListener(topics = "icutool-push")
    public void listen2(String msg){
        System.out.println("listen2 收到value " + msg);
    }
}
