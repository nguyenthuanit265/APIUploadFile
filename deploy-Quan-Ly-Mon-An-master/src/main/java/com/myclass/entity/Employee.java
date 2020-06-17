package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Vui lòng nhập email!")
	@Email(message = "Email không đúng định dạng!")
	private String email;

	@NotBlank(message = "Vui lòng nhập họ tên!")
	private String name;

	// @NotBlank(message = "Vui lòng nhập mật khẩu!")
	@Length(min = 6, message = "Mật khẩu từ min 6 ký tự!")
	private String password;
	private String phone;

	@Column(name = "is_delete")
	private boolean isDelete;

	@Column(name = "role_id")
	@NotBlank(message = "Vui lòng chọn loại người dùng!")
	private String roleId;

	@ManyToOne()
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	private List<Bill> bills;

	public Employee() {
		
	}

	public Employee(int id,
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			@Length(min = 6, message = "Mật khẩu từ min 6 ký tự!") String password, String phone,
			@NotBlank(message = "Vui lòng chọn loại người dùng!") String roleId, Role role, List<Bill> bills) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.roleId = roleId;
		this.role = role;
		this.bills = bills;
	}

	public Employee(
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			@Length(min = 6, message = "Mật khẩu từ min 6 ký tự!") String password, String phone,
			@NotBlank(message = "Vui lòng chọn loại người dùng!") String roleId, Role role, List<Bill> bills) {

		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.roleId = roleId;
		this.role = role;
		this.bills = bills;
	}
	public Employee(int id,
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			@Length(min = 6, message = "Mật khẩu từ min 6 ký tự!") String password, String phone, boolean isDelete,
			@NotBlank(message = "Vui lòng chọn loại người dùng!") String roleId, Role role, List<Bill> bills) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.isDelete = isDelete;
		this.roleId = roleId;
		this.role = role;
		this.bills = bills;
	}
	
	public Employee(
			@NotBlank(message = "Vui lòng nhập email!") @Email(message = "Email không đúng định dạng!") String email,
			@NotBlank(message = "Vui lòng nhập họ tên!") String name,
			@Length(min = 6, message = "Mật khẩu từ min 6 ký tự!") String password, String phone, boolean isDelete,
			@NotBlank(message = "Vui lòng chọn loại người dùng!") String roleId, Role role, List<Bill> bills) {
		
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.isDelete = isDelete;
		this.roleId = roleId;
		this.role = role;
		this.bills = bills;
	}
	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", phone="
				+ phone + ", roleId=" + roleId + ", role=" + role + ", bills=" + bills + "]";
	}

}
