package org.sopt.comment.dto;

import lombok.Getter;
import org.sopt.comment.domain.Comment;

@Getter
public class CommentResponseDto  {
    final String content;
    final String author;
    final int likes;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.author = comment.getAuthor().getName();
        this.likes = comment.getLikes().size();
    }
}
