package org.romys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.romys.exception.StudentException;
import org.romys.model.DAO.AbsenModel;
import org.romys.model.DAO.StudentModel;
import org.romys.model.DTO.RangeDTO;
import org.romys.model.DTO.StudentWithAbsenDTO;
import org.romys.repository.AbsenRepository;
import org.romys.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentAbsenService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AbsenRepository absenRepository;

    public void absen(int id, String keterangan) {
        try {
            this.absenRepository.save(new AbsenModel(keterangan, id));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentModel> getAbsenAllPage() {
        try {
            return new ArrayList<StudentModel>(this.studentRepository.findAll());
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentModel> getAbsenPage(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);

            Page<StudentModel> studentPage = this.studentRepository.findAll(pageable);
            List<StudentModel> studentList = studentPage.getContent();

            return new ArrayList<>(studentList);
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentModel> getAbsenById(long id) {
        try {
            return new ArrayList<StudentModel>(List.of(this.studentRepository.findById(id).get()));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentWithAbsenDTO> getAbsenByRange(RangeDTO rangeDTO, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);

            Page<StudentModel> studentPage = this.studentRepository.findAll(pageable);
            List<StudentModel> studentList = studentPage.getContent();

            return new ArrayList<>(studentList.stream()
                    .map(student -> new StudentWithAbsenDTO(
                            student,
                            student.getAbsen().stream()
                                    .filter(absen -> absen.getDate().after(rangeDTO.getStart())
                                            && absen.getDate().before(rangeDTO.getEnd()))
                                    .collect(Collectors.toList())))
                    .collect(Collectors.toList()));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentWithAbsenDTO> getStudentByRange(RangeDTO rangeDTO, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);

            Page<StudentModel> studentPage = this.studentRepository.findAllStudentsInDateRangeInner(
                    rangeDTO.getStart(),
                    rangeDTO.getEnd(), pageable);
            List<StudentModel> studentList = studentPage.getContent();

            return new ArrayList<>(studentList.stream()
                    .map(student -> new StudentWithAbsenDTO(
                            student,
                            student.getAbsen().stream()
                                    .filter(absen -> absen.getDate().after(rangeDTO.getStart())
                                            && absen.getDate().before(rangeDTO.getEnd()))
                                    .collect(Collectors.toList())))
                    .collect(Collectors.toList()));

        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }
}