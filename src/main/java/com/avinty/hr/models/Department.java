package com.avinty.hr.models;

import com.avinty.hr.listeners.DepartmentListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners({AuditingEntityListener.class, DepartmentListener.class})
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Department {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "department name cannot be empty")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="manager_id", nullable = true, insertable=false, updatable=false)
	@JsonIgnore
	private Employee manager;

	@Column(name = "manager_id")
	@JsonProperty("manager_id")
	private Integer manager_id;

	@OneToMany ( mappedBy = "department")
	private List<Employee> employees;

	@CreationTimestamp
	private Date created_at;

	@CreatedBy
	private Integer created_by;

	@UpdateTimestamp
	private Date updated_at;

	@LastModifiedBy
	private Integer updated_by;
}
