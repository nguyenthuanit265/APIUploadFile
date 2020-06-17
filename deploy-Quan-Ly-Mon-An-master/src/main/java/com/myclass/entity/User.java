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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Vui lòng nhập email!")
	@Email(message = "Email không đúng định dạng!")
	private String email;

	@NotBlank(message = "Vui lòng nhập họ tên!")
	private String name;

	// @NotBlank(message = "Vui lòng nhập mật khẩu!")
	
	private String address;
	private String phone;

	@Column(name = "is_delete")
	private boolean isDelete;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private List<Bill> bills;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id,
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			String address, String phone,
			List<Bill> bills) {

		this.id = id;
		this.email = email;
		this.name = name;
		
		this.address = address;
		this.phone = phone;
		this.bills = bills;
	}

	public User(
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			 String address, String phone,
			List<Bill> bills) {

		this.email = email;
		this.name = name;
		
		this.address = address;
		this.phone = phone;
		this.bills = bills;
	}

	public User(int id,
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			String address, String phone,
			boolean isDelete, List<Bill> bills) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		
		this.address = address;
		this.phone = phone;
		this.isDelete = isDelete;
		this.bills = bills;
	}

	public User(
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			String address, String phone,
			boolean isDelete, List<Bill> bills) {

		this.email = email;
		this.name = name;
		
		this.address = address;
		this.phone = phone;
		this.isDelete = isDelete;
		this.bills = bills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", isDelete=" + isDelete + ", bills=" + bills + "]";
	}

}
