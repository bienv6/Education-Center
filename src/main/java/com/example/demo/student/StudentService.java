package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

  private final StudentRepository studentRepo;

  public List<Student> getAllStudents() {
    return studentRepo.findAll();
  }

  public Student addStudent(@PathVariable Student student) {
    return studentRepo.save(student);
  }

  public Student getStudentById(long id) {
    return studentRepo.findStudentById(id);
  }

  public Student getStudentByEmail(String email) {
    return studentRepo.findStudentByEmail(email);
  }

  public List<Student> getStudentsByGender(Gender gender){
    return studentRepo.findStudentByGender(gender);
  }
}
