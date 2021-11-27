package com.sparta.weeklytestspring.repository;

import com.sparta.weeklytestspring.domain.Comment;
import com.sparta.weeklytestspring.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
