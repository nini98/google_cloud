package web.project.backend.entity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name="blog_post")
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog_post extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	@NonNull
	@Column(name = "blog_id")
	private Long blogId; //블로그 아이디
	@Column
	@NonNull
	private String title; //포스트 제목
	@Column
	private int enabled; //포스트 숨김기능
	@Column
	private int views; //조회수
	@Column(columnDefinition = "TEXT", nullable = false)
	@NonNull
    private String content;
	@Column
	private int likes; // post_like 테이블의 cnt를 해서 저장함.
	@Column
	private String tag; // tag는 ,로 구분
	
}
