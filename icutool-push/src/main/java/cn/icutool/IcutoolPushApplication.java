package cn.icutool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class IcutoolPushApplication {
    public static void main(String[] args) {
        SpringApplication.run(IcutoolPushApplication.class, args);
    }
}