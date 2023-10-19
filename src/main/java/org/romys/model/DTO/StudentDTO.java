package org.romys.model.DTO;

import org.romys.model.DAO.StudentModel;

import lombok.Data;

@Data
public class StudentDTO {
    private int id;
    private String name;
    private int age;
    private String city;

    public StudentDTO(StudentModel studentModel) {
        this.id = studentModel.getId();
        this.name = studentModel.getName();
        this.age = studentModel.getAge();
        this.city = studentModel.getCity();
    }
}
