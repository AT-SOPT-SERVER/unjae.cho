package org.sopt.global.dto;
import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int currentPage,
        int totalPages,
        long elementsPerPage
) {
}
