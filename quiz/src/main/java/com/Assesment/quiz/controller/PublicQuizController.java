package com.Assesment.quiz.controller;

import com.Assesment.quiz.Entity.Quiz;
import com.Assesment.quiz.Entity.Submission;
import com.Assesment.quiz.service.QuizService;
import com.Assesment.quiz.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PublicQuizController {

    @Autowired
    private final QuizService quizService;
    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/quizzes/{id}")
    public Quiz getQuizService(@PathVariable Long id){
        return quizService.getQuiz(id);
    }

    @PostMapping("/quizzes/{id}/submit")
    public Submission submitQuiz(@PathVariable Long id, @RequestBody Map<Long,String> answers){

        return submissionService.submitQuiz(id,answers);

    }
    @GetMapping("/submission/{submissionId}")
    public Submission getSubmission(@PathVariable Long submissionId){
        return submissionService.getSubmission(submissionId);
    }



}
