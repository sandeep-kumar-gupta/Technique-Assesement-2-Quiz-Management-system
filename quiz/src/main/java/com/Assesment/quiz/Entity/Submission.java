package com.Assesment.quiz.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private int score;
    private int totalQuestions;
    private Instant submittedAt;

   /* @OneToMany(mappedBy = "submission", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SubmissionAnswer> answers;*/

    @ElementCollection
    @CollectionTable(
            name = "submission_answers",
            joinColumns = @JoinColumn(name = "submission_id")
    )
    @MapKeyColumn(name = "question_id")
    @Column(name = "answer")
    private Map<Long, String> answers = new HashMap<>();

    @PrePersist
    public void prePersist(){
        this.submittedAt = Instant.now();
    }
}
