package org.romys.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.romys.exception.StudentException;
import org.romys.model.DAO.AbsenModel;
import org.romys.model.DAO.StudentModel;
import org.romys.model.DTO.AbsenDTO;
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

    public ArrayList<StudentWithAbsenDTO> getAbsenById(int id, int pageAbsen, int sizeAbsen) {
        try {
            StudentModel student = this.studentRepository.findById(id).get();
            return new ArrayList<StudentWithAbsenDTO>(
                    List.of(new StudentWithAbsenDTO(student, new AbsenDTO(student.getAbsen(), pageAbsen, sizeAbsen))));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<?> getAbsenByRange(RangeDTO rangeDTO) {
        try {
            Pageable pageable = PageRequest.of(rangeDTO.getPage() - 1, rangeDTO.getSize());

            Page<Object[]> studentPage = this.studentRepository.findAllStudentsInDateRangeLeft2(
                    Timestamp.valueOf(rangeDTO.getStart()),
                    Timestamp.valueOf(rangeDTO.getEnd()), pageable);

            List<Object[]> studentList = studentPage.getContent();

            return new ArrayList<>(
                    studentList.stream()
                            .map(e -> e[1])
                            .collect(Collectors.toList()));

        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    public ArrayList<StudentWithAbsenDTO> getStudentByRange(RangeDTO rangeDTO) {
        try {
            Pageable pageable = PageRequest.of(rangeDTO.getPage() - 1, rangeDTO.getSize());

            Page<StudentModel> studentPage = this.studentRepository.findAllStudentsInDateRangeInner(
                    Timestamp.valueOf(rangeDTO.getStart()),
                    Timestamp.valueOf(rangeDTO.getEnd()), pageable);

            List<StudentModel> studentList = studentPage.getContent();

            return new ArrayList<>(studentList.stream()
                    .map(student -> new StudentWithAbsenDTO(
                            student,
                            new AbsenDTO(student.getAbsen(), rangeDTO.getPageAbsen(), rangeDTO.getSizeAbsen())))
                    .collect(Collectors.toList()));
        } catch (NoSuchElementException e) {
            throw new StudentException("student not found");
        }
    }

    private ArrayList<StudentWithAbsenDTO> filterDate(List<StudentModel> studentList, RangeDTO rangeDTO) {
        return new ArrayList<>(studentList.stream()
                .map(student -> new StudentWithAbsenDTO(
                        student, new AbsenDTO(student.getAbsen().stream()
                                .filter(absen -> absen.getDate().after(Timestamp.valueOf(rangeDTO.getStart()))
                                        && absen.getDate().before(Timestamp.valueOf(rangeDTO.getEnd())))
                                .collect(Collectors.toList()), rangeDTO.getPageAbsen(), rangeDTO.getSizeAbsen())))
                .collect(Collectors.toList()));
    }

    private AbsenDTO generateAbsen(AbsenModel absenModel, RangeDTO rangeDTO) {
        if (absenModel == null) {
            List<AbsenModel> absenModelList = new ArrayList<AbsenModel>(List.of(absenModel));

            return new AbsenDTO(absenModelList, rangeDTO.getPageAbsen(),
                    rangeDTO.getSizeAbsen());
        } else {
            return null;
        }
    }
}