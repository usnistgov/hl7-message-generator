package com.example.messagegenerator;

import com.example.messagegenerator.runner.Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageGeneratorApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MessageGeneratorApplication.class, args);

        Runner runner = new Runner();
        runner.run();


    }

}
