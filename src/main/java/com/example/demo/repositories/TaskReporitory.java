package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Task;


@Repository
public interface TaskReporitory extends JpaRepository<Task, Long> {
    
    List<Task> findByUser_id(Long id);

    //@Query(value="SELECT t FROM Task t WHERE t.user.id = id")
    //List<Task> findByUser_id(@Param ("user_id") Long id);

    //@Query(value="SELECT * FROM task t WHERE t.user_id = :id",nativeQuery=true)
    //List<Task> findByUser_id(@Param("id") Long id);
}
