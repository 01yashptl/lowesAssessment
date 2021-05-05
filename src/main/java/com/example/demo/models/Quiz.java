package com.example.demo.models;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Quiz {
  private List<QuizDto> quiz;
}
