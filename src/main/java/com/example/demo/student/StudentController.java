package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

  private final StudentService studentService;

  @GetMapping
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  @GetMapping("/{id}")
  public Student getStudentById(@PathVariable long id) {
    return studentService.getStudentById(id);
  }

  @PostMapping
  public Student addStudent(@RequestBody Student student) {
    return studentService.addStudent(student);
  }

 /* @GetMapping(value = "", params = "gender")
  List<Student> getStudentsByGender(@PathParam("gender") Gender gender) {
    return studentService.getStudentsByGender(gender);
  }*/

  @GetMapping(value = "", params = "email")
  Student getStudentByEmail(@PathParam("email") String email) {
    return studentService.getStudentByEmail(email);
  }

  @PutMapping("/{id}")
  public Student updateStudent(@PathVariable long id, @RequestBody Student student) {
    return studentService.updateStudent(id, student);
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable long id) {
    studentService.deleteStudent(id);
  }
}
