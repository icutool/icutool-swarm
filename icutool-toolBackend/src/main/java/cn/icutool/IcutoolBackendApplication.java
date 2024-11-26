package cn.icutool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Properties;

@SpringBootApplication
@MapperScan("cn.icutool.mapper")
@EnableCaching
public class IcutoolBackendApplication {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        // 用 select 1 替换 ping 来检测连接保活
        properties.setProperty("druid.mysql.usePingMethod", "false");
        SpringApplication.run(IcutoolBackendApplication.class, args);
    }

}
