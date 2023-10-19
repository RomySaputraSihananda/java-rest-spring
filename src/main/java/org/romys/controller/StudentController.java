package org.romys.controller;

import java.util.ArrayList;
import java.util.List;

import org.romys.model.DAO.StudentModel;
import org.romys.model.DTO.StudentDTO;
import org.romys.payload.response.BodyResponse;
import org.romys.payload.response.BodyResponsePage;
import org.romys.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

        @Autowired
        private StudentService studentService;

        // @Operation(summary = "Get all student info", description = "API for get all
        // student info")
        // @GetMapping
        // public ResponseEntity<BodyResponse<StudentModel>> getAllStudents() {
        // return new ResponseEntity<>(
        // new BodyResponse<>("ok", HttpStatus.OK.value(), "all data",
        // this.studentService.readAllStudents()),
        // HttpStatus.OK);
        // }

        @Operation(summary = "Get student info", description = "API for get all student info")
        @GetMapping
        public ResponseEntity<BodyResponse<?>> getStudents(
                        @RequestParam(name = "page", defaultValue = "1") String page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {
                if (page.equals("all"))
                        return new ResponseEntity<>(
                                        new BodyResponse<>("ok", HttpStatus.OK.value(), "all data",
                                                        this.studentService.readAllStudents()),
                                        HttpStatus.OK);

                return new ResponseEntity<>(
                                new BodyResponsePage<>("ok", HttpStatus.OK.value(),
                                                "data page " + page + " size " + size,
                                                this.studentService.readStudentsPage(Integer.parseInt(page),
                                                                size),
                                                page),
                                HttpStatus.OK);
        }

        @Operation(summary = "Get student info by id", description = "API for get student info by id")
        @GetMapping("/detail")
        public ResponseEntity<BodyResponse<?>> getStudentById(
                        @RequestParam(name = "id", defaultValue = "1") long id) {
                return new ResponseEntity<>(
                                new BodyResponse<>("ok", HttpStatus.OK.value(), "data by id " + id,
                                                this.studentService.readStudentsById(id)),
                                HttpStatus.OK);
        }

        @Operation(summary = "Add student info", description = "API for add student info")
        @PostMapping
        public ResponseEntity<BodyResponse<StudentDTO>> addStudent(@RequestBody(required = true) StudentDTO body) {
                this.studentService.createStudent(body);
                return new ResponseEntity<>(
                                new BodyResponse<>("ok", HttpStatus.CREATED.value(), "data berhasil ditambah",
                                                new ArrayList<>(List.of(body))),
                                HttpStatus.CREATED);
        }

        @Operation(summary = "Update student info", description = "API for update student info")
        @PutMapping
        public ResponseEntity<BodyResponse<?>> updateStudent(
                        @RequestParam(name = "id", defaultValue = "1", required = true) long id,
                        @RequestBody(required = true) StudentDTO body) {
                return new ResponseEntity<>(
                                new BodyResponse<>("ok", HttpStatus.OK.value(), "data berhasil ditambah",
                                                this.studentService.updateStudent(id, new StudentModel(body))),
                                HttpStatus.OK);
        }

        @Operation(summary = "Delete student info", description = "API for delete student info")
        @DeleteMapping
        public ResponseEntity<BodyResponse<StudentModel>> deleteStudent(
                        @RequestParam(name = "id", defaultValue = "1", required = true) long id) {
                this.studentService.deleteStudent(id);
                return new ResponseEntity<>(
                                new BodyResponse<>("ok", HttpStatus.OK.value(), "data berhasil dihapus", null),
                                HttpStatus.OK);
        }
}
