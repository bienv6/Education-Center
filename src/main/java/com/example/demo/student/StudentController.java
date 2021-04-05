package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.data.web.JsonPath;
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
  public Student addStudent(@RequestBody Student student){
    return studentService.addStudent(student);
  }

  @RequestMapping("/?gender={gender}")
  List<Student> getStudentsByGender(@PathVariable Gender gender){
    return studentService.getStudentsByGender(gender);
  }
}
