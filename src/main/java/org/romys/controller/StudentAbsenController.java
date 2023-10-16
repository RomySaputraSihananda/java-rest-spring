package org.romys.controller;

import org.romys.model.AbsenModel;
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
    public ResponseEntity<BodyResponse<StudentWithAbsenDTO>> GetAbsenStudent(
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
    public ResponseEntity<BodyResponse<StudentWithAbsenDTO>> GetAbsenStudent(
            @RequestParam(name = "id", defaultValue = "1", required = true) long id) {

        return new ResponseEntity<>(
                new BodyResponse<>("ok", HttpStatus.OK.value(),
                        "data with absences id " + id,
                        this.studentAbsenService.getAbsenById(id)),
                HttpStatus.OK);
    }
}
