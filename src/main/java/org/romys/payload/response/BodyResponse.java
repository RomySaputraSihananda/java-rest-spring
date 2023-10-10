package org.romys.payload.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BodyResponse<Student> {
        private String status;
        private int code;
        private String message;
        private List<Student> data;
}

// public record BodyResponse<Student>(
// String status, int code, String message, List<Student> data) {
// }
