package org.romys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.romys.exception.StudentException;
import org.romys.model.StudentModel;
import org.romys.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public ArrayList<StudentModel> readAllStudents() {
        return (ArrayList<StudentModel>) this.studentRepository.findAll();
    }

    public ArrayList<StudentModel> readStudentsById(long id) {
        try {
            return new ArrayList<>(List.of(this.studentRepository.findById(id).get()));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public void createStudent(StudentModel studentModel) {
        this.studentRepository.save(studentModel);
    }

    public ArrayList<StudentModel> updateStudent(long id, StudentModel newStudentModel) {
        try {
            StudentModel oldStudentModel = this.studentRepository.findById(id).get();

            oldStudentModel.setName(newStudentModel.getName());
            oldStudentModel.setAge(newStudentModel.getAge());
            oldStudentModel.setCity(newStudentModel.getCity());

            this.studentRepository.save(oldStudentModel);

            return new ArrayList<>(List.of(oldStudentModel));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }

    }

    public void deleteStudent(long id) {
        try {
            this.studentRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

}
