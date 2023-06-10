package com.learn2code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.learn2code.entity.Student;

@CrossOrigin
@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> { }
