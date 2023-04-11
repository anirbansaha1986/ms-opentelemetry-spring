package com.learn2code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn2code.entity.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {

}
