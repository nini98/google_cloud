package web.project.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import web.project.backend.entity.Blog_post;
import web.project.backend.security.CurrentUser;
import web.project.backend.security.service.MyUserDetails;
import web.project.backend.service.PostService;

@RestController
@RequestMapping("blog")
@Log4j2
public class PostController {
	
	@Autowired
	private PostService postService;

	
	@GetMapping("/{blogid}/post/{postid}")
	public ResponseEntity<?> getPost(@PathVariable Long blogid,
									@PathVariable Long postid)
	{
		
		Blog_post post = postService.getPost(blogid, postid);
		
		return ResponseEntity.ok(post);
		
	}
	
	@GetMapping(value = "/{blogid}")
    public ResponseEntity<List<Blog_post>> getPostList(@PathVariable Long blogid,
    													Pageable pageable) {
        log.debug("REST request to get Posts : {}", pageable);
        Page<Blog_post> posts = postService.findAllByOrderByCreatedDateDescPageable(blogid,pageable);
        //Page<PostDto> postDto = posts.map(post -> new PostDto((post)));
        return new ResponseEntity<>(posts.getContent(), HttpStatus.OK);
    }
	
	@PostMapping(value = "/postwrite")
    public ResponseEntity<?> registerPost(@CurrentUser MyUserDetails myUserDetails,
    										@RequestBody Blog_post post) throws Exception {
		
		
		if(postService.RegisterPost(myUserDetails.getId(), post))
		{
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
    }
}
