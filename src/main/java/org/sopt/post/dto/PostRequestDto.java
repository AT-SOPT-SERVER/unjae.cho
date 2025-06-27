package org.sopt.post.dto;

import java.util.List;

public record PostRequestDto(
        String title,
        String contents,
        String tags
) {
}
