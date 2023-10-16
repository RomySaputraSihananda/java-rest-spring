package org.romys.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// CREATE TABLE absen (
//     id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
//     date TIMESTAMP default CURRENT_TIMESTAMP,
//     student_id int,
//     FOREIGN KEY (student_id) REFERENCES students(id)
// );

@Data
@NoArgsConstructor
@Entity
@Table(name = "absen")
public class AbsenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "student_id")
    private int student_id;

    // @ManyToOne
    // @JoinColumn(name = "student_id", referencedColumnName = "id")
    // private int student_id;

    public AbsenModel(String keterangan, int student_id) {
        this.keterangan = keterangan;
        this.student_id = student_id;
    }
}
