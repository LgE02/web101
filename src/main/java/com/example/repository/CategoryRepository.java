package com.example.repository;

import com.example.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //Spring Data JPA에서 Category 엔티티와 데이터베이스를 연결하는 역할
}
