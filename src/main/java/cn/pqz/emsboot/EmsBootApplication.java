package cn.pqz.emsboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement(proxyTargetClass = true)
public class EmsBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmsBootApplication.class, args);
    }

}
