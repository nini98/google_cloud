package web.project.backend.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;


/*
 * 필요한 테이블에 기본으로 적용하는 Entity
 * 
 * index, createdDate, ModifiedDate
 * 
 * */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
	
	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime CreatedDate;
	@Column(name = "created_by")
	private Long CreatedBy;
	@LastModifiedDate
	@Column(name = "modified_date")
	private LocalDateTime ModifiedDate;
	@Column(name = "modified_by")
	private Long ModifiedBy;
}
