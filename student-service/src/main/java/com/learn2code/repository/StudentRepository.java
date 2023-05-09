package com.learn2code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learn2code.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Long> { }
