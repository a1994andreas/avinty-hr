package com.avinty.hr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Email(message = "invalid email format")
	@NotBlank(message = "email cannot be empty")
	private String email;

	@NotBlank(message = "password cannot be empty")
	private String password;

	@NotBlank(message = "employee full_name cannot be empty")
	private String full_name;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="dep_id", nullable = true, insertable=false, updatable=false)
	@JsonIgnore
	private Department department;

	@Column(name = "dep_id")
//	@JsonProperty("dep_id")
	private Integer dep_id;

	@CreationTimestamp
	private Date created_at;

	@CreatedBy
	private Integer created_by;

	@UpdateTimestamp
	private Date updated_at;

	@LastModifiedBy
	private Integer updated_by;

	private Boolean is_active = Boolean.TRUE;
	private String role = "USER";
}
