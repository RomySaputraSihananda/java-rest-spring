package org.romys.model.DTO;

import java.sql.Timestamp;

import org.romys.model.AbsenModel;

import lombok.Data;

@Data
public class AbsenDTO {
    private String keterangan;
    private Timestamp date;

    public AbsenDTO(AbsenModel absenModel) {
        this.keterangan = absenModel.getKeterangan();
        this.date = absenModel.getDate();
    }
}
