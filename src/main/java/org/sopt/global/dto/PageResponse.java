package org.sopt.global.dto;
import java.util.List;

public record PageResponse<T>(
        List<T> pageBody,
        int currentPage,
        int totalPages,
        long elementsPerPage
) {
}
