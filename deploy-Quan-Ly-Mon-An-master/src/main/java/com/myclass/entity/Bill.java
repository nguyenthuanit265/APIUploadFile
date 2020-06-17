package com.myclass.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date_bill")
	private Date dateBill;

	@Column(name = "user_id")
	private int userId;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;

	@Column(name = "employee_id")
	private int employeeId;

	@ManyToOne
	@JoinColumn(name = "employee_id", insertable = false, updatable = false)
	private Employee employee;

	private float price;


	@OneToMany(mappedBy = "bill" , cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<DetailBill> details;

	public Bill() {
		// TODO Auto-generated constructor stub
	}

	public Bill(int id, Date dateBill, int userId, User user, int employeeId, Employee employee, float price) {
		super();
		this.id = id;
		this.dateBill = dateBill;
		this.userId = userId;
		this.user = user;
		this.employeeId = employeeId;
		this.employee = employee;
		this.price = price;
	}

	public Bill(Date dateBill, int userId, User user, int employeeId, Employee employee, float price) {

		this.dateBill = dateBill;
		this.userId = userId;
		this.user = user;
		this.employeeId = employeeId;
		this.employee = employee;
		this.price = price;
	}

	public Bill(Date dateBill, int userId, int employeeId, float price) {

		this.dateBill = dateBill;
		this.userId = userId;

		this.employeeId = employeeId;
		
		this.price = price;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateBill() {
		return dateBill;
	}

	public void setDateBill(Date dateBill) {
		this.dateBill = dateBill;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public float getPrice() {
//		String pattern = "#,###.###";
//		DecimalFormat decimalFormat = new DecimalFormat(pattern);
//		decimalFormat.setGroupingSize(4);
//
//		String number = decimalFormat.format(123456789.123);
//		System.out.println(number);
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<DetailBill> getDetails() {
		return details;
	}

	public void setDetails(List<DetailBill> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", dateBill=" + dateBill + ", userId=" + userId + ", user=" + user + ", employeeId="
				+ employeeId + ", employee=" + employee + ", price=" + price + "]";
	}

}
