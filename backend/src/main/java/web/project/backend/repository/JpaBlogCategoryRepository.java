package web.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.project.backend.entity.Blog_category;

@Repository
public interface JpaBlogCategoryRepository extends JpaRepository<Blog_category, Long>{

}
