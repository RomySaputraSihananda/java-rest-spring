package org.romys.model.DTO;

// import java.sql.Timestamp;

import lombok.Data;

@Data
public class RangeDTO {
    private String start;
    private String end;
    private int page;
    private int size;
    private int pageAbsen;
    private int sizeAbsen;

    // public RangeDTO(String start, String end) {
    // this.start = Timestamp.valueOf(start);
    // this.end = Timestamp.valueOf(end);
    // }
}
