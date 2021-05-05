package com.example.demo.controller;

import com.example.demo.models.Quiz;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


public interface IDemoController {

  @GetMapping(value = "/coding/exercise/quiz")
  ResponseEntity<Quiz> getQuizList();

}
