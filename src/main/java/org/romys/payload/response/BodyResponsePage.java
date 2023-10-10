package org.romys.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class BodyResponsePage<Student> extends BodyResponse<Student> {
    private String page;
    private int dataLength;

    public BodyResponsePage(String status, int code, String message, List<Student> data,
            String page) {
        super(status, code, message, data);
        this.page = page;
        this.dataLength = data.size();
    }
}
