package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repositories.TaskReporitory;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskReporitory taskReporitory;

    @Autowired
    private UserService userService;

    @Transactional
    public Task findById(Long id) {
        Optional<Task> task = this.taskReporitory.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        return this.taskReporitory.save(obj);
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskReporitory.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.taskReporitory.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas");
        }
    }
}
