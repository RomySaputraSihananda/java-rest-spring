package org.romys.service;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.romys.exception.StudentException;
import org.romys.model.DAO.AbsenModel;
import org.romys.model.DAO.StudentModel;
import org.romys.model.DTO.RangeDTO;
import org.romys.model.DTO.StudentDTO;
import org.romys.model.DTO.StudentWithAbsenDTO;
import org.romys.repository.AbsenRepository;
import org.romys.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAbsenService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AbsenRepository absenRepository;

    @Autowired
    private StudentService studentService;

    public void absen(int id, String keterangan) {
        try {
            // if (!this.studentRepository.existsById((long) id))
            // throw new StudentException("student not found");
            this.absenRepository.save(new AbsenModel(keterangan, id));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentModel> getAbsenAllPage() {
        try {
            // ArrayList<StudentModel> students = this.studentService.readAllStudents();

            // List<StudentWithAbsenDTO> studentWithAbsenDTO = students.stream()
            // .map(student -> new StudentWithAbsenDTO(student,
            // absenRepository.findAbsenByStudentId(student.getId())))
            // .collect(Collectors.toList());

            // return new ArrayList<StudentWithAbsenDTO>(studentWithAbsenDTO);
            return new ArrayList<StudentModel>(this.studentRepository.findAll());
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentModel> getAbsenPage(int page, int size) {
        try {
            return this.studentService.readStudentsPage(page, size);
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentModel> getAbsenById(long id) {
        try {
            // StudentModel student = this.studentRepository.findById(id).get();
            // return new ArrayList<>(
            // List.of(new StudentWithAbsenDTO(student,
            // absenRepository.findAbsenByStudentId(student.getId()))));
            return new ArrayList<StudentModel>(List.of(this.studentRepository.findById(id).get()));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<AbsenModel> getAbsenByRange(RangeDTO rangeDTO) {
        return new ArrayList<>(this.absenRepository.findByDateBetween(rangeDTO.getStart(), rangeDTO.getEnd()));
    }
}
