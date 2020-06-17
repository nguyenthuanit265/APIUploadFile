package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name="roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Vui lòng nhập tên!")
	@Column(name="name")
	private String name;
	
	@NotBlank(message = "Vui lòng nhập mô tả!")
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy = "role",
			fetch = FetchType.LAZY,cascade= {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.PERSIST})
	private List<Employee> employees;

	public Role() {
		
	}

	public Role(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
