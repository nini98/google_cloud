package web.project.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;


@Getter
@Entity(name="comment_like")
public class Comment_like extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	@Column
	private Long comment_id; //코멘트가 가지고 있는 아이디
	@Column
	private Long user_id; //유저 아이디
}
