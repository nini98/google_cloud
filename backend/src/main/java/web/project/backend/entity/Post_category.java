package web.project.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

/*
 * 해당 카테고리의 포스트를 찾기 위한 테이블
 * */

@Getter
@Entity
public class Post_category extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	@Column
	private Long category_id; // 카테고리 아이디
	@Column
	private Long post_id; // 포스트 아이디
}
