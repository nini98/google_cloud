package web.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.project.backend.entity.Blog;

@Repository
public interface JpaBlogRepository extends JpaRepository<Blog, Long>{

}
