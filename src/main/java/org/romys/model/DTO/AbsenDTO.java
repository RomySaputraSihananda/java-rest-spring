package org.romys.model.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.romys.model.DAO.AbsenModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbsenDTO {
    private List<AbsenModel> data;
    private int page;
    private int size;
    private int totalAbsen;

    public AbsenDTO(List<AbsenModel> absen, int page, int size) {
        List<AbsenModel> newAbsen = absen == null ? new ArrayList<AbsenModel>() : absen;

        this.data = Page(newAbsen, page, size);
        this.data = absen;
        this.page = page;
        this.size = size;
        this.totalAbsen = newAbsen.size();
    }

    private List<AbsenModel> Page(List<AbsenModel> absen, int page, int size) {
        // int startIndex = (page - 1) * size;
        // int endIndex = Math.min(startIndex + size, absen.size());

        // if (startIndex >= endIndex) {
        // return Collections.emptyList();
        // }

        // return absen.subList(startIndex, endIndex);

        return absen.stream()
                .skip((page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());

    }
}
