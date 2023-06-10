package com.application.todoapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="task")
    private String task;

    @Enumerated(EnumType.STRING)
    @Column(name="progress")
    private Progress taskProgress;

    public enum Progress {
        INCOMPLETE,
        ONGOING,
        COMPLETE
    }

     
}
