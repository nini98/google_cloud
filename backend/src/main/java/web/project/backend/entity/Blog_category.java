package web.project.backend.entity;

import javax.persistence.*;


import lombok.Getter;

@Getter
@Entity(name="blog_category")
public class Blog_category extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	@Column
	private Long blog_id; //블로그 아이디
	@Column(nullable = false)
	private String name; // 카테고리 이름
}
