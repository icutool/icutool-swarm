package cn.icutool;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// @EnableAdminServer
@SpringBootApplication
public class IcutoolMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(IcutoolMonitorApplication.class, args);
    }

}
