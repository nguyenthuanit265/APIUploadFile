package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="status")
public class Status {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="status", fetch = FetchType.LAZY,cascade= {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.PERSIST})
	private List<Product> products;
	public Status() {
		// TODO Auto-generated constructor stub
	}

	public Status(int id, String name, List<Product> products) {
		
		this.id = id;
		this.name = name;
		this.products = products;
	}

	public Status(String name, List<Product> products) {
		
		
		this.name = name;
		this.products = products;
	}
	public Status(int id, String name) {
		
		this.id = id;
		this.name = name;
	}
	
	public Status(String name) {
		
		this.name = name;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
