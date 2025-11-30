package com.Assesment.quiz.controller;

import com.Assesment.quiz.Entity.Quiz;
import com.Assesment.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/quizzes")
@RequiredArgsConstructor
public class AdminQuizController {

    @Autowired
    private final QuizService quizService;

    @PostMapping
    public Quiz createquiz(@RequestBody Quiz quiz){
        return quizService.createQuiz(quiz);
    }
    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id){
        return quizService.getQuiz(id);
    }


}
