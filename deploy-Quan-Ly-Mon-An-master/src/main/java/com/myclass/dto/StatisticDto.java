package com.myclass.dto;

public class StatisticDto {
	
	private int year;
	private long totalBill;
	private double totalPrice;
	private String formatTotalPrice;
	
	public StatisticDto() {
		// TODO Auto-generated constructor stub
	}

	public StatisticDto(int year, long totalBill, double totalPrice) {
		super();
		this.year = year;
		this.totalBill = totalBill;
		this.totalPrice = totalPrice;
		this.formatTotalPrice = String.format("%.2f", totalPrice);

	}
	public StatisticDto(int year, long totalBill, String formatTotalPrice) {
		
		this.year = year;
		this.totalBill = totalBill;
		this.formatTotalPrice = Double.toString(totalPrice);
	}

	public String getFormatTotalPrice() {
		return formatTotalPrice;
	}

	public void setFormatTotalPrice(String formatTotalPrice) {
		this.formatTotalPrice = formatTotalPrice;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	@Override
	public String toString() {
		return "StatisticDto [year=" + year + ", totalBill=" + totalBill + ", totalPrice=" + totalPrice
				+ ", formatTotalPrice=" + formatTotalPrice + "]";
	}


}
