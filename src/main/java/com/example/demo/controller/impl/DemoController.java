package com.example.demo.controller.impl;

import com.example.demo.controller.IDemoController;
import com.example.demo.models.Quiz;
import com.example.demo.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class DemoController implements IDemoController {
  @Autowired
  private IDemoService demoService;

  @Override
  public ResponseEntity<Quiz> getQuizList() {
    return new ResponseEntity<Quiz>(demoService.getQuizList(), HttpStatus.OK);


  }
}
