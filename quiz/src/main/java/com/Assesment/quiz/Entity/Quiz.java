package com.Assesment.quiz.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter@NoArgsConstructor@AllArgsConstructor
@Builder
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length =2000)
    private String description;

    private Instant createdAt;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questionList;

    @PrePersist
    public void prePersist(){
        this.createdAt = Instant.now();
    }

}
