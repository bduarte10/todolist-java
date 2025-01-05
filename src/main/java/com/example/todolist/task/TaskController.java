package com.example.todolist.task;

import com.example.todolist.users.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @Autowired
    private IUserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TaskModel taskModel) {

        if (taskModel.getUser() == null || taskModel.getUser().getId() == null) {
            return ResponseEntity.badRequest().body("User ID is required");
        }


        var user = this.userRepository.findById(taskModel.getUser().getId());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }


        TaskModel task = new TaskModel();
        task.setTitle(taskModel.getTitle());
        task.setDescription(taskModel.getDescription());
        task.setStartAt(taskModel.getStartAt());
        task.setEndAt(taskModel.getEndAt());
        task.setPriority(taskModel.getPriority());
        task.setUser(user.get());


        var newTask = this.taskRepository.save(task);
        return ResponseEntity.ok(newTask);
    }

    @GetMapping
    public ResponseEntity<?> list() {
        var tasks = this.taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }
}
