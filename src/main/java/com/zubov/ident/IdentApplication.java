package com.zubov.ident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // see class DailyTask
public class IdentApplication {

  public static void main(String[] args) {
    SpringApplication.run(IdentApplication.class, args);
  }
}
