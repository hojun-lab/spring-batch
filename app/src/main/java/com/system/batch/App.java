package com.system.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
  public static void main(String[] args) {
    System.exit(SpringApplication.exit(SpringApplication.run(App.class, args)));
  }

  public Object getGreeting() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getGreeting'");
  }
}
