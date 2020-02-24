package xyz.intellij.streamsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.intellij.streamsys.repo.UserListRepo;

@SpringBootApplication
public class StreamSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamSysApplication.class, args);
    }

}
