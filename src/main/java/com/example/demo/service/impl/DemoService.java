package com.example.demo.service.impl;

import com.example.demo.exception.DemoServiceException;
import com.example.demo.models.QuizDto;
import com.example.demo.models.Quiz;
import com.example.demo.models.Results;
import com.example.demo.service.IDemoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DemoService implements IDemoService {

  private static String EXT_END_POINT = "https://opentdb.com/api.php?amount=5&category=11";
  private static String EXT_END_POINT2 = "https://opentdb.com/api.php?amount=5&category=12";

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public Quiz getQuizList() {
    try {

      List<Results> resultsList = new ArrayList<>();

      CompletableFuture<List<Results>> firstEndPointData = getFirstEndPointData();
      CompletableFuture<List<Results>> secondEndPointData = getSecondEndPointData();
      resultsList.addAll(firstEndPointData.get());
      resultsList.addAll(secondEndPointData.get());


      List<QuizDto> quizList = new ArrayList<>();
      Map<String, List<Results>> transformedMap = resultsList.stream().collect(Collectors.groupingBy(Results::getCategory));
      transformedMap.forEach((k, v) -> {
        QuizDto q = new QuizDto();
        q.setCategory(k);
        q.setResults(v);
        quizList.add(q);
      });
      Quiz quizJson = new Quiz();
      quizJson.setQuiz(quizList);
      return quizJson;

    } catch (Exception e) {
      log.error("Error Invoking End pont:" + e.getMessage(), e);
      throw new DemoServiceException(e.getMessage());
    }
  }

  @Async
  public CompletableFuture<List<Results>> getFirstEndPointData() throws JsonProcessingException {
    Map<String, Integer> parameters = new HashMap<>();
    List<Results> resultsList = new ArrayList<>();
    TypeReference<HashMap<String, Object>> typeRef
        = new TypeReference<HashMap<String, Object>>() {
    };
    String forObject = restTemplate.getForObject(EXT_END_POINT, String.class, typeRef, parameters);

    ObjectMapper mapper = new ObjectMapper();
    HashMap<String, Object> responseMap = mapper.readValue(forObject, typeRef);
    List<Map<String, Object>> results = (List<Map<String, Object>>) responseMap.get("results");
    results.forEach(rec -> {
      resultsList.add(new Results((String) rec.get("category"),
          (String) rec.get("type"),
          (String) rec.get("difficulty"),
          (String) rec.get("question"),
          (String) rec.get("correct_answer"),
          (List) rec.get("incorrect_answers")
      ));
    });
    return CompletableFuture.completedFuture(resultsList);
  }

  @Async
  public CompletableFuture<List<Results>> getSecondEndPointData() throws JsonProcessingException {
    Map<String, Integer> parameters = new HashMap<>();
    List<Results> resultsList = new ArrayList<>();
    TypeReference<HashMap<String, Object>> typeRef
        = new TypeReference<HashMap<String, Object>>() {
    };
    String forObject = restTemplate.getForObject(EXT_END_POINT2, String.class, typeRef, parameters);

    ObjectMapper mapper = new ObjectMapper();
    HashMap<String, Object> responseMap = mapper.readValue(forObject, typeRef);
    List<Map<String, Object>> results = (List<Map<String, Object>>) responseMap.get("results");
    results.forEach(rec -> {
      resultsList.add(new Results((String) rec.get("category"),
          (String) rec.get("type"),
          (String) rec.get("difficulty"),
          (String) rec.get("question"),
          (String) rec.get("correct_answer"),
          (List) rec.get("incorrect_answers")
      ));
    });
    return CompletableFuture.completedFuture(resultsList);
  }
}
