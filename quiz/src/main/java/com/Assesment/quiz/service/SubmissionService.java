package com.Assesment.quiz.service;

import com.Assesment.quiz.Entity.Submission;

import java.util.Map;

public interface SubmissionService {

    Submission submitQuiz(Long quizId, Map<Long,String> answers);
    Submission getSubmission(Long id);
}
