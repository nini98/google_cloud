package web.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.project.backend.entity.Post_comment;

@Repository
public interface JpaPostCommentRepository extends JpaRepository<Post_comment, Long>{
}
