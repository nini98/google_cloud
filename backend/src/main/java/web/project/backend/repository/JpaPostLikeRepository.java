package web.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.project.backend.entity.Post_like;

@Repository
public interface JpaPostLikeRepository extends JpaRepository<Post_like, Long>{
}
