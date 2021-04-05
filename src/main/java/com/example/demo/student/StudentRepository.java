package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  Student findStudentById(long id);

  Student findStudentByEmail(String email);

  List<Student> findStudentByGender(Gender gender);
}
