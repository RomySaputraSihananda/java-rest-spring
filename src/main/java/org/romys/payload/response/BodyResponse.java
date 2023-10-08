package org.romys.payload.response;

import java.util.List;

public record BodyResponse<Student>(
        String status, int code, String message, List<Student> data) {
}
