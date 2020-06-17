package com.myclass.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myclass.entity.DetailBill;
import com.myclass.entity.Status;

public class ProductDto {

	private int id;
	private String name;
	private int quantity;
	private float price;

	private Date dateProduct;

	private boolean isBeverage;

	private int statusId;

	private Status status;

	private boolean isDelete;

	private String imageUrl;

	private MultipartFile[] fileDatas;

	List<DetailBill> details;

	public ProductDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductDto(int id, String name, String imageUrl) {

		this.id = id;
		this.name = name;

		this.imageUrl = imageUrl;

	}

	public ProductDto(int id, String name, int quantity, float price, Date dateProduct, boolean isBeverage,
			int statusId, Status status, boolean isDelete, String imageUrl, List<DetailBill> details) {

		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.dateProduct = dateProduct;
		this.isBeverage = isBeverage;
		this.statusId = statusId;
		this.status = status;
		this.isDelete = isDelete;
		this.imageUrl = imageUrl;
		this.details = details;
	}

	public ProductDto(int id, String name, int quantity, float price, Date dateProduct, boolean isBeverage,
			int statusId, boolean isDelete, String imageUrl) {

		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.dateProduct = dateProduct;
		this.isBeverage = isBeverage;
		this.statusId = statusId;
		this.isDelete = isDelete;
		this.imageUrl = imageUrl;

	}

	public ProductDto(String name, int quantity, float price, Date dateProduct, boolean isBeverage, int statusId,
			Status status, boolean isDelete, String imageUrl, List<DetailBill> details) {

		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.dateProduct = dateProduct;
		this.isBeverage = isBeverage;
		this.statusId = statusId;
		this.status = status;
		this.isDelete = isDelete;
		this.imageUrl = imageUrl;
		this.details = details;
	}

	public ProductDto(int id, String name, int quantity, float price, Date dateProduct, boolean isBeverage,
			int statusId, Status status, boolean isDelete, String imageUrl) {

		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.dateProduct = dateProduct;
		this.isBeverage = isBeverage;
		this.statusId = statusId;
		this.status = status;
		this.isDelete = isDelete;
		this.imageUrl = imageUrl;

	}

	public ProductDto(String name, int quantity, float price, Date dateProduct, boolean isBeverage, int statusId,
			boolean isDelete, String imageUrl) {

		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.dateProduct = dateProduct;
		this.isBeverage = isBeverage;
		this.statusId = statusId;

		this.isDelete = isDelete;
		this.imageUrl = imageUrl;

	}

	public ProductDto(String name, int quantity, float price, Date dateProduct, boolean isBeverage, int statusId,
			Status status, boolean isDelete, String imageUrl) {

		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.dateProduct = dateProduct;
		this.isBeverage = isBeverage;
		this.statusId = statusId;
		this.status = status;
		this.isDelete = isDelete;
		this.imageUrl = imageUrl;
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

	public Date getDateProduct() {
		return dateProduct;
	}

	public void setDateProduct(Date dateProduct) {
		this.dateProduct = dateProduct;
	}

	public boolean isBeverage() {
		return isBeverage;
	}

	public void setBeverage(boolean isBeverage) {
		this.isBeverage = isBeverage;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public List<DetailBill> getDetails() {
		return details;
	}

	public void setDetails(List<DetailBill> details) {
		this.details = details;
	}

	public MultipartFile[] getFileDatas() {
		return fileDatas;
	}

	public void setFileDatas(MultipartFile[] fileDatas) {
		this.fileDatas = fileDatas;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + ", dateProduct="
				+ dateProduct + ", isBeverage=" + isBeverage + ", statusId=" + statusId + ", status=" + status
				+ ", isDelete=" + isDelete + ", imageUrl=" + imageUrl + ", details=" + details + "]";
	}

}
