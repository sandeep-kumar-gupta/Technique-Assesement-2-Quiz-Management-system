package com.Assesment.quiz.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Quiz quiz;

    private int score;
    private int totalQuestions;
    private Instant submittedAt;

    @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SubmissionAnswer> answers;

    @PrePersist
    public void prePersist(){
        this.submittedAt = Instant.now();
    }
}
