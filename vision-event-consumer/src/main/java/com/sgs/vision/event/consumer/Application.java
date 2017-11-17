package com.sgs.vision.event.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages={"com.sgs.vision"})
@EnableMongoRepositories(basePackages="com.sgs.vision.data.repositories")
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
