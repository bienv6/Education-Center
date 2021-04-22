package com.example.demo.student;

import com.example.demo.Exceptions.BadRequestException;
import com.example.demo.Exceptions.StudentNotFoundException;
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
    Student student1 = studentRepo.findStudentByEmail(student.getEmail());
    if (student1 != null)
      throw new BadRequestException("Student with email " + student.getEmail() + " already exist");
    return studentRepo.save(student);
  }

  public Student getStudentById(long id) {
    Student student = studentRepo.findStudentById(id);
    if (student == null)
      throw new StudentNotFoundException(" No student with id " + id + " was found");
    return student;
  }

  public Student getStudentByEmail(String email) {
    Student student = studentRepo.findStudentByEmail(email);
    if (student == null)
      throw new StudentNotFoundException("No student with email " + email + " exist");
    return student;
  }

  public void deleteStudent(long id) {
    Student student = studentRepo.findStudentById(id);
    if (student == null)
      throw new StudentNotFoundException(" No student with id " + id + " was found");
    studentRepo.deleteById(id);
  }

  /*public List<Student> getStudentsByGender(Gender gender) {
    return studentRepo.findStudentByGender(gender);
  }*/

  public Student updateStudent(long id, Student student) {
    Optional<Student> optionalStudent = studentRepo.findById(id);
    if (optionalStudent.isEmpty()) {
      throw new StudentNotFoundException(" No student with id " + id + " was found");
    }
    Student student1 = optionalStudent.get();
    student1.setEmail(student.getEmail());
    student1.setName(student.getName());
    student1.setGender(student.getGender());
    return studentRepo.save(student1);
  }
}
