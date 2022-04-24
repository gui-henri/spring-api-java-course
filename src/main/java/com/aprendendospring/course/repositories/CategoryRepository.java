package com.aprendendospring.course.repositories;

import com.aprendendospring.course.entities.Category;
import com.aprendendospring.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
