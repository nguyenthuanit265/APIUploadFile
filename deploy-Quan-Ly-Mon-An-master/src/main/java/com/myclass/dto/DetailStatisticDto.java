package com.myclass.dto;

public class DetailStatisticDto {
	private int monhth;
	private long totalBill;
	private double totalPrice;
	private String formatTotalPrice;
	
	public DetailStatisticDto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public int getMonhth() {
		return monhth;
	}



	public void setMonhth(int monhth) {
		this.monhth = monhth;
	}



	public long getTotalBill() {
		return totalBill;
	}



	public void setTotalBill(long totalBill) {
		this.totalBill = totalBill;
	}



	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getFormatTotalPrice() {
		return formatTotalPrice;
	}



	public void setFormatTotalPrice(String formatTotalPrice) {
		this.formatTotalPrice = formatTotalPrice;
	}



	public DetailStatisticDto(int monhth, long totalBill, double totalPrice, String formatTotalPrice) {
		super();
		this.monhth = monhth;
		this.totalBill = totalBill;
		this.totalPrice = totalPrice;
		this.formatTotalPrice = formatTotalPrice;
	}


	public DetailStatisticDto(int monhth, long totalBill, double totalPrice) {
		super();
		this.monhth = monhth;
		this.totalBill = totalBill;
		this.totalPrice = totalPrice;
		this.formatTotalPrice = String.format("%.2f", totalPrice);

	}



	@Override
	public String toString() {
		return "DetailStatisticDto [monhth=" + monhth + ", totalBill=" + totalBill + ", totalPrice=" + totalPrice
				+ ", formatTotalPrice=" + formatTotalPrice + "]";
	}
	
}
