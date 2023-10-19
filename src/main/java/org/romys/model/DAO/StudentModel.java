package org.romys.model.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.romys.model.DTO.StudentDTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<AbsenModel> absen;

    public StudentModel(int id) {
        this.id = id;
    }

    public StudentModel(StudentDTO studentDTO) {
        this.name = studentDTO.getName();
        this.age = studentDTO.getAge();
        this.city = studentDTO.getCity();
    }

}