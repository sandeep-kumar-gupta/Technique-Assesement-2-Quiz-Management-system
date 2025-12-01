package com.Assesment.quiz.controller;

import com.Assesment.quiz.Entity.Quiz;
import com.Assesment.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/quizzes")
@RequiredArgsConstructor
@CrossOrigin
public class AdminQuizController {

    @Autowired
    private final QuizService quizService;

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public Quiz createquiz(@RequestBody Quiz quiz){
        return quizService.createQuiz(quiz);
    }
    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id){
        return quizService.getQuiz(id);
    }


}
