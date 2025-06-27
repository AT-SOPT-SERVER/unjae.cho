package org.sopt.post.domain.submodel;

import lombok.Getter;

@Getter
public enum Tag {
    SERVER("서버"),
    DATABASE("데이터베이스"),
    INFRA("인프라"),
    OTHER("기타");

    private final String tagName;

    Tag(String name) {
        this.tagName = name;
    }
}
