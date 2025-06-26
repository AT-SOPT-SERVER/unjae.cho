package org.sopt.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.sopt.post.domain.Post;
import org.sopt.post.domain.QPost;
import org.sopt.user.domain.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> searchPosts(String title, String author, Pageable pageable) {
        QPost post = QPost.post;
        QUser user = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();
        if (title != null && !title.isEmpty()) {
            builder.and(post.title.contains(title));
        }
        if (author != null && !author.isEmpty()) {
            builder.and(post.user.name.eq(author));
        }

        List<Post> content = queryFactory
                .selectFrom(post)
                .leftJoin(post.user, user).fetchJoin()
                .where(builder)
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())   //현재 페이지
                .limit(pageable.getPageSize())  //페이지 당 게시글
                .fetch();

        long total = queryFactory
                .selectFrom(post)
                .where(builder)
                .fetch()
                .size();

        return new PageImpl<>(content, pageable, total);
    }
}

