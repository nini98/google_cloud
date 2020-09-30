package web.project.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;


@Getter
@Entity(name="post_like")
public class Post_like extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	@Column
	private Long post_id; // 포스트 아이디
	@Column
	private Long user_id; // 유저 아이디
}
