package org.romys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.romys.exception.StudentException;
import org.romys.model.DAO.StudentModel;
import org.romys.model.DTO.StudentDTO;
import org.romys.repository.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public ArrayList<StudentDTO> readAllStudents() {
        return filter((ArrayList<StudentModel>) this.studentRepository.findAll());
    }

    public ArrayList<StudentDTO> readStudentsPage(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<StudentModel> studentPage = this.studentRepository.findAll(pageable);
        List<StudentModel> studentList = studentPage.getContent();

        return filter(new ArrayList<>(studentList));
    }

    public ArrayList<StudentDTO> readStudentsById(long id) {
        try {
            return filter(new ArrayList<>(List.of(this.studentRepository.findById(id).get())));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public void createStudent(StudentDTO studentDTO) {
        this.studentRepository.save(new StudentModel(studentDTO));
    }

    public ArrayList<StudentDTO> updateStudent(long id, StudentModel newStudentModel) {
        try {
            StudentModel oldStudentModel = this.studentRepository.findById(id).get();

            oldStudentModel.setName(newStudentModel.getName());
            oldStudentModel.setAge(newStudentModel.getAge());
            oldStudentModel.setCity(newStudentModel.getCity());

            this.studentRepository.save(oldStudentModel);

            return filter(new ArrayList<>(List.of(oldStudentModel)));
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

    public ArrayList<StudentDTO> search(String name) {
        try {
            return new ArrayList<>(this.studentRepository.findByNameLike(name));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentDTO> filter(ArrayList<StudentModel> studentModels) {
        return studentModels.stream()
                .map(StudentDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
