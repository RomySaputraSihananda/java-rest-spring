package org.romys.model.DTO;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RangeDTO {
    private Timestamp start;
    private Timestamp end;

    public RangeDTO(String start, String end) {
        this.start = Timestamp.valueOf(start);
        this.end = Timestamp.valueOf(end);
    }
}
