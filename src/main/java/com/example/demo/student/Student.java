package com.example.demo.student;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;


@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
  @GenericGenerator(name = "seq", strategy = "increment")
  private Long id;
  private String name;
  private String email;
  @Enumerated(EnumType.STRING)
  private Gender gender;

  public Student(String name, String email, Gender gender) {
    this.name = name;
    this.email = email;
    this.gender = gender;
  }
}
