package org.romys.model.DTO;

import java.sql.Timestamp;
import java.util.List;

import org.romys.model.DAO.AbsenModel;
import org.romys.model.DAO.StudentModel;

import lombok.Data;

@Data
public class StudentWithAbsenDTO {
    private int id;
    private String name;
    private int age;
    private String city;
    private Timestamp created;
    private int totalAbsen;
    private List<AbsenModel> absen;

    public StudentWithAbsenDTO(StudentModel studentModel) {
        this.id = studentModel.getId();
        this.name = studentModel.getName();
        this.age = studentModel.getAge();
        this.city = studentModel.getCity();
        this.created = studentModel.getCreated();
        this.totalAbsen = absen.size();
        this.absen = studentModel.getAbsen();
    }
}