package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  Student findStudentById(long id);

  Student findStudentByEmail(String email);

  /* @Query("" + "Select Student " + "FROM Student s " + "WHERE s.gender = ?1")
    List<Student> findStudentByGender(Gender gender);
  }*/
}
