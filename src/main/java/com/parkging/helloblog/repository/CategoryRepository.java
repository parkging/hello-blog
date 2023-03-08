package com.parkging.helloblog.repository;

import com.parkging.helloblog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c left join fetch c.parent p left join fetch c.child order by nvl(p.Id, c.Id), c.Id")
    public List<Category> findAll();

    Optional<Category> findByName(String categoryName);
}
