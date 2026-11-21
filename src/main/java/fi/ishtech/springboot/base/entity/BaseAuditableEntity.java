package fi.ishtech.springboot.base.entity;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for entities having audit columns
 *
 * @author Muneer Ahmed Syed
 */
@EnableJpaAuditing
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseAuditableEntity extends BaseEntity {

	@Serial
	private static final long serialVersionUID = -2330487755033212668L;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedBy
	@Column(name = "updated_by")
	private Long updatedBy;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}