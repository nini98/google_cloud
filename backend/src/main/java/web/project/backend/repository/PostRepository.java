package web.project.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import web.project.backend.entity.Blog_post;


public interface PostRepository {
	
	Page<Blog_post> findByblogIdOrderByIdxDesc(Long blogId,Pageable pageable);
	
	Optional<Blog_post> findById(Long postId);
	
	Long GetNextIdx();
	
	Blog_post save(Blog_post post);
	
	void delete(Blog_post post);
}
