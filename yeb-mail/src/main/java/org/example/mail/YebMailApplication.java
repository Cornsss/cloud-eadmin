package org.example.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class YebMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(YebMailApplication.class);
        System.out.println("test");
    }
}
