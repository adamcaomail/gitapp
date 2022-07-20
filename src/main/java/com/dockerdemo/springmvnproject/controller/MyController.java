package com.dockerdemo.springmvnproject.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  @GetMapping("/hi")
  public String sayHi() {
    return "say hello world";
  }

  @GetMapping("/hello")
  public String sayHello() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    return "say hello world: " + dtf.format(now);
  }
}
