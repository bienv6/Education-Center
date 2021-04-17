package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

  public void deleteStudent(long id) { studentRepo.deleteById(id);}

  public List<Student> getStudentsByGender(Gender gender) { return studentRepo.findStudentByGender(gender);
  }

  public Student updateStudent(long id, Student student) {
    Student student1;
    Optional<Student> optionalStudent = studentRepo.findById(id);
    if (optionalStudent.isPresent()) {
      student1 = optionalStudent.get();
      student1.setEmail(student.getEmail());
      student1.setName(student.getName());
      student1.setGender(student.getGender());
      return studentRepo.save(student1);
    }
    return null;
  }


}
