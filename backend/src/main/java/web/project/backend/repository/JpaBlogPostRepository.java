package web.project.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import web.project.backend.entity.Blog_post;

@Repository
public interface JpaBlogPostRepository extends JpaRepository<Blog_post, Long>,
												PostRepository{


	Page<Blog_post> findByblogIdOrderByIdxDesc(Long blogId,Pageable pageable);
	
	@Query(value="SELECT AUTO_INCREMENT FROM information_schema.tables WHERE TABLE_NAME = 'blog_post'", nativeQuery=true)
	Long GetNextIdx();

}



