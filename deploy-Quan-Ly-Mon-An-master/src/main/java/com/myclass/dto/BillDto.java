package com.myclass.dto;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.myclass.entity.DetailBill;
import com.myclass.entity.Employee;
import com.myclass.entity.User;

public class BillDto {
	
	
	private int id;

	
	private Date dateBill;

	
	private int userId;

	
	private User user;

	
	private int employeeId;

	
	private Employee employee;

	private float price;

	private String formatNumber;
	
	private List<DetailBill> details;

	public BillDto() {
		// TODO Auto-generated constructor stub
	}

	public BillDto(int id, Date dateBill, int userId, User user, int employeeId, Employee employee, float price,
			String formatNumber, List<DetailBill> details) {
		super();
		this.id = id;
		this.dateBill = dateBill;
		this.userId = userId;
		this.user = user;
		this.employeeId = employeeId;
		this.employee = employee;
		this.price = price;
		this.formatNumber = formatNumber;
		this.details = details;
	}


	public BillDto(Date dateBill, int userId, User user, int employeeId, Employee employee, float price,
			String formatNumber, List<DetailBill> details) {
	
		this.dateBill = dateBill;
		this.userId = userId;
		this.user = user;
		this.employeeId = employeeId;
		this.employee = employee;
		this.price = price;
		this.formatNumber = formatNumber;
		this.details = details;
	}
	

	public BillDto(int id, Date dateBill, int userId, User user, int employeeId, Employee employee, float price, List<DetailBill> details) {
		super();
		this.id = id;
		this.dateBill = dateBill;
		this.userId = userId;
		this.user = user;
		this.employeeId = employeeId;
		this.employee = employee;
		this.price = price;
	
		this.details = details;
	}
	

	public BillDto(Date dateBill, int userId, User user, int employeeId, Employee employee, float price, List<DetailBill> details) {
	
		this.dateBill = dateBill;
		this.userId = userId;
		this.user = user;
		this.employeeId = employeeId;
		this.employee = employee;
		this.price = price;
		
		this.details = details;
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
		return price;
	}

	public void setPrice(float price) {
		
		this.price = price;
	}

	public String getFormatNumber() {
		String pattern = "#,###.###";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		decimalFormat.setGroupingSize(4);

		String formatNumber = decimalFormat.format(price);
		System.out.println(formatNumber);
		return formatNumber;
	}

	public void setFormatNumber(String formatNumber) {
		this.formatNumber = formatNumber;
	}

	public List<DetailBill> getDetails() {
		return details;
	}

	public void setDetails(List<DetailBill> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "BillDto [id=" + id + ", dateBill=" + dateBill + ", userId=" + userId + ", user=" + user
				+ ", employeeId=" + employeeId + ", employee=" + employee + ", price=" + price + ", formatNumber="
				+ formatNumber + ", details=" + details + "]";
	}
	
	
}
