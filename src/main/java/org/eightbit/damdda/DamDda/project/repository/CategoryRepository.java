package org.eightbit.damdda.DamDda.project.repository;

import org.eightbit.damdda.DamDda.project.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
