package org.romys.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255, name = "name", nullable = false)
    private String name;

    @Column(length = 3, name = "age", nullable = false)
    private int age;

    @Column(length = 255, name = "city", nullable = false)
    private String city;

    @Column(name = "created")
    private Timestamp created;
}