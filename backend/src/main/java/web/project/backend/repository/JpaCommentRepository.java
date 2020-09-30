package web.project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.project.backend.entity.Post_comment;

public interface JpaCommentRepository extends JpaRepository<Post_comment, Long>{

}
