package com.app.appbackend.repository;

import com.app.appbackend.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroEntity,Long> {
}
