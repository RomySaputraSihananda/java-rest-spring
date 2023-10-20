package org.romys.controller;

import java.sql.Timestamp;

import org.romys.model.DAO.AbsenModel;
import org.romys.model.DAO.StudentModel;
import org.romys.model.DTO.RangeDTO;
import org.romys.model.DTO.StudentWithAbsenDTO;
import org.romys.payload.response.BodyResponse;
import org.romys.payload.response.BodyResponsePage;
import org.romys.service.StudentAbsenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/student/absen")
public class StudentAbsenController {
        @Autowired
        private StudentAbsenService studentAbsenService;

        @Operation(summary = "Absen student", description = "API for absen student")
        @PostMapping
        public ResponseEntity<BodyResponse<AbsenModel>> absenStudent(
                        @RequestParam(name = "id", defaultValue = "1", required = true) int id,
                        @RequestParam(name = "keterangan", defaultValue = "hadir", required = true) String keterangan) {
                this.studentAbsenService.absen(id, keterangan);
                return new ResponseEntity<>(
                                new BodyResponse<>("ok", HttpStatus.CREATED.value(),
                                                "student " + id + " berhasil absen",
                                                null),
                                HttpStatus.CREATED);
        }

        @Operation(summary = "Get all student with absen", description = "API for get all student with absen")
        @GetMapping
        public ResponseEntity<BodyResponse<?>> GetAbsenStudent(
                        @RequestParam(name = "page", defaultValue = "1") String page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {

                if (page.equals("all"))
                        return new ResponseEntity<>(
                                        new BodyResponse<>("ok", HttpStatus.OK.value(), "all data with absences",
                                                        this.studentAbsenService.getAbsenAllPage()),
                                        HttpStatus.OK);

                return new ResponseEntity<>(
                                new BodyResponsePage<>("ok", HttpStatus.OK.value(),
                                                "data with absences page " + page + " size " + size,
                                                this.studentAbsenService.getAbsenPage(Integer.parseInt(page),
                                                                size),
                                                page),
                                HttpStatus.OK);
        }

        @Operation(summary = "Get student with absen by id", description = "API for get student with absen by id")
        @GetMapping("/detail")
        public ResponseEntity<BodyResponse<StudentModel>> GetAbsenDetailStudent(
                        @RequestParam(name = "id", defaultValue = "1", required = true) long id) {

                return new ResponseEntity<>(
                                new BodyResponse<>("ok", HttpStatus.OK.value(),
                                                "data with absences id " + id,
                                                this.studentAbsenService.getAbsenById(id)),
                                HttpStatus.OK);
        }

        @Operation(summary = "Get student data and absences by range date", description = "API for get student with absen by id")
        @GetMapping("/range")
        public ResponseEntity<BodyResponsePage<StudentWithAbsenDTO>> GetAbsenStudentByRange(
                        @RequestParam(name = "start", defaultValue = "2023-09-01 00:00:00") String start,
                        @RequestParam(name = "end", defaultValue = "2023-10-01 00:00:00") String end,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {

                return new ResponseEntity<>(
                                new BodyResponsePage<>("ok", HttpStatus.OK.value(),
                                                "data with absences by range " + start + " - "
                                                                + end,
                                                this.studentAbsenService.getAbsenByRange(new RangeDTO(start, end),
                                                                page, size),
                                                Integer.toString(page)),
                                HttpStatus.OK);
        }

        @Operation(summary = "Get who is absent by date range", description = "API for get who is absent by date range")
        @GetMapping("/by")
        public ResponseEntity<BodyResponsePage<StudentWithAbsenDTO>> GetStudentByRange(
                        @RequestParam(name = "start", defaultValue = "2023-09-01 00:00:00") String start,
                        @RequestParam(name = "end", defaultValue = "2023-10-01 00:00:00") String end,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {

                return new ResponseEntity<>(
                                new BodyResponsePage<>("ok", HttpStatus.OK.value(),
                                                "data with absences by range " + start + " - "
                                                                + end,
                                                this.studentAbsenService.getStudentByRange(new RangeDTO(start, end),
                                                                page, size),
                                                Integer.toString(page)),
                                HttpStatus.OK);
        }
}