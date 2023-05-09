package com.learn2code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learn2code.entity.Mentor;


public interface MentorRepository extends JpaRepository<Mentor, Long> {}
