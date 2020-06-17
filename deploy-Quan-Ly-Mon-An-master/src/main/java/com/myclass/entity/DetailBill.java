package com.myclass.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detail_bill")
public class DetailBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bill_id")
	private Bill bill;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;

	private float price;

	public DetailBill() {
		// TODO Auto-generated constructor stub
	}

	public DetailBill(int id, Bill bill, Product product, int quantity, float price) {
		super();
		this.id = id;
		this.bill = bill;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public DetailBill(Bill bill, Product product, int quantity, float price) {
		super();
		this.bill = bill;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
