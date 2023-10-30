package org.romys.payload.response;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BodyResponsePage<Student> extends BodyResponse<Student> {
    private String page;
    private int dataLength;

    public BodyResponsePage(String status, int code, String message, ArrayList<Student> data,
            String page) {
        super(status, code, message, data);
        this.page = page;
        this.dataLength = data.size();
    }
}