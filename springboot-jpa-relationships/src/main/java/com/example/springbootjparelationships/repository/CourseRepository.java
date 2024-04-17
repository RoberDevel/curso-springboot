package com.example.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootjparelationships.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
