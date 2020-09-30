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
public class Blog extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	@Column
	private Long blog_name; // 블로그 이름
	@Column
	private Long member_id; // 블로그 멤버 아이디
	@Column
	private Long visit_cnt; // 방문자수
}
