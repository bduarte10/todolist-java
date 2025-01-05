package com.example.todolist.task;

import com.example.todolist.users.UserModel;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @ManyToOne
    @JoinColumn(name = "user_id") // Nome da coluna de chave estrangeira no banco de dados
    private UserModel user;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
